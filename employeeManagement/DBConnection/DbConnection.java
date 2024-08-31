package employeeManagement.DBConnection;

import java.sql.*;

public class DbConnection {
	
	private static Connection conn = null;
	private static String url = "jdbc:postgresql://localhost:5432/EmployeeManagement?user=postgres&password=admin";
	public static Connection getInstance() throws SQLException {
		if(conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(url);
			return conn;
		} else {
			return conn;
		}
	}
}