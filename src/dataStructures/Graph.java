package dataStructures;

import java.util.ArrayList;
import java.util.Collections;

public  class Graph<V> implements IGraph<V> {

	private ArrayList<Vertex<V>> vertices;
	private ArrayList<Edge<V>> edges;
	private ArrayList<ArrayList<Integer>> weights;
	private boolean isWeighted;
	private boolean isDirected;
	private int time;
	ArrayList<Edge<V>> listEdges;


	public Graph(boolean isWeighted, boolean isDirected) {
		this.isWeighted=isWeighted;
		this.isDirected=isDirected;
		vertices= new ArrayList<>();
		edges= new ArrayList<>();
		weights= new ArrayList<>();
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



	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public ArrayList<ArrayList<Integer>> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<ArrayList<Integer>> weights) {
		this.weights = weights;
	}

	@Override
	public void addVertex(V value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) {
		Edge<V> edge= new Edge<>(u,v,weight);
		edges.add(edge);

		Collections.sort(edges);
	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v) {
		Edge<V> edge= new Edge<>(u,v,0);
		edges.add(edge);
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
		// TODO Auto-generated method stub

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
		for(int i=0;i<vertices.size();i++) {
			vertices.get(i).setColor('W');
			vertices.get(i).setPredecesor(null);
		}

		time=0;

		for(int i=0;i<vertices.size();i++) {
			if(vertices.get(i).getColor()=='W') {
				dfsVisit(vertices.get(i));
			}

		}

	}

	public void dfsVisit(Vertex<V> u) {
		//Needs adjacency vertices
	}

	@Override
	public void kruskal() {
		listEdges= new ArrayList<>();

		for(int i=0; i<vertices.size();i++) {
			vertices.get(i).setPredecesor(vertices.get(i));
		}

		for(int i=0; i<edges.size();i++) {
			if(find(edges.get(i).getScr())!=find(edges.get(i).getDest())) {
				listEdges.add(edges.get(i));
				union(edges.get(i).getScr(),edges.get(i).getDest());
			}
		}

	}

	public Vertex<V> find(Vertex<V> u) {
		int i=vertices.indexOf(u);
		if(vertices.get(i).getPredecesor()==u) {
			return u;
		}else {
			return find(vertices.get(i).getPredecesor());
		}
	}

	public void union(Vertex<V> x, Vertex<V> y) {
		Vertex<V> repx=find(x);
		Vertex<V> repy=find(y);
		int index=vertices.indexOf(repx);
		vertices.get(index).setPredecesor(repy);

	}

	@Override
	public void prim() {
		// TODO Auto-generated method stub

	}







}
