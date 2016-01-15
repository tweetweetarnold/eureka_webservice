<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>LunchTime</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Bootstrap Core CSS -->
<link href="/eureka_webservice/resources/startbootstrap-business/css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="/eureka_webservice/resources/startbootstrap-business/css/modern-business.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/eureka_webservice/resources/startbootstrap-business/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css"
>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<jsp:include page="header.jsp" />

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Cart
					<small>your orders</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="/eureka_webservice/pages/homepage.jsp">Home</a>
					</li>
					<li class="active">Payment</li>
				</ol>
			</div>



		</div>
		<!-- /.row -->

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

			<c:set var="haveOrder" value="false" />

			<c:if test="${empty sessionScope.submittedOrders}">
				You haven't ordered anything! Go order something!
			</c:if>

			<c:if test="${not empty sessionScope.paymentSuccess}">
				<c:remove var="submittedOrders" scope="session" />
			</c:if>

			<c:if test="${not empty sessionScope.error}">
				<c:remove var="submittedOrders" scope="session" />
			</c:if>

			<c:forEach items="${sessionScope.foodDisplayPaymentList}" var="order" varStatus="orderLoop">

				<c:set var="haveOrder" value="true" />
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="heading${orderLoop.index}">
						<h4 class="panel-title">
							<!--  	<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${orderLoop.index}"
							aria-expanded="false" aria-controls="collapse${orderLoop.index}"
						>-->
							Order ID:
							<c:out value="${order.foodOrderId}" />
							-
							<fmt:formatDate type="both" value="${order.createDate}" />
							<p style="float: right;">
								<c:out value="${order.status}" />
							</p>
							</a>
						</h4>
					</div>
					<div>
						<!--  	<div id="collapse${orderLoop.index}" class="panel-collapse collapse" role="tabpanel"
					aria-labelledby="heading${orderLoop.index}"
				>-->
						<div class="panel-body">
							<div style="font-size: 18px;">

								<table>
									<tr>
										<td style="padding-right: 10px;">
											<strong>Canteen:</strong>
										</td>
										<td>Taman Jurong Market and Food Centre</td>
									</tr>
									<tr>
										<td style="padding-right: 10px;">
											<strong>Price:</strong>
										</td>
										<td>
											<fmt:formatNumber value="${order.totalPriceIncludingDisc}" var="totalPrice" minFractionDigits="2" />
											$
											<c:out value="${totalPrice}" />
										</td>
									</tr>
								</table>
							</div>
							<br>

							<table class="table table-striped" border="1">
								<thead>
									<tr>
										<th>Stall</th>
										<th>Food</th>
										<th>Quantity</th>
										<th>Price</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${order.foodOrderDiscountList}" var="foodItem">
										<tr>
											<td>
												<c:out value="${foodItem.food.stall.name}" />
											</td>
											<td>
												<c:out value="${foodItem.food.name}" />
												<ul>
													<c:forEach items="${foodItem.modifierChosenList}" var="modifierChosen">
														<li>
															<small>
																<c:out value="${modifierChosen.name}" />
																<fmt:formatNumber value="${modifierChosen.price}" var="newModifierPrice" minFractionDigits="2" />
																+ $
																<c:out value="${newModifierPrice}" />
															</small>
														</li>
													</c:forEach>
												</ul>
											</td>
											<td>
												<c:out value="${foodItem.quantity}" />
											</td>
											<td>
												<fmt:formatNumber value="${foodItem.price}" var="newPrice" minFractionDigits="2" />
												$
												<c:out value="${newPrice}" />
											</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>

						</div>
					</div>
				</div>

			</c:forEach>
		</div>



		<!-- PayPal form -->
		<c:choose>
			<c:when test="${haveOrder eq true}">
				<form action="${initParam['posturl']}" method="post">
					<input type="hidden" name="upload" value="1" />
					<input type="hidden" name="return" value="${initParam['returnurl']}" />
					<input type="hidden" name="cmd" value="_cart" />
					<input type="hidden" name="business" value="${initParam['business']}" />
					<input type="hidden" name="currency_code" value="SGD">

					<c:set var="count" value="0" />

					<c:forEach items="${sessionScope.foodDisplayPaymentList}" var="order" varStatus="orderLoop">

						<c:forEach items="${order.foodOrderDiscountList}" var="foodItem" varStatus="foodItemLoop">
							<c:set var="count" value="${count + 1}" />
							<c:set var="modifiedFoodName" value="${foodItem.food.name}" />

							<c:forEach items="${foodItem.modifierChosenList}" var="modifierChosen" varStatus="modifierChosenLoop">
								<c:choose>
									<c:when test="${fn:contains(modifierChosen.name, 'Upsize')}">
										<c:set var="modifiedFoodName" value="${modifiedFoodName} with Upsize" />
									</c:when>

									<c:when test="${fn:contains(modifierChosen.name, 'juice')}">
										<c:set var="modifiedFoodName" value="${modifiedFoodName} juice" />
									</c:when>

									<c:otherwise>
										<c:set var="modifiedFoodName" value="${modifiedFoodName} with ${modifierChosen.name}" />
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<input type="hidden" name="item_name_<c:out value="${count}"/>" value="<c:out value="${modifiedFoodName}" />">
							<input type="hidden" name="quantity_<c:out value="${count}"/>" value="<c:out value="${foodItem.quantity}" />">
							<fmt:formatNumber value="${foodItem.price}" var="newPrice" minFractionDigits="2" />
							<input type="hidden" name="amount_<c:out value="${count}"/>" value="<c:out value="${newPrice}" />">

						</c:forEach>

					</c:forEach>

					<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" style="float: right;">

				</form>

			</c:when>
			<c:otherwise>
				<p>There are no orders to be processed.</p>
				<br>
			</c:otherwise>
		</c:choose>
		<!-- End of PayPal -->


		<jsp:include page="footer.jsp" />

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="/eureka_webservice/resources/startbootstrap-business/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/eureka_webservice/resources/startbootstrap-business/js/bootstrap.min.js"></script>

</body>

</html>
