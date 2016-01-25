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

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Cart
					<small>what you ordered</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="/eureka_webservice/pages/homepage.jsp">Home</a>
					</li>
					<li class="active">Cart</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-12">
				<!-- Success message handling -->
				<c:if test="${not empty sessionScope.success}">
					<div class="alert alert-success alert-dismissible fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
						<span class="sr-only">Success:</span>
						${sessionScope.success}
					</div>
					<c:remove var="success" scope="session" />
				</c:if>
				<!-- / success message handling -->

				<!-- Error message handling -->
				<c:if test="${not empty sessionScope.error}">
					<div class="alert alert-danger alert-dismissible fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						<span class="sr-only">Error:</span>
						${sessionScope.error}
					</div>
					<c:remove var="error" scope="session" />
				</c:if>
				<!-- / error message handling -->
			</div>
		</div>

		<c:set var="overallPrice" value="0" />
		<div class="row">
			<div class="col-md-12">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>Food</th>
								<th>Add-On(s)</th>
								<th>Qty</th>
								<th>Price</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.myFoodOrderItems}" var="foodOrderItem" varStatus="loop">
								<tr>
									<td>${foodOrderItem.food.name}</td>
									<td>
										<c:forEach items="${foodOrderItem.modifierChosenList}" var="modifierChosen" varStatus="innerLoop">
											${modifierChosen.name}
											<c:if test="${!innerLoop.last}">, </c:if>
										</c:forEach>
									</td>
									<td>${foodOrderItem.quantity}</td>
									<td>
										<c:set value="${overallPrice + foodOrderItem.price}" var="overallPrice" />
										<fmt:formatNumber value="${foodOrderItem.price}" var="amt" minFractionDigits="2" />
										$${amt}
									</td>
									<td>
										<button type="button" class="btn btn-link btn-xs" data-toggle="modal" data-target="#modalDelete${loop.index}">
											<i class="fa fa-trash-o fa-2x"></i>
										</button>

										<!-- Modal delete -->
										<div class="modal fade" id="modalDelete${loop.index}" tabindex="-1" role="dialog"
											aria-labelledby="myModalLabel"
										>
											<div class="modal-dialog" role="document">
												<form action="/eureka_webservice/ProcessDeleteFoodItemFromOrderItemsServlet">
													<input type="hidden" name="foodPosition" value="${loop.index}">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
															<h4 class="modal-title text-center" id="myModalLabel">Confirmation</h4>
														</div>
														<!-- / modal header -->
														<div class="modal-body">You are removing a food item from cart. Are you sure you want to continue?</div>
														<!-- / modal body -->

														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">No, keep my food</button>
															<button type="submit" class="btn btn-danger">Yes, remove the food</button>
														</div>
														<!-- / modal footer -->
													</div>
													<!-- / modal content -->
												</form>
											</div>
										</div>
										<!-- / Modal delete -->
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /dataTable_wrapper -->


			</div>
			<!-- /col-md-12 -->
		</div>
		<!-- /row -->

		<div class="row">
			<div class="col-md-4 pull-right">

				<table class="table">
					<tr>
						<td>
							<h4>
								<i>Order Price:</i>
							</h4>
						</td>
						<td>
							<h4>
								<fmt:formatNumber value="${overallPrice}" var="overallPrice2" minFractionDigits="2" />
								<i>$${overallPrice2}</i>
							</h4>
						</td>
					</tr>

					<tr>
						<td>
							<h4>
								<i>Discount:</i>
							</h4>
						</td>
						<td>
							<h4>
								<fmt:formatNumber value="${sessionScope.orderWindow.priceModifierList[0].value * -1}" var="discount"
									minFractionDigits="2"
								/>
								-
								<i>$${discount}</i>
							</h4>
						</td>
					</tr>

					<tr>
						<td>
							<h2>
								<i>Amount Payable:</i>
							</h2>
						</td>
						<td>
							<h2>
								<i>
									<fmt:formatNumber value="${overallPrice +  sessionScope.orderWindow.priceModifierList[0].value}"
										var="amountPayable" minFractionDigits="2"
									/>
									<c:choose>
										<c:when test="${overallPrice +  sessionScope.orderWindow.priceModifierList[0].value > 0}">
									$${amountPayable}
									</c:when>
										<c:otherwise>
									$0.00
									</c:otherwise>
									</c:choose>
								</i>
							</h2>
						</td>
					</tr>
				</table>

			</div>
		</div>

		<div class="row">
			<div class="col-md-4 pull-right">
				<form action="/eureka_webservice/ProcessAddNewFoodOrderServlet">
					<button type="button" class="btn btn-success btn-lg btn-block" data-toggle="modal" data-target="#modalCheckout">Checkout</button>
				</form>
			</div>

		</div>

		<jsp:include page="footer.jsp" />

	</div>
	<!-- /.container -->

	<!-- Modal checkout -->
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
					<div class="modal-body">
						<p>You are going to submit your order. Are you sure you want to continue?</p>
						<p>
							<strong>WARNING: YOU CAN ONLY SUBMIT 1 ORDER PER SESSION.</strong>
						</p>
					</div>
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
	<!-- / Modal checkout -->






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
