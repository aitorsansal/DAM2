using System.ComponentModel;
using System.Runtime.CompilerServices;
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
using Xceed.Wpf.Toolkit.Core;

namespace ColorPicker;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window, INotifyPropertyChanged
{
    private byte _alpha = 255;
    private byte _red = 255;
    private byte _green = 255;
    private byte _blue = 255;
    private Color _color = Colors.Black;

    #region Properties

    public int Alpha
    {
        get => (int)_alpha;
        set
        {
            _alpha = (byte)value;
            OnPropertyChanged(nameof(Alpha));
            UpdateColor();
        }
    }

    public int Red
    {
        get => (int)_red;
        set
        {
            _red = (byte)value;
            OnPropertyChanged(nameof(Red));
            UpdateColor();
        }
    }

    public int Green
    {
        get => (int)_green;
        set
        {
            _green = (byte)value;
            OnPropertyChanged(nameof(Green));
            UpdateColor();
        }
    }

    public int Blue
    {
        get => (int)_blue;
        set
        {
            _blue = (byte)value;
            UpdateColor();
            OnPropertyChanged(nameof(Blue));
        }
    }

    public Color CurrentColor
    {
        get => _color;
        set
        {
            _color = value;
            OnPropertyChanged(nameof(CurrentColor));
        }
    }

    #endregion

    public MainWindow()
    {
        InitializeComponent();
        Alpha = 255;
        Red = 255;
        Green = 0;
        Blue = 0;
    }

    void UpdateColor()
    {
        CurrentColor = Color.FromArgb((byte)Alpha, (byte)Red, (byte)Green, (byte)Blue);
    }
    
    public event PropertyChangedEventHandler? PropertyChanged;

    protected void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}