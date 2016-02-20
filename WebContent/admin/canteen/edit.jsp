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

<!-- Bootstrap Core CSS -->
<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet"
>

<!-- MetisMenu CSS -->
<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet"
>

<!-- Timeline CSS -->
<link href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
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

<!-- Angular -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.min.js"></script>
<link href="/eureka_webservice/resources/angularbusy/angular-busy.min.css" rel="stylesheet">
<script src="/eureka_webservice/resources/angularbusy/angular-busy.min.js"></script>
<script src='/eureka_webservice/resources/js/myapp.js'></script>

</head>

<body ng-app="myApp" ng-controller="EditCanteenController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Edit canteen</h1>

					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li>
							<a target='_self' href="/eureka_webservice/LoadViewCanteenServlet">Canteens</a>
						</li>
					</ol>

				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Edit canteen</div>

						<div class="panel-body">

							<div class="row">

								<div class="col-lg-12">

									<form name="form">

										<div class="form-group">
											<label>Canteen name</label>
											<input class="form-control" name="name" ng-model='canteen.name' ng-value="canteen.name" required>
											<span ng-show='form.name.$error.required'>
												<font style="color: red;">This is required!</font>
											</span>
										</div>

										<div class="form-group">
											<label>Address</label>
											<input class="form-control" name="address" ng-model='canteen.address' ng-value="canteen.address" required>
											<span ng-show='form.address.$error.required'>
												<font style="color: red;">This is required!</font>
											</span>
										</div>

										<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm"
											ng-disabled='form.$invalid'
										>Update Canteen details</button>

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
				<div class="modal-body">Are you sure you want to update Canteen details?</div>
				<div class="modal-footer">
					<button type="button" data-dismiss='modal' class="btn btn-outline btn-default">Cancel</button>
					<button type="submit" ng-click='submit()' class="btn btn-primary">Confirm</button>
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
	<script src="resources/js/bootstrap-datetimepicker.min.js"></script>


	<script>
		app
				.controller(
						'EditCanteenController',
						[
								'$http',
								'$location',
								'$scope',
								'$window',
								function($http, $location, $scope, $window) {
									$http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetCanteenServlet',
												params : {
													canteenId : $location
															.search().canteenId
												}
											}).then(
											function successCallback(response) {
												console.log(response);
												$scope.canteen = response.data;
												console.log($scope.canteen);
											});

									$scope.submit = function() {

										console.log($scope.canteen);

										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/SetCanteenServlet',
													data : $scope.canteen
												})
												.then(
														function successCallback(
																response) {

															if (response.data.success != null) {
																$window.sessionStorage.success = response.data.success;
															} else if (response.data.error != null) {
																$window.sessionStorage.error = response.data.error;
															} else {
																alert('fail');
															}
															$window.location.href = '/eureka_webservice/admin/canteen/view.jsp';
														});
									};

								} ]);
	</script>

</body>

</html>
