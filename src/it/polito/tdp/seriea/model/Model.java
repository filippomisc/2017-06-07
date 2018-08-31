package it.polito.tdp.seriea.model;

import java.util.List;


import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	
	SerieADAO dao ;

	List<Season> seasons;
	List<Team> teams;
	List<Match> matches;
	
	SeasonIdMap seasonIdMap;
	TeamIdMap teamIdMap;
	MatchIdMap matchIdMap;
	
//	private Graph<V, E> graph;
	
	public Model() {
		
		dao = new SerieADAO();
		
		//BISOGNA creare PRIMA LE IDMAP DOPO IL DAO 
		//altrimenti da nullPointerexc. quanfo si popolano le liste 
		seasonIdMap = new SeasonIdMap();
		teamIdMap = new TeamIdMap();
		matchIdMap = new MatchIdMap();
		
		seasons = dao.listSeasons(seasonIdMap);
		System.out.println(seasons.size());
		
		teams = dao.listTeams(teamIdMap);
		System.out.println(teams.size());
		
		matches = dao.listMatches(matchIdMap, seasonIdMap, teamIdMap);
		System.out.println(matches.size());
//		this.graph = new 
		
		
	}
	
	
	
}
