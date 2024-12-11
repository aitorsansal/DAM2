package DAO;
import java.sql.SQLException;

public class DAOFactory {
    public static DAOManager createDAOManager() throws SQLException {
        return new DAOManagerHibernateImplementation();
    }
}