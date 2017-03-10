package com.dub.site.breadthFirstSearch;

/** container for AJAX response */
public class StepResult {
	
	private BFSGraph graph;
	private Status status;
	

	
	public BFSGraph getGraph() {
		return graph;
	}

	public void setGraph(BFSGraph graph) {
		this.graph = graph;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}




	enum Status {
		STEP, FINISHED
	}
}
