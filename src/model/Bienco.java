package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import dataStructures.Graph;
import dataStructures.GraphAL;
import dataStructures.GraphAM;
import dataStructures.Vertex;
import exceptions.NegativeValueException;
import exceptions.NoValueException;
import exceptions.SimpleGraphException;

public class Bienco implements Serializable {

	private static final long serialVersionUID = 1;
	public  static String BIENCO_SAVE_PATH_FILE;
	public final static int PROGRAM=0;
	public final static int TEST=1;

	private Graph<Building> graph;
	private GraphAL<Building> graphAL;
	private GraphAM<Building> graphAM;

	private ArrayList<Building> buildings;


	public Bienco(int num) {
		if(num==PROGRAM) {
			BIENCO_SAVE_PATH_FILE = "data/bienco.ackldo";
		}
		if(num==TEST) {
			BIENCO_SAVE_PATH_FILE ="data/biencoTest.ackldo";
		}
		buildings=new ArrayList<>();
		graph=graphAL;
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



	public boolean addBuilding(String address, String neighborhood, String zone, String typeOfBuilding, String p, boolean forSale, String observations) throws NoValueException, NegativeValueException {
		boolean added= false;
		double price = Double.parseDouble(p);
		if(price<0) {
			throw new NegativeValueException(price);
		}else if (price==0) {
			throw new NoValueException(price);
		}
		boolean founded = searchBuilding(address);
		if(founded==false) {
			Building newBuilding = new Building(address, neighborhood, Zone.valueOf(zone.toUpperCase()), TypeOfBuilding.valueOf(typeOfBuilding.toUpperCase()), price, forSale, observations);
			buildings.add(newBuilding);	

			added = true;
		}
		//saveDataBienco();
		return added;
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

        
        public String addDistancesBetweenProperties (Building u,Building v,String weight) throws SimpleGraphException{
            int distanceToInt = Integer.valueOf(weight);
            String message="El inmueble: "+u.getAddress()+" con el inmueble: "+v.getAddress()+" ,tienen una distancia de: "+distanceToInt;
            
            Vertex<Building> uVertex = graph.searchVertex(u);
            Vertex<Building> vVertex = graph.searchVertex(v);
            
            graphAL.addEdge(uVertex,vVertex,distanceToInt);
            graphAM.addEdge(uVertex,vVertex,distanceToInt);
            
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

	public void saveDataBienco() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIENCO_SAVE_PATH_FILE));
		oos.writeObject(this);
		oos.close();
	}

	public Bienco loadDataBienco(Bienco bienco) throws IOException, ClassNotFoundException{
		File f = new File(BIENCO_SAVE_PATH_FILE);
		if(f.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			bienco = (Bienco)ois.readObject();
			ois.close();
		}
		return bienco;
	}


