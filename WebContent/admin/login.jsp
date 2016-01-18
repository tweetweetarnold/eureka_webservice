
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
<title>LunchTime - Admin</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="/eureka_webservice/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/eureka_webservice/resources/css/signin.css" rel="stylesheet">

<script src="/eureka_webservice/resources/js/ie-emulation-modes-warning.js"></script>

</head>

<body>
<fmt:setTimeZone value="GMT+8" />

	<div class="container">

		<form class="form-signin" method="post" action="/eureka_webservice/ProcessAdminLoginServlet">
			<h2 class="form-signin-heading">Please sign in</h2>

			<!-- User input -->
			<input type="text" name="adminUsername" class="form-control" placeholder="Username" value="${sessionScope.username}"
				required
			>
			<input type="password" name="adminPassword" class="form-control" placeholder="Password" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			<br>


			<!-- Error message handling -->
			<c:if test="${not empty sessionScope.error}">
				<div class="alert alert-danger">
					<b>Error!</b>
					<br>
					<c:out value="${error}" />
				</div>
				<c:remove var="error" scope="session" />
			</c:if>

			<!-- Success message handling -->
			<c:if test="${not empty sessionScope.success}">
				<div class="alert alert-success">
					<b>Success!</b>
					<br>
					<c:out value="${success}" />
				</div>
				<c:remove var="success" scope="session" />
			</c:if>
		</form>

		<c:remove var="username" scope="session" />

	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/eureka_webservice/resources/js/ie10-viewport-bug-workaround.js"></script>



</body>
</html>
