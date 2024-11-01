using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace StarterTeclatMouseControlsDinàmics
{
    public class Graella : Grid
    {
        int nFiles = 0;
        
        public Graella()
        {
            ShowGridLines = true;
        }

        public int NFiles { 
            get { return nFiles; } 
            set { 
                nFiles = value; 
                ConfiguraFiles(); 
            } 
        }

        private void ConfiguraFiles()
        {
            if(nFiles > RowDefinitions.Count)
            {
                for(int fila = RowDefinitions.Count; fila < nFiles; fila++)
                {
                    RowDefinitions.Add(new RowDefinition());
                    ColumnDefinitions.Add(new ColumnDefinition());
                }
            }
            else if (nFiles < RowDefinitions.Count) 
            {
                for (int fila = 0; fila < RowDefinitions.Count-nFiles; fila++)
                {
                    RowDefinitions.RemoveAt(0);
                    ColumnDefinitions.RemoveAt(0);
                }
            }
        }
    }
}
