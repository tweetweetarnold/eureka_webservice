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

				JSONObject obj = null;
				obj = (JSONObject) session.getAttribute("error");
				if (obj != null) {
					JSONObject msg = (JSONObject) obj.get("message");

					username = (String) msg.get("username");
					name = (String) msg.get("name");
					contactNo = (String) msg.get("contactNo");
					bankAcc = (String) msg.get("bankAcc");
					company = (String) msg.get("company");
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
				if (obj != null) {
			%>
			<div class="alert alert-danger" role="alert">
				<b>Error!</b>
				<br>
				<%=obj.get("error")%>
			</div>
			<%
				session.removeAttribute("error");
				}
			%>
			
			
			<!-- Success message handling -->
			<%
			JSONObject objSuccess = null;
			objSuccess = (JSONObject) session.getAttribute("success");
				if (objSuccess != null) {
			%>
			<div class="alert alert-danger" role="alert">
				<b>Success!</b>
				<br>
				<%=objSuccess.get("success")%>
			</div>
			<%
				session.removeAttribute("sucess");
				}
			%>
		</form>

	</div>

	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>