package it.polito.tdp.seriea.model;

public class Classificata {

	private Team squadra;
	private int punteggio;
	
	public Classificata(Team squadra, int punteggio) {
		super();
		this.squadra = squadra;
		this.punteggio = 0;
	}

	public Team getSquadra() {
		return squadra;
	}

	public void setSquadra(Team squadra) {
		this.squadra = squadra;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((squadra == null) ? 0 : squadra.hashCode());
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
		Classificata other = (Classificata) obj;
		if (squadra == null) {
			if (other.squadra != null)
				return false;
		} else if (!squadra.equals(other.squadra))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Classificata [squadra=" + squadra + ", punteggio=" + punteggio + "]";
	}
	
	
	
}
