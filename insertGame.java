package heritage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import heritage.model.Game;


public class insertGame {

	public static void main(String[] args) throws SQLException {

		
		Connection dbConn = DriverManager.getConnection("jdbc:mariadb://localhost:1111/Board Game Java?user=root&password=xxxxxx");

		
		PreparedStatement gamesQuery = dbConn.prepareStatement(
				"select games_ID,name,publisher,publication_date,price,SKU from games", ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);

		
		ResultSet gamesResult = gamesQuery.executeQuery();

		
		ArrayList<Game> games = new ArrayList<>();
		
		games.add(new Game (1,"The Game of Life","Days of Wonder",LocalDate.of(2018,04,01),38.0,"TS480JST"));
		games.add(new Game (2,"Candy Land","Z-Man Games",LocalDate.of(2019,05,18),41.0,"FU582LSA"));
		games.add(new Game (3,"Battleship","Stonemaier Games",LocalDate.of(2017,03,15),32.0,"DE143RHG"));
		
		while (gamesResult.next()) {

			
			Game tg = new Game(gamesResult.getInt("games_ID"), gamesResult.getString("name"),
					gamesResult.getString("publisher"), gamesResult.getDate("publication_date").toLocalDate(), 
					gamesResult.getDouble("price"),gamesResult.getString("SKU"));
	
			games.add(tg);
		}

		dbConn.close();
		
		
		for (Game g : games) {
			
			System.out.println(g.toString());
		}

	}

}
