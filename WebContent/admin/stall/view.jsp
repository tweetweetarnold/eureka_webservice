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

<body ng-app='myApp' ng-controller='ViewStallController'>

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">
			<div class="row">

				<div class="col-lg-12">
					<h1 class="page-header">{{canteen.name}}:&nbsp;Stalls</h1>

					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li>
							<a target="_self" href="/eureka_webservice/admin/canteen/view.jsp">Canteens</a>
						</li>
						<li class="active">Stalls</li>
					</ol>

				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div class="row" cg-busy='loading'>
				<div class="col-lg-12">


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



					<b>Total stalls:</b>
					{{stallList.length}}
					<br>
					<br>
					<a class="btn btn-primary" ng-href='/eureka_webservice/admin/stall/add.jsp?canteenId={{canteenId}}' target="_self">Add
						Stall</a>
					<br>
					<br>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>ID</th>
									<th>Stall</th>
									<th>Contact No</th>
									<th>Create Date</th>
									<th>Image</th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat='stall in stallList track by stall.stallId'>
									<td>{{stall.stallId}}</td>
									<td>{{stall.name}}</td>
									<td>{{stall.contactNo}}</td>
									<td>{{stall.createDate | date:'medium' : '+0800'}}</td>
									<td>
										<img ng-src="{{stall.imageDirectory}}"
											onerror="this.src='http://res.cloudinary.com/dmeln4k8n/image/upload/c_pad,h_231,w_173/v1455951761/default/img-error.jpg'"
										/>
									</td>
									<td>
										<a target='_self' ng-href="/eureka_webservice/admin/food/view.jsp?stallId={{stall.stallId}}">View all food</a>
									</td>
									<td>
										<a target='_self' ng-href='/eureka_webservice/LoadAdminEditStallServlet?stallId={{stall.stallId}}'>
											<button type="button" class="btn btn-link btn-xs">
												<i class="fa fa-pencil fa-2x"></i>
											</button>
										</a>
									</td>
									<td>
										<button type="button" class="btn btn-link btn-xs" data-toggle="modal"
											data-target="#modalDelete{{stall.stallId}}"
										>
											<i class="fa fa-trash-o fa-2x"></i>
										</button>

										<!-- Modal delete -->
										<div class="modal fade" id="modalDelete{{stall.stallId}}" tabindex="-1" role="dialog"
											aria-labelledby="myModalLabel"
										>
											<div class="modal-dialog" role="document">
												<form action="/eureka_webservice/ProcessAdminDeleteStallServlet" method="post">
													<input type="hidden" name="stallId" ng-value="stall.stallId">
													<input type="hidden" name="canteenId" ng-value="canteenId">
													
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
																You are deleting stall
																<b>{{stall.name}}</b>
																from canteen {{canteen.name}}.
																<br>
																<br>
																Are you sure you want to continue?
															</p>
														</div>
														<!-- / modal body -->

														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">No, keep my stall</button>
															<button type="submit" class="btn btn-danger">Yes, delete the stall</button>
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
	<!-- <script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.min.js"></script> -->
	<!-- <script src="resources/css/startbootstrap-sb-admin-2-1.0.7/js/morris-data.js"></script> -->

	<script src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>

	<script>
		app
				.controller(
						'ViewStallController',
						[
								'$http',
								'$location',
								'$scope',
								'$window',
								function($http, $location, $scope, $window) {

									$scope.success = angular
											.copy($window.sessionStorage.success);
									$scope.error = angular
											.copy($window.sessionStorage.error);
									$window.sessionStorage
											.removeItem('success');
									$window.sessionStorage.removeItem('error');

									$scope.canteenId = $location.search().canteenId;
									var canteenId = $location.search().canteenId;

									$scope.loading = $http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetAllStallsUnderCanteenServlet',
												params : {
													canteenId : canteenId
												}
											})
											.then(
													function successCallback(
															response) {
														console.log(response);
														$scope.canteen = response.data.canteen;
														$scope.stallList = response.data.stalls;
													});

								} ]);
	</script>
</body>

</html>
