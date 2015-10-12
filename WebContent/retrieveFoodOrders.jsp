
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.ModifierChosen"%>
<%@ page import="model.FoodDisplayObject"%>
<%@ page import="model.FoodOrderItem"%>\
<%@ page import="model.FoodOrder"%>

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
			ArrayList<FoodDisplayObject> foodDisplayObjectList = (ArrayList<FoodDisplayObject>) request
					.getAttribute("foodOrders");

			for (FoodDisplayObject fDO : foodDisplayObjectList) {
				String stallName = fDO.getStallName();
				ArrayList<FoodOrderItem> foodOrderItemList = fDO.getFoodOrderItem();
	%>
	</br>
	<%=stallName%>
	<table>
		<tr>
			<th>food</th>
			<th>addons</th>
			<th>quantity</th>
			<th>price</th>
			<th>users</th>
		</tr>
		<%	double totalPrice = 0;
			
			for (FoodOrderItem fOI : foodOrderItemList) {
				String foodName = fOI.getFood().getName();
				ArrayList<ModifierChosen> modifierList = new ArrayList<ModifierChosen>(fOI.getModifierList());
				int quantity = fDO.getQuantity(fOI);
				double price = fOI.getPrice();
				totalPrice+=quantity*price;
				ArrayList<String> userList = fDO.getUsernameList(fOI);
		%>
		<tr>
			<td><%=foodName%></td>
			<td>
				<table>
					<% 
			for(ModifierChosen mod : modifierList){
				String modName = mod.getName();
				%>
					<tr>
						<td><%=modName %></td>
					</tr>
					<%} %>
				</table>
			</td>
			<td><%=quantity%></td>
			<td><%=price%></td>

			<td>
				<table>
					<%for(String username :userList){
						%>

					<tr>
						<td><%=username %></td>
					</tr>
					<%} %>
				</table>
			</td>

		</tr>
		<%
			}
		%>

	</table>
	Total Price = <%=totalPrice %>
	</br>
	</br>

	<%
		}
	%>



	<%
		}
	%>

</body>
</html>