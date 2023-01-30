package heritage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class Customer {

	private String last, first;
	private int Avg, Sum, Days;

	public Customer(String last, String first, int avg, int sum, int days) {
		super();
		this.last = last;
		this.first = first;
		Avg = avg;
		Sum = sum;
		Days = days;
	}

	public String getLast() {
		return last;
	}

	public String getFirst() {
		return first;
	}

	public int getAvg() {
		return Avg;
	}

	public int getSum() {
		return Sum;
	}

	public int getDays() {
		return Days;
	}

	@Override
	public String toString() {
		return "Average Steps: " + Avg + "\nTotal Steps: " + Sum + "\nDays Recorded: " + Days;
	}
	public static ArrayList<Customer> load() throws SQLException {
		Connection conn = DBconnection.getConnection();
		
		PreparedStatement q = conn.prepareStatement(
				"SELECT `Last`,`First`,round(SUM(steps)/COUNT(`Date`)) as Avg, SUM(steps) as Sum, COUNT(`Date`) as Days from step_counter.customer join step_counter.step on customer.CustomerID = step.CustomerID GROUP by step.CustomerID;", ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs = q.executeQuery();
		
		ArrayList<Customer> results = new ArrayList<Customer>();
		
		while (rs.next()) {
			Customer loaded = new Customer(rs.getString("last"), rs.getString("first"),
					rs.getInt("Avg"), rs.getInt("Sum"), rs.getInt("Days"));

			results.add(loaded);			
		}
		return results;
	}
}











//VBox vbSideMenu = new VBox();
//root.setCenter(vbSideMenu);
//ArrayList<Customer> Customers = Customer.load();
//Button btn = new Button();
//btn.getItems().addAll(Customers);

//ChoiceBox<Customer> cbPlayers = new ChoiceBox<>();
//cbPlayers.getItems().addAll(Customers);
//
//cbPlayers.getSelectionModel().selectFirst();

//Customer p = (Customer) Customers.getSelectionModel().getSelectedItem();

//ObservableList<Node> vChildren =vbSideMenu.getChildren(); 
//vChildren.clear();
//
//
//
//vChildren.add(new Button("Goals: "));
//vChildren.add(new Button("Assists: " + p.getNumAssists()));


//root.setTop(cbPlayers);
