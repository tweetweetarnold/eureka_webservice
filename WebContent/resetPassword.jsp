
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>LunchTime</title>

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

		<div style="max-width: 500px;
	margin-left: auto;
	margin-right: auto;">
			<form class="form-signin" method="post" action="ProcessResetPasswordServlet" style="max-width: 500px;">
				<h2 class="form-signin-heading">Reset Password</h2>
				<br>
				<br>
				<br>

				<!-- User input -->
				<table>
					<tr>
						<td>Your Email Address</td>
						<td width="250">
							<input type="email" name="email" class="form-control-table" placeholder="Your Email Address"
								value="${sessionScope.email}" required
							>
						</td>
						<td>
							<button class="btn btn-sm btn-primary" type="submit">
								<font face="verdana">Submit</font>
							</button>
						</td>
					</tr>
				</table>

				<br>
				<div align="left">
					<a href="login.jsp">Back to Login Page</a>
				</div>
			</form>
		</div>
	</div>

	<br>

	<!-- Error message handling -->
	<c:if test="${not empty sessionScope.error}">
		<div class="alert alert-danger">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Error:</span>
			<c:out value="${error}" />
		</div>
		<c:remove var="error" scope="session" />
	</c:if>

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
			m.parentNode.insertBefore(a, m);
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');
		ga('create', 'UA-68676403-1', 'auto');
		ga('send', 'pageview');
	</script>

</body>
</html>