package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;


import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	
	SerieADAO dao ;

	List<Season> seasons;
	List<Team> teams;
	List<Match> matches;
	
	SeasonIdMap seasonIdMap;
	TeamIdMap teamIdMap;
	MatchIdMap matchIdMap;
	
	private Graph<Team, DefaultWeightedEdge> graph;
	
	
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
		
		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
	}
	
	public List<Team> getTeams() {
		return teams;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void createGraph (int stagione) {
		
		Graphs.addAllVertices(this.graph, dao.listTeams(teamIdMap, stagione));
		
		System.out.println("vertici creati: " + this.graph.vertexSet().size());

		for(Result match : dao.risultati(matchIdMap, teamIdMap, stagione)) {
			Graphs.addEdge(this.graph, match.gettHome(), match.gettAway(), match.getPeso());
		}
		
		System.out.println("archi creati: " + this.graph.edgeSet().size());

	}
	
	public List<Team> generaClassifica() {
		
		for(Team s : this.teams) {
			s.setPunti(0);
		}
		
		for (DefaultWeightedEdge arco : this.graph.edgeSet()) {
			
			int punteggio = (int) this.graph.getEdgeWeight(arco);
			Team sH = this.graph.getEdgeSource(arco);
			Team sA= this.graph.getEdgeTarget(arco);
			
			
			switch (punteggio) {
			case +1:
				sH.setPunti(sH.getPunti()+3);
				break;
			case 0:
				sH.setPunti(sH.getPunti()+1);
				sA.setPunti(sA.getPunti()+1);
				break;
			case -1:
				sA.setPunti(sA.getPunti()+3);
				break;

			default:
				throw new NumberFormatException();
			}
		}
		List<Team> s= new ArrayList<>(this.graph.vertexSet());
		Collections.sort(s);
		return s;
	}
	
}
