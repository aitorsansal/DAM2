namespace MoviesCollection_A1;

public class IDAOFactory
{
    public static IDAO CreateNetflixDao()
    {
        return new NetflixImpl();
    }
}