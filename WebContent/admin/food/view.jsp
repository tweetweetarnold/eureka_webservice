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

<body ng-app='myApp' ng-controller='ViewFoodController'>

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">{{stall.name}}:&nbsp;Foods</h1>

					<!-- breadcrumb -->
					<ol class="breadcrumb">
						<li>
							<a target="_self" href="/eureka_webservice/admin/canteen/view.jsp">Canteens</a>
						</li>
						<li>
							<a target="_self" ng-href="/eureka_webservice/admin/stall/view.jsp?canteenId={{canteenId}}">Stalls</a>
						</li>
						<li class="active">Foods</li>
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



					<b>Total foods:</b>
					{{foodList.length}}
					<br>
					<br>

					<form action="/eureka_webservice/admin/food/add.jsp">
						<input type="hidden" name="stallId" ng-value="stall.stallId">
						<button type="submit" class="btn btn-primary">Add food</button>
					</form>
					<br>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>ID</th>
									<th>Food</th>
									<th>Price</th>
									<th>Create Date</th>
									<th>Image</th>
									<th>Add-On(s)</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat='food in foodList track by food.foodId'>
									<td>{{food.foodId}}</td>
									<td>
										{{food.name}}
										<br>
										{{food.chineseName}}
									</td>
									<td>{{food.price | currency}}</td>
									<td>{{food.createDate | date:'medium' : '+0800'}}</td>
									<td>
										<img ng-src="{{food.imageDirectory}}"
											onerror="this.src='http://res.cloudinary.com/dmeln4k8n/image/upload/c_pad,h_169,w_263/v1455951761/default/img-error.jpg'"
											style="width: 263px;
	height: 169px;"
										/>
									</td>
									<td>
										<p ng-repeat='modifier in food.modifierList track by modifier.modifierId'>
											{{modifier.name}}&nbsp;{{modifier.chineseName}} - ({{modifier.price | currency}})
											<br>
										</p>
										<a target="_self" ng-href='/eureka_webservice/admin/food/addon-add.jsp?foodId=${food.foodId}'>Add Add-On</a>
									</td>
									<td>
										<a target="_self" ng-href="/eureka_webservice/LoadAdminEditFoodServlet?foodId={{food.foodId}}">
											<button type="button" class="btn btn-link btn-xs">
												<i class="fa fa-pencil fa-2x"></i>
											</button>
										</a>
									</td>
									<td>
										<button type="button" class="btn btn-link btn-xs" data-toggle="modal"
											data-target="#modalDelete{{food.foodId}}"
										>
											<i class="fa fa-trash-o fa-2x"></i>
										</button>

										<!-- Modal delete -->
										<div class="modal fade" id="modalDelete{{food.foodId}}" tabindex="-1" role="dialog"
											aria-labelledby="myModalLabel"
										>
											<div class="modal-dialog" role="document">
												<form method="post" action="/eureka_webservice/ProcessAdminDeleteFoodServlet">
													<input type="hidden" name="foodId" ng-value="food.foodId">
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
																You are deleting food
																<b> {{food.name}}</b>
																from stall {{stall.name}}.
																<br>
																<br>
																Are you sure you want to continue?
															</p>
														</div>
														<!-- / modal body -->

														<div class="modal-footer">
															<button type="button" class="btn btn-default" data-dismiss="modal">No, keep my food</button>
															<button type="submit" class="btn btn-danger">Yes, delete the food</button>
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
						'ViewFoodController',
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

									$scope.loading = $http(
											{
												method : 'GET',
												url : '/eureka_webservice/GetAllFoodsUnderStallServlet',
												headers : {
													'Content-Type' : 'application/json; charset=UTF-8'
												},
												params : {
													stallId : $location
															.search().stallId
												}
											})
											.then(
													function successCallback(
															response) {
														console.log(response);
														$scope.canteenId = response.data.canteenId;
														$scope.stall = response.data.stall;
														$scope.foodList = response.data.foods;
													});
								} ]);
	</script>
</body>

</html>
