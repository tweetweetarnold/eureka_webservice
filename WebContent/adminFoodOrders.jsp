<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">

<%@include file="protect/adminProtect.jsp"%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title>DABAO</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet"
>

<!-- MetisMenu CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet"
>

<!-- Timeline CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"
>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

</head>

<body>

	<div id="wrapper">

		<%@include file="headerfooter/adminHeader2.jsp"%>


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Order Windows: Opened</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->


			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">

						<div class="panel-heading">Data</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#nogroup" data-toggle="tab">No Group</a>
								</li>
								<li>
									<a href="#groupByStall" data-toggle="tab">Group by Stalls</a>
								</li>
								<li>
									<a href="#groupByStallCN" data-toggle="tab">Group by Stalls (CN)</a>
								</li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div class="tab-pane fade in active" id="nogroup">
									<div class="dataTable_wrapper">
										<br>
										<table class="table table-striped table-bordered table-hover" id="dataTables-example">
											<thead>
												<tr>
													<th>S/N</th>
													<th>User(s)</th>
													<th>Food</th>
													<th>Qty</th>
													<th>Price($)</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${sessionScope.orderWindowOpenedNogroup}" var="order" varStatus="loop">
													<tr class="odd gradeX">
														<td rowspan="${fn:length(order.value) + 1}">${loop.index + 1}</td>
														<td rowspan="${fn:length(order.value) + 1}">${order.key}</td>

														<c:forEach items="${order.value}" var="foodOrderItem">
															<tr>
																<td>${foodOrderItem.food.name}</td>
																<td>${foodOrderItem.quantity}</td>
																<td>$${foodOrderItem.priceString}</td>
															</tr>
														</c:forEach>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>


								<div class="tab-pane fade" id="groupByStall">
									<div class="dataTable_wrapper">
										<br>
										<table class="table table-striped table-bordered table-hover" id="dataTables-example">
											<thead>
												<tr>
													<th>Stall</th>
													<th>Stall No</th>
													<th>Total($)</th>
													<th>Food</th>
													<th>Add On</th>
													<th>Qty</th>
													<th>Subtotal($)</th>
													<th>User</th>
												</tr>
											</thead>
											<tbody>
												<c:set value="0" var="overallPrice" />
												<c:forEach items="${sessionScope.orderWindowOpenedStalls}" var="foodDisplayObj" varStatus="loop">

													<tr class="odd gradeX">
														<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">${foodDisplayObj.stallName}</td>
														<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">${foodDisplayObj.phoneNumber }</td>
														<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">
															<fmt:formatNumber value="${foodDisplayObj.totalPrice }" var="totalPrice" minFractionDigits="2" />
															$${totalPrice}
															<c:set value="${overallPrice + foodDisplayObj.totalPrice}" var="overallPrice" />
														</td>


														<c:set value="${foodDisplayObj.quantityList }" var="quantityList" />
														<c:set value="${foodDisplayObj.usernameList }" var="usernameList" />
														<c:set value="${foodDisplayObj.priceList }" var="priceList" />

														<c:forEach items="${foodDisplayObj.foodOrderItemList}" var="item">
															<tr>
																<td>${item.food.name}</td>
																<td>
																	<c:forEach items="${item.modifierChosenList}" var="modifierChosen">
																			${modifierChosen.name}<br>
																	</c:forEach>
																</td>
																<td>${quantityList[item.foodOrderItemId]}</td>
																<td>
																	<fmt:formatNumber value="${priceList[item.foodOrderItemId]}" var="subtotal" minFractionDigits="2" />
																	$${subtotal}
																</td>
																<td>${usernameList[item.foodOrderItemId]}</td>
															</tr>
														</c:forEach>

													</tr>
												</c:forEach>
											</tbody>
										</table>
										<b>Overall Price: </b>
										$
										<fmt:formatNumber value="${overallPrice }" var="overallPrice2" minFractionDigits="2" />${overallPrice2}


									</div>
									<!-- /.table-responsive -->
								</div>


								<div class="tab-pane fade" id="groupByStallCN">
									<fmt:setLocale value="zh_CN" />
									<fmt:setBundle basename="RBExample2" var="lang" />


									<div class="dataTable_wrapper">
										<br>
										<table class="table table-striped table-bordered table-hover" id="dataTables-example">
											<thead>
												<tr>
													<th>Stall</th>
													<th>Stall No</th>
													<th>Total($)</th>
													<th>Food</th>
													<th>Add On</th>
													<th>Qty</th>
													<th>Subtotal($)</th>
													<th>User</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${sessionScope.orderWindowOpenedStalls}" var="foodDisplayObj" varStatus="loop">

													<tr class="odd gradeX">
														<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">${foodDisplayObj.stallName}</td>
														<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">${foodDisplayObj.phoneNumber }</td>
														<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">
															<fmt:formatNumber value="${foodDisplayObj.totalPrice }" var="totalPrice" minFractionDigits="2" />
															$${totalPrice}
														</td>

														<c:set value="${foodDisplayObj.quantityList }" var="quantityList" />
														<c:set value="${foodDisplayObj.usernameList }" var="usernameList" />
														<c:set value="${foodDisplayObj.priceList }" var="priceList" />

														<c:forEach items="${foodDisplayObj.foodOrderItemList}" var="item">
															<tr>
																<td>
																	<fmt:message bundle="${lang}" key="${fn:replace(item.food.name, ' ', '_')}" />
																</td>
																<td>
																	<c:forEach items="${item.modifierChosenList}" var="modifierChosen">
																		<fmt:message bundle="${lang}" key="${fn:replace(modifierChosen.name, ' ', '_')}" />
																		<br>
																	</c:forEach>
																</td>
																<td>${quantityList[item.foodOrderItemId]}</td>
																<td>
																	<fmt:formatNumber value="${priceList[item.foodOrderItemId]}" var="subtotal" minFractionDigits="2" />
																	$${subtotal}
																</td>
																<td>${usernameList[item.foodOrderItemId]}</td>
															</tr>
														</c:forEach>

													</tr>
												</c:forEach>
											</tbody>
										</table>
										<b>Overall Price: </b>
										$
										<fmt:formatNumber value="${overallPrice }" var="overallPrice2" minFractionDigits="2" />${overallPrice2}

									</div>
									<!-- /.table-responsive -->
								</div>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /# page-wrapper -->

	</div>
	<!-- /#wrapper -->


	<!-- jQuery -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/raphael/raphael-min.js"></script>
	<!-- <script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.min.js"></script> -->
	<!-- <script src="resources/css/startbootstrap-sb-admin-2-1.0.7/js/morris-data.js"></script> -->

	<!-- Custom Theme JavaScript -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>
</body>

</html>
