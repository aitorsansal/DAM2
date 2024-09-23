using System.ComponentModel;
using System.Diagnostics;
using System.Globalization;
using System.Runtime.CompilerServices;
using System.Text;
using System.Text.RegularExpressions;
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
    private readonly IDAO iDao;
    public MainWindow()
    {
        InitializeComponent();
        iDao = new NetflixImpl();
    }
    private void OnIndexPreviewKeyDown(object? sender, TextCompositionEventArgs e)
    {
        Regex regex = new Regex("[^0-9]+");
        e.Handled = regex.IsMatch(e.Text);
    }
    
    #region Exercise1

    private string ex1FileName;
    
    private void OnSubmitClick(object sender, RoutedEventArgs e)
    {
        var returned = iDao.SelectByGenre(TbGenreInput.Text, TbEx1FileNameInput.Text);
        bool worked = returned > 0;
        LblFileGeneratedInfo.Content = $"{(worked ? $"Found {returned} Raw Files with the genre {TbGenreInput.Text}.\nA new file was generated" : 
            $"No Raw Files were found with {TbGenreInput.Text}.\nNo file was generated.")}";
        BtnOpenFile.IsEnabled = worked;
        if (!Regex.IsMatch(TbEx1FileNameInput.Text, @"\.[a-zA-Z]+$"))
            ex1FileName = TbEx1FileNameInput.Text+".txt";
        else
            ex1FileName = TbEx1FileNameInput.Text;
    }

    private void OnEx1OpenFileClick(object sender, RoutedEventArgs e)
    {
        try
        {
            Process.Start(new ProcessStartInfo
            {
                FileName = ex1FileName,
                UseShellExecute = true
            });
        }
        catch (Exception ex)
        {
            MessageBox.Show($"An error occurred: {ex.Message}");
        }
    }
    #endregion

    #region Exercise2

    private void OnIndexSearchClicked(object sender, RoutedEventArgs e)
    {
        try
        {
            int index = Convert.ToInt32(TbIndexInput.Text);
            string? result = iDao.SelectByIndex(index);
            if (result == null)
            {
                TxtBlId_IndexSearch.Text = string.Empty;
                TxtBlTitle_IndexSearch.Text = string.Empty;
                TxtBlType_IndexSearch.Text = string.Empty;
                TxtBlSeasons_IndexSearch.Text = string.Empty;
                TxtBlReleaseYear_IndexSearch.Text = string.Empty;
                TxtBlImdbVotes_IndexSearch.Text = string.Empty;
                TxtBlImdbScore_IndexSearch.Text = string.Empty;
            }
            else
            {
                RawTitle title = IDAO.ConvertToRawTitle(result);
                TxtBlId_IndexSearch.Text = title.Id;
                TxtBlTitle_IndexSearch.Text = title.Title;
                TxtBlType_IndexSearch.Text = title.Type;
                TxtBlSeasons_IndexSearch.Text = title.Seasons;
                TxtBlReleaseYear_IndexSearch.Text = title.ReleaseYear.ToString();
                TxtBlImdbVotes_IndexSearch.Text = title.Imdb_Votes.ToString(CultureInfo.InvariantCulture);
                TxtBlImdbScore_IndexSearch.Text = title.Imdb_Score.ToString(CultureInfo.InvariantCulture);
            }

            
        }
        catch (Exception ex)
        {
            MessageBox.Show(ex.Message);
        }

        
    }

    #endregion

    #region Exercise3

    private void OnIdSearchClicked(object sender, RoutedEventArgs e)
    {
        try
        {
            string? result = iDao.SelectByID(TbIdInput.Text);
            if (result == null)
            {
                TxtBlId_IdSearch.Text = string.Empty;
                TxtBlTitle_IdSearch.Text = string.Empty;
                TxtBlType_IdSearch.Text = string.Empty;
                TxtBlSeasons_IdSearch.Text = string.Empty;
                TxtBlReleaseYear_IdSearch.Text = string.Empty;
                TxtBlImdbVotes_IdSearch.Text = string.Empty;
                TxtBlImdbScore_IdSearch.Text = string.Empty;
            }
            else
            {
                RawTitle title = IDAO.ConvertToRawTitle(result);
                TxtBlId_IdSearch.Text = title.Id;
                TxtBlTitle_IdSearch.Text = title.Title;
                TxtBlType_IdSearch.Text = title.Type;
                TxtBlSeasons_IdSearch.Text = title.Seasons;
                TxtBlReleaseYear_IdSearch.Text = title.ReleaseYear.ToString();
                TxtBlImdbVotes_IdSearch.Text = title.Imdb_Votes.ToString(CultureInfo.InvariantCulture);
                TxtBlImdbScore_IdSearch.Text = title.Imdb_Score.ToString(CultureInfo.InvariantCulture);
            }

        }
        catch (Exception ex)
        {
            MessageBox.Show(ex.Message);
        }

        
    }

    #endregion

    #region Exercise4

    private RawTitle[] rawTitles;
    private List<string> Titles { get; set; } = new();

    private string ex4FileName;

    public void OnGenerateListClick(object sender, RoutedEventArgs e)
    {
        try
        {
            rawTitles = iDao.ReadTitles(Convert.ToInt32(TbStartPos.Text), Convert.ToInt32(TbQuantity.Text));
            Titles = rawTitles.Select(x => x.ToString()).ToList();
            LsBxItems.ItemsSource = rawTitles.ToList();
            TxtBlShowFound.Text = rawTitles.Length.ToString();
            if(rawTitles.Length > 0)
            {
                BtnGenerateReadedTitlesFile.IsEnabled = true;
                TbEx4FileNameInput.IsEnabled = true;
            }
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
        }
    }

    public void OnGenerateRawTitlesFileClick(object sender, RoutedEventArgs e)
    {
        try
        {
            iDao.PreMerge(rawTitles, TbEx4FileNameInput.Text);
            if (!Regex.IsMatch(TbEx4FileNameInput.Text, @"\.[a-zA-Z]+$"))
                ex4FileName = TbEx4FileNameInput.Text + ".txt";
            else
                ex4FileName = TbEx4FileNameInput.Text;
            BtnOpenReadTitlesFile.IsEnabled = true;
        }
        catch (Exception ex)
        {
            MessageBox.Show(ex.Message);
        }
        
    }
    
    private void OnEx4OpenFileClick(object sender, RoutedEventArgs e)
    {
        try
        {
            Process.Start(new ProcessStartInfo
            {
                FileName = ex4FileName,
                UseShellExecute = true
            });
        }
        catch (Exception ex)
        {
            MessageBox.Show($"An error occurred: {ex.Message}");
        }
    }

    public void OnEx4ResolveButtonClicked(object sender, RoutedEventArgs e)
    {
        iDao.PreMerge(iDao.ReadTitles(0, 3000), "FIRSTHALF.CSV");
        iDao.PreMerge(iDao.ReadTitles(3000, 3000), "SECONDHALF.CSV");
        iDao.Merge("FIRSTHALF.CSV","SECONDHALF.CSV", "MERGED.CSV");
        MessageBoxResult result = MessageBox.Show(
            "The file MERGED.csv has been created.\nDo you want to open it?",
            "Merged.csv created", 
            MessageBoxButton.YesNo,
            MessageBoxImage.Question);

        if (result == MessageBoxResult.Yes)
        {
            try
            {
                Process.Start(new ProcessStartInfo
                {
                    FileName = "MERGED.CSV",
                    UseShellExecute = true
                });
            }
            catch (Exception ex)
            {
                MessageBox.Show($"An error occurred: {ex.Message}");
            }
        }
    }
    
    #endregion

    #region Exercise5

    public void OnLoadBetweenRageClick(object sender, RoutedEventArgs e)
    {
        try
        {
            if()
        }
        catch (Exception ex)
        {
            MessageBox.Show(ex.Message);
        }
    }

    #endregion
}
