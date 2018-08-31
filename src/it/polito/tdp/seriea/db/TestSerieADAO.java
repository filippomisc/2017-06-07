package it.polito.tdp.seriea.db;

import java.util.List;


import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.MatchIdMap;
import it.polito.tdp.seriea.model.Result;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.SeasonIdMap;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.TeamIdMap;

public class TestSerieADAO {

	public static void main(String[] args) {
		
		SerieADAO dao = new SerieADAO();
		
		SeasonIdMap seasonIdMap = new SeasonIdMap();
		TeamIdMap teamIdMap = new TeamIdMap();
		MatchIdMap matchIdMap = new MatchIdMap();
		
		
		
		List<Season> seasons = dao.listSeasons(seasonIdMap) ;
		System.out.println(seasons.size());
		
		List<Team> teams = dao.listTeams(teamIdMap) ;
		System.out.println(teams.size());
		
		List<Match> matches = dao.listMatches(matchIdMap, seasonIdMap, teamIdMap);
		System.out.println(matches.size());
		
		System.out.println();
		List<Team> teamsSeason = dao.listTeams(teamIdMap, 2007);
		System.out.println(teamsSeason.size());
		
		System.out.println();
		List<Result> result = dao.risultati(matchIdMap, teamIdMap, 2007);
		System.out.println(result.size());


	}

}
