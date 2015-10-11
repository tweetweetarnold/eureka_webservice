<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DaBao - Cart</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
				<c:set var="totalPrice" value="0" />
				<c:set var="count" value="0" />
				<c:forEach items="${sessionScope.myFoodOrderItems}" var="foodItem">
					<tr>
						<c:set var="count" value="${count + 1}" />
						<td>
							<c:out value="${count}" />
						</td>
						<td>
							<c:out value="${foodItem.food.name}" />
						</td>
						<td>
							<c:out value="${foodItem.quantity}" />
						</td>
						<td>
							$
							<c:out value="${foodItem.priceString}" />
							<c:set value="${totalPrice + foodItem.priceString}" var="totalPrice" />
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td style="font-size: xx-large;">Total:</td>
					<td style="font-size: xx-large;">
						$
						<fmt:formatNumber value="${totalPrice}" var="totalPrice2" minFractionDigits="2" />
						<c:out value="${totalPrice2}" />
					</td>
				</tr>
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

	<!-- 	Google Analytics -->
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');
		ga('create', 'UA-68676403-1', 'auto');
		ga('send', 'pageview');
	</script>


</body>


</html>