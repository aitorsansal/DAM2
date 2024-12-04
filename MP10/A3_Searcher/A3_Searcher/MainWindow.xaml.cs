using System.Collections.Concurrent;
using System.Collections.ObjectModel;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using Microsoft.Win32;
using Path = System.IO.Path;

namespace A3_Searcher;

public partial class MainWindow : Window
{
    /// <summary>
    /// Gestiona el token de cancel·lació per permetre aturar threads de manera controlada.
    /// </summary>
    private CancellationTokenSource _cancellationTokenSource;

    /// <summary>
    /// Col·lecció observable que conté els resultats trobats; està vinculada al ListView de la UI.
    /// </summary>
    public ObservableCollection<FileResult> Results { get; set; } = new();



    /// <summary>
    /// Emmagatzema la ruta de la carpeta arrel seleccionada per iniciar la cerca.
    /// </summary>
    public string SelectedFolder
    {
        get => (string)GetValue(SelectedFolderProperty);
        set => SetValue(SelectedFolderProperty, value);
    }

    public static readonly DependencyProperty SelectedFolderProperty = DependencyProperty.Register(
        nameof(SelectedFolder), typeof(string), typeof(MainWindow), new PropertyMetadata(default(string)));

    /// <summary>
    /// Conté el nombre màxim de threads que pot gestionar el sistema (igual al nombre de processadors).
    /// </summary>
    public int MaxThreads { get; } = Environment.ProcessorCount;

    /// <summary>
    /// Guarda els fitxers ja processats per evitar duplicats.
    /// </summary>
    private readonly HashSet<string> processedFiles = new();
    private readonly HashSet<string> processedFolders = new();

    /// <summary>
    /// Objecte per bloquejar l'accés als recursos compartits entre threads.
    /// </summary>
    private readonly object lockObject = new object();

    /// <summary>
    /// Cua segura per a múltiples threads que conté carpetes pendents de processar.
    /// </summary>
    private BlockingCollection<string> folderQueue = new BlockingCollection<string>();

    /// <summary>
    /// Comptador del nombre total d'arxius processats durant la cerca.
    /// </summary>
    private int fileCount = 0;

    /// <summary>
    /// Temporitzador per mesurar el temps transcorregut durant la cerca.
    /// </summary>
    private System.Diagnostics.Stopwatch stopwatch = new System.Diagnostics.Stopwatch();

    /// <summary>
    /// Constructor de la finestra principal. Inicialitza els components i vincula els resultats al ListView.
    /// </summary>
    public MainWindow()
    {
        InitializeComponent();
        ResultsListView.ItemsSource = Results;
        SelectedFolder = "C:\\";
        DataContext = this;
    }

