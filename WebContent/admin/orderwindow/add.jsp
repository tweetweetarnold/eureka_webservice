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
	<fmt:setTimeZone value="GMT+8" />

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Order Window Management: Add</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Add new Order window</div>

						<div class="panel-body">

							<div class="row">



								<form role="form" action="/eureka_webservice/ProcessAdminAddNewOrderWindowServlet" id="submitForm">

									<div class="col-md-6 col-lg-6">

										<h2 class="page-header">Required</h2>

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

										<div class="container">
											<div class='col-md-3'>
												<div class="form-group">
													<label>Start Datetime</label>
													<div class='input-group date' id='datetimepicker6'>
														<input type='text' class="form-control" name="startDatetime" placeholder="Start Date" required />
														<span class="input-group-addon">
															<span class="glyphicon glyphicon-calendar"></span>
														</span>
													</div>
												</div>
											</div>

											<div class='col-md-3'>
												<div class="form-group">
													<label>End Datetime</label>
													<div class='input-group date' id='datetimepicker7'>
														<input type='text' class="form-control" name="endDatetime" placeholder="End Date" required />
														<span class="input-group-addon">
															<span class="glyphicon glyphicon-calendar"></span>
														</span>
													</div>
												</div>
											</div>
										</div>


										<div class="form-group">
											<label>Repeat Number of Weeks</label>
											<select class="form-control" name="repeat" required>
												<c:forEach items="${sessionScope.weekList}" var="week">
													<option value="${week}">${week}</option>
												</c:forEach>
											</select>
										</div>


										<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirm">Add New
											Order Window</button>

										<!-- Modal confirmation -->
										<div class="modal" id="confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h4 class="modal-title" id="myModalLabel">Confirmation</h4>
													</div>
													<div class="modal-body">Are you sure you want to add a new Order Window?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
														<button type="submit" class="btn btn-primary" form="submitForm">Confirm</button>
													</div>
												</div>
											</div>
										</div>
										<!-- /modal confirmation -->

									</div>
									<!-- /.col-md-6 col-lg-6 -->



									<div class="col-md-6 col-lg-6">

										<h2 class="page-header">Optional</h2>

										<div class="form-group">
											<label>Discount Value (Optional)</label>
											<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Help"
												data-content="Put in a value (eg 3.00) to give the users here a $3.00 discount for their orders!"
												data-placement="right"
											>
												<i class="fa fa-question-circle"></i>
											</a>
											<input name="discountAbsolute" class="form-control" placeholder="Eg. 2.00">
										</div>

										<div class="form-group">
											<label>Comments to Users (Optional)</label>
											<textarea rows="3" name="remarks" class="form-control"
												placeholder="What do you want to let your users know about this Order Window?"
											></textarea>
										</div>

									</div>
									<!-- /.col-md-6 col-lg-6 -->

								</form>



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

	<!-- new datepicker -->
	<script type="text/javascript">
		$(function() {
			$('#datetimepicker6').datetimepicker({
				format : 'DD-MMMM-YYYY HH:mm'
			});
			$('#datetimepicker7').datetimepicker({
				useCurrent : false,
				format : 'DD-MMMM-YYYY HH:mm'
			//Important! See issue #1075
			});
			$("#datetimepicker6").on("dp.change", function(e) {
				$('#datetimepicker7').data("DateTimePicker").minDate(e.date);
			});
			$("#datetimepicker7").on("dp.change", function(e) {
				$('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
			});
		});
	</script>

	<!-- popover -->
	<script>
		$(function() {
			$('[data-toggle="popover"]').popover();
		});
	</script>


</body>

</html>
