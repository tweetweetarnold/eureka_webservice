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

	<!-- header -->
	<jsp:include page="header.jsp" />

	<!-- Page Content -->
	<div class="container">

		<!-- Portfolio Section -->
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">
					Foods
					<small>${sessionScope.stallName}</small>
				</h2>


				<!-- breadcrumb -->
				<ol class="breadcrumb">
					<li>
						<a href="/eureka_webservice/pages/homepage.jsp">Home</a>
					</li>
					<li class="active">${sessionScope.stallName}</li>
				</ol>
				<!-- /breadcrumb -->
			</div>

			<c:forEach items="${sessionScope.foodList}" var="food">
				<div class="col-md-3 img-portfolio">
					<div class="pull-left">${food.name}</div>
					<div class="pull-right">
						<fmt:formatNumber value="${food.price}" var="amt" minFractionDigits="2" />
						$${amt}
					</div>
					<a href="portfolio-item.html">
						<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
					</a>
				</div>
			</c:forEach>

			<div class="col-md-3 img-portfolio text-center">
				<div class="pull-left">Test</div>
				<div class="pull-right">$price</div>
				<a href="portfolio-item.html">
					<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
				</a>
			</div>

		</div>
		<!-- /.row -->


		<jsp:include page="footer.jsp" />


	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="/eureka_webservice/resources/startbootstrap-business/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/eureka_webservice/resources/startbootstrap-business/js/bootstrap.min.js"></script>

	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		});
	</script>
</body>

</html>
