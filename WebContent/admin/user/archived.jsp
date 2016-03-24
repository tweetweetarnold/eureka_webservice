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

<body ng-app="myApp" ng-controller="ViewUserController" ng-cloak>

	<div id="wrapper">

		<%@include file="/admin/adminHeader.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">User Management: Archived</h1>
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
				<div class="col-lg-12" cg-busy='loading'>

					<b>Total users:</b>
					{{data.length}}

					<br>

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

					<br>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>
										<a href="#" ng-click="sortType = 'company'; sortReverse = !sortReverse">
											Company
											<span ng-show="sortType == 'company' && !sortReverse" class="fa fa-caret-down"></span>
											<span ng-show="sortType == 'company' && sortReverse" class="fa fa-caret-up"></span>
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
										<a href="#" ng-click="sortType = 'createDate'; sortReverse = !sortReverse">
											Date Joined
											<span ng-show="sortType == 'createDate' && !sortReverse" class="fa fa-caret-down"></span>
											<span ng-show="sortType == 'createDate' && sortReverse" class="fa fa-caret-up"></span>
										</a>
									</th>
									<th>O/S</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="user in data | orderBy:sortType:sortReverse | filter:searchText track by $index">
									<td>{{user.company.name}}</td>
									<td>{{user.name}}</td>
									<td>{{user.email}}</td>
									<td>{{user.createDate | date:'medium' : '+0800'}}</td>
									<td>{{user.amountOwed | currency}}</td>
									<td>
										<a target="_self"
											ng-href="/eureka_webservice/LoadAdminViewUserOrderHistoryServlet?email={{user.email}}&name={{user.name}}"
										> Order History</a>
									</td>
									<td>
										<a ng-href="#">
											<button type="button" class="btn btn-link btn-xs" data-toggle="modal" data-target="#modalRevive{{$index}}">
												<i class="fa fa-undo fa-2x"></i>
											</button>
										</a>


										<!-- Modal delete -->
										<div class="modal fade" id="modalRevive{{$index}}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
														<h4 class="modal-title text-center" id="myModalLabel">Confirmation</h4>
													</div>
													<!-- / modal header -->
													<div class="modal-body">
														<p>
															<b>WARNING: </b>
															You are restoring user {{user.name}} ({{user.email}}) back to Active.
															<br>
															<br>
															Are you sure you want to continue?
														</p>
													</div>
													<!-- / modal body -->

													<div class="modal-footer" cg-busy='loading'>
														<button type="button" class="btn btn-default" data-dismiss="modal">No, leave the user</button>
														<button type="button" ng-click='reviveUser(user.email)' class="btn btn-danger">Yes, restore the
															user</button>
													</div>
													<!-- / modal footer -->
												</div>
												<!-- / modal content -->
											</div>
										</div>
										<!-- / Modal delete -->

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
		app
				.controller(
						'ViewUserController',
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

									$scope.sortType = 'company'; // set the default sort type
									$scope.sortReverse = false; // set the default sort order

									$scope.loading = $http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetAllArchivedUsersServlet'
											})
											.then(
													function successCallback(
															response) {
														console.log(response);
														console
																.log(response.data);
														$scope.data = response.data;
														$scope.display = response.status;
													},
													function errorCallback(
															response) {
														alert(response);
													});

									$scope.reviveUser = function(email) {
										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/SetUserToActiveServlet',
													data : {
														email : email
													}
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
														});
										$window.location.href = '/eureka_webservice/admin/user/archived.jsp';
									};

								}

						]);
	</script>

</body>

</html>
