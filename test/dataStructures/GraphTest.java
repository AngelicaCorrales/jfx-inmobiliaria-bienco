package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GraphTest {

	private Graph<Building> graph;
	
	public void setupScenary1() {
		graph= new GraphAL<>(true, false);
	}
	
	public void setupScenary2() {
		graph= new GraphAL<>(true, false);
		
		//BUILDINGS!!
	}
	
	public void setupScenary3() {
		graph= new GraphAL<>(true, true);
	}
	
	public void setupScenary4() {
		graph= new GraphAL<>(true, true);
		
		//BUILDINGS!!
	}
	
	
	public void setupScenary5() {
		graph= new GraphAM<>(true, false);
	}
	
	public void setupScenary6() {
		graph= new GraphAM<>(true, false);
		
		//BUILDINGS!!
	}
	
	public void setupScenary7() {
		graph= new GraphAM<>(true, true);
	}
	
	public void setupScenary8() {
		graph= new GraphAM<>(true, true);
		
		//BUILDINGS!!
	}
	
	
	
	@Test
	public void testAddEdge1() {
		fail("Not yet implemented");
	}

}
