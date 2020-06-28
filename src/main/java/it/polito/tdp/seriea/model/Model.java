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
	private Graph<Season, DefaultWeightedEdge> grafo;
	private Map<Integer, Season> idMap;
	private List<Season> vertici;
	
	public Model() {
		dao= new SerieADAO();
		idMap= new HashMap<>();
	}
	
	public void creaGrafo() {
		dao.listSeasons(idMap);
		vertici= dao.getVertici(idMap);
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, vertici);
		
		for(Connessione c: dao.getArchi(idMap)) {
			if(c.getS1()!=c.getS2() && this.grafo.containsVertex(c.getS1()) && this.grafo.containsVertex(c.getS2())) {
				Graphs.addEdgeWithVertices(grafo, c.getS1(), c.getS2(), c.getPeso());
			}
		}
		
		System.out.println("Grafo creato con "+ this.grafo.vertexSet().size() +"vertici e con "+ this.grafo.edgeSet().size()+" archi");
		
	
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Season> getStagioni(){
		return this.vertici;
	}
	
	public List<Connessione> getVicini(Season s){
		List<Connessione> result= new ArrayList<>();
		
		List<Season> vicini= Graphs.neighborListOf(grafo, s);
		
		for(Season ss: vicini) {
			Integer peso= (int) this.grafo.getEdgeWeight(this.grafo.getEdge(s, ss));
			result.add(new Connessione(s, ss, peso));
		}
		
		Collections.sort(result);
		return result;
	}
	
	
}
