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

<link rel="stylesheet" type="text/css" href="/eureka_webservice/resources/css/dabao/jquery.autocomplete.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script src="/eureka_webservice/resources/js/dabao/jquery.autocomplete.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script>
	var noRedirect = true;
	function myFunction() {
		if (noRedirect) {
			return "Please remember to checkout the items in your cart!";
		} else {
			noRedirect = false;
		}
	}
</script>





</head>

<body onbeforeunload="return myFunction()">


	<!-- header -->
	<jsp:include page="header.jsp" />
	<fmt:setTimeZone value="GMT+8" />



	<!-- Page Content -->
	<div class="container">



		<!-- Arnold's row -->
		<div class="row">
			<div class="col-lg-8">

				<h3>
					Today's canteen is ${sessionScope.orderPeriod.canteen.name}
					<br>
					Order your food before
					<fmt:formatDate pattern="E, dd-MMM-yyyy HH:mm:ss" type="both" value="${sessionScope.orderPeriod.endDateFormatted}" />
				</h3>

			</div>
			<!-- col-lg-8 -->
		</div>
		<!-- /row -->



		<div class="row">
			<div class="col-lg-12">
				<form action="/eureka_webservice/LoadUserSearchFood" method="post">
					<input type="text" id="food" name="food" placeholder="Search" />
					<input type="Submit">
				</form>

			</div>
		</div>




		<!-- Portfolio Section -->
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">
					Stalls
					<small>${orderPeriod.canteen.name}</small>
				</h2>
			</div>


			<c:forEach items="${sessionScope.canteenList}" var="canteen">
				<c:forEach items="${canteen.stallList}" var="stall">
					<c:if test="${stall.status == 'Active'}">
						<div class="col-md-4 col-sm-6">
							<a href="/eureka_webservice/LoadStallFoodServlet?stallId=${stall.stallId}" onclick="noRedirect=false">
								<b style="font-size: large;">${stall.name}</b>
								<img class="img-responsive img-portfolio img-hover"
									onerror="this.src='http://res.cloudinary.com/dmeln4k8n/image/upload/c_pad,h_231,w_173/v1455951761/default/img-error.jpg'"
									src="${stall.imageDirectory}"
									alt="http://res.cloudinary.com/dmeln4k8n/image/upload/c_pad,h_231,w_173/v1455951761/default/img-error.jpg"
								>
								<!-- this.src='http://placehold.it/700x450' -->
							</a>
						</div>
					</c:if>
				</c:forEach>
			</c:forEach>

		</div>




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


	<script>
		$("#food").autocomplete("searchTest2.jsp");
	</script>


</body>

</html>
