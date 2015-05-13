<%@page import="java.io.PrintWriter"%>
<%@page import="localsearch.model.VarIntLS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.::CSP::TankAllocation::Result::.</title>
<style>
	table, th, td{
		border: 1px solid black;		
		border-collapse: collapse;
	}
	th, td{
		padding: 5px;
		text-align: center;
	}
	table#t01{
		vertical-align: middle;
		width: 50%;
		background-color: #f1f1c1;	
	}
</style>
<style>
	.right {
		position: absolute;
		right: 50px;
		top: 0px;
	}
</style>
</head>
<body>
Result is:<br>
<p class="right" ><a href="Search.jsp">Back to Search</a></p>
<center>
<table id="t01">
<tr>
<th>Tank</th>
<th>Cargo</th>
</tr>
<c:forEach var="cargo" items="${result}" varStatus="status">
<tr>
<td>${status.index + 1}</td>
<td>${cargo.getValue()}</td>
<tr>
</c:forEach>
</table>
</center>
</body>
</html>