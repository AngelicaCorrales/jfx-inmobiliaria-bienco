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
		graph.addEdge(graph.getVertices().get(0), graph.getVertices().get(2), 2);
		graph.addEdge(graph.getVertices().get(1), graph.getVertices().get(0), 2);
		graph.addEdge(graph.getVertices().get(1), graph.getVertices().get(4), 6);
		graph.addEdge(graph.getVertices().get(2), graph.getVertices().get(1), 2);
		graph.addEdge(graph.getVertices().get(2), graph.getVertices().get(4), 3);
		graph.addEdge(graph.getVertices().get(3), graph.getVertices().get(1), 4);
		graph.addEdge(graph.getVertices().get(3), graph.getVertices().get(4), 8);
		graph.addEdge(graph.getVertices().get(5), graph.getVertices().get(5), 10);
		graph.addEdge(graph.getVertices().get(5), graph.getVertices().get(4), 5);
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
		addEdgesDirected();
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
		addEdgesDirected();
	}
	
	@Test
	public void testAddVertex1() {
		setupScenary1();
		Building objBuilding = new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 2750000.0, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, baño,  comedor, sitio para lavado y un pequeño balcón.");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==1);
		assertFalse(((GraphAL<Building>) graph).getAdjList().isEmpty());
		assertFalse(graph.getWeights().isEmpty());
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 11B # 2-16");
	}
	
	
	@Test
	public void testAddVertex2() {
		setupScenary2();
		Building objBuilding = new Building("Calle 8B # 2-18", "Vipasa", Zone.NORTE, TypeOfBuilding.OFICINA, 500000.0, false, "Oficina de 200 metros con baño incluido, buena ubicación en zona comercial, se encuentra en un primer piso");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==7);
		assertEquals(((GraphAL<Building>) graph).getAdjList().size(), 7);
		assertEquals(graph.getWeights().size(), 7);
		assertEquals(graph.getVertices().get(6).getValue().getAddress(), "Calle 8B # 2-18");
	}
	
	@Test
	public void testAddVertex3() {
		setupScenary5();
		Building objBuilding = new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 2750000.0, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, baño,  comedor, sitio para lavado y un pequeño balcón.");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==1);
		assertFalse(((GraphAM <Building>) graph).getadjMatrix().isEmpty());
		assertFalse(graph.getWeights().isEmpty());
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 11B # 2-16");
	}
	
	
	@Test
	public void testAddVertex4() {
		setupScenary6();
		Building objBuilding = new Building("Calle 8B # 2-18", "Vipasa", Zone.NORTE, TypeOfBuilding.OFICINA, 500000.0, false, "Oficina de 200 metros con baño incluido, buena ubicación en zona comercial, se encuentra en un primer piso");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==7);
		assertEquals(((GraphAM<Building>) graph).getadjMatrix().size(), 7);
		assertEquals(graph.getWeights().size(), 7);
		assertEquals(graph.getVertices().get(6).getValue().getAddress(), "Calle 8B # 2-18");
	}
	
	@Test
	public void testDijkstra1() {
		setupScenary1();
		Building objBuilding = new Building("Calle 7A # 4-15", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 205000000.0, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 baños, garaje y patio trasero.");
		Vertex<Building> vertex = new Vertex<>(objBuilding);
		graph.dijkstra(vertex);
		assertTrue(graph.getVertices().isEmpty());
		assertTrue(((GraphAL<Building>)graph).getAdjList().isEmpty());
	}
	
	@Test
	public void testDijkstra2() {
		setupScenary2();
		graph.dijkstra(graph.getVertices().get(0));
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 4C # 5-29" );
		assertEquals(graph.getVertices().get(0).getDistance(),0);
		assertEquals(graph.getVertices().get(0).getPredecesor(), null);
		assertEquals(graph.getVertices().get(1).getValue().getAddress(),"Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(1).getDistance(), 3);
		assertEquals(graph.getVertices().get(1).getPredecesor().getValue().getAddress(), "Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(2).getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(2).getDistance(),8);
		assertEquals(graph.getVertices().get(2).getPredecesor().getValue().getAddress(), "Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(3).getValue().getAddress(), "Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(3).getDistance(),2);
		assertEquals(graph.getVertices().get(3).getPredecesor().getValue().getAddress(), "Calle 4C # 5-29");
		assertEquals(graph.getVertices().get(4).getValue().getAddress(),"Calle 9B # 8-19");
		assertEquals(graph.getVertices().get(4).getDistance(),10);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),13);
		assertEquals(graph.getVertices().get(5).getPredecesor().getValue().getAddress(),"Calle 9B # 8-19");
	}
	
	@Test
	public void testDijkstra3() {
		setupScenary5();
		Building objBuilding = new Building("Calle 7A # 4-15", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 205000000.0, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 baños, garaje y patio trasero.");
		Vertex<Building> vertex = new Vertex<>(objBuilding);
		graph.dijkstra(vertex);
		assertTrue(graph.getVertices().isEmpty());
		assertTrue(((GraphAM<Building>)graph).getadjMatrix().isEmpty());
	}
	
	@Test
	public void testDijkstra4() {
		setupScenary6();
		graph.dijkstra(graph.getVertices().get(0));
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 4C # 5-29" );
		assertEquals(graph.getVertices().get(0).getDistance(),0);
		assertEquals(graph.getVertices().get(0).getPredecesor(), null);
		assertEquals(graph.getVertices().get(1).getValue().getAddress(),"Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(1).getDistance(), 3);
		assertEquals(graph.getVertices().get(1).getPredecesor().getValue().getAddress(), "Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(2).getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(2).getDistance(),8);
		assertEquals(graph.getVertices().get(2).getPredecesor().getValue().getAddress(), "Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(3).getValue().getAddress(), "Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(3).getDistance(),2);
		assertEquals(graph.getVertices().get(3).getPredecesor().getValue().getAddress(), "Calle 4C # 5-29");
		assertEquals(graph.getVertices().get(4).getValue().getAddress(),"Calle 9B # 8-19");
		assertEquals(graph.getVertices().get(4).getDistance(),10);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),13);
		assertEquals(graph.getVertices().get(5).getPredecesor().getValue().getAddress(),"Calle 9B # 8-19");
	}
	
	@Test
	public void testBfs1() {
		setupScenary2();
		graph.bfs(graph.getVertices().get(0));
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 4C # 5-29" );
		assertEquals(graph.getVertices().get(0).getDistance(),0);
		assertEquals(graph.getVertices().get(0).getPredecesor(), null);
		assertEquals(graph.getVertices().get(1).getValue().getAddress(),"Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(1).getDistance(), 1);
		assertEquals(graph.getVertices().get(1).getPredecesor().getValue().getAddress(), "Calle 4C # 5-29");
		assertEquals(graph.getVertices().get(2).getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(2).getDistance(),2);
		assertEquals(graph.getVertices().get(2).getPredecesor().getValue().getAddress(), "Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(3).getValue().getAddress(), "Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(3).getDistance(),1);
		assertEquals(graph.getVertices().get(3).getPredecesor().getValue().getAddress(), "Calle 4C # 5-29");
		assertEquals(graph.getVertices().get(4).getValue().getAddress(),"Calle 9B # 8-19");
		assertEquals(graph.getVertices().get(4).getDistance(),2);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),3);
		assertEquals(graph.getVertices().get(5).getPredecesor().getValue().getAddress(),"Calle 7A # 4-15");
	}
	
	@Test
	public void testBfs2() {
		setupScenary6();
		graph.bfs(graph.getVertices().get(0));
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 4C # 5-29" );
		assertEquals(graph.getVertices().get(0).getDistance(),0);
		assertEquals(graph.getVertices().get(0).getPredecesor(), null);
		assertEquals(graph.getVertices().get(1).getValue().getAddress(),"Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(1).getDistance(), 1);
		assertEquals(graph.getVertices().get(1).getPredecesor().getValue().getAddress(), "Calle 4C # 5-29");
		assertEquals(graph.getVertices().get(2).getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(2).getDistance(),2);
		assertEquals(graph.getVertices().get(2).getPredecesor().getValue().getAddress(), "Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(3).getValue().getAddress(), "Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(3).getDistance(),1);
		assertEquals(graph.getVertices().get(3).getPredecesor().getValue().getAddress(), "Calle 4C # 5-29");
		assertEquals(graph.getVertices().get(4).getValue().getAddress(),"Calle 9B # 8-19");
		assertEquals(graph.getVertices().get(4).getiDistance(),2);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),3);
		assertEquals(graph.getVertices().get(5).getPredecesor().getValue().getAddress(),"Calle 7A # 4-15");
	}
	
	@Test
	public void testBfs3() {
		setupScenary4();
		graph.bfs(graph.getVertices().get(5));
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 4C # 5-29" );
		assertEquals(graph.getVertices().get(0).getDistance(),3);
		assertEquals(graph.getVertices().get(0).getPredecesor(), "Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(1).getValue().getAddress(),"Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(1).getDistance(), 2);
		assertEquals(graph.getVertices().get(1).getPredecesor().getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(2).getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(2).getDistance(),1);
		assertEquals(graph.getVertices().get(2).getPredecesor().getValue().getAddress(),"Calle 11B # 2-16" );
		assertEquals(graph.getVertices().get(3).getValue().getAddress(), "Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(3).getDistance(),4);
		assertEquals(graph.getVertices().get(3).getPredecesor().getValue().getAddress(), "Calle 4C # 5-29");
		assertEquals(graph.getVertices().get(4).getValue().getAddress(),"Calle 9B # 8-19");
		assertEquals(graph.getVertices().get(4).getiDistance(),1);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),0);
		assertEquals(graph.getVertices().get(5).getPredecesor().getValue().getAddress(),null);
	}
	
	@Test
	public void testBfs4() {
		setupScenary8();
		graph.bfs(graph.getVertices().get(5));
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 4C # 5-29" );
		assertEquals(graph.getVertices().get(0).getDistance(),3);
		assertEquals(graph.getVertices().get(0).getPredecesor(), "Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(1).getValue().getAddress(),"Calle 5B # 3-10");
		assertEquals(graph.getVertices().get(1).getDistance(), 2);
		assertEquals(graph.getVertices().get(1).getPredecesor().getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(2).getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(graph.getVertices().get(2).getDistance(),1);
		assertEquals(graph.getVertices().get(2).getPredecesor().getValue().getAddress(),"Calle 11B # 2-16" );
		assertEquals(graph.getVertices().get(3).getValue().getAddress(), "Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(3).getDistance(),4);
		assertEquals(graph.getVertices().get(3).getPredecesor().getValue().getAddress(), "Calle 4C # 5-29");
		assertEquals(graph.getVertices().get(4).getValue().getAddress(),"Calle 9B # 8-19");
		assertEquals(graph.getVertices().get(4).getiDistance(),1);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),0);
		assertEquals(graph.getVertices().get(5).getPredecesor().getValue().getAddress(),null);
	}
	
	
	@Test
	public void testAddEdge1() {
		fail("Not yet implemented");
	}
	
}
