package com.dub.site.breadthFirstSearch;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/** for the stepwise implementation the queue has to be a field */

public class BFSGraph extends Graph implements Serializable {
	
	/**
	 * This class subclasses Graph for BFS
	 */
	private static final long serialVersionUID = 1L;

	/** all additional fields for BFS implementation */
	private Queue<Vertex> queue;
	
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

	
	public Queue<Vertex> getQueue() {
		return queue;
	}

	public void setQueue(Queue<Vertex> queue) {
		this.queue = queue;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}


	public void searchInit(String vertexName) {
		Vertex source = null;
		Iterator<Vertex> iter = vertices.iterator();
		while (iter.hasNext()) {
			source = iter.next();
			if (source.getName().equals(vertexName)) {
				queue.push_back(source);
				System.out.println("Start search from: " 
												+ source.getName());
				break;
			}
		}
		if (!iter.hasNext()) {
			throw new RuntimeException("initialization error");
		}
		init = true;
		System.out.println("searchInit completed " + queue.isEmpty());
	}
	
	public StepResult searchStep() {
		
		StepResult result = new StepResult();// empty container
		
		if (queue.isEmpty()) {
			result.setStatus(StepResult.Status.FINISHED);
			return result;
		}
		
		Vertex u = queue.pop_front();
		List<Vertex> adj = u.getAdjacency();
		
		for (Vertex v : adj) {
			if (((BFSVertex)v).getColor().equals(BFSVertex.Color.BLACK)) {
				((BFSVertex)v).setColor(BFSVertex.Color.GREEN);
				((BFSVertex)v).setD(((BFSVertex)u).getD() + 1);
				((BFSVertex)v).setParent((BFSVertex)u);
				queue.push_back(v);
			}
		}
		
		System.out.println(u.getName() + " becomes blue");
		((BFSVertex)u).setColor(BFSVertex.Color.BLUE);
		
		this.display();
		
		/** here we need to return a clone */
		BFSGraph snapshot = new BFSGraph(this);
		result.setGraph(snapshot);
		result.setStatus(StepResult.Status.STEP);
		
		return result;
		
	}// searchStep
	
}
