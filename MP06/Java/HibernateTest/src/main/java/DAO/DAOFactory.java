package DAO;
import java.sql.SQLException;

public class DAOFactory {
    public DAOManager createDAOManager() throws SQLException {
        return new DAOManagerHibernateImplementation();
    }
}