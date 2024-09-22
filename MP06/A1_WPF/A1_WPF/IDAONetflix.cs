namespace MoviesCollection_A1;

public class IDAONetflix
{
    public static IDAO CreateNetflixDao()
    {
        return new NetflixImpl();
    }
}