<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<meta name="description" content="">
<meta name="author" content="">
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
	<div class="container">

		<form class="form-signin" method="post" action="ProcessLoginServlet">
			<h2 class="form-signin-heading">Welcome to...</h2>
			<h1 class="form-signin-heading">DABAO</h1>

			<!-- User input -->
			<label for="username" class="sr-only">Username</label>
			<input type="text" name="username" class="form-control" placeholder="Username" value="${sessionScope.username}"
				required
			>
			<label for="password" class="sr-only">Password</label>
			<input type="password" name="password" class="form-control" placeholder="Password" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			
			<br>
			<div align="center">
				<a href="registration.jsp">Register as a New User</a>
			</div>
		</form>
		

		<!-- Error message handling -->
		<c:if test="${not empty sessionScope.error}">
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				<span class="sr-only">Error:</span>
				<c:out value="${error}" />
			</div>
			<c:remove var="error" scope="session" />
		</c:if>

		<!-- Success message handling -->
		<c:if test="${not empty sessionScope.success}">
			<div class="alert alert-success" role="alert">
				<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
				<span class="sr-only">Success:</span>
				<c:out value="${success}" />
			</div>
			<c:remove var="success" scope="session" />
		</c:if>

		<c:remove var="username" scope="session" />

	</div>
	<!-- /container -->


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