package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Building;
import model.TypeOfBuilding;
import model.Zone;

public class GraphTest {

	private Graph<Building> graph;
	
	public void setupScenary1() {
		graph= new GraphAL<>(true, false);
	}
	public void addVertices() {
		//String address, String neighborhood,Zone zone,TypeOfBuilding type, double price, boolean forSale,String observations
		graph.addVertex(new Building("Calle 4C # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 580000, false, "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero."));
		graph.addVertex(new Building("Calle 5B # 3-10", "Porvenir", Zone.SUR, TypeOfBuilding.LOCAL, 620000, false, "Local de 500 metros cuadrados con segundo piso que contiene un baño"));
		graph.addVertex(new Building("Calle 7A # 4-15", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 205000000, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 baños, garaje y patio trasero."));
		graph.addVertex(new Building("Calle 3A # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.APARTAESTUDIO, 1000000, false, "Apartaestudio amoblado ubicado en segundo piso. Contiene 2 habitaciones, sala, cocina, 1 baño."));
		graph.addVertex(new Building("Calle 9B # 8-19", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 350000000, true, "Casa de 2 plantas que contiene 3 habitaciones, sala, comedor, 2 baños, garaje pequeño y un patio trasero."));
		graph.addVertex(new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 275000000, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, baño,  comedor, sitio para lavado y un pequeño balcón."));

	}
	
	public void addEdgesNoDirected() {
		graph.addEdge(graph.getVertices().get(0), graph.getVertices().get(1), 4);
		graph.addEdge(graph.getVertices().get(1), graph.getVertices().get(2), 5);
		graph.addEdge(graph.getVertices().get(2), graph.getVertices().get(3), 8);
		graph.addEdge(graph.getVertices().get(3), graph.getVertices().get(4), 10);
		graph.addEdge(graph.getVertices().get(4), graph.getVertices().get(5), 3);
		graph.addEdge(graph.getVertices().get(0), graph.getVertices().get(3), 2);
		graph.addEdge(graph.getVertices().get(1), graph.getVertices().get(3), 1);
		graph.addEdge(graph.getVertices().get(2), graph.getVertices().get(4), 2);
		graph.addEdge(graph.getVertices().get(2), graph.getVertices().get(5), 6);

	}
	public void addEdgesDirected() {
		
	}
	
	
	
	public void setupScenary2() {
		graph= new GraphAL<>(true, false);
		addVertices();
		addEdgesNoDirected();

	}
	
	public void setupScenary3() {
		graph= new GraphAL<>(true, true);
	}
	
	public void setupScenary4() {
		graph= new GraphAL<>(true, true);
		addVertices();
		//BUILDINGS!!
	}
	
	
	public void setupScenary5() {
		graph= new GraphAM<>(true, false);
	}
	
	public void setupScenary6() {
		graph= new GraphAM<>(true, false);
		addVertices();
		addEdgesNoDirected();
		
	}
	
	public void setupScenary7() {
		graph= new GraphAM<>(true, true);
	}
	
	public void setupScenary8() {
		graph= new GraphAM<>(true, true);
		addVertices();
	}
	
	
	
	@Test
	public void testAddEdge1() {
		fail("Not yet implemented");
	}

}
