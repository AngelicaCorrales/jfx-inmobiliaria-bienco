package model;

import java.util.ArrayList;

import dataStructures.Graph;
import dataStructures.GraphAL;
import dataStructures.GraphAM;
import exceptions.NegativeValueException;
import exceptions.NoValueException;

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


	public boolean addBuilding(String address, String neighborhood, String zone, String typeOfBuilding, String p, String purpose, String observations) throws NoValueException, NegativeValueException {
		double price = Double.parseDouble(p);
		if(price<0) {
			throw new NegativeValueException(price);
		}else if (price==0) {
			throw new NoValueException(price);
		}
		boolean founded = searchBuilding(address);
		if(founded==false) {
			Building newBuilding = new Building(address, neighborhood, zone, typeOfBuilding, price, purpose, observations);
			buildings.add(newBuilding);	
		}
	}
	
	private boolean searchBuilding(String address) {
		boolean founded = false;
		for(int k=0; k<buildings.size();k++) {
			if(buildings.get(k).getAddress().equals(address)) {
				founded = true;
			}
		}
		return founded;
	}
	
}
