package heritage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import heritage.DBConnection;

public class Team {

	private int team_id;
	private String name;
	
	public String getName() {
		return name;
	}

	public Team(int team_id, String name) {
		super();
		this.team_id = team_id;
		this.name = name;
	}
	
	public static ArrayList<Team> loadForPlayer(Player player) throws SQLException {

		Connection conn = DBConnection.getConnection();
		
		PreparedStatement q = conn
				.prepareStatement("select team.team_id,name from player_team join team on player_team.team_id = team.team_id where player_id = ?;");

		
		q.setInt(1, player.getPlayerId());
		
		ResultSet rs = q.executeQuery();
		
		ArrayList<Team> results = new ArrayList<Team>();
	
		while (rs.next()) {

			int teamId = rs.getInt("team_id");
			String name = rs.getString("name");
			
			Team loaded = new Team(teamId, name);
			results.add(loaded);
		}
		return results;
	}	
}
