using System.Windows;
using System;

namespace MessageBox;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    public MainWindow()
    {
        InitializeComponent();
        cbButtons.ItemsSource = Enum.GetValues<MessageBoxButton>();
        cbIcon.ItemsSource = Enum.GetNames<MessageBoxImage>();
    }

    private void CbButtons_SelectionChanged(object sender, RoutedEventArgs e)
    {
        cbSelected.Items.Clear();
        switch ((MessageBoxButton)cbButtons.SelectedItem)
        {
            case MessageBoxButton.OK:
                cbSelected.Items.Add(MessageBoxResult.OK);
                break;
            case MessageBoxButton.OKCancel:
                cbSelected.Items.Add(MessageBoxResult.OK);
                cbSelected.Items.Add(MessageBoxResult.Cancel);
                break;
            case MessageBoxButton.YesNoCancel:
                cbSelected.Items.Add(MessageBoxResult.Yes);
                cbSelected.Items.Add(MessageBoxResult.No);
                cbSelected.Items.Add(MessageBoxResult.Cancel);
                break;
            case MessageBoxButton.YesNo:
                cbSelected.Items.Add(MessageBoxResult.Yes);
                cbSelected.Items.Add(MessageBoxResult.No);
                break;
            default:
                throw new ArgumentOutOfRangeException();
        }
    }

    private void TextBoxShow_OnClick(object sender, RoutedEventArgs e)
    {
        var res = System.Windows.MessageBox.Show(tbText.Text, tbTitle.Text, 
            cbButtons.SelectedItem is not null ? (MessageBoxButton)cbButtons.SelectedItem : MessageBoxButton.OK,
            cbIcon.SelectedItem is not null ? Enum.Parse<MessageBoxImage>(cbIcon.SelectedItem.ToString()!) : MessageBoxImage.None,
            cbSelected.SelectedItem is not null ? (MessageBoxResult)cbSelected.SelectedItem : MessageBoxResult.None);
        txtResult.Text = "Result: " + res;
    }

    private void AddButton_OnClick(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrEmpty(tbAddToOptions.Text)) return;
        if (lbPossibleButtons.Items.Contains(tbAddToOptions.Text)) return;
        lbPossibleButtons.Items.Add(tbAddToOptions.Text);
        tbAddToOptions.Text = string.Empty;
    }

    private void CustomTextBoxShow_OnClick(object sender, RoutedEventArgs e)
    {
        WndCustomMessageBox w = new WndCustomMessageBox();
        foreach (var itm in lbPossibleButtons.Items)
        {
            w.ButtonsList.Add(itm.ToString()!);
        }

        w.Title = tbTitle.Text;
        w.MsgBoxContent = tbText.Text;
        w.ShowDialog();
        
        txtResult.Text = "Result: " + w.Response;
    }
}