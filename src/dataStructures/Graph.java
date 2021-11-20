package dataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;


public  class Graph<V> implements IGraph<V> {

	private ArrayList<Vertex<V>> vertices;
	private ArrayList<Edge<V>> edges;
	private boolean isWeighted;
	private boolean isDirected;
	private ArrayList<Vertex<V>> prev;
	
	ArrayList<Edge<V>> listEdges;
	
	public Graph(boolean isWeighted, boolean isDirected) {
		 this.isWeighted=isWeighted;
		 this.isDirected=isDirected;
		 vertices= new ArrayList<>();
		 edges= new ArrayList<>();
		
	}
	
	public ArrayList<Vertex<V>> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertex<V>> vertices) {
		this.vertices = vertices;
	}
	
	public ArrayList<Edge<V>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge<V>> edges) {
		this.edges = edges;
	}
	
	public boolean isWeighted() {
		return isWeighted;
	}

	public void setWeighted(boolean isWeighted) {
		this.isWeighted = isWeighted;
	}

	public boolean isDirected() {
		return isDirected;
	}

	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}
	
	

	@Override
	public void addVertex(V value) {
		Vertex<V> vertex = new Vertex<V>(value);
		vertices.add(vertex);
	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) {
		// TODO Auto-generated method stub
		Collections.sort(edges);
	}
	
	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVertex(Vertex<V> u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(Vertex<V> u, Vertex<V> v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dijkstra(Vertex<V> source) {
		if(isWeighted && !isDirected) {
			prev = new ArrayList<Vertex<V>>();
			PriorityQueue<Vertex<V>> Q = new PriorityQueue<Vertex<V>>(vertices.size(), new Comparator<Vertex<V>>() {
				@Override
				public int compare(Vertex<V> v1, Vertex<V> v2) {
					if(v2.getDistance()>v1.getDistance()){
						return -1;
					}else if(v2.getDistance()==v1.getDistance()){
						return 0;
					}else{
						return 1;
					}
				}
			});
			for(int k=0; k<vertices.size();k++) {
				if(vertices.get(k)==source) {
					vertices.get(k).setDistance(0);
				}else {
					vertices.get(k).setDistance(Integer.MAX_VALUE);
				}
				prev.add(new Vertex<V>(null));
				Q.add(vertices.get(k));
			}
		}
		
	}

	@Override
	public void floydWarshall() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bfs(Vertex<V> source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dfs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kruskal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prim() {
		// TODO Auto-generated method stub
		
	}

	

	

	

}
