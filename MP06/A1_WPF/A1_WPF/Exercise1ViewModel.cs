using System.ComponentModel;
using System.Diagnostics;
using System.Runtime.CompilerServices;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Input;
using MoviesCollection_A1;

namespace A1_WPF;

public class Exercise1ViewModel : INotifyPropertyChanged
{
    public ICommand SubmitCommand { get; }
    public ICommand OpenFileCommand { get; }
    public string LblText => "Write the name of the output file\n(no termination = .txt)";
    
    private string genreTextInput;
    public string GenreTextInput
    {
        get => genreTextInput;
        set
        {
            genreTextInput = value;
            OnPropertyChanged();
        }
    }

    private string fileTextInput;
    public string FileTextInput
    {
        get => fileTextInput;
        set
        {
            fileTextInput = value;
            OnPropertyChanged();
        }
    }

    private string outputText;
    public string OutputText
    {
        get => outputText;
        set
        {
            outputText = value;
            OnPropertyChanged();
        }
    }

    private bool openFile;
    public bool OpenFile
    {
        get => openFile;
        set
        {
            openFile = value;
            OnPropertyChanged();
        }
    }

    private string fileName;

    private readonly IDAO iDao;

    public Exercise1ViewModel()
    {
        SubmitCommand = new RelayCommand(OnSubmitClick);
        OpenFileCommand = new RelayCommand(OnOpenFileClick);
        iDao = new NetflixImpl();
    }
    private void OnSubmitClick(object obj)
    {
        var returned = iDao.SelectByGenre(GenreTextInput, FileTextInput);
        OutputText = $"{(returned.Item1 ? $"Found {returned.Item2} Raw Files with the genre {GenreTextInput}.\nA new file was generated" : 
            $"No Raw Files were found with {GenreTextInput}.\nNo file was generated.")}";
        OpenFile = returned.Item1;
        fileName = returned.Item3;
    }

    private void OnOpenFileClick(object obj)
    {
        try
        {
            // Use Process.Start to open the file in the default explorer
            Process.Start(new ProcessStartInfo
            {
                FileName = fileName,
                UseShellExecute = true // This is necessary to open with the default application
            });
        }
        catch (Exception ex)
        {
            MessageBox.Show($"An error occurred: {ex.Message}");
        }
    }
    
    public event PropertyChangedEventHandler? PropertyChanged;
    protected void OnPropertyChanged([CallerMemberName] string? propertyName = null)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}