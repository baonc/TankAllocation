<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::CSP::TankAllocation::Search::.</title>
</head>
<body>
<h1>Search Algorithms</h1>
<p><b>Step 1:</b>Upload your input data: <br>
<p>You can see format of input file to construct a new input file in <a href="SampleInputFile.xml" >here</a>. Or:<br>
Skip this step and using us sample <a href="SampleInputFile.xml">input file</a> to see result.<br>

	<form action="uploadFile" method="post" enctype="multipart/form-data">
		<input type="file" name="file" />
		<input type="submit" value="Upload"/>
	</form>

<c:set var="check" scope="session" value="${message}"></c:set>
<c:if test="${check == 1}">
	<p><b>Upload file successfully.</b><br>
	${fileName}
</c:if>

<p><b>Step 2:</b>Select your search algorithms</p>
<p><a href="GreedySearch.jsp">Greedy search</a>
 
<p><a href="TabuSearch.jsp">Tabu search</a>
 
<p><a href="SimulatedAnealing.jsp">Simulated annealing search</a>

<p><a href="GASearch.jsp">Generic algorithm search</a>
<hr>
<p><a href="Index.jsp">Back</a>
</body>
</html>