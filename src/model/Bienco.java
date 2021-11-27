package model;

import java.util.ArrayList;

import dataStructures.Graph;
import dataStructures.GraphAL;
import dataStructures.GraphAM;

public class Bienco {

	private Graph<Building> graph;
	private GraphAL<Building> graphAL;
	private GraphAM<Building> graphAM;
	
	private ArrayList<Building> buildings;
	
	
	public Bienco() {
		buildings=new ArrayList<>();
	}


	public Graph<Building> getGraph() {
		return graph;
	}


	public void setGraph(Graph<Building> graph) {
		this.graph = graph;
	}


	public ArrayList<Building> getBuildings() {
		return buildings;
	}


	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}


	public GraphAL<Building> getGraphAL() {
		return graphAL;
	}


	public void setGraphAL(GraphAL<Building> graphAL) {
		this.graphAL = graphAL;
	}


	public GraphAM<Building> getGraphAM() {
		return graphAM;
	}


	public void setGraphAM(GraphAM<Building> graphAM) {
		this.graphAM = graphAM;
	}
	
	
	
	
}
