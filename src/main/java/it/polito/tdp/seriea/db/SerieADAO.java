package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Connessione;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons(Map<Integer, Season> idMap) {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(!idMap.containsKey(res.getInt("season"))) {
				Season s= new Season(res.getInt("season"), res.getString("description"));
				idMap.put(s.getSeason(), s);
				result.add(s);
				}
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

	public List<Season> getVertici(Map<Integer, Season> idMap){
		String sql="SELECT DISTINCT s.season as se, s.description as d " + 
				"FROM seasons AS s ";
		
		List<Season> result= new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("se"), res.getString("d")));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//archi
	public List<Connessione> getArchi(Map<Integer, Season> idMap){
		String sql="SELECT m.Season AS s1, m2.Season AS s2, COUNT(distinct m.HomeTeam) AS peso " + 
				"FROM matches AS m, matches AS m2 " + 
				"WHERE m.Season> m2.Season " + 
				"		AND m.HomeTeam=m2.HomeTeam " + 
				"GROUP BY s1,s2 ";
		
		List<Connessione> result= new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Season ss= idMap.get(res.getInt("s1"));
				Season ss2= idMap.get(res.getInt("s2"));
				
				if(ss!=null && ss2!=null) {
					Connessione c= new Connessione(ss, ss2, res.getInt("peso"));
					result.add(c);
				}
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

