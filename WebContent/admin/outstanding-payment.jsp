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

<body ng-app="myApp" ng-controller='OutstandingPaymentController'>

	<div id="wrapper">

		<%@include file="/admin/adminHeader.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Outstanding Payment</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-12" cg-busy='loading'>

					<b>Total users:</b>
					{{data.length}}
					<br>

					<div ng-show='data.length == 0'>
						<br>
						<div class="alert alert-warning fade in" role="alert">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							<span class="sr-only">Warning:</span>
							There are currently no outstanding payment!
						</div>
					</div>

					<!-- if no records -->
					<div ng-hide='data.length == 0'>

						<!--  Search -->
						<div class="col-lg-4" style="padding: 20px 0px 20px;">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="Search" ng-model='searchText'>
								<div class="input-group-btn">
									<button class="btn btn-default">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</div>
						<!--  / Search -->

						<br>

						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover" id="dataTables-example">
								<thead>
									<tr>
										<th>
											<a href="#" ng-click="sortType = 'company.name'; sortReverse = !sortReverse">
												Company
												<span ng-show="sortType == 'company.name' && !sortReverse" class="fa fa-caret-down"></span>
												<span ng-show="sortType == 'company.name' && sortReverse" class="fa fa-caret-up"></span>
											</a>
										</th>
										<th>
											<a href="#" ng-click="sortType = 'name'; sortReverse = !sortReverse">
												Name
												<span ng-show="sortType == 'name' && !sortReverse" class="fa fa-caret-down"></span>
												<span ng-show="sortType == 'name' && sortReverse" class="fa fa-caret-up"></span>
											</a>
										</th>
										<th>
											<a href="#" ng-click="sortType = 'email'; sortReverse = !sortReverse">
												Email
												<span ng-show="sortType == 'email' && !sortReverse" class="fa fa-caret-down"></span>
												<span ng-show="sortType == 'email' && sortReverse" class="fa fa-caret-up"></span>
											</a>
										</th>
										<th>
											<a href="#" ng-click="sortType = 'amountOwed'; sortReverse = !sortReverse">
												O/S
												<span ng-show="sortType == 'amountOwed' && !sortReverse" class="fa fa-caret-down"></span>
												<span ng-show="sortType == 'amountOwed' && sortReverse" class="fa fa-caret-up"></span>
											</a>
										</th>
										<th>
											<a href="#" ng-click="sortType = 'status'; sortReverse = !sortReverse">
												Status
												<span ng-show="sortType == 'status' && !sortReverse" class="fa fa-caret-down"></span>
												<span ng-show="sortType == 'status' && sortReverse" class="fa fa-caret-up"></span>
											</a>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat='user in data | orderBy:sortType:sortReverse | filter:searchText track by $index'>
										<td>{{user.company.name}}</td>
										<td>{{user.name}}</td>
										<td>{{user.email}}</td>
										<td>{{user.amountOwed | currency}}
										<td>{{user.status}}</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->


					</div>
					<!-- if no records  -->


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
		app
				.controller(
						'OutstandingPaymentController',
						[
								'$http',
								'$scope',
								'$window',
								function($http, $scope, $window) {

									$scope.success = angular
											.copy($window.sessionStorage.success);
									$scope.error = angular
											.copy($window.sessionStorage.error);
									$window.sessionStorage
											.removeItem('success');
									$window.sessionStorage.removeItem('error');

									$scope.sortType = 'amountOwed'; // set the default sort type
									$scope.sortReverse = true; // set the default sort order

									$scope.loading = $http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetUsersWithOutstandingPaymentServlet'
											}).then(
											function successCallback(response) {
												console.log(response.data);
												$scope.data = response.data;
											},
											function errorCallback(response) {
												console.log(response)
												alert("Something went wrong!");
											});

								} ]);
	</script>
</body>

</html>
