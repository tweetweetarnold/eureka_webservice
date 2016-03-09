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

<body ng-app="myApp" ng-controller="EditCompanyController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Edit Company</h1>

					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li>
							<a target="_self" href="/eureka_webservice/admin/company/view.jsp">Company</a>
						</li>
					</ol>

				</div>
				<!-- /.col-lg-12 -->



			</div>


			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Edit Company</div>

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
											<input class="form-control" ng-model='company.companyCode' name="companyCode" required>
											<span style="color: red;" ng-show='form.companyCode.$error.required'>This is required!</span>
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
												<tr ng-repeat="boo in company.deliveryPointSet track by $index">
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
											Update Company
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
				<div class="modal-body">Are you sure you want to update Company details?</div>
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
						'EditCompanyController',
						[
								'$http',
								'$location',
								'$scope',
								'$window',
								function($http, $location, $scope, $window) {
									$http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetCompanyServlet',
												params : {
													companyId : $location
															.search().companyId
												}
											}).then(
											function successCallback(response) {
												console.log(response);
												$scope.company = response.data;
												console.log($scope.canteen);
											});

									$scope.addDeliveryPoint = function() {
										$scope.company.deliveryPointSet
												.push($scope.deliveryPoint);
										$scope.deliveryPoint = "";
										console
												.log($scope.company.deliveryPointSet);
									};

									$scope.removeDeliveryPoint = function(index) {
										$scope.company.deliveryPointSet.splice(
												index, 1);
									};

									$scope.submit2 = function() {
										console.log($scope.company);
									}

									$scope.submit = function() {

										console.log($scope.company);

										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/SetCompanyServlet',
													data : $scope.company
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
															$window.location.href = '/eureka_webservice/admin/company/view.jsp';
														});
									};

								} ]);
	</script>


</body>

</html>