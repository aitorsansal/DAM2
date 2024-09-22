using System.Text.RegularExpressions;

namespace MoviesCollection_A1;

public interface IDAO
{
    const string regexSpliting = @",(?=(?:[^""]*""[^""]*"")*[^""]*$)";
    public int SelectByGenre(String genre, String outputFile);

    public string SelectByIndex(int index);
    public string SelectByID(int id);

    public RawTitle[] ReadTitles(int index, int length);

    public static int PreMerge(RawTitle[] titles, string outFileName)
    {
        return 0;
    }

    public static RawTitle ConvertToRawTitle(string line)
    {
        string?[] film = Regex.Split(line, regexSpliting, RegexOptions.None).Select(s=> string.IsNullOrEmpty(s) ? null : s).ToArray();
        RawTitle title = new RawTitle(index: Convert.ToInt32(film[0]), 
                                        id: film[1] ?? "", 
                                        title: film[2] ?? "",
                                        type: film[3] ?? "",
                                        releaseYear: Convert.ToInt32(film[4] ?? "0"),
                                        seasons: film[9] ?? "",
                                        imdbScore: Convert.ToDouble(film[11] ?? "0"),
                                        imdbVotes: Convert.ToDouble(film[12] ?? "0"));

        return title;
    }


}