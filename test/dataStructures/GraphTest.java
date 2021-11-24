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
		graph.addVertex(new Building("Calle 4C # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 580000, false, "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero."));
		graph.addVertex(new Building("Calle 5B # 3-10", "Porvenir", Zone.SUR, TypeOfBuilding.LOCAL, 620000, false, "Local de 500 metros cuadrados con segundo piso que contiene un baño"));
		graph.addVertex(new Building("Calle 7A # 4-15", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 205000000, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 baños, garaje y patio trasero."));
		graph.addVertex(new Building("Calle 3A # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.APARTAESTUDIO, 1000000, false, "Apartaestudio amoblado ubicado en segundo piso. Contiene 2 habitaciones, sala, cocina, 1 baño."));
		graph.addVertex(new Building("Calle 9B # 8-19", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 350000000, true, "Casa de 2 plantas que contiene 3 habitaciones, sala, comedor, 2 baños, garaje pequeño y un patio trasero."));
		graph.addVertex(new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 275000000, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, baño,  comedor, sitio para lavado y un pequeño balcón."));

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
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testAddVertex1() {
		setupScenary1();
		Building objBuilding = new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 2750000.0, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, baño,  comedor, sitio para lavado y un pequeño balcón.");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==1);
		assertFalse(((GraphAL) graph).getAdjList().isEmpty());
		assertFalse(graph.getWeights().isEmpty());
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 11B # 2-16");
	}
	
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testAddVertex2() throws SimpleGraphException {
		setupScenary2();
		Building objBuilding = new Building("Calle 8B # 2-18", "Vipasa", Zone.NORTE, TypeOfBuilding.OFICINA, 500000.0, false, "Oficina de 200 metros con baño incluido, buena ubicación en zona comercial, se encuentra en un primer piso");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==7);
		assertEquals(((GraphAL) graph).getAdjList().size(), 7);
		assertEquals(graph.getWeights().size(), 7);
		assertEquals(graph.getVertices().get(6).getValue().getAddress(), "Calle 8B # 2-18");
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testAddVertex3() {
		setupScenary5();
		Building objBuilding = new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 2750000.0, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, baño,  comedor, sitio para lavado y un pequeño balcón.");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==1);
		assertFalse(((GraphAM) graph).getadjMatrix().isEmpty());
		assertFalse(graph.getWeights().isEmpty());
		assertEquals(graph.getVertices().get(0).getValue().getAddress(), "Calle 11B # 2-16");
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testAddVertex4() throws SimpleGraphException {
		setupScenary6();
		Building objBuilding = new Building("Calle 8B # 2-18", "Vipasa", Zone.NORTE, TypeOfBuilding.OFICINA, 500000.0, false, "Oficina de 200 metros con baño incluido, buena ubicación en zona comercial, se encuentra en un primer piso");
		graph.addVertex(objBuilding);
		assertTrue(graph.getVertices().size()==7);
		assertEquals(((GraphAM) graph).getadjMatrix().size(), 7);
		assertEquals(graph.getWeights().size(), 7);
		assertEquals(graph.getVertices().get(6).getValue().getAddress(), "Calle 8B # 2-18");
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
		int weight=3;
		
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
		int weight=3;
		
		try {
			graph.addEdge(u, v, weight);
		} catch (SimpleGraphException e) {
			assertTrue(graph.getEdges().size()==9);
			
			assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(1).get(4)==v);
			assertTrue(((GraphAM<Building>) graph).getAdjMatrix().get(4).get(1).getValue()==null);
			
			assertTrue(graph.getWeights().get(1).get(4)!=weight);
			
		}
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
	
}
