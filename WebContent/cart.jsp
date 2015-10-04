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
					Food food = new Food("abc", "123", 2.30, null, new Date());
					FoodOrder order = (FoodOrder) session.getAttribute("foodOrder");
					FoodOrderItem item2 = new FoodOrderItem(order, food, 2, 6.00, "food sucks", new Date());
					Set<FoodOrderItem> list = order.getFoodOrderList();
					list.add(item2);
					int i = 0;
					if(true){
// 					if (order != null) {
// 						Set<FoodOrderItem> list = order.getFoodOrderList();
						for(FoodOrderItem item : list){
				%>
				<tr>
					<td><%=i++%></td>
					<td><%=item.getFood().getName()%></td>
					<td><%=item.getQuantity()%></td>
					<td><%=item.getPrice()%></td>
				</tr>
				<%
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

		<form action="#" method="post">
			<button class="btn btn-lg btn-primary btn-block" type="submit" style="max-width: 50%;">Check Out</button>
		</form>


		<!-- Footer JSP Include -->
		<jsp:include page="headerfooter/footer.jsp" />

	</div>
	<!-- container -->


</body>


</html>