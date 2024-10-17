using System.IO;
using System.Reflection;
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
using A3.DAO;
using Path = System.IO.Path;

namespace A3;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{

    public static readonly DependencyProperty BinFilesProperty = DependencyProperty.Register(
        nameof(BinFiles), typeof(string[]), typeof(MainWindow), new PropertyMetadata(default(string[])));

    private IDAO? dao;
    private Factory factory = new();
    
    
    public string[] BinFiles
    {
        get { return (string[])GetValue(BinFilesProperty); }
        set { SetValue(BinFilesProperty, value); }
    }
    public MainWindow()
    {
        InitializeComponent();
        UpdateBinFiles();
    }

    void UpdateBinFiles()
    {
        BinFiles = Directory.GetFiles(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location) ?? string.Empty, "*.bin").Select(x => x.Split("\\").Last()).ToArray();
    }

    private void BtnWritingFixSeed_OnClick(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrEmpty(TbWritingSeed.Text))
        {
            txtWrongWrtitngSeed.Text = "\u255aThe seed can't be null\u255d";
            txtWrongWrtitngSeed.IsEnabled = true;
            return;
        }
        if (TbWritingSeed.Text.Length != 4)
        {
            txtWrongWrtitngSeed.IsEnabled = true;
            txtWrongWrtitngSeed.Text = "\u255aWrong seed size. Should be 4\u255d";
            return;
        }
        if(!int.TryParse(TbWritingSeed.Text, out _))
        {
            txtWrongWrtitngSeed.IsEnabled = true;
            txtWrongWrtitngSeed.Text = "\u255aSeed must be an integer\u255d";
            return;
        }

        if (txtWrongWrtitngSeed.IsEnabled)
            txtWrongWrtitngSeed.IsEnabled = false;
        dao?.Dispose();
        dao = factory.CreateDAO(TbWritingSeed.Text, overWrite: true);
        btnTest.IsEnabled = true;
    }

    private void BtnTest_OnClick(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrEmpty(TbWritingName.Text))
        {
            txtWrongName.IsEnabled = true;
            txtWrongName.Text = "\u255aThe namecan't be empty\u255d";
        }
        btnWrite.IsEnabled = true;
    }
}