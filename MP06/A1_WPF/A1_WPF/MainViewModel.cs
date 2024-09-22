using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows.Input;
using A1_WPF;

public class MainViewModel : INotifyPropertyChanged
{
    private object _currentView;
    public object CurrentView
    {
        get => _currentView;
        set { _currentView = value; OnPropertyChanged(); }
    }

    public MainViewModel()
    {
        CurrentView = new Exercise1ViewModel();
    }
    
    public void SwitchView(int exerciseNumber)
    {
        switch (exerciseNumber)
        {
            case 1:
                CurrentView = new Exercise1ViewModel();
                break;
            case 2:
                CurrentView = new Exercise2ViewModel();
                break;
            // Add cases for other exercises
        }
    }

    public event PropertyChangedEventHandler? PropertyChanged;

    protected void OnPropertyChanged([CallerMemberName] string? name = null)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
    }
}
