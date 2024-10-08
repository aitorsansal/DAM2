using System.Windows;
using System.Windows.Controls;

namespace MenuRestaurant;

public partial class WndFactura : Window
{
    public (string?, double?) PrimerPlat { get; set; }
    public (string?,double?) SegonPlat { get; set; }
    public (string?,double?) Postres { get; set; }
    public List<(string?,double?)> Extres { get; set; } = [];

    public WndFactura()
    {
        InitializeComponent();
    }

    private void WndFactura_OnLoaded(object sender, RoutedEventArgs e)
    {
        double total = 0;
        if (PrimerPlat.Item1 is not null)
        {
            StkPnlName.Children.Add(new TextBlock { Text = PrimerPlat.Item1 });
            StkPnlPrice.Children.Add(new TextBlock { Text = PrimerPlat.Item2+"€", TextAlignment = TextAlignment.Right });
            total += PrimerPlat.Item2!.Value;
        }
        if (SegonPlat.Item1 is not null)
        {
            StkPnlName.Children.Add(new TextBlock { Text = SegonPlat.Item1 });
            StkPnlPrice.Children.Add(new TextBlock { Text = SegonPlat.Item2+"€", TextAlignment = TextAlignment.Right });
            total += SegonPlat.Item2!.Value;
        }
        if (Postres.Item1 is not null)
        {
            StkPnlName.Children.Add(new TextBlock { Text = Postres.Item1 });
            StkPnlPrice.Children.Add(new TextBlock { Text = Postres.Item2+"€", TextAlignment = TextAlignment.Right });
            total += Postres.Item2!.Value;
        }

        if (Extres.Count > 0)
        {
            foreach ((string, double?) value in Extres)
            {
                StkPnlName.Children.Add(new TextBlock { Text = value.Item1 });
                StkPnlPrice.Children.Add(new TextBlock { Text = value.Item2+"€", TextAlignment = TextAlignment.Right });
                total += value.Item2!.Value;
            }
        }
        TexBlkTotal.Text = total + "€";
    }
}