using System.IO;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows;

namespace MoviesCollection_A1;

public class NetflixImpl : IDAO
{
    public int SelectByGenre(string genre, string outputFile)
    {
        int counter = 0;
        genre = genre.Trim();
        if (!Regex.IsMatch(outputFile, @"\.[a-zA-Z]+$"))
            outputFile += ".txt";
        if(File.Exists(outputFile))
            File.Delete(outputFile);
        try
        {
            using (StreamReader sr = new StreamReader("raw_titles.csv"))
            {
                sr.ReadLine();
                using (StreamWriter sw = new StreamWriter(outputFile, append: true))
                {
                    string? line = sr.ReadLine();
                    while (line != null)
                    {
                        string?[] film = Regex.Split(line, IDAO.regexSpliting)
                            .Select(s => string.IsNullOrEmpty(s) ? null : s).ToArray();
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
        }
        catch (Exception ex)
        {
            MessageBox.Show(ex.Message);
        }
        
        return counter;
    }

    public string? SelectByIndex(int index)
    {
        bool found = false;
        string? line;
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

    public string? SelectByID(string id)
    {
        bool found = false;
        string? line;
        using (StreamReader sr = new StreamReader("raw_titles.csv"))
        {
            sr.ReadLine(); //skip first line
            line = sr.ReadLine();
            while (line != null && !found)
            {
                if (line.Split(",")[1] == id)
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

    public RawTitle[] ReadTitles(int index, int length)
    {
        List<RawTitle> titles = new();
        using (StreamReader sr = new("raw_titles.csv")) {
            
            sr.ReadLine();
            string? line = sr.ReadLine();
            while (line != null && Convert.ToInt32(line.Split(",")[0]) < index)
            {
                line = sr.ReadLine();
            }
            int i = 0;
            while (line!=null && i < length)
            {
                titles.Add(IDAO.ConvertToRawTitle(line));
                line = sr.ReadLine();
                i++;
            }
        }
        return titles.ToArray();
    }

    public int PreMerge(RawTitle[] titles, string outFileName)
    {
        if (!Regex.IsMatch(outFileName, @"\.[a-zA-Z]+$"))
            outFileName += ".txt";
        if(File.Exists(outFileName))
            File.Delete(outFileName);
        int counter = 0;
        Array.Sort(titles);
        using (StreamWriter sw = new(outFileName))
        {
            foreach (var title in titles)
            {
                sw.WriteLine(title);
                counter++;
            }
        }
        return counter;
    }

    public int Merge(string file1, string file2, string outFileName)
    {
        throw new NotImplementedException();
    }

    public List<string> TitlesInRange(string file1, double minScore, double maxScore)
    {
        throw new NotImplementedException();
    }

    public List<string> MergeTitles(string file1, string file2, string outFileName)
    {
        throw new NotImplementedException();
    }
}