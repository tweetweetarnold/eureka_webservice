<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.FoodOrder"%>
<%@ page import="model.FoodOrderItem"%>
<%@ page import="model.Employee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	Hi there! are you ready to retrieve today's orders?

	<form action="retrieveFoodOrdersServlet" method="post">
		<input type="submit" value="YES">
	</form>
	<%
		if (request.getAttribute("foodOrders") != null) {
			
	%>
	
	<table>
		<tr>
		<th>Serial Number</th><th>Stall</th><th>Orders</th><th>Add-ons</th><th>price</th><th>quantity</th><th>total price</th><th>user</th>
		</tr>
		
	
	
	
	
	
	
	</table>
	
	
	
	
	
	
	
	
	<%		
		}
	%>




</body>
</html>