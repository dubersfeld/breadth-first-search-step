package com.dub.site.breadthFirstSearch;


/** Vertex has an adjacency list of vertices */
public class BFSVertex extends Vertex {

	/**
	 * 
	 */
	/** all additional fields */   
	private BFSVertex parent = null;
	private Color color = Color.BLACK;
	private int d = 0;
	
	public BFSVertex() {
		super();
	}
	
	public BFSVertex(BFSVertex source) {// deep copy c'tor
		super(source);
		this.parent = source.parent;
		this.color = source.color;
		this.d = source.d;
	}
	
	
	public Vertex getParent() {
		return parent;
	}

	public void setParent(BFSVertex parent) {
		this.parent = parent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	

	public static enum Color {
		BLACK, GREEN, BLUE
	}

}
