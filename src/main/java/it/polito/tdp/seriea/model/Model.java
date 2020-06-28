package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	
	private SerieADAO dao;
	private Graph<Team, DefaultWeightedEdge> grafo;
	private Map<String, Team> idMap;
	private List<Team> vertici;
	
	public Model() {
		dao= new SerieADAO();
		idMap= new HashMap<>();
	}

	public void creaGrafo() {
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao.listTeams(idMap);
		vertici= this.dao.getVertici(idMap);
		
		Graphs.addAllVertices(grafo, vertici);
		
		for(Squadre s: dao.getArchi(idMap)) {
			if(s.getT1()!=s.getT2() && this.grafo.containsVertex(s.getT1()) && this.grafo.containsVertex(s.getT2())) {
			//	DefaultWeightedEdge e= this.grafo.getEdge(s.getT1(),s.getT2());
			//	if(e==null) {
				Graphs.addEdgeWithVertices(grafo, s.getT1(), s.getT2(), s.getPeso());
		//	}else {
			//	Double peso= this.grafo.getEdgeWeight(e);
			//	Double pesoNuovo= (peso+s.getPeso())/2;
			//	Graphs.addEdge(grafo, s.getT1(), s.getT2(), pesoNuovo);
			//	}
			}
		}
		
		
		System.out.println("Grafo creato con "+ this.grafo.vertexSet().size()+"vertici e con "+ this.grafo.edgeSet().size()+"archi");
		
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Team> getTeam(){
		return this.dao.getVertici(idMap);
	}
	
	public List<Squadre> getVicini(Team t){
		List<Squadre> result= new ArrayList<>();
		
		List<Team> vicini= Graphs.neighborListOf(grafo, t);
		
		for(Team v: vicini) {
			Integer peso= (int) this.grafo.getEdgeWeight(this.grafo.getEdge(t, v));
			result.add(new Squadre(t, v, peso));
		}
		Collections.sort(result);
		return result;
	}
}
