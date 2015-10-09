
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Modifier"%>
<%@ page import="model.FoodDisplayObject"%>
<%@ page import="model.FoodOrderItem"%>

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
			ArrayList<FoodDisplayObject> foodDisplayObjectList = (ArrayList<FoodDisplayObject>) request.getAttribute("foodOrders");
	%>



	<table border="1">
		<tr>
			<th>Serial Number</th>
			<th>Stall</th>
			<th>Orders</th>
			<th>Add-ons</th>
			<th>price</th>
			<th>quantity</th>
			<th>total price</th>
			<th>user</th>
		</tr>
		<%
			for (FoodDisplayObject fDO : foodDisplayObjectList) {
					double totalPrice = 0.0;
					int serialNumber = fDO.getSerialNumber();
					String stallName = fDO.getStallName();
					ArrayList<FoodOrderItem> foodOrderItemList = fDO.getFoodOrderItem();
					HashMap<FoodOrderItem, Integer> quantityList = fDO.getQuantity();
					HashMap<FoodOrderItem, ArrayList<String>> userList = fDO.getUsername();
		%>
		<tr>
			<td><%=serialNumber%></td>
			<td><%=stallName%></td>
			<td><table>
					<%
						for (FoodOrderItem fOI : foodOrderItemList) {
									String foodName = fOI.getFood().getName();
									Set<Modifier> modifierList = new HashSet<Modifier>(fOI.getModifierList());
									double tempPrice = fOI.getPrice();
									int tempQuantity = quantityList.get(fOI);
									totalPrice+=(tempPrice*tempQuantity);
									ArrayList<String> tempUserList = userList.get(fOI);
					%>
					<tr>
						<td><%=foodName%></td>

						<td><table>
								<%
									for (Modifier m : modifierList) {
													String modifierName = m.getName();
								%>
								<tr>
									<td><%=modifierName%></td>
								</tr>
								<%
									}
								%>
							</table></td>
						<td><%=tempPrice%></td>
						<td><%=tempQuantity%></td>
						<td><table>
								<%
									for (String user : tempUserList) {
								%>
								<tr>
									<td><%=user%></td>
								</tr>
								<%
									}
								%>
							</table></td>
							<td><%=totalPrice %></td>
					</tr>
					<%
						}
					%>
				</table></td>
			<td></td>

		</tr>







		<%
			}
		}
		%>



</body>
</html>