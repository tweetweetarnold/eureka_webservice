<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="org.json.simple.JSONObject"%>

<title>DaBao - Admin Register</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/signin.css" rel="stylesheet">

<script src="resources/js/ie-emulation-modes-warning.js"></script>

</head>
<body>

	<div class="container">

		<form class="form-signin" method="post" action="ProcessAdminRegistrationServlet">
			<h2 class="form-signin-heading">Please insert fields</h2>

			<c:set value="${sessionScope.userInput}" var="userInput" />

			<!-- User input -->
			<input type="text" name="username" class="form-control" placeholder="Username" value="<c:out value="${userInput['username']}"/>" required>
			<input type="text" name="name" class="form-control" placeholder="Name" value="<c:out value="${userInput['name']}"/>" required>
			<input type="password" name="password" class="form-control" placeholder="Password (Min length 8 Characters)" onfocus="" required>
			<input type="password" name="confirmPwd" class="form-control" placeholder="Confirm Password" required>
			
			<input type="text" name="contactNo" class="form-control" placeholder="Contact Number (8 Digits)" value="<c:out value="${userInput['contactNo']}"/>" required>
			

			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
			<br>
			<div align="center">
				<a href="adminLogin.jsp">Return to Admin Login</a>
			</div>
			<br>


			<!-- Error message handling -->
			<c:if test="${not empty sessionScope.error}">
				<div class="alert alert-danger" role="alert">
					<b>Error!</b>
					<br>
					<c:out value="${error}" />
				</div>
				<c:remove var="error" scope="session" />
			</c:if>
		</form>

		<c:remove var="userInput" scope="session" />

	</div>


	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>

	<!-- 	Google Analytics -->
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