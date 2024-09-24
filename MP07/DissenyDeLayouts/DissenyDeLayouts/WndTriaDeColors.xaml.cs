using System.Windows;
using System.Windows.Media;

namespace DissenyDeLayouts;

public partial class WndTriaDeColors : Window
{
    public WndTriaDeColors()
    {
        InitializeComponent();
    }
    
    private void TxtBxParaulaGotFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxParaula.Text != "Paraula max 20 lletres")
            return;
        TxtBxParaula.Text = "";
        TxtBxParaula.Foreground = Brushes.Black;
    }
    
    private void TxtBxParaulaLostFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxParaula.Text == string.Empty)
        {
            TxtBxParaula.Text = "Paraula max 20 lletres";
            TxtBxParaula.Foreground = Brushes.Gray;
        }
    }
    private void TxtBxTraduccioGotFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxTraduccio.Text != "Paraula max 20 lletres")
            return;
        TxtBxTraduccio.Text = "";
        TxtBxTraduccio.Foreground = Brushes.Black;
    }
    
    private void TxtBxTraduccioLostFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxTraduccio.Text == string.Empty)
        {
            TxtBxTraduccio.Text = "Paraula max 20 lletres";
            TxtBxTraduccio.Foreground = Brushes.Gray;
        }
    }
}