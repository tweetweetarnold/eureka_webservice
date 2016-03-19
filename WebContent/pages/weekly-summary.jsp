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
					<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Display the weekly spending summary for the week of </b>
					<br>
					<br>
					<form class="sign-in-up-form"
						action="/eureka_webservice/LoadUserSpendingSummaryByWeek"
						role="form">
						<div class="col-md-3">
						
							<select class="form-control" name="id" required>
								<c:forEach items="${sessionScope.weekList}" var="map">
									<c:set var="splitWeek" value="${fn:split(map,'to')}" />
									
									<fmt:parseDate value="${splitWeek[0]}" var="parsedEmpDate1" pattern="yyyy-MM-dd" />
									<fmt:formatDate  value="${parsedEmpDate1}" var="weekChange1" pattern="dd-MMM-yyyy"/>
									
									<fmt:parseDate value="${splitWeek[1]}" var="parsedEmpDate2" pattern="yyyy-MM-dd" />
									<fmt:formatDate  value="${parsedEmpDate2}" var="weekChange2" pattern="dd-MMM-yyyy"/>
									<option value="${map}">${weekChange1} to ${weekChange2}</option>
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
			</center> <br>
				
				<c:set var="sum" value="0"/>
					<c:set var="n" value="${sessionScope.dateToTotalPrice.size()}"/>
					<c:forEach items="${sessionScope.dateToTotalPrice}" var="map" varStatus="loop">
						<c:set var="key" value="${map.key}" />
							
								<c:set var="sum" value="${sum + map.value}"/>
						
					</c:forEach>
					
					
					<fmt:formatNumber value="${sum/n}" var="avg" minFractionDigits="2" />
					<h3><b>&nbsp;Average Spending for the Week: $${avg}</b></h3>
					<br>
						


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
										 
										 <c:set var="week" value="${fn:split(key,'to')}" />
												
										 <fmt:parseDate value="${week[0]}" var="parsedEmpDate3" pattern="yyyy-MM-dd" />
										 <fmt:formatDate  value="${parsedEmpDate3}" var="newWeek1" pattern="dd-MMM-yyyy"/>
												
										 <fmt:parseDate value="${week[1]}" var="parsedEmpDate4" pattern="yyyy-MM-dd" />
										 <fmt:formatDate  value="${parsedEmpDate4}" var="newWeek2" pattern="dd-MMM-yyyy"/>
										 
										 Week: ${newWeek1} to ${newWeek2} <fmt:formatNumber
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
										<c:choose>
										<c:when test="${foodOrder.finalPrice < 0 }">
										<i class="pull-right">Price: $0.00 </i>
										</c:when>
										<c:otherwise>
										<i class="pull-right">Price: $${amtSpent} </i>
										</c:otherwise>
										</c:choose>
										
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

