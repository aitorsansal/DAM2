using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows.Input;
using MoviesCollection_A1;

namespace A1_WPF;

public class Exercise1ViewModel : INotifyPropertyChanged
{
    public ICommand SubmitCommand { get; }
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

    private readonly IDAO iDao;

    public Exercise1ViewModel()
    {
        SubmitCommand = new RelayCommand(OnButtonClick);
        iDao = new NetflixImpl();
    }
    private void OnButtonClick(object obj)
    {
        int returned = iDao.SelectByGenre(GenreTextInput, FileTextInput);
        OutputText = $"Found {returned} Raw Files with the genre {GenreTextInput}";
    }
    
    public event PropertyChangedEventHandler? PropertyChanged;
    protected void OnPropertyChanged([CallerMemberName] string? propertyName = null)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}