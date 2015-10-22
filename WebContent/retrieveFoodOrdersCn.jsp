<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	ResourceBundle resourceBundle = ResourceBundle.getBundle("RBExample2", Locale.CHINA);
%>

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
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0"> <jsp:include
	page="headerfooter/adminHeader.jsp" /> </nav>
</head>
<body>
	<%
		if (request.getAttribute("foodOrders") != null) {
			ArrayList<FoodDisplayObject> foodDisplayObjectList = (ArrayList<FoodDisplayObject>) request
					.getAttribute("foodOrders");

			for (FoodDisplayObject fDO : foodDisplayObjectList) {
				String stallName = fDO.getStallName();
				ArrayList<FoodOrderItem> foodOrderItemList = fDO.getFoodOrderItem();
	%>
	</br>

	

	<div class="row">
		<div class="col-lg-9">
		<%=stallName%>
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
											String foodNameUnderscore = foodName.replaceAll(" ", "_");
											ArrayList<ModifierChosen> modifierList = new ArrayList<ModifierChosen>(fOI.getModifierList());
											int quantity = fDO.getQuantity(fOI);
											double price = fOI.getPrice();
											totalPrice += quantity * price;
											ArrayList<String> userList = fDO.getUsernameList(fOI);
							%>
							<tr>
								<td><%=resourceBundle.getString(foodNameUnderscore)%></td>
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
					</div></div>
					<%
						}
					%>
					<%
						}
					%>
				
</body>
</html>