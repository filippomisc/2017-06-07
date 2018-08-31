package it.polito.tdp.seriea.model;

public class Result {
	
	private Team tHome;
	private Team tAway;
	private int peso;
	
	public Result(Team tHome, Team tAway, int peso) {
		this.tHome = tHome;
		this.tAway = tAway;
		this.peso = peso;
	}

	public Team gettHome() {
		return tHome;
	}

	public Team gettAway() {
		return tAway;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return "Result [tHome=" + tHome + ", tAway=" + tAway + ", peso=" + peso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tAway == null) ? 0 : tAway.hashCode());
		result = prime * result + ((tHome == null) ? 0 : tHome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (tAway == null) {
			if (other.tAway != null)
				return false;
		} else if (!tAway.equals(other.tAway))
			return false;
		if (tHome == null) {
			if (other.tHome != null)
				return false;
		} else if (!tHome.equals(other.tHome))
			return false;
		return true;
	}
	
	
	

}
