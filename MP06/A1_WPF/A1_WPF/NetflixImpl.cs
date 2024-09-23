using System.IO;
using System.Text;
using System.Text.RegularExpressions;

namespace MoviesCollection_A1;

public class NetflixImpl : IDAO
{
    public (bool, int, string) SelectByGenre(string genre, string outputFile)
    {
        int counter = 0;
        genre = genre.Trim();
        if (!Regex.IsMatch(outputFile, @"\.[a-zA-Z]+$"))
            outputFile += ".txt";
        using (StreamReader sr = new StreamReader("raw_titles.csv"))
        {
            sr.ReadLine();
            using (StreamWriter sw = new StreamWriter(outputFile, append: true))
            {
                string? line = sr.ReadLine();
                while (line != null)
                {
                    string?[] film = Regex.Split(line, IDAO.regexSpliting).Select(s => string.IsNullOrEmpty(s) ? null : s).ToArray();
                    if (film[7] is not null)
                    {
                        var genres = Regex.Matches(film[7]!, @"'([^']*)'")
                            .Select(m => m.Groups[1].Value.Trim())
                            .ToArray();
                        if (genres.Contains(genre))
                        {
                            StringBuilder sb = new();
                            sb.Append(film[0] + ";");
                            sb.Append(film[1] + ";");
                            sb.Append(film[2] + ";");
                            sb.Append(film[7] + ";");
                            counter++;
                            sw.WriteLine(sb.ToString());
                        }
                    }

                    line = sr.ReadLine();
                }
            }
        }
        return (counter > 0,counter, outputFile);
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