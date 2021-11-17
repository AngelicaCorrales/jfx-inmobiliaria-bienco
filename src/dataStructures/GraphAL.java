package dataStructures;

import java.util.ArrayList;

public class GraphAL<V> implements IGraph<V> {
	
	private ArrayList<ArrayList<Vertex<V>>> adjList;
	private ArrayList<ArrayList<Integer>> weights;
	private ArrayList<Vertex<V>> vertex;
	
	
	
	public ArrayList<ArrayList<Vertex<V>>> getAdjList() {
		return adjList;
	}

	public void setAdjList(ArrayList<ArrayList<Vertex<V>>> adjList) {
		this.adjList = adjList;
	}
	
	public ArrayList<ArrayList<Integer>> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<ArrayList<Integer>> weights) {
		this.weights = weights;
	}
	
	public ArrayList<Vertex<V>> getVertex() {
		return vertex;
	}

	public void setVertex(ArrayList<Vertex<V>> vertex) {
		this.vertex = vertex;
	}



	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dfs() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void addVertex(V value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dijkstra(Vertex<V> source) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void bfs(Vertex<V> source) {
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
	public void floydWarshall() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	
	
	
	

	

	
	

	
}
