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

<!-- Angular -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.min.js"></script>
<link href="/eureka_webservice/resources/angularbusy/angular-busy.min.css" rel="stylesheet">
<script src="/eureka_webservice/resources/angularbusy/angular-busy.min.js"></script>
<script src='/eureka_webservice/resources/js/myapp.js'></script>

</head>

<body ng-app="myApp" ng-controller="EditOrderWindowController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Order Window Management: Edit</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

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
									<th>Start Date</th>
									<th>End date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>{{window.windowId}}</td>
									<td>{{window.company.name}}</td>
									<td>{{window.canteen.name}}</td>
									<td>{{window.discountAbsolute | currency}}</td>
									<td>{{window.createDate | date:'medium' : '+0800'}}</td>
									<td>{{window.startDateFormatted | date:'medium' : '+0800'}}</td>
									<td>{{window.endDateFormatted | date:'medium' : '+0800'}}</td>
								</tr>
							</tbody>
						</table>


						<form action="" method="post" name="form">

							<input type="hidden" name="orderWindowId" ng-value="window.windowId">

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
									</div>
								</div>
							</div>


							<button type="submit" class="btn btn-primary">Update</button>

							<a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Help"
								data-content="The end date cannot be earlier than the start date!" data-placement="right"
							>
								<i class="fa fa-question-circle"></i>
							</a>

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


	<script>
		app
				.controller(
						'EditOrderWindowController',
						[
								'$http',
								'$scope',
								'$location',
								'$window',
								function($http, $scope, $location, $window) {

									var windowId = $location.search().windowId;

									$scope.loading = $http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetOrderWindowByIdServlet',
												params : {
													windowId : windowId
												}
											}).then(
											function successCallback(response) {
												console.log(response);
												console.log(response.data);
												$scope.window = response.data;
											},
											function errorCallback(response) {
												alert('fail');
											});

									$scope.updateWindow = function() {
												$http(
														{
															method : 'POST',
															url : '/eureka_webservice/EditOrderWindowServlet',
															data : $scope.window
														})
														.then(
																function successCallback(
																		response) {

																	if (response.data.success != null) {
																		$window.sessionStorage.success = response.data.success;
																	} else if (response.data.error != null) {
																		$window.sessionStorage.error = response.data.error;
																	} else {
																		alert(response);
																	}
																	$window.location.href = '/eureka_webservice/admin/orderwindow/view.jsp';
																}),
												function errorCallback(repsonse) {
													alert('fail');
												};

									};
								}

						]);
	</script>

</body>

</html>
