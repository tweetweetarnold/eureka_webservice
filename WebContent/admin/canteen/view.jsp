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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Angular -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.min.js"></script>
<link href="/eureka_webservice/resources/angularbusy/angular-busy.min.css" rel="stylesheet">
<script src="/eureka_webservice/resources/angularbusy/angular-busy.min.js"></script>
<script src='/eureka_webservice/resources/js/myapp.js'></script>

</head>

<body ng-app="myApp" ng-controller="ViewCanteenController" ng-cloak>


	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Canteen Management</h1>

					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li class='active'>Canteens</li>
					</ol>

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

					<b>Total canteens:</b>
					{{data.length}}
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
							<a href='/eureka_webservice/admin/canteen/add.jsp' class="btn btn-primary pull-right">
								<i class="fa fa-plus fa-lg"></i>
								Add Canteen
							</a>
						</div>

					</div>
					<!-- /row -->


					<br>

					<div class="dataTable_wrapper">


						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>ID</th>
									<th>
										<a href="#" ng-click="sortType = 'name'; sortReverse = !sortReverse">
											Canteen
											<span ng-show="sortType == 'name' && !sortReverse" class="fa fa-caret-down"></span>
											<span ng-show="sortType == 'name' && sortReverse" class="fa fa-caret-up"></span>
										</a>
									</th>
									<th>Address</th>
									<th>Create Date</th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="canteen in data | orderBy:sortType:sortReverse | filter:searchText track by $index">
									<td>{{canteen.canteenId}}</td>
									<td>{{canteen.name | date:'medium' : '+0800'}}</td>
									<td>{{canteen.address | date:'medium' : '+0800'}}</td>
									<td>{{canteen.createDate | date:'medium' : '+0800'}}</td>
									<td>
										<a ng-href="/eureka_webservice/admin/stall/view.jsp?canteenId={{canteen.canteenId}}">View all stalls</a>
									</td>
									<td>
										<a ng-href='/eureka_webservice/admin/canteen/edit.jsp?canteenId={{canteen.canteenId}}'>
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
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
														<h4 class="modal-title text-center" id="myModalLabel">Confirmation</h4>
													</div>
													<!-- / modal header -->

													<div class="modal-body">
														<div cg-busy='loading2'></div>
														<p>
															<b>WARNING: </b>
															You are deleting Canteen
															<b>{{canteen.name}}</b>
															.
															<br>
															<br>
															Are you sure you want to continue?
														</p>
													</div>
													<!-- / modal body -->

													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">No, keep my canteen</button>
														<button type="button" ng-click='deleteCanteen(canteen.canteenId, canteen.name)' class="btn btn-danger"
															ng-disabled='loading2'
														>Yes, delete the canteen</button>
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
						'ViewCanteenController',
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

									$scope.sortType = 'name'; // set the default sort type
									$scope.sortReverse = false; // set the default sort order

									$scope.loading = $http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetAllCanteensServlet'
											})
											.then(
													function successCallback(
															response) {
														$scope.data = response.data;
														$scope.display = response.status;
													},
													function errorCallback(
															response) {
														console.log(response);
													});

									$scope.deleteCanteen = function(canteenId,
											canteenName) {
										console.log({
											canteenId : canteenId,
											canteenName : canteenName
										});

										$scope.loading2 = $http(
												{
													method : 'POST',
													url : '/eureka_webservice/DeleteCanteenServlet',
													data : {
														canteenId : canteenId,
														canteenName : canteenName
													}
												})
												.then(
														function successCallback(
																response) {
															console
																	.log(response);
															$window.sessionStorage.success = response.data.success;
															$window.location.href = '/eureka_webservice/admin/canteen/view.jsp';
														},
														(function errorCallback(
																response) {
															$window.sessionStorage.error = response.data.error;
															$window.location.href = '/eureka_webservice/admin/canteen/view.jsp';
														}));
									};

								}

						]);
	</script>
</body>

</html>
