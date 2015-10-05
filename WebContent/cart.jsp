<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Date"%>
<%@page import="model.Food"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="model.FoodOrderItem"%>
<%@page import="model.FoodOrder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DaBao - Cart</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/shop-homepage.css" rel="stylesheet">

</head>
<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/header.jsp" />

	<div class="container">

		<h1>Your Orders</h1>
		<%
			JSONObject foodOrder = (JSONObject) session.getAttribute("foodOrder");
		System.out.println("foodOrder: " + foodOrder);
		FoodOrder order = (FoodOrder) foodOrder.get("foodOrder");
		System.out.println("order: " + order.toString());
		%>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Row</th>
					<th>Food Item</th>
					<th>Quantity</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(order != null){
					Set<FoodOrderItem> list = order.getFoodOrderList();	
					System.out.println("foodList: " + list.size());
				if(list != null){
								for(FoodOrderItem item : list){
				%>
				<tr>
					<td>1</td>
					<td><%=item.getFood().getName()%></td>
					<td><%=item.getQuantity()%></td>
					<td><%=item.getPrice()%></td>
				</tr>

				<%
					}
					}
					}
				%>

				<tr>
					<td>1</td>
					<td>Chicken Rice</td>
					<td>2</td>
					<td>$5.00</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Wanton Mee</td>
					<td>1</td>
					<td>$3.00</td>
				</tr>
			</tbody>
		</table>

		<form action="/eureka_webservice/AddNewFoodOrder" method="post">
			<%-- 			<input type="hidden" value="<%=request.setAttribute("foodOrder", order)%>" name="foodOrder"> --%>
			<button class="btn btn-lg btn-primary btn-block" type="submit" style="max-width: 50%;">Check Out</button>
		</form>


		<!-- Footer JSP Include -->
		<jsp:include page="headerfooter/footer.jsp" />

	</div>
	<!-- container -->


</body>


</html>