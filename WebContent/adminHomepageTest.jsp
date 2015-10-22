<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="controller.*"%>
<%@page import="java.text.*"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Bootstrap Admin Theme</title>

<!-- Bootstrap Core CSS -->
<link
	href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link
	href="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/timeline.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/css/bower_components/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="resources/css/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<%
	List<Employee> employeeOweList = (List<Employee>) session.getAttribute("outstandingPayments");
	FoodOrderController foodOrderController = new FoodOrderController();
	List<FoodOrder> foodOrderList = (List<FoodOrder>) foodOrderController.getFoodOrderBetweenCutOff();
%>
</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<jsp:include page="headerfooter/adminHeader.jsp" />
			
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-comments fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">26</div>
									<div>New Comments!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-tasks fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">12</div>
									<div>New Tasks!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-shopping-cart fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"><%=foodOrderList.size()%>
									</div>
									<div>New Orders!</div>
								</div>
							</div>
						</div>
						<a href="GetTodayOrdersServlet">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-support fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"><%=employeeOweList.size()%></div>
									<div>Outstanding Payments</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Outstanding Payments
						</div>
						<!-- /.panel-heading -->

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Username</th>
										<th>Amount owed</th>
										<th>Email address</th>
										<th>Current Status</th>
										<th>Company</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (Employee e : employeeOweList) {
											String username = e.getUsername();
											double amountOwed = e.getAmountOwed();
											DecimalFormat f = new DecimalFormat("#.00");
											String userEmail = e.getEmail();
											String currentStatus = e.getStatus();
											String companyName = e.getCompany().getName();
									%>
									<tr>

										<td><a href="processAdminGetEmployeeServlet?username=<%=username%>"><%=username%></a></td></td>
										<td>$<%=f.format(amountOwed)%></td>

										<td><%=userEmail%></td>
										<td><%=currentStatus%></td>
										<td><%=companyName%></td>
									</tr>
									<%
										}
									%>
								</tbody>
								
							</table>
									<a href="SendEmailServlet" class="btn btn-default btn-block">Send Email to All</a>

						</div>

					<!-- /.panel-body -->
					<div class="panel-footer">
						<div class="input-group">
							<input id="btn-input" type="text" class="form-control input-sm"
								placeholder="Type your message here..." /> <span
								class="input-group-btn">
								<button class="btn btn-warning btn-sm" id="btn-chat">
									Send</button>
							</span>
						</div>
					</div>
					<!-- /.panel-footer -->
				</div>
				<!-- /.panel .chat-panel -->
			</div>
			<!-- /.col-lg-4 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script
		src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script
		src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/raphael/raphael-min.js"></script>
	<script
		src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.min.js"></script>
	<script
		src="resources/css/startbootstrap-sb-admin-2-1.0.7/js/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>

</body>

</html>
