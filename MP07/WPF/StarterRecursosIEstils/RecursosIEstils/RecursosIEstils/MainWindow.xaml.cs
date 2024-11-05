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

namespace RecursosIEstils
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void btnRecursosDeFinestra_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndRecursosDeFinestra();
            finestra.ShowDialog();
        }

        private void btnRecursosDinamics_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndRecursosDinamics();
            finestra.ShowDialog();
        }

        private void btnRecursosApp_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndRecursosDeLaApp();
            finestra.ShowDialog();
        }

        private void btnReaprofitamentAmbRecursos_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndReutilitzaFontAmbRecursos();
            finestra.ShowDialog();
        }

        private void btnReaprofitamentAmbEstils_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndReutilitzaFontsAmbEstils();
            finestra.ShowDialog();
        }

        private void btnEventSetter_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndEventSetter();
            finestra.ShowDialog();
        }

        private void btnTriggers_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndEventTriggers();
            finestra.ShowDialog();
        }

        private void btnEstilsAutomatics_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndEstilsAutomatics();
            finestra.ShowDialog();
        }

        private void btnHerenciaDEstils_Click(object sender, RoutedEventArgs e)
        {
            Window finestra = new wndHerènciaDEstils();
            finestra.ShowDialog();
        }
    }
}
