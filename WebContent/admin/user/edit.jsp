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

<body ng-app="myApp" ng-controller="ViewUserController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">User Management</h1>
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
									<th>Company</th>
									<th>Name</th>
									<th>Email</th>
									<th>Date Joined</th>
									<th>O/S</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>{{user.company.name}}</td>
									<td>
										<input type='text' ng-model='user.name' ng-value="user.name" placeholder='Chris Cheng'>
									</td>
									<td>{{user.email}}</td>
									<td>{{user.createDate}}</td>
									<td>
										<input type='text' ng-model='user.amountOwed' ng-value="user.amountOwed" placeholder='2.30'>
									</td>
									<td>{{user.status}}</td>
								</tr>
							</tbody>
						</table>

						<button ng-click='updateUser()' class="btn btn-primary">Update</button>
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

	<script>
		var app = angular.module('myApp', [ 'ngRoute' ]);

		app.config([ '$locationProvider', function($locationProvider) {
			$locationProvider.html5Mode({
				enabled : true,
				requireBase : false
			});
		} ]);

		app
				.controller(
						'ViewUserController',
						[
								'$http',
								'$scope',
								'$location',
								'$window',
								function($http, $scope, $location, $window) {

									$scope.error = angular
											.copy($window.sessionStorage.error);
									$window.sessionStorage.removeItem('error');

									var email = $location.search().email;

									$http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetUserByEmailServlet',
												params : {
													'email' : email
												}
											}).then(
											function successCallback(response) {
												console.log(response);
												console.log(response.data);
												$scope.user = response.data;
											},
											function errorCallback(response) {
												alert('fail');
											});

									$scope.updateUser = function() {
										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/EditUserServlet',
													data : $scope.user
												})
												.then(
														function successCallback(
																response) {
															if (response.data.success != null) {
																$window.sessionStorage.success = response.data.success;
																$window.location.href = '/eureka_webservice/admin/user/view.jsp';
															} else if (response.data.error != null) {
																$window.sessionStorage.error = response.data.error;
																$window.location.href = '/eureka_webservice/admin/user/edit.jsp';
															} else {
																alert(response);
															}
														});

									};
								}

						]);
	</script>

</body>

</html>
