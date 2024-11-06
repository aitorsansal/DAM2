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

    private bool estaBenColocada;
    public bool EstaBenColocada
    {
        get => estaBenColocada;
        set
        {
            estaBenColocada = value;
            UpdateStyles();
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
        UpdateStyles();
    }
    
    private void UpdateStyles()
    {
        // Choose the appropriate style keys
        string borderStyleKey = EstaBenColocada ? "BorderStyleCorrect" : "BorderStyleIncorrect";
        string textBlockStyleKey = EstaBenColocada ? "TextBlockStyleCorrect" : "TextBlockStyleIncorrect";
        
        // Apply the styles
        ((Border)Content).Style = (Style)FindResource(borderStyleKey);
        ((TextBlock)((Viewbox)((Border)Content).Child).Child).Style = (Style)FindResource(textBlockStyleKey);
    }

}