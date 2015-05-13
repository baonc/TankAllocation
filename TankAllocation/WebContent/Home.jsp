<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OPENCBOLib: Library for local search constraint base</title>
</head>
<body>
<h1>OPENCBOLib: Library for local search constraint base</h1>
<h2>Tutorial: Demo using search of the library</h2>
<hr>
<h2>Overview</h2>
This library are constructed to resolve the problem for constraint.<br>
This tutorial will demo using search on the library to resolve the  number of <a href="IndexOfProblem.jsp">problem</a> on page 
<a href="http://csplib.org/">CSPLib.</a><br>
You can download lastest version of the library <a href="DownloadLib.jsp">here.</a>

<ul>
<li> OpenLS is based mainly on the CBLS architecture [Van Hentenryck and Michel, 2005]
<li> The Model:
	<ul>
		<li> <b>Variables:</b> Model the problem, represent a solution. <br>
		A problem can have dirrent modeling strategies 
		<li> <b>Invariables:</b> Specify a relation that must be maintained under changes of its variables<br>
		Invariants are declarative: Specify WHAT to maintain, not HOW to maintain<br>
		Important for differentiable object<br>
		An Assignment of a value to a variable will include a propagation which updates the invariant incrementally thanks to a dependency graph 
		maintained 
		<li> <b>Diffrentiable Objects:</b> Constraint, Function:
		<ul>
		<li> Constraint: Capture substructure of the problem<br>
		Violations: specify how much a constraint is violated<br>
		Differentiation: how much a local move (eg. a change of a variable) affects the violations
		<li> Function: Capture substructure of the problem<br>
		Evaluation: specify the evaluation of a function<br>
		Differentiation: how much a local move (e.g., a change of a variable) affects the evaluation 
		</ul>
	</ul>
<li> The Search:
	<ul>
		<li> Heuristic
		<li> Meta-heuristic
	</ul>
</ul>
For more information see <a href="Document.jsp">document</a>.
</body>
</html>