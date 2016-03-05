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


<!-- Angular -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.min.js"></script>
<link href="/eureka_webservice/resources/angularbusy/angular-busy.min.css" rel="stylesheet">
<script src="/eureka_webservice/resources/angularbusy/angular-busy.min.js"></script>
<script src='/eureka_webservice/resources/js/myapp.js'></script>

</head>

<body ng-app="myApp" ng-controller="AddCompanyController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Add new Company</h1>

					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li>
							<a target="_self" href="/eureka_webservice/admin/company/view.jsp">Back</a>
						</li>
					</ol>

				</div>
				<!-- /.col-lg-12 -->



			</div>


			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Add new Company</div>

						<div class="panel-body">

							<div class="row">

								<div class="col-lg-12">

									<form name="form" novalidate>
										<div class="form-group">
											<label> Company name </label>
											<input class="form-control" ng-model='company.name' name="name" required>
											<span style="color: red;" ng-show='form.name.$error.required'>This is required!</span>
										</div>

										<div class="form-group">
											<label>Company Code</label>
											<input class="form-control" ng-model='company.code' name="code" required>
											<span style="color: red;" ng-show='form.code.$error.required'>This is required!</span>
										</div>


										<hr>


										<div class="form-group">
											<label>Add Delivery Point</label>
											<br>

											<input type="text" ng-model='deliveryPoint' class="form-control pull-left" style="width: 33%;"
												placeholder="Eg. Admin Building"
											>
											<input type='submit' class='btn btn-primary' ng-disabled='!(deliveryPoint)' ng-click='addDeliveryPoint()'
												value='Add Delivery Point'
											>

											<table class="table">
												<tr ng-repeat="boo in deliveryPointList track by $index">
													<td>{{boo}}</td>
													<td>
														<button type="button" class="btn btn-link btn-xs" ng-click='removeDeliveryPoint($index)'>
															<i class="fa fa-trash-o fa-2x"></i>
														</button>
													</td>
												</tr>
											</table>

										</div>


										<br>


										<button ng-disabled='form.$invalid' type="button" class="btn btn-primary" data-toggle="modal"
											data-target=".bs-example-modal-sm"
										>
											<i class="fa fa-plus fa-lg"></i>
											Add new Company
										</button>

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
				<div class="modal-body">Are you sure you want to Add new Company?</div>
				<div class="modal-footer">
					<button type="button" data-dismiss='modal' class="btn btn-outline btn-default">Cancel</button>
					<button type="button" ng-click="submit()" class="btn btn-primary">Confirm</button>
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

	<script>
		app
				.controller(
						'AddCompanyController',
						[
								'$scope',
								'$http',
								'$window',
								function($scope, $http, $window) {

									$scope.error = angular
											.copy($window.sessionStorage.error);
									$window.sessionStorage.removeItem('error');

									$scope.deliveryPointList = [];

									$scope.addDeliveryPoint = function() {
										$scope.deliveryPointList
												.push($scope.deliveryPoint);
										$scope.deliveryPoint = "";
										console.log($scope.deliveryPointList);
									};

									$scope.removeDeliveryPoint = function(index) {
										$scope.deliveryPointList.splice(index,
												1);
									};

									$scope.submit = function() {
										console.log($scope.company);

										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/AddNewCompanyServlet',
													data : {
														name : $scope.company.name,
														code : $scope.company.code
													}
												})
												.then(
														function successCallback(
																response) {
															console
																	.log(response);
															if (response.data.success != null) {
																$window.sessionStorage.success = response.data.success;
																$window.location.href = '/eureka_webservice/admin/company/view.jsp';
															} else if (response.data.error != null) {
																$window.sessionStorage.error = response.data.error;
																$window.location.href = '/eureka_webservice/admin/company/add.jsp';
															} else {
																alert(response);
															}

														});
									};
								} ]);
	</script>


</body>

</html>