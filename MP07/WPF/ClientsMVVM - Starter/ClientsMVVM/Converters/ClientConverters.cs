using System;
using System.Globalization;
using System.Windows.Data;
using System.Windows.Media;

namespace ClientsMVVM.Converters;

public class ColorClientConverter : IValueConverter
{
    public object? Convert(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        decimal? val = (decimal?)value;
        if (val is null) return Brushes.Black;
        return val < 0 ? Brushes.Red : Brushes.Black;
    }

    public object? ConvertBack(object? value, Type targetType, object? parameter, CultureInfo culture)
    {
        throw new NotImplementedException();
    }
}