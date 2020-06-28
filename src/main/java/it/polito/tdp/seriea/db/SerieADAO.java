package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.seriea.model.Partite;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> getVertici(){
		String sql="SELECT DISTINCT m.FTAG as goal " + 
				"FROM matches AS m " + 
				"ORDER BY m.FTAG asc ";
		List<Integer> result= new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getInt("goal"));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Partite> getArchi(){
		String sql="SELECT m.FTHG as v1, m.FTAG as v2, COUNT(DISTINCT m.match_id) AS peso " + 
				"FROM matches AS m " + 
				"WHERE m.FTHG<>m.FTAG " + 
				"GROUP BY m.FTHG, m.FTAG " + 
				"HAVING peso!=0 ";
		List<Partite> result= new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Partite p= new Partite(res.getInt("v1"), res.getInt("v2"), res.getInt("peso"));
				result.add(p);
			}conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Partite> getRisultati(Integer goal){
		String sql="SELECT distinct m.FTHG AS v1, m.FTAG AS v2, COUNT(*) as c " + 
				"FROM matches AS m " + 
				"WHERE m.FTHG= ? " + 
				"GROUP BY v1, v2 ";
		
		List<Partite> result= new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, goal);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Partite p= new Partite(res.getInt("v1"), res.getInt("v2"), res.getInt("c"));
				result.add(p);
			}
			conn.close();
			return result;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}
	
	
}

