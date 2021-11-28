package dataStructures;

import java.io.Serializable;

public class Edge<V> implements Comparable<Edge<V>>, Serializable {

	private static final long serialVersionUID = 1;
	private Vertex<V> scr;
	private Vertex<V> dest;
	private int weight;
	
	public Edge(Vertex<V> scr, Vertex<V> dest, int weight) {
		this.scr=scr;
		this.dest=dest;
		this.weight=weight;
	}
	
	
	public Vertex<V> getScr() {
		return scr;
	}


	public void setScr(Vertex<V> scr) {
		this.scr = scr;
	}


	public Vertex<V> getDest() {
		return dest;
	}


	public void setDest(Vertex<V> dest) {
		this.dest = dest;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	@Override
	public int compareTo(Edge<V> compareEdge) {
		
		return this.weight-compareEdge.getWeight();
	}
}
