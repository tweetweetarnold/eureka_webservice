
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title></title>
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
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">SB Admin v2.0</a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-left">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <!-- Navigation --> <nav
						class="navbar navbar-default navbar-static-top" role="navigation"
						style="margin-bottom: 0"> <jsp:include
						page="headerfooter/adminHeader.jsp" /> </nav>
			</a> <%
 	if (request.getAttribute("foodOrders") != null) {
 		ArrayList<FoodDisplayObject> foodDisplayObjectList = (ArrayList<FoodDisplayObject>) request
 				.getAttribute("foodOrders");
 		%>
 		
 		<form action="ChineseRetrieveFoodOrderServlet" method="post">
 			<input type = "submit" value="Get Chinese Food Names">
 		</form>
 		
 		<% 

 		for (FoodDisplayObject fDO : foodDisplayObjectList) {
 			String stallName = fDO.getStallName();
 			ArrayList<FoodOrderItem> foodOrderItemList = fDO.getFoodOrderItem();
 %> </br>
				<div class="row">
					<div class="col-lg-12">
						<!-- /.panel -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<%=stallName%>
							</div>
							<div class="table-responsive">
								<table class="table table-striped">
									<tr>
										<th>food</th>
										<th>addons</th>
										<th>quantity</th>
										<th>price</th>
										<th>users</th>
									</tr>
									<%
										double totalPrice = 0;

												for (FoodOrderItem fOI : foodOrderItemList) {
													String foodName = fOI.getFood().getName();
													ArrayList<ModifierChosen> modifierList = new ArrayList<ModifierChosen>(fOI.getModifierChosenList());
													int quantity = fDO.getQuantity(fOI);
													double price = fOI.getPrice();
													totalPrice += quantity * price;
													ArrayList<String> userList = fDO.getUsernameList(fOI);
									%>
									<tr>
										<td><%=foodName%></td>
										<td>
											<table>
												<%
													for (ModifierChosen mod : modifierList) {
																	String modName = mod.getName();
												%>
												<tr>
													<td><%=modName%></td>
												</tr>
												<%
													}
												%>
											</table>
										</td>
										<td><%=quantity%></td>
										<td><%=price%></td>

										<td>
											<table>
												<%
													for (String username : userList) {
												%>

												<tr>
													<td><%=username%></td>
												</tr>
												<%
													}
												%>
											</table>
										</td>

									</tr>
									<%
										}
									%>

								</table>
								Total Price =<%=totalPrice%>
							</div>
						</div>

					</div>
				</div> </br> </br> <%
 	}
 %> <%
 	}
 %>
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