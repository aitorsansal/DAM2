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
        if (genre[0] == '\'')
            genre = genre.Remove(0, 1);
        if (genre[^1] == '\'')
            genre = genre.Remove(genre.Length - 1, 1);
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
        bool over = false;
        string? line;
        using (StreamReader sr = new StreamReader("raw_titles.csv"))
        {
            sr.ReadLine(); //skip first line
            line = sr.ReadLine();
            while (line != null && !found && !over)
            {
                int indx = Convert.ToInt32(line.Split(",")[0]);
                if (indx == index)
                    found = true;
                else if (indx > index)
                    over = true;
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
        if(File.Exists(outFileName))
            File.Delete(outFileName);
        int counter = 0;
        using (StreamReader sr1 = new(file1))
        {
            using (StreamReader sr2 = new(file2))
            {
                using (StreamWriter sw = new(outFileName))
                {
                    string? line1 = sr1.ReadLine();
                    string? line2 = sr2.ReadLine();

                    while (line1 != null && line2 != null) 
                    {
                        if (Convert.ToDouble(line1.Split(";")[5]) > Convert.ToDouble(line2.Split(";")[5]))
                        {
                            sw.WriteLine(line1);
                            line1 = sr1.ReadLine();
                        }
                        else if (Convert.ToDouble(line1.Split(";")[5]) < Convert.ToDouble(line2.Split(";")[5]))
                        {
                            sw.WriteLine(line2);
                            line2 = sr2.ReadLine();
                        }
                        else
                        {
                            sw.WriteLine(line2);
                            sw.WriteLine(line1);
                            line1 = sr1.ReadLine();
                            line2 = sr2.ReadLine();
                        }

                        counter++;
                    }

                    while (line1 != null)
                    {
                        sw.WriteLine(line1);
                        line1 = sr1.ReadLine();
                        counter++;
                    }

                    while (line2 != null)
                    {
                        sw.WriteLine(line2);
                        line2 = sr2.ReadLine();
                        counter++;
                    }
                    
                }
            }
        }

        return counter;
    }

    public List<string> TitlesInRange(string file1, double minScore, double maxScore)
    {
        if(!File.Exists(file1)) throw new Exception($"File {file1} doesn't exist.");
        List<string> titles = new();
        using (StreamReader sr = new(file1))
        {
            sr.ReadLine();
            string? line = sr.ReadLine();
            bool cont = line != null && Convert.ToDouble(line.Split(";")[5]) >= minScore;
            while (cont)
            {
                if(Convert.ToDouble(line.Split(";")[5]) <= maxScore)
                    titles.Add(line);
                line = sr.ReadLine();
                cont = line != null && Convert.ToDouble(line.Split(";")[5]) >= minScore;
            }
        }

        return titles;
    }
}