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

<body ng-app="myApp" ng-controller="AddModifierController">


	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Edit Add-On(s)</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>


			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Edit Add-On(s)</div>

						<div class="panel-body">

							<div class="row">

								<div class="col-lg-8">


									<h4>Sections</h4>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>Section Name</th>
												<th>Choose Type</th>
												<th>Add On(s)</th>
												<th></th>
											</tr>
										</thead>


										<tbody>
											<tr ng-repeat='modSection in food.modifierSectionList track by $index'>

												<td>{{modSection.categoryName}}</td>
												<td>{{modSection.displayType}}</td>
												<td>
													<table class="table">
														<tr ng-repeat='mod in modSection.modifierList track by $index'>
															<td>{{mod.name}}</td>
															<td>{{mod.price | currency}}</td>
															<td>
																<!-- 																<button type="button" class="btn btn-link btn-xs" ng-click='removeModifier(mod.modifierId)'> -->
																<!-- 																	<i class="fa fa-trash-o fa-2x"></i> -->
																<!-- 																</button> -->
															</td>
														</tr>
														<tr>
															<td>
																<input type="text" ng-model='modifier.name' placeholder="Add meat">
															</td>
															<td>
																<input type='text' ng-model='modifier.price' placeholder='2.30'>
															</td>
															<td>
																<input type='submit' class='btn btn-primary' ng-disabled='!(modifier.name && modifier.price)'
																	ng-click='addModifier(modSection.modifierSectionId)' value='Add Add-On'
																>
															</td>
														</tr>

													</table>
												</td>
												<td>
													<!-- 													<a target="_self" href="#">Delete</a> -->
												</td>
											</tr>

											<tr>

												<td>
													<input ng-model='sec.categoryName' type="text">
												</td>
												<td>
													<select ng-model='sec.displayType'>
														<option value="c">Checkbox</option>
														<option value="d">Dropdown</option>
													</select>
												</td>
												<td></td>
												<td>
													<a class="btn btn-primary" href="#" ng-click='addSection()'>Add</a>
												</td>

											</tr>


										</tbody>

									</table>


									<button class="btn btn-primary" ng-click='done()'>Update Add-On(s)</button>


								</div>
								<!-- /.col-lg-8 -->



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



	<script>
		app
				.controller(
						'AddModifierController',
						[
								'$scope',
								'$http',
								'$window',
								'$location',
								function($scope, $http, $window, $location) {

									var load = function() {
										$http(
												{
													method : 'GET',
													url : '/eureka_webservice/GetFoodServlet',
													params : {
														foodId : $location
																.search().foodId
													}
												})
												.then(
														function successCallback(
																response) {
															console
																	.log(response.data);
															$scope.food = response.data;
														});
									}
									load();
									$scope.modifier = {};
									var stallId = $location.search().stallId;
									$scope.modifierList = [];

									$scope.addSection = function() {
										$http
												.post(
														'/eureka_webservice/AddModifierSectionServlet',
														{
															categoryName : $scope.sec.categoryName,
															displayType : $scope.sec.displayType,
															foodId : $location
																	.search().foodId
														})
												.then(
														function successCallback(
																response) {
															load();
														});
									}

									$scope.addModifier = function(modSectionId) {

										$http
												.post(
														'/eureka_webservice/AddModifierToSectionServlet',
														{
															modifierSectionId : modSectionId,
															name : $scope.modifier.name,
															price : $scope.modifier.price,
															foodId : $location
																	.search().foodId
														})
												.then(
														function successCallback(
																response) {
															load();
														});

									}

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
														function successCallback(
																response) {
															if (response.data.success != null) {
																$window.sessionStorage.success = response.data.success;
																$window.location.href = '/eureka_webservice/admin/food/view.jsp?stallId='
																		+ stallId;
															} else if (response.data.error != null) {
																$window.sessionStorage.error = response.data.error;
																$window.location.href = '/eureka_webservice/admin/food/view.jsp?stallId='
																		+ stallId;
															} else {
																console
																		.log(response);
																alert('fail');
															}

														});

									};

								} ]);
	</script>

</body>

</html>