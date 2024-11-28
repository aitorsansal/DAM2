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

namespace RkPpSsLzSk;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    public MainWindow()
    {
        InitializeComponent();
        MoveToSecondScreen();
        ViewModel.StartFightAnimationEvent += res => FightAnimation(res);
    }

    #region Animations

    public static event Action FinishedFightAnimation;
    async Task FightAnimation(Result result)
    {
        await Task.Delay(TimeSpan.FromSeconds(1));
        FinishedFightAnimation?.Invoke();
    }

    #endregion







    
    
    
    
    
    #region Change Screen On Start

    private void MoveToSecondScreen()
    {
        // Get the available displays using WPF-native APIs
        var allDisplays = GetAllDisplays();

        // Check if a second display exists
        // Check if a second display exists
        if (allDisplays.Length > 1)
        {
            var secondScreen = allDisplays[1]; // Get the second screen's rectangle

            // Center the window on the second screen
            this.WindowStartupLocation = WindowStartupLocation.Manual;
            this.Left = secondScreen.Left + (secondScreen.Width - this.Width) / 2;
            this.Top = secondScreen.Top + (secondScreen.Height - this.Height) / 2;
        }
        else
        {
            MessageBox.Show("No second screen detected.");
        }
    }

    private Rect[] GetAllDisplays()
    {
        var virtualScreenLeft = SystemParameters.VirtualScreenLeft;
        var virtualScreenTop = SystemParameters.VirtualScreenTop;

        // Get primary screen bounds
        var primaryScreenWidth = SystemParameters.PrimaryScreenWidth;
        var primaryScreenHeight = SystemParameters.PrimaryScreenHeight;

        // Assume each screen is the same size (this may need refinement for multi-monitor setups with different resolutions)
        return new[]
        {
            new Rect(virtualScreenLeft, virtualScreenTop, primaryScreenWidth, primaryScreenHeight),
            new Rect(virtualScreenLeft + primaryScreenWidth, virtualScreenTop, primaryScreenWidth, primaryScreenHeight) // Assume second screen is to the right of the primary
        };
    }

    #endregion
}