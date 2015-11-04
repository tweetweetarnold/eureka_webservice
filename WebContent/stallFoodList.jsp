<!DOCTYPE html>
<html lang="en">
<%@include file="protect.jsp"%>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>DABAO</title>

<!-- Library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="resources/js/jquery-1.11.3.js"></script>
<script src="resources/js/dabao/transition.js"></script>
<script src="resources/js/dabao/dabao.js"></script>

<!-- Bootstrap core CSS -->
<link href="resources/css/dabao/dabao.css" rel="stylesheet">

<!-- Custom styles for this template -->
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


	<!-- container -->
	<div class="container">

		<!-- breadcrumb -->
		<ol class="left breadcrumb">
			<li>
				<a href="homepage.jsp">Home</a>
			</li>
			<li class="active">
				<c:out value="${sessionScope.stallName}" />
			</li>
		</ol>
		<br>
		<!-- end breadcrumb -->

		<c:out value='${param["stallId"]}' />

		<div class="jumbotron">
			<div class="row">

				<c:forEach items="${sessionScope.stallFoodList}" var="food" varStatus="foodListLoop">
					<div class="thumbnail">
						<%-- 						<img src="${pageContext.request.contextPath}/ImageServlet?id=${food.foodId}" /> --%>
						<img id="image${foodListLoop.index}" onerror="onImageError(${foodListLoop.index})" src="${food.imageDirectory}">

						<div class="caption">

							<!-- Button trigger modal -->
							<button type="button" class="button" data-toggle="modal" data-target="#myModal${food.foodId}"
								style="white-space: normal;"
							>
								<c:out value="${food.name}" />
								<br>
								<fmt:formatNumber value="${food.price}" var="foodPrice2" minFractionDigits="2" />
								$
								<c:out value="${foodPrice2}" />
							</button>

							<!-- Modal -->
							<div class="modal fade" id="myModal${food.foodId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
								<div class="modal-dialog" role="document">
									<div class="modal-content">


										<form action="AddFoodItemToOrderItemsServlet">
											<input type="hidden" value="${food.foodId}" name="foodId">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<h4 class="modal-title" id="myModalLabel">Customize your order</h4>
											</div>

											<div class="modal-body">
												<table class="table table-striped">
													<thead>
														<tr>
															<th style="text-align: center;">S/N</th>
															<th>Modifier</th>
															<th>Price</th>
															<th>Add</th>
														</tr>
													</thead>
													<tbody>
														<c:choose>
															<c:when test="${not empty food.modifierList}">
																<c:forEach varStatus="loop" items="${food.modifierList}" var="modifier">
																	<tr>
																		<td style="text-align: center;">
																			<c:out value="${loop.index + 1}" />
																		</td>
																		<td style="text-align: left;">
																			<c:out value="${modifier.name}" />
																		</td>
																		<td style="text-align: left;">
																			<fmt:formatNumber value="${modifier.price}" var="modPrice" minFractionDigits="2" />
																			+ $
																			<c:out value="${modPrice}" />
																		</td>
																		<td style="text-align: center;">
																			<input type="checkbox" value="true" name="${modifier.name}">
																		</td>
																	</tr>
																</c:forEach>
															</c:when>
															<c:otherwise>
																<tr>
																	<td colspan="4">No modifiers available</td>
																</tr>
															</c:otherwise>
														</c:choose>

													</tbody>

												</table>

											</div>

											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
												<button type="submit" class="btn btn-primary">Add to Cart!</button>
											</div>
										</form>


									</div>
								</div>
							</div>
							<!-- end Modal -->

						</div>
					</div>

				</c:forEach>

			</div>

		</div>

	</div>
	<!-- end container -->


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