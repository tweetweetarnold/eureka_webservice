<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.FoodOrder" %>
<%@ page import="model.FoodOrderItem" %>
<%@ page import="model.Employee" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Jsp to retrieve orders from the database</title>
</head>
<body>
Hi there! are you ready to retrieve today's orders?

<form action="GetTodayOrdersServlet" method="post">
<input type="submit" value="YES">
</form>


<%
if(request.getAttribute("foodOrders")!=null){
	JSONObject foodOrders = (JSONObject) request.getAttribute("foodOrders");    
	Iterator iter = foodOrders.keySet().iterator();
	
	out.println("Number of people" + (foodOrders.size()-1));
	
	while(iter.hasNext()){
		out.println(iter.next());
	}
%>
<br/>

<table border="1">
	<tr>
		<th>Number</th> <th>Name</th> <th>Items</th> <th>Quantity</th> <th>Price</th>
	</tr>
<%
	
%>

		

<%		
	}
%>
</table>
<% %>
</body>
</html>