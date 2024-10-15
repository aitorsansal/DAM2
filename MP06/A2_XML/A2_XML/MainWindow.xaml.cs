using System.ComponentModel;
using System.Windows;
using A2_XML;

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

    public static readonly DependencyProperty StatisticsListProperty = DependencyProperty.Register(
        nameof(StatisticsList), typeof(List<Statistics>), typeof(MainWindow),
        new PropertyMetadata(default(List<Statistics>)));

    public List<Statistics> StatisticsList
    {
        get { return (List<Statistics>)GetValue(StatisticsListProperty); }
        set { SetValue(StatisticsListProperty, value); }
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
        try
        {
            IxmlManager = factory.CreateXMLDao();
            InitializeComponent();
            DataContext = this;
            PossibleYears = IxmlManager.GetDistinctYears().Select(x => x.ToString()).ToList();
            PossibleYears.Add("ALL");
            selectedYear = PossibleYears[^1];

            PossibleMonths = Enum.GetValues(typeof(Months)).Cast<Months>().ToList();
        }
        catch (Exception e)
        {
            MessageBox.Show(e.Message);
        }


    }

    public event PropertyChangedEventHandler PropertyChanged;

    private void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }

    private void GetSalesByMonths_OnClicked(object sender, RoutedEventArgs e)
    {
        try
        {
            string month = ConvertMonth((Months)MonthComboBox.SelectedValue);
            Statistics monthStatistics = IxmlManager.GetSalesByMonth(month);
            YearTextBox = "ALL";
            MonthTextBox = monthStatistics.Month.ToString();
            AmountOfNewsTextBox = monthStatistics.AmountNews.ToString();
            AmountOfUsedTextBox = monthStatistics.AmountUsed.ToString();
            TotalNewsTextBox = monthStatistics.TotalNews.ToString();
            TotalUsedTextBox = monthStatistics.TotalUsed.ToString();
        }
        catch (Exception exception)
        {
            MessageBox.Show(exception.Message);
        }

    }

    private void GetSalesByYear_OnClicked(object sender, RoutedEventArgs e)
    {
        try
        {
            var yearStatistics = IxmlManager.GetSalesByYear(Convert.ToInt32(YearComboBox.SelectedValue));
            YearTextBox = yearStatistics.Year.ToString();
            MonthTextBox = yearStatistics.Month;
            AmountOfNewsTextBox = yearStatistics.AmountNews.ToString();
            AmountOfUsedTextBox = yearStatistics.AmountUsed.ToString();
            TotalNewsTextBox = yearStatistics.TotalNews.ToString();
            TotalUsedTextBox = yearStatistics.TotalUsed.ToString();
        }
        catch (Exception exception)
        {
            MessageBox.Show(exception.Message);
        }

    }

    private void GetSalesMonthByMonth_OnClicked(object sender, RoutedEventArgs e)
    {
        StatisticsList = IxmlManager.GetSalesMonthByMonth(Convert.ToInt32(YearComboBox.SelectedValue));
    }

    private void GetSalesByMonthAndYear_OnClicked(object sender, RoutedEventArgs e)
    {
        try
        {
            string month = ConvertMonth((Months)MonthComboBox.SelectedValue);
            var selecteStatistics = IxmlManager.GetSalesByYearAndMonth(Convert.ToInt32(YearComboBox.SelectedValue), month);
            YearTextBox = selecteStatistics.Year.ToString();
            MonthTextBox = selecteStatistics.Month;
            AmountOfNewsTextBox = selecteStatistics.AmountNews.ToString();
            AmountOfUsedTextBox = selecteStatistics.AmountUsed.ToString();
            TotalNewsTextBox = selecteStatistics.TotalNews.ToString();
            TotalUsedTextBox = selecteStatistics.TotalUsed.ToString();
        }
        catch (Exception exception)
        {
            MessageBox.Show(exception.Message);
        }

    }
    
    private void UpdateStatistics_OnClick(object sender, RoutedEventArgs e)
    {
        try
        {
            string month = ConvertMonth((Months)MonthComboBox.SelectedValue);
            if (month == "ALL")
                throw new Exception("Can't select all months");
            var selectedStatistic = IxmlManager.GetSalesByYearAndMonth(Convert.ToInt32(YearComboBox.SelectedValue), month);
            wndUpdateStatistics editWnd = new wndUpdateStatistics(IxmlManager, selectedStatistic);
            editWnd.ShowDialog();
        }
        catch (Exception exception)
        {
            MessageBox.Show(exception.Message);
        }
        
    }

    private string ConvertMonth(Months toCheckMonth)
    {
        return toCheckMonth switch
        {
            Months.January => "JAN",
            Months.February => "FEB",
            Months.March => "MAR",
            Months.April => "APR",
            Months.May => "MAY",
            Months.June => "JUN",
            Months.July => "JUL",
            Months.August => "AUG",
            Months.September => "SEP",
            Months.October => "OCT",
            Months.November => "NOV",
            Months.December => "DEC",
            Months.All => "ALL",
            _ => throw new ArgumentOutOfRangeException()
        };
    }


}