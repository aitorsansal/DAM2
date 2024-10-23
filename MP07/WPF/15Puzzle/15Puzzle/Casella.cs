using System.Configuration;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using Color = System.Drawing.Color;

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

    private int valorActual;
    public int ValorActual
    {
        get => valorActual;
        set
        {
            valorActual = value;
            Text = value.ToString();
        }
    }

    public int ValorDesitjat { get; set; }
    public SolidColorBrush ColorFonsCorrecte { get; set; } = new SolidColorBrush(Colors.Green);
    public SolidColorBrush ColorLLetraCorrecte { get; set; } = new SolidColorBrush(Colors.Black);
    public SolidColorBrush ColorFonsIncorrecte { get; set; } = new SolidColorBrush(Colors.Red);
    public SolidColorBrush ColorLletraIncorrecte { get; set; } = new SolidColorBrush(Colors.White);
    public bool EstaBenColocada { get; set; }

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
        marc.Background = ColorFonsCorrecte;
        textBlock.Foreground = ColorLLetraCorrecte;

    }
}