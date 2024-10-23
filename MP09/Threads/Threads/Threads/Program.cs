using System;
using System.Diagnostics;
using System.Net;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;

class Program
{

    static void Main(string[] args)
    {

        Console.WriteLine("Quin exercici vols fer? 1 o 3?");
        string input = Console.ReadLine();
        switch (input)
        {
            case "1":
                Exercici1();
                break;
            case "3":
                Exercici3();
                break;
            default:
                Console.WriteLine("L'element entrat no és cap dels que he demanat");
                Console.ReadKey();
                break;
        }

    }

    #region Ex1
    static int[,] resultMatrix;
    static int[,] matrixA;
    static int[,] matrixB;
    static Thread[] threads;
    static readonly object lockObjectEx1 = new object();
    static void Exercici1()
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
                Console.Write(resultMatrix[i, j] + "\t");
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

                lock (lockObjectEx1)
                {
                    resultMatrix[i, j] = sum;
                }
            }
        }

    }

    #endregion

    #region Ex3

    private const string IMAGE_PATH = "image.jpg";
    private const string OUTPUT_IMAGE_PATH = "inverted_image.jpg";

    static void Exercici3()
    {
        Console.WriteLine("Amb quants fils vols invertir els colors de l'imatge?");
        int n = int.Parse(Console.ReadLine() ?? throw new InvalidOperationException());

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.Start();
        Bitmap bmp = Bitmap.FromFile(IMAGE_PATH) as Bitmap;

        var rect = new Rectangle(0, 0, bmp.Width, bmp.Height);
        var data = bmp.LockBits(rect, ImageLockMode.ReadWrite, bmp.PixelFormat);
        var depth = Bitmap.GetPixelFormatSize(data.PixelFormat) / 8;

        var buffer = new byte[data.Width * data.Height * depth];

        // Copy pixels to buffer
        Marshal.Copy(data.Scan0, buffer, 0, buffer.Length);


        Thread[] threads = new Thread[n];
        int quantPerTask = data.Height / n;

        for (int i = 0; i < n; i++)
        {
            int startY = i * quantPerTask;
            int endY = (i == n - 1) ? data.Height : startY + quantPerTask; // Handle last thread

            threads[i] = new Thread(() => Process(buffer, startY, endY, data.Width, depth));
            threads[i].Start();
        }
        foreach (var thread in threads)
        {
            thread.Join();
        }
        
        Marshal.Copy(buffer, 0, data.Scan0, buffer.Length);
        bmp.UnlockBits(data);
        bmp.Save(OUTPUT_IMAGE_PATH, ImageFormat.Jpeg);
        stopwatch.Stop();
        
        double elapsedMilliseconds = stopwatch.ElapsedTicks * 1000.0 / Stopwatch.Frequency; // Converteix a mil·lisegons

        Console.WriteLine($"Temps invertir colors: {elapsedMilliseconds} ms\n");
        

        Console.WriteLine("Saved image");
    }
    
    static void Process(byte[] buffer, int startY, int endY, int width, int depth)
    {
        for (int j = startY; j < endY; j++)
        {
            for (int i = 0; i < width; i++)
            {
                var offset = ((j * width) + i) * depth;

                // Invert colors: 255 - original color value
                buffer[offset + 0] = (byte)(255 - buffer[offset + 0]); // Invert Red
                buffer[offset + 1] = (byte)(255 - buffer[offset + 1]); // Invert Green
                buffer[offset + 2] = (byte)(255 - buffer[offset + 2]); // Invert Blue
            }
        }
    }

    #endregion
}