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
<title>LunchTime &mdash; Login</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link href="/eureka_webservice/resources/css/bootstrap.min.css" rel="stylesheet">
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

<body class="index" id="to-top">


	<!-- Jumbotron -->
	<header class="jumbotron" role="banner">

		<div class="container">

			<div class="row">


				<div class="col-md-12" style="padding-left: 25%;
	padding-right: 25%;">

					<section class="sign-in-up-content">

						<h4 class="text-center">Admin Login</h4>

						<form method="post" class="sign-in-up-form" action="/eureka_webservice/ProcessAdminLoginServlet" role="form">

							<!-- Input 1 -->
							<div class="form-group">
								<input class="form-control" name="adminUsername" type="text" placeholder="Username" required>
							</div>
							<!-- /.form-group -->

							<!-- Input 2 -->
							<div class="form-group">
								<input class="form-control" name="adminPassword" type="password" placeholder="Password" required>
							</div>
							<!-- /.form-group -->

							<!-- Button -->
							<button class="btn btn-success btn-block" type="submit">Sign In</button>
							<br>

							<!-- Error message handling -->
							<c:if test="${not empty sessionScope.error}">
								<div class="alert alert-danger" style="margin-top: 10px;
	padding: 10px;">
									<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
									<span class="sr-only">Error:</span>
									<c:out value="${error}" />
								</div>
								<c:remove var="error" scope="session" />
							</c:if>

							<!-- Success message handling -->
							<c:if test="${not empty sessionScope.success}">
								<div class="alert alert-success" style="margin-top: 10px;
	padding: 10px;">
									<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
									<span class="sr-only">Success:</span>
									<c:out value="${success}" />
								</div>
								<c:remove var="success" scope="session" />
							</c:if>

							<c:remove var="email" scope="session" />




						</form>
						<!-- /.sign-in-up-form -->

					</section>
					<!-- /.sign-in-up-content -->

				</div>
				<!-- /.col-md-12 -->

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

	</header>
	<!-- /.jumbotron -->

	<!-- Footer -->
	<footer class="footer-section" role="contentinfo">

		<div class="container">

			<div class="row">


				<div class="col-md-4 col-footer col-padding">

					<!-- Footer 1 -->
					<section class="text-center">
						<p>This is for Admin login only.</p>
					</section>

					<!-- Social media links -->
					<!-- 					<ul class="social-media-links"> -->

					<!-- 						<li> -->
					<!-- 							<a class="fa fa-twitter tw" href="#fakelinks"></a> -->
					<!-- 						</li> -->
					<!-- 						<li> -->
					<!-- 							<a class="fa fa-facebook fb" href="#fakelinks"></a> -->
					<!-- 						</li> -->
					<!-- 						<li> -->
					<!-- 							<a class="fa fa-pinterest pn" href="#fakelinks"></a> -->
					<!-- 						</li> -->

					<!-- 					</ul> -->
					<!-- /.social-media-links -->

				</div>
				<!-- /.col-md-4 -->

				<div class="col-md-4 col-footer">

					</section>

				</div>
				<!-- /.col-md-4 -->

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

	</footer>
	<!-- /.footer-section -->

	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/eureka_webservice/resources/guri-one/templates/javascript/vendor/jquery-2.1.0.min.js"></script>
	<script src="/eureka_webservice/resources/guri-one/templates/javascript/bootstrap.min.js"></script>
	<script src="/eureka_webservice/resources/guri-one/templates/javascript/assets/application.js"></script>
</body>
</html>
