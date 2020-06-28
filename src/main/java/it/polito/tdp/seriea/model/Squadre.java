package it.polito.tdp.seriea.model;

public class Squadre implements Comparable<Squadre>{
	Team t1;
	Team t2;
	Integer peso;
	
	public Squadre(Team t1, Team t2, Integer peso) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.peso = peso;
	}

	public Team getT1() {
		return t1;
	}

	public void setT1(Team t1) {
		this.t1 = t1;
	}

	public Team getT2() {
		return t2;
	}

	public void setT2(Team t2) {
		this.t2 = t2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Squadre o) {
		return -(this.getPeso().compareTo(o.getPeso()));
	}

	@Override
	public String toString() {
		return  t2 + " " + peso ;
	}
	
	
	

}
