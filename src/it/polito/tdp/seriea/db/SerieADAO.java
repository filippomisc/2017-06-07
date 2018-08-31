package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.RESyntaxException;

import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.MatchIdMap;
import it.polito.tdp.seriea.model.Result;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.SeasonIdMap;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.TeamIdMap;

public class SerieADAO {
	
	public List<Season> listSeasons(SeasonIdMap seasonIdMap) {
		String sql = "SELECT season, description FROM seasons" ;
		
		List<Season> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Season season = new Season(res.getInt("season"), res.getString("description"));
				result.add(seasonIdMap.get(season)) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Team> listTeams(TeamIdMap teamIdMap) {
		String sql = "SELECT team FROM teams" ;
		
		List<Team> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Team team = new Team(res.getString("team"));
				result.add(teamIdMap.get(team)) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Team> listTeams(TeamIdMap teamIdMap, int stagione) {
		String sql = "select distinct matches.HomeTeam\r\n" + 
				"from matches\r\n" + 
				"where matches.season =?" ;
		
		List<Team> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, stagione);
			
			ResultSet res = st.executeQuery() ;
			
		
			
			while(res.next()) {
				Team team = new Team(res.getString("HomeTeam"));
				result.add(teamIdMap.get(team)) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	public List<Match> listMatches(MatchIdMap matchIdMap, SeasonIdMap seasonIdMap, TeamIdMap teamIdMap) {
		String sql = "SELECT * FROM matches" ;
		
		List<Match> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Season season = seasonIdMap.get(res.getInt("Season"));
				Team homeTeam = teamIdMap.get(res.getString("HomeTeam"));
				Team awayTeam = teamIdMap.get(res.getString("AwayTeam"));
				
				Match match = new Match(res.getInt("match_id"), season, res.getString("Div"), res.getDate("Date").toLocalDate(), homeTeam, awayTeam, res.getInt("FTHG"), res.getInt("FTAG"), res.getString("FTR"));
				
				result.add(matchIdMap.get(match)) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Result> risultati(MatchIdMap matchIdMap, TeamIdMap teamIdMap, int anno){
		String sql = "select m.HomeTeam, m.AwayTeam, m.FTR\r\n" + 
				"from matches as m\r\n" + 
				"where m.Season=?" ;
		
		List<Result> results = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, anno);

			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Team homeTeam = teamIdMap.get(res.getString("HomeTeam"));
				Team awayTeam = teamIdMap.get(res.getString("AwayTeam"));
				String result = res.getString("FTR");
				
				int peso;
				switch(result) {
				case "H":
					peso = +1;
					break;
				case "D":
					peso = 0;
					break;
				case "A":
					peso =-1;
					break;
				default:
					throw new IllegalArgumentException("errore nel DB");
					
				
				}
				
				Result risultato = new Result(homeTeam, awayTeam, peso);
				results.add(risultato) ;
			}
			
			conn.close();
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}		
	}
	
}
