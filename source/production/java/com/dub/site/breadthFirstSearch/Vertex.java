package com.dub.site.breadthFirstSearch;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** Vertex has an adjacency list of vertices */
public class Vertex {

	/**
	 * 
	 */
	protected String name = "";   
	
	@JsonIgnore
	protected List<Vertex> adjacency;// all adjacent vertices, unused in AJAX response
	
	public Vertex() {
		adjacency = new ArrayList<Vertex>();
	}
	
	public Vertex(Vertex source) {
		this.name = source.name;
		this.adjacency = new ArrayList<Vertex>();
		for (int i = 0; i < source.adjacency.size(); i++) {
			this.adjacency.add(source.adjacency.get(i));
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public List<Vertex> getAdjacency() {
		return adjacency;
	}

	public void setAdjacency(List<Vertex> adjacency) {
		this.adjacency = adjacency;
	}


	/*
	public Vertex clone() {
		Vertex v = new Vertex();
		v.name = name;
		v.parent = parent;
		v.color = color;
		v.d = d;
		v.adjacency = new ArrayList<>();
		v.adjacency.addAll(adjacency);
		return v;
	}
	*/
	

	/*
	public static enum Color {
		BLACK, GREEN, BLUE
	}
	*/

}
