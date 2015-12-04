
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

<title>DABAO</title>

<link rel="icon" href="../../favicon.ico">

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Javascript imports -->
<script src="resources/js/jquery-1.11.3.js"></script>
<script src="resources/js/dabao/dabao.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/dabao/transition.js"></script>
<script src="resources/js/dabao/carousel.js"></script>
<script src="resources/js/dabao/collapse.js"></script>


<!-- Bootstrap core CSS -->
<link href="resources/css/dabao/dabao.css" rel="stylesheet">
<link href="resources/css/dabao/carousel.css" rel="stylesheet">
<link href="resources/css/dabao/starter-template.css" rel="stylesheet">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/shop-homepage.css" rel="stylesheet">
<link href="resources/css/sticky-footer.css" rel="stylesheet">

</head>
<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/adminHeader.jsp" />


	<div class="container">
		<div align="center">

			<div class="container">
				<div class="row center">
					<div class="col-xs-10 col center">
						<div class="panel panel-default">

							<div class="panel-heading">
								<div class="panel-title">
									<h1>User Profile</h1>
								</div>
							</div>

							<table class="table table-striped">
								<tr>
									<td>Name:</td>
									<td>
										<c:out value="${sessionScope.name}" />
									</td>
								</tr>
								<tr>
									<td>Email:</td>
									<td>
										<c:out value="${sessionScope.email}" />
									</td>
								</tr>
								<tr>
									<td>Contact Number:</td>
									<td>
										<c:out value="${sessionScope.contactNumber}" />
									</td>
								</tr>
								<tr>
									<td>Status:</td>
									<td>
										<c:out value="${sessionScope.status}" />
									</td>
								</tr>
								<tr>
									<td>Amount Owed:</td>
									<td>
										$
										<c:out value="${sessionScope.amountOwed}" />
									</td>
								</tr>

							</table>
						</div>
					</div>
				</div>
			</div>
			<br>
			<br>
			<H1>Order History</h1>
			<br>

			<div class="container" style="margin-top: 100px;">
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

					<c:forEach items="${requestScope.orderHistory}" var="order" varStatus="orderLoop">

						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="heading${orderLoop.index}">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${orderLoop.index}"
										aria-expanded="false" aria-controls="collapse${orderLoop.index}"
									>
										<c:out value="${order.createDate}" />
										<p style="float: right;">
											<c:out value="${order.status}" />
										</p>
									</a>
								</h4>
							</div>

							<div id="collapse${orderLoop.index}" class="panel-collapse collapse" role="tabpanel"
								aria-labelledby="heading${orderLoop.index}"
							>
								<div class="panel-body">
									<div style="font-size: 18px;">

										<table>
											<tr>
												<td style="padding-right: 10px;">
													<strong>Canteen:</strong>
												</td>
												<td>Taman Jurong Market and Food Centre</td>
											</tr>
											<tr>
												<td style="padding-right: 10px;">
													<strong>Price:</strong>
												</td>
												<td>
													<fmt:formatNumber value="${order.totalPrice}" var="totalPrice" minFractionDigits="2" />
													$
													<c:out value="${totalPrice}" />
												</td>
											</tr>
										</table>
									</div>
									<br>

									<table class="table table-striped" border="1">
										<thead>
											<tr>
												<th>Stall</th>
												<th>Food</th>
												<th>Quantity</th>
												<th>Price</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${order.foodOrderList}" var="foodItem">
												<tr>
													<td>
														<c:out value="${foodItem.food.stall.name}" />
													</td>
													<td>
														<c:out value="${foodItem.food.name}" />
														<ul>
															<c:forEach items="${foodItem.modifierChosenList}" var="modifierChosen">
																<li>
																	<small>
																		<c:out value="${modifierChosen.name}" />
																		<fmt:formatNumber value="${modifierChosen.price}" var="newModifierPrice" minFractionDigits="2" />
																		+ $
																		<c:out value="${newModifierPrice}" />
																	</small>
																</li>
															</c:forEach>
														</ul>
													</td>
													<td>
														<c:out value="${foodItem.quantity}" />
													</td>
													<td>
														<fmt:formatNumber value="${foodItem.price}" var="newPrice" minFractionDigits="2" />
														$
														<c:out value="${newPrice}" />
													</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>

								</div>
							</div>
						</div>
					</c:forEach>


				</div>
			</div>


		</div>


		<!-- center align -->

	</div>
	<!-- container -->

	<!-- Footer JSP Include -->
	<jsp:include page="headerfooter/footer.jsp" />


</body>


</html>