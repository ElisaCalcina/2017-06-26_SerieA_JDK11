package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Squadre;
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

	public List<Team> listTeams(Map<String, Team> idMap) {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(!idMap.containsKey(res.getString("team"))) {
				Team t=new Team(res.getString("team"));
				result.add(t);
				idMap.put(t.getTeam(), t);
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

	
	//vertici--> controllo che ogni squadra abbia giocato in almeno una stagione
	public List<Team> getVertici(Map<String, Team> idMap){
		String sql="SELECT  t.team as te ,COUNT(distinct m.Season) AS partita " + 
				"FROM teams AS t, matches AS m " + 
				"WHERE m.HomeTeam=t.team or m.AwayTeam=t.team " + 
				"GROUP BY t.team " + 
				"HAVING partita>0 ";
		List<Team> result= new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Team t= new Team(res.getString("te"));
				result.add(t);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//Archi
	public List<Squadre> getArchi(Map<String, Team> idMap){
		String sql="SELECT t.team as te1, t2.team as te2, COUNT(*) AS peso " + 
				"FROM teams AS t, teams AS t2, matches AS m " + 
				"WHERE (t.team=m.HomeTeam OR t.team=m.AwayTeam) " + 
				"		AND (t2.team=m.HomeTeam OR t2.team=m.AwayTeam) " + 
				"		AND t.team<>t2.team " + 
				"GROUP BY t.team, t2.team " + 
				"HAVING peso!=0 ";
		
		List<Squadre> result= new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				
				Team t1= idMap.get(res.getString("te1"));
				Team t2= idMap.get(res.getString("te2"));
				
				if(t1!=null && t2!=null) {
					Squadre s= new Squadre(t1, t2, res.getInt("peso"));
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
}

