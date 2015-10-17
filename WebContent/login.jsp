
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
<title>DABAO</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- import JQuery -->
<script src="resources/js/jquery-1.11.3.js"></script>

<!-- Bootstrap core CSS -->
<link href="resources/css/eureka.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/cover.css" rel="stylesheet">

<!-- <script src="resources/js/ie-emulation-modes-warning.js"></script> -->

</head>

<body>
	<div class="site-wrapper">

		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="inner cover">
					<h1>DABAO</h1>
					<form class="form-signin" action="ProcessLoginServlet">

						<input type="text" name="username" class="form-control" placeholder="Username" value="${sessionScope.username}" required>
						<input type="password" name="password" class="form-control" placeholder="Password" required>

						<button class="btn btn-primary btn-block" type="submit">
							<font face="verdana">Sign in</font>
						</button>
					</form>

					<form action="registration.jsp">
						<button class="btn btn-primary btn-block" type="submit">
							<font face="verdana">Register as a New User</font>
						</button>
					</form>

				</div>
				<!-- /container -->

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
				
				<c:remove var="username" scope="session" />


				<div class="mastfoot">
					<div class="inner">
						<p>Created by Eureka!</p>
					</div>
				</div>
			</div>
		</div>
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
