using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace StarterTeclatMouseControlsDinàmics
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        Random r = new Random();
        int nBotons = 0;
        DispatcherTimer cronometre = new DispatcherTimer();
        TimeSpan acumulat = TimeSpan.Zero;
        DateTime inici = DateTime.Now;
        public MainWindow()
        {
            InitializeComponent();
            cronometre.Interval = new TimeSpan(100);
            cronometre.Tick += Cronometre_Tick;
        }

        private void Cronometre_Tick(object? sender, EventArgs e)
        {
            
            EscriuTemps();
        }

        private void EscriuTemps()
        {
            DateTime ara = DateTime.Now;
            TimeSpan diferencia = ara.Subtract(inici);
            diferencia = diferencia.Add(acumulat);
            tbkCronometre.Text = String.Format($"{diferencia.Hours:00}:{diferencia.Minutes:00}:{diferencia.Seconds:00}.{diferencia.Milliseconds:000}");
        }

        private void sldNumFiles_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
            if(graGraella!=null)
            {
                graGraella.NFiles = (int)e.NewValue;
            }
            
        }

        private void btnCrea_Click(object sender, RoutedEventArgs e)
        {
            List<Dock> docks = Enum.GetValues<Dock>().ToList();
            Element element = new Element();
            element.ColorFons = SortejaColor();
            element.ColorMarc = SortejaColor();
            element.ColorText = SortejaColor();

            element.SetValue(Grid.RowProperty,2); // no fara res pq no esta en un grid
            element.SetValue(DockPanel.DockProperty,Dock.Left); // aqui si que fa pq esta dins el dockapanel
            Dock dock = docks[r.Next(docks.Count)]; // aixi es random
            DockPanel.SetDock(element, dock); // aixo tambe funciona, es el mateix que laltre, aixi sol existir
            //  pero no sempre, en comptes de dock es podria posar Dock.Left
            element.Text = "Text";
            dckBotons.Children.Add(element);
            nBotons++;
            sbiNumBotons.Content = $"Num botons: {nBotons}";
            element.MouseDown += Element_MouseDown;
        }

        private void Element_MouseDown(object sender, MouseButtonEventArgs e)
        {
            Element elementActual = (Element)sender;
            elementActual.ColorMarc = SortejaColor();
        }

        private void btnElimina_Click(object sender, RoutedEventArgs e)
        {
            dckBotons.Children.Clear();
            nBotons = 0;
            sbiNumBotons.Content = $"Num botons: {nBotons}";
        }

        private Color SortejaColor()
        {
            return Color.FromRgb((Byte)r.Next(256), (Byte)r.Next(256), (Byte)r.Next(256));
        }


        private void btnEngegaAtura_Click(object sender, RoutedEventArgs e)
        {
            if (btnEngegaAtura.Content.ToString().Equals("Engega"))
            {
                btnEngegaAtura.Content = "Atura";
                
                inici = DateTime.Now;     
                cronometre.Start();
                
            }
            else
            {
                btnEngegaAtura.Content = "Engega";
                acumulat = acumulat.Add(DateTime.Now.Subtract(inici));
                
                cronometre.Stop();
            }
        }

        private void btnZero_Click(object sender, RoutedEventArgs e)
        {
            acumulat = TimeSpan.Zero;
            //cronometre.Stop();
            inici = DateTime.Now;
            EscriuTemps();
        }
    }
}
