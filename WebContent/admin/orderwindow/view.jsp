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

<body ng-app="myApp" ng-controller="ViewOrderWindowController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Order Window Management: All</h1>
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

					<b>Total order windows:</b>
					{{data.length}}
					<br>
					<br>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>Id</th>
									<th>Company</th>
									<th>Canteen</th>
									<th>Discount</th>
									<th>Date Created</th>
									<th>Start Date</th>
									<th>End date</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="window in data track by $index">
									<td>{{window.windowId}}</td>
									<td>{{window.company.name}}</td>
									<td>{{window.canteen.name}}</td>
									<td>{{window.priceModifierList[0].value | currency}}</td>
									<td>{{window.createDate | date:'medium' : '+0800'}}</td>
									<td>{{window.startDateFormatted | date:'medium' : '+0800'}}</td>
									<td>{{window.endDateFormatted | date:'medium' : '+0800'}}</td>
									<td>
										<a ng-href="/eureka_webservice/LoadAdminEditOrderWindowServlet?windowId={{window.windowId}}">
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
														<p>
															<b>WARNING: </b>
															You are deleting Order Window ID: {{window.windowId}}.
															<br>
															<br>
															Are you sure you want to continue?
														</p>
													</div>
													<!-- / modal body -->

													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">No, keep the window</button>
														<button type="button" ng-click="deleteWindow(window.windowId)" class="btn btn-danger">Yes, delete
															the window</button>
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
						'ViewOrderWindowController',
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

									$scope.loading = $http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetAllOrderWindowsServlet'
											}).then(
											function successCallback(response) {
												$scope.data = response.data;
												console.log(response);
											});

									$scope.deleteWindow = function(windowId) {

										console.log(windowId);

										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/DeleteOrderWindowServlet',
													data : {
														windowId : windowId
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
																alert(response);
															}
															$window.location.href = '/eureka_webservice/admin/orderwindow/view.jsp';
														});
									};
								} ])
	</script>

</body>

</html>
