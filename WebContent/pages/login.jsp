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

	<!-- Side nav -->
	<nav class="side-nav" role="navigation">

		<ul class="nav-side-nav">
			<li>
				<a class="tooltip-side-nav" href="#section-1" title="" data-original-title="Services" data-placement="left"></a>
			</li>
			<li>
				<a class="tooltip-side-nav" href="#section-2" title="" data-original-title="Features" data-placement="left"></a>
			</li>
			<li>
				<a class="tooltip-side-nav" href="#section-3" title="" data-original-title="Subscribe" data-placement="left"></a>
			</li>
			<li>
				<a class="tooltip-side-nav" href="#to-top" title="" data-original-title="Back" data-placement="left"></a>
			</li>
		</ul>

	</nav>
	<!-- /.side-nav -->




	<!-- Jumbotron -->
	<header class="jumbotron" role="banner">

		<div class="container">

			<div class="row">

				<div class="col-md-6">

					<!-- Logo -->
					<figure class="text-center">
						<!-- 						<img class="img-logo" src="/eureka_webservice/resources/guri-one/templates/images/logo.png" alt=""> -->
					</figure>
					<!-- /.text-center -->
					<br>
					<br>
					<br>
					<br>
					<!-- Title -->
					<h1>
						Order with
						<b style="color: #e74c3c;">LunchTime</b>
					</h1>

					<!-- Sub title -->
					<p>Ordering food from Jurong Island is no longer a hassle!</p>

					<!-- Button -->
					<!-- 					<p class="btn-app-store"> -->
					<!-- 						<a class="btn btn-danger btn-lg" href="#fakelinks"> -->
					<!-- 							<img src="resources/guri-one/templates/images/btn-app-store.png" alt=""> -->
					<!-- 						</a> -->
					<!-- 					</p> -->
					<!-- /.btn-app-store -->

				</div>
				<!-- /.col-md-6 -->

				<div class="col-md-6">

					<section class="sign-in-up-content">

						<h4 class="text-center">Sign In to your account</h4>

						<form class="sign-in-up-form" action="/eureka_webservice/ProcessLoginServlet" role="form">

							<!-- Input 1 -->
							<div class="form-group">
								<input class="form-control" id="exampleInputEmail2" name="email" type="email" placeholder="Enter email address">
							</div>
							<!-- /.form-group -->

							<!-- Input 2 -->
							<div class="form-group">
								<input class="form-control" id="exampleInputPassword1" name="password" type="password" placeholder="Password">
							</div>
							<!-- /.form-group -->

							<!-- Button -->
							<button class="btn btn-success btn-block" type="submit">Sign In</button>


							<!-- Sign In/Sign Up links -->
							<section class="sign-in-up-links text-center" style="margin-top: 15px;">
								<p style="margin: 0px;">
									<a href="/eureka_webservice/pages/reset-password.jsp">Forgot password</a>
									<span class="sep">&ndash;</span>
									<a href="/eureka_webservice/pages/register.jsp">Register</a>
								</p>
							</section>
							<!-- /.sign-in-up-links -->

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
				<!-- /.col-md-6 -->

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

	</header>
	<!-- /.jumbotron -->




	<!-- Services -->
	<section class="services-section" id="section-1">

		<div class="container">

			<div class="row">

				<div class="col-md-4 col-services">

					<!-- Icons -->
					<figure>
						<img class="img-services" src="/eureka_webservice/resources/guri-one/templates/images/icons/flag.png" alt="">
					</figure>

					<!-- Title -->
					<h4>Register</h4>

					<!-- Description -->
					<p>Register for an account with your company's code!</p>

				</div>
				<!-- /.col-md-4 -->

				<div class="col-md-4 col-services">

					<!-- Icons -->
					<figure>
						<img class="img-services" src="/eureka_webservice/resources/guri-one/templates/images/icons/search.png" alt="">
					</figure>

					<!-- Title -->
					<h4>Order</h4>

					<!-- Description -->
					<p>Browse the canteen and order your food for the day!</p>

				</div>
				<!-- /.col-md-4 -->

				<div class="col-md-4 col-services">

					<!-- Icons -->
					<figure>
						<img class="img-services img-margin"
							src="/eureka_webservice/resources/guri-one/templates/images/icons/world-map.png" alt=""
						>
					</figure>

					<!-- Title -->
					<h4>Pay</h4>

					<!-- Description -->
					<p>Eat happy already, time to pay.</p>

				</div>
				<!-- /.col-md-4 -->

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

	</section>
	<!-- /.services-section -->




	<!-- Features -->
	<section class="features-section" id="section-2">

		<div class="container">

			<div class="row">

				<div class="col-md-5 col-features text-center">

					<!-- Images showcase -->
					<figure>
						<img class="img-iPhone" src="/eureka_webservice/resources/guri-one/templates/images/iphone/1.png" alt="">
					</figure>

				</div>
				<!-- /.col-md-5 -->

				<div class="col-md-7 col-features features-content">

					<!-- Title -->
					<h3 id="title-1">Register an account</h3>

					<!-- Description -->
					<p>Register an account by clicking on "Register" button. You will need your company's code in order to
						register. Please contact your HR Department to find out more.</p>

					<p>
						<a class="btn btn-danger" href="/eureka_webservice/pages/register.jsp">Register</a>
					</p>

				</div>
				<!-- /.col-md-7 -->

			</div>
			<!-- /.row -->




			<div class="row media-screen-800">

				<div class="col-md-7 col-features features-content">

					<!-- Title -->
					<h3 id="title-2">Order your food order</h3>

					<!-- Description -->
					<p>Browse through the canteen selected for today! Make your order and submit into cart.</p>

				</div>
				<!-- /.col-md-7 -->

				<div class="col-md-5 col-features text-center">

					<!-- Images showcase -->
					<figure>
						<img class="img-iPhone margin-top margin-screen-800"
							src="/eureka_webservice/resources/guri-one/templates/images/iphone/4.png" alt=""
						>
					</figure>

				</div>
				<!-- /.col-md-5 -->

			</div>
			<!-- /.row -->




			<div class="row">

				<div class="col-md-5 col-features text-center">

					<!-- Images showcase -->
					<figure>
						<img class="img-iPhone margin-top margin-top-1"
							src="/eureka_webservice/resources/guri-one/templates/images/iphone/3.png" alt=""
						>
					</figure>

				</div>
				<!-- /.col-md-5 -->

				<div class="col-md-7 col-features features-content">

					<!-- Title -->
					<h3 id="title-3">Pay only at the end of the week</h3>

					<!-- Description -->
					<p>We know that you may not always carry your wallet around with you. So, pay only at the end of the week! You
						can make payment through Paypal. All you need is a Paypal account or your credit card number.</p>

				</div>
				<!-- /.col-md-7 -->

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

	</section>
	<!-- /.features-section -->




	<!-- Subscribe -->
	<section class="subscribe-section" id="section-3">

		<div class="container">

			<div class="row">

				<div class="col-md-12">

					<!-- Title -->
					<h2>Register now and start eating!</h2>

					<!-- Subscribe form -->
					<div class="row">

						<div class="col-md-6 col-md-offset-3 col-subscribe">

							<!-- 							<form class="subscribe-form form-inline" action="./index.html" role="form"> -->

							<!-- Input -->
							<!-- 								<div class="form-group"> -->

							<!-- 									<label class="sr-only" for="exampleInputEmail1">Company Code</label> -->
							<!-- 									<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Company Code"> -->

							<!-- 								</div> -->
							<!-- 								/.form-group -->

							<!-- Button -->
							<!-- 							<button class="btn btn-success" type="submit" style="margin: 25px;">Register!</button> -->
							<a class="btn btn-success" href="#to-top" style="margin: 25px;">Go to top!</a>

							<!-- 							</form> -->
							<!-- /.subscribe-form -->


							<section class="subscribe-description">

								<p>Contact your HR department for your company's code</p>

							</section>
							<!-- /.subscribe-description -->

						</div>
						<!-- /.col-md-6 -->

					</div>
					<!-- /.row -->

				</div>
				<!-- /.col-md-12 -->

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

	</section>
	<!-- /.subscribe-section -->




	<!-- Footer -->
	<footer class="footer-section" role="contentinfo">

		<div class="container">

			<div class="row">

				<div class="col-md-4 col-footer">

					<!-- Footer 1 -->
					<section>
						<p>
							Theme is made with
							<i class="fa fa-heart"></i>
							by Aryandhani.
						</p>
					</section>

					<script>
						var addthis_config = {
							"data_track_addressbar" : true
						};
					</script>
					<script src="http://s7.addthis.com/js/300/addthis_widget.js#pubid=ra-533f6ac03b59c72a"></script>

				</div>
				<!-- /.col-md-4 -->

				<div class="col-md-4 col-footer col-padding">

					<!-- Footer 1 -->
					<section class="text-center">
						<p>
							Be sure to read our
							<a href="#" data-toggle="modal" data-target="#modalTC">Terms & Conditions</a>
							.
						</p>
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

					<!-- Footer 1 -->
					<section>
						<p>
							<strong>Koh Bus Transport Services</strong>
							<br>
							10 Bukit Batok Cres
							<br>
							Singapore 658079
						</p>
					</section>

				</div>
				<!-- /.col-md-4 -->

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

	</footer>
	<!-- /.footer-section -->

	<!-- Terms & Condition Modal -->
	<div class="modal fade" id="modalTC" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="height: 100%;">
			<div class="modal-content" style="height: 80%;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title text-center" id="myModalLabel">LunchTime's Terms & Conditions</h4>
				</div>
				<!-- / modal header -->
				<div class="modal-body text-center" style="height: 75%;">
					<iframe style="width: 100%;
	height: 100%;" src="/eureka_webservice/resources/terms.txt"></iframe>
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
</body>
</html>
