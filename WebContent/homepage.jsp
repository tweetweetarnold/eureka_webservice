
<!DOCTYPE html>
<!-- Java imports -->
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="java.text.*"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DaBao - Homepage</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/sticky-footer.css" rel="stylesheet">
<link href="resources/css/shop-homepage.css" rel="stylesheet">

</head>

<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/header.jsp" />

	<!-- Page Content -->
	<div class="container">
		<h1>Homepage</h1>

		<div id="content"></div>

		<div class="row">
			<div class="col-md-3">
				<p class="lead"></p>
				<div class="list-group">
					<a href="#" class="list-group-item">Food Court 1</a>
					<a href="#" class="list-group-item">Food Court 2</a>
					<a href="#" class="list-group-item">Food Court 3</a>
					<a href="#" class="list-group-item">Food Court 4</a>
				</div>
			</div>

			<div class="col-md-9">

				<!-- Carousel Holder -->
				<div class="row carousel-holder">
					<div class="col-md-12">
						<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
								<li data-target="#carousel-example-generic" data-slide-to="1"></li>
								<li data-target="#carousel-example-generic" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner">
								<div class="item active">
									<img class="slide-image" src="assets/images/img-friedrice.jpg" alt="">
								</div>
								<div class="item">
									<img class="slide-image" src="assets/images/img-noodles.jpg" alt="">
								</div>
								<div class="item">
									<img class="slide-image" src="assets/images/img-spaghetti.jpg" alt="">
								</div>
							</div>
							<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
								<span class="glyphicon glyphicon-chevron-left"></span>
							</a>
							<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</a>
						</div>
					</div>
				</div>


				<!-- Individual Food Item -->
				<div class="row">

					<c:forEach begin="0" end="10" var="food" items="${sessionScope.allFood}" varStatus="loop">
						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="http://placehold.it/320x150" alt="">
								<div class="caption">
									<h4 class="pull-right">
										$
										<c:out value="${food.priceString}" />
									</h4>
									<h4>
										<a href="#">
											<c:out value="${food.name}" />
										</a>
									</h4>
									<p>
										<c:out value="${food.description}" />
									</p>

								</div>
								<div class="pull-right" style="margin-right: 5px;">
									<form action="AddFoodItemToSessionServlet">
										<input type="hidden" value='<c:out value="${loop.index}"/>' id="foodId" name="foodId">
										<button type="submit" class="btn btn-danger">Add to Cart</button>
									</form>
								</div>
								<br>
								<br>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>

		</div>

	</div>
	<!-- /.container -->

	<div class="container">

		<hr>

	</div>
	<!-- /.container -->

	<!-- Footer JSP Include -->
	<jsp:include page="headerfooter/footer.jsp" />

	<!-- jQuery -->
	<script src="resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<!-- Arnold Test JavaScript -->
	<!-- 	<script src="resources/js/pagedirect.js"></script> -->

	<!-- 	Google Analytics -->
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-68676403-1', 'auto');
		ga('send', 'pageview');
	</script>
</body>

</html>
