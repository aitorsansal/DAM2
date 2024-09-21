using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows.Input;

namespace A1_WPF;

public class Exercise1ViewModel
{
    public ICommand SubmitCommand { get; }
    
    private string inputText;

    public string InputText
    {
        get => inputText;
        set
        {
            inputText = value;
            OnPropertyChanged();
        }
    }
    
    public Exercise1ViewModel()
    {
        SubmitCommand = new RelayCommand<string?>(OnButtonClick);
    }
    private void OnButtonClick(string? inputText)
    {
        InputText = $"I've recived a text: {inputText ?? ""}";
    }
    
    public event PropertyChangedEventHandler? PropertyChanged;
    protected void OnPropertyChanged([CallerMemberName] string? propertyName = null)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}