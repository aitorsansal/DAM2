using System.Globalization;
using System.Text.RegularExpressions;

namespace MoviesCollection_A1;

public interface IDAO
{
    const string regexSpliting = @",(?=(?:[^""]*""[^""]*"")*[^""]*$)";
    public int SelectByGenre(String genre, String outputFile);

    public string? SelectByIndex(int index);
    public string? SelectByID(string id);

    public RawTitle[] ReadTitles(int index, int length);
    public int PreMerge(RawTitle[] titles, string outFileName);
    public int Merge(string file1, string file2, string outFileName);
    public List<string> TitlesInRange(string file1, double minScore, double maxScore);

    public static RawTitle ConvertToRawTitle(string line)
    {
        string?[] film = Regex.Split(line, regexSpliting, RegexOptions.None).Select(s=> string.IsNullOrEmpty(s) ? null : s).ToArray();
        RawTitle title = new RawTitle(index: Convert.ToInt32(film[0]), 
                                        id: film[1] ?? "", 
                                        title: film[2] ?? "",
                                        type: film[3] ?? "",
                                        releaseYear: Convert.ToInt32(film[4] ?? "0"),
                                        seasons: film[9] ?? "",
                                        imdbScore: double.Parse(film[11] ?? "0", CultureInfo.InvariantCulture),
                                        imdbVotes: double.Parse(film[12] ?? "0", CultureInfo.InvariantCulture));

        return title;
    }


}