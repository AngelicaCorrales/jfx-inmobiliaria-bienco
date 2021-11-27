package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Stack;

import dataStructures.Graph;
import dataStructures.GraphAL;
import dataStructures.GraphAM;
import dataStructures.Vertex;
import exceptions.NegativeValueException;
import exceptions.NoValueException;
import exceptions.SimpleGraphException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
		Building founded = searchBuilding(address);
		if(founded==null) {
			Building newBuilding = new Building(address, neighborhood, Zone.valueOf(zone.toUpperCase()), TypeOfBuilding.valueOf(typeOfBuilding.toUpperCase()), price, forSale, observations);
			buildings.add(newBuilding);	

			added = true;
		}
		//saveDataBienco();
		return added;
	}

	private Building searchBuilding(String address) {
		Building founded = null;
		for(int k=0; k<buildings.size();k++) {
			if(buildings.get(k).getAddress().equals(address)) {
				founded = buildings.get(k);
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
            String inicialMessage="***DISTANCIAS AGREGADAS: ***\n";
            
            Vertex<Building> uVertex = graph.searchVertex(u);
            Vertex<Building> vVertex = graph.searchVertex(v);
            
            graphAL.addEdge(uVertex,vVertex,distanceToInt);
            graphAM.addEdge(uVertex,vVertex,distanceToInt);
            
            String message="El inmueble: "+u.getAddress()+" con el inmueble: "+v.getAddress()+" ,tienen una distancia de: "+distanceToInt;
            
            return inicialMessage+="\n"+message+"\n";
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

	public void deleteBuilding(Building selectedItem) {
		buildings.remove(selectedItem);
		//saveDataBienco();
	}


	public boolean updateBuilding(Building b, String newAddress, String nbhd, String newZone, String newType, String p, boolean forSale, String observations) throws NoValueException, NegativeValueException {
		Building building = searchBuilding(newAddress);
		boolean updated=false;
		boolean findBuilding = false;
		double price = Double.parseDouble(p);
		if(price<0) {
			throw new NegativeValueException(price);
		}else if (price==0) {
			throw new NoValueException(price);
		}
		if(b!=building) {
			if(building!=null) {
				findBuilding =true;
			}
		}
		if(!findBuilding) {
			b.setAddress(newAddress);
			b.setNeighborhood(nbhd);
			b.setZone(Zone.valueOf(newZone.toUpperCase()));
			b.setType(TypeOfBuilding.valueOf(newType.toUpperCase()));
			b.setPrice(price);
			b.setForSale(forSale);
			b.setObservations(observations);
			//saveDataBienco();
			updated=true;
		}
		return updated;
	}

	public void generatePDFReport(OutputStream txt, ArrayList<Building> buildings) throws DocumentException {
		Document doc = new Document(PageSize.LETTER);
		PdfWriter.getInstance(doc, txt);
		doc.open();
		//Font negrilla = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		Font normal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
		PdfPTable tbl_routes = new PdfPTable(2);
		Paragraph texto = null;
		
		try {
			Image imagen = Image.getInstance("src/ui/Plantilla.png");
			 imagen.setAlignment(Element.ALIGN_CENTER);
			 doc.add(imagen);
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		} 
		
		texto = new Paragraph();
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);

		tbl_routes.setWidths(new int[]{1, 4});
		String[][] data = new String[buildings.size()][2];
		for (int x=0; x < data.length; x++) {
			Building b = buildings.get(x);
			for (int y=0; y < data[x].length; y++) {
				if(y==1) {
					data[x][y] = b.getAddress();
				}
			}
		}
		for (String[] row : data) {
			tbl_routes.addCell(createImageCell(1, Element.ALIGN_LEFT));
			tbl_routes.addCell(createTextCell(row[1], normal, 4, Element.ALIGN_LEFT));
		}
		doc.add(tbl_routes);
		texto = new Paragraph();
		texto.add(new Phrase(Chunk.NEWLINE));
		texto.add(new Phrase(Chunk.NEWLINE));
		doc.add(texto);
		doc.close();
	}
	
	public PdfPCell createTextCell(String content, Font f, int colspan, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(content, f));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(colspan);
		cell.setHorizontalAlignment(alignment);
		return cell;
	}
	
	public PdfPCell createImageCell(int colspan, int alignment) {
		PdfPCell cell = null;
		try {
			Image imagen = Image.getInstance("src/ui/checkbox.png");
			cell = new PdfPCell(imagen);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(colspan);
			cell.setHorizontalAlignment(alignment);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cell;
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
