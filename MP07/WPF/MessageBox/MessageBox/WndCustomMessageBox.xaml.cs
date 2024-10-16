using System.Windows;
using System.Windows.Controls;

namespace MessageBox;

public partial class WndCustomMessageBox : Window
{
    public List<string> ButtonsList { get; set; } = [];
    public string MsgBoxContent { get; set; }
    public string Response { get; set; } = string.Empty;
    public WndCustomMessageBox()
    {
        InitializeComponent();
    }

    private void WndCustomMessageBox_OnLoaded(object sender, RoutedEventArgs e)
    {
        txtInformation.Text = MsgBoxContent;
        foreach (var btn in ButtonsList)
        {
            Button b = new Button();
            b.Content = btn;
            b.Margin = new Thickness(5);
            b.Click += (sender, args) =>
            {
                Close();
                Response = btn;
            };
            b.HorizontalAlignment = HorizontalAlignment.Stretch;
            stkPnlButtons.Children.Add(b);
        }
    }
}