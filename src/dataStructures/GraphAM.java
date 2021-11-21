package dataStructures;

import java.util.ArrayList;

public class GraphAM <V> extends Graph<V> {

	private ArrayList<ArrayList<Vertex<V>>> adjMatrix;


	public GraphAM(boolean isWeighted, boolean isDirected) {
		super(isWeighted, isDirected);

		adjMatrix= new ArrayList<>();

		
	}


	public ArrayList<ArrayList<Vertex<V>>> getadjMatrix() {
		return adjMatrix;
	}

	public void setadjMatrix(ArrayList<ArrayList<Vertex<V>>> adjMatrix) {
		this.adjMatrix = adjMatrix;
	}
	
	@Override
	public void addVertex(V value) {

		super.addVertex(value);
		adjMatrix.add(new ArrayList<>());
		if(super.isWeighted()) {
			super.getWeights().add(new ArrayList<>());
		}
		
		for(int i=0;i<adjMatrix.size();i++) {
			adjMatrix.get(i).add(new Vertex<>(null));
			if(super.isWeighted()) {
				super.getWeights().get(i).add(Integer.MAX_VALUE);
			}
		}
		
	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) {
		super.addEdge(u, v, weight);
		int i=super.getVertices().indexOf(u);
		int j=super.getVertices().indexOf(v);

		adjMatrix.get(i).set(j, v);
		
		super.getWeights().get(i).set(j, weight);

		if(!super.isDirected()) {
			adjMatrix.get(j).set(i, v);
			
			super.getWeights().get(j).set(i, weight);
		}
	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v) {
		super.addEdge(u, v);

		int i=super.getVertices().indexOf(u);
		int j=super.getVertices().indexOf(v);

		adjMatrix.get(i).set(j, v);
		
		if(!super.isDirected()) {
			adjMatrix.get(j).set(i, v);
			
		}

	}

	@Override
	public void dfsVisit(Vertex<V> u) {
		super.setTime(super.getTime()+1);
		u.setiDistance(super.getTime());
		u.setColor('G');
		int i=super.getVertices().indexOf(u);

		for(int j=0;j<adjMatrix.size();j++) {
			if(adjMatrix.get(i).get(j).getValue()!=null && adjMatrix.get(i).get(j).getColor()=='W') {
				adjMatrix.get(i).get(j).setPredecesor(u);
				dfsVisit(adjMatrix.get(i).get(j));
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

			for(int i=0;i<adjMatrix.get(index).size();i++) {
				if(adjMatrix.get(index).get(i).getColor()=='W' && super.getWeights().get(index).get(i)<adjMatrix.get(index).get(i).getDistance()) {
					adjMatrix.get(index).get(i).setDistance(super.getWeights().get(index).get(i));
					Q.remove(adjMatrix.get(index).get(i));
					adjMatrix.get(index).get(i).setPredecesor(u);
					
				}
			}
			u.setColor('B');
		}
		
	}







}
