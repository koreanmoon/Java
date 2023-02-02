package heritage.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

import heritage.DBConnection;


public class Player implements Comparable<Player> {
	private int playerId, numGamesPlayed, numGoals, numAssists;
	
	public int getNumGamesPlayed() {
		return numGamesPlayed;
	}

	public int getNumGoals() {
		return numGoals;
	}

	public int getNumAssists() {
		return numAssists;
	}
	
	
	
	public void setNumGamesPlayed(int numGamesPlayed) {
		this.numGamesPlayed = numGamesPlayed;
	}

	public void setNumGoals(int numGoals) {
		this.numGoals = numGoals;
	}

	public void setNumAssists(int numAssists) {
		this.numAssists = numAssists;
	}

	public int getPoints() {
		return numGoals+numAssists;
	}

	public int getPlayerId() {
		return playerId;
	}
	
	public float getPointsPerGame() {
		return ((float)getPoints())/numGamesPlayed;
	}

	private String first, last;

	public Player(String last, String first, int numGamesPlayed, int numGoals, int numAssists) {
		super();
		this.last = last;
		this.first = first;
		this.numGamesPlayed = numGamesPlayed;
		this.numGoals = numGoals;
		this.numAssists = numAssists;
	}

	private Player(int playerId, String last, String first, int numGamesPlayed, int numGoals, int numAssists) {
		this(last, first, numGamesPlayed, numGoals, numAssists);
		this.playerId = playerId;
	}
	

	@Override
	public String toString() {
		return last +", " + first;
		
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;

	}

	public void setName(String first, String last) {
		setFirst(first);
		setLast(last);
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void insert() throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement q = conn.prepareStatement(
				"INSERT INTO player (`first`,`last`,games_played,goals,assists) VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		q.setString(1, first);
		q.setString(2, last);
		q.setInt(3, numGamesPlayed);
		q.setInt(4, numGoals);
		q.setInt(5, numAssists);

		int numInserted = q.executeUpdate();
		if (numInserted < 1) {
			throw new SQLException("did not insert");
		}

		
		ResultSet rs = q.getGeneratedKeys();
		rs.next(); 
		int newId = rs.getInt(1);
		
		playerId = newId;

	}
	
	public void delete() throws SQLException {
		if (playerId != 0) {
		

			Connection conn = DBConnection.getConnection();
			PreparedStatement q = conn.prepareStatement(
				"DELETE FROM player WHERE player_id=?");

			q.setInt(1, playerId);			
			
			int numUpdated = q.executeUpdate();
			if (numUpdated < 1) {
				throw new SQLException("did not delete");
			}
			
			playerId = 0;
		}
	}

	public void update() throws SQLException {

		if (playerId != 0) {

			Connection conn = DBConnection.getConnection();
			PreparedStatement q = conn.prepareStatement(
				"UPDATE player SET first=?, last=?, games_played=?, goals=?, assists=? WHERE player_id=?");

			q.setString(1, first);
			q.setString(2, last);
			q.setInt(3, numGamesPlayed);
			q.setInt(4, numGoals);
			q.setInt(5, numAssists);
			q.setInt(6, playerId);

			int numUpdated = q.executeUpdate();
			if (numUpdated < 1) {
				throw new SQLException("did not update");
			}
		} else {
			insert();
		}
	}

	public static Player loadPlayer(int playerId) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement q = conn.prepareStatement(
				"select `first`,`last`,games_played,goals,assists from player where player_id=?;",
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		q.setInt(1, playerId);

		ResultSet rs = q.executeQuery();

		if (rs.next()) {

			String first = rs.getString("first");
			String last = rs.getString("last");
			int gamesPlayed = rs.getInt("games_played");
			int goals = rs.getInt("goals");
			int assists = rs.getInt("assists");

			return new Player(playerId, last, first, gamesPlayed, goals, assists);
		}

		return null;

	}

	
	public ArrayList<Team> getTeams() throws SQLException {

	
		Connection conn = DBConnection.getConnection();

		PreparedStatement q = conn
				.prepareStatement("select team.team_id,name from player_team join team on player_team.team_id = team.team_id where player_id = ?;");

		
		q.setInt(1, playerId);
		
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
	
	public static ArrayList<Player> load() throws SQLException {

		
		Connection conn = DBConnection.getConnection();

		
		PreparedStatement q = conn
				.prepareStatement("select player_id,`first`,`last`,games_played,goals,assists from player;");

		
		ResultSet rs = q.executeQuery();

		ArrayList<Player> results = new ArrayList<Player>();

		while (rs.next()) {

			int playerId = rs.getInt("player_id");
			String first = rs.getString("first");
			String last = rs.getString("last");
			int gamesPlayed = rs.getInt("games_played");
			int goals = rs.getInt("goals");
			int assists = rs.getInt("assists");

			Player loaded = new Player(playerId, last, first, gamesPlayed, goals, assists);
			results.add(loaded);
		}

		return results;
	}

	public static Comparator<Player> byName =  (p1, p2) -> (p1.last+p1.first).compareTo(p2.last+p2.first);

	
	public static Comparator<Player> byGamesPlayed =  (p1, p2) -> {
		if(p1.numGamesPlayed > p2.numGamesPlayed) {
			return -1;
		} 
		
		if(p1.numGamesPlayed < p2.numGamesPlayed) {
			return 1;
		} 
		
		return 0;
	};
	
	
	@Override
	public int compareTo(Player other) {
		int thisPoints = getPoints(), otherPoints = other.getPoints();		
		
		if( thisPoints>otherPoints ) {
			return -1;
		} 
		
		if( thisPoints<otherPoints ) {
			return 1;
		} 			
		return 0;
	}	
}
