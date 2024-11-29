using System.Globalization;
using System.Windows;
using System.Windows.Data;

namespace RkPpSsLzSk.Converters;

public class GridVisibilityConverter : IValueConverter
{
    public object? Convert(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        if ((Screens)parameter == Screens.Playing)
            return (Screens)value ==  (Screens)parameter || (Screens)value == Screens.StartMenu ? Visibility.Visible : Visibility.Collapsed;

        return (Screens)value ==  (Screens)parameter ? Visibility.Visible : Visibility.Collapsed;
    }

    public object? ConvertBack(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        throw new NotImplementedException();
    }
}