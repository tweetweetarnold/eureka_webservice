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
					<h1 class="page-header">Order History for User: ${sessionScope.name}</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-12">

					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

						<c:forEach items="${sessionScope.orderHistoryList}" var="orderHistory" varStatus="loop">

							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="heading${loop.index }">
									<h4 class="panel-title">
										<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${loop.index }"
											aria-expanded="true" aria-controls="collapse${loop.index }"
										>
											Status:
											<b>${orderHistory.status}</b>
											- Date:
											<b>
												<fmt:formatDate type="both" value="${orderHistory.createDate}" />
											</b>
										</a>
									</h4>
								</div>
								<div id="collapse${loop.index }" class="panel-collapse collapse" role="tabpanel"
									aria-labelledby="heading${loop.index }"
								>
									<div class="panel-body">
										<b>Order Window: </b>
										${orderHistory.orderWindow.windowId}
										<br>
										<b>Canteen: </b>
										${orderHistory.orderWindow.canteen.name}
										<br>
										<br>

										<div class="dataTable_wrapper">
											<table class="table table-striped table-bordered table-hover" id="dataTables-example">
												<thead>
													<tr>
														<th>Stall</th>
														<th>Food</th>
														<th>Modifier</th>
														<th>Qty</th>
														<th>Price($)</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${orderHistory.foodOrderList}" var="foodOrderItem">
														<tr>
															<td>${foodOrderItem.food.stall.name}</td>
															<td>${foodOrderItem.food.name}</td>
															<td>
																<c:forEach items="${foodOrderItem.modifierChosenList}" var="modifierChosen" varStatus="innerLoop">
																	${modifierChosen.name}
																	<c:if test="${!innerLoop.last}">, </c:if> 
																</c:forEach>
															</td>
															<td>${foodOrderItem.quantity}</td>
															<td>
																<fmt:formatNumber value="${foodOrderItem.price}" var="finalPrice" minFractionDigits="2" />
																$${finalPrice}
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<!-- /.table-responsive -->

									</div>
								</div>
							</div>
							<!-- /.panel-default -->
						</c:forEach>



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
