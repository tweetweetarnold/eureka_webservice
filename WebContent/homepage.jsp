
<!DOCTYPE html>
<!-- Java imports -->
<%@page import="java.util.List"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="model.Canteen"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
<%@page import="model.Food"%>
<%@page import="java.util.ArrayList"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DaBao - Homepage</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
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
				<%
// 				JSONObject allFood = (JSONObject) session.getAttribute("allFood");
// 				ArrayList<Food> list = (ArrayList<Food>) allFood.get("allFood");
				List<Food> list = (ArrayList<Food>) session.getAttribute("allFood");
				%>

				<!-- Individual Food Item -->
				<div class="row">

					<%
// 						for(int i = 0; i < list.size(); i++){
						for(int i = 0; i < 10; i++){
							Food f = (Food) list.get(i);
					%>
					<div class="col-sm-4 col-lg-4 col-md-4">
						<div class="thumbnail">
							<img src="http://placehold.it/320x150" alt="">
							<div class="caption">
								<h4 class="pull-right">
									$<%=f.getPrice()%>
								</h4>
								<h4>
									<a href="#"><%=f.getName()%></a>
								</h4>
								<p>
									See more snippets like this online store item at
									<a target="_blank" href="http://www.bootsnipp.com">Bootsnipp - http://bootsnipp.com</a>
									.
								</p>

							</div>
							<div class="pull-right" style="margin-right: 5px;">
								<form action="/eureka_webservice/AddFoodItemToSessionServlet">
									<input type="hidden" value="<%=i %>" id="foodId" name="foodId">
									<button type="submit" class="btn btn-danger">Add to Cart</button>
								</form>
							</div>
							<br>
							<br>


						</div>
					</div>

					<%
						}
					%>


					<div class="col-sm-4 col-lg-4 col-md-4">
						<div class="thumbnail">
							<img src="http://placehold.it/320x150" alt="">
							<div class="caption">
								<h4 class="pull-right">$24.99</h4>
								<h4>
									<a href="#">First Product</a>
								</h4>
								<p>
									See more snippets like this online store item at
									<a target="_blank" href="http://www.bootsnipp.com">Bootsnipp - http://bootsnipp.com</a>
									.
								</p>
							</div>
							<div class="ratings">
								<p class="pull-right">15 reviews</p>
								<p>
									<span class="glyphicon glyphicon-star"></span>
									<span class="glyphicon glyphicon-star"></span>
									<span class="glyphicon glyphicon-star"></span>
									<span class="glyphicon glyphicon-star"></span>
									<span class="glyphicon glyphicon-star"></span>
								</p>
							</div>
						</div>
					</div>


					<!-- 					<div class="col-sm-4 col-lg-4 col-md-4"> -->
					<!-- 						<h4> -->
					<!-- 							<a href="#">Like this template?</a> -->
					<!-- 						</h4> -->
					<!-- 						<p> -->
					<!-- 							If you like this template, then check out -->
					<!-- 							<a target="_blank" href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">this tutorial</a> -->
					<!-- 							on how to build a working review system for your online store! -->
					<!-- 						</p> -->
					<!-- 						<a class="btn btn-primary" target="_blank" href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">View -->
					<!-- 							Tutorial</a> -->
					<!-- 					</div> -->

				</div>

			</div>

		</div>

	</div>
	<!-- /.container -->

	<div class="container">

		<hr>

		<!-- Footer JSP Include -->
		<jsp:include page="headerfooter/footer.jsp" />

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<!-- Arnold Test JavaScript -->
	<!-- 	<script src="resources/js/pagedirect.js"></script> -->
</body>

</html>
