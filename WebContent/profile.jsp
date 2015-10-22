<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DABAO</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

			<table style="width: 50%;
	font-size: 15px;" border="1">
				<tr>
					<td>Name:</td>
					<td>Arnold</td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td>Male</td>
				</tr>
			</table>

			<br>



			<table style="width: 50%;
	font-size: 15px;" border="1">
				<thead>
					<tr>
						<th>OrderId</th>
						<th>Date</th>
						<th>Food</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.orderHistory}" varStatus="loop" var="order">
						<tr>
							<td>
								<c:out value="${order.foodOrderId}" />
							</td>
							<td><c:out value="${order.createDate}"/></td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<!-- center align -->

		<c:remove var="orderHistory" scope="session" />

	</div>
	<!-- container -->

	<!-- Footer JSP Include -->
	<jsp:include page="headerfooter/footer.jsp" />


</body>


</html>