	public void importData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		String line = "";
		try {
			line = br.readLine();

			while(line != null) {

				try {
					String[] parts = line.split(";");

					//String address, String neighborhood,Zone zone,TypeOfBuilding type, double price, boolean forSale,String observations

					if(!parts[0].equals("Tm")) {
						double price = Double.parseDouble(parts[4]);
						boolean forSale=false;
						if(parts[5].equalsIgnoreCase("Venta")) {
							forSale=true;
						}
						Building building = new Building(parts[0], parts[1], Zone.valueOf(parts[2].toUpperCase()), TypeOfBuilding.valueOf(parts[3].toUpperCase()),price,forSale,parts[6]);
						buildings.add(building);

					}

					line = br.readLine();

				} catch (NumberFormatException e) {
					line = br.readLine();
				}
			}

		}catch (IOException e) {
		}
		try {
			br.close();
		} catch (IOException e) {
		}

	}


	public void filterBuildings(String neighborhood, String zone, String typeOfBuilding, String pFrom, String pTo, String purpose) throws NegativeValueException, NoValueException {
		graphAL=new GraphAL<Building>(true,false);
		graphAM=new GraphAM<Building>(true,false);
		double priceFrom = 0;
		double priceTo = Double.MAX_VALUE;
		if(!pFrom.isEmpty()) {

			priceFrom = Double.parseDouble(pFrom);

			if(priceFrom<0) {
				throw new NegativeValueException(priceFrom);
			}else if (priceFrom==0) {
				throw new NoValueException(priceFrom);
			}

		}

		if(!pTo.isEmpty()) {

			priceTo = Double.parseDouble(pTo);

			if(priceTo<0) {
				throw new NegativeValueException(priceTo);
			}else if (priceTo==0) {
				throw new NoValueException(priceTo);
			}
		}
		boolean forSale=false;
		if(!purpose.isEmpty()) {
			if(purpose.equals("V")) {
				forSale=true;
			}
		}


		for(int i=0;i<buildings.size();i++) {
			if(!neighborhood.isEmpty() &&buildings.get(i).getAddress().equalsIgnoreCase(neighborhood)) {
				if(!zone.isEmpty() &&buildings.get(i).getZone().equalsIgnoreCase(zone)) {
					if(!typeOfBuilding.isEmpty() &&buildings.get(i).getType().equalsIgnoreCase(typeOfBuilding)) {

						if(!purpose.isEmpty() && buildings.get(i).isForSale()==forSale) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}

						}else if(purpose.isEmpty()) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}
						}

					}else if(typeOfBuilding.isEmpty()) {

						if(!purpose.isEmpty() && buildings.get(i).isForSale()==forSale) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}

						}else if(purpose.isEmpty()) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}
						}
					}
				}else if(zone.isEmpty()) {
					if(!typeOfBuilding.isEmpty() &&buildings.get(i).getType().equalsIgnoreCase(typeOfBuilding)) {

						if(!purpose.isEmpty() && buildings.get(i).isForSale()==forSale) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}

						}else if(purpose.isEmpty()) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}
						}

					}else if(typeOfBuilding.isEmpty()) {

						if(!purpose.isEmpty() && buildings.get(i).isForSale()==forSale) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}

						}else if(purpose.isEmpty()) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}
						}
					}
				}
				
				
				
			}else if(neighborhood.isEmpty()) {
				if(!zone.isEmpty() &&buildings.get(i).getZone().equalsIgnoreCase(zone)) {
					if(!typeOfBuilding.isEmpty() &&buildings.get(i).getType().equalsIgnoreCase(typeOfBuilding)) {

						if(!purpose.isEmpty() && buildings.get(i).isForSale()==forSale) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}

						}else if(purpose.isEmpty()) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}
						}

					}else if(typeOfBuilding.isEmpty()) {

						if(!purpose.isEmpty() && buildings.get(i).isForSale()==forSale) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}

						}else if(purpose.isEmpty()) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}
						}
					}
				}else if(zone.isEmpty()) {
					if(!typeOfBuilding.isEmpty() &&buildings.get(i).getType().equalsIgnoreCase(typeOfBuilding)) {

						if(!purpose.isEmpty() && buildings.get(i).isForSale()==forSale) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}

						}else if(purpose.isEmpty()) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}
						}

					}else if(typeOfBuilding.isEmpty()) {

						if(!purpose.isEmpty() && buildings.get(i).isForSale()==forSale) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}

						}else if(purpose.isEmpty()) {
							if(priceFrom<=buildings.get(i).getPrice() && buildings.get(i).getPrice()<= priceTo) {
								graphAL.addVertex(buildings.get(i));
								graphAM.addVertex(buildings.get(i));
							}
						}
					}
				}
			}

		}
	}
	
	public boolean connectionFilterBuildings() {
		boolean conected=true;
		if(!graph.getVertices().isEmpty()) {
			graph.bfs(graph.getVertices().get(0));
		}
		for(int i=0;i<graph.getVertices().size() && conected;i++) {
			if(graph.getVertices().get(i).getColor()!='B') {
				conected=false;
			}
		}
		
		return conected;
	}

}
