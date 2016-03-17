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

<body ng-app="myApp" ng-controller="AddFoodOrderItemController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Order Period Management: Edit Food Order Item</h1>

					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li>
							<a target="_self" href="/eureka_webservice/LoadOrderPeriodActiveServlet">Back</a>
						</li>
						<li class="active">Edit</li>
					</ol>

				</div>




				<!-- Message handling -->
				<div class="col-lg-12">
					<div class="alert alert-success alert-dismissible fade in" role="alert" ng-show="success != null" ng-cloak>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
						<span class="sr-only">Success: </span>
						{{success}}
					</div>
					<div class="alert alert-danger alert-dismissible fade in" role="alert" ng-show="error != null" ng-cloak>
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						<span class="sr-only">Error: </span>
						{{error}}
					</div>
				</div>
				<!-- / message handling -->
				<!-- /.col-lg-12 -->


			</div>

			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Edit Food Order Item</div>

						<div class="panel-body">

							<div class="row">

								<div class="col-lg-6">

									<div class="form-group">
										<label>Canteen: </label>
										{{canteen.name}}
									</div>

									<div class="form-group">
										<label for="stall">Stall: </label>
										<select class="form-control" ng-model='stall' name="food" ng-change='getFood()'
											ng-options='s.name for s in stalls track by s.stallId'
										>
										</select>
									</div>



									<div class="form-group">
										<label for="food">Food: </label>
										<select class="form-control" ng-model='food' name="food"
											ng-options='f as f.name  + " (" + (f.price | currency) + ")" for f in data track by f.foodId'
											ng-disabled='data == null'
										>
										</select>
									</div>




									<div class="form-group" ng-show="food != null && food.modifierList.length > 0">
										<label for="modifierChosen">Add-On: </label>
										<select class="form-control" name="modifierChosen" ng-model='modifierChosen'
											ng-options='m as m.name + " (" + (m.price | currency) + ")" for m in food.modifierList track by m.modifierId'
										>
										</select>
									</div>



									<div class="form-group">
										<label>Total Price: </label>
										<p>{{food.price + modifierChosen.price | currency}}</p>
									</div>




									<br>
									<button class="btn btn-primary" ng-disabled='!(stall && food)' ng-click="submit()">Update food item</button>

								</div>
								<!-- /col-lg-6 -->


							</div>
							<!-- /.row -->
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

	<script>
		app
				.controller(
						'AddFoodOrderItemController',
						[
								'$scope',
								'$http',
								'$location',
								'$window',
								function($scope, $http, $location, $window) {

									$scope.success = angular
											.copy($window.sessionStorage.success);
									$scope.error = angular
											.copy($window.sessionStorage.error);
									$window.sessionStorage
											.removeItem('success');
									$window.sessionStorage.removeItem('error');

									$scope.foodOrderId = $location.search().foodOrderId;
									$scope.canteenId = $location.search().canteenId;
									$scope.foodOrderItemId = $location.search().foodOrderItemId;

									$http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetAllStallsUnderCanteenServlet',
												params : {
													canteenId : $scope.canteenId
												}
											})
											.then(
													function successCallback(
															response) {
														console.log(response);
														$scope.stalls = response.data.stalls;
														$scope.canteen = response.data.canteen;
													});

									$scope.getFood = function() {
										$http(
												{
													method : 'GET',
													url : '/eureka_webservice/GetAllFoodsUnderStallServlet',
													params : {
														stallId : $scope.stall.stallId
													}
												})
												.then(
														function successCallback(
																response) {
															console
																	.log(response.data);
															$scope.data = response.data.foods;
														},
														function errorCallback(
																response) {
															alert(response);
														});
									};

									$scope.totalPrice = $scope.food;

									$scope.submit = function() {
										console.log({
											food : $scope.food,
											modifier : $scope.modifierChosen,
											foodOrderId : $scope.foodOrderId
										});

										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/AddFoodOrderItemIntoFoodOrderServlet',
													data : {
														food : $scope.food,
														modifier : $scope.modifierChosen,
														foodOrderId : $scope.foodOrderId,
														foodOrderItemId : $scope.foodOrderItemId
													}
												})
												.then(
														function successCallback(
																response) {
															console
																	.log(response);
															if (response.data.success != null) {
																$scope.success = response.data.success;
															} else if (response.data.error != null) {
																$scope.error = response.data.error;
															} else {
																alert(response);
															}

														});

									}

								} ]);
	</script>


</body>

</html>
