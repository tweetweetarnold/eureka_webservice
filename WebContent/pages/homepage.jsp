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



		<!-- header's row -->
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
		<!-- /header row -->


		<!-- search -->
		<div class="row">
			<div class="col-lg-12">
				<form action="/eureka_webservice/LoadUserSearchFood" method="post">
					<input type="text" id="food" name="food" placeholder="Search" />
					<input type="Submit" onclick="noRedirect=false">
				</form>

			</div>
		</div>
		<!-- / search -->


		<!-- favourite food -->
		<div class="row">

			<div class="col-lg-12">
				<h2 class="page-header">Top 3 Most Ordered Food from Oasis Canteen</h2>
			</div>

			<c:forEach items="${sessionScope.mostOrderedList}" var="favFood" begin="0" end="2">
			<c:set value="${favFood.key}" var="food"/>
				<div class="col-md-4 img-portfolio">

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
					</a>

				</div>

				<!-- Modal -->
				<div class="modal fade" id="myModal${food.foodId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<form action="/eureka_webservice/ProcessAddFoodItemToOrderItemsServlet">
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
		<!-- / favourite food -->




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



	<script>
		$("#food").autocomplete("searchTest2.jsp");
	</script>


</body>

</html>
