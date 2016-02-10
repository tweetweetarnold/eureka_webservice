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


<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.min.js"></script>

</head>

<body ng-app="myApp" ng-controller="AddModifierController">


	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Add new Add-On(s)</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>


			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Add new Add-On(s)</div>

						<div class="panel-body">

							<div class="row">

								<div class="col-lg-12">

									<input type="text" ng-model='modifier.name' placeholder="Add meat">
									<input type='text' ng-model='modifier.price' placeholder='2.30'>
									<input type='submit' class='btn btn-primary' ng-click='addModifier()' value='Add Add-On'>
									<button class="btn btn-primary" ng-click='done()'>Done</button>

									<br>
									<br>
									<table class="table">
										<tr ng-repeat="boo in modifierList track by $index">
											<td>{{boo.name}}</td>
											<td>{{boo.price}}</td>
											<td>
												<button type="button" class="btn btn-link btn-xs" ng-click='removeModifier($index)'>
													<i class="fa fa-trash-o fa-2x"></i>
												</button>
											</td>
										</tr>
									</table>

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

	<script>
		var app = angular.module('myApp', []);

		app.config([ '$locationProvider', function($locationProvider) {
			$locationProvider.html5Mode({
				enabled : true,
				requireBase : false
			});
		} ]);

		app
				.controller(
						'AddModifierController',
						[
								'$scope',
								'$http',
								'$window',
								'$location',
								function($scope, $http, $window, $location) {

									$scope.modifierList = [];
									$scope.modifier = {
										name : 'Arnold',
										price : '123'
									};

									$scope.addModifier = function() {
										$scope.modifierList
												.push($scope.modifier);
										$scope.modifier = {};
										console.log($scope.modifierList);
									};

									$scope.removeModifier = function(index) {
										$scope.modifierList.splice(index, 1);
									};

									$scope.done = function() {
										console.log($scope.modifierList);

										console.log({
											data : $scope.modifierList
										});

										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/ProcessAdminAddModifierToFoodServlet',
													data : {
														foodId : $location
																.search().foodId,
														modifierList : $scope.modifierList
													}
												})
												.then(
														function successCallback() {
															$window.location.href = '/eureka_webservice/admin/food/view.jsp';
														});

									};

								} ]);
	</script>

</body>

</html>