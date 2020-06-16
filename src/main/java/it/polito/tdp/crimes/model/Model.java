package it.polito.tdp.crimes.model;
import it.polito.tdp.crimes.db.EventsDao;
import javafx.scene.effect.Light.Spot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm;
import org.jgrapht.alg.interfaces.HamiltonianCycleImprovementAlgorithm;
import org.jgrapht.alg.tour.HamiltonianCycleAlgorithmBase;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


public class Model {
	EventsDao dao;
	Graph<String, DefaultWeightedEdge> grafo;
	List<String> path;
	Double mainTot = Double.MAX_VALUE;


	public Graph<String, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public Model() {
		super();
		this.dao = new EventsDao();
	}
	
	public List<String> listAllOffenseCategoryId() {
		return dao.listAllOffenseCategoryId();
	}

	public void creaGrafo(String categoryId, int year) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		for (Event e : dao.listAllEvents(categoryId, year))
			if(!grafo.containsVertex(e.getOffense_type_id()))
				grafo.addVertex(e.getOffense_type_id());
		 
		for (Arco a : dao.listArchi(new ArrayList<String>(grafo.vertexSet()), categoryId, year))
			this.grafo.setEdgeWeight(grafo.addEdge(a.getCategoryIdDue(), a.getCategoryIdUno()), a.getWeight());
		
		
	}
	
	public List<Arco> archiMassimi() {
		Double maxValue = 0.0;
		List<Arco> maxArchi = new ArrayList<Arco>();
		
		for (DefaultWeightedEdge e : grafo.edgeSet())
			if (grafo.getEdgeWeight(e)>maxValue)
				maxValue = grafo.getEdgeWeight(e);
		
		for (DefaultWeightedEdge e : grafo.edgeSet())
			if(grafo.getEdgeWeight(e)==maxValue)
				maxArchi.add(new Arco(grafo.getEdgeSource(e), grafo.getEdgeTarget(e), (int) grafo.getEdgeWeight(e)));
		
		return maxArchi;
	}
	
	/* TODO: da mettere a posto */
	
	public List<String> getPath(String precedente, String ultimo) {
		List<String> spot = new ArrayList<String>(grafo.vertexSet());
		Map<String, Double> part = new HashMap<String, Double>();
		
		recursive(spot, part, precedente, ultimo);
		
		return this.path;
	}
	
	public void recursive(List<String> spot, Map<String, Double> part, String precedente, String ultimo) {
		if (spot.isEmpty())
		{
			Double tot = 0.0;
			
			for(Double d : part.values())
				tot += d;
			
			if (tot<mainTot)
				path = new ArrayList<String>(part.keySet());
			return;
		}
		
		for (DefaultWeightedEdge r : grafo.edgesOf(precedente)) {
			String opp = Graphs.getOppositeVertex(grafo, r, precedente);
			Double weight = grafo.getEdgeWeight(r);
			
			if (!part.containsKey(opp) && spot.contains(opp)) {
				part.put(opp, weight);
				spot.remove(opp);
				
				if(opp.equals(ultimo))
					return;
				else
					this.recursive(spot, part, opp, ultimo);
				
				part.remove(opp);
			}
		}
	}
	
	

}
