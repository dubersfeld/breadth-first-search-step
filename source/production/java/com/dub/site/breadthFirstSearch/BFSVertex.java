package com.dub.site.breadthFirstSearch;


public class BFSVertex extends Vertex {

	/**
	 * Subclass used for BFS 
	 */
	// all additional fields    
	private Integer parent = null;
	private Color color = Color.BLACK;
	private int d = 0;
	
	public BFSVertex(BFSVertex source) {// deep copy c'tor
		super(source);
		this.parent = source.parent;
		this.color = source.color;
		this.d = source.d;
	}
	

	public BFSVertex() {
		super();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
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
