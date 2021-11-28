package dataStructures;

import java.io.Serializable;

public class Vertex<V> implements Comparable<Vertex<V>>, Serializable {

	private static final long serialVersionUID = 1;
	private V value;
	private char color; //W=white; B=black; G=gray
	private int distance;
	private int iDistance;
	private int fDistance;
	
	private Vertex<V> predecesor;
	
	public Vertex(V value) {
		color='W';
		distance=0;
		predecesor=null;
		this.value=value;
		
	}
	
	public char getColor() {
		return color;
	}
	public void setColor(char color) {
		this.color = color;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Vertex<V> getPredecesor() {
		return predecesor;
	}
	public void setPredecesor(Vertex<V> predecesor) {
		this.predecesor = predecesor;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public int compareTo(Vertex<V> compareVertex) {
		
		return this.distance-compareVertex.getDistance();
	}
	
	public int getiDistance() {
		return iDistance;
	}


	public void setiDistance(int iDistance) {
		this.iDistance = iDistance;
	}


	public int getfDistance() {
		return fDistance;
	}


	public void setfDistance(int fDistance) {
		this.fDistance = fDistance;
	}

	
}
