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

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>


</head>

<body ng-app='myApp' ng-controller='AddFoodController'>

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
			<!-- /.row -->

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
					<div class="panel panel-default">
						<div class="panel-heading">Add new food</div>

						<div class="panel-body">

							<div class="row">

								<div class="col-lg-12">

									<form id='addFoodForm' name="addFoodForm" novalidate>

										<input ng-model='food.stallId' type="text" name="stallId" value="2">

										<div class="form-group">
											<label>Food name</label>
											<input ng-model='food.name' class="form-control" name="name" required>
										</div>

										<div class="form-group">
											<label>Food name (Chinese)</label>
											<input ng-model='food.chineseName' class="form-control" name="chineseName">
										</div>

										<div class="form-group">
											<label>Description (Optional)</label>
											<input ng-model='food.description' class="form-control" name="description">
										</div>

										<div class="form-group">
											<label>Price</label>
											<input ng-model='food.price' class="form-control" name="price">
										</div>

										<div class="form-group">
											<label>Weather Conditions</label>
											<input ng-model='food.weatherConditions' class="form-control" name="weatherConditions">
										</div>

										<div class="form-group">
											<label>Image Directory (*only accepts jpeg or jpg image formats)</label>
											<input fileread='food.file' type="file" name="file" style="width: 228px;" />
										</div>
									</form>

									<form name='modifierForm' novalidate>
										<div class="form-group">
											<label>Modifiers (Optional)</label>
											<br>
											<input type="text" name="name" ng-model='modifier.name' placeholder="Modifier Name" required>
											<input type="number" name="price" ng-model='modifier.price' placeholder="Modifier Price" required>
											<button ng-disabled='modifierForm.$invalid' ng-click='addModifier(modifier)'>Add Modifier</button>
											<br>
											<div ng-show='modifierList.length > 0'>
												<br>
												<table class="table">
													<tr ng-repeat='modifier2 in modifierList track by $index'>
														<td>{{modifier2.name}}</td>
														<td>{{modifier2.price | currency}}</td>
														<td>
															<button type="submit" ng-click='removeModifier($index)' class="btn btn-link btn-xs">
																<i class="fa fa-trash-o fa-2x"></i>
															</button>
														</td>
													</tr>
												</table>
											</div>
										</div>
									</form>

									<button ng-disabled='addFoodForm.$invalid' type="button" class="btn btn-primary" data-toggle="modal"
										data-target=".bs-example-modal-sm"
									>Add new Food</button>


									<br>

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
	<div class="modal bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Confirmation</h4>
				</div>
				<div class="modal-body">Are you sure you want to Add new Food?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-outline btn-default">Cancel</button>
					<button type="submit" ng-click='submitForm()' class="btn btn-primary">Confirm</button>
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

	<script>
		var app = angular.module('myApp', []);

		app.controller('AddFoodController', function($scope, $http) {

			$scope.modifierList = [];

			$scope.addModifier = function(modifier) {
				$scope.modifierList.push(angular.copy(modifier));
				$scope.modifier = {};
				console.log($scope.modifierList);
			};

			$scope.removeModifier = function(index) {
				$scope.modifierList.splice(index, 1);
				console.log('removing modifier');
			};

			$scope.submitForm = function() {
				var object = {};
				$scope.food.modifierList = angular.copy($scope.modifierList);
				object.food = $scope.food;

				console.log(object);

				$http({
					method : 'POST',
					url : '/eureka_webservice/ProcessAdminAddNewFoodServlet',
					data : object
				}).then(function successCallback(response) {
					alert('success');
				});

			};
		});

		app.directive("fileread", [ function() {
			return {
				scope : {
					fileread : "="
				},
				link : function(scope, element, attributes) {
					element.bind("change", function(changeEvent) {
						scope.$apply(function() {
							scope.fileread = changeEvent.target.files[0];
							// or all selected files:
							// scope.fileread = changeEvent.target.files;
						});
					});
				}
			};
		} ]);
	</script>

</body>

</html>
