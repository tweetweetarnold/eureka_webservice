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

<!-- Custom Fonts -->
<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"
>

<!-- Datetime picker css -->
<link href="/eureka_webservice/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">


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
					<h1 class="page-header">Add new food</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- Error message handling -->
			<c:if test="${not empty sessionScope.error}">
				<div class="alert alert-danger" role="alert">
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					<span class="sr-only">Error: </span>
					${error}
				</div>
				<c:remove var="error" scope="session" />
			</c:if>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Add new food</div>

						<div class="panel-body">

							<div class="row">

								<div class="col-lg-12">

									<form role="form" action="/eureka_webservice/ProcessAdminAddNewFoodServlet" method="POST" id="submitForm"
										enctype="multipart/form-data"
									>

										<input type="hidden" name="stallId" value="${stallId}">

										<div class="form-group">
											<label>Food name</label>
											<input class="form-control" name="name" required>
										</div>

										<div class="form-group">
											<label>Food name (Chinese)</label>
											<input class="form-control" name="chineseName">
										</div>

										<div class="form-group">
											<label>Description</label>
											<input class="form-control" name="description">
										</div>

										<div class="form-group">
											<label>Price</label>
											<input class="form-control" name="price" required>
										</div>

										<input name="weatherConditions" type="hidden" value="${sessionScope.weatherConditions}">

										<div class="form-group">
											<label>Image Directory</label>
											<input type="file" name="file" style="width: 228px;" required />
										</div>
										<br>

										<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Add
											new Food</button>

									</form>
								</div>
								<!-- /.col-lg-12 -->

							</div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->



	</div>
	<!-- /#wrapper -->

	<!-- Create confirmation -->
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Confirmation</h4>
				</div>
				<div class="modal-body">Are you sure you want to Add new Food?</div>
				<div class="modal-footer">
					<button type="button" data-dismiss='modal' class="btn btn-outline btn-default">Cancel</button>
					<button type="submit" form="submitForm" class="btn btn-primary">Confirm</button>
				</div>
			</div>
		</div>
	</div>


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

	<!-- Datetime picker -->
	<script src="http://momentjs.com/downloads/moment.js"></script>
	<script src="/eureka_webservice/resources/js/bootstrap-datetimepicker.min.js"></script>

</body>

</html>