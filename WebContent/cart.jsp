<!DOCTYPE html>

<html lang="en">
<%@include file="protect/protect.jsp"%>
<head>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<meta name="description" content="">
<meta name="author" content="">

<title>LunchTime</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="resources/js/jquery-1.11.3.js"></script>
<script src="resources/js/dabao/dabao.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<link href="resources/css/dabao/dabao.css" rel="stylesheet">
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
		<div class="row center">
			<div class="col-xs-8 center">
				<div class="panel panel-info">

					<!-- panel header -->
					<div class="panel-heading">
						<div class="panel-title">
							<div class="row">
								<div class="col-xs-6">
									<h4>
										<span class="glyphicon glyphicon-shopping-cart"></span>
										Food Cart
									</h4>
								</div>
								<div class="col-xs-4">
									<form action="homepage.jsp">
										<button type="submit" class="btn btn-primary btn-sm btn-block">
											<span class="glyphicon glyphicon-share-alt"></span>
											<u style="font-size: large;">Continue</u>
										</button>
									</form>

								</div>
							</div>
						</div>
					</div>
					<!-- end header -->


					<!-- panel body -->
					<div class="panel-body">

						<c:set var="totalPrice" value="0" />

						<c:choose>
							<c:when test="${empty sessionScope.myFoodOrderItems}">
								<p>Your cart is empty! Go and add some food!</p>
							</c:when>

							<c:otherwise>
								<c:forEach items="${sessionScope.myFoodOrderItems}" var="foodItem" varStatus="loop">
									<div class="row">
										<div class="col-xs-2">
											<img class="img-responsive" id="image${loop.index}" onerror="onImageError(${loop.index})"
												src="${foodItem.food.imageDirectory}"
											>
										</div>
										<div class="col-xs-4 text-left">
											<h4>
												<strong>
													<c:out value="${foodItem.food.name}" />
												</strong>
											</h4>
											<ul>
												<c:forEach items="${foodItem.modifierChosenList}" var="modifierChosen">
													<li>
														<small>
															<c:out value="${modifierChosen.name}" />
														</small>
														<small style="float: right;">
															<fmt:formatNumber value="${modifierChosen.price}" var="newModifierPrice" minFractionDigits="2" />
															+ $
															<c:out value="${newModifierPrice}" />
														</small>
													</li>
												</c:forEach>
											</ul>

											<!-- 									<h4> -->
											<!-- 										<small>everyone loves chris</small> -->
											<!-- 									</h4> -->
										</div>
										<div class="col-xs-6">
											<div class="col-xs-6 text-right">
												<h4>
													<strong>
														<fmt:formatNumber value="${foodItem.price}" var="price2" minFractionDigits="2" />
														$
														<c:out value="${price2}" />
														<c:set value="${totalPrice + foodItem.priceString}" var="totalPrice" />
													</strong>
												</h4>
											</div>
											<div class="col-xs-4">
												<%-- 										<input type="text" class="form-control input-sm" value="${foodItem.quantity}"> --%>
											</div>
											<div class="col-xs-2">
												<form action="DeleteFoodItemFromOrderItemsServlet">
													<input type="hidden" id="foodPosition" name="foodPosition" value="${loop.index}" />
													<button type="submit" class="btn btn-link btn-xs">
														<span class="glyphicon glyphicon-trash"></span>
													</button>
												</form>
											</div>
										</div>
									</div>
									<hr>
								</c:forEach>
							</c:otherwise>

						</c:choose>

					</div>
					<!-- panel body end -->


					<!-- panel footer -->
					<c:if test="${not empty sessionScope.myFoodOrderItems}">
						<div class="panel-footer">
							<div class="row text-center">
								<div class="col-xs-9">
									<fmt:formatNumber value="${totalPrice}" var="totalPrice2" minFractionDigits="2" />
									<h4 class="text-right">
										Total Payable:
										<strong>
											$
											<c:out value="${totalPrice2}" />
										</strong>
									</h4>
								</div>
								<div class="col-xs-3">
									<form action="AddNewFoodOrderServlet">
										<button type="submit" class="btn btn-success btn-block">Check Out</button>
									</form>
								</div>
							</div>
						</div>
					</c:if>
					<!-- panel footer end -->
					<br>


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


				</div>
			</div>
		</div>
	</div>



	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<!--     <script src="../../dist/js/bootstrap.min.js"></script> -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--     <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->


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