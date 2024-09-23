using System.Configuration;
using System.Data;
using System.Globalization;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace A1_WPF;

/// <summary>
/// Interaction logic for App.xaml
/// </summary>
public partial class App : Application
{
    private void TextBox_OnPreviewTextInput(object sender, TextCompositionEventArgs e)
    {
        var textBox = sender as TextBox;
        // Use SelectionStart property to find the caret position.
        // Insert the previewed text into the existing text in the textbox.
        var fullText = textBox.Text.Insert(textBox.SelectionStart, e.Text);

        double val;
        // If parsing is successful, set Handled to false
        e.Handled = !double.TryParse(fullText, 
            NumberStyles.AllowDecimalPoint | NumberStyles.AllowLeadingSign, 
            CultureInfo.InvariantCulture,
            out val);
    }
}