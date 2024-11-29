using System.Windows;
using System.Windows.Controls;
using RkPpSsLzSk.Model;

namespace RkPpSsLzSk;

public class PlayerTemplateSelector : DataTemplateSelector
{
    public DataTemplate HeaderTemplate { get; set; }
    public DataTemplate PlayerTemplate { get; set; }

    public override DataTemplate SelectTemplate(object item, DependencyObject container)
    {
        return item switch
        {
            Header => HeaderTemplate,
            Player => PlayerTemplate,
            _ => base.SelectTemplate(item, container)
        };
    }
}