using System.Text;
using System.Text.RegularExpressions;

internal class Program
{
    public static string key = "";
    public static readonly Regex repaceWhitespace = new Regex(@"\s+", RegexOptions.Compiled);
    public static void Main(string[] args)
    {
        PaintMenu();
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

        //TODO: GET KEY WORD AND GENERATE A LIST OF ASCII NUMBER
        //TODO: GENERATE A NEW MATRIX WITH THE SAME SIZE AS THE OTHER ONE
        //TODO: FOR LOOP ON THE LIST TO GET THE LEAST NUMBER AND ADD THE EXISTENT COLUMN TO THE NEW MATRIX

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
        return "";
    }
}