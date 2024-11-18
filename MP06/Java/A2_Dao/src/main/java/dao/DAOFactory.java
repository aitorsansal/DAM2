package dao;

public class DAOFactory {
	public DAOManager createDAOManager() throws DAOException {
		return new DAOManagerJDBCImpl();
	}
}
