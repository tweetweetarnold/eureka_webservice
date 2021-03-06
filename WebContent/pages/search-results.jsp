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

<link href="/eureka_webservice/resources/img/favicon/lunchtime_favicon.png" rel="shortcut icon">

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

	<!-- Page Content -->
	<div class="container">

		<!-- Portfolio Section -->
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">Search Results</h2>


				<!-- breadcrumb -->
				<ol class="breadcrumb">
					<li>
						<a href="/eureka_webservice/pages/homepage.jsp" onclick="noRedirect=false">Home</a>
					</li>
					<li class="active">Search</li>
				</ol>
				<!-- /breadcrumb -->
			</div>
		</div>

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
			<!--/ col-lg-12 -->
		</div>
		<!-- / row -->

		<div class="row">
			<c:forEach items="${sessionScope.results}" var="food">
				<div class="col-md-3 img-portfolio">

					<a href="portfolio-item.html" data-toggle="modal" data-target="#myModal${food.foodId}">
						<b class="pull-left">${food.name}</b>
						<b class="pull-right">
							<fmt:formatNumber value="${food.price}" var="amt" minFractionDigits="2" />
							$${amt}
						</b>
						<img class="img-responsive img-portfolio img-hover" src="${food.imageDirectory}"
							onerror="this.src='http://res.cloudinary.com/dmeln4k8n/image/upload/c_pad,h_169,w_263/v1455951761/default/img-error.jpg'"
							alt="http://res.cloudinary.com/dmeln4k8n/image/upload/c_pad,h_169,w_263/v1455951761/default/img-error.jpg"
							style="width: 263px;
	height: 169px;"
						>
						<!--"http://placehold.it/700x450  -->
					</a>

				</div>

				<!-- Modal -->
				<div class="modal fade" id="myModal${food.foodId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<form action="/eureka_webservice/ProcessAddFoodItemToOrderItemsServlet2">
							<input type="hidden" name="foodId" value="${food.foodId}">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title text-center" id="myModalLabel">${food.name}'s&nbsp;Add-On(s)</h4>
								</div>
								<!-- / modal header -->
								<div class="modal-body">
									<table class="table table-striped">
										<thead>
											<tr>
												<th class="text-center">S/N</th>
												<th>Add-On</th>
												<th>Price</th>
												<th class="text-center">Add</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${not empty food.modifierSectionList}">
													<!-- / loop for modifierSection -->
													<c:forEach varStatus="loop" items="${food.modifierSectionList}" var="modifierSection">
														<tr>
															<td>${modifierSection.categoryName}</td>
														</tr>


														<!-- / if checkbox -->
														<c:if test="${modifierSection.displayType =='c'}">
															<c:forEach varStatus="loop" items="${modifierSection.modifierList}" var="modifier">
																<tr>
																	<td style="text-align: center;">${loop.index+1}</td>
																	<td style="text-align: left;">${modifier.name}</td>
																	<td style="text-align: left;">
																		<fmt:formatNumber value="${modifier.price}" var="modPrice" minFractionDigits="2" />
																		+ $ ${modPrice}
																	</td>
																	<td class="text-center">
																		<input type="checkbox" value="true" name="${modifier.name}">
																	</td>
																</tr>
															</c:forEach>
														</c:if>

														<c:if test="${modifierSection.displayType =='d'}">
															<tr>
																<td>
																	<select class="form-control" name="modifierDropdown" required>
																		<c:forEach varStatus="loop" items="${modifierSection.modifierList}" var="modifier">
																			<option value="${modifier.name}" name="${modifier.name}">${modifier.name}+$
																				${modifier.price}</option>
																		</c:forEach>
																	</select>
																</td>
															</tr>
														</c:if>



													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="4" style="text-align: center;">No add-ons available</td>
													</tr>
												</c:otherwise>
											</c:choose>

										</tbody>

									</table>


									<div>
										Quantity:
										<select name="quantity" class="form-control" style="display: inline;
	width: 10%;">
											<c:forEach begin="1" end="10" varStatus="count">
												<option value="${count.index}">${count.index}</option>
											</c:forEach>
										</select>
									</div>

								</div>
								<!-- / modal body -->
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-danger" onclick="noRedirect=false">Add to cart</button>
								</div>
								<!-- / modal footer -->
							</div>
							<!-- / modal content -->
						</form>
					</div>
				</div>
				<!-- / Modal -->

			</c:forEach>



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
