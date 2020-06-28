package it.polito.tdp.seriea.model;

import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	private SerieADAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private List<Integer> vertici;
	
	public Model() {
		dao = new SerieADAO();
	}
	
	public void creaGrafo() {
		grafo= new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		vertici= dao.getVertici();
		
		Graphs.addAllVertices(grafo, vertici);
		
		for(Partite p: dao.getArchi()) {
			if(p.getV1()!=p.getV2() && this.grafo.containsVertex(p.getV1()) && this.grafo.containsVertex(p.getV2())) {
			//	if(p.getV1()>p.getV2()) {
					Graphs.addEdgeWithVertices(grafo, p.getV1(), p.getV2(), p.getPeso());
				//}else {
				//	Graphs.addEdgeWithVertices(grafo, p.getV2(),  p.getV1(), p.getPeso());
				//}
			}
		}
		
		System.out.println("Grafo creato con " + this.grafo.vertexSet().size()+" vertici e con "+ this.grafo.edgeSet().size()+" archi");
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Integer> getVertici(){
		return vertici;
	}
	
	public List<Partite> getRisultati(Integer goal){
		List<Partite> res= this.dao.getRisultati(goal);
		Collections.sort(res);
		return res;
	}

}
