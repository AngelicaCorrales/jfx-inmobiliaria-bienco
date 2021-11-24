package dataStructures;

import java.util.ArrayList;

import exceptions.SimpleGraphException;


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
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) throws SimpleGraphException {

		int index=super.getVertices().indexOf(u);
		if(adjList.get(index).indexOf(v)!=-1) {
			throw new SimpleGraphException();
		}

		adjList.get(index).add(v);
		super.getWeights().get(index).add(weight);

		if(!super.isDirected()) {
			index=super.getVertices().indexOf(v);
			adjList.get(index).add(u);
			super.getWeights().get(index).add(weight);
		}
		super.addEdge(u, v, weight);
	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v) throws SimpleGraphException {


		int index=super.getVertices().indexOf(u);

		if(adjList.get(index).indexOf(v)!=-1) {
			throw new SimpleGraphException();
		}
		adjList.get(index).add(v);

		if(!super.isDirected()) {
			index=super.getVertices().indexOf(v);
			adjList.get(index).add(u);
		}

		super.addEdge(u, v);
	}

	@Override
	public void removeVertex(Vertex<V> u) {
		int index=super.getVertices().indexOf(u);
		adjList.remove(index);

		if(super.isWeighted()) {
			super.getWeights().remove(index);
		}
		super.removeVertex(u);
	}

	@Override
	public void removeEdge(Vertex<V> u, Vertex<V> v) {
		super.removeEdge(u,v);
		int index=super.getVertices().indexOf(u);

		if(adjList.get(index).indexOf(v)!=-1){
			int i=adjList.get(index).indexOf(v);
			adjList.get(index).remove(i);
			super.getWeights().get(index).remove(i);
		}
		if(!super.isDirected()){
			index=super.getVertices().indexOf(v);
			if(adjList.get(index).indexOf(u)!=-1){
				int i=adjList.get(index).indexOf(u);
				adjList.get(index).remove(i);
				super.getWeights().get(index).remove(i);
			}
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
		while(!super.getPQ().isEmpty()) {
			u= super.getPQ().poll();
			int index=super.getVertices().indexOf(u);

			for(int i=0;i<adjList.get(index).size();i++) {
				if(adjList.get(index).get(i).getColor()=='W' && super.getWeights().get(index).get(i)<adjList.get(index).get(i).getDistance()) {
					adjList.get(index).get(i).setDistance(super.getWeights().get(index).get(i));
					super.getPQ().remove(adjList.get(index).get(i));
					super.getPQ().offer(adjList.get(index).get(i));
					adjList.get(index).get(i).setPredecesor(u);

				}
			}
			u.setColor('B');
		}

	}

	
	@Override
	public void dijkstra(Vertex<V> source) {
		super.dijkstra(source);
		while(!super.getPQ().isEmpty()) {
			Vertex<V> u = super.getPQ().poll();
			int index=super.getVertices().indexOf(u);
			for(int k=0;k<adjList.get(index).size();k++) {
				Vertex<V> v = adjList.get(index).get(k);
				int length = (super.getWeights().get(index).get(k));
				int alt = u.getDistance()+length;
				if(alt<v.getDistance()) {
					v.setDistance(alt);
					v.setPredecesor(u);
					super.getPQ().remove(v);
					super.getPQ().add(v);
				}
			}
		}
	}


	@Override
	public void bfs(Vertex<V> source) {
		super.bfs(source);
		while(!super.getQ().isEmpty()) {
			Vertex<V> u = super.getQ().remove();
			int index=super.getVertices().indexOf(u);
			for(int k=0;k<adjList.get(index).size();k++) {
				Vertex<V> v = adjList.get(index).get(k);
				if(v.getColor()=='W') {
					super.getBF().insertNode(v);
					v.setColor('G');
					v.setDistance((u.getDistance())+1);
					v.setPredecesor(u);
					super.getQ().add(v);
				}
				u.setColor('B');
			}
		}
	}


	@Override
	public Edge<V> searchEdge(Vertex<V> u, Vertex<V> v) {
		int iu=super.getVertices().indexOf(u);

		if(adjList.get(iu).indexOf(v)!=-1) {

			return super.searchEdge(u, v);
		}else {
			return null;
		}


	}

	@Override
	public void distWeights(int[][] dist) {
		for(int i=0; i<adjList.size();i++) {
			for(int j=0; j<adjList.get(i).size();j++) {
				int index=super.getVertices().indexOf(adjList.get(i).get(j));
				dist[i][index]=super.getWeights().get(i).get(j);
				
			}
		}

	}
}
