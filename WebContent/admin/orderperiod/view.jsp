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

<body ng-app="myApp" ng-controller="ViewOrderPeriodController">

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Order Period Management: All</h1>
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

					<b>Total Order Periods:</b>
					{{data.length}}
					<br>
					<br>
					<a class="btn btn-primary" ng-href='/eureka_webservice/LoadAdminAddNewPeriodServlet' target="_self">
						<i class="fa fa-plus fa-lg"></i>
						Add Order Period
					</a>
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
									<th>
										<a href="#" ng-click="sortType = 'createDate'; sortReverse = !sortReverse">
											Date Created
											<span ng-show="sortType == 'createDate' && !sortReverse" class="fa fa-caret-down"></span>
											<span ng-show="sortType == 'createDate' && sortReverse" class="fa fa-caret-up"></span>
										</a>
									</th>
									<th>
										<a href="#" ng-click="sortType = 'start'; sortReverse = !sortReverse">
											Start Date/Time
											<span ng-show="sortType == 'start' && !sortReverse" class="fa fa-caret-down"></span>
											<span ng-show="sortType == 'start' && sortReverse" class="fa fa-caret-up"></span>
										</a>
									</th>
									<th>End Date/Time</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="period in data | orderBy:sortType:sortReverse track by $index">
									<td>{{period.periodId}}</td>
									<td>{{period.company.name}}</td>
									<td>{{period.canteen.name}}</td>
									<td>{{period.priceModifierList[0].value | currency}}</td>
									<td>{{period.createDate | date:'medium' : '+0800'}}</td>
									<td>{{period.startDateFormatted | date:'medium' : '+0800'}}</td>
									<td>{{period.endDateFormatted | date:'medium' : '+0800'}}</td>
									<td>
										<a ng-href="/eureka_webservice/LoadAdminEditOrderPeriodServlet?periodId={{period.periodId}}">
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
															You are deleting Order Period ID: {{period.periodId}}.
															<br>
															<br>
															Are you sure you want to continue?
														</p>
													</div>
													<!-- / modal body -->

													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">No, keep the period</button>
														<button type="button" ng-click="deletePeriod(period.periodId)" class="btn btn-danger">Yes, delete
															the period</button>
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
						'ViewOrderPeriodController',
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

									$scope.sortType = 'start'; // set the default sort type
									$scope.sortReverse = false; // set the default sort order

									$scope.loading = $http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetAllOrderPeriodsServlet'
											}).then(
											function successCallback(response) {
												$scope.data = response.data;
												console.log(response);
											});

									$scope.deletePeriod = function(periodId) {

										console.log(periodId);

										$http(
												{
													method : 'POST',
													url : '/eureka_webservice/DeleteOrderPeriodServlet',
													data : {
														periodId : periodId
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
															$window.location.href = '/eureka_webservice/admin/orderperiod/view.jsp';
														});
									};
								} ])
	</script>

</body>

</html>
