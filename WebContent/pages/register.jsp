<!DOCTYPE html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7 ]> <html class="ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>

<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<meta name="googlebot" content="index,follow">

<!-- Title -->
<title>LunchTime &mdash; Register</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Bootstrap Core CSS -->
<link href="/eureka_webservice/resources/startbootstrap-business/css/bootstrap.css" rel="stylesheet">

<!-- Templates core CSS -->
<link href="/eureka_webservice/resources/guri-one/templates/stylesheets/application.css" rel="stylesheet">

<!-- Favicons -->
<link href="/eureka_webservice/resources/guri-one/templates/images/favicon/favicon.png" rel="shortcut icon">
<link href="/eureka_webservice/resources/guri-one/templates/images/favicon/apple-touch-icon-57-precomposed.png"
	rel="apple-touch-icon"
>
<link href="/eureka_webservice/resources/guri-one/templates/images/favicon/apple-touch-icon-72-precomposed.png"
	rel="apple-touch-icon" sizes="72x72"
>
<link href="/eureka_webservice/resources/guri-one/templates/images/favicon/apple-touch-icon-144-precomposed.png"
	rel="apple-touch-icon" sizes="114x114"
>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

<!-- Modernizr Scripts -->
<script src="/eureka_webservice/resources/guri-one/templates/javascript/vendor/modernizr-2.7.1.min.js"></script>
</head>
<body class="sign-in-up" id="to-top">

	<!-- Sign In/Sign Up section -->
	<section class="sign-in-up-section">

		<div class="container">


			<section class="sign-in-up-content">

				<div class="row">

					<div class="col-md-12">

						<h4 class="text-center">Create an account</h4>

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

						<form class="sign-in-up-form" action="/eureka_webservice/ProcessRegistrationServlet" role="form">

							<div class="form-group">
								<input class="form-control" name="name" type="text" placeholder="Your name" required>
							</div>

							<div class="form-group">
								<input class="form-control" name="email" type="email" placeholder="Enter email address" required>
							</div>

							<div class="form-group">
								<input class="form-control" type="password" name="password" placeholder="Password" required>
							</div>

							<div class="form-group">
								<input class="form-control" type="password" name="confirmPassword" placeholder="Type your password again"
									style="width: 95%;" required
								>
								<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Password Requirements"
									data-content="Min of 7 alphanumeric characters without spacing." data-placement="left"
								>
									<i class="fa fa-question-circle"></i>
								</a>
							</div>

							<div class="form-group">
								<input class="form-control" type="tel" name="contactNo" placeholder="Contact Number" data-placement="left"
									style="width: 95%;" required
								>
								<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Contact Number Requirements"
									data-content="8 digits starting with either 6, 8, 9." data-placement="left"
								>
									<i class="fa fa-question-circle"></i>
								</a>
							</div>

							<div class="form-group">
								<input class="form-control" type="text" name="companyCode" placeholder="Company Code" data-placement="left"
									style="width: 95%;" required
								>
								<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Company Code"
									data-placement="left"
									data-content="You need a company code to register your account under your company! If you are unsure, please check with your HR Department."
								>
									<i class="fa fa-question-circle"></i>
								</a>
							</div>

							<!-- Checkbox -->
							<div class="checkbox text-center form-group">
								<label>
									<input type="checkbox" name="tc" value="acknowledged" required>
									I agree to the
									<a href="#" data-toggle="modal" data-target="#modalTC">Terms & Conditions</a>
									.
								</label>
							</div>
							<!-- /.text-center -->

							<!-- Button -->
							<button class="btn btn-success btn-block" type="submit">Register</button>

							<br>
							<br>

							<!-- Sign In/Sign Up links -->
							<section class="sign-in-up-links text-center">
								<p>
									Already have an account?
									<a href="/eureka_webservice/pages/login.jsp">Sign In here</a>
								</p>
							</section>
							<!-- /.sign-in-up-links -->

						</form>
						<!-- /.sign-in-up-form -->

					</div>
					<!-- /.col-md-12 -->

				</div>
				<!-- /.row -->

			</section>
			<!-- /.sign-in-up-content -->


		</div>
		<!-- /.container -->

	</section>
	<!-- /.sign-in-up-section -->

	<!-- Terms & Condition Modal -->
	<div class="modal fade" id="modalTC" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title text-center" id="myModalLabel">LunchTime's Terms & Conditions</h4>
				</div>
				<!-- / modal header -->
				<div class="modal-body text-center">
					<iframe style="width: 100%;" src="/eureka_webservice/resources/terms.txt"></iframe>
				</div>
				<!-- / modal body -->

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
				<!-- / modal footer -->
			</div>
			<!-- / modal content -->
		</div>
	</div>
	<!-- / Terms & Condition Modal -->

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/eureka_webservice/resources/guri-one/templates/javascript/vendor/jquery-2.1.0.min.js"></script>
	<script src="/eureka_webservice/resources/guri-one/templates/javascript/bootstrap.min.js"></script>
	<script src="/eureka_webservice/resources/guri-one/templates/javascript/assets/application.js"></script>
	<!-- Popover Js -->
	<script>
		$(function() {
			$('[data-toggle="popover"]').popover();
		});
	</script>
</body>
</html>
