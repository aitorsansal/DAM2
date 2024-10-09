using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Runtime.Intrinsics.Arm;
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
using Xceed.Wpf.Toolkit;
using Xceed.Wpf.Toolkit.Core;

namespace ColorPicker;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window, INotifyPropertyChanged
{
    private byte alpha = 255;
    private byte red;
    private byte green;
    private byte blue;
    private SolidColorBrush brush = new();
    
    public MainWindow()
    {
        InitializeComponent();
        UpdateColor();
    }

    void UpdateColor()
    {
        brush.Color = Color.FromArgb((byte)alpha, red, green, blue);
        colorPanel.Background = brush;
    }
    
    public event PropertyChangedEventHandler? PropertyChanged;

    protected void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }

    private void AlphaOnValueChanged(object sender, RoutedPropertyChangedEventArgs<object> e)
    {
        alpha = ((IntegerUpDown)sender).Value is null ? (byte)0 : (byte)((IntegerUpDown)sender).Value!;
        UpdateColor();
    }

    private void RedOnValueChanged(object sender, RoutedPropertyChangedEventArgs<object> e)
    {
        red = ((IntegerUpDown)sender).Value is null ? (byte)0 : (byte)((IntegerUpDown)sender).Value!;
        UpdateColor();
    }

    private void GreenValueChanged(object sender, RoutedPropertyChangedEventArgs<object> e)
    {
        green = ((IntegerUpDown)sender).Value is null ? (byte)0 : (byte)((IntegerUpDown)sender).Value!;
        UpdateColor();
    }

    private void BlueValueChanged(object sender, RoutedPropertyChangedEventArgs<object> e)
    {
        blue = ((IntegerUpDown)sender).Value is null ? (byte)0 : (byte)((IntegerUpDown)sender).Value!;
        UpdateColor();
    }

    private void ColorPicker_OnSelectedColorChanged(object sender, RoutedPropertyChangedEventArgs<Color?> e)
    {
        Color? color = ((Xceed.Wpf.Toolkit.ColorPicker)sender).SelectedColor!;
        AUpDown.Value = color.Value.A;
        RUpDown.Value = color.Value.R;
        GUpDown.Value = color.Value.G;
        BUpDown.Value = color.Value.B;
    }
}