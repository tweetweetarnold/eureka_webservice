
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

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

		<form class="form-signin" method="post" action="ProcessLoginServlet">
			<h2 class="form-signin-heading">Please sign in</h2>

			<!-- User input -->
			<input type="text" name="username" class="form-control" placeholder="Username" value="${sessionScope.username}" required>
			<input type="password" name="password" class="form-control" placeholder="Password" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			<br>
			<div align="center">
				<a href="registration.jsp">Register new user</a>
			</div>

			<!-- Error message handling -->
			<c:if test="${not empty sessionScope.error}">
				<div class="alert alert-danger" role="alert">
					<b>Error!</b>
					<br>
					<c:out value="${error}" />
				</div>
			</c:if>
			
			<!-- Success message handling -->
			<c:if test="${not empty sessionScope.success}">
				<div class="alert alert-success" role="alert">
					<b>Success!</b>
					<br>
					<c:out value="${success}" />
				</div>
			</c:if>
		</form>

		<!-- clearing attributes from session -->
		<c:remove var="username" scope="session" />
		<c:remove var="error" scope="session" />
		<c:remove var="success" scope="session" />

	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>


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
