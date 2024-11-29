using System.ComponentModel;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using ArgumentOutOfRangeException = System.ArgumentOutOfRangeException;

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
        ViewModel.StartFightAnimationEvent += FightAnimation;
    }

    #region Animations

    private bool enemyAnimation = false;
    bool playerAnimation = false;
    
    
    public static event Action FinishedFightAnimation;
    void FightAnimation(Result result)
    {
        imgEnemy.Visibility = Visibility.Visible;
        imgPlayer.Visibility = Visibility.Visible;
        
        var enemAnim = DoAnimation(new Thickness(-15,-15,-15,-15), new Thickness(0), 100);
        var playerAnim = DoAnimation(new Thickness(-15,-15,-15,-15), new Thickness(0), 100);


        enemAnim.Completed += (_, _) =>
        {
            enemyAnimation = true;
            SecondAnimationPart(result);
        };
        playerAnim.Completed += (_, _) =>
        {
            playerAnimation = true;
            SecondAnimationPart(result);
        };
        imgEnemy.BeginAnimation(MarginProperty, enemAnim);
        imgPlayer.BeginAnimation(MarginProperty, playerAnim);
    }

    void SecondAnimationPart(Result result)
    {
        if (playerAnimation && enemyAnimation)
        {
            playerAnimation = false;
            enemyAnimation = false;
            var enemAnim = DoAnimation(new Thickness(0), new Thickness(0,35,0,-35), 150);
            var playerAnim = DoAnimation(new Thickness(0), new Thickness(0,-35,0,35), 150);

            
            enemAnim.Completed += (_, _) =>
            {
                enemyAnimation = true;
                ThirdAnimationPart(result);
            };
            playerAnim.Completed += (_, _) =>
            {
                playerAnimation = true;
                ThirdAnimationPart(result);
            };       
            imgEnemy.BeginAnimation(MarginProperty, enemAnim);
            imgPlayer.BeginAnimation(MarginProperty, playerAnim);
        }
    }

    void ThirdAnimationPart(Result result)
    {
        if (playerAnimation && enemyAnimation)
        {
            playerAnimation = false;
            enemyAnimation = false;

            ThicknessAnimation enemAnim;
            ThicknessAnimation playerAnim;
            Thickness enemyTargetMargin;
            Thickness playerTargetMargin;

            switch (result)
            {
                case Result.Win:
                    enemyTargetMargin = new Thickness(0, -1000, 0, 1000);
                    playerTargetMargin = new Thickness(0, -84, 0, 84);
                    break;
                case Result.Lose:
                    enemyTargetMargin = new Thickness(0, 84, 0, -84);
                    playerTargetMargin = new Thickness(0, 1000, 0, -1000);
                    break;
                case Result.Draw:
                    enemyTargetMargin = new Thickness(0);
                    playerTargetMargin = new Thickness(0);
                    break;
                default:
                    throw new ArgumentOutOfRangeException(nameof(result), result, null);
            }

            enemAnim = DoAnimation(imgEnemy.Margin, enemyTargetMargin, 250);
            playerAnim = DoAnimation(imgPlayer.Margin, playerTargetMargin, 250);

            enemAnim.Completed += (_, _) =>
            {
                imgEnemy.Margin = enemyTargetMargin;
                enemyAnimation = true;
                EndOfAnimation();
            };
            playerAnim.Completed += (_, _) =>
            {
                imgPlayer.Margin = playerTargetMargin;
                playerAnimation = true;
                EndOfAnimation();
            };

            imgEnemy.BeginAnimation(MarginProperty, enemAnim);
            imgPlayer.BeginAnimation(MarginProperty, playerAnim);
        }
    }

    async Task EndOfAnimation()
    {
        if (playerAnimation && enemyAnimation)
        {
            await Task.Delay(TimeSpan.FromMilliseconds(650));
            FinishedFightAnimation.Invoke();
            imgEnemy.Visibility = Visibility.Collapsed;
            imgPlayer.Visibility = Visibility.Collapsed;
        }
    }


    ThicknessAnimation DoAnimation(Thickness from, Thickness to, double miliseconds)
    {
        ThicknessAnimation anim = new ThicknessAnimation()
        {
            From = from,
            To = to,
            Duration = new Duration(TimeSpan.FromMilliseconds(miliseconds))
        };
        return anim;
    }
    
    #endregion

    public static event Action ClosingWindow;
    private void MainWindow_OnClosing(object? sender, CancelEventArgs e)
    {
        ClosingWindow?.Invoke();
    }





    
    
    
    
    
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