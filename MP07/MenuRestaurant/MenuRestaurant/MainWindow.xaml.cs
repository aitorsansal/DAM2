using System.Globalization;
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

namespace MenuRestaurant;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    public MainWindow()
    {
        InitializeComponent();
    }

    private void ClearSelectionClick(object sender, RoutedEventArgs e)
    {
        foreach (var child in SpPrimerPlat.Children)
        {
            if(child is RadioButton btn)
                btn.IsChecked = false;
        }
        foreach (var child in SpSegonPlat.Children)
        {
            if(child is RadioButton btn)
                btn.IsChecked = false;
        }
        foreach (var child in SpPostres.Children)
        {
            if(child is RadioButton btn)
                btn.IsChecked = false;
        }
        foreach (var child in SpExtres.Children)
        {
            if(child is CheckBox checkBox)
                checkBox.IsChecked = false;
        }
    }

    private void CbCopa_OnUnchecked(object sender, RoutedEventArgs e)
    {
        if(CbPuro.IsChecked == true) CbPuro.IsChecked = false;
    }

    private void CbPuro_OnChecked(object sender, RoutedEventArgs e)
    {
        if (CbCopa.IsChecked != true) CbPuro.IsChecked = false;
    }

    private void SendButtonOnClicked(object sender, RoutedEventArgs e)
    {
        WndFactura fact = new WndFactura();
        foreach (var child in SpPrimerPlat.Children)
        {
            if (child is not RadioButton { IsChecked: true } btn) continue;
            fact.PrimerPlat = (btn.Content.ToString(), Convert.ToDouble(btn.Tag, CultureInfo.InvariantCulture));
            break;
        }
        foreach (var child in SpSegonPlat.Children)
        {
            if (child is not RadioButton { IsChecked: true } btn) continue;
            fact.SegonPlat = (btn.Content.ToString(), Convert.ToDouble(btn.Tag, CultureInfo.InvariantCulture));
            break;
        }
        foreach (var child in SpPostres.Children)
        {
            if (child is not RadioButton { IsChecked: true } btn) continue;
            fact.Postres = (btn.Content.ToString(), Convert.ToDouble(btn.Tag, CultureInfo.InvariantCulture));
            break;
        }
        foreach (var child in SpExtres.Children)
        {
            if(child is CheckBox { IsChecked: true } checkBox)
            {
                fact.Extres.Add((checkBox.Content.ToString(), Convert.ToDouble(checkBox.Tag, CultureInfo.InvariantCulture)));
            }
        }
        fact.ShowDialog();
    }
} 