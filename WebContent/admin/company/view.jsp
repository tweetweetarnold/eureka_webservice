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

					<b>Total companies:</b>
					{{companyList.length}}
					<br>
					<br>
					<a class="btn btn-primary" ng-href='/eureka_webservice/admin/company/add.jsp' target="_self">
						<i class="fa fa-plus fa-lg"></i>
						Add Company
					</a>
					<br>
					<br>

					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th>Company</th>
									<th>Code</th>
									<th>Date Joined</th>
									<th>Delivery Points</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="company in companyList track by $index">
									<td>{{company.name}}</td>
									<td>{{company.companyCode}}</td>
									<td>{{company.createDate | date:'medium' : '+0800'}}</td>
									<td>
										<p ng-repeat="deliveryPoint in company.deliveryPointSet track by $index">{{deliveryPoint}},</p>
									</td>
									<td>
										<a target="_self"
											ng-href="/eureka_webservice/LoadAdminViewCompanyMonthlySpending?company={{company.companyCode}}&name={{company.name}}"
										>Monthly Spending Summary</a>
										<br>
										<a target="_self"
											ng-href="/eureka_webservice/LoadAdminViewCompanyWeeklySpending?company={{company.companyCode}}&name={{company.name}}"
										>Weekly Spending Summary</a>
									</td>
									<td>
										<a target="_self" ng-href="#">
											<button type="button" class="btn btn-link btn-xs">
												<i class="fa fa-pencil fa-2x"></i>
											</button>
										</a>
										<a target="_self" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="Help"
											data-content="This feature is not ready yet." data-placement="left"
										>
											<i class="fa fa-question-circle"></i>
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
		app.controller('ViewCompanyController', [ '$scope', '$http',
				function($scope, $http) {

					$scope.loading = $http({
						method : 'GET',
						url : '/eureka_webservice/GetAllCompaniesServlet'
					}).then(function successCallback(response) {
						$scope.companyList = response.data;
						console.log(response.data);
					});

				} ]);
	</script>


	<script>
		$(function() {
			$('[data-toggle="popover"]').popover();
		});
	</script>


</body>

</html>
