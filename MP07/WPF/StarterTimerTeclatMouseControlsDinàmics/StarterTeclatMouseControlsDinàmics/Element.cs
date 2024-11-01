using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace StarterTeclatMouseControlsDinàmics
{
    public class Element : ContentControl
    {
        SolidColorBrush pinzellFons = new SolidColorBrush(Colors.Wheat);
        SolidColorBrush pinzellMarc = new SolidColorBrush(Colors.DarkSlateBlue);
        SolidColorBrush pinzellText = new SolidColorBrush(Colors.Khaki);
        public Element()
        {
            Border marc = new Border();
            TextBlock textBlock = new TextBlock();
            this.Content = marc;
            marc.Child = textBlock;

            marc.BorderThickness = new Thickness(5,5,5,5);
            marc.BorderBrush = pinzellMarc;
            marc.Background = pinzellFons;
            textBlock.Foreground = pinzellText;
            textBlock.FontSize = 18;
        }
        
        public Color ColorFons
        {
            get { return pinzellFons.Color; }
            set { pinzellFons.Color = value; }
        }

        public Color ColorMarc
        {
            get { return pinzellMarc.Color; }
            set { pinzellMarc.Color = value; }
        }

        public Color ColorText
        {
            get { return pinzellText.Color; }
            set { pinzellText.Color = value; }
        }

        public String Text
        {
            get { 
                Border marc = (Border)this.Content;
                TextBlock textBlock = (TextBlock)marc.Child;
                return textBlock.Text;
            }
            set 
            {
                Border marc = (Border)this.Content;
                TextBlock textBlock = (TextBlock)marc.Child;
                textBlock.Text = value;
            }
        }
    }
}
