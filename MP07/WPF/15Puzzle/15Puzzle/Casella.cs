using System.Configuration;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace _15Puzzle;

public class Casella : ContentControl
{
    
    public String Text
    {
        get { 
            Border marc = (Border)this.Content;
            Viewbox vb = (Viewbox)marc.Child;
            TextBlock textBlock = (TextBlock)vb.Child;
            return textBlock.Text;
        }
        set 
        {
            Border marc = (Border)this.Content;
            Viewbox vb = (Viewbox)marc.Child;
            TextBlock textBlock = (TextBlock)vb.Child;
            textBlock.Text = value;
        }
    }
    public int Fila { get; set; }
    public int Columna { get; set; }

    private int? valorActual;
    public int? ValorActual
    {
        get => valorActual;
        set
        {
            valorActual = value;
            Text = valorActual == null ? "" : valorActual.ToString();
            EstaBenColocada = ValorActual == ValorDesitjat;
        }
    }

    public int ValorDesitjat { get; set; }
    public Color ColorFonsCorrecte { get; set; } = Colors.Green;
    public Color ColorLLetraCorrecte { get; set; } = Colors.Black;
    public Color ColorFonsIncorrecte { get; set; } = Colors.Red;
    public Color ColorLletraIncorrecte { get; set; } = Colors.White;
    
    SolidColorBrush backgroundBrush = new SolidColorBrush();
    SolidColorBrush textBrush = new SolidColorBrush();

    private bool estaBenColocada;
    public bool EstaBenColocada
    {
        get => estaBenColocada;
        set
        {
            backgroundBrush.Color = value ? ColorFonsCorrecte : ColorFonsIncorrecte;
            textBrush.Color = value ? ColorLLetraCorrecte : ColorLletraIncorrecte;
            estaBenColocada = value;
        }
    }

    public bool EsVisible
    {
        get => Visibility == Visibility.Visible;
        set => Visibility = value ? Visibility.Visible : Visibility.Hidden;
    }


    public Casella()
    {
        Border marc = new Border();
        Viewbox viewBox = new Viewbox();
        TextBlock textBlock = new TextBlock();
        this.Content = marc;
        marc.Child = viewBox;
        viewBox.Child = textBlock;

        marc.BorderThickness = new Thickness(5, 5, 5, 5);
        marc.BorderBrush = Brushes.Black;
        marc.Background = backgroundBrush;
        textBlock.Foreground = textBrush;

    }
}