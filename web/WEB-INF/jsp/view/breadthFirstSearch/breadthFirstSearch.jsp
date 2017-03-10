<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    												pageEncoding="UTF-8" %>
<!doctype html>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Breadth First Search</title>

<link rel="stylesheet"
              href="<c:url value="/resources/stylesheet/bfsDemo.css" />" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>


<script>

"use strict";

var Debugger = function() { };// create  object
	Debugger.log = function(message) {
  	try {
    	console.log(message);
  	} catch(exception) {
    	return;
  	}
}


function canvasSupport() {
  	return !!document.createElement('canvas').getContext;
} 

function canvasApp() {

 	function Vertex(name) {
    	this.mName = name;
    	this.mParent = null;
    	this.mDistance = null;
    	this.mColor = "black";    
  	}// Vertex

  	// Vertex augmentation
  	function DisplayVertex(name) {
    	Vertex.call(this, name);
  	}// DisplayVertex

  	DisplayVertex.prototype = new Vertex();
  	DisplayVertex.prototype.mRadius = 20;
  	DisplayVertex.prototype.xPos = 0;
  	DisplayVertex.prototype.yPos = 0;
  	DisplayVertex.prototype.yConnU = 0;
  	DisplayVertex.prototype.yConnB = 0;
  	DisplayVertex.prototype.xConnL = 0;
  	DisplayVertex.prototype.xConnR = 0;
  	// 4 connection points, bottom, up, left, right
  	DisplayVertex.prototype.mNx = 0;
  	DisplayVertex.prototype.mNy = 0;
 
  	DisplayVertex.prototype.updateGeometry = function() {  
    	this.yConnU = this.yPos - this.mRadius;
    	this.yConnB = this.yPos + this.mRadius;
    	this.xConnL = this.xPos - this.mRadius;
    	this.xConnR = this.xPos + this.mRadius;
  	};

  	function Graph(N) {// A Graph contains a vector of N vertices
    	this.mV = [];// all vertices
    	this.mAdj = [];// all vertices adjacent to a given vertex
    	this.init = function() {
      	for (var i = 0; i < N; i++) {
        	this.mAdj.push(new Array());
      	}
    	}; 
    	// array of arrays of arrays format [[v,v,v],[]...[]]
    	// v = vertex number 
    	this.init();
  	}// Graph


  	// get canvas context
  	if (!canvasSupport()) {
    	alert("canvas not supported");
    	return;
  	} else {
    	var theCanvas = document.getElementById("canvas");
    	var context = theCanvas.getContext("2d");
  	}// if

  	var sourcename;
  
  	var xMin = 0;
  	var yMin = 0;
  	var xMax = theCanvas.width;
  	var yMax = theCanvas.height; 

  	var xPos = [50, 150, 250, 350, 450, 550, 650];
 
  	var yPos = [100, 200, 300, 400, 500];

  	var names = ["a", "b", "c", "d", "e", "f" ,"g"];
 
  	var N = 35;// number of vertices

  	var Nedges = 30;// number of edges

  	var graph = new Graph(N);// empty graph

  	var src = null;// source

  	function setTextStyle() {
    	context.fillStyle    = '#000000';
    	context.font         = '12px _sans';
  	}


  	function fillBackground() {
    	// draw background
    	context.fillStyle = '#ffffff';
    	context.fillRect(xMin, yMin, xMax, yMax);    
  	}

  	function drawVertex(vertex) {
    	context.beginPath();
    	context.strokeStyle = vertex.mColor;
    	context.lineWidth = 2;
    	context.arc(vertex.xPos, vertex.yPos, vertex.mRadius, (Math.PI/180)*0, (Math.PI/180)*360, true); // draw full circle
    	context.stroke();
    	context.closePath();
   
    	var roff = vertex.mRadius + 2;

    	context.fillText(vertex.mName, vertex.xPos, vertex.yPos);
    	if (vertex.mColor != "BLACK" && vertex.mDistance != null) {
      		context.fillText(vertex.mDistance, vertex.xPos + roff, vertex.yPos - roff);
    	}
	}


  	function drawConnect(v1, v2) { 
    	if (v1 == v2.mParent || v2 == v1.mParent) {
      		context.strokeStyle = "blue";
    	} else {
      		context.strokeStyle = "black";
    	}
    	context.lineWidth = 2;
    	context.beginPath();
    	// discuss according to geometry
    	var xa, ya, xb, yb;

    	if (v1.mNx == v2.mNx) {
      		xa = v1.xPos;
      		ya = (v1.mNy < v2.mNy) ? v1.yConnB : v2.yConnB; 
      		xb = v1.xPos;
      		yb = (v1.mNy < v2.mNy) ? v2.yConnU : v1.yConnU;
    	} else if (v1.mNy == v2.mNy) {
      		ya = v1.yPos;
      		xa = (v1.mNx < v2.mNx) ? v1.xConnR : v2.xConnR; 
      		yb = v1.yPos;
      		xb = (v1.mNx < v2.mNx) ? v2.xConnL : v1.xConnL;
    	} else {
      		xa = (v1.mNx < v2.mNx) ? v1.xConnR : v2.xConnR; 
      		ya = (v1.mNx < v2.mNx) ? v1.yPos : v2.yPos;
      		xb = (v1.mNx < v2.mNx) ? v2.xConnL : v1.xConnL; 
      		yb = (v1.mNx < v2.mNx) ? v2.yPos : v1.yPos;
    	}
    	context.moveTo(xa, ya);
    	context.lineTo(xb, yb);
    	context.stroke();
    	context.closePath();
 	}

 	var vertex;
 
 	function build(graph, Nedges) {
 
    	setTextStyle();

    	context.textBaseline = "middle";
    	context.textAlign = "center";

   		for (var i = 0; i < 5; i++) {
    		for (var j = 0; j < 7; j++) {
        		vertex = new DisplayVertex(names[j] + i);
        		vertex.mNx = j;
        		vertex.mNy = i;
        		vertex.xPos = xPos[j];
        		vertex.yPos = yPos[i];
        		vertex.updateGeometry();        
        		graph.mV.push(vertex);
        		drawVertex(vertex);         
    		}
  		}
  
    	randomize(graph, Nedges);
  
	}// build

  	function redraw(graph) {
    	// only use mAdj for drawing connections
    	// clear canvas
    	fillBackground();

    	setTextStyle();

    	context.textBaseline = "middle";
    	context.textAlign = "center";

    	var N = graph.mV.length;

    	// draw all vertices
    	for (var i = 0; i < N; i++) {
      		drawVertex(graph.mV[i]);
    	}

    	// draw all connections
    	for (var i = 0; i < N; i++) {
      		var conn = graph.mAdj[i]; // all vertices connected to vertex i
      		for (var k = 0; k < conn.length; k++) {        
        		drawConnect(graph.mV[i], graph.mV[conn[k]]);        
      		}
    	}
 	}// redraw

  	function randomize(graph, Nedges) {

    	/* adding random edges, only Nedges (sparse)
       	allow connection only in cases below:
       mNx1 = mNx2, |mNy1 - mNy2| == 1
       mNy1 = mNy2, |mNx1 - mNx2| == 1
       |mNx1 - mNx2| == 1 and |mNy1 - mNy2| == 1   
    	*/
  
    	var edges = 0;
    	var count = 0;

    	var check = new Array(35);
    	for (var i = 0; i < 35; i++) {
      		check[i] = new Array(35);
    	}

    	for (var i = 0; i < 35; i++) {
      		for (var j = 0; j < 35; j++) {
        		check[i][j] = 0;
      		}
    	}

    	var index1, index2;

    	// reset all vertices
    	for (var i = 0; i < graph.mV.length; i++) {
      		graph.mV[i].mColor = "black";
      		graph.mV[i].mParent = null;
      		graph.mV[i].mDistance = null;
    	}

    	// remove all existing edges
    	for (var i = 0; i < graph.mAdj.length; i++) {
      		graph.mAdj[i] = [];
    	}

    	while (edges < Nedges) {
      		// select 2 random indexes
      		index1 = Math.floor(Math.random() * 35);// range
      		index2 = index1;
      		while (index2 == index1) {
        		index2 = Math.floor(Math.random() * 35);// range
      		}
      		var nX1 = graph.mV[index1].mNx;
      		var nY1 = graph.mV[index1].mNy;
      		var nX2 = graph.mV[index2].mNx;
      		var nY2 = graph.mV[index2].mNy;
      		if ((Math.abs(nX1-nX2) <= 1) && (Math.abs(nY1-nY2) <= 1) ) {// allow edge           
        		if (check[index1][index2] == 0 && check[index2][index1] == 0) {        
          			graph.mAdj[index1].push(index2);
          			graph.mAdj[index2].push(index1);

          			check[index1][index2] = 1;
          			check[index2][index1] = 1;
          			edges++;      
        		}        
      		}
    	}// while
    	 	
    	var disp;
    
    	redraw(graph);
    	$('#vertexForm').find(':submit')[0].disabled = false;   
		$('#status').text('Ready to search');
 	}// randomize

 	
	function validSource(sourcename) {
		var i;
    	// validity check
    	var isvalid = /^[a-z]\d$/.test(sourcename);

    	if (!isvalid) {
      		return false;
    	} else {
      		// search graph for element with name sourcename
      		for (i = 0; i < 35; i++) {
        		if (graph.mV[i].mName == sourcename) {
          			break;
        		}
      		}// for    
      		if (i == 35) {
        		// not found
        		alert("vertex not found");
        		return false;
      		} else {
        		src = graph.mV[i];
        		return true;
      		}// if
 		}// if
	}// validSource
  	  
	build(graph, Nedges);
  
	var message;
  
  	var edgeArray = [];
  	var vertexArray = [];
  
  	var count = 0;
  	var edges;
  	var vertices;
		
  	for (var j = 0; j < 35; j++) {// for each vertex
		vertexArray.push({"name":graph.mV[j].mName});
		for (var i = 0; i < graph.mAdj[j].length; i++) {// for each adjacent vertex		
			edgeArray.push({"from":j, "to":graph.mAdj[j][i]});
		}// i
  	}// j

  	function stepInit(graph) {
	  	sourcename = $("#vertexForm input[name='sourcename']").val();
	  	$('#stepForm').find(':submit')[0].disabled = false;
	  
	  	if (!validSource(sourcename)) { return; }
	  
	  	var message;
	  
	  	var edgeArray = [];
	  	var vertexArray = [];
	  
	  	var count = 0;
	  	var edges;
	  	var vertices;
			
	 	for (var j = 0; j < 35; j++) {// for each vertex
			vertexArray.push({"name":graph.mV[j].mName});
			for (var i = 0; i < graph.mAdj[j].length; i++) {// for each adjacent vertex		
				edgeArray.push({"from":j, "to":graph.mAdj[j][i]});
			}// i
	  	}// j
			  
	  	edges = {"jsonEdges":edgeArray};
	  	vertices = {"jsonVertices":vertexArray};
	   
	  	message = {"jsonEdges":edgeArray, "jsonVertices":vertexArray, 
			  "sourcename":sourcename};
	  
	  	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : '<c:url value="/initGraph" />',
			data : JSON.stringify(message),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("INITIALIAZATION SUCCESSFUL");
			},
			
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	   
	  	console.log("stepInit completed");
  	}
  
  	function searchStep(graph) {// change this name in the final version
	  	console.log("debug step begin");
  
  	  	message = {"type":"STEP"};// minimal message
  	  
	  	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : '<c:url value="/searchStep" />',
			data : JSON.stringify(message),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS");
					
				if (data["status"] == "STEP") {
					// data is a StepResult container so we extract the graph attribute
					var stepVertices = data["graph"]["vertices"];
					for (var i = 0; i < stepVertices.length; i++) {
						graph.mV[i].mColor = stepVertices[i].color;// update graph
						graph.mV[i].mDistance = stepVertices[i].d;
					}
					
					redraw(graph);
					$('#status').text('Searching...');
				} else if (data["status"] == "FINISHED") {
					$('#vertexForm').find(':submit')[0].disabled = true;
					console.log("Search completed");
					$('#status').text('Search completed');
					$('#stepForm').find(':submit')[0].disabled = true;
				}
			},
			
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	  
  	}
 
  	$("#initelem").submit(function(event) { randomize(graph, Nedges); return false; }); 
  	$('#initelem').find(':submit')[0].disabled = false;
  	$('#stepForm').find(':submit')[0].disabled = true;
  	$("#stepForm").submit(function(event) { searchStep(graph); return false; });
  	$("#vertexForm").submit(function(event) { stepInit(graph); return false; });
}// canvasApp

