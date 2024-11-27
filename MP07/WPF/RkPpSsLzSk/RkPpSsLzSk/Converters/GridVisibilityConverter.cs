using System.Globalization;
using System.Windows;
using System.Windows.Data;

namespace RkPpSsLzSk.Converters;

public class GridVisibilityConverter : IValueConverter
{
    public object? Convert(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        Visibility visible = value.ToString() == (string)parameter ? Visibility.Visible : Visibility.Collapsed;
        return visible;
    }

    public object? ConvertBack(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        throw new NotImplementedException();
    }
}