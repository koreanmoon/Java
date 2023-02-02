package heritage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import heritage.DBConnection;

public class Salary {
	
	private String last, first;

	private int amount;



	public int getAmount() {
		return amount;
	}

	public Salary(String last, String first, int amount) {
		super();
		this.last = last;
		this.first = first;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Salary: " + amount;
	}

	public String getLast() {
		return last;
	}

	public String getFirst() {
		return first;
	}

	public static ArrayList<Salary> loadForSalary() throws SQLException {

		Connection conn = DBConnection.getConnection();
		PreparedStatement q = conn
				.prepareStatement("SELECT `first`, `last`, amount from player\n"
						+ "    join salary on player.player_id = salary.player_id\n"
						+ "        ORDER by amount DESC;");
		ResultSet rs = q.executeQuery();
		ArrayList<Salary> results = new ArrayList<Salary>();
		
		while (rs.next()) {

			Salary loaded = new Salary(rs.getString("last"), rs.getString("first"),
					rs.getInt("amount"));

			results.add(loaded);
		}
		return results;
	}
}
