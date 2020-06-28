package it.polito.tdp.seriea.model;

public class Connessione implements Comparable<Connessione> {
	Season s1;
	Season s2;
	Integer peso;
	
	public Connessione(Season s1, Season s2, Integer peso) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.peso = peso;
	}

	public Season getS1() {
		return s1;
	}

	public void setS1(Season s1) {
		this.s1 = s1;
	}

	public Season getS2() {
		return s2;
	}

	public void setS2(Season s2) {
		this.s2 = s2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Connessione o) {
		return this.getS1().getDescription().compareTo(o.getS1().getDescription());
	}

	@Override
	public String toString() {
		return  s2 + " " + peso;
	}
	
	

}
