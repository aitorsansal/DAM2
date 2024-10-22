using System;
using System.Diagnostics;

class Program
{
    static int[,] resultMatrix;
    static int[,] matrixA;
    static int[,] matrixB;
    static Thread[] threads;
    static readonly object lockObject = new object();

    static void Main(string[] args)
    {
        // Demanar la mida de les matrius una vegada al principi
        Console.WriteLine("Quina mida de matrius vols (n x n)? Introdueix el valor de n: ");
        int n = int.Parse(Console.ReadLine());

        // Crear dues matrius aleatòries de mida n x n una sola vegada
        matrixA = GenerateRandomMatrix(n, n);
        matrixB = GenerateRandomMatrix(n, n);
        resultMatrix = new int[n, n]; // Matriu de resultats buida
        Console.WriteLine("Amb quants threads vols que es calculi la multiplicació? ");
        int m = int.Parse(Console.ReadLine());
        threads = new Thread[m];

        int rowsPerTime = n / m;

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.Start();
        
        for (int i = 0; i < m-1; i++)
        {
            var start = rowsPerTime * i;
            var end = rowsPerTime * i + rowsPerTime;
            threads[i] = new Thread(() => MultiplyMatrices(matrixA, matrixB, start,end));
            threads[i].Start();
        }
        var st = rowsPerTime *(m-1);
        var et = matrixA.GetLength(0);
        threads[^1] = new Thread(() => MultiplyMatrices(matrixA, matrixB, st,et));
        threads[^1].Start();
        foreach (var thread in threads)
        {
            thread.Join();
        }
        
        stopwatch.Stop();
        double elapsedMilliseconds = stopwatch.ElapsedTicks * 1000.0 / Stopwatch.Frequency; // Converteix a mil·lisegons

        Console.WriteLine($"Temps de multiplicació de matrius: {elapsedMilliseconds} ms\n");
        Console.WriteLine("Matriu resultant:");
        int rows = resultMatrix.GetLength(0);
        int cols = resultMatrix.GetLength(1);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                Console.Write(resultMatrix[i, j] + " ");
            }

            Console.WriteLine();
        }
    }

    // Funció per generar una matriu aleatòria de m x n
    static int[,] GenerateRandomMatrix(int rows, int cols)
    {
        Random random = new Random();
        int[,] matrix = new int[rows, cols];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                matrix[i, j] = random.Next(1, 100); // Valors aleatoris entre 1 i 9
            }
        }

        return matrix;
    }

    // Funció per multiplicar les matrius sense utilitzar fils
    static void MultiplyMatrices(int[,] matrixA, int[,] matrixB, int start, int end)
    {
        int colsA = matrixA.GetLength(1);
        int colsB = matrixB.GetLength(1);

        for (int i = start; i < end; i++)
        {
            for (int j = 0; j < colsB; j++)
            {
                int sum = 0;
                for (int k = 0; k < colsA; k++)
                {
                    sum += matrixA[i, k] * matrixB[k, j];
                }

                lock (lockObject)
                {
                    resultMatrix[i, j] = sum;
                }
            }
        }

    }
}