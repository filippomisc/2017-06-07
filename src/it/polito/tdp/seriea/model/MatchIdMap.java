package it.polito.tdp.seriea.model;

import java.util.HashMap;
import java.util.Map;

public class MatchIdMap {
	
	private Map<Integer, Match> idMap;

	public MatchIdMap() {
		idMap = new HashMap<>();
	}
	
	public Match get(int id) {
		return idMap.get(id);
	}
	
	public Match get(Match match) {
		Match old = idMap.get(match.getId());
		if(old==null) {
			idMap.put(match.getId(), match);
			return match;
		}
		return old;
	}

	public void put(Match match, int id) {
		idMap.put(id, match);
	}
}
