package heritage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class sortingData implements Comparable<sortingData>{


	private int games_ID;
	private String name, publisher,SKU;
	private double price;
	private LocalDate publication_date;

	public sortingData(int games_ID, String name, String publisher, LocalDate publication_date, double price, String SKU) {
		super();
		this.games_ID = games_ID;
		this.name = name;
		this.publisher = publisher;
		this.SKU = SKU;
		this.price = price;
		this.publication_date = publication_date;
	}



	public String getName() {
		return name;
	}



	public double getPrice() {
		return price;
	}



	public LocalDate getPublication_date() {
		return publication_date;
	}



	@Override
	public String toString() {
		return "games_ID : " + games_ID + ", name : " + name + 
				", publisher : " + publisher + ", publication_date : " 
				+ publication_date + ", price : " + price + ", SKU : " + SKU;
	}

	public static Comparator<sortingData> ByDate = (l0, l1) ->
	{
		return l0.getPublication_date().compareTo(l1.getPublication_date());
	};

	public static Comparator<sortingData> ByPrice = (l0, l1) -> {

		double l0P = l0.getPrice();
		double l1P = l1.getPrice();

		if(l0P> l1P) {
			return 1;
		} else if(l0P < l1P) {
			return -1;
		}

		return 0;
	};




	public static void main(String[] args) throws SQLException {


		Connection dbConn = DriverManager.getConnection("jdbc:mariadb://localhost:1111/Board Game Java?user=root&password=xxxxxx");


		PreparedStatement gamesQuery = dbConn.prepareStatement(
				"select games_ID,name,publisher,publication_date,price,SKU from games", ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);


		ResultSet gamesResult = gamesQuery.executeQuery();


		ArrayList<sortingData> games = new ArrayList<>();

		games.add(new sortingData (1,"The Game of Life","Days of Wonder",LocalDate.of(2018,04,01),38.0,"TS480JST"));
		games.add(new sortingData (2,"Candy Land","Z-Man Games",LocalDate.of(2019,05,18),41.0,"FU582LSA"));
		games.add(new sortingData (3,"Battleship","Stonemaier Games",LocalDate.of(2017,03,15),32.0,"DE143RHG"));

		while (gamesResult.next()) {


			sortingData tg = new sortingData(gamesResult.getInt("games_ID"), gamesResult.getString("name"),
					gamesResult.getString("publisher"), gamesResult.getDate("publication_date").toLocalDate(), 
					gamesResult.getDouble("price"),gamesResult.getString("SKU"));

			games.add(tg);
		}

		dbConn.close();

		Collections.sort(games);
		Collections.sort(games,ByDate);
		Collections.sort(games,ByPrice);

		for (sortingData g : games) {

			System.out.println(g.toString());
		}

	}

	@Override
	public int compareTo(sortingData o) {

		return name.compareTo(o.name);
	}

}
