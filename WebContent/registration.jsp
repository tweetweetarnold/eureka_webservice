<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="org.json.simple.JSONObject"%>

<title>DaBao - Register</title>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/signin.css" rel="stylesheet">

<script src="resources/js/ie-emulation-modes-warning.js"></script>

</head>
<body>

	<div class="container">

		<form class="form-signin" method="post" action="ProcessRegistrationServlet">
			<h2 class="form-signin-heading">Please insert fields</h2>

			<%
				String username = "";
								String name = "";
								String contactNo = "";
								String bankAcc = "";
								String company = "";

								if(session.getAttribute("userInput") != null){
									HashMap<String,String> map = (HashMap<String,String>) session.getAttribute("userInput");
									username = map.get("username");
									name =  map.get("name");
									contactNo = map.get("contactNo");
									bankAcc = map.get("bankAcc");
									company = map.get("company");
								}
			%>

			<!-- User input -->
			<input type="text" name="username" class="form-control" placeholder="Username" value="<%=username%>" required>
			<input type="text" name="name" class="form-control" placeholder="Name" value="<%=name%>" required>
			<input type="password" name="password" class="form-control" placeholder="Password" required>
			<input type="password" name="confirmPwd" class="form-control" placeholder="Confirm Password" required>
			<input type="text" name="contactNo" class="form-control" placeholder="Contact Number" value="<%=contactNo%>" required>
			<input type="text" name="bankAcc" class="form-control" placeholder="Bank Account Number" value="<%=bankAcc%>" required>
			<input type="text" name="company" class="form-control" placeholder="Company" value="<%=company%>" required>

			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
			<br>
			<div align="center">
				<a href="login.jsp">Return to Login</a>
			</div>
			<br>


			<!-- Error message handling -->
			<%
				if (session.getAttribute("error") != null) {
			%>
			<div class="alert alert-danger" role="alert">
				<b>Error!</b>
				<br>
				<%=session.getAttribute("error")%>
			</div>
			<%
				session.removeAttribute("error");
						}
			%>
		</form>

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