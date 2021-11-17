package dataStructures;

public class Vertex<V> {
	private V value;
	private char color; //W=white; B=black; G=gray
	private int distance;
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
	
	
}
