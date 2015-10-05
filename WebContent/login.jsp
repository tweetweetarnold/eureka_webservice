
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<%@page import="org.json.simple.JSONObject"%>
<title>DaBao - Sign In</title>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/signin.css" rel="stylesheet">

<script src="resources/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container">

		<form class="form-signin" method="post" action="/eureka_webservice/LoginServlet">
			<%
				String username = "";
				String password = "";
				JSONObject obj = null;
				obj = (JSONObject) session.getAttribute("error");
				if (obj != null) {
					JSONObject msg = (JSONObject) obj.get("message");

					username = (String) msg.get("username");
					password = (String) msg.get("inputPwd");

				}
			%>
			<h2 class="form-signin-heading">Please sign in</h2>

			<!-- User input -->
			<input type="text" name="username" class="form-control" placeholder="Username" value="<%=username%>" required>
			<input type="password" name="password" class="form-control" placeholder="Password" value="<%=password%>" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			<br>
			<div align="center">
				<a href="registration.jsp">Register new user</a>
			</div>

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
		</form>

	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
