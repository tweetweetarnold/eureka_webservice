<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="sun.misc.FpUtils"%>

<%@ page import="model.FoodOrder"%>
<%@ page import="model.FoodOrderItem"%>
<%@ page import="model.Employee"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% List<Employee> employeeList = (List<Employee>)request.getAttribute("employeeList"); 
		if (employeeList != null && employeeList.size()!=0) {%>
	
		
		The following list are still owing their payment
		<table>
		<th>Username</th>
		<th>Amount owed</th>
		<th>Email address</th>
		<th>Current Status</th>
		<th>Company</th>
		<%
			for (Employee e : employeeList) {
		String username = e.getUsername();
		double amountOwed =	e.getAmountOwed();
		String userEmail = e.getEmail();
		String currentStatus = e.getStatus();
		String companyName = e.getCompany().getName();
		
			%>
		<tr><td>	<%=username%></td>
			<td>$<%=amountOwed%></td>
			<td><%=userEmail%></td>
			<td><%=currentStatus%></td>
			<td><%=companyName%></td></tr>
			<%} %>
		</table>
		<%} else {%>
				There are no users having outstanding payment
		<%} %>
</body>
</html>