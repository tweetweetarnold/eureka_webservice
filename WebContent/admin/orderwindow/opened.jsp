<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">

<%@include file="/protect/adminProtect.jsp"%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title>LunchTime - Admin</title>

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

		<%@include file="/headerfooter/adminHeader2.jsp"%>


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
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

						<c:forEach items="${sessionScope.orderWindowMap}" var="map" varStatus="mapLoop">
							<c:set value="${map.key}" var="window" />

							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="heading${mapLoop.index}">
									<h4 class="panel-title">
										<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${mapLoop.index}"
											aria-expanded="true" aria-controls="collapse${mapLoop.index}"
										>
											${window.company.name}:&nbsp;&nbsp;
											<b>
												<fmt:setLocale value="EN" />
												<fmt:formatDate type="both" pattern="E, dd-MMM-yyyy HH:mm:ss" value="${window.startDateFormatted}" />
											</b>
											&nbsp;&nbsp;to&nbsp;&nbsp;
											<b>
												<fmt:formatDate type="both" pattern="E, dd-MMM-yyyy HH:mm:ss" value="${window.endDateFormatted}" />
											</b>
										</a>
									</h4>
								</div>
								<div id="collapse${mapLoop.index}" class="panel-collapse collapse" role="tabpanel"
									aria-labelledby="heading${mapLoop.index}"
								>
									<div class="panel-body">
										<b>Window ID: </b>
										${window.windowId}
										<br>

										<b>Canteen: </b>
										${window.canteen.name}
										<br>
										<br>

										<!-- Nav tabs -->
										<ul class="nav nav-tabs">
											<li class="active">
												<a href="#nogroup${mapLoop.index}" data-toggle="tab">No Group</a>
											</li>
											<li>
												<a href="#groupByStall${mapLoop.index}" data-toggle="tab">Group by Stalls</a>
											</li>
											<li>
												<a href="#groupByStallCN${mapLoop.index}" data-toggle="tab">Group by Stalls (CN)</a>
											</li>
										</ul>

										<c:set value="${map.value[0]}" var="noGroupList" />
										<c:set value="${map.value[1]}" var="groupByStallList" />


										<!-- Tab panes -->
										<div class="tab-content">
											<c:set value="0" var="overallPrice" />

											<!-- 1st tab - group by users -->
											<div class="tab-pane fade in active" id="nogroup${mapLoop.index }">
												<div class="dataTable_wrapper">
													<br>
													<table class="table table-striped table-bordered table-hover" id="dataTables-example">
														<thead>
															<tr>
																<th>S/N</th>
																<th>User</th>
																<th>Food</th>
																<th>Add-On(s)</th>
																<th>Qty</th>
																<th>Price($)</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${noGroupList}" var="order" varStatus="loop">
																<tr class="odd gradeX">
																	<td rowspan="${fn:length(order.value) + 1}">${loop.index + 1}</td>
																	<td rowspan="${fn:length(order.value) + 1}">
																		<p title="${order.key.email}">${order.key.name}&nbsp;(${order.key.deliveryPoint})</p>
																	</td>

																	<c:forEach items="${order.value}" var="foodOrderItem">
																		<tr>
																			<td>${foodOrderItem.food.name}</td>
																			<td>
																				<c:forEach items="${foodOrderItem.modifierChosenList}" var="modifier" varStatus="innerLoop">
																					${modifier.name}
																					<c:if test="${!innerLoop.last}">, </c:if>
																				</c:forEach>
																			</td>
																			<td>${foodOrderItem.quantity}</td>
																			<td>
																				<fmt:formatNumber value="${foodOrderItem.quantity * foodOrderItem.price}" var="combinedPrice"
																					minFractionDigits="2"
																				/>
																				$${combinedPrice}
																				<c:set value="${overallPrice + combinedPrice}" var="overallPrice" />
																			</td>
																		</tr>
																	</c:forEach>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<b>Overall Price: </b>
													$
													<fmt:formatNumber value="${overallPrice}" var="overallPrice2" minFractionDigits="2" />${overallPrice2}
												</div>
												<!-- /.table-responsive -->
											</div>


											<!-- 2nd tab - group by stall -->
											<div class="tab-pane fade" id="groupByStall${mapLoop.index }">
												<div class="dataTable_wrapper">
													<br>
													<table class="table table-striped table-bordered table-hover" id="dataTables-example">
														<thead>
															<tr>
																<th>Stall</th>
																<th>Stall No</th>
																<th>Total($)</th>
																<th>Food</th>
																<th>Add-On(s)</th>
																<th>Qty</th>
																<th>Subtotal($)</th>
																<th>User</th>
															</tr>
														</thead>
														<tbody>
															<c:set value="0" var="overallPrice" />
															<c:forEach items="${groupByStallList}" var="foodDisplayObj" varStatus="loop">

																<tr class="odd gradeX">
																	<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">${foodDisplayObj.stallName}</td>
																	<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">${foodDisplayObj.phoneNumber }</td>
																	<td rowspan="${fn:length(foodDisplayObj.foodOrderItemList) + 1}">
																		<fmt:formatNumber value="${foodDisplayObj.totalPrice}" var="totalPrice" minFractionDigits="2" />
																		$${totalPrice}
																		<c:set value="${overallPrice + foodDisplayObj.totalPrice}" var="overallPrice" />
																	</td>


																	<c:set value="${foodDisplayObj.quantityList}" var="quantityList" />
																	<c:set value="${foodDisplayObj.usernameList}" var="usernameList" />
																	<c:set value="${foodDisplayObj.priceList}" var="priceList" />

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
																			<td>
																				<c:forEach items="${usernameList[item.foodOrderItemId]}" var="user">
																					<p title="${user.email}">${user.name}&nbsp;(${user.deliveryPoint})</p>
																				</c:forEach>
																			</td>
																		</tr>
																	</c:forEach>

																</tr>
															</c:forEach>
														</tbody>
													</table>
													<b>Overall Price: </b>
													$
													<fmt:formatNumber value="${overallPrice}" var="overallPrice2" minFractionDigits="2" />${overallPrice2}


												</div>
												<!-- /.table-responsive -->
											</div>


											<!-- 3rd tab - group by stall CHINESE -->
											<div class="tab-pane fade" id="groupByStallCN${mapLoop.index }">
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
																<th>Add-On(s)</th>
																<th>Qty</th>
																<th>Subtotal($)</th>
																<th>User</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${groupByStallList}" var="foodDisplayObj" varStatus="loop">

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
																			<td>
																				<c:forEach items="${usernameList[item.foodOrderItemId]}" var="user">
																					<p title="${user.email}">${user.name}&nbsp;(${user.deliveryPoint})</p>
																				</c:forEach>
																			</td>
																		</tr>
																	</c:forEach>

																</tr>
															</c:forEach>
														</tbody>
													</table>
													<b>Overall Price: </b>
													$
													<fmt:formatNumber value="${overallPrice}" var="overallPrice2" minFractionDigits="2" />${overallPrice2}

													<br>
													<br>
													<button class="btn btn-primary" type="button" onclick="window.print()">Print This Page</button>

												</div>

												<!-- /.table-responsive -->
											</div>


										</div>
									</div>
								</div>
							</div>
							<!-- /.panel-default -->
						</c:forEach>



					</div>
					<!-- /.panel-group -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /# page-wrapper -->

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
	<script src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>


</body>

</html>
