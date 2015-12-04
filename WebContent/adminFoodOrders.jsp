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
<script src="resources/js/dabao/dabao.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<link href="resources/css/dabao/dabao.css" rel="stylesheet">
<link href="resources/css/dabao/starter-template.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- header -->
	<jsp:include page="headerfooter/adminHeader.jsp" />


	<div class="container" style="margin-top: 50px;">

		<div class="row center" style="">
			<div class="col-xs-3 center" style="float: none;">
				<form action="RetrieveFoodOrdersServlet" method="post">
					<button style="margin: 0px, auto;" type="submit" class="btn btn-lg btn-success btn-block">Group by stalls</button>
				</form>
			</div>
		</div>

		<br>
		<br>
		<div class="row center">
			<div class="col-xs-10 col center" style="float: none;">
				<div class="panel panel-default" style="float: none;">

					<div class="panel-heading">
						<div class="panel-title">
							<%
								/* HttpSession session = request.getSession(); */
													if (session.getAttribute("NoOrders") == null) {
																																							
														if (session.getAttribute("foodOrders") != null) {
															JSONObject foodOrders = (JSONObject) session.getAttribute("foodOrders");
															Iterator iter = foodOrders.keySet().iterator();
															out.println("Number of people: " + (foodOrders.size() - 1));
							%>
						</div>
					</div>

					<table class="table table-striped">

						<tr>
							<th style="text-align: center;">Number</th>
							<th style="text-align: left;">Name</th>
							<th style="text-align: left;">Items</th>
							<th style="text-align: center;">Quantity</th>
							<th style="text-align: center;">Price</th>
						</tr>
						<%
							
						%>
						<%
							double totalPrice = 0.0;
								int listSize = 0;
								int number = 0;
								while (iter.hasNext()) {
									String email = (String) iter.next();
									if (!email.equals("totalPrice")) {
										ArrayList<FoodOrderItem> foodOrderItemList = (ArrayList<FoodOrderItem>) foodOrders
												.get(email);
										listSize = foodOrderItemList.size();
										String foodName = foodOrderItemList.get(0).getFood().getName();
										DecimalFormat df3 = new DecimalFormat("####0.00");
						%>
						<tr>
							<td style="text-align: center;" rowspan=<%=listSize%>><%=++number%></td>
							<td style="text-align: left;" rowspan=<%=listSize%>>
								<a href="ProcessAdminGetEmployeeServlet?email=<%=email%>"><%=email%></a>
							</td>
							<td style="text-align: left;"><%=foodName%></td>
							<td style="text-align: center;"><%=foodOrderItemList.get(0).getQuantity()%></td>
							<td style="text-align: center;">
								$<%=df3.format(foodOrderItemList.get(0).getPrice())%></td>
						</tr>
						<tr>
							<%
								for (int i = 1; i < listSize; i++) {
									FoodOrderItem tempItem = foodOrderItemList.get(i);
									foodName = tempItem.getFood().getName();
									int quantity = tempItem.getQuantity();
									double price = tempItem.getPrice();
									DecimalFormat df2 = new DecimalFormat("####0.00");
							%>
							<td style="text-align: left;"><%=foodName%></td>
							<td><%=quantity%></td>
							<td>
								$<%=df2.format(price)%></td>

						</tr>
						<%
							}
							} else {
								// 						totalPrice =Double.parseDouble((String)foodOrders.get(username));
								totalPrice = (Double) foodOrders.get(email);
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
		<div class="col-xs-10 col center" style="float: none;">
			<h3>
				Total Price : $<%=df.format(totalPrice)%></h3>
		</div>
	</div>



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