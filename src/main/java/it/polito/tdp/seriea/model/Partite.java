package it.polito.tdp.seriea.model;

public class Partite implements Comparable<Partite>{

	Integer v1;
	Integer v2;
	Integer peso;
	
	public Partite(Integer v1, Integer v2, Integer peso) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.peso = peso;
	}

	public Integer getV1() {
		return v1;
	}

	public void setV1(Integer v1) {
		this.v1 = v1;
	}

	public Integer getV2() {
		return v2;
	}

	public void setV2(Integer v2) {
		this.v2 = v2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Partite o) {
		return -(this.getPeso().compareTo(o.getPeso()));
	}

	@Override
	public String toString() {
		return "Partite [v1=" + v1 + ", v2=" + v2 + ", peso=" + peso + "]";
	}
	
	
	
	
}
