package model;

import java.util.ArrayList;
import java.util.Stack;

import dataStructures.Graph;
import dataStructures.GraphAL;
import dataStructures.GraphAM;
import dataStructures.Vertex;
import exceptions.NegativeValueException;
import exceptions.NoValueException;
import exceptions.SimpleGraphException;

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

	public ArrayList<Building> getFilterBuildings(){
		ArrayList<Building> filter= new ArrayList<Building>();
		for(int i=0; i<graph.getVertices().size();i++) {
			filter.add(graph.getVertices().get(i).getValue());
		}
		return filter;
	}
        
        public String addDistancesBetweenProperties (Building u,Building v,Integer weight) throws SimpleGraphException{
            String message="El inmueble: "+u.getAddress()+" con el inmueble: "+v.getAddress()+" ,tienen una distancia de: "+weight;
            
            Vertex<Building> uVertex = graph.searchVertex(u);
            Vertex<Building> vVertex = graph.searchVertex(v);
            
            graphAL.addEdge(uVertex,vVertex,weight);
            graphAM.addEdge(uVertex,vVertex,weight);
            
            return message;
        }

	public String calculateRoute(Building building) {
		String routes="*** Rutas calculadas: ***\n";
		int suggested=-1;
		ArrayList<Stack<Vertex<Building>>> paths= new ArrayList<>();
		Vertex<Building> bv=graph.searchVertex(building);
		graph.dijkstra(bv);

		for(int i=0; i<graph.getVertices().size();i++) {
			Vertex<Building> vertex=graph.getVertices().get(i);
			paths.add(new Stack<>());

			while(vertex!=null) {
				paths.get(i).push(vertex);
				vertex=vertex.getPredecesor();
			}

			if(paths.get(i).peek()!=bv) {
				paths.remove(i);

			}else {

				if(suggested==-1) {
					suggested=i;
				}else {
					if(paths.get(suggested).size()<paths.get(i).size()) {
						suggested=i;
					}
				}
			}
		}

		String suggRoute="";

		for(int i=0; i<paths.size();i++) {
			String route="";
			while(!paths.get(i).isEmpty()) {
				Vertex<Building> property=paths.get(i).pop();
				int distance=property.getDistance();
				route+= property.getValue();

				if(!paths.get(i).isEmpty()) {
					route+=" --> ";

				}else {
					route+="|Distancia: "+ distance +"metros\n\n";
				}
			}
			routes+=route;
			if(i==suggested) {
				suggRoute+=route;
			}

		}

		routes+="*** Ruta sugerida: ***\n"+suggRoute+"\n";


		return routes;
	}


}
