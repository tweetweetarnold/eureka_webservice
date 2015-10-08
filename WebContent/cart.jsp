<%@page import="model.FoodOrder"%>
<%@page import="model.FoodOrderItem"%>
<%@page import="java.util.List"%>
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
			FoodOrder foodOrder = (FoodOrder) session.getAttribute("foodOrder");
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
					if(foodOrder != null){
						List<FoodOrderItem> list = foodOrder.getFoodOrderList();	
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
			</tbody>
		</table>

		<form action="AddNewFoodOrderServlet" method="post">
			<%-- 			<input type="hidden" value="<%=request.setAttribute("foodOrder", order)%>" name="foodOrder"> --%>
			<button class="btn btn-lg btn-primary btn-block" type="submit" style="max-width: 50%;">Check Out</button>
		</form>


		<!-- Footer JSP Include -->
		<jsp:include page="headerfooter/footer.jsp" />

	</div>
	<!-- container -->


</body>


</html>