package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.SimpleGraphException;
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
		graph.addVertex(new Building("Calle 4C # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 580000, false, "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 banio y un pequenio patio trasero."));
		graph.addVertex(new Building("Calle 5B # 3-10", "Porvenir", Zone.SUR, TypeOfBuilding.LOCAL, 620000, false, "Local de 500 metros cuadrados con segundo piso que contiene un banio"));
		graph.addVertex(new Building("Calle 7A # 4-15", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 205000000, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 banios, garaje y patio trasero."));
		graph.addVertex(new Building("Calle 3A # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.APARTAESTUDIO, 1000000, false, "Apartaestudio amoblado ubicado en segundo piso. Contiene 2 habitaciones, sala, cocina, 1 banio."));
		graph.addVertex(new Building("Calle 9B # 8-19", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 350000000, true, "Casa de 2 plantas que contiene 3 habitaciones, sala, comedor, 2 banios, garaje pequeño y un patio trasero."));
		graph.addVertex(new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 275000000, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, banio,  comedor, sitio para lavado y un pequenio balcon."));

	}

	public void addEdgesNoDirected() throws SimpleGraphException {
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
	public void addEdgesDirected() throws SimpleGraphException {
		graph.addEdge(graph.getVertices().get(0), graph.getVertices().get(3), 2);
		graph.addEdge(graph.getVertices().get(1), graph.getVertices().get(0), 2);
		graph.addEdge(graph.getVertices().get(1), graph.getVertices().get(4), 6);
		graph.addEdge(graph.getVertices().get(2), graph.getVertices().get(1), 2);
		graph.addEdge(graph.getVertices().get(2), graph.getVertices().get(4), 3);
		graph.addEdge(graph.getVertices().get(3), graph.getVertices().get(1), 4);
		graph.addEdge(graph.getVertices().get(3), graph.getVertices().get(4), 8);
		graph.addEdge(graph.getVertices().get(5), graph.getVertices().get(2), 10);
		graph.addEdge(graph.getVertices().get(5), graph.getVertices().get(4), 5);
	}



	public void setupScenary2() throws SimpleGraphException {
		graph= new GraphAL<>(true, false);
		addVertices();
		addEdgesNoDirected();

	}

	public void setupScenary3() {
		graph= new GraphAL<>(true, true);
	}

	public void setupScenary4() throws SimpleGraphException {
		graph= new GraphAL<>(true, true);
		addVertices();
		addEdgesDirected();
	}


	public void setupScenary5() {
		graph= new GraphAM<>(true, false);
	}

	public void setupScenary6() throws SimpleGraphException {
		graph= new GraphAM<>(true, false);
		addVertices();
		addEdgesNoDirected();

	}

	public void setupScenary7() {
		graph= new GraphAM<>(true, true);
	}

	public void setupScenary8() throws SimpleGraphException {
		graph= new GraphAM<>(true, true);
		addVertices();
		addEdgesDirected();
	}

	@Test
	public void testAddVertex1() {
		setupScenary1();
		Building objBuilding = new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 2750000.0, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, banio,  comedor, sitio para lavado y un pequenio balcon.");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==1);
		assertFalse(((GraphAL<Building>) graph).getAdjList().isEmpty());
		assertFalse(graph.getWeights().isEmpty());
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 11B # 2-16");
	}


	@Test
	public void testAddVertex2() throws SimpleGraphException {
		setupScenary2();
		Building objBuilding = new Building("Calle 8B # 2-18", "Vipasa", Zone.NORTE, TypeOfBuilding.OFICINA, 500000.0, false, "Oficina de 200 metros con ba�o incluido, buena ubicaci�n en zona comercial, se encuentra en un primer piso");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==7);
		assertEquals(((GraphAL<Building>) graph).getAdjList().size(), 7);
		assertEquals(graph.getWeights().size(), 7);
		assertEquals(graph.getVertices().get(6).getValue().getAddress(), "Calle 8B # 2-18");
	}

	@Test
	public void testAddVertex3() {
		setupScenary5();
		Building objBuilding = new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 2750000.0, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, ba�o,  comedor, sitio para lavado y un peque�o balc�n.");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==1);
		assertFalse(((GraphAM <Building>) graph).getAdjMatrix().isEmpty());
		assertFalse(graph.getWeights().isEmpty());
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 11B # 2-16");
	}


	@Test
	public void testAddVertex4() throws SimpleGraphException {
		setupScenary6();
		Building objBuilding = new Building("Calle 8B # 2-18", "Vipasa", Zone.NORTE, TypeOfBuilding.OFICINA, 500000.0, false, "Oficina de 200 metros con ba�o incluido, buena ubicaci�n en zona comercial, se encuentra en un primer piso");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==7);
		assertEquals(((GraphAM<Building>) graph).getAdjMatrix().size(), 7);
		assertEquals(graph.getWeights().size(), 7);
		assertEquals(graph.getVertices().get(6).getValue().getAddress(), "Calle 8B # 2-18");
	}
        
        @Test
	public void testRemoveVertex1() throws SimpleGraphException {
		setupScenary2();
		((GraphAL<Building>) graph).removeVertex(graph.getVertices().get(4));
		assertEquals(graph.getWeights().size(), 5);
		assertEquals(((GraphAL<Building>) graph).getAdjList().size(), 5);
	}

	@Test
	public void testRemoveVertex2() throws SimpleGraphException {
		setupScenary4();
		((GraphAL<Building>) graph).removeVertex(graph.getVertices().get(2));
		assertEquals(graph.getWeights().size(), 5);
		assertEquals(((GraphAL<Building>) graph).getAdjList().size(), 5);
	}

	@Test
	public void testRemoveVertex3() throws SimpleGraphException{
		setupScenary6();
		((GraphAM<Building>) graph).removeVertex(graph.getVertices().get(0));
		assertEquals(graph.getWeights().size(), 5);
		assertEquals(((GraphAM<Building>) graph).getAdjMatrix().size(), 5);
	}

	@Test
	public void testRemoveVertex4() throws SimpleGraphException{
		setupScenary8();
		((GraphAM<Building>) graph).removeVertex(graph.getVertices().get(3));
		assertEquals(graph.getWeights().size(), 5);
		assertEquals(((GraphAM<Building>) graph).getAdjMatrix().size(), 5);
	}

	@Test
	public void testDijkstra1() {
		setupScenary1();
		Building objBuilding = new Building("Calle 7A # 4-15", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 205000000.0, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 ba�os, garaje y patio trasero.");
		Vertex<Building> vertex = new Vertex<>(objBuilding);
		graph.dijkstra(vertex);
		assertTrue(graph.getVertices().isEmpty());
		assertTrue(((GraphAL<Building>)graph).getAdjList().isEmpty());
	}

	@Test
	public void testDijkstra2() throws SimpleGraphException {
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
		Building objBuilding = new Building("Calle 7A # 4-15", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 205000000.0, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 ba�os, garaje y patio trasero.");
		Vertex<Building> vertex = new Vertex<>(objBuilding);
		graph.dijkstra(vertex);
		assertTrue(graph.getVertices().isEmpty());
		assertTrue(((GraphAM<Building>)graph).getAdjMatrix().isEmpty());
	}

	@Test
	public void testDijkstra4() throws SimpleGraphException {
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
	public void testBfs1() throws SimpleGraphException {
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
	public void testBfs2() throws SimpleGraphException {
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
		assertEquals(graph.getVertices().get(4).getDistance(),2);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 3A # 5-29");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),3);
		assertEquals(graph.getVertices().get(5).getPredecesor().getValue().getAddress(),"Calle 7A # 4-15");
	}

	@Test
	public void testBfs3() throws SimpleGraphException {
		setupScenary4();
		graph.bfs(graph.getVertices().get(5));
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 4C # 5-29" );
		assertEquals(graph.getVertices().get(0).getDistance(),3);
		assertEquals(graph.getVertices().get(0).getPredecesor().getValue().getAddress(), "Calle 5B # 3-10");
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
		assertEquals(graph.getVertices().get(4).getDistance(),1);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),0);
		assertEquals(graph.getVertices().get(5).getPredecesor(),null);
	}

	@Test
	public void testBfs4() throws SimpleGraphException {
		setupScenary8();
		graph.bfs(graph.getVertices().get(5));
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 4C # 5-29" );
		assertEquals(graph.getVertices().get(0).getDistance(),3);
		assertEquals(graph.getVertices().get(0).getPredecesor().getValue().getAddress(), "Calle 5B # 3-10");
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
		assertEquals(graph.getVertices().get(4).getDistance(),1);
		assertEquals(graph.getVertices().get(4).getPredecesor().getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getValue().getAddress(),"Calle 11B # 2-16");
		assertEquals(graph.getVertices().get(5).getDistance(),0);
		assertEquals(graph.getVertices().get(5).getPredecesor(),null);
	}

	@Test
	public void testsearchVertex1() {
		setupScenary1();
		Building b = new Building("Calle 7A # 4-15", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 205000000, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 ba�os, garaje y patio trasero.");
		assertEquals(graph.searchVertex(b), null);
	}

	@Test
	public void testsearchVertex2() throws SimpleGraphException {
		setupScenary2();
		Vertex<Building> v = graph.searchVertex(graph.getVertices().get(2).getValue());
		assertEquals(v.getValue().getAddress(), "Calle 7A # 4-15");
		assertEquals(v.getValue().getNeighborhood(), "Vipasa");
		assertEquals(v.getValue().getZone(), Zone.NORTE);
		assertEquals(v.getValue().getType(), TypeOfBuilding.CASA);
		assertEquals(v.getValue().getPrice(), 205000000.0);
		assertTrue(v.getValue().isForSale());
		assertEquals(v.getValue().getObservations(), "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 banios, garaje y patio trasero.");
	}






















	@Test
	public void testAddEdge1() throws SimpleGraphException {
		setupScenary2();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(4);
		int weight=12;
		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			fail("SimpleGraphException not expected");
		}

		assertTrue(graph.getEdges().get(9).getScr()==u);
		assertTrue(graph.getEdges().get(9).getDest()==v);
		assertTrue(graph.getEdges().get(9).getWeight()==weight);

		assertTrue(((GraphAL<Building>) graph).getAdjList().get(1).get(3)==v);
		assertTrue(((GraphAL<Building>) graph).getAdjList().get(4).get(3)==u);

		assertTrue(graph.getWeights().get(1).get(3)==weight);
		assertTrue(graph.getWeights().get(4).get(3)==weight);


	}

	@Test
	public void testAddEdge2() throws SimpleGraphException {
		setupScenary2();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(3);
		int weight=3;

		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			assertTrue(graph.getEdges().size()==9);

			assertTrue(((GraphAL<Building>) graph).getAdjList().get(1).get(2)==v);
			assertTrue(((GraphAL<Building>) graph).getAdjList().get(3).get(3)==u);

			assertTrue(graph.getWeights().get(1).get(2)!=weight);
			assertTrue(graph.getWeights().get(3).get(3)!=weight);

		}


	}

	@Test
	public void testAddEdge3() throws SimpleGraphException {
		setupScenary6();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(4);
		int weight=12;
		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			fail("SimpleGraphException not expected");
		}

		assertTrue(graph.getEdges().get(9).getScr()==u);
		assertTrue(graph.getEdges().get(9).getDest()==v);
		assertTrue(graph.getEdges().get(9).getWeight()==weight);

		assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(1).get(4)==v);
		assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(4).get(1)==u);

		assertTrue(graph.getWeights().get(1).get(4)==weight);
		assertTrue(graph.getWeights().get(4).get(1)==weight);


	}

	@Test
	public void testAddEdge4() throws SimpleGraphException {
		setupScenary6();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(3);
		int weight=3;

		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			assertTrue(graph.getEdges().size()==9);

			assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(1).get(3)==v);
			assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(3).get(1)==u);

			assertTrue(graph.getWeights().get(1).get(3)!=weight);
			assertTrue(graph.getWeights().get(3).get(1)!=weight);

		}
	}

	@Test
	public void testAddEdge5() throws SimpleGraphException {
		setupScenary4();
		Vertex<Building> u= graph.getVertices().get(4);
		Vertex<Building> v= graph.getVertices().get(1);
		int weight=12;
		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			fail("SimpleGraphException not expected");
		}

		assertTrue(graph.getEdges().get(9).getScr()==u);
		assertTrue(graph.getEdges().get(9).getDest()==v);
		assertTrue(graph.getEdges().get(9).getWeight()==weight);

		assertTrue(((GraphAL<Building>) graph).getAdjList().get(4).get(0)==v);

		assertTrue(graph.getWeights().get(4).get(0)==weight);
		assertTrue(graph.getWeights().get(1).get(1)!=weight);


	}

	@Test
	public void testAddEdge6() throws SimpleGraphException {
		setupScenary2();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(4);
		int weight=5;

		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			assertTrue(graph.getEdges().size()==9);

			assertTrue(((GraphAL<Building>) graph).getAdjList().get(1).get(1)==v);

			assertTrue(graph.getWeights().get(1).get(1)!=weight);
			assertTrue(((GraphAL<Building>) graph).getAdjList().get(4).isEmpty());

		}


	}

	@Test
	public void testAddEdge7() throws SimpleGraphException {
		setupScenary8();
		Vertex<Building> u= graph.getVertices().get(4);
		Vertex<Building> v= graph.getVertices().get(1);
		int weight=12;
		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			fail("SimpleGraphException not expected");
		}

		assertTrue(graph.getEdges().get(9).getScr()==u);
		assertTrue(graph.getEdges().get(9).getDest()==v);
		assertTrue(graph.getEdges().get(9).getWeight()==weight);

		assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(4).get(1)==v);

		assertTrue(graph.getWeights().get(1).get(4)!=weight);
		assertTrue(graph.getWeights().get(4).get(1)==weight);


	}
	@Test
	public void testAddEdge8() throws SimpleGraphException {
		setupScenary8();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(4);
		int weight=5;

		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			assertTrue(graph.getEdges().size()==9);

			assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(1).get(4)==v);
			assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(4).get(1).getValue()==null);

			assertTrue(graph.getWeights().get(1).get(4)!=weight);

		}
	}
        
        //REVISAR CON LAS CHICAS
        @Test
        public void testRemoveEdge1() throws SimpleGraphException{
            setupScenary2();
            Vertex<Building> u= graph.getVertices().get(5);
            Vertex<Building> v= graph.getVertices().get(2);
            
            graph.removeEdge(u,v);
            
            assertEquals(((GraphAL<Building>) graph).getAdjList().get(5).size(), 1);
            assertEquals(((GraphAL<Building>) graph).getAdjList().get(2).size(), 3);
            
            assertEquals(graph.getEdges().size(), 8);
        }
        
        @Test
        public void testRemoveEdge2() throws SimpleGraphException{
            setupScenary4();
            Vertex<Building> u= graph.getVertices().get(2);
            Vertex<Building> v= graph.getVertices().get(4);
            
            graph.removeEdge(u,v);
            
            assertEquals(((GraphAL<Building>) graph).getAdjList().get(2).size(), 1);
            assertEquals(((GraphAL<Building>) graph).getAdjList().get(4).size(), 0);
            
            assertEquals(graph.getEdges().size(), 8);
        }
        
        @Test
        public void testRemoveEdge3() throws SimpleGraphException{
            setupScenary6();
            Vertex<Building> u= graph.getVertices().get(0);
            Vertex<Building> v= graph.getVertices().get(3);
            
            graph.removeEdge(u,v);
            
            assertEquals(((GraphAM<Building>) graph).getAdjMatrix().get(0).size(), 6);
            assertEquals(((GraphAM<Building>) graph).getAdjMatrix().get(3).size(), 6);
            
            assertEquals(graph.getEdges().size(), 8);
        }
        
        @Test
        public void testRemoveEdge4() throws SimpleGraphException{
            setupScenary8();
            Vertex<Building> u= graph.getVertices().get(3);
            Vertex<Building> v= graph.getVertices().get(1);
            
            graph.removeEdge(u,v);
            
            assertEquals(((GraphAM<Building>) graph).getAdjMatrix().get(3).size(), 6);
            assertEquals(((GraphAM<Building>) graph).getAdjMatrix().get(1).size(), 6);
            
            assertEquals(graph.getEdges().size(), 8);
        }

	@Test
	public void testDFS1() throws SimpleGraphException {
		setupScenary2();
		graph.dfs();

		assertTrue(graph.getVertices().get(0).getiDistance()==1);
		assertTrue(graph.getVertices().get(0).getfDistance()==12);
		assertTrue(graph.getVertices().get(0).getPredecesor()==null);
		assertTrue(graph.getVertices().get(0).getColor()=='B');

		assertTrue(graph.getVertices().get(1).getiDistance()==2);
		assertTrue(graph.getVertices().get(1).getfDistance()==11);
		assertTrue(graph.getVertices().get(1).getPredecesor()==graph.getVertices().get(0));
		assertTrue(graph.getVertices().get(1).getColor()=='B');


		assertTrue(graph.getVertices().get(2).getiDistance()==3);
		assertTrue(graph.getVertices().get(2).getfDistance()==10);
		assertTrue(graph.getVertices().get(2).getPredecesor()==graph.getVertices().get(1));
		assertTrue(graph.getVertices().get(2).getColor()=='B');


		assertTrue(graph.getVertices().get(3).getiDistance()==4);
		assertTrue(graph.getVertices().get(3).getfDistance()==9);
		assertTrue(graph.getVertices().get(3).getPredecesor()==graph.getVertices().get(2));
		assertTrue(graph.getVertices().get(3).getColor()=='B');


		assertTrue(graph.getVertices().get(4).getiDistance()==5);
		assertTrue(graph.getVertices().get(4).getfDistance()==8);
		assertTrue(graph.getVertices().get(4).getPredecesor()==graph.getVertices().get(3));
		assertTrue(graph.getVertices().get(4).getColor()=='B');


		assertTrue(graph.getVertices().get(5).getiDistance()==6);
		assertTrue(graph.getVertices().get(5).getfDistance()==7);
		assertTrue(graph.getVertices().get(5).getPredecesor()==graph.getVertices().get(4));
		assertTrue(graph.getVertices().get(5).getColor()=='B');


	}

	@Test
	public void testDFS2() throws SimpleGraphException {
		setupScenary6();
		graph.dfs();

		assertTrue(graph.getVertices().get(0).getiDistance()==1);
		assertTrue(graph.getVertices().get(0).getfDistance()==12);
		assertTrue(graph.getVertices().get(0).getPredecesor()==null);
		assertTrue(graph.getVertices().get(0).getColor()=='B');

		assertTrue(graph.getVertices().get(1).getiDistance()==2);
		assertTrue(graph.getVertices().get(1).getfDistance()==11);
		assertTrue(graph.getVertices().get(1).getPredecesor()==graph.getVertices().get(0));
		assertTrue(graph.getVertices().get(1).getColor()=='B');


		assertTrue(graph.getVertices().get(2).getiDistance()==3);
		assertTrue(graph.getVertices().get(2).getfDistance()==10);
		assertTrue(graph.getVertices().get(2).getPredecesor()==graph.getVertices().get(1));
		assertTrue(graph.getVertices().get(2).getColor()=='B');


		assertTrue(graph.getVertices().get(3).getiDistance()==4);
		assertTrue(graph.getVertices().get(3).getfDistance()==9);
		assertTrue(graph.getVertices().get(3).getPredecesor()==graph.getVertices().get(2));
		assertTrue(graph.getVertices().get(3).getColor()=='B');


		assertTrue(graph.getVertices().get(4).getiDistance()==5);
		assertTrue(graph.getVertices().get(4).getfDistance()==8);
		assertTrue(graph.getVertices().get(4).getPredecesor()==graph.getVertices().get(3));
		assertTrue(graph.getVertices().get(4).getColor()=='B');


		assertTrue(graph.getVertices().get(5).getiDistance()==6);
		assertTrue(graph.getVertices().get(5).getfDistance()==7);
		assertTrue(graph.getVertices().get(5).getPredecesor()==graph.getVertices().get(4));
		assertTrue(graph.getVertices().get(5).getColor()=='B');


	}

	@Test
	public void testDFS3() throws SimpleGraphException {
		setupScenary4();
		graph.dfs();

		assertTrue(graph.getVertices().get(0).getiDistance()==1);
		assertTrue(graph.getVertices().get(0).getfDistance()==8);
		assertTrue(graph.getVertices().get(0).getPredecesor()==null);
		assertTrue(graph.getVertices().get(0).getColor()=='B');

		assertTrue(graph.getVertices().get(1).getiDistance()==3);
		assertTrue(graph.getVertices().get(1).getfDistance()==6);
		assertTrue(graph.getVertices().get(1).getPredecesor()==graph.getVertices().get(3));
		assertTrue(graph.getVertices().get(1).getColor()=='B');


		assertTrue(graph.getVertices().get(2).getiDistance()==9);
		assertTrue(graph.getVertices().get(2).getfDistance()==10);
		assertTrue(graph.getVertices().get(2).getPredecesor()==null);
		assertTrue(graph.getVertices().get(2).getColor()=='B');


		assertTrue(graph.getVertices().get(3).getiDistance()==2);
		assertTrue(graph.getVertices().get(3).getfDistance()==7);
		assertTrue(graph.getVertices().get(3).getPredecesor()==graph.getVertices().get(0));
		assertTrue(graph.getVertices().get(3).getColor()=='B');


		assertTrue(graph.getVertices().get(4).getiDistance()==4);
		assertTrue(graph.getVertices().get(4).getfDistance()==5);
		assertTrue(graph.getVertices().get(4).getPredecesor()==graph.getVertices().get(1));
		assertTrue(graph.getVertices().get(4).getColor()=='B');


		assertTrue(graph.getVertices().get(5).getiDistance()==11);
		assertTrue(graph.getVertices().get(5).getfDistance()==12);
		assertTrue(graph.getVertices().get(5).getPredecesor()==null);
		assertTrue(graph.getVertices().get(5).getColor()=='B');
	}

	@Test
	public void testDFS4() throws SimpleGraphException {
		setupScenary8();
		graph.dfs();

		assertTrue(graph.getVertices().get(0).getiDistance()==1);
		assertTrue(graph.getVertices().get(0).getfDistance()==8);
		assertTrue(graph.getVertices().get(0).getPredecesor()==null);
		assertTrue(graph.getVertices().get(0).getColor()=='B');

		assertTrue(graph.getVertices().get(1).getiDistance()==3);
		assertTrue(graph.getVertices().get(1).getfDistance()==6);
		assertTrue(graph.getVertices().get(1).getPredecesor()==graph.getVertices().get(3));
		assertTrue(graph.getVertices().get(1).getColor()=='B');


		assertTrue(graph.getVertices().get(2).getiDistance()==9);
		assertTrue(graph.getVertices().get(2).getfDistance()==10);
		assertTrue(graph.getVertices().get(2).getPredecesor()==null);
		assertTrue(graph.getVertices().get(2).getColor()=='B');


		assertTrue(graph.getVertices().get(3).getiDistance()==2);
		assertTrue(graph.getVertices().get(3).getfDistance()==7);
		assertTrue(graph.getVertices().get(3).getPredecesor()==graph.getVertices().get(0));
		assertTrue(graph.getVertices().get(3).getColor()=='B');


		assertTrue(graph.getVertices().get(4).getiDistance()==4);
		assertTrue(graph.getVertices().get(4).getfDistance()==5);
		assertTrue(graph.getVertices().get(4).getPredecesor()==graph.getVertices().get(1));
		assertTrue(graph.getVertices().get(4).getColor()=='B');


		assertTrue(graph.getVertices().get(5).getiDistance()==11);
		assertTrue(graph.getVertices().get(5).getfDistance()==12);
		assertTrue(graph.getVertices().get(5).getPredecesor()==null);
		assertTrue(graph.getVertices().get(5).getColor()=='B');
	}

	@Test
	public void testPrim1() throws SimpleGraphException {
		setupScenary2();
		Vertex<Building> r=graph.getVertices().get(0);

		graph.prim(r);

		assertTrue(graph.getVertices().get(0).getDistance()==0);
		assertTrue(graph.getVertices().get(0).getPredecesor()==null);
		assertTrue(graph.getVertices().get(0).getColor()=='B');

		assertTrue(graph.getVertices().get(1).getDistance()==1);
		assertTrue(graph.getVertices().get(1).getPredecesor()==graph.getVertices().get(3));
		assertTrue(graph.getVertices().get(1).getColor()=='B');


		assertTrue(graph.getVertices().get(2).getDistance()==5);
		assertTrue(graph.getVertices().get(2).getPredecesor()==graph.getVertices().get(1));
		assertTrue(graph.getVertices().get(2).getColor()=='B');


		assertTrue(graph.getVertices().get(3).getDistance()==2);
		assertTrue(graph.getVertices().get(3).getPredecesor()==graph.getVertices().get(0));
		assertTrue(graph.getVertices().get(3).getColor()=='B');


		assertTrue(graph.getVertices().get(4).getDistance()==2);
		assertTrue(graph.getVertices().get(4).getPredecesor()==graph.getVertices().get(2));
		assertTrue(graph.getVertices().get(4).getColor()=='B');


		assertTrue(graph.getVertices().get(5).getDistance()==3);
		assertTrue(graph.getVertices().get(5).getPredecesor()==graph.getVertices().get(4));
		assertTrue(graph.getVertices().get(5).getColor()=='B');


	}

	@Test
	public void testPrim2() throws SimpleGraphException {
		setupScenary6();
		Vertex<Building> r=graph.getVertices().get(0);

		graph.prim(r);

		assertTrue(graph.getVertices().get(0).getDistance()==0);
		assertTrue(graph.getVertices().get(0).getPredecesor()==null);
		assertTrue(graph.getVertices().get(0).getColor()=='B');

		assertTrue(graph.getVertices().get(1).getDistance()==1);
		assertTrue(graph.getVertices().get(1).getPredecesor()==graph.getVertices().get(3));
		assertTrue(graph.getVertices().get(1).getColor()=='B');


		assertTrue(graph.getVertices().get(2).getDistance()==5);
		assertTrue(graph.getVertices().get(2).getPredecesor()==graph.getVertices().get(1));
		assertTrue(graph.getVertices().get(2).getColor()=='B');


		assertTrue(graph.getVertices().get(3).getDistance()==2);
		assertTrue(graph.getVertices().get(3).getPredecesor()==graph.getVertices().get(0));
		assertTrue(graph.getVertices().get(3).getColor()=='B');


		assertTrue(graph.getVertices().get(4).getDistance()==2);
		assertTrue(graph.getVertices().get(4).getPredecesor()==graph.getVertices().get(2));
		assertTrue(graph.getVertices().get(4).getColor()=='B');


		assertTrue(graph.getVertices().get(5).getDistance()==3);
		assertTrue(graph.getVertices().get(5).getPredecesor()==graph.getVertices().get(4));
		assertTrue(graph.getVertices().get(5).getColor()=='B');


	}

	@Test
	public void testKruskal1() throws SimpleGraphException {
		setupScenary2();

		graph.kruskal();

		assertTrue(graph.getListEdges().get(0).getScr()==graph.getVertices().get(1));
		assertTrue(graph.getListEdges().get(0).getDest()==graph.getVertices().get(3));
		assertTrue(graph.getListEdges().get(0).getWeight()==1);

		assertTrue(graph.getListEdges().get(1).getScr()==graph.getVertices().get(0));
		assertTrue(graph.getListEdges().get(1).getDest()==graph.getVertices().get(3));
		assertTrue(graph.getListEdges().get(1).getWeight()==2);

		assertTrue(graph.getListEdges().get(2).getScr()==graph.getVertices().get(2));
		assertTrue(graph.getListEdges().get(2).getDest()==graph.getVertices().get(4));
		assertTrue(graph.getListEdges().get(2).getWeight()==2);

		assertTrue(graph.getListEdges().get(3).getScr()==graph.getVertices().get(4));
		assertTrue(graph.getListEdges().get(3).getDest()==graph.getVertices().get(5));
		assertTrue(graph.getListEdges().get(3).getWeight()==3);

		assertTrue(graph.getListEdges().get(4).getScr()==graph.getVertices().get(1));
		assertTrue(graph.getListEdges().get(4).getDest()==graph.getVertices().get(2));
		assertTrue(graph.getListEdges().get(4).getWeight()==5);


	}

	@Test
	public void testKruskal2() throws SimpleGraphException {
		setupScenary6();

		graph.kruskal();

		assertTrue(graph.getListEdges().get(0).getScr()==graph.getVertices().get(1));
		assertTrue(graph.getListEdges().get(0).getDest()==graph.getVertices().get(3));
		assertTrue(graph.getListEdges().get(0).getWeight()==1);

		assertTrue(graph.getListEdges().get(1).getScr()==graph.getVertices().get(0));
		assertTrue(graph.getListEdges().get(1).getDest()==graph.getVertices().get(3));
		assertTrue(graph.getListEdges().get(1).getWeight()==2);

		assertTrue(graph.getListEdges().get(2).getScr()==graph.getVertices().get(2));
		assertTrue(graph.getListEdges().get(2).getDest()==graph.getVertices().get(4));
		assertTrue(graph.getListEdges().get(2).getWeight()==2);

		assertTrue(graph.getListEdges().get(3).getScr()==graph.getVertices().get(4));
		assertTrue(graph.getListEdges().get(3).getDest()==graph.getVertices().get(5));
		assertTrue(graph.getListEdges().get(3).getWeight()==3);

		assertTrue(graph.getListEdges().get(4).getScr()==graph.getVertices().get(1));
		assertTrue(graph.getListEdges().get(4).getDest()==graph.getVertices().get(2));
		assertTrue(graph.getListEdges().get(4).getWeight()==5);


	}


	@Test
	public void testSearchEdge1() throws SimpleGraphException {
		setupScenary2();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(3);

		Edge<Building> edge= graph.searchEdge(u, v);

		assertTrue(edge.getScr()==u);
		assertTrue(edge.getDest()==v);
		assertTrue(edge.getWeight()==1);

	}

	@Test
	public void testSearchEdge2() throws SimpleGraphException {
		setupScenary4();
		Vertex<Building> u= graph.getVertices().get(3);
		Vertex<Building> v= graph.getVertices().get(1);

		Edge<Building> edge= graph.searchEdge(u, v);

		assertTrue(edge.getScr()==u);
		assertTrue(edge.getDest()==v);
		assertTrue(edge.getWeight()==4);

	}

	@Test
	public void testSearchEdge3() throws SimpleGraphException {
		setupScenary6();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(3);

		Edge<Building> edge= graph.searchEdge(u, v);

		assertTrue(edge.getScr()==u);
		assertTrue(edge.getDest()==v);
		assertTrue(edge.getWeight()==1);

	}


	@Test
	public void testSearchEdge4() throws SimpleGraphException {
		setupScenary8();
		Vertex<Building> u= graph.getVertices().get(3);
		Vertex<Building> v= graph.getVertices().get(1);

		Edge<Building> edge= graph.searchEdge(u, v);

		assertTrue(edge.getScr()==u);
		assertTrue(edge.getDest()==v);
		assertTrue(edge.getWeight()==4);

	}

	@Test
	public void testSearchEdge5() throws SimpleGraphException {
		setupScenary2();
		Vertex<Building> u= graph.getVertices().get(4);
		Vertex<Building> v= graph.getVertices().get(1);

		Edge<Building> edge= graph.searchEdge(u, v);

		assertTrue(edge==null);

	}

	@Test
	public void testSearchEdge6() throws SimpleGraphException {
		setupScenary6();
		Vertex<Building> u= graph.getVertices().get(4);
		Vertex<Building> v= graph.getVertices().get(1);

		Edge<Building> edge= graph.searchEdge(u, v);

		assertTrue(edge==null);

	}

	@Test
	public void testSearchEdge7() throws SimpleGraphException {
		setupScenary4();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(3);

		Edge<Building> edge= graph.searchEdge(u, v);

		assertTrue(edge==null);

	}

	@Test
	public void testSearchEdge8() throws SimpleGraphException {
		setupScenary8();
		Vertex<Building> u= graph.getVertices().get(1);
		Vertex<Building> v= graph.getVertices().get(3);

		Edge<Building> edge= graph.searchEdge(u, v);

		assertTrue(edge==null);

	}
        
	@Test
	public void testFloydWarshall1() throws SimpleGraphException{
		setupScenary2();
		int[][] dist= graph.floydWarshall();
		
		String fw= "";
		
		for(int i=0;i<graph.getVertices().size();i++) {
			for(int j=0;j<graph.getVertices().size();j++) {
				fw+=dist[i][j]+" ";
			}
			fw+="\n";
		}
		
		String expected=
				  "0 3 8 2 10 13 \n"
				+ "3 0 5 1 7 10 \n"
				+ "8 5 0 6 2 5 \n"
				+ "2 1 6 0 8 11 \n"
				+ "10 7 2 8 0 3 \n"
				+ "13 10 5 11 3 0 \n";
		
		assertEquals(fw,expected);
		
	}
	
	@Test
	public void testFloydWarshall2() throws SimpleGraphException{
		setupScenary4();
		int[][] dist= graph.floydWarshall();
		
		String fw= "";
		
		for(int i=0;i<graph.getVertices().size();i++) {
			for(int j=0;j<graph.getVertices().size();j++) {
				fw+=dist[i][j]+" ";
			}
			fw+="\n";
		}
		int infinity=Integer.MAX_VALUE/2;
		String expected=
				  "0 6 "+infinity+" 2 10 "+infinity+" \n"
				+ "2 0 "+infinity+" 4 6 "+infinity+" \n"
				+ "4 2 0 6 3 "+infinity+" \n"
				+ "6 4 "+infinity+" 0 8 "+infinity+" \n"
				+ ""+infinity+" "+infinity+" "+infinity+" "+infinity+" 0 "+infinity+" \n"
				+ "14 12 10 16 5 0 \n";
		
		assertEquals(fw,expected);
		
	}
	
	@Test
	public void testFloydWarshall3() throws SimpleGraphException{
		setupScenary6();
		int[][] dist= graph.floydWarshall();
		
		String fw= "";
		
		for(int i=0;i<graph.getVertices().size();i++) {
			for(int j=0;j<graph.getVertices().size();j++) {
				fw+=dist[i][j]+" ";
			}
			fw+="\n";
		}
		
		String expected=
				  "0 3 8 2 10 13 \n"
				+ "3 0 5 1 7 10 \n"
				+ "8 5 0 6 2 5 \n"
				+ "2 1 6 0 8 11 \n"
				+ "10 7 2 8 0 3 \n"
				+ "13 10 5 11 3 0 \n";
		
		assertEquals(fw,expected);
		
	}
	
	@Test
	public void testFloydWarshall4() throws SimpleGraphException{
		setupScenary8();
		int[][] dist= graph.floydWarshall();
		
		String fw= "";
		
		for(int i=0;i<graph.getVertices().size();i++) {
			for(int j=0;j<graph.getVertices().size();j++) {
				fw+=dist[i][j]+" ";
			}
			fw+="\n";
		}
		int infinity=Integer.MAX_VALUE/2;
		String expected=
				  "0 6 "+infinity+" 2 10 "+infinity+" \n"
				+ "2 0 "+infinity+" 4 6 "+infinity+" \n"
				+ "4 2 0 6 3 "+infinity+" \n"
				+ "6 4 "+infinity+" 0 8 "+infinity+" \n"
				+ ""+infinity+" "+infinity+" "+infinity+" "+infinity+" 0 "+infinity+" \n"
				+ "14 12 10 16 5 0 \n";
		
		assertEquals(fw,expected);
		
	}
}
