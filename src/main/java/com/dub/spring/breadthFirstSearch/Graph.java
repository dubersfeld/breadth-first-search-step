package com.dub.spring.breadthFirstSearch;

import java.io.Serializable;


/** Graph has Vertices and Adjacency Lists */
public class Graph implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Vertex[] vertices;
	protected int N;
	
	
	public Graph(int N) {
		this.N = N;
		vertices = new Vertex[N];
	}
	
	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	

	public void display() {// used for debugging only
		for (Vertex v : vertices) {
			System.out.println(v.getName());
		}
		System.out.println();
	}
	
}
