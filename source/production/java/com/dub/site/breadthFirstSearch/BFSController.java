package com.dub.site.breadthFirstSearch;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dub.config.annotation.WebController;

@WebController
public class BFSController {
	
	@Inject
	private GraphServices graphServices;
	
	/** Initialize graph for both automatic and stepwise search */
	@RequestMapping(value="initGraph")
	@ResponseBody
	public BFSResponse initGraph(@RequestBody GraphInitRequest message, 
				HttpServletRequest request) 
	{	
		List<JSONEdge> jsonEdges = message.getJsonEdges();
		List<JSONVertex> jsonVertices = message.getJsonVertices();
		String sourcename = message.getSourcename();
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("graph") != null) {
			session.removeAttribute("graph");
		}
		
		BFSGraph graph = graphServices.jsonToBFS(jsonEdges, jsonVertices);
			
		session.setAttribute("graph", graph);
			
		BFSResponse bfsResponse = new BFSResponse();
		bfsResponse.setStatus(BFSResponse.Status.OK);
	
		System.out.println("graph constructed");
		
		System.out.println("sourcename: "+ sourcename);
	
		// initialize queue for search loop
		graph.searchInit(sourcename);
	
		// here the graph is ready for the search loop
		
		graph.display();
	
		System.out.println("initGraph completed: " + graph.isInit());
			
		return bfsResponse;
	}

	
	@RequestMapping(value="searchStep")
	@ResponseBody
	public StepResult searchStep(@RequestBody SearchRequest message, 
											HttpServletRequest request) 
	{	
		StepResult bfsResponse = new StepResult();
		
		HttpSession session = request.getSession();
		BFSGraph graph = (BFSGraph)session.getAttribute("graph");
				
		System.out.println("search sator");
		
		// snapshots for display
		graph.searchStep();
		JSONSnapshot snapshot = graphServices.graphToJSON(graph);
				
		bfsResponse.setSnapshot(snapshot);
		
		if (graph.isFinished()) {
			bfsResponse.setStatus(StepResult.Status.FINISHED);// search completed
		} else {
			bfsResponse.setStatus(StepResult.Status.STEP);
		}
		
		return bfsResponse;
	}// searchStep
	
}
