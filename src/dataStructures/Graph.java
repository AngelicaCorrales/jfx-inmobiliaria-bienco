package dataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public  class Graph<V> implements IGraph<V> {

	private ArrayList<Vertex<V>> vertices;
	private ArrayList<Edge<V>> edges;
	private ArrayList<ArrayList<Integer>> weights;
	private boolean isWeighted;
	private boolean isDirected;
	private int time;

	private ArrayList<Edge<V>> listEdges;
	private PriorityQueue<Vertex<V>> PQ; 
	private Queue<Vertex<V>> Q;
	
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
	
	public ArrayList<Edge<V>> getListEdges() {
		return listEdges;
	}

	public void setListEdges(ArrayList<Edge<V>> listEdges) {
		this.listEdges = listEdges;
	}

	public PriorityQueue<Vertex<V>> getPQ() {
		return PQ;
	}

	public void setPQ(PriorityQueue<Vertex<V>> q) {
		PQ = q;
	}

	public Queue<Vertex<V>> getQ() {
		return Q;
	}

	public void setQ(Queue<Vertex<V>> q) {
		Q = q;
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

		Vertex<V> vertex = new Vertex<V>(value);
		vertices.add(vertex);

	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) {
		Edge<V> edge= new Edge<>(u,v,weight);
		edges.add(edge);

	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v) {
		Edge<V> edge= new Edge<>(u,v,0);
		edges.add(edge);
	}

	@Override
	public void removeVertex(Vertex<V> u) {
            boolean exit=false;
            for (int i=0;i<vertices.size() && !exit; i++){
                if (vertices.get(i).getPredecesor()==u){
                    vertices.remove(i);
                    exit=true;
                }
            }
	}

	@Override
	public void removeEdge(Vertex<V> u, Vertex<V> v) {
            boolean exit=false;
            for (int i=0;i<edges.size() && !exit; i++){
                if (edges.get(i).getScr()==u && edges.get(i).getDest()==v){
                    if(isWeighted){
                        for(int j=0;j<weights.size() && !exit; j++){
                            edges.remove(i);
                            weights.remove(j);
                            exit=true;
                        }
                    }
                    else{
                        edges.remove(i);
                        exit=true;
                    }
                }
            }

	}

	@Override
	public void dijkstra(Vertex<V> source) {

		if(isWeighted && !isDirected) {
			PQ = new PriorityQueue<Vertex<V>>(vertices.size());
			for(int k=0; k<vertices.size();k++) {
				if(vertices.get(k)==source) {
					vertices.get(k).setDistance(0);
				}else {
					vertices.get(k).setDistance(Integer.MAX_VALUE);
				}
				vertices.get(k).setPredecesor(null);
				PQ.add(vertices.get(k));
			}
		}
		
	}

	@Override
	public void floydWarshall() {
            int size=vertices.size();
            int visitados[][] = new int[size][size];
            int dist[][] = new int[size][size];
            int proximo[][] = new int[size][size];

            for (int i=0;i<size;i++) {
                for (int j=0;j<size;j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                    proximo[i][j] = j;
                    visitados[i][j] = 0;
                }
            }

            for (int i = 0; i < size; i++) {
                dist[i][i] = 0;
            }

            for (int u = 0; u < size; u++) {
                for (int s = 0; s < size; s++) {
                    for (int v = 0; v < size; v++) {
                        if(dist[s][u] + dist[u][v] < dist[s][v] && visitados[s][v] == 0){
                           dist[s][v] = dist[s][u] + dist[u][v];
                           proximo[s][v] = u;
                           visitados[s][v] = 1;
                        }
                    }
                }
            }
	}

	@Override
	public void bfs(Vertex<V> source) {
		Q = new LinkedList<>();
		for(int k=0;k<vertices.size();k++) {
			if(vertices.get(k)==source) {
				vertices.get(k).setColor('G');
				vertices.get(k).setDistance(0);
				vertices.get(k).setPredecesor(null);
			}else {
				vertices.get(k).setColor('W');
				vertices.get(k).setDistance(Integer.MAX_VALUE);
				vertices.get(k).setPredecesor(null);
			}
		}
		Q.add(source);
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
		Collections.sort(edges);
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
	public void prim(Vertex<V> r) {
		for(int i=0;i<vertices.size();i++) {
			vertices.get(i).setColor('W');
			vertices.get(i).setDistance(Integer.MAX_VALUE);
		}
		int index=vertices.indexOf(r);
		
		vertices.get(index).setDistance(0);
		vertices.get(index).setPredecesor(null);
		
		PQ = new PriorityQueue<Vertex<V>>(vertices.size());
		
		
	}







}
