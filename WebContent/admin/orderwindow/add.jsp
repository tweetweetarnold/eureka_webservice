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

<!-- Datetime picker css -->
<link href="/eureka_webservice/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">


<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

</head>

<body>

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Create New Window</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Create new order window</div>

						<div class="panel-body">

							<div class="row">

								<div class="col-lg-12">

									<form role="form" action="/eureka_webservice/ProcessAdminAddNewOrderWindowServlet" id="submitForm">

										<div class="form-group">
											<label>Select Company</label>
											<select class="form-control" name="company" required>
												<option value="">-- Select --</option>
												<c:forEach items="${sessionScope.companyList}" var="company">
													<option value="${company.companyId}">${company.name}</option>
												</c:forEach>
											</select>
										</div>

										<div class="form-group">
											<label>Select Canteen</label>
											<select class="form-control" name="canteen" required>
												<option value="">-- Select --</option>
												<c:forEach items="${sessionScope.canteenList}" var="canteen">
													<option value="${canteen.canteenId}">${canteen.name}</option>
												</c:forEach>
											</select>
										</div>

										<div class="form-group">
											<label>Start Datetime</label>
											<div class='input-group date' id='startDatetime'>
												<input type='text' class="form-control" name="startDatetime" required />
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>

										<div class="form-group">
											<label>End Datetime</label>
											<div class='input-group date' id='endDatetime'>
												<input type='text' class="form-control" name="endDatetime" required />
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
										</div>

										<div class="form-group">
											<label>Discount (decimal)</label>
											<input name="discount" class="form-control">
										</div>
										<div class="form-group">
											<label>Number of Weeks</label>
											<select class="form-control" name="Repeat" required>
												<option value="">-- Select --</option>
												<c:forEach items="${sessionScope.weekList}" var="week">
													<option value="${week}">${week}</option>
												</c:forEach>
											</select>
										</div>

										<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Create
											New Window</button>

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
				<div class="modal-body">Are you sure you want to create a new Order Window?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline btn-default">Cancel</button>
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
	<script src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>


	<!-- Datetime picker -->
	<script src="http://momentjs.com/downloads/moment.js"></script>
	<script src="/eureka_webservice/resources/js/bootstrap-datetimepicker.min.js"></script>

	<!-- Datepicker -->
	<script type="text/javascript">
		$(function() {
			$('#startDatetime').datetimepicker({
				inline : true,
				sideBySide : true,
				format : 'DD-MMMM-YYYY hh:mm'
			});
			$('#endDatetime').datetimepicker({
				useCurrent : false,
				inline : true,
				sideBySide : true,
				format : 'DD-MMMM-YYYY hh:mm'
			// Important! See issue #1075
			});
			$("#startDatetime").on("dp.change", function(e) {
				$('#endDatetime').data("DateTimePicker").minDate(e.date);
			});
			$("#endDatetime").on("dp.change", function(e) {
				$('#startDatetime').data("DateTimePicker").maxDate(e.date);
			});
		});
	</script>

</body>

</html>
