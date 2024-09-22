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
using MoviesCollection_A1;

namespace A1_WPF;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    private static Grid? currentActiveGrid;
    public MainWindow()
    {
        InitializeComponent();
    }

    private void RadioButton_Checked(object sender, RoutedEventArgs e)
    {
        if (DataContext is MainViewModel viewModel && sender is RadioButton radioButton && int.TryParse(radioButton.Tag.ToString(), out int exerciseNumber))
        {
            // viewModel.ShowExercise(exerciseNumber);
        }
    }

    static void ChangeActiveGrid(Grid newGrid)
    {
        if (currentActiveGrid != null)
        {
            currentActiveGrid.Visibility = Visibility.Collapsed;
            newGrid.Visibility = Visibility.Visible;
        }

        currentActiveGrid = newGrid;
    }
}
