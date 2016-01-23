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
					Your Profile
					<small></small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="/eureka_webservice/pages/homepage.jsp">Home</a>
					</li>
					<li class="active">Profile</li>
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
			<div class="col-md-6">
				<div class="dataTable_wrapper">
					<table class="table table-striped table-bordered table-hover">
						<tr>
							<td>
								<strong>Name:</strong>
							</td>
							<td>${user.name}</td>
						</tr>
						<tr>
							<td>
								<strong>Company:</strong>
							</td>
							<td>${user.company.name}</td>
						</tr>
						<tr>
							<td>
								<strong>Delivery Point:</strong>
							</td>
							<td>${user.deliveryPoint}</td>
						</tr>
						<tr>
							<td>
								<strong>Email:</strong>
							</td>
							<td>${user.email}</td>
						</tr>
						<tr>
							<td>
								<strong>Password:</strong>
							</td>
							<td>
								**********
								<a href="#" data-toggle="modal" data-target="#modalEditPassword">Edit</a>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Contact No:</strong>
							</td>
							<td>${user.contactNo}
								<a href="#" data-toggle="modal" data-target="#modalEditContact">Edit</a>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Current spending:</strong>
							</td>
							<td>
								<fmt:formatNumber value="${user.amountOwed}" var="owedMoney" minFractionDigits="2" />
								$${owedMoney}

								<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Your Spending"
									data-content="This is how much you have spent so far! You will need to pay this at the end of the week!"
								>
									<i class="fa fa-question-circle"></i>
								</a>
							</td>
						</tr>
					</table>
				</div>
				<!-- /dataTable_wrapper -->


			</div>
			<!-- /col-md-12 -->
		</div>
		<!-- /row -->


		<jsp:include page="footer.jsp" />

	</div>
	<!-- /.container -->

	<!-- edit password Modal -->
	<div class="modal fade" id="modalEditPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form action="/eureka_webservice/ProcessSetUserPasswordServlet">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-center" id="myModalLabel">Update Password</h4>
					</div>
					<!-- / modal header -->
					<div class="modal-body">
						<table>
							<tr>
								<td>
									<strong>Old Password:</strong>
								</td>
								<td>
									<input type="password" name="oldPassword" required>
								</td>
							</tr>
							<tr>
								<td>
									<strong>New Password:</strong>
								</td>
								<td>
									<input type="password" name="newPassword" required>
									<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Password Requirements"
										data-content="Min of 7 alphanumeric characters without spacing."
									>
										<i class="fa fa-question-circle"></i>
									</a>
								</td>
							</tr>
							<tr>
								<td>
									<strong>Confirm New Password:</strong>
								</td>
								<td>
									<input type="password" name="confirmNewPassword" required>
								</td>
							</tr>
						</table>
					</div>
					<!-- / modal body -->

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-danger">Update Password</button>
					</div>
					<!-- / modal footer -->
				</div>
				<!-- / modal content -->
			</form>
		</div>
	</div>
	<!-- / edit password modal -->

	<!-- edit contact Modal -->
	<div class="modal fade" id="modalEditContact" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form action="/eureka_webservice/ProcessSetUserContactNumberServlet">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-center" id="myModalLabel">Edit Contact Number</h4>
					</div>
					<!-- / modal header -->
					<div class="modal-body text-center">
						<strong>Update your phone number: &nbsp;</strong>
						<input type="text" name="contactNo" value="${user.contactNo}" required>
						<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Contact Number Requirements"
							data-content="8 digits starting with either 6, 8, 9."
						>
							<i class="fa fa-question-circle"></i>
						</a>
					</div>
					<!-- / modal body -->

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-danger">Update Contact Number</button>
					</div>
					<!-- / modal footer -->
				</div>
				<!-- / modal content -->
			</form>
		</div>
	</div>
	<!-- / edit contact Modal -->

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
