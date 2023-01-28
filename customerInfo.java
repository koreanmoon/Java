package heritage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


public class customerInfo {

	private String last, first;
	private int Avg, Sum, Days;

	public customerInfo(String last, String first, int avg, int sum, int days) {
		super();
		this.last = last;
		this.first = first;
		Avg = avg;
		Sum = sum;
		Days = days;
	}

	@Override
	public String toString() {
		return last + ", " + first + ": " + Avg + "; " + Sum + "; " + Days + ";";
	}

	public static void main(String[] args) throws SQLException {

		Connection dbConn = DriverManager.getConnection("jdbc:mariadb://localhost:1111/step_counter?user=root&password=xxxxxx");


		PreparedStatement stepQuery = dbConn.prepareStatement(
				"SELECT `Last`,`First`,round(SUM(steps)/COUNT(`Date`)) as Avg, SUM(steps) as Sum, COUNT(`Date`) as Days from step_counter.customer join step_counter.step on customer.CustomerID = step.CustomerID GROUP by step.CustomerID;", ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);


		ResultSet stepResult = stepQuery.executeQuery();


		ArrayList<customerInfo> steps = new ArrayList<>();

		while (stepResult.next()) {

			customerInfo sc = new customerInfo(stepResult.getString("last"), stepResult.getString("first"),
					stepResult.getInt("Avg"), stepResult.getInt("Sum"), stepResult.getInt("Days"));

			steps.add(sc);
		}

		dbConn.close();
		
		System.out.println("last, first: Avg; Sum; Days;");

		for (customerInfo s : steps) {
			
			System.out.println(s.toString());
		}

	}
	
}	