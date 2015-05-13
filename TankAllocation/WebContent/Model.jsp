<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::CSP::Modeling::.</title>
<script type="text/javascript"
  src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>
<script type="text/x-mathjax-config">
  MathJax.Hub.Config({tex2jax: {inlineMath: [['$','$'], ['\\(','\\)']]}});
</script>
</head>
<body>
<h1>Models of Tank Allocation problem</h1>
	<p>Variable x[t] for each tank t representing the product type to be place inside that tank. The initial domain is 0..P (P number of the cargo 
	(product)), 0 represent a dummy product meaning that no product is assigned to the tank t (in this case x[t] = 0)</p>	
	There are three constraint of this problem<br>
	<ol>
	 <li> Volume to ship of the cargo X must be less or equal amout of cargo X included in allocation<br>
	 		$$\sum_{i=1}^{nCargo} capacity(tank) * (x[tank] == cargo_i) \ge Volume(cargo_i)$$
	 <li> No tank can be allocated a cargo in its impossibleCargo<br>
	 		$$x[tank] \neq imposibleCargo[tank]$$
	 <li> No tank can be neighbours with a tank containing incompatible cargo
	 		$$\{x[tank_i], x[neighboursOfTank_i] \} \notin \{incompatibilities\}$$
	</ol>
You can download file modeling by click <a href="ModelSample.jsp">here</a>.
<hr>

<p><a href="Index.jsp">Back</a>
<p><a href="Search.jsp">Searching</a>
</body>
</html>