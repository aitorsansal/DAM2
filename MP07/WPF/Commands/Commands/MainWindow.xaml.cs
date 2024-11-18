using System.IO;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Microsoft.Win32;

namespace Commands;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    string? DocumentName { get; set; }
    bool IsSaved { get; set; }
    double FontSize { get; set; }
    public MainWindow()
    {
        InitializeComponent();
        IsSaved = true;
        ChangeFontSize(12);

        CreateBindings();
    }

    private void ChangeFontSize(double size)
    {
        FontSize = size;
        txtDocument.FontSize = FontSize;
        sbiZoom.Content = FontSize;
    }

    private void CreateBindings()
    {
        CommandBinding copyBinding = new CommandBinding(ApplicationCommands.Copy);
        copyBinding.Executed += (_, _) => txtDocument.Copy();
        CommandBindings.Add(copyBinding);
        
        CommandBinding cutBinding = new CommandBinding(ApplicationCommands.Cut);
        cutBinding.Executed += (_, _) => txtDocument.Cut();
        CommandBindings.Add(cutBinding);
        
        CommandBinding pasteBinding = new CommandBinding(ApplicationCommands.Paste);
        pasteBinding.Executed += (_, _) => txtDocument.Paste();
        CommandBindings.Add(pasteBinding);
        
        CommandBinding undoBinding = new CommandBinding(ApplicationCommands.Undo);
        undoBinding.Executed += (_, _) => txtDocument.Undo();
        undoBinding.CanExecute += (_, e) => e.CanExecute = txtDocument.CanUndo;
        CommandBindings.Add(undoBinding);
        
        CommandBinding redoBinding = new CommandBinding(ApplicationCommands.Redo);
        redoBinding.Executed += (_, _) => txtDocument.Redo();
        redoBinding.CanExecute += (_, e) => e.CanExecute = txtDocument.CanRedo;
        CommandBindings.Add(redoBinding);

        CommandBinding augmentSizeBinding = new CommandBinding(EditorCommands.AugmentSize);
        augmentSizeBinding.Executed += (_, e) => ChangeFontSize(FontSize + int.Parse((string)e.Parameter)); 
        CommandBindings.Add(augmentSizeBinding);
        CommandBinding reduceSizeBinding = new CommandBinding(EditorCommands.ReduceSize);
        reduceSizeBinding.Executed += (_, e) => ChangeFontSize(FontSize - int.Parse((string)e.Parameter));
        CommandBindings.Add(reduceSizeBinding);   
    }

    private void mnuAmaga_OnClick(object sender, RoutedEventArgs e)
    {
        mnuShowEines.IsChecked = !mnuShowEines.IsChecked;
        tbpEines.Visibility = mnuShowEines.IsChecked ? Visibility.Visible : Visibility.Collapsed;
    }

    private void CommandNew_OnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        txtDocument.Text = "";
        DocumentName = null;
        Title =  "New Document";    
    }

    private void CommandOpen_OnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        OpenFile();
    }

    private void CommandDesa_OnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        SaveFile(DocumentName);
    }

    private void CommandDesa_OnCanExecute(object sender, CanExecuteRoutedEventArgs e)
    {
        e.CanExecute = !IsSaved;
    }

    private void CommandDesaCom_OnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        SaveFileAs();
    }

    private void CommandTanca_OnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        throw new NotImplementedException();
    }

    private void TxtDocument_OnTextChanged(object sender, TextChangedEventArgs e)
    {
        
        IsSaved = false;
    }

    private void TxtDocument_OnSelectionChanged(object sender, RoutedEventArgs e)
    {
        int line = txtDocument.GetLineIndexFromCharacterIndex(txtDocument.SelectionStart);
        int column = txtDocument.SelectionStart - txtDocument.GetCharacterIndexFromLineIndex(line);
        sbiPosicio.Content = $"Ln {line}, Col {column}";
    }


    public void OpenFile()
    {
        OpenFileDialog openFileDialog = new()
        {
            Filter = "Text Files (*.txt)|*.txt",
            InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments)
        };
        if (openFileDialog.ShowDialog() == true)
        {
            txtDocument.Text = File.ReadAllText(openFileDialog.FileName);
            DocumentName = openFileDialog.FileName;
            Title = DocumentName;
            IsSaved = true;
        }
    }

    public void SaveFileAs()
    {
        SaveFileDialog saveFileDialogue = new()
        {
            Filter = "Text Files (*.txt)|*.txt",
            InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments)
        };
        if (saveFileDialogue.ShowDialog() == true)
        {
            File.WriteAllText(saveFileDialogue.FileName, txtDocument.Text);
            DocumentName = saveFileDialogue.FileName;
            Title = DocumentName;
            IsSaved = true;
        }
    }

    public void SaveFile(String? fileName)
    {
        if(fileName is null) SaveFileAs();
        else
        {
            File.WriteAllText(fileName, txtDocument.Text);
            IsSaved = true;
        }
    }
}