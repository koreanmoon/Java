package heritage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	
	private static Connection dbConnection;

	public static Connection getConnection() {
		return dbConnection;
	}
	
	public static void init() {		
		try {
			dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:1111/nhl?user=root&password=xxxxxx");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void close() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
