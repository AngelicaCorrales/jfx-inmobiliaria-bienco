package dataStructures;

public interface IGraph<V> {
	
	public void addVertex(V value);
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight);
	public void addEdge(Vertex<V> u, Vertex<V> v);
	public void removeVertex(Vertex<V> u);
	public void removeEdge(Vertex<V> u, Vertex<V> v);
	public void dijkstra(Vertex<V> source);
	public void floydWarshall();
	public void bfs(Vertex<V> source);
	public void dfs();
	public void kruskal();
	public void prim();
	
	
	

}
