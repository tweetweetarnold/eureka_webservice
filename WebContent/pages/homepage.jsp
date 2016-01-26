<!DOCTYPE html>
<html lang="en">

<head>

<%@include file="/protect/protectHomepage.jsp"%>

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
	<fmt:setTimeZone value="GMT+8" />



	<!-- Page Content -->
	<div class="container">



		<!-- Arnold's row -->
		<div class="row">
			<div class="col-lg-8">
				<h1 class="page-header">Homepage</h1>
				<table class="table table-striped table-bordered table-hover">
					<tr>
						<td>
							<strong>Company:</strong>
						</td>
						<td>${sessionScope.user.company.name}</td>
					</tr>
					<tr>
						<td>
							<strong>Canteen:</strong>
						</td>
						<td>${sessionScope.orderWindow.canteen.name}</td>
					</tr>
					<tr>
						<td>
							<strong>Start:</strong>
						</td>
						<td>
							<fmt:formatDate pattern="E, dd-MMM-yyyy HH:mm:ss" type="both"
								value="${sessionScope.orderWindow.startDateFormatted}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<strong>End:</strong>
						</td>
						<td>
							<fmt:formatDate pattern="E, dd-MMM-yyyy HH:mm:ss" type="both"
								value="${sessionScope.orderWindow.endDateFormatted}"
							/>
						</td>
					</tr>
					<tr>
						<td>
							<strong>Comments from Administrator:</strong>
						</td>
						<td>${sessionScope.orderWindow.remarks}</td>
					</tr>
				</table>
			</div>
			<!-- col-lg-12 -->
		</div>
		<!-- /row -->


		<!-- Marketing Icons Section -->
		<!-- 		<div class="row"> -->
		<!-- 			<div class="col-lg-12"> -->
		<!-- 				<h1 class="page-header">Welcome to Modern Business</h1> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-4"> -->
		<!-- 				<div class="panel panel-default"> -->
		<!-- 					<div class="panel-heading"> -->
		<!-- 						<h4> -->
		<!-- 							<i class="fa fa-fw fa-check"></i> -->
		<!-- 							Bootstrap v3.2.0 -->
		<!-- 						</h4> -->
		<!-- 					</div> -->
		<!-- 					<div class="panel-body"> -->
		<!-- 						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla aspernatur in -->
		<!-- 							alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus eveniet incidunt dicta nostrum quod?</p> -->
		<!-- 						<a href="#" class="btn btn-default">Learn More</a> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-4"> -->
		<!-- 				<div class="panel panel-default"> -->
		<!-- 					<div class="panel-heading"> -->
		<!-- 						<h4> -->
		<!-- 							<i class="fa fa-fw fa-gift"></i> -->
		<!-- 							Free &amp; Open Source -->
		<!-- 						</h4> -->
		<!-- 					</div> -->
		<!-- 					<div class="panel-body"> -->
		<!-- 						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla aspernatur in -->
		<!-- 							alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus eveniet incidunt dicta nostrum quod?</p> -->
		<!-- 						<a href="#" class="btn btn-default">Learn More</a> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-4"> -->
		<!-- 				<div class="panel panel-default"> -->
		<!-- 					<div class="panel-heading"> -->
		<!-- 						<h4> -->
		<!-- 							<i class="fa fa-fw fa-compass"></i> -->
		<!-- 							Easy to Use -->
		<!-- 						</h4> -->
		<!-- 					</div> -->
		<!-- 					<div class="panel-body"> -->
		<!-- 						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla aspernatur in -->
		<!-- 							alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus eveniet incidunt dicta nostrum quod?</p> -->
		<!-- 						<a href="#" class="btn btn-default">Learn More</a> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<!-- /.row -->

		<!-- Portfolio Section -->
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">
					Stalls
					<small>${orderWindow.canteen.name}</small>
				</h2>
			</div>

			<c:forEach items="${sessionScope.canteenList}" var="canteen">
				<c:forEach items="${canteen.stallList}" var="stall">
					<c:if test="${stall.status == 'Ok'}">
						<div class="col-md-4 col-sm-6">
							<a href="/eureka_webservice/LoadStallFoodServlet?stallId=${stall.stallId}">
								<b style="font-size: large;">${stall.name}</b>
								<img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="">
							</a>
						</div>
					</c:if>
				</c:forEach>
			</c:forEach>

		</div>
		<!-- /.row -->

		<!-- Features Section -->
		<!-- 		<div class="row"> -->
		<!-- 			<div class="col-lg-12"> -->
		<!-- 				<h2 class="page-header">Modern Business Features</h2> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-6"> -->
		<!-- 				<p>The Modern Business template by Start Bootstrap includes:</p> -->
		<!-- 				<ul> -->
		<!-- 					<li> -->
		<!-- 						<strong>Bootstrap v3.2.0</strong> -->
		<!-- 					</li> -->
		<!-- 					<li>jQuery v1.11.0</li> -->
		<!-- 					<li>Font Awesome v4.1.0</li> -->
		<!-- 					<li>Working PHP contact form with validation</li> -->
		<!-- 					<li>Unstyled page elements for easy customization</li> -->
		<!-- 					<li>17 HTML pages</li> -->
		<!-- 				</ul> -->
		<!-- 				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis, omnis doloremque non cum id -->
		<!-- 					reprehenderit, quisquam totam aspernatur tempora minima unde aliquid ea culpa sunt. Reiciendis quia dolorum ducimus -->
		<!-- 					unde.</p> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-6"> -->
		<!-- 				<img class="img-responsive" src="http://placehold.it/700x450" alt=""> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<!-- /.row -->


		<!-- Call to Action Section -->
		<!-- 		<div class="well"> -->
		<!-- 			<div class="row"> -->
		<!-- 				<div class="col-md-8"> -->
		<!-- 					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestias, expedita, saepe, vero rerum deleniti -->
		<!-- 						beatae veniam harum neque nemo praesentium cum alias asperiores commodi.</p> -->
		<!-- 				</div> -->
		<!-- 				<div class="col-md-4"> -->
		<!-- 					<a class="btn btn-lg btn-default btn-block" href="#">Call to Action</a> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->



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
