<%@page import="sun.misc.FpUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Test Jsp to retrieve orders from the database</title>
</head>
<body>
	Hi there! are you ready to retrieve today's orders?

	<form action="GetTodayOrdersServlet" method="post">
		<input type="submit" value="YES">
	</form>


	<%
		if (request.getAttribute("foodOrders") != null) {
			JSONObject foodOrders = (JSONObject) request.getAttribute("foodOrders");
			Iterator iter = foodOrders.keySet().iterator();

			out.println("Number of people " + (foodOrders.size() - 1));
	%>
	</br>


	<table border="1">
		<tr>
			<th>Number</th>
			<th>Name</th>
			<th>Items</th>
			<th>Quantity</th>
			<th>Price</th>
		</tr>
		<%
			
		%>
		<%
			double totalPrice = 0.0;
				int listSize = 0;
				int number = 0;
				while (iter.hasNext()) {
					String username = (String) iter.next();
					if (!username.equals("totalPrice")) {
						ArrayList<FoodOrderItem> foodOrderItemList = (ArrayList<FoodOrderItem>) foodOrders
								.get(username);

						listSize = foodOrderItemList.size();
						String foodName = foodOrderItemList.get(0).getFood().getName();
		%>
		<tr>
			<td rowspan=<%=listSize%>><%=++number%></td>
			<td rowspan=<%=listSize%>><%=username%></td>
			<td><%=foodName%></td>
			<td><%=foodOrderItemList.get(0).getQuantity()%></td>
			<td><%=foodOrderItemList.get(0).getPrice()%></td>
		</tr>
		<tr>
			<%
				for (int i = 1; i < listSize; i++) {
								FoodOrderItem tempItem = foodOrderItemList.get(i);
								foodName = tempItem.getFood().getName();
								int quantity = tempItem.getQuantity();
								double price = tempItem.getPrice();
			%>
			<td><%=foodName%></td>
			<td><%=quantity%></td>
			<td><%=price%></td>

		</tr>
		<%
			}
					} else {
// 						totalPrice =Double.parseDouble((String)foodOrders.get(username));
						totalPrice = (Double) foodOrders.get(username);
					}
				}
		%>
	</table>
	</br>
	The total price is = <%=totalPrice%>
	<%
		}
	%>
	
	
</body>
</html>