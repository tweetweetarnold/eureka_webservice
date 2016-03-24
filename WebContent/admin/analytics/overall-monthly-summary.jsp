<!DOCTYPE html>
<html lang="en">

<%@include file="/protect/adminProtect.jsp"%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>LunchTime - Admin</title>

<link href="/eureka_webservice/resources/img/favicon/lunchtime_favicon.png" rel="shortcut icon">

<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet"
>
<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet"
>
<link href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/timeline.css" rel="stylesheet">
<link href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/sb-admin-2.css" rel="stylesheet">
<link href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.css"
	rel="stylesheet"
>
<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"
>


<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

</head>

<body>
	<fmt:setTimeZone value="GMT+8" />

	<div id="wrapper">

		<%@include file="/admin/adminHeader.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Overall Monthly Spending Summary</h1>
					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li class="active">Overall Monthly Spending Summary</li>
						<li>
							<a href="/eureka_webservice/LoadAdminViewOverallWeeklySpending">Overall Weekly Spending Summary</a>
						</li>
						
					</ol>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-12">
					<c:if test="${not empty sessionScope.yearToMonthList}">
					<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Display the monthly spending summary for the year of </b>
					<br>
					<br>
					<form class="sign-in-up-form"
						action="/eureka_webservice/LoadAdminViewOverallMonthlySpending"
						role="form">
						<div class="col-md-2">
							
							
							<select class="form-control" name="id" required>
								<c:forEach items="${sessionScope.yearToMonthList}" var="map">

									<option value="${map.key}">${map.key}</option>
								</c:forEach>

							</select>
						</div>
						<div class="col-md-2">
							<button class="btn btn-success btn-block" type="submit">Display</button>
						</div>
					</form>


					<br>
					<br>
					
				</c:if>
					<c:if test="${not empty sessionScope.result}">
					<c:set var="yearValue" value="${result}" />
					<center>
						<img
							src="/eureka_webservice/LoadOverallMonthlyChart?year=${yearValue}" />
					</center>
					<br>
					<br>

					<c:set var="sum" value="0"/>
					<c:set var="n" value="${sessionScope.yearMonthToTotalPrice.size()}"/>
					<c:forEach items="${sessionScope.yearMonthToTotalPrice}" var="map" varStatus="loop">
						<c:set var="key" value="${map.key}" />
							<c:if test="${fn:contains(key,yearValue)}">
								<c:set var="sum" value="${sum + map.value}"/>
							</c:if>
					</c:forEach>
					
					
					<fmt:formatNumber value="${sum/n}" var="avg" minFractionDigits="2" />
					<h3><b>&nbsp;Average Monthly Spending: $${avg}</b></h3>
					<br>

					<div class="panel-group" id="accordion" role="tablist"
						aria-multiselectable="true">
						<c:forEach items="${sessionScope.yearMonthToTotalPrice}" var="map"
							varStatus="loop">
							<c:set var="key" value="${map.key}" />
							<c:if test="${fn:contains(key,yearValue)}">
								<div class="panel panel-default">
									<div class="panel-heading" role="tab" id="headingOne">
										<h4 class="panel-title">
											<a role="button" data-toggle="collapse"
												data-parent="#accordion" href="#collapse${loop.index}"
												aria-expanded="true" aria-controls="collapse${loop.index}">







	


												Month-Year:<fmt:parseDate value="${key}" var="parsedEmpDate" pattern="yyyy-MM" />
                              						<fmt:formatDate pattern="MMM-yyyy" value="${parsedEmpDate}" />
												<fmt:formatNumber value="${map.value}"
													var="amt" minFractionDigits="2" /> <i class="pull-right">Amount
													Spent: $${amt}</i>


											</a>
										</h4>
									</div>
									<div id="collapse${loop.index}" class="panel-collapse collapse"
										role="tabpanel" aria-labelledby="headingOne">
										<div class="panel-body">

											<c:set var="foodOrdersMap"
												value="${sessionScope.yearMonthToFoodOrders}" />
											<c:set var="list" value="${foodOrdersMap[key]}" />
											<c:forEach items="${list}" var="foodOrder" varStatus="loop">
										
										 Order ID: ${foodOrder.foodOrderId} &mdash;
										<fmt:formatDate type="both" value="${foodOrder.createDate}" />
										 <br>User: ${foodOrder.employee.name}&nbsp;&nbsp;Company: ${foodOrder.employee.company.name}</br>
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
					<c:remove var="result" scope="session" />
				</c:if>



					</div>
					<!-- /.panel-group -->

				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

		</div>
	</div>
	<!-- /#wrapper -->


	<script
		src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/jquery/dist/jquery.min.js"
	></script>
	<script
		src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/js/bootstrap.min.js"
	></script>
	<script
		src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.js"
	></script>
	<script src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/raphael/raphael-min.js"></script>
	<!-- <script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.min.js"></script> -->
	<!-- <script src="resources/css/startbootstrap-sb-admin-2-1.0.7/js/morris-data.js"></script> -->
	<script src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>
</body>

</html>
