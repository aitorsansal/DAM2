namespace MoviesCollection_A1;

public class RawTitle(
    int index,
    string id,
    string title,
    string type,
    int releaseYear,
    string seasons,
    double imdbScore,
    double imdbVotes)
    : IComparable<RawTitle>
{


    public int Index { get; set; } = index;
    public string Id { get; set; } = id;
    public string Title { get; set; } = title;
    public string Type { get; set; } = type;
    public int ReleaseYear { get; set; } = releaseYear;
    public string Seasons { get; set; } = seasons;
    public double Imdb_Score { get; set; } = imdbScore;
    public double Imdb_Votes { get; set; } = imdbVotes;

    public int CompareTo(RawTitle? other)
    {
        if (ReferenceEquals(this, other)) return 0;
        return other is null ? 1 : -Imdb_Score.CompareTo(other.Imdb_Score);
    }
}