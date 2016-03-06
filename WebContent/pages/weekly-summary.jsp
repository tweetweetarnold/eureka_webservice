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

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Bootstrap Core CSS -->
<link
	href="/eureka_webservice/resources/startbootstrap-business/css/bootstrap.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="/eureka_webservice/resources/startbootstrap-business/css/modern-business.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="/eureka_webservice/resources/startbootstrap-business/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
var noRedirect = true;
function myFunction() {
    if (noRedirect ){
         return "Please remember to checkout the items in your cart!";
    }else{
    	noRedirect = false;
    }
}
</script>
</head>

<body onbeforeunload="return myFunction()">

	<jsp:include page="header.jsp" />
	<fmt:setTimeZone value="GMT+8" />

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Weekly Spending Summary <small>your previous orders</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="/eureka_webservice/pages/homepage.jsp" onclick = "noRedirect=false">Home</a></li>
					<li><a href="/eureka_webservice/LoadUserOrderHistoryServlet" onclick = "noRedirect=false">Order
							History</a></li>

					<li><a
						href="/eureka_webservice/LoadUserSpendingSummaryByMonth" onclick = "noRedirect=false">Monthly
							Spending Summary</a></li>
					<li class="active">Weekly Spending Summary</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->
		
		<div class="row">
			<div class="col-lg-12">
				<c:if test="${not empty sessionScope.weekList}">
					<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Select the week range to display the Spending Summary of the week</b>
					<br>
					<br>
					<form class="sign-in-up-form"
						action="/eureka_webservice/LoadUserSpendingSummaryByWeek"
						role="form">
						<div class="col-md-3">
						
							<select class="form-control" name="id" required>
								<c:forEach items="${sessionScope.weekList}" var="map">

									<option value="${map}">${map}</option>
								</c:forEach>

							</select>
						</div>
						<div class="col-md-2">
							<button class="btn btn-success btn-block" type="submit" onclick = "noRedirect=false">Display</button>
						</div>
					</form>
					</c:if>
				<br><br>
				<c:if test="${not empty sessionScope.resultSet}">
					<c:set var="weekValue" value="${resultSet}" />
					<center>
				<img src="/eureka_webservice/LoadUserWeeklyChart?week=${weekValue}" />
			</center> <br><br>
		
						


				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<c:forEach items="${sessionScope.weekToTotalPrice}" var="map"
						varStatus="loop">
						<c:set var="key" value="${map.key}" />
						<c:if test="${key == weekValue}">
						
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse${loop.index}"
										aria-expanded="true" aria-controls="collapse${loop.index}">
										 Week: ${key} <fmt:formatNumber
											value="${map.value}" var="amt" minFractionDigits="2" /> <i
										class="pull-right">Amount Spent: $${amt}</i>

									</a>
								</h4>
							</div>
							<div id="collapse${loop.index}" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<c:set var="foodOrdersMap"
										value="${sessionScope.weekToFoodOrders}" />
									<c:set var="list" value="${foodOrdersMap[key]}" />
									<c:forEach items="${list}" var="foodOrder" varStatus="loop">
										Order ID: ${foodOrder.foodOrderId} &mdash;
										<fmt:formatDate type="both" value="${foodOrder.createDate}" />
										<fmt:formatNumber value="${foodOrder.finalPrice}"
											var="amtSpent" minFractionDigits="2" />
										<i class="pull-right">Price: $${amtSpent} </i>
										<br>
									</c:forEach>
								</div>
							</div>
						</div>
						</c:if>
					</c:forEach>
				</div>
					<c:remove var="resultSet" scope="session" />
				</c:if>












			</div>
			<!-- /col-lg-12 -->
		</div>
		<!-- /row -->


		<jsp:include page="footer.jsp" />

	</div>
	<!-- /.container -->

	
	<!-- jQuery -->
	<script
		src="/eureka_webservice/resources/startbootstrap-business/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="/eureka_webservice/resources/startbootstrap-business/js/bootstrap.min.js"></script>

	<!-- Popover Js -->
	<script>
		$(function() {
			$('[data-toggle="popover"]').popover();
		});
	</script>

</body>

</html>

