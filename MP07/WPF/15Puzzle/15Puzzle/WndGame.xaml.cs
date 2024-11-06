using System.Windows;
using System.Windows.Controls;
using System.Windows.Threading;

namespace _15Puzzle;

public partial class WndGame : Window
{
    public int Rows { get; set; }
    public int Cols { get; set; }
    readonly DispatcherTimer timer = new DispatcherTimer();
    readonly TimeSpan acumulated = TimeSpan.Zero;
    readonly DateTime start = DateTime.Now;
    public WndGame()
    {
        InitializeComponent();
        timer.Interval = new TimeSpan(100);
        timer.Tick += (sender, args) => {WriteTime();};
        timer.Start();
    }

    private void WndGame_OnLoaded(object sender, RoutedEventArgs e)
    {
        Width = Cols * 65;
        Height = Rows * 65+40;
        var tauler = new Tauler(Rows, Cols)
        {
            Name = "tlrGame"
        };
        dckPanel.Children.Add(tauler);
        tauler.EndedGame += () =>
        {
            timer.Stop();
            var mb = MessageBox.Show(
                $"Felicitats! Has completat el joc en {tauler.Movements} moviments i un temps de {WriteTime()}");
            if (mb == MessageBoxResult.OK)
                Close();
        };
        tauler.ExecutedMovement += (movements) => sbiMovements.Content = $"Moves {movements}";
        var screen = System.Windows.SystemParameters.WorkArea;
        Left = screen.Left + screen.Width / 2 - Width / 2;
        Top = screen.Top + screen.Height / 2 - Height / 2;
        if (Top < screen.Top)
            Top = screen.Top;
        if(Left < screen.Left)
            Left = screen.Left;
        tauler.CheckIfCompleted();
    }
    
    private string WriteTime()
    {
        DateTime ara = DateTime.Now;
        TimeSpan diferencia = ara.Subtract(start);
        diferencia = diferencia.Add(acumulated);
        var time = string.Format($"{diferencia.Hours:00}:{diferencia.Minutes:00}:{diferencia.Seconds:00}.{diferencia.Milliseconds:000}");
        sbiTime.Content = time;
        return time;
    }
}