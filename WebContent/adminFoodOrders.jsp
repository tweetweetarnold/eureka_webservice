<!DOCTYPE html>
<%@ page import="model.*"%>
<%@page import="org.json.simple.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@include file="adminProtect.jsp"%>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>DABAO</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="resources/js/jquery-1.11.3.js"></script>
<!-- <script src="resources/js/jquery-1.11.3.min.js"></script> -->
<script src="resources/js/dabao/dabao.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<link href="resources/css/dabao/dabao.css" rel="stylesheet">
<!-- <link href="resources/css/bootstap.min.css" rel="stylesheet"> -->
<link href="resources/css/dabao/starter-template.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0"> <jsp:include
	page="headerfooter/adminHeader.jsp" /> </nav>


	<div class="container">
		<div class="row center">
			<div class="col-xs-10 col center"">
				<div class="panel panel-default">

					<div class="panel-heading">
						<%
							if (request.getAttribute("NoOrders") == null) {
								if (request.getAttribute("foodOrders") != null) {
									JSONObject foodOrders = (JSONObject) request.getAttribute("foodOrders");
									Iterator iter = foodOrders.keySet().iterator();
									out.println("Number of people " + (foodOrders.size() - 1));
						%>
					</div>



					<table class="table table-striped">


						<tr>
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
								href="ProcessAdminGetEmployeeServlet?username=<%=username%>"><%=username%></a></td>
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
	<%
		DecimalFormat df = new DecimalFormat("####0.00");
	%>
	</div>
	<div class="row center">
			<div class="col-xs-10 col center"">
	<h3>Total Price : $<%=df.format(totalPrice)%></h3>
	</div></div>
	<div class="row center">
	<div class="col-xs-3 center">
		<form action="retrieveFoodOrdersServlet" method="post">
			<button type="submit"  class="btn btn-success btn-block">Go to print</button>
		</form>
</div></div>
	

	<%
		}
		} else {
	%>
	<h1>No Orders Today!</h1>

	<%
		}
	%>

	











	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<!--     <script src="../../dist/js/bootstrap.min.js"></script> -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--     <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->


	<!-- Google Analytics -->
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');
		ga('create', 'UA-68676403-1', 'auto');
		ga('send', 'pageview');
	</script>


</body>
</html>