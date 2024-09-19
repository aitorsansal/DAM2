using System.Text;
using System.Text.RegularExpressions;

namespace MoviesCollection_A1;

public class NetflixImpl : IDAO
{
    public int SelectByGenre(string genre, string outputFile)
    {
        const string regexSpliting = @"(?:[^,""]+|""[^""]*"")+";
        int counter = 0;
        using (StreamReader sr = new StreamReader(Program.FILE_NAME))
        {
            sr.ReadLine();
            using (StreamWriter sw = new StreamWriter(outputFile))
            {
                string line = sr.ReadLine();
                while (line != null)
                {
                    string[] film = Regex.Matches(line, regexSpliting).Select(m => m.Value).ToArray();
                    if (film[7].Contains(genre))
                    {
                        StringBuilder sb = new();
                        sb.Append(film[0] + ";");
                        sb.Append(film[1] + ";");
                        sb.Append(film[2] + ";");
                        sb.Append(film[7] + ";");
                        counter++;
                    }

                    line = sr.ReadLine();
                }
            }
        }
        return counter;
    }
}