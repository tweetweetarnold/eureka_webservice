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
					<h1 class="page-header">${sessionScope.canteenName}:&nbsp;Stalls</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-12">

					<!-- Success message handling -->
					<c:if test="${not empty sessionScope.success}">
						<div class="alert alert-success" role="alert">
							<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							<span class="sr-only">Success: </span>
							${success}
						</div>
						<c:remove var="success" scope="session" />
					</c:if>

					<b>Total stalls:</b>
					${fn:length(sessionScope.stallList)}
					<br>
					<br>
					<form action="/eureka_webservice/admin/stall/add.jsp">
						<input type="hidden" name="canteenId" value="${sessionScope.canteenId}">
						<button type="submit" class="btn btn-primary">Add stall</button>
					</form>
					<br>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>ID</th>
									<th>Stall</th>
									<th>Create Date</th>
									<th>Image</th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sessionScope.stallList}" var="stall" varStatus="loop">
									<tr>
										<td>${stall.stallId}</td>
										<td>${stall.name}</td>
										<td>
											<fmt:formatDate type="both" value="${stall.createDate}" />
										</td>
										<td>
											<img src="/eureka_webservice/${stall.imageDirectory}" />
										</td>
										<td>
											<a href="/eureka_webservice/LoadAdminViewFoodsServlet?stallId=${stall.stallId}">View all
												${fn:length(stall.foodList)} food</a>
										</td>
										<td>
											<a href="/eureka_webservice/LoadAdminEditStallServlet?stallId=${stall.stallId}">Edit stall</a>
										</td>
										<td>
											<button type="button" class="btn btn-link btn-xs" data-toggle="modal" data-target="#modalDelete${loop.index}">
												<i class="fa fa-trash-o fa-2x"></i>
											</button>

											<!-- Modal delete -->
											<div class="modal fade" id="modalDelete${loop.index}" tabindex="-1" role="dialog"
												aria-labelledby="myModalLabel"
											>
												<div class="modal-dialog" role="document">
													<form action="">
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
																	You are deleting a stall from canteen.
																	<br>
																	<br>
																	Are you sure you want to continue?
																</p>
															</div>
															<!-- / modal body -->

															<div class="modal-footer">
																<button type="button" class="btn btn-default" data-dismiss="modal">No, keep my stall</button>
																<button type="submit" class="btn btn-danger">Yes, delete the stall</button>
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
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->

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
