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

<body ng-app="myApp" ng-controller="ViewCompanyController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Company Management</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-12" cg-busy='loading'>



					<!-- Message handling -->
					<div class="col-lg-12">
						<div class="alert alert-success alert-dismissible fade in" role="alert" ng-show="success != null">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							<span class="sr-only">Success: </span>
							{{success}}
						</div>
						<div class="alert alert-danger alert-dismissible fade in" role="alert" ng-show="error != null">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							<span class="sr-only">Error: </span>
							{{error}}
						</div>
					</div>
					<!-- / message handling -->



					<b>Total companies:</b>
					{{companyList.length}}
					<br>
					<br>

					<div class="row">
						<div class="col-md-5">
							<div class="input-group">

								<input type="text" class="form-control" placeholder="Search" ng-model='searchText'>
								<div class="input-group-btn">
									<button class="btn btn-default">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</div>


						<div class="col-md-7">
							<a class="btn btn-primary pull-right" ng-href='/eureka_webservice/admin/company/add.jsp' target="_self">
								<i class="fa fa-plus fa-lg"></i>
								Add Company
							</a>
						</div>

					</div>
					<!-- /row -->

					<br>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>
										<a target="_self" href="#" ng-click="sortType = 'name'; sortReverse = !sortReverse">
											Company
											<span ng-show="sortType == 'name' && !sortReverse" class="fa fa-caret-down"></span>
											<span ng-show="sortType == 'name' && sortReverse" class="fa fa-caret-up"></span>
										</a>
									</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="company in companyList | orderBy:sortType:sortReverse | filter:searchText track by $index">
									<td>{{company.name}}</td>
									<td>
										<a target="_self"
											ng-href="/eureka_webservice/LoadAdminViewCompanyMonthlySpending?company={{company.companyCode}}&name={{company.name}}"
										>View Monthly Spending</a>
										<br>
										<a target="_self"
											ng-href="/eureka_webservice/LoadAdminViewCompanyWeeklySpending?company={{company.companyCode}}&name={{company.name}}"
										>View Weekly Spending</a>
									</td>
									<td>
										<a target="_self" ng-href="/eureka_webservice/admin/company/edit.jsp?companyId={{company.companyId}}">
											<button type="button" class="btn btn-link btn-xs">
												<i class="fa fa-pencil fa-2x"></i>
											</button>
										</a>
									</td>

								</tr>
							</tbody>
						</table>
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
		app.controller('ViewCompanyController', [
				'$scope',
				'$http',
				'$window',
				function($scope, $http, $window) {

					$scope.success = angular
							.copy($window.sessionStorage.success);
					$scope.error = angular.copy($window.sessionStorage.error);
					$window.sessionStorage.removeItem('success');
					$window.sessionStorage.removeItem('error');

					$scope.sortType = 'name'; // set the default sort type
					$scope.sortReverse = false; // set the default sort order

					$scope.loading = $http({
						method : 'GET',
						url : '/eureka_webservice/GetAllCompaniesServlet'
					}).then(function successCallback(response) {
						$scope.companyList = response.data;
						console.log(response.data);
					});

				} ]);
	</script>


</body>

</html>
