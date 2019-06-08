package com.dub.spring.breadthFirstSearch;

/** POJO represents an edge for AJAX initialization request */
public class JSONEdge {
	
	private int from;// origin
	private int to;// end

	
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
	
	public String toString() {// for debug only
		return from + ", " + to;
	}

}
