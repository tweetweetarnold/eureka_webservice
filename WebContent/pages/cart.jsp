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
								<th>Price</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.myFoodOrderItems}" var="foodOrderItem" varStatus="loop">
								<tr>
									<td>${foodOrderItem.food.name}</td>
									<td>
										<c:forEach items="${foodOrderItem.modifierChosenList}" var="modifierChosen">
									${modifierChosen.name}, 
									</c:forEach>
									</td>
									<td>
										<c:set value="${overallPrice + foodOrderItem.price}" var="overallPrice" />
										<fmt:formatNumber value="${foodOrderItem.price}" var="amt" minFractionDigits="2" />
										$${amt}
									</td>
									<td>
										<form action="/eureka_webservice/ProcessDeleteFoodItemFromOrderItemsServlet">
											<input type="hidden" name="foodPosition" value="${loop.index}">
											<button type="submit" class="btn btn-link btn-xs">
												<i class="fa fa-trash-o fa-2x"></i>
											</button>
										</form>
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
				<h2>
					Total Payable:
					<fmt:formatNumber value="${overallPrice}" var="overallPrice2" minFractionDigits="2" />
					$${overallPrice2}
				</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4 pull-right">
				<form action="/eureka_webservice/ProcessAddNewFoodOrderServlet">
					<button class="btn btn-success btn-lg btn-block">Submit my order</button>
				</form>
			</div>

		</div>

		<!-- 		<!-- Project One -->
		<!-- 		<div class="row"> -->
		<!-- 			<div class="col-md-7"> -->
		<!-- 				<a href="portfolio-item.html"> -->
		<!-- 					<img class="img-responsive img-hover" src="http://placehold.it/700x300" alt=""> -->
		<!-- 				</a> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-5"> -->
		<!-- 				<h3>Project One</h3> -->
		<!-- 				<h4>Subheading</h4> -->
		<!-- 				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laudantium veniam exercitationem expedita laborum -->
		<!-- 					at voluptate. Labore, voluptates totam at aut nemo deserunt rem magni pariatur quos perspiciatis atque eveniet -->
		<!-- 					unde.</p> -->
		<!-- 				<a class="btn btn-primary" href="portfolio-item.html"> View Project </a> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<!-- 		<!-- /.row -->

		<!-- 		<hr> -->



		<!-- 		<!-- Project Two -->
		<!-- 		<div class="row"> -->
		<!-- 			<div class="col-md-7"> -->
		<!-- 				<a href="portfolio-item.html"> -->
		<!-- 					<img class="img-responsive img-hover" src="http://placehold.it/700x300" alt=""> -->
		<!-- 				</a> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-5"> -->
		<!-- 				<h3>Project Two</h3> -->
		<!-- 				<h4>Subheading</h4> -->
		<!-- 				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ut, odit velit cumque vero doloremque repellendus -->
		<!-- 					distinctio maiores rem expedita a nam vitae modi quidem similique ducimus! Velit, esse totam tempore.</p> -->
		<!-- 				<a class="btn btn-primary" href="portfolio-item.html"> View Project </a> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<!-- /.row -->




		<!-- <hr> -->

		<!-- Pagination -->
		<!-- 		<div class="row text-center"> -->
		<!-- 			<div class="col-lg-12"> -->
		<!-- 				<ul class="pagination"> -->
		<!-- 					<li> -->
		<!-- 						<a href="#">&laquo;</a> -->
		<!-- 					</li> -->
		<!-- 					<li class="active"> -->
		<!-- 						<a href="#">1</a> -->
		<!-- 					</li> -->
		<!-- 					<li> -->
		<!-- 						<a href="#">2</a> -->
		<!-- 					</li> -->
		<!-- 					<li> -->
		<!-- 						<a href="#">3</a> -->
		<!-- 					</li> -->
		<!-- 					<li> -->
		<!-- 						<a href="#">4</a> -->
		<!-- 					</li> -->
		<!-- 					<li> -->
		<!-- 						<a href="#">5</a> -->
		<!-- 					</li> -->
		<!-- 					<li> -->
		<!-- 						<a href="#">&raquo;</a> -->
		<!-- 					</li> -->
		<!-- 				</ul> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<!-- /.row -->

		<jsp:include page="footer.jsp" />

	</div>
	<!-- /.container -->

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
