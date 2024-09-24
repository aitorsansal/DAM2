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

namespace DissenyDeLayouts;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    public MainWindow()
    {
        InitializeComponent();
    }

    private void BtnOpenCalculadoraOnClick(object sender, RoutedEventArgs e)
    {
        WndCalculadora wndCalculadora = new();
        wndCalculadora.ShowDialog();
    }

    private void BtnOpenImageOnClick(object sender, RoutedEventArgs e)
    {
        WndImatge wndImatge = new WndImatge();
        wndImatge.ShowDialog();
    }

    private void BtnOpenFormulariOnClick(object sender, RoutedEventArgs e)
    {
        WndFormulari wndFormulari = new WndFormulari();
        wndFormulari.ShowDialog();
    }

    private void BtnOpenTriaColorOnClick(object sender, RoutedEventArgs e)
    {
        WndTriaDeColors wndTriaDeColors = new();
        wndTriaDeColors.ShowDialog();
    }
    
}