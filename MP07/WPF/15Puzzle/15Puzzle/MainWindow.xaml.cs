﻿using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace _15Puzzle;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    public MainWindow()
    {
        InitializeComponent();
    }

    private void StartGame_OnClick(object sender, RoutedEventArgs e)
    {
        var gmWnd = new WndGame();
        gmWnd.Rows = (int)SldRow.Value;
        gmWnd.Cols = (int)SldCol.Value;

        
        gmWnd.ShowDialog();
    }

    private void MainWindow_OnLoaded(object sender, RoutedEventArgs e)
    {
        var screen = System.Windows.SystemParameters.WorkArea;
        this.Left = screen.Left + screen.Width / 2 - this.Width / 2;
        this.Top = screen.Top + screen.Height / 2 - this.Height / 2;
    }
}