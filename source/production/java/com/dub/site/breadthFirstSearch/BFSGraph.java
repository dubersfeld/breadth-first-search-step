package com.dub.site.breadthFirstSearch;

import java.util.List;

/** Graph has Vertices and Adjacency Lists */
public class BFSGraph extends Graph {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int index = 0;
	private boolean finished;

	private Queue<Integer> queue;
	
	private boolean init;
	
	int time = 0;
	
	public BFSGraph(int N) {
		super(N);
		queue = new SimpleQueue<>();
		init = false;
	}
	

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public void searchInit(String vertexName) {
		
		int lind = 0;
		
		for (lind = 0; lind < N; lind++) {
			if (this.vertices[lind].getName().equals(vertexName)) {
				queue.push_back(lind);
				System.out.println("Start search from: " 
											+ this.vertices[lind].getName());
				break;
			}
		}// for
		
		if (lind == N) {
			throw new RuntimeException("initialization error");
		}
		
		init = true;
		finished = false;
		System.out.println("init completed");
	}
	
	public void searchStep() {
			
		if (queue.isEmpty()) {
			finished = true;
		} else {
			index = queue.pop_front();
			List<Integer> adj = vertices[index].getAdjacency();
			BFSVertex u = (BFSVertex)vertices[index];
			
			for (Integer vi : adj) {
				BFSVertex v = (BFSVertex)vertices[vi];
				if (v.getColor().equals(BFSVertex.Color.BLACK)) {
					v.setColor(BFSVertex.Color.GREEN);
					v.setD(u.getD() + 1);
					v.setParent(index);
					queue.push_back(vi);// push current adjacency index
				}
			}// for
		
			u.setColor(BFSVertex.Color.BLUE);
		
			//this.display();
		}
	
	}// searchStep
	
	public void display() {// used for debugging only
		for (Vertex v : vertices) {
			System.out.println(((BFSVertex)v).getName() + " " + ((BFSVertex)v).getColor() 
			+ " " + ((BFSVertex)v).getD());
		}
		System.out.println();
	}
	
}
