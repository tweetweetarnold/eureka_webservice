
<!DOCTYPE html>
<%@include file="protect.jsp"%>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>DABAO</title>

<!-- JavaScript imports -->
<script src="resources/js/jquery-1.11.3.js"></script>

<!-- <script src="resources/js/jquery-1.11.3.min.js"></script> -->
<!-- <script src="resources/js/ie-emulation-modes-warning.js"></script> -->
<!-- <script src="resources/js/dabao/dabao.js"></script> -->

<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/dabao/transition.js"></script>
<script src="resources/js/dabao/carousel.js"></script>
<script src="resources/js/dabao/collapse.js"></script>

<!-- Library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Bootstrap core CSS -->
<link href="resources/css/dabao/dabao.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/dabao/carousel.css" rel="stylesheet">
<link href="resources/css/dabao/starter-template.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/header.jsp" />


	<div class="container">

		<!-- Carousel Holder -->
		<div class="row carousel-holder">
			<div class="col-md-12">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img class="slide-image" src="resources/img/home-carousel/1.jpg" alt="">
						</div>
						<div class="item">
							<img class="slide-image" src="resources/img/home-carousel/2.jpg" alt="">
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
		<!-- End Carousel Holder -->


		<!-- JSTL get variables -->
		<c:set value="${sessionScope.canteenList}" var="canteenList" />

		<!-- Success message handling -->
		<c:if test="${not empty sessionScope.success}">
			<div class="alert alert-success" role="alert">
				<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
				<span class="sr-only">Success:</span>
				<c:out value="${success}" />
			</div>
			<c:remove var="success" scope="session" />
		</c:if>

		<!-- Error message handling -->
		<c:if test="${not empty sessionScope.error}">
			<div class="alert alert-danger" role="alert">
				<b>Error!</b>
				<br>
				<c:out value="${error}" />
			</div>
			<c:remove var="error" scope="session" />
		</c:if>

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

			<!-- Per canteen -->
			<c:forEach items="${canteenList}" varStatus="canteenLoop" var="canteen">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="heading">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${canteenLoop.index}"
								aria-expanded="true" aria-controls="collapse"
							>
								<c:out value="${canteen.name}" />
							</a>
						</h4>
					</div>
					<div id="collapse${canteenLoop.index}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby=""heading"">
						<div class="panel-body">
							<div class="row">

								<c:forEach items="${canteen.stallList}" var="stall" varStatus="stallLoop">
									<div class="thumbnail">
										<img src="${stall.imageDirectory}" onerror="onImageError(${stallLoop.index})" id="image${stallLoop.index}">
										<div class="caption">
											<form method="post" action="RenderStallFoodListServlet?stallId=${stall.stallId}">
												<button class="button" style="white-space: normal;">
													<c:out value="${stall.name}" />
												</button>
											</form>
										</div>
									</div>
								</c:forEach>

							</div>
						</div>
					</div>
				</div>

			</c:forEach>

		</div>

	</div>
	<!-- close container -->


	<!-- Bootstrap core JavaScript
        ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<!-- 	<script src="../../dist/js/bootstrap.min.js"></script> -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- 	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->



	<!-- Google Analytics -->
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

	<!-- no image error -->
	<script src="resources/js/myerrors.js"></script>


</body>
</html>
