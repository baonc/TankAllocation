<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::CSPLib::TankAllocationProblem::.</title>
<style>
.right{
	position: absolute;
	right: 200px;
	top: 0px;
}
</style>
</head>
<body>
<h1>Tank Allocation Problem</h1>
<p>Proposed by Pierre Schaus</p>
<p class="right"><a href="IndexOfProblem.jsp">Back</a>
<hr>
<p>The tank allocation problem involves the assignment of difference cargoes (volumes of chemical products to be shipped 
by the vessel) to the available tank of vessel. The loading plans of bulk vessels are generated manually by the vessel 
planner although it is difficult to generate high quality solutions. The constraints to satisfy are mainly segregation 
constraints:</p>
<ol id="firstItem">
	<li>Prevent chemicals from being loaded into certain types of tank because:
	<ul id="secondItem">
		<li>The chemical may need to have its temperature managed and the tank needs to be equipped with a heating 
		system
		<li>The tank must be resistant to the chemical
		<li>A tank may still be contaminated by previous cargoes incompatible with the chemical
	</ul>
	<li>Prevent some pairs of cargoes to be placed next each other: not only the chemical interactions between the 
	difference cargos need to be considered but also the temperature at which need to be transported. Too difference 
	temperature requirements for adjacent tanks cause the second one to solidfy due to cooling off by the first cargo 
	or the first maybe become chemically unstable due to heating up of the second cargo
</ol>
In order to minimise the costs and inconvenience of tank cleaning, an ideal loading plan should maximise the total volume of 
unused tanks (i.e. free space).<br/>
<p><a href="Model.jsp">Modeling</a>
<p><a href="Search.jsp">Searching</a>
</body>
</html>