<!DOCTYPE html>
<html lang="en">

<%@include file="adminProtect.jsp"%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DABAO</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet"
>

<!-- MetisMenu CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet"
>

<!-- Timeline CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"
>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

</head>

<body>

	<div id="wrapper">

		<%@include file="headerfooter/adminHeader2.jsp"%>


		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Today's Orders</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->

			<div style="float: right;
	margin-bottom: 20px;">
				<form action="RetrieveFoodOrdersServlet" method="post">
					<button style="margin: 0px, auto;" type="submit" class="btn btn-lg btn-success btn-block">Group by stalls</button>
				</form>
			</div>


			<c:set scope="session" value="foodOrders" var="foodOrdersList" />

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Data</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#no-group" data-toggle="tab">No Group</a>
								</li>
								<li>
									<a href="#profile" data-toggle="tab">Group by Stalls</a>
								</li>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div class="tab-pane fade in active" id="no-group">
									<div class="dataTable_wrapper">
										<br>
										<table class="table table-striped table-bordered table-hover" id="dataTables-example">
											<thead>
												<tr>
													<th>Number</th>
													<th>Email</th>
													<th>Item(s)</th>
													<th>Quantity</th>
													<th>Price</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${sessionScope.todayOrders}" var="order" varStatus="loop">
													<tr class="odd gradeX">
														<td rowspan="${fn:length(order.value) + 1}">${loop.index + 1}</td>
														<td rowspan="${fn:length(order.value) + 1}">${order.key}</td>

														<c:forEach items="${order.value}" var="foodOrderItem">
															<tr>
																<td>${foodOrderItem.food.name}</td>
																<td>${foodOrderItem.quantity}</td>
																<td>$${foodOrderItem.priceString}</td>
															</tr>
														</c:forEach>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<div class="tab-pane fade" id="profile">
									<h4>Profile Tab</h4>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
										dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
										commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
										nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim
										id est laborum.</p>
								</div>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->

		</div>
		<!-- /#wrapper -->


		<!-- jQuery -->
		<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/jquery/dist/jquery.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

		<!-- Metis Menu Plugin JavaScript -->
		<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.js"></script>

		<!-- Morris Charts JavaScript -->
		<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/raphael/raphael-min.js"></script>
		<!-- <script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.min.js"></script> -->
		<!-- <script src="resources/css/startbootstrap-sb-admin-2-1.0.7/js/morris-data.js"></script> -->

		<!-- Custom Theme JavaScript -->
		<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>
</body>

</html>
