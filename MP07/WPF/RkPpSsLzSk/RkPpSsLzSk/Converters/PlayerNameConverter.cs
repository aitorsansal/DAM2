using System.Globalization;
using System.Windows.Data;

namespace RkPpSsLzSk.Converters;

public class PlayerNameConverter : IValueConverter
{
    public object? Convert(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        return value.ToString() == "New Player" ? string.Empty : value.ToString();
    }

    public object? ConvertBack(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        throw new NotImplementedException();
    }
}