    /// <summary>
    /// Mostra un diàleg perquè l'usuari seleccioni la carpeta arrel per començar la cerca.
    /// Desa la carpeta seleccionada a la variable `selectedFolder`.
    /// </summary>
    private void SelectFolder_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new OpenFolderDialog();
        if (dialog.ShowDialog() == true)
        {
            SelectedFolder = dialog.FolderName;
        }
    }

    /// <summary>
    /// Inicia la cerca multi-threading. Gestiona la cua de carpetes i els threads.
    /// Mostra un missatge amb el temps transcorregut i els resultats processats.
    /// </summary>
    private async void StartSearch_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(SelectedFolder))
        {
            MessageBox.Show("Please select a folder first.", "Error", MessageBoxButton.OK, MessageBoxImage.Warning);
            return;
        }

        Results.Clear();
        processedFiles.Clear();
        processedFolders.Clear();
        folderQueue = new BlockingCollection<string>();
        fileCount = 0;
        stopwatch.Reset();
        stopwatch.Start();

        _cancellationTokenSource = new CancellationTokenSource();
        folderQueue.Add(SelectedFolder);

        try
        {
            await ProcessFolders(FileNameTextBox.Text, _cancellationTokenSource.Token); // Await the async ProcessFolders
        }
        catch (OperationCanceledException)
        {
            MessageBox.Show($"Search canceled. Files processed: {Results.Count}", "Canceled");
        }
        finally
        {
            _cancellationTokenSource.Dispose();
            stopwatch.Stop();
            folderQueue.CompleteAdding();
            MessageBox.Show($"Search completed in {stopwatch.Elapsed}. Total files found: {Results.Count}", "Done");
        }
    }

    /// <summary>
    /// Atura la cerca mitjançant el token de cancel·lació.
    /// </summary>
    private void StopSearch_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            folderQueue.CompleteAdding();
            _cancellationTokenSource.Cancel();
        }
        catch (Exception exception)
        {
            // ignored
        }
    }

    /// <summary>
    /// Processa les carpetes de manera recursiva. Cerca fitxers que coincideixin amb el nom indicat
    /// i afegeix subcarpetes a la cua.
    /// </summary>
    private async Task ProcessFolders(string fileName, CancellationToken token)
    {
        int maxConcurrency = (int)ThreadSlider.Value;
        var tasks = new List<Task>();

        while (folderQueue.Count > 0 || tasks.Count > 0)
        {
            if (tasks.Count < maxConcurrency && folderQueue.Count > 0)
            {
                var task = Task.Run(() => ProcessFolderAsync(fileName, token), token);
                tasks.Add(task);
            }

            var completedTask = await Task.WhenAny(tasks);
            tasks.Remove(completedTask);

            await completedTask;
        }
    }

    private async Task ProcessFolderAsync(string fileName, CancellationToken token)
    {
        try
        {
            string currentFolder;

            lock (lockObject)
            {
                if (folderQueue.Count == 0)
                    return;

                currentFolder = folderQueue.Take(token);
            }

            var files = Directory.GetFiles(currentFolder, $"*{fileName}*", SearchOption.TopDirectoryOnly);
            foreach (var file in files)
            {
                lock (lockObject)
                {
                    if (processedFiles.Add(file))
                    {
                        Dispatcher.Invoke(() =>
                        {
                            Results.Add(new FileResult
                            {
                                Name = Path.GetFileName(file),
                                Path = file
                            });
                        });
                    }
                }
            }

            foreach (var subFolder in Directory.GetDirectories(currentFolder))
            {
                lock (lockObject)
                {
                    if (processedFolders.Add(subFolder))
                    {
                        folderQueue.Add(subFolder, token);
                    }
                }
            }
        }
        catch (UnauthorizedAccessException)
        {
            //ignored
        }
    }

    /// <summary>
    /// Detecta el doble clic a un element del ListView i obre el fitxer o la carpeta corresponent.
    /// Aquesta funció ja està completament implementada.
    /// </summary>
    private void ResultsListView_MouseDoubleClick(object sender, System.Windows.Input.MouseButtonEventArgs e)
    {
        if (ResultsListView.SelectedItem is FileResult selectedFile)
        {
            var hitTest = ResultsListView.InputHitTest(e.GetPosition(ResultsListView)) as FrameworkElement;

            if (hitTest != null && hitTest.DataContext is FileResult)
            {
                if (hitTest is TextBlock textBlock && textBlock.Text == selectedFile.Name)
                {
                    OpenFile_Click(selectedFile);
                }
                else if (hitTest is TextBlock textBlockPath && textBlockPath.Text == selectedFile.Path)
                {
                    OpenDirectory_Click(selectedFile);
                }
            }
        }
    }

    /// <summary>
    /// Obre el fitxer seleccionat amb l'aplicació predeterminada.
    /// </summary>
    private void OpenFile_Click(FileResult file)
    {
        try
        {
            if (File.Exists(file.Path))
            {
                System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo
                {
                    FileName = file.Path,
                    UseShellExecute = true
                });
            }
            else
            {
                MessageBox.Show("The selected file does not exist.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
        catch (Exception ex)
        {
            MessageBox.Show($"An error occurred while opening the file: {ex.Message}", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
        }
    }

    /// <summary>
    /// Obre la carpeta que conté el fitxer seleccionat.
    /// </summary>
    private void OpenDirectory_Click(FileResult file)
    {
        try
        {
            if (File.Exists(file.Path))
            {
                System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo
                {
                    FileName = "explorer.exe",
                    Arguments = $"/select,\"{file.Path}\"",
                    UseShellExecute = true
                });
            }
            else
            {
                MessageBox.Show("The selected file does not exist.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
        catch (Exception ex)
        {
            MessageBox.Show($"An error occurred while opening the directory: {ex.Message}", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
        }
    }

    /// <summary>
    /// Esborra tots els resultats de la UI i reinicia el conjunt de fitxers processats.
    /// </summary>
    private void ClearResults_Click(object sender, RoutedEventArgs e)
    {
        Results.Clear();
    }
}

    /// <summary>
    /// Representa un fitxer trobat durant la cerca.
    /// </summary>
    public class FileResult
    {
        public string Name { get; set; }
        public string Path { get; set; }
    }