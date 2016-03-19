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

		<%@include file="/headerfooter/adminHeader2.jsp"%>


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Order Period Management: Closed-Details</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->


			<div class="row">
				<div class="col-lg-12">
					<c:set value="${sessionScope.period}" var="period" />

					<div class="panel-body">
						<b>Period ID: </b>
						${period.periodId}
						<br>

						<b>Canteen: </b>
						${period.canteen.name}
						<br>
						<br>

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

						<c:set value="${sessionScope.noGroup}" var="noGroupList" />
						<c:set value="${sessionScope.groupedByStall}" var="groupByStallList" />


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
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${noGroupList}" var="order" varStatus="loop">
												<tr class="odd gradeX">
													<td rowspan="${fn:length(order.value) + 1}">${loop.index + 1}</td>
													<td rowspan="${fn:length(order.value) + 1}">
														<p title="${order.key.email}">${order.key.name}&nbsp;(${order.key.deliveryPoint})</p>
														<a
															href="/eureka_webservice/admin/orderperiod/add-fooditem.jsp?foodOrderId=${order.value[0].foodOrder.foodOrderId}"
														>Add new line</a>
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
															<td>
																<button type="button" class="btn btn-link btn-xs" data-toggle="modal"
																	data-target="#modalDelete${innerLoop}"
																>
																	<i class="fa fa-trash-o fa-2x"></i>
																</button>

																<!-- Modal delete -->
																<div class="modal fade" id="modalDelete${innerLoop}" tabindex="-1" role="dialog"
																	aria-labelledby="myModalLabel"
																>
																	<div class="modal-dialog" role="document">
																		<form action="/eureka_webservice/ProcessAdminDeleteFoodOrderItemServlet" method="post">
																			<input type="hidden" name="foodOrderItemId" value="${foodOrderItem.foodOrderItemId}">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
																						<span aria-hidden="true">&times;</span>
																					</button>
																					<h4 class="modal-title text-center" id="myModalLabel">Confirmation</h4>
																				</div>
																				<!-- / modal header -->
																				<div class="modal-body">
																					<p>
																						<b>WARNING: </b>
																						You are deleting food order item.
																						<br>
																						<br>
																						Are you sure you want to continue?
																					</p>
																				</div>
																				<!-- / modal body -->

																				<div class="modal-footer">
																					<!--  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
																					<button type="button" class="btn btn-default" data-dismiss="modal">No, keep the item</button>
																					<button type="submit" class="btn btn-danger">Yes, delete the item</button>
																				</div>
																				<!-- / modal footer -->
																			</div>
																			<!-- / modal content -->
																		</form>
																	</div>
																</div>
																<!-- / Modal delete -->


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
									<button class="btn btn-primary" type="button" onclick="period.print()">Print This Page</button>
								</div>
								<!-- /.table-responsive -->
							</div>


							<!-- 2nd tab - group by stall -->
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

									<br>
									<br>
									<button class="btn btn-primary" type="button" onclick="period.print()">Print This Page</button>


								</div>
								<!-- /.table-responsive -->
							</div>


							<!-- 3rd tab - group by stall CHINESE -->
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
									<button class="btn btn-primary" type="button" onclick="period.print()">Print This Page</button>

								</div>

								<!-- /.table-responsive -->
							</div>


						</div>
					</div>

				</div>
				<!-- /.col-lg-12 -->
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
