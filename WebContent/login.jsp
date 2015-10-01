
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

<body style="background-color: #e73f4d">
	<div class="container">

		<form class="form-signin" method="post" action="/eureka_webservice/LoginServlet">
			<h2 class="form-signin-heading">Please sign in</h2>

			<!-- Error message handling -->
			<%
				String errorMsg = (String) session.getAttribute("error");
				if (errorMsg != null && !errorMsg.isEmpty()) {
			%>
			<div class="alert alert-danger" role="alert">
				<b>Error!</b>
				<br>
				<%=errorMsg%>
			</div>
			<%
				}
			%>

			<!-- User input -->
			<input type="text" name="username" class="form-control" placeholder="Username" required>
			<input type="password" name="password" class="form-control" placeholder="Password" required>
			<!-- 			<div class="checkbox"> -->
			<!-- 				<label> -->
			<!-- 					<input type="checkbox" value="remember-me"> -->
			<!-- 					Remember me -->
			<!-- 				</label> -->
			<!-- 			</div> -->
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

			<br>
			<div align="center">
				<a href="registration.jsp">Register new user</a>
			</div>

		</form>

		<!-- 		**************** For testing purposes ****************   -->
		<form class="form-signin" method="post" action="homepage.jsp">
			<button class="btn btn-lg btn-primary btn-block" type="submit">Lazy Button</button>
		</form>
		<!-- 		**************** For testing purposes ****************   -->

	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>