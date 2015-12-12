
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
		<h2>PayPal</h2>
		<br>
		<!-- PayPal -->
	<!--  	<div class="container" style="margin-top: 100px;">-->
	<!--	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"> -->
			
			<c:set var="haveOrder" value="false"/>
			<c:if test="${empty sessionScope.submittedOrders}">
				<h3a>You haven't ordered anything! Go order something!</h2>
			</c:if>

			<c:forEach items="${sessionScope.submittedOrders}" var="order" varStatus="orderLoop">
				
				<c:set var="haveOrder" value="true"/>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="heading${orderLoop.index}">
						<h4 class="panel-title">
							<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${orderLoop.index}"
								aria-expanded="false" aria-controls="collapse${orderLoop.index}"
							>
								Order ID:
								<c:out value="${order.foodOrderId}" />
								-
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

		
		

		<br>
		<!-- PayPal form -->
		<c:choose>
		<c:when test="${haveOrder eq true}">
			<form action="${initParam['posturl']}" method="post">
				<input type="hidden" name="upload" value="1"/>
				<input type="hidden" name="return" value="${initParam['returnurl']}"/>
				<input type="hidden" name="cmd" value="_cart"/>
				<input type="hidden" name="business" value="${initParam['business']}"/>
				<input type="hidden" name="currency_code" value="SGD">
			
				<c:set var="count" value="0" />
			
				<c:forEach items="${sessionScope.submittedOrders}" var="order" varStatus="orderLoop">		
	
					<c:forEach items="${order.foodOrderList}" var="foodItem" varStatus="foodItemLoop">
						<c:set var="count" value="${count + 1}" />
						<c:set var="modifiedFoodName" value="${foodItem.food.name}" />
						
						<c:forEach items="${foodItem.modifierChosenList}" var="modifierChosen" varStatus="modifierChosenLoop">
							<c:choose>
								<c:when test="${fn:contains(modifierChosen.name, 'Upsize')}">
		   							<c:set var="modifiedFoodName" value="${modifiedFoodName} with Upsize"/>	
								</c:when>
								<c:otherwise>
									<c:set var="modifiedFoodName" value="${modifiedFoodName} with ${modifierChosen.name}"/>	
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<input type="hidden" name="item_name_<c:out value="${count}"/>" value="<c:out value="${modifiedFoodName}" />">
						<input type="hidden" name="quantity_<c:out value="${count}"/>" value="<c:out value="${foodItem.quantity}" />">
						<fmt:formatNumber value="${foodItem.price}" var="newPrice" minFractionDigits="2" />
						<input type="hidden" name="amount_<c:out value="${count}"/>" value="<c:out value="${newPrice}" />">
					
					</c:forEach>
					
				</c:forEach>
			
				<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif">
			
			</form>
		</c:when>
		<c:otherwise>There are no orders to be processed.
      	<br />
      	</c:otherwise>
		</c:choose>
	<!-- End of PayPal -->
	
	<!-- Error message handling -->
		<c:if test="${not empty sessionScope.error}">
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				<span class="sr-only">Error:</span>
				<c:out value="${error}" />
			</div>
			<c:remove var="error" scope="session" />
		</c:if>
	
	<!-- Success message handling -->
		<c:if test="${not empty sessionScope.paymentSuccess}">
			<div class="alert alert-success" role="alert">
				<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
				<span class="sr-only">Success:</span>
				<c:out value="${paymentSuccess}" />
			</div>
			<c:remove var="paymentSuccess" scope="session" />
		</c:if>
</div>
	</div>

	<!-- Bootstrap core JavaScript
        ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<!-- 	<script src="../../dist/js/bootstrap.min.js"></script> -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- 	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->


</body>
</html>