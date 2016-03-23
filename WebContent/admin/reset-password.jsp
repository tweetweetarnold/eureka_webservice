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

<!-- Angular -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.min.js"></script>
<link href="/eureka_webservice/resources/angularbusy/angular-busy.min.css" rel="stylesheet">
<script src="/eureka_webservice/resources/angularbusy/angular-busy.min.js"></script>
<script src='/eureka_webservice/resources/js/myapp.js'></script>

</head>

<body ng-app='myApp' ng-controller='ResetPasswordController'>

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>


		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Reset Password</h1>
				</div>
				<!-- /.col-lg-12 -->


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


			</div>
			<!-- /.row -->


			<div class="row">
				<div class="col-lg-12">

					<div class="panel-group">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">Reset password</div>

							<div class="panel-body">

								<h3>Warning! Read this before proceeding.</h3>
								<p>You are about to reset the password for admin. Ensure that you do not lose your password.</p>

								<hr>



								<div class="col-md-6">


									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-6 text-right">
											<strong>Current Password:</strong>
										</div>
										<div class="col-md-6">
											<input class="form-control" type="password" name="oldPassword" ng-model='input.oldPassword' required>
										</div>
									</div>


									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-6 text-right">
											<strong>New Password:</strong>
										</div>
										<div class="col-md-6">
											<input class="form-control" ng-model='input.newPassword' name="newPassword" type="password" required>
										</div>
									</div>


									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-6 text-right">
											<strong>Confirm New Password:</strong>
										</div>
										<div class="col-md-6">
											<input class="form-control" ng-model='input.confirmPassword' name="confirmPassword" type="password" required>
										</div>
									</div>


									<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-6"></div>
										<div class="col-md-6">
											<button class="btn btn-primary" ng-click='submit()'>Update password</button>
										</div>
									</div>


								</div>





							</div>
						</div>
					</div>

				</div>
				<!-- /.col-lg-12 -->


			</div>
			<!-- /.row -->


		</div>
		<!-- /# page-wrapper -->

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
		app.controller('ResetPasswordController', [
				'$http',
				'$scope',
				'$window',
				function($http, $scope, $window) {

					$scope.success = angular
							.copy($window.sessionStorage.success);
					$scope.error = angular.copy($window.sessionStorage.error);
					$window.sessionStorage.removeItem('success');
					$window.sessionStorage.removeItem('error');
					$scope.input = {};

					$scope.submit = function() {

						$http({
							method : 'POST',
							url : '/eureka_webservice/UpdateAdminPassword',
							data : {
								oldPassword : $scope.input.oldPassword,
								newPassword : $scope.input.newPassword,
								confirmPassword : $scope.input.confirmPassword
							}
						}).then(function successCallback(response) {
							$scope.input = {};
							console.log(response.data);
							$scope.success = response.data.success;
							$scope.error = response.data.error;
						}, function errorCallback(response) {
							console.log(response);
							alert("Something went wrong!");
						});
					}

				}

		]);
	</script>


</body>

</html>
