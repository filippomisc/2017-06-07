package it.polito.tdp.seriea.model;

import java.util.HashMap;
import java.util.Map;

public class SeasonIdMap {
	
	private Map<Integer, Season> idMap;

	public SeasonIdMap() {
		idMap = new HashMap<>();
	}
	
	public Season get(int id) {
		return idMap.get(id);
	}
	
	public Season get(Season season) {
		Season old = idMap.get(season.getSeason());
		if(old==null) {
			idMap.put(season.getSeason(), season);
			return season;
		}
		return old;
	}

	public void put(Season season, int id) {
		idMap.put(id, season);
	}
}
