using System.ComponentModel;
using System.Windows;

namespace A2;

public enum Months {January, February, March, April, May, June, July, August, September, October, November, December, All }

public partial class MainWindow : INotifyPropertyChanged
{
    #region DependencyProperties

    public static readonly DependencyProperty YearTextBoxProperty = DependencyProperty.Register(
        nameof(YearTextBox), typeof(string), typeof(MainWindow), new PropertyMetadata(default(string)));
    public string YearTextBox
    {
        get { return (string)GetValue(YearTextBoxProperty); }
        set { SetValue(YearTextBoxProperty, value); }
    }
    
    public static readonly DependencyProperty MonthTextBoxProperty = DependencyProperty.Register(
        nameof(MonthTextBox), typeof(string), typeof(MainWindow), new PropertyMetadata(default(string)));
    public string MonthTextBox
    {
        get { return (string)GetValue(MonthTextBoxProperty); }
        set { SetValue(MonthTextBoxProperty, value); }
    }

    public static readonly DependencyProperty AmountOfNewsTextBoxProperty = DependencyProperty.Register(
        nameof(AmountOfNewsTextBox), typeof(string), typeof(MainWindow), new PropertyMetadata(default(string)));
    public string AmountOfNewsTextBox
    {
        get { return (string)GetValue(AmountOfNewsTextBoxProperty); }
        set { SetValue(AmountOfNewsTextBoxProperty, value); }
    }
    
    public static readonly DependencyProperty AmountOfUsedTextBoxProperty = DependencyProperty.Register(
        nameof(AmountOfUsedTextBox), typeof(string), typeof(MainWindow), new PropertyMetadata(default(string)));
    public string AmountOfUsedTextBox
    {
        get { return (string)GetValue(AmountOfUsedTextBoxProperty); }
        set { SetValue(AmountOfUsedTextBoxProperty, value); }
    }

    public static readonly DependencyProperty TotalNewsTextBoxProperty = DependencyProperty.Register(
        nameof(TotalNewsTextBox), typeof(string), typeof(MainWindow), new PropertyMetadata(default(string)));
    public string TotalNewsTextBox
    {
        get { return (string)GetValue(TotalNewsTextBoxProperty); }
        set { SetValue(TotalNewsTextBoxProperty, value); }
    }

    public static readonly DependencyProperty TotalUsedTextBoxProperty = DependencyProperty.Register(
        nameof(TotalUsedTextBox), typeof(string), typeof(MainWindow), new PropertyMetadata(default(string)));

    public string TotalUsedTextBox
    {
        get { return (string)GetValue(TotalUsedTextBoxProperty); }
        set { SetValue(TotalUsedTextBoxProperty, value); }
    }

    #endregion
    
    
    private string selectedYear;
    private Months selectedMonth;
    public List<string> PossibleYears { get; set; }
    public List<Months> PossibleMonths { get; set; }

    public string SelectedYear
    {
        get => selectedYear;
        set 
        {
            selectedYear = value;
            OnPropertyChanged(nameof(SelectedYear));
        }
    }
    public Months SelectedMonth
    {
        get => selectedMonth;
        set 
        {
            selectedMonth = value;
            OnPropertyChanged(nameof(SelectedMonth));
        }
    }

    private IXMLManager IxmlManager { get; set; }
    private readonly DAOFactory factory = new DAOFactory();

    public MainWindow()
    {
        IxmlManager = factory.CreateXMLDao();
        InitializeComponent();
        DataContext = this;
        PossibleYears = IxmlManager.GetDistinctYears().Select(x => x.ToString()).ToList();
        var a = IxmlManager.GetSalesByYear(2003);
        YearTextBox = a.Year.ToString();
        MonthTextBox = a.Month.ToString();
        AmountOfNewsTextBox = a.AmountNews.ToString();
        AmountOfUsedTextBox = a.AmountUsed.ToString();
        TotalNewsTextBox = a.TotalNews.ToString();
        TotalUsedTextBox = a.TotalUsed.ToString();
        PossibleYears.Add("ALL");
        selectedYear = PossibleYears[^1];
        
        PossibleMonths = Enum.GetValues(typeof(Months)).Cast<Months>().ToList();
        
    }

    public event PropertyChangedEventHandler PropertyChanged;

    private void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}