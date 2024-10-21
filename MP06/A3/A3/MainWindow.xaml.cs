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
        if (txtWrongWrtitngSeed.IsEnabled)
            txtWrongWrtitngSeed.IsEnabled = false;
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

        dao?.Dispose();
        dao = factory.CreateDAO(TbWritingSeed.Text, overWrite: true);
        btnTest.IsEnabled = true;
        UpdateBinFiles();
    }

    private void BtnTest_OnClick(object sender, RoutedEventArgs e)
    {
        txtWrongNif.IsEnabled = false;
        txtWrongName.IsEnabled = false;
        if (string.IsNullOrEmpty(TbWritingName.Text))
        {
            txtWrongName.IsEnabled = true;
            txtWrongName.Text = "\u255aThe name can't be empty\u255d";
            return;
        }

        if (TbWritingNif.Text.Length != 9)
        {
            txtWrongNif.IsEnabled = true;
            return;
        }
        
        string noLetterNIF = TbWritingNif.Text[..^1];
        string newDni = GetDNI(int.Parse(noLetterNIF));
        if (newDni != TbWritingNif.Text)
        {
            var txb = MessageBox.Show($"Wrong DNI detected. Do you want to change it for {newDni}?", 
                "Warning", MessageBoxButton.YesNo);
            if (txb == MessageBoxResult.Yes)
            {
                TbWritingNif.Text = newDni;
            }
        }

        if (!dao.IsFeasible(TbWritingName.Text, TbWritingNif.Text))
        {
            return;
        }
        btnWrite.IsEnabled = true;
    }

    private string GetDNI(int num)
    {
        string letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        return num.ToString()+letters[num % 23];
    }


    private void BtnWrite_OnClick(object sender, RoutedEventArgs e)
    {
        dao.WriteData(TbWritingName.Text, TbWritingNif.Text);
        txtSavedFile.IsEnabled = true;
        Invoke(() =>txtSavedFile.IsEnabled = false, 2f);
    }

    private void BtnReadingFixSeed_OnClick(object sender, RoutedEventArgs e)
    {
        txtWrongReadingSeed.IsEnabled = false;
        if (string.IsNullOrEmpty(tbReadingSeed.Text))
        {
            txtWrongReadingSeed.Text = "\u255aThe seed can't be null\u255d";
            txtWrongReadingSeed.IsEnabled = true;
            return;
        }
        if (tbReadingSeed.Text.Length != 4)
        {
            txtWrongReadingSeed.IsEnabled = true;
            txtWrongReadingSeed.Text = "\u255aWrong seed size. Should be 4\u255d";
            return;
        }
        if(!int.TryParse(tbReadingSeed.Text, out _))
        {
            txtWrongReadingSeed.IsEnabled = true;
            txtWrongReadingSeed.Text = "\u255aSeed must be an integer\u255d";
            return;
        }
        
        dao?.Dispose();
        try
        {
            dao = factory.CreateDAO(tbReadingSeed.Text, overWrite: false);
        }
        catch (Exception exception)
        {
            if (exception is FileNotFoundException)
            {
                txtWrongReadingSeed.Text = "\u255aThe inserted seed doesn't exist\u255d";
                txtWrongReadingSeed.IsEnabled = true;
                return;
            }

            MessageBox.Show(exception.Message);
            return;
        }
        btnRead.IsEnabled = true;
        UpdateBinFiles();
    }

    private void BtnRead_OnClick(object sender, RoutedEventArgs e)
    {
        try
        {
            var name = dao.ReadName();
            var nif = dao.ReadNIF();
            tbReadingName.Text = name;
            tbReadingNIF.Text = nif;
        }
        catch (Exception exception)
        {
            MessageBox.Show(exception.Message == "Unable to read beyond the end of the stream."
                ? "The file is not in my desired format."
                : exception.Message);
        }
    }

    private void LbFiles_OnDoubleClick(object sender, MouseButtonEventArgs e)
    {
        string? elem = lbFiles.SelectedItem.ToString();
        if (elem is not null)
        {
            string sp = elem.Split(".")[0];
            tbReadingSeed.Text = sp;
            TbWritingSeed.Text = sp;
        }
    }

    private void TbNifAndTbName_OnTextChanged(object sender, TextChangedEventArgs e)
    {
        if (btnWrite.IsEnabled) btnWrite.IsEnabled = false;
    }
    
    
    static async void Invoke(Action method, float delayInSeconds)
    {
        await Task.Delay(TimeSpan.FromSeconds(delayInSeconds));
        method.Invoke();
    }

    private void TbWritingSeed_OnTextChanged(object sender, TextChangedEventArgs e)
    {
        if(btnRead.IsEnabled) btnRead.IsEnabled = false;
    }
}