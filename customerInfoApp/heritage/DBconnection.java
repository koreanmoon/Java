package heritage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

	
	private static Connection dbConnection;

	public static Connection getConnection() {
		return dbConnection;
	}
	
	public static void init() {		
		try {
			dbConnection = DriverManager.getConnection("jdbc:mariadb://localhost:0000/step_counter?user=root&password=xxxxxx");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void close() {
		try {
			dbConnection.close();
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}
	
	
	
}

