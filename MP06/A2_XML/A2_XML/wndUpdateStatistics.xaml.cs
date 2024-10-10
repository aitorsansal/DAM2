using System.Windows;

namespace A2_XML;

public partial class wndUpdateStatistics : Window
{
    #region DependencyProperties

    public static readonly DependencyProperty YearTextBoxProperty = DependencyProperty.Register(
        nameof(YearTextBox), typeof(string), typeof(wndUpdateStatistics), new PropertyMetadata(default(string)));

    public string YearTextBox
    {
        get { return (string)GetValue(YearTextBoxProperty); }
        set { SetValue(YearTextBoxProperty, value); }
    }

    public static readonly DependencyProperty MonthTextBoxProperty = DependencyProperty.Register(
        nameof(MonthTextBox), typeof(string), typeof(wndUpdateStatistics), new PropertyMetadata(default(string)));

    public string MonthTextBox
    {
        get { return (string)GetValue(MonthTextBoxProperty); }
        set { SetValue(MonthTextBoxProperty, value); }
    }

    public static readonly DependencyProperty AmountOfNewsTextBoxProperty = DependencyProperty.Register(
        nameof(AmountOfNewsTextBox), typeof(string), typeof(wndUpdateStatistics), new PropertyMetadata(default(string)));

    public string AmountOfNewsTextBox
    {
        get { return (string)GetValue(AmountOfNewsTextBoxProperty); }
        set { SetValue(AmountOfNewsTextBoxProperty, value); }
    }

    public static readonly DependencyProperty AmountOfUsedTextBoxProperty = DependencyProperty.Register(
        nameof(AmountOfUsedTextBox), typeof(string), typeof(wndUpdateStatistics), new PropertyMetadata(default(string)));

    public string AmountOfUsedTextBox
    {
        get { return (string)GetValue(AmountOfUsedTextBoxProperty); }
        set { SetValue(AmountOfUsedTextBoxProperty, value); }
    }

    public static readonly DependencyProperty TotalNewsTextBoxProperty = DependencyProperty.Register(
        nameof(TotalNewsTextBox), typeof(string), typeof(wndUpdateStatistics), new PropertyMetadata(default(string)));

    public string TotalNewsTextBox
    {
        get { return (string)GetValue(TotalNewsTextBoxProperty); }
        set { SetValue(TotalNewsTextBoxProperty, value); }
    }

    public static readonly DependencyProperty TotalUsedTextBoxProperty = DependencyProperty.Register(
        nameof(TotalUsedTextBox), typeof(string), typeof(wndUpdateStatistics), new PropertyMetadata(default(string)));

    public string TotalUsedTextBox
    {
        get { return (string)GetValue(TotalUsedTextBoxProperty); }
        set { SetValue(TotalUsedTextBoxProperty, value); }
    }

    #endregion
    public wndUpdateStatistics()
    {
        InitializeComponent();
    }
}