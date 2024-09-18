using System.Text;
using System.Text.RegularExpressions;

internal class Program
{
    public static string key = "";
    public static readonly Regex repaceWhitespace = new Regex(@"\s+", RegexOptions.Compiled);
    public static void Main(string[] args)
    {
        var returnedValue = PaintMenu();
        Console.WriteLine($"The resulting value is: \n{returnedValue}");
    }

    static string PaintMenu()
    {
        string whatToDo;
        do
        {
            Console.WriteLine("Tell me what you want to do: ");
            Console.WriteLine("1. Encript");
            Console.WriteLine("2. Decript");
            whatToDo = Console.ReadLine().ToLower();
        } while (whatToDo != "encript" &&  whatToDo != "decript");

        Console.WriteLine("Give me the text you want to use. If spaces are entered, those will be removed: ");
        string textToUse = Console.ReadLine();
        Console.WriteLine("Give me the key to use. If spaces are entered, those will be removed: ");
        key = repaceWhitespace.Replace(Console.ReadLine(), ""); 
        
        return whatToDo == "encript" ? Encript(textToUse) : Decript(textToUse);
    }


    static string Encript(string text)
    {
        string noWhiteSpace = repaceWhitespace.Replace(text, "");
        int nValue = noWhiteSpace.Length % key.Length == 0 ? noWhiteSpace.Length / key.Length: noWhiteSpace.Length / key.Length + 1;
        char[,] matrix = new char[nValue, key.Length];
        int strCharCounter = 0;
        for (int i = 0; i < matrix.GetLength(0); i++)
        {
            for (int j = 0; j < matrix.GetLength(1); j++)
            {
                matrix[i,j] = strCharCounter < noWhiteSpace.Length ? noWhiteSpace[strCharCounter] : ' ';
                strCharCounter++;
            }
        }

        List<int> asciiList = [];
        asciiList.AddRange(key.Select(character => (int)character));
        char[,] newMatrix = new char[nValue, key.Length];
        int max = asciiList.Max() + 50;
        for (int i = 0; i < matrix.GetLength(1); i++)
        {
            int lower = asciiList.Min();

            int toChange = asciiList.IndexOf(lower);
            
            for (int k = 0; k < nValue; k++)
            {
                newMatrix[k,i] = matrix[k,toChange];
            }

            asciiList[asciiList.IndexOf(lower)] = max;
        }

        StringBuilder sb = new();

        for (int i = 0; i < newMatrix.GetLength(1); i++)
        {
            for (int j = 0; j < newMatrix.GetLength(0); j++)
            {
                sb.Append(newMatrix[j,i]);
            }
        }

        return sb.ToString();

    }

    static string Decript(string text)
    {
        //uemaseasusgcqtnseresitet
        string noWhiteSpace = repaceWhitespace.Replace(text, "");
        int nValue = text.Length % key.Length == 0 ? text.Length / key.Length: text.Length / key.Length + 1;
        char[,] matrix = new char[nValue, key.Length];
        List<int> asciiList = [];
        asciiList.AddRange(key.Select(character => (int)character));
        int[] newAsciiList = [..asciiList];
        Array.Sort(newAsciiList);
        
        int pos = 0;
        for (int i = 0; i < key.Length; i++)
        {
            for (int j = 0; j < nValue; j++)
            {
                matrix[j,i] = pos < text.Length ? text[pos] : ' ';
                pos++;
            }
        }

        for (int i = 0; i < key.Length; i++)
        {
            int posToChange = Array.IndexOf(newAsciiList, asciiList[i]);
            
            for (int k = 0; k < nValue; k++)
            {
                (matrix[k,i], matrix[k,posToChange]) = (matrix[k,posToChange], matrix[k,i]);
            }

            (newAsciiList[i], newAsciiList[posToChange]) = (newAsciiList[posToChange], newAsciiList[i]);
        }
        
        StringBuilder sb = new();
        for (int i = 0; i < matrix.GetLength(0); i++)
        {
            for (int j = 0; j < matrix.GetLength(1); j++)
            {
                sb.Append(matrix[i, j]);
            }
        }
        
        return sb.ToString();
    }
}