using System.ComponentModel;

namespace A2;

public enum Months {January, February, March, April, May, June, July, August, September, October, November, December, All }

public partial class MainWindow : INotifyPropertyChanged
{
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