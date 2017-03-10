package com.dub.site.breadthFirstSearch;

import java.io.Serializable;
import java.util.List;

/** for the stepwise implementation the queue has to be a field */

public class BFSGraph extends Graph implements Serializable {
	
	/**
	 * This class subclasses Graph for BFS
	 */
	private static final long serialVersionUID = 1L;

	/** all additional fields for BFS implementation */
	private Queue<Integer> queue;
	
	private boolean init;
	
	int time = 0;
	
	
	public BFSGraph() {
		super();
		queue = new SimpleQueue<>();
		init = false;
	}
	
	public BFSGraph(BFSGraph source) {// deep copy c'tor
		queue = new SimpleQueue<>();
		init = false;
	
		for (Vertex vertex : source.getVertices()) {
			BFSVertex bfsVertex = (BFSVertex)vertex;
			BFSVertex v = new BFSVertex(bfsVertex);// deep copy
			this.getVertices().add(v);
		}
	}


	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}


	public void searchInit(String vertexName) {
		
		int lind = 0;
		
		for (lind = 0; lind < this.vertices.size(); lind++) {
			if (this.vertices.get(lind).getName().equals(vertexName)) {
				queue.push_back(lind);
				System.out.println("Start search from: " 
											+ this.vertices.get(lind).getName());
				break;
			}
		}// for
		
		if (lind == this.vertices.size()) {
			throw new RuntimeException("initialization error");
		}
		
		init = true;
	}
	
	public StepResult searchStep() {
		
		StepResult result = new StepResult();// empty container
		
		if (queue.isEmpty()) {
			result.setStatus(StepResult.Status.FINISHED);
			return result;
		}
		
		Integer index = queue.pop_front();
		List<Integer> adj = this.vertices.get(index).getAdjacency();
		BFSVertex u = (BFSVertex)this.vertices.get(index);
		
		for (Integer vi : adj) {
			BFSVertex v = (BFSVertex)this.vertices.get(vi);
			if (v.getColor().equals(BFSVertex.Color.BLACK)) {
				v.setColor(BFSVertex.Color.GREEN);
				v.setD(((BFSVertex)u).getD() + 1);
				v.setParent(index);
				queue.push_back(vi);
			}
		}
		
		((BFSVertex)u).setColor(BFSVertex.Color.BLUE);
		
		this.display();
		
		/** here we need to return a clone */
		BFSGraph snapshot = new BFSGraph(this);
		result.setGraph(snapshot);
		result.setStatus(StepResult.Status.STEP);
		
		return result;
		
	}// searchStep
	
}
