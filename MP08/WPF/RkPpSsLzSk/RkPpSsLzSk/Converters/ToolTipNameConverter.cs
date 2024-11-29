using System.Globalization;
using System.Windows;
using System.Windows.Data;

namespace RkPpSsLzSk.Converters;

public class ToolTipNameConverter : IValueConverter
{
    public object? Convert(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        return value.ToString() != "New Player" && !string.IsNullOrEmpty(value.ToString()) ? Visibility.Collapsed : Visibility.Visible;
    }

    public object? ConvertBack(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        throw new NotImplementedException();
    }
}