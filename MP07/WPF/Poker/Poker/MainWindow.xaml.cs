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

namespace Poker;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    public MainWindow()
    {
        InitializeComponent();
    }

    private void Card_OnClick(object sender, RoutedEventArgs e)
    {
        ((Button)sender).Margin = ((Button)sender).Margin.Top == 20 ? new Thickness(0,-20,0,20) : new Thickness(0,20,0,0);
    }
}