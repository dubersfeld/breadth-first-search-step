package com.dub.site.breadthFirstSearch;


/** POJO represents vertex for AJAX initialization request */
public class JSONVertex {
	
	/**
	 * 
	 */
	private String name;
	private BFSVertex.Color color;
	private int d;
	private Integer parent;
	
	public JSONVertex() {
	}
	
	public JSONVertex(BFSVertex v) {
		this.name = v.getName();
		this.parent = v.getParent(); 
		this.color = v.getColor();
		this.d = v.getD();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public BFSVertex.Color getColor() {
		return color;
	}


	public void setColor(BFSVertex.Color color) {
		this.color = color;
	}


	public int getD() {
		return d;
	}


	public void setD(int d) {
		this.d = d;
	}


	public Integer getParent() {
		return parent;
	}


	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
	
	
}
