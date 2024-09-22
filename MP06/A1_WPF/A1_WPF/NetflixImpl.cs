using System.IO;
using System.Text;
using System.Text.RegularExpressions;

namespace MoviesCollection_A1;

public class NetflixImpl : IDAO
{
    public int SelectByGenre(string genre, string outputFile)
    {
        int counter = 0;
        using (StreamReader sr = new StreamReader("raw_titles.csv"))
        {
            sr.ReadLine();
            using (StreamWriter sw = new StreamWriter(outputFile, append: true))
            {
                string? line = sr.ReadLine();
                while (line != null)
                {
                    string?[] film = Regex.Split(line, IDAO.regexSpliting).Select(s => string.IsNullOrEmpty(s) ? null : s).ToArray();
                    if (film[7]!.Contains(genre))
                    {
                        StringBuilder sb = new();
                        sb.Append(film[0] + ";");
                        sb.Append(film[1] + ";");
                        sb.Append(film[2] + ";");
                        sb.Append(film[7] + ";");
                        counter++;
                        sw.Write(sb.ToString());
                    }

                    line = sr.ReadLine();
                }
            }
        }
        return counter;
    }

    public string SelectByIndex(int index)
    {
        bool found = false;
        string line;
        using (StreamReader sr = new StreamReader("raw_titles.csv"))
        {
            sr.ReadLine(); //skip first line
            line = sr.ReadLine();
            while (line != null && !found)
            {
                if (Convert.ToInt32(line.Split(",")[0]) == index)
                    found = true;
                else
                    line = sr.ReadLine();
            }
        }

        //search for the id
        //if found, load the parameters into labels in the form
        //if not found, throw an error or a message
        return found ? line : null;
    }

    public string SelectByID(int id)
    {
        //search for the id
        //if found, load the parameters into labels in the form
        //if not found, throw an error or a message
        throw new NotImplementedException();
    }

    public RawTitle[] ReadTitles(int index, int length)
    {
        throw new NotImplementedException();
    }
}