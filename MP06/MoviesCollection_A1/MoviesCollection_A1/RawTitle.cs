namespace MoviesCollection_A1;

public class RawTitle : IComparable<RawTitle>
{
    public int Index { get; set; }
    public string Id { get; set; }
    public string Title { get; set; }
    public string Type { get; set; }
    public int ReleaseYear { get; set; }
    public string Seasons { get; set; }
    public double Imdb_Score { get; set; }
    public double Imdb_Votes { get; set; }

    public int CompareTo(RawTitle? other)
    {
        if (ReferenceEquals(this, other)) return 0;
        return other is null ? 1 : -Imdb_Score.CompareTo(other.Imdb_Score);
    }
}