package frontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLHelloWorld {

	public static void main(String[] args) {
		String connectionUrl = "jdbc:mysql://localhost:3306/hospital?serverTimezone=UTC";
		try {
			Connection conn = DriverManager.getConnection(connectionUrl, "root", "12345");
			if (conn!=null) 
				System.out.println("CONNECTED");
			DeleteUpdateOperation(conn);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void DeleteUpdateOperation(Connection conn) throws SQLException {
		Statement st = conn.createStatement();
		String sSql = "DELETE FROM hospital WHERE hospital_codi=13;";
		int result = st.executeUpdate(sSql);
		System.out.println(result);
		
		sSql = "UPDATE hospital SET hospital_adreca='test' WHERE hospital_codi=18;";
		result = st.executeUpdate(sSql);
		System.out.println(result);
	}
	
	static void PreparedStatementsExercises(Connection conn) throws SQLException {
		PreparedStatement pS = conn.prepareStatement("SELECT * FROM hospital WHERE hospital_codi = ?");
		ResultSet set = pS.executeQuery();
	}

}
