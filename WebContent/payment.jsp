<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DaBao - Profile</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/sticky-footer.css" rel="stylesheet">
<link href="resources/css/shop-homepage.css" rel="stylesheet">

</head>
<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/header.jsp" />

	<div class="container">
		<h1>Payment</h1>
		<h3>Your receipts</h3>
		<br>
		<br>

		<table>
			<tbody>
				<c:forEach items="${sessionScope.myFoodOrders}" var="myFoodOrder">

					<tr>
						<td>
							<c:out value="${myFoodOrder.foodOrderId}" />
						</td>
						<td></td>
					</tr>

					<c:forEach items="${myFoodOrder.getFoodOrderList}" var="myFoodOrderItem">
						<tr>
							<td>
								<c:out value="${myFoodOrderItem.food.name}" />
							</td>
							<td>
								<c:out value="${myFoodOrderItem.getPrice}" />
							</td>
						</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>

		<br>
		<br>
		<form action="#" method="post">
			<button type="submit" class="btn btn-lg btn-primary btn-block">Make Payment</button>
		</form>


	</div>
	<!-- container -->

	<!-- Footer JSP Include -->
	<jsp:include page="headerfooter/footer.jsp" />


</body>


</html>