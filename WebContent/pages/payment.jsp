<!DOCTYPE html>
<html lang="en">

<head>

<%@include file="/protect/protect.jsp"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>LunchTime</title>

<link href="/eureka_webservice/resources/img/favicon/lunchtime_favicon.png" rel="shortcut icon">

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

<script>
var noRedirect = true;
function myFunction() {
    if (noRedirect ){
         return "Please remember to checkout the items in your cart!";
    }else{
    	noRedirect = false;
    }
}
</script>
</head>

<body onbeforeunload="return myFunction()">

	<jsp:include page="header.jsp" />
	<fmt:setTimeZone value="GMT+8" />

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Payment
					<small></small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="/eureka_webservice/pages/homepage.jsp" onclick = "noRedirect=false">Home</a>
					</li>
					<li class="active">Payment</li>
				</ol>
			</div>

		</div>
		<!-- /.row -->

		<c:choose>

			<c:when test="${not empty sessionScope.paymentSuccess}">
				<c:remove var="paymentFoodOrderList" scope="session" />
			</c:when>

			<c:when test="${not empty sessionScope.error}">
				<c:remove var="paymentFoodOrderList" scope="session" />
			</c:when>

			<c:when test="${not empty sessionScope.paymentSuccess}">
				<div class="alert alert-success alert-dismissible fade in" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
					<span class="sr-only">Success:</span>
					<c:out value="${paymentSuccess}" />
				</div>
				<c:remove var="paymentSuccess" scope="session" />
			</c:when>

			<c:when test="${not empty sessionScope.error}">
				<div class="alert alert-danger alert-dismissible fade in" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					<span class="sr-only">Error:</span>
					<c:out value="${error}" />
				</div>
				<c:remove var="error" scope="session" />
			</c:when>

			<c:when test="${sessionScope.orderPeriod == null}">
				<div class="alert alert-warning alert-dismissible fade in" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					<span class="sr-only">Warning:</span>
					<c:out value="${warning}" />
				</div>
				<c:remove var="orderPeriod" scope="session" />
			</c:when>

			<c:when test="${fn:length(sessionScope.paymentFoodOrderList) eq 0}">
				<div class="alert alert-warning alert-dismissible fade in" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					<span class="sr-only">Warning:</span>
					You haven't ordered anything! Go order something!
				</div>
				<c:remove var="paymentFoodOrderList" scope="session" />
			</c:when>


		</c:choose>



		<div class="row">
			<div class="col-md-12">
				<!-- PayPal form -->
				<c:if test="${not empty sessionScope.paymentFoodOrderList}">
				<c:set var="sum" value="0" />
				<c:forEach items="${sessionScope.paymentFoodOrderList}" var="order" varStatus="orderLoop">
							<c:set var="sum" value="${sum + order.finalPrice}" />
							
				</c:forEach>
					
					<c:if test="${sum > 0 }">
					<form action="${initParam['posturl']}" method="post">
						<input type="hidden" name="upload" value="1" />
						<input type="hidden" name="return"
							value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/ProcessPaymentServlet"
						/>
						<input type="hidden" name="cmd" value="_cart" />
						<input type="hidden" name="business" value="${initParam['business']}" />
						<input type="hidden" name="currency_code" value="SGD">

						<c:set var="count" value="0" />

						<c:forEach items="${sessionScope.paymentFoodOrderList}" var="order" varStatus="orderLoop">
							<c:set var="count" value="${count + 1}" />
							<fmt:formatNumber value="${order.totalPriceBeforePriceModifiers}" var="amt" minFractionDigits="2" />

							<fmt:formatNumber value="${order.orderPeriod.priceModifierList[0].value * -1}" var="discount"
								minFractionDigits="2"
							/>

							<c:if test="${order.finalPrice > 0 }">
								<input type="hidden" name="item_name_<c:out value="${count}"/>"
									value="Food Order ID No.${order.foodOrderId}, Order Placed On:<fmt:formatDate type="both" value="${order.createDate}" />"
								>
	
								<input type="hidden" name="amount_<c:out value="${count}"/>" value="<c:out value="${amt}" />">
	
								<input type="hidden" name="discount_amount_<c:out value="${count}"/>" value="${discount}">
							</c:if>

						</c:forEach>

						<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" onclick = "noRedirect=false">

					</form>
					</c:if>
				</c:if>
				<!-- End of PayPal -->
				<div class="row"></div>
			</div>
			<!-- /col-md-12 -->
		</div>
		<!-- /row -->




		<div class="row">
			<div class="col-md-12">
				<h2 class="page-header">
					Charges
					<small>here's the list of items you are paying for</small>
					<i class="pull-right">
						<fmt:formatNumber value="${sessionScope.user.amountOwed}" var="owedPrice" minFractionDigits="2" />
						Amount Owed: $${owedPrice}
					</i>
				</h2>

				<c:forEach items="${sessionScope.paymentFoodOrderList}" var="foodOrder" varStatus="loop">

					<div class="panel panel-default">

						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${loop.index}"
									aria-expanded="true" aria-controls="collapse${loop.index}"
								>
									Order ID: ${foodOrder.foodOrderId} &mdash;
									<fmt:formatDate type="both" value="${foodOrder.createDate}" />
									<i class="pull-right">${foodOrder.status}</i>
								</a>
							</h4>
						</div>

						<div id="collapse${loop.index}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
								Canteen: ${foodOrder.orderPeriod.canteen.name}
								<br>
								Price:
								<fmt:formatNumber value="${foodOrder.totalPriceBeforePriceModifiers}" var="amt" minFractionDigits="2" />
								$${amt}
								<br>
								Discount:
								<fmt:formatNumber value="${foodOrder.totalPriceBeforePriceModifiers - foodOrder.finalPrice}" var="discount"
									minFractionDigits="2"
								/>
								$${discount}
								<br>
								Payable:
								<fmt:formatNumber value="${foodOrder.finalPrice}" var="payable" minFractionDigits="2" />
								<c:choose>
								<c:when test="${foodOrder.finalPrice < 0 }">
									$0.00
								</c:when>
								<c:otherwise>
								$${payable}
								</c:otherwise>
								</c:choose>
								<br>
								<br>

								<table class="table table-striped">
									<thead>
										<tr>
											<th>Stall</th>
											<th>Food</th>
											<th>Add-On(s)</th>
											<th class="text-center">Quantity</th>
											<th class="text-center">Price</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${foodOrder.foodOrderList}" var="foodOrderItem">
											<tr>
												<td>${foodOrderItem.food.stall.name}</td>
												<td>${foodOrderItem.food.name}</td>
												<td>
													<c:forEach items="${foodOrderItem.modifierChosenList}" var="modifierChosen" varStatus="innerLoop">
																${modifierChosen.name} 
																<c:if test="${!innerLoop.last}">, </c:if>
													</c:forEach>
												</td>
												<td class="text-center">${foodOrderItem.quantity}</td>
												<td class="text-center">
													<fmt:formatNumber value="${foodOrderItem.price}" var="amt2" minFractionDigits="2" />
													$${amt2}
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- /panel -->

				</c:forEach>

			</div>
			<!-- /col-md-12 -->
		</div>
		<!-- /row -->




		<jsp:include page="footer.jsp" />

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="/eureka_webservice/resources/startbootstrap-business/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/eureka_webservice/resources/startbootstrap-business/js/bootstrap.min.js"></script>

</body>

</html>
