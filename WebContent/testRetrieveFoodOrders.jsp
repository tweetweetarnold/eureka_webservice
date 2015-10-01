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
	JSONArray foodOrderList = (JSONArray) request.getAttribute("foodOrders");
	ArrayList<FoodOrder> listData = new ArrayList<FoodOrder>();     
	
	if (foodOrderList != null) { 
	   for (int i=0;i<foodOrderList.size();i++){ 
		   	//Add Canteen to the ArrayList!
	    	listData.add((FoodOrder)foodOrderList.get(i));
		}
	}
	out.println("Number of people" + listData.size());
	
	out.println("status = " + listData.get(0).getStatus());
%>
<br/>

<table border="1">
	<tr>
		<th>Number</th> <th>Name</th> <th>Items</th> <th>Quantity</th> <th>Price</th>
	</tr>
<%
	if(listData!=null){
		for(int i = 0; i<=listData.size(); i++){
			FoodOrder tempFoodOrder = listData.get(i);
			Employee employee = tempFoodOrder.getEmployee();
			String name = null;
			if(employee!=null){
				name = tempFoodOrder.getEmployee().getName();
			}
			Set<FoodOrderItem> food = tempFoodOrder.getFoodOrderList();
			if(food!=null){
				Iterator<FoodOrderItem> iterator = food.iterator();
				FoodOrderItem foodOrderItem = (FoodOrderItem)iterator.next();
%>

		<tr>
		<td rowspan=<%=food.size()%>><%=i+1%></td><td rowspan=<%=food.size()%>><%=name%></td>
<% 			
			
%>			
				<td><%=foodOrderItem.getFood().getName()%></td> <td><%=foodOrderItem.getFood().getPrice() %></td>
	</tr>
<%				
				
			while(iterator.hasNext()){
				foodOrderItem=(FoodOrderItem)iterator.next();
%>
				<tr>
					<td><%=foodOrderItem.getFood().getName()%></td><td><%=foodOrderItem.getFood().getPrice() %></td>
				</tr>
			
<%
			}			
%>
	
<%
			}
			}
%>
	

<%		
	}
%>
</table>
<%} %>
</body>
</html>