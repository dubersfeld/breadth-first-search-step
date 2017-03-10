package com.dub.site.breadthFirstSearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/** Graph has Vertices and Adjacency Lists */
public class Graph implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<Vertex> vertices;
	
	
	public Graph() {
		vertices = new ArrayList<>();
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}
	
	public void display() {// used for debugging only
		for (Vertex v : vertices) {
			System.out.println(v.getName());
		}
		System.out.println();
	}
	
}
