package it.polito.tdp.crimes.model;

import org.jgrapht.graph.DefaultWeightedEdge;

public class TestModel {

	public static void main(String[] args) {
		Model m = new Model();
		m.creaGrafo("all-other-crimes", 2015);
		
		for (String r : m.getGrafo().vertexSet())
			System.out.println("ID: " + r);
		
		for (DefaultWeightedEdge e : m.getGrafo().edgeSet())
			System.out.println(String.format("Da %s a %s", m.getGrafo().getEdgeSource(e), m.getGrafo().getEdgeTarget(e)));
		
		System.out.println(String.format("Numero di vertici: %d e di archi: %d", m.getGrafo().vertexSet().size(), m.getGrafo().edgeSet().size()));
		
		System.out.println(m.archiMassimi());
		System.out.println("Partendo da criminal-trespassing e raggiungendo police-interference");
		System.out.println(m.getPath("criminal-trespassing", "police-interference"));
		
		System.out.println(m.getPath("criminal-trespassing", "police-interference").size());
	}

}
