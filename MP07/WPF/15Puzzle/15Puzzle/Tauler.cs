using System.Printing;
using System.Reflection.Metadata.Ecma335;
using System.Windows;
using System.Windows.Controls;

namespace _15Puzzle;

public class Tauler : Grid
{

    public event MovementDone ExecutedMovement;
    public event GameCompleted EndedGame;
    public int TotalBlocks { get; set; }
    public int NFiles { get; set; }
    public int NColumnes { get; set; }
    public Casella? CasellaBuida { get; set; }

    private int numCasellesBenColocades;
    public int NumCasellesBenColocades
    {
        get => numCasellesBenColocades;
        set
        {
            numCasellesBenColocades = value;
            CheckIfCompleted();
        }
    }

    public bool EstaSolucionat
    {
        set
        {
            if (value)
                EndedGame?.Invoke();
        }
    }

    private int movements;
    public int Movements
    {
        get => movements;
        set
        {
            movements = value;
            ExecutedMovement.Invoke(movements);
        }
    }

    public Tauler(int rows, int columns)
    {
        NFiles = rows;
        NColumnes = columns;
        Inicialitza();
        TotalBlocks = NFiles * NColumnes - 1;
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
                        Columna = j,
                        Fila = i,
                        EsVisible = true,
                        ValorDesitjat = val+1,
                        ValorActual = numbers[val]
                    };
                    newCasella.SetValue(Grid.RowProperty, i);
                    newCasella.SetValue(Grid.ColumnProperty, j);
                    val++;
                    Children.Add(newCasella);
                    newCasella.MouseDown += (sender, args) => MouFitxa(newCasella);
                    if (newCasella.EstaBenColocada) NumCasellesBenColocades++;
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
            if (Math.Abs(CasellaBuida.Columna - toMove.Columna) == 1)
            {
                MoveTwoBlocks(toMove);
            }
            else 
            {
                List<Casella> toMoveList = new List<Casella>();
                if (CasellaBuida.Columna < toMove.Columna)
                    toMoveList = Children.OfType<Casella>().Where(c => c.Fila == CasellaBuida.Fila && c.Columna > CasellaBuida.Columna && c.Columna <= toMove.Columna).ToList();
                else
                {
                    toMoveList = Children.OfType<Casella>().Where(c => c.Fila == CasellaBuida.Fila && c.Columna < CasellaBuida.Columna && c.Columna >= toMove.Columna).ToList();
                    toMoveList.Reverse();
                }
                foreach (var block in toMoveList)
                {
                    MoveTwoBlocks(block);
                }
            }
            NumCasellesBenColocades = Children.OfType<Casella>().Where(c => c.EstaBenColocada).ToList().Count;
        }
        else if (CasellaBuida.Columna == toMove.Columna)
        {
            if (Math.Abs(CasellaBuida.Fila - toMove.Fila) == 1)
            {
                MoveTwoBlocks(toMove);
            }
            else 
            {
                List<Casella> toMoveList = new List<Casella>();
                if (CasellaBuida.Fila < toMove.Fila)
                    toMoveList = Children.OfType<Casella>().Where(c => c.Columna == CasellaBuida.Columna && c.Fila > CasellaBuida.Fila && c.Fila <= toMove.Fila).ToList();
                else
                {
                    toMoveList = Children.OfType<Casella>().Where(c => c.Columna == CasellaBuida.Columna && c.Fila < CasellaBuida.Fila && c.Fila >= toMove.Fila).ToList();
                    toMoveList.Reverse();
                }
                foreach (var block in toMoveList)
                {
                    MoveTwoBlocks(block);
                }
            }
            NumCasellesBenColocades = Children.OfType<Casella>().Where(c => c.EstaBenColocada).ToList().Count;
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


    void MoveTwoBlocks(Casella second)
    {
        CasellaBuida.ValorActual = second.ValorActual;
        CasellaBuida.EsVisible = true;
        var csTemp = CasellaBuida;
        CasellaBuida = second;
        CasellaBuida.ValorActual = null;
        CasellaBuida.EsVisible = false;
        Movements++;
    }

    public void CheckIfCompleted()
    {
        EstaSolucionat = NumCasellesBenColocades == TotalBlocks;
    }

    public delegate void MovementDone(int movements);

    public delegate void GameCompleted();
}