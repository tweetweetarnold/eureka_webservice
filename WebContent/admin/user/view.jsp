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


			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-lg-12" cg-busy='loading'>

					<b>Total users:</b>
					{{data.length}}
					<br>
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
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="user in data track by $index">
									<td>{{user.company.name}}</td>
									<td>{{user.name}}</td>
									<td>{{user.email}}</td>
									<td>
										{{user.createDate | date:'medium' : '+0800'}}</td>
									<td>{{user.amountOwed | currency}}</td>
									<td>{{user.status}}</td>
									<td>
										<a target="_self"
											ng-href="/eureka_webservice/LoadAdminViewUserOrderHistoryServlet?email={{user.email}}&name={{user.name}}"
										> Order History</a>
									</td>
									<td>
										<a ng-href="/eureka_webservice/admin/user/edit.jsp?email={{user.email}}">
											<button type="button" class="btn btn-link btn-xs">
												<i class="fa fa-pencil fa-2x"></i>
											</button>
										</a>
									</td>
									<td>
										<button type="button" class="btn btn-link btn-xs" data-toggle="modal" data-target="#modalDelete{{$index}}">
											<i class="fa fa-trash-o fa-2x"></i>
										</button>

										<!-- Modal delete -->
										<div class="modal fade" id="modalDelete{{$index}}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
											<div class="modal-dialog" role="document">
												<form action="/eureka_webservice/ProcessAdminDeleteUserServlet" method="post">
													<input type="hidden" name="emailID" ng-value="user.email">
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
																You are deleting user {{user.name}} ({{user.email}}).
																<br>
																<br>
																Are you sure you want to continue?
															</p>
														</div>
														<!-- / modal body -->

														<div class="modal-footer">
															<!--  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
															<button type="button" class="btn btn-default" data-dismiss="modal">No, keep the user</button>
															<button type="submit" class="btn btn-danger">Yes, delete the user</button>
														</div>
														<!-- / modal footer -->
													</div>
													<!-- / modal content -->
												</form>
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
		app.controller('ViewUserController', [
				'$http',
				'$scope',
				'$window',
				function($http, $scope, $window) {

					$scope.success = angular
							.copy($window.sessionStorage.success);
					$scope.error = angular.copy($window.sessionStorage.error);
					$window.sessionStorage.removeItem('success');
					$window.sessionStorage.removeItem('error');

					$scope.loading = $http({
						method : 'GET',
						url : '/eureka_webservice/GetAllUsersServlet'
					}).then(function successCallback(response) {
						console.log(response);
						console.log(response.data);
						$scope.data = response.data;
						$scope.display = response.status;
					}, function errorCallback(response) {
						alert(response);
					});

				}

		]);
	</script>

</body>

</html>
