package dataStructures;

import exceptions.SimpleGraphException;

public interface IGraph<V> {
	
	public void addVertex(V value);
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) throws SimpleGraphException;
	public void addEdge(Vertex<V> u, Vertex<V> v) throws SimpleGraphException;
	public void removeVertex(Vertex<V> u);
	public void removeEdge(Vertex<V> u, Vertex<V> v);
	public Vertex<V> searchVertex(V value);
	public Edge<V> searchEdge(Vertex<V> u, Vertex<V> v);
	public void dijkstra(Vertex<V> source);
	public int[][] floydWarshall();
	public void bfs(Vertex<V> source);
	public void dfs();
	public void kruskal();
	public void prim(Vertex<V> r);
	
	
	

}
