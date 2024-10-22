using System.Windows.Controls;

namespace StarterTeclatMouseControlsDinàmics;

public class Graella : Grid
{
    private int nFiles = 0;


    public Graella()
    {
        ShowGridLines = true;
    }

    public int NFiles
    {
        get => nFiles;
        set
        {
            nFiles = value;
            ConfiguraFiles();
        }
    }

    private void ConfiguraFiles()
    {
        if (NFiles > RowDefinitions.Count)
        {
            for (int i = RowDefinitions.Count; i < NFiles; i++)
            {
                RowDefinitions.Add(new RowDefinition());
                ColumnDefinitions.Add(new ColumnDefinition());
            }
        }
        else if (NFiles < RowDefinitions.Count)
        {
            for (int i = 0; i < RowDefinitions.Count-NFiles; i--)
            {
                RowDefinitions.RemoveAt(0);
                ColumnDefinitions.RemoveAt(0);
            }
        }
    }
}