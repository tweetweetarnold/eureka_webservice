<%@page import="sun.misc.FpUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.FoodOrder"%>
<%@ page import="model.FoodOrderItem"%>
<%@ page import="model.Employee"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Daily Food Order Summary</title>
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
			style="margin-bottom: 0"> <jsp:include
			page="headerfooter/adminHeader.jsp" /> </nav>
		</br> </br>
		</br>
		<row> <%
 	if (request.getAttribute("NoOrders") == null) {
 		if (request.getAttribute("foodOrders") != null) {
 			JSONObject foodOrders = (JSONObject) request.getAttribute("foodOrders");
 			Iterator iter = foodOrders.keySet().iterator();
 			out.println("Number of people " + (foodOrders.size() - 1));
 %> </row>
		</br> </br>
		
		<div align="center">
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive">
						<table class="table table-striped table-hover" style="width: 60%;">

							<tr class="active">
								<th>Number</th>
								<th>Name</th>
								<th>Items</th>
								<th>Quantity</th>
								<th>Price</th>
							</tr>
							<%
								
							%>
							<%
								double totalPrice = 0.0;
										int listSize = 0;
										int number = 0;
										while (iter.hasNext()) {
											String username = (String) iter.next();
											if (!username.equals("totalPrice")) {
												ArrayList<FoodOrderItem> foodOrderItemList = (ArrayList<FoodOrderItem>) foodOrders
														.get(username);
												listSize = foodOrderItemList.size();
												String foodName = foodOrderItemList.get(0).getFood().getName();
							%>
							<tr>
								<td rowspan=<%=listSize%>><%=++number%></td>
								<td rowspan=<%=listSize%>><a
									href="RetrieveUserByUsernameServlet?username=<%=username%>"><%=username%></a></td>
								<td><%=foodName%></td>
								<td><%=foodOrderItemList.get(0).getQuantity()%></td>
								<td><%=foodOrderItemList.get(0).getPrice()%></td>
							</tr>
							<tr>
								<%
									for (int i = 1; i < listSize; i++) {
														FoodOrderItem tempItem = foodOrderItemList.get(i);
														foodName = tempItem.getFood().getName();
														int quantity = tempItem.getQuantity();
														double price = tempItem.getPrice();
								%>
								<td><%=foodName%></td>
								<td><%=quantity%></td>
								<td><%=price%></td>

							</tr>
							<%
								}
											} else {
												// 						totalPrice =Double.parseDouble((String)foodOrders.get(username));
												totalPrice = (Double) foodOrders.get(username);
											}
										}
							%>
						</table>
					</div>
				</div>
			</div>
		</div>
		</br> The total price is =
		<%=totalPrice%>
		<%
			}
			} else {
		%>
		<h1>No Orders Today!</h1>

		<%
			}
		%>
		<form action="retrieveFoodOrdersServlet" method="post">
			<input type="submit" value="Go to print" />
		</form>
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