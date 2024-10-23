using System.Windows;
using System.Windows.Controls;

namespace _15Puzzle;

public partial class WndGame : Window
{
    public int Rows { get; set; }
    public int Cols { get; set; }
    public WndGame()
    {
        InitializeComponent();

    }

    private void WndGame_OnLoaded(object sender, RoutedEventArgs e)
    {
        Width = Cols * 65;
        Height = Rows * 65+40;
        dckPanel.Children.Add(new Tauler(Rows, Cols));
    }
}