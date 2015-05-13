<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::CSP::TankAllocation::Search::SimulatedAnealing::.</title>
<style>
.left{
	position: absolute;
	left: 100px;
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
	<h1>Simulated Anealing</h1>
	<form action="simulatedAnealingSearch" method="post">
		<p>T: <input class="left" type="text" name="T" value="1">
		<p>alpha: <input class="left" type="text" name="alpha" value="0.9">
		<p>T_min: <input class="left" type="text" name="T_min" value="0.000001">
		<p> <input class="left" type="submit" value="Submit"><br>
	</form>
<p>Click "Submit" button and wait for result
<h2>Description</h2>
<p><b>Simulated Anealing:</b>Simulated annealing is a method for finding a good (not necessarily perfect) solution to an optimization problem. 
If you're in a situation where you want to maximize or minimize something, your problem can likely be tackled with simulated annealing.</p>
<h3>The basic algorithm</h3>
<p>Here's really high-level overview. It skips some very importance details, which we get to in a moment.
<ol>
<li>First, generate a random solution
<li>Calculate its cost using some cost function you've defined
<li>Generate a random neighboring solution
<li>Calculate the new solution's cost
<li>Compare them:
<ul>
<li>If cnew less than cold: move to the new solution
<li>If cnew greater than cold: maybe move to the new solution
</ul>
<li>Repeat steps 3-5 above until an acceptable solution is found or you reach some maximum number of iterations.
</ol>
<p>For more information see <a href="http://katrinaeg.com/simulated-annealing.html">here.</a>
<h2>Parameter</h2>
<p><b>T:</b> Temperature parameter on simulated anealing algorithm
<p><b>alpha:</b> Alpha parameter using when update T parameter (in my code I using formual: T(k+1) = T(k) * alpha)
<p><b>T_min:</b> Min temperature, if T less than T_min than terminated process of simulated anealing algorithm 
<h2>Usage on the library</h2>
<div class="body">
Chemical chemical = new Chemical();					// create new instance of your problem<br>
chemical.stateModel();								// state model for your problem<br>
chemical.simulatedAnealingSearch(T, alpha, T_min)	// call simulated anealing search from library<br> 
</div>
<br>
<br>
<br>
<hr>
<p><a href="Search.jsp">Back</a>
</body>
</html>