
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
		
		<p>***Please ensure that you have confirmed your food orders.<br>
		Have sufficient funds in your account before proceeding to Paypal.</p>
			
		
		<br>
		
		<!-- End of PayPal -->
		<form action="${initParam['posturl']}" method="post">
		<input type="hidden" name="upload" value="1"/>
		<input type="hidden" name="return" value="${initParam['returnurl']}"/>
		<input type="hidden" name="cmd" value="_cart"/>
		<input type="hidden" name="business" value="${initParam['business']}"/>
		
		<c:set var="count" value="0" />
		
		
		<c:forEach items="${sessionScope.orderHistory}" var="order" varStatus="orderLoop">		
			<c:if test="${order.status == 'Submitted'}">
			
			<c:forEach items="${order.foodOrderList}" var="foodItem" varStatus="foodItemLoop">
				<c:set var="count" value="${count + 1}" />
				
				<input type="hidden" name="item_name_<c:out value="${count}"/>" value="<c:out value="${foodItem.food.name}" />">
				<c:forEach items="${foodItem.modifierChosenList}" var="modifierChosen" varStatus="modifierChosenLoop">
					
					<input type="hidden" name="modifier_name_${modifierChosenLoop.index+1}" value="<c:out value="${modifierChosen.name}" />
					<fmt:formatNumber value="${modifierChosen.price}" var="newModifierPrice" minFractionDigits="2" />+$<c:out value="${newModifierPrice}" />">			
				</c:forEach>
				<input type="hidden" name="quantity_<c:out value="${count}"/>" value="<c:out value="${foodItem.quantity}" />">
				<fmt:formatNumber value="${foodItem.price}" var="newPrice" minFractionDigits="2" />
				<input type="hidden" name="amount_<c:out value="${count}"/>" value="<c:out value="${newPrice}" />">
			</c:forEach>
			
			</c:if>
		</c:forEach>
		<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif">
		</form>
	</div>
	
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


	<!-- Bootstrap core JavaScript
        ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<!-- 	<script src="../../dist/js/bootstrap.min.js"></script> -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- 	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->


</body>
</html>