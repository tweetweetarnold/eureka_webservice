<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.FoodOrder" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.ArrayList" %>
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
</form>>


<%
if(request.getAttribute("foodOrders")!=null){
	JSONArray foodOrderList = (JSONArray) request.getAttribute("foodOrders");
	ArrayList<FoodOrder> listdata = new ArrayList<FoodOrder>();     
	
	if (foodOrderList != null) { 
	   for (int i=0;i<foodOrderList.size();i++){ 
		   	//Add Canteen to the ArrayList!
	    	listdata.add((FoodOrder)foodOrderList.get(i));
		}
	}
	out.println("Size " + listdata.size());
}

%>

</body>
</html>