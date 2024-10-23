using System.Printing;
using System.Reflection.Metadata.Ecma335;
using System.Windows.Controls;

namespace _15Puzzle;

public class Tauler : Grid
{
    public int NFiles { get; set; }
    public int NColumnes { get; set; }
    public Casella CasellaBuida { get; set; }
    public int NumCasellesBenColocades { get; set; }
    public bool EstaSolucionat { get; set; }

    public Tauler(int rows, int columns)
    {
        NFiles = rows;
        NColumnes = columns;
        Inicialitza();
    }
    
    void Inicialitza()
    {
        for (int i = 0; i < NFiles; i++)
        {
            RowDefinitions.Add(new RowDefinition());
        }

        for (int i = 0; i < NColumnes; i++)
        {
            ColumnDefinitions.Add(new ColumnDefinition());
        }
        var numbers = GenerateUniqueVector(NFiles * NColumnes);
        int val = 0;
        for (int i = 0; i < NFiles; i++)
        {
            for (int j = 0; j < NColumnes; j++)
            {
                if (val < numbers.Length)
                {
                    Casella newCasella = new Casella
                    {
                        ValorActual = numbers[val],
                        Columna = j,
                        Fila = i,
                        EsVisible = true,
                        ValorDesitjat = val+1
                    };
                    newCasella.SetValue(Grid.RowProperty, i);
                    newCasella.SetValue(Grid.ColumnProperty, j);
                    val++;
                    Children.Add(newCasella);
                    newCasella.MouseDown += (sender, args) => MouFitxa(newCasella);
                }
                else
                {
                    Casella newCasella = new Casella
                    {
                        Text = "",
                        Columna = j,
                        Fila = i,
                        EsVisible = false
                    };
                    newCasella.SetValue(Grid.RowProperty, i);
                    newCasella.SetValue(Grid.ColumnProperty, j);
                    Children.Add(newCasella);
                    newCasella.MouseDown += (sender, args) => MouFitxa(newCasella);
                    CasellaBuida = newCasella;
                }
            }
        }
    }

    void Pausa()
    {
        
    }

    void MouFitxa(Casella toMove)
    {
        if (CasellaBuida.Fila == toMove.Fila)
        {
            if (CasellaBuida.Fila < toMove.Fila)
            {
                for (int i = CasellaBuida.Fila; i < toMove.Fila; i++)
                {
                }
            }
        }
        else if (CasellaBuida.Columna == toMove.Columna)
        {
            Console.WriteLine("same column");
        }
    }


    static int[] GenerateUniqueVector(int size)
    {
        int[] vector = new int[size-1];
        for (int i = 1; i <= vector.Length; i++)
        {
            vector[i-1] = i;
        }
        Shuffle(vector);
        CheckForTwoLast(array:vector);
        return vector;
    }
    
    static void Shuffle(int[] list)
    {
        Random rng = new Random();
        int count = list.Length;

        while (count > 1)
        {
            int k = rng.Next(count--); // Get a random index
            // Swap elements
            (list[count], list[k]) = (list[k], list[count]);
        }
    }

    static void CheckForTwoLast(int[] array)
    {
        int timesUnordered = 0;
        for (int i = 0; i < array.Length; i++)
        {
            for (int j = i+1; j < array.Length; j++)
            {
                if(array[i] > array[j]) timesUnordered++;
            }
        }

        if (timesUnordered % 2 != 0)
        {
            (array[^2],array[^1]) = (array[^1], array[^2]);
        }

    }
}