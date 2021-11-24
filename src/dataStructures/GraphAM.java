package dataStructures;

import java.util.ArrayList;

import exceptions.SimpleGraphException;

public class GraphAM <V> extends Graph<V> {

	private ArrayList<ArrayList<Vertex<V>>> adjMatrix;


	public GraphAM(boolean isWeighted, boolean isDirected) {
		super(isWeighted, isDirected);

		adjMatrix= new ArrayList<>();


	}


	public ArrayList<ArrayList<Vertex<V>>> getAdjMatrix() {
		return adjMatrix;
	}

	public void setAdjMatrix(ArrayList<ArrayList<Vertex<V>>> adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	@Override
	public void addVertex(V value) {

		super.addVertex(value);
		adjMatrix.add(new ArrayList<>());
		if(super.isWeighted()) {
			super.getWeights().add(new ArrayList<>());
		}


		for(int i=0;i<adjMatrix.size()-1;i++) {

			adjMatrix.get(i).add(new Vertex<>(null));
			if(super.isWeighted()) {
				super.getWeights().get(i).add(Integer.MAX_VALUE);
			}
		}

		for(int i=0;i<adjMatrix.size();i++) {

			adjMatrix.get(adjMatrix.size()-1).add(new Vertex<>(null));
			if(super.isWeighted()) {
				super.getWeights().get(adjMatrix.size()-1).add(Integer.MAX_VALUE);
			}
		}

	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v, int weight) throws SimpleGraphException {



		int i=super.getVertices().indexOf(u);
		int j=super.getVertices().indexOf(v);

		if(adjMatrix.get(i).get(j).getValue()!=null) {
			throw new SimpleGraphException();
		}

		adjMatrix.get(i).set(j, v);

		super.getWeights().get(i).set(j, weight);

		if(!super.isDirected()) {
			adjMatrix.get(j).set(i, u);

			super.getWeights().get(j).set(i, weight);
		}
		super.addEdge(u, v, weight);
	}

	@Override
	public void addEdge(Vertex<V> u, Vertex<V> v) throws SimpleGraphException {
		

		int i=super.getVertices().indexOf(u);
		int j=super.getVertices().indexOf(v);

		if(adjMatrix.get(i).get(j).getValue()!=null) {
			throw new SimpleGraphException();
		}


		adjMatrix.get(i).set(j, v);

		if(!super.isDirected()) {
			adjMatrix.get(j).set(i, u);

		}
		super.addEdge(u, v);
	}

	@Override
	public void removeVertex(Vertex<V> u) {
            int index=super.getVertices().indexOf(u);
            adjMatrix.remove(index);
            
            if(super.isWeighted()) {
                super.getWeights().remove(index);
            }
            
            for(int i=0;i<adjMatrix.size();i++){
                adjMatrix.get(i).remove(index);
                if(super.isWeighted()) {
                    super.getWeights().get(i).remove(index);
                }
            }
            super.removeVertex(u);
	}

	@Override
	public void removeEdge(Vertex<V> u, Vertex<V> v) {
            super.removeEdge(u,v);
            int i=super.getVertices().indexOf(u);
            int j=super.getVertices().indexOf(v);
            
            if(adjMatrix.get(i).get(j).getValue()!=null){
                adjMatrix.get(i).get(j).setValue(null);
                super.getWeights().get(i).set(j,Integer.MAX_VALUE);
            }
            if(!super.isDirected()){
                if(adjMatrix.get(j).get(i).getValue()!=null){
                    adjMatrix.get(j).get(i).setValue(null);
                    super.getWeights().get(j).set(i,Integer.MAX_VALUE);
                }
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
		while(!super.getPQ().isEmpty()) {
			u= super.getPQ().poll();
			int i=super.getVertices().indexOf(u);

			for(int j=0;j<adjMatrix.get(i).size();j++) {
				if(adjMatrix.get(i).get(j).getValue()!=null && adjMatrix.get(i).get(j).getColor()=='W' && super.getWeights().get(i).get(j)<adjMatrix.get(i).get(j).getDistance()) {
					adjMatrix.get(i).get(j).setDistance(super.getWeights().get(i).get(j));
					super.getPQ().remove(adjMatrix.get(i).get(j));
					super.getPQ().offer(adjMatrix.get(i).get(j));
					adjMatrix.get(i).get(j).setPredecesor(u);

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
			int i=super.getVertices().indexOf(u);
			for(int j=0;j<adjMatrix.size();j++) {
				if(adjMatrix.get(i).get(j).getValue()!=null) {
					Vertex<V> v = adjMatrix.get(i).get(j);
					int length = (super.getWeights().get(i).get(j));
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
	}

	@Override
	public void bfs(Vertex<V> source) {
		super.bfs(source);
		while(!super.getQ().isEmpty()) {
			Vertex<V> u = super.getQ().remove();
			int i=super.getVertices().indexOf(u);

			for(int j=0;j<adjMatrix.size();j++) {
				if(adjMatrix.get(i).get(j).getValue()!=null) {
					Vertex<V> v = adjMatrix.get(i).get(j);
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
	}

	
	@Override
	public Edge<V> searchEdge(Vertex<V> u, Vertex<V> v) {
		int iu=super.getVertices().indexOf(u);
		int iv=super.getVertices().indexOf(v);
		
		if(adjMatrix.get(iu).get(iv).getValue()!=null) {

			return super.searchEdge(u, v);
		}else {
			return null;
		}
	}

	@Override
	public void distWeights(int[][] dist) {
		for(int i=0; i<adjMatrix.size();i++) {
			for(int j=0; j<adjMatrix.size();j++) {
				dist[i][j]=super.getWeights().get(i).get(j);
				
			}
		}

	}

}
