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
	<fmt:setTimeZone value="GMT+8" />

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Order History
					<small>your previous orders</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="/eureka_webservice/pages/homepage.jsp">Home</a>
					</li>
					<li class="active">Order History</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->


		<div class="row">
			<div class="col-lg-12">

				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<c:forEach items="${sessionScope.orderHistory}" var="foodOrder" varStatus="loop">
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
							<div id="collapse${loop.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<c:if test="${foodOrder.status ne 'Submitted'}">
									Transaction ID: ${foodOrder.transactionId}
									<br>
									</c:if>
									Canteen: ${foodOrder.orderWindow.canteen.name}
									<br>
									Price:
									<fmt:formatNumber value="${foodOrder.totalPriceBeforePriceModifiers}" var="amt" minFractionDigits="2" />
									$${amt}
									<br>
									Discount:
									<fmt:formatNumber value="${foodOrder.orderWindow.priceModifierList[0].value * -1}" var="discamt"
										minFractionDigits="2"
									/>
									-$${discamt}
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


			</div>
			<!-- /col-lg-12 -->
		</div>
		<!-- /row -->


		<jsp:include page="footer.jsp" />

	</div>
	<!-- /.container -->

	<!-- Modal -->
	<div class="modal fade" id="modalCheckout" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form action="/eureka_webservice/ProcessAddNewFoodOrderServlet">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-center" id="myModalLabel">Confirmation</h4>
					</div>
					<!-- / modal header -->
					<div class="modal-body">You are going to submit your order. Are you sure you want to continue?</div>
					<!-- / modal body -->

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-danger">Confirm Checkout</button>
					</div>
					<!-- / modal footer -->
				</div>
				<!-- / modal content -->
			</form>
		</div>
	</div>
	<!-- / Modal -->

	<!-- jQuery -->
	<script src="/eureka_webservice/resources/startbootstrap-business/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/eureka_webservice/resources/startbootstrap-business/js/bootstrap.min.js"></script>

	<!-- Popover Js -->
	<script>
		$(function() {
			$('[data-toggle="popover"]').popover();
		});
	</script>

</body>

</html>

