
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DABAO</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Bootstrap core CSS -->
<!-- <link href="resources/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="resources/css/dabao/dabao.css" rel="stylesheet">

<!-- Custom styles for this template -->
<!-- <link href="resources/css/signin.css" rel="stylesheet"> -->
<link href="resources/css/dabao/signin.css" rel="stylesheet">

</head>
<body>
	<%
		Set<String> buildingSet = (Set<String>) request.getAttribute("buildingSet");
		System.out.println("JSP ... " + buildingSet.size());
		Iterator iter = buildingSet.iterator();
	%>
	
	
	<div class="container">

		<div style="max-width: 500px;
	margin-left: auto;
	margin-right: auto;">
	
	<form class="form-signin" action="ProcessSetDefaultDeliveryPointServlet" method="post" style="max-width: 500px;">
		<h2 class="form-signin-heading">Create an account:</h2>
			<br>
			<br>
			<br>
			<table>
				<tr>
					Hey There! <br>
					Please select the building where you want your food
					delivered to:
					<br>
					<br>
				</tr>
				<tr>
					<select name="deliveryPoint" required>
					<%
						while (iter.hasNext()) {
							String buildingName = (String)iter.next();
					%>
						<option value=<%=buildingName%>><%=buildingName %></option>
					<%
						}
					%>
					</select> 
				</tr>
			<tr>
				<br>
				<br>
				<button class="btn btn-lg btn-primary btn-block" type="submit" style="max-width: 300px;">
					<font face="verdana">Register</font>
				</button>
			</tr>
		</table>
	</form>
</body>
</html>