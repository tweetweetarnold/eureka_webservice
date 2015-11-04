<!DOCTYPE html>
<%@ page import="model.*"%>
<%@page import="org.json.simple.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@include file="adminProtect.jsp"%>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<%
	ResourceBundle resourceBundle = ResourceBundle.getBundle("RBExample2", Locale.CHINA);
%>
</head>

<body>
	<jsp:include page="headerfooter/adminHeader.jsp" />
	<%
		if (request.getAttribute("foodOrders") != null) {
			DecimalFormat df2 = new DecimalFormat("####0.00");
		ArrayList<FoodDisplayObject> foodDisplayObjectList = (ArrayList<FoodDisplayObject>) request
		.getAttribute("foodOrders");
	%>
	<div class="row center">
		<div class="col-xs-3 center" style="float: right;">
			<form action="RetrieveFoodOrdersServlet" method="post">
				<button type="submit" class="btn btn-lg btn-success btn-block"><%=resourceBundle.getString("translate_to_english")%>
				</button>
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
						<div class="panel-title">
							<%=stallName%>
							<div style="float: right;">
								<%=foodOrderItemList.get(0).getFood().getStall().getContactNo()%>
							</div>
						</div>
					</div>
					<table class="table table-striped">
						<tr>
							<th><%=resourceBundle.getString("food")%></th>
							<th>addons</th>
							<th style="text-align: center;"><%=resourceBundle.getString("quantity")%></th>
							<th style="text-align: center;"><%=resourceBundle.getString("price")%></th>
							<th><%=resourceBundle.getString("user")%></th>
						</tr>
						<%
							double totalPrice = 0;

																				for (FoodOrderItem fOI : foodOrderItemList) {
																					String foodName = fOI.getFood().getName();
																					String foodNameUnderscore = foodName.replaceAll(" ", "_");
																					ArrayList<ModifierChosen> modifierList = new ArrayList<ModifierChosen>(fOI.getModifierChosenList());
																					int quantity = fDO.getQuantity(fOI);
																					double price = fOI.getPrice();
																					totalPrice += quantity * price;
																					ArrayList<String> userList = fDO.getUsernameList(fOI);
						%>
						<tr>
							<td style="text-align: left;"><%=resourceBundle.getString(foodNameUnderscore)%></td>
							<td>
								<table>
									<%
										for (ModifierChosen mod : modifierList) {
																																							String modName = mod.getName();
																																							String modNameUnderscore = modName.replaceAll(" ", "_");
									%>
									<tr>
										<td><%=resourceBundle.getString(modNameUnderscore)%></td>
									</tr>
									<%
										}
									%>
								</table>
							</td>
							<td><%=quantity%></td>
							<td>
								$<%=df2.format(price)%></td>

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
					<%
						DecimalFormat df = new DecimalFormat("####0.00");
					%>
					<h3>
						<%=resourceBundle.getString("total_price")%>
						: $<%=df.format(totalPrice)%></h3>
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