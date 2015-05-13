<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::CSP::TankAllocation::Search::TabuSearch::.</title>
<style>
.left {
	position: absolute;
	left: 200px;
}
</style>
<style>
.body{
	position: absolute;
	left: 100px;
	font-family: monospace;
}
</style>
</head>
<body>
<h1>Tabu Search</h1>
<form action="tabuSearch" method="post">
<p>Tabu Length: <input class="left" type="text" name="tabuLength" value="10">
<p>Max Time: <input class="left" type="text" name="maxTime" value="300">
<p>Max Iteration: <input class="left" type="text" name="maxIte" value="100000">
<p>Max Stable: <input class="left" type="text" name="maxStable" value="100">
<p><input  class="left" type="submit" value="Submit"><br> 
</form>
<p>Click "Submit" button and wait for result</p>
<h2>Description</h2>
<p><b>Tabu search:</b> Tabu search is a Global Optimization and a Metaheuristic or Meta-strategy for controlling an 
embedded heuristic technique. Tabu search is a parent for a large family of derivative approaches that introduce memory
 structures in Metaheuristic, such as Reactive Tabu Search and Parallel Tabu Search</p>
 <p>For more information see <a href="http://en.wikipedia.org/wiki/Tabu_search">here.</a>
<h2>Parameter</h2>
<p><b>Tabu Length:</b> Length of tabu list
<p><b>Max Time:</b> Max time to solution problem
<p><b>Max Iteration: </b> Max iteration
<p><b>Max Stable:</b> Max stable of algorithm
<h2>Usage on the library</h2>
<div class="body">
Chemical chemical = new Chemical();					// create new instance of your problem<br>
chemical.stateModel();								// state model for your problem<br>
chemical.tabuSearch(tabuLength, maxTime, maxIte, maxStable);	// call tabu search from library<br> 
</div>
<br>
<br>
<br>
<hr>
<p><a href="Search.jsp">Back</a>
</body>
</html>