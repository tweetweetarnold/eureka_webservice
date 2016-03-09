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

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Order Period Management: Edit</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<!-- Success message handling -->
			<c:if test="${not empty sessionScope.success}">
				<div class="alert alert-success" role="alert">
					<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
					<span class="sr-only">Success: </span>
					${success}
				</div>
				<c:remove var="success" scope="session" />
			</c:if>

			<!-- Error message handling -->
			<c:if test="${not empty sessionScope.error}">
				<div class="alert alert-danger" role="alert">
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					<span class="sr-only">Error: </span>
					${error}
				</div>
				<c:remove var="error" scope="session" />
			</c:if>



			<div class="row">
				<div class="col-lg-12">

					<br>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>Id</th>
									<th>Company</th>
									<th>Canteen</th>
									<th>Discount</th>
									<th>Date Created</th>
									<th>Start Date/Time</th>
									<th>End Date/Time</th>
								</tr>
							</thead>
							<tbody>
								<c:set value="${sessionScope.period}" var="period" />
								<tr>
									<td>${period.periodId}</td>
									<td>${period.company.name}</td>
									<td>${period.canteen.name}</td>
									<td>
										$
										<fmt:formatNumber value="${period.priceModifierList[0].value}" var="amt" minFractionDigits="2" />${amt}
									</td>
									<td>
										<fmt:formatDate type="both" value="${period.createDate}" />
									</td>
									<td>
										<fmt:formatDate type="both" value="${period.startDateFormatted}" />
									</td>
									<td>
										<fmt:formatDate type="both" value="${period.endDateFormatted}" />
									</td>
								</tr>
							</tbody>
						</table>


						<form name="form" action="/eureka_webservice/EditOrderPeriodServlet" method="post">

							<input type="hidden" value="${period.periodId}" name="orderPeriodId">

							<div class="container">
								<div class='col-md-3'>
									<div class="form-group">
										<label>New Start Date</label>
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
										<label>New End Date</label>
										<div class='input-group date' id='datetimepicker7'>
											<input type='text' class="form-control" name="endDatetime" placeholder="End Date" required />
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>

										<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Help"
											data-content="The end date cannot be earlier than the start date!" data-placement="right"
										>
											<i class="fa fa-question-circle"></i>
										</a>


									</div>
								</div>

								<div class="col-md-1"></div>
							</div>


							<button type="submit" class="btn btn-primary">Update</button>



						</form>

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
