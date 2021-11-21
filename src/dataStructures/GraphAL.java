package dataStructures;

import java.util.ArrayList;


public class GraphAL<V> extends Graph<V> {

	private ArrayList<ArrayList<Vertex<V>>> adjList;


	public GraphAL(boolean isWeighted, boolean isDirected) {
		super(isWeighted, isDirected);

		adjList= new ArrayList<>();

	}


	public ArrayList<ArrayList<Vertex<V>>> getAdjList() {
		return adjList;
	}

	public void setAdjList(ArrayList<ArrayList<Vertex<V>>> adjList) {
		this.adjList = adjList;
	}

	@Override
	public void addVertex(V value) {

		super.addVertex(value);
		adjList.add(new ArrayList<>());
		if(super.isWeighted()) {
			super.getWeights().add(new ArrayList<>());
		}
		
	}
	
	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) {
		super.addEdge(u, v, weight);
		int index=super.getVertices().indexOf(u);

		adjList.get(index).add(v);
		super.getWeights().get(index).add(weight);

		if(!super.isDirected()) {
			index=super.getVertices().indexOf(v);
			adjList.get(index).add(u);
			super.getWeights().get(index).add(weight);
		}
	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v) {
		super.addEdge(u, v);

		int index=super.getVertices().indexOf(u);

		adjList.get(index).add(v);

		if(!super.isDirected()) {
			index=super.getVertices().indexOf(v);
			adjList.get(index).add(u);
		}

	}

	@Override
	public void dfsVisit(Vertex<V> u) {
		super.setTime(super.getTime()+1);
		u.setiDistance(super.getTime());
		u.setColor('G');
		int index=super.getVertices().indexOf(u);

		for(int i=0;i<adjList.get(index).size();i++) {
			if(adjList.get(index).get(i).getColor()=='W') {
				adjList.get(index).get(i).setPredecesor(u);
				dfsVisit(adjList.get(index).get(i));
			}
		}
		u.setColor('B');
		super.setTime(super.getTime()+1);
		u.setfDistance(super.getTime());
		
	}
	@Override
	public void prim(Vertex<V> r) {
		super.prim(r);
		Vertex<V> u= null;
		while(!super.getQ().isEmpty()) {
			u= super.getQ().poll();
			int index=super.getVertices().indexOf(u);

			for(int i=0;i<adjList.get(index).size();i++) {
				if(adjList.get(index).get(i).getColor()=='W' && super.getWeights().get(index).get(i)<adjList.get(index).get(i).getDistance()) {
					adjList.get(index).get(i).setDistance(super.getWeights().get(index).get(i));
					Q.remove(adjList.get(index).get(i));
					adjList.get(index).get(i).setPredecesor(u);
					
				}
			}
			u.setColor('B');
		}
		
	}













}
