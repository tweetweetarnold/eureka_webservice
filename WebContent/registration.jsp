<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>DaBao - Register</title>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/signin.css" rel="stylesheet">

<script src="resources/js/ie-emulation-modes-warning.js"></script>

</head>
<body>

	<div class="container">

		<form class="form-signin" method="post" action="/eureka_webservice/RegistrationServlet">
			<h2 class="form-signin-heading">Please insert fields</h2>
			<input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
			<input type="text" name="name" class="form-control" placeholder="Name" required>
			<input type="password" name="password" class="form-control" placeholder="Password" required>
			<input type="password" name="confirmPwd" class="form-control" placeholder="Confirm Password" required>
			<input type="text" name="contactNumber" class="form-control" placeholder="Contact Number" required>
			<input type="text" name="bankAcc" class="form-control" placeholder="Bank Account Number" required>
			<input type="text" name="company" class="form-control" placeholder="Company" required>

			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
		</form>

		<%
			String error = (String) request.getAttribute("error");
			// 	if (error != null) {
			// 		out.println(error);
			// 	}
			// 	 request.removeAttribute("error");
		%>

	</div>

	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>