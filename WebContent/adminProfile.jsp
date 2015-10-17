<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DaBao ADMIN - Profile</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/shop-homepage.css" rel="stylesheet">
<link href="resources/css/sticky-footer.css" rel="stylesheet">

</head>
<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/header.jsp" />


	<div class="container">
		<div align="center">
			<h1>My Profile</h1>

			<%
				request.getAttribute("name");
			%>

			<table style="width: 50%; font-size: 15px;" border="1">
				<tr>
					<td>Name:</td>
					<td><%=request.getAttribute("name")%></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td><%=request.getAttribute("username")%></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><%=request.getAttribute("email")%></td>
				</tr>
				<tr>
					<td>Contact Number:</td>
					<td><%=request.getAttribute("contactNumber")%></td>
				</tr>
				<tr>
					<td>Status:</td>
					<td><%=request.getAttribute("status")%></td>
				</tr>
				<tr>
					<td>Amount Owed:</td>
					<td><%=request.getAttribute("amountOwed")%></td>
				</tr>

			</table>

			<br> <br>
			<H1>Order History</h1>
			<br>

			<table class="table table-striped">
				<tr>
					<th>S/N</th>
					<th>Food Order ID</th>
					<th>Price</th>
					<th>Date</th>
				</tr>
				<%
					ArrayList<FoodOrder> foodOrderList = new ArrayList<FoodOrder>(
							(Set<FoodOrder>) request.getAttribute("orderHistory"));
					int i = 1;
					for (FoodOrder f : foodOrderList) {
				%>
				<tr>
					<td><%=i++%></td>
					<td><%=f.getFoodOrderId()%></td>
					<td><%=f.getTotalPrice()%></td>
					<td><%=f.getCreateDate()%></td>
					
				</tr>
				<%
					}
				%>


			</table>


		</div>


		<!-- center align -->

	</div>
	<!-- container -->

	<!-- Footer JSP Include -->
	<jsp:include page="headerfooter/footer.jsp" />


</body>


</html>