$(document).ready(canvasApp);


</script>
</head>

<body>

<nav>
<br>
<br><br>
</nav>

<header id="intro">
<h1>Breadth First Search Demonstration</h1>
<p>I present here a Java based demonstration of the Breadth First Search algorithm.<br>
I follow closely the approach of Cormen in his classical textbook.</p>
<h2>Explanations</h2>
<p>The graph edges are randomly initialized. You can select the source vertex by entering its name. Then you can choose between two demonstration modes:
In stepwise mode a new request is sent to the server on each step that sends a response that is used to update the graph.
In animation mode a single request is sent to the server and the response is used in an automatic animation.<br>
All newly discovered vertices are colored in green. When the search is completed all connected vertices are blue and the edges that belong to the Breadth First Tree are blue. The distance of each vertex to the source vertex is also displayed.<br>
The animation speed can be changed at any time using the range control.</p>
</header>

<div id="display">
  <canvas id="canvas" width="700" height="600">
    Your browser does not support HTML 5 Canvas
  </canvas>
<footer>
<p>Dominique Ubersfeld, Cachan, France</p>
</footer> 
 
</div>

<div id="controls">
  <div id="stepwise">
  <h3>Stepwise mode</h3>
   	<form name="step" id="stepForm">
        Click to step:
        <input type="submit" name="step-btn" value="Step">
    </form>
    <form name="step" id="vertexForm">
        Source vertex: <input type="text" name="sourcename" size="2">
        <input type="submit" name="step-btn" value="Enter">
    </form>
  </div>
  
  <div id="randomize">
      <p>Click here to randomize the graph edges</p>
      <form name="initialize" id="initelem">
        <input type="submit" name="randomize-btn" value="Randomize">
      </form>
  </div>  
  <div id="msg">
    <p id="status"></p>
  </div> 
</div>

</body>

</html>