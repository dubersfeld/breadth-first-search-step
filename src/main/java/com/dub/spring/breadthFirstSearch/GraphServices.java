package com.dub.spring.breadthFirstSearch;


import java.util.List;

import org.springframework.stereotype.Service;

/** service layer used by the main controller */
@Service
public class GraphServices {
	
	public BFSGraph jsonToBFS(
						List<JSONEdge> jsonEdges,
						List<JSONVertex> jsonVertices) {
	
		BFSGraph graph = new BFSGraph(jsonVertices.size());
		
		for (int i = 0; i < jsonVertices.size(); i++) {
			BFSVertex v = new BFSVertex();
			v.setName(jsonVertices.get(i).getName());
			v.setColor(BFSVertex.Color.BLACK);
			graph.getVertices()[i] = v;
		}
		
		for (int i = 0; i < jsonEdges.size(); i++) {
			int from = jsonEdges.get(i).getFrom();
			int to = jsonEdges.get(i).getTo();
			Vertex v1 = graph.getVertices()[from];
			//Vertex v2 = graph.getVertices().get(to);
			v1.getAdjacency().add(to);
		}
		
		return graph;
	}// jsonToDFS
	
	
	public JSONSnapshot graphToJSON(BFSGraph graph) {
		
		System.out.println("graphToJSON begin");
		
		int N = graph.getVertices().length;
		
		JSONSnapshot snapshot = new JSONSnapshot(N); 
			
		for (int i = 0; i < N; i++) {
			snapshot.getVertices()[i] = new JSONVertex((BFSVertex)graph.getVertices()[i]);
		}
		
		
		for (int i = 0; i < N; i++) {// for each vertex
			List<Integer> adjacency = graph.getVertices()[i].getAdjacency();
			int Nadj = adjacency.size();
			JSONAdjacency adj = new JSONAdjacency(Nadj);
			
			for (int k = 0; k < Nadj; k++) {
				adj.getAdjacency()[k] = adjacency.get(k);
			}
			
			snapshot.getAdjacencies()[i] = adj;
			
		}
			
		System.out.println("graphToJSON return");
		
		return snapshot;
	}
	
}