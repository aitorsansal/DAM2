using System.Windows;
using System.Windows.Media;

namespace DissenyDeLayouts;

public partial class WndFormulari : Window
{
    public WndFormulari()
    {
        InitializeComponent();
    }

    private void TxtBxNameGotFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxName.Text != "Nom(*)")
            return;
        TxtBxName.Text = "";
        TxtBxName.Foreground = Brushes.Black;
    }
    private void TxtBxNameLostFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxName.Text == string.Empty)
        {
            TxtBxName.Text = "Nom(*)";
            TxtBxName.Foreground = Brushes.Gray;
        }
    }
    private void TxtBxSurnameGotFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxSurname.Text != "Cognoms(*)")
            return;
        TxtBxSurname.Text = "";
        TxtBxSurname.Foreground = Brushes.Black;
    }
    private void TxtBxSurnameLostFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxSurname.Text == string.Empty)
        {
            TxtBxSurname.Text = "Cognoms(*)";
            TxtBxSurname.Foreground = Brushes.Gray;
        }
    }
    private void TxtBxMailGotFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxMail.Text != "Correu(*)")
            return;
        TxtBxMail.Text = "";
        TxtBxMail.Foreground = Brushes.Black;
    }
    private void TxtBxMailLostFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxMail.Text == string.Empty)
        {
            TxtBxMail.Text = "Correu(*)";
            TxtBxMail.Foreground = Brushes.Gray;
        }
    }
    private void TxtBxMessageGotFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxMessage.Text != "Missatge(*)")
            return;
        TxtBxMessage.Text = "";
        TxtBxMessage.Foreground = Brushes.Black;
    }
    private void TxtBxMessageLostFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxMessage.Text == string.Empty)
        {
            TxtBxMessage.Text = "Missatge(*)";
            TxtBxMessage.Foreground = Brushes.Gray;
        }
    }
    private void TxtBxWebGotFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxWeb.Text != "Web del servidor")
            return;
        TxtBxWeb.Text = "";
        TxtBxWeb.Foreground = Brushes.Black;
    }
    private void TxtBxWebLostFocus(object sender, RoutedEventArgs e)
    {
        if (TxtBxWeb.Text == string.Empty)
        {
            TxtBxWeb.Text = "Web del servidor";
            TxtBxWeb.Foreground = Brushes.Gray;
        }
    }
}