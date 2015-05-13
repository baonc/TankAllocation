<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::CSP::TankAllocation::Search::GreedySearch::.</title>
<style>
.body{
	position: absolute;
	left: 100px;
	font-family: monospace;
}
</style>
</head>
<body>
<h1>Greedy Search</h1>
<form action="greedySearch" method="post">
Max iteration: <input type="text" name="maxIteration" value="100000">
<input type="submit" value="Submit">
</form>
<p>Click the "Submit" button and wait for result</p>
<h2>Description</h2>
<p><b>Greedy algorithm:</b> Greedy algorithm select most violation variable and find most promissing value for most violation variable and 
set this value for this variable</p>
<h2>Parameter</h2>
<p><b>Max iteration:</b> Maximum number of iteration for the greedy algorithms (it must be integer and greater 0)</p>
<h2>Usage on the library</h2>
<div class="body">
Chemical chemical = new Chemical();					// create new instance of your problem<br>
chemical.stateModel();								// state model for your problem<br>
chemical.greedySearch(maxIteration);	// call greedy anealing search from library<br> 
</div>
<br>
<br>
<br>
<hr>
<p><a href="Search.jsp">Back</a>
</body>
</html>