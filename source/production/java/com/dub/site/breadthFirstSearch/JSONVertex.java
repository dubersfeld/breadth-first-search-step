package com.dub.site.breadthFirstSearch;

import java.util.List;

import com.dub.site.breadthFirstSearch.BFSVertex.Color;

/** POJO represents vertex for AJAX initialization request */
public class JSONVertex {
	

	/**
	 * 
	 */
	private String name;    
	private int parent;
	private Color color = Color.BLACK;
	private int md = 0;
	private int mf = 0;    	
	private List<Integer> edges;// all edges indices from this vertex
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getMd() {
		return md;
	}
	public void setMd(int md) {
		this.md = md;
	}
	public int getMf() {
		return mf;
	}
	public void setMf(int mf) {
		this.mf = mf;
	}
	public List<Integer> getEdges() {
		return edges;
	}
	public void setEdges(List<Integer> edges) {
		this.edges = edges;
	}

}
