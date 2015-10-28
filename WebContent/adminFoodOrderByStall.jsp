<!DOCTYPE html>
<%@ page import="model.FoodOrder"%>
<%@ page import="model.ModifierChosen"%>
<%@ page import="model.FoodOrderItem"%>
<%@ page import="model.FoodDisplayObject"%>
<%@ page import="model.Employee"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>DABAO Food Cart</title>

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
	<%
		if (request.getAttribute("foodOrders") != null) {
			ArrayList<FoodDisplayObject> foodDisplayObjectList = (ArrayList<FoodDisplayObject>) request
					.getAttribute("foodOrders");
	%>
	<div class="row center">
		<div class="col-xs-3 center">
			<form action="ChineseRetrieveFoodOrderServlet" method="post">
				<button type="submit" class="btn btn-success btn-block">Get
					Chinese Food Names</button>
			</form>
		</div>
	</div>


	<%
		for (FoodDisplayObject fDO : foodDisplayObjectList) {
				String stallName = fDO.getStallName();
				ArrayList<FoodOrderItem> foodOrderItemList = fDO.getFoodOrderItem();
	%>
	</br>
	<div class="container">
		<div class="row center">
			<div class="col-xs-10 col center">
				<div class="panel panel-default">

					<div class="panel-heading">
						<%=stallName%>
					</div>
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
										ArrayList<ModifierChosen> modifierList = new ArrayList<ModifierChosen>(fOI.getModifierList());
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
	</div>
	</br>
	</br>
	<%
		}
	%>
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