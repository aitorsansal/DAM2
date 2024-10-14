namespace A3.DAO;

public class Factory
{
    public IDAO CreateDAO(string seed, bool overWrite)
    {
        return new MidSquareAccess(seed, overWrite);
    }
}