<%@page import="model.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@include file="protect.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DABAO</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
			<h1>Modifier</h1>

			<c:set value="${sessionScope.allFood[param.foodId]}" var="selectedFood" />
			<h3>
				Food:
				<c:out value="${selectedFood.name}" />
			</h3>

			<form action="AddFoodItemToOrderItemsServlet" method="post">
				<input type="hidden" name="foodPos" value="${param.foodPos}">
				<table class="table table-striped" style="width: 60%;">
					<thead>
						<tr>
							<th>S/N</th>
							<th>Modifier</th>
							<th>Price</th>
							<th>Add</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach varStatus="loop" items="${selectedFood.modifierList}" var="modifier">
							<tr>
								<td>
									<c:out value="${loop.index + 1}" />
								</td>
								<td>
									<c:out value="${modifier.name}" />
								</td>
								<td>
									<fmt:formatNumber value="${modifier.price}" var="modPrice" minFractionDigits="2" />
									+ $
									<c:out value="${modPrice}" />
								</td>
								<td>
									<input type="checkbox" value="true" name="${modifier.name}">
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>

				<button class="btn btn-lg btn-danger" type="submit" style="width: 50%;">Add to Cart</button>
			</form>
		</div>
	</div>

	<!-- container -->

	<!-- Footer JSP Include -->
	<jsp:include page="headerfooter/footer.jsp" />
</body>


</html>