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
					Contact Us
					<small>talk to us</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="/eureka_webservice/pages/homepage.jsp">Home</a>
					</li>
					<li class="active">Contact Us</li>
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

		<div class="row">
			<div class="col-md-8">
				<p>This platform is for users, like yourself, to provide feedback to us (the administrator) about how you think
					things can be improved! Thank you for your feedback!</p>
				<br>

				<div class="dataTable_wrapper">
					<form method="post" action="/eureka_webservice/ProcessSendFeedbackServlet">
						<table class="table table-striped table-bordered table-hover">
							<tr>
								<td>
									<strong>Recipient:</strong>
								</td>
								<td>Administrator (SMU Development Team)</td>
							</tr>
							<tr>
								<td>
									<strong>Your name:</strong>
								</td>
								<td>${user.name}</td>
							</tr>
							<tr>
								<td>
									<strong>Your email:</strong>
								</td>
								<td>${user.email}</td>
							</tr>
							<tr>
								<td>
									<strong>Your message:</strong>
								</td>
								<td>
									<textarea name="message" rows="10" cols="80"></textarea>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<button class="btn btn-danger btn-lg" type="submit">Send Message!</button>
								</td>
							</tr>
						</table>
					</form>

				</div>
				<!-- /dataTable_wrapper -->


			</div>
			<!-- /col-md-12 -->
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
