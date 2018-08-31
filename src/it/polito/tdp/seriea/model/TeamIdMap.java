package it.polito.tdp.seriea.model;

import java.util.HashMap;
import java.util.Map;

//INUTILE IN QUANTO LA TABELLA TEAM DISPONE DI UN SOLO CAMPO

public class TeamIdMap {
	
	private Map<String, Team> idMap;

	public TeamIdMap() {
		idMap = new HashMap<>();
	}
	
	public Team get(String id) {
		return idMap.get(id);
	}
	
	public Team get(Team team) {
		Team old = idMap.get(team.getTeam());
		if(old==null) {
			idMap.put(team.getTeam(), team);
			return team;
		}
		return old;
	}

	public void put(Team team, String id) {
		idMap.put(id, team);
	}
}
