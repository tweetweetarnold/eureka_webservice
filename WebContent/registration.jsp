<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>DABAO</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- import JQuery -->
<script src="resources/js/jquery-1.11.3.js"></script>

<!-- Bootstrap core CSS -->
<!-- <link href="resources/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="resources/css/eureka.css" rel="stylesheet">

<!-- Custom styles for this template -->
<!-- <link href="resources/css/signin.css" rel="stylesheet"> -->
<link href="resources/css/cover.css" rel="stylesheet">


</head>
<body>

	<div class="site-wrapper">

		<div class="site-wrapper-inner">

			<div class="cover-container">
				<div class="inner cover">
					<h3>Create an account:</h3>
					<form class="form-signin" action='ProcessRegistrationServlet'>

						<div class="table">
							<br>
							<input type="text" name="username" class="form-control-table" placeholder="Username" value="<c:out value="${userInput['username']}"/>" required>
							<input type="text" name="name" class="form-control-table" placeholder="Name" value="<c:out value="${userInput['name']}"/>" required>
							<input type="password" name="password" class="form-control-table" placeholder="Password" required>
							<p style="color: white">
								<font face="verdana" size='1'>Password must be no shorter than 7 characters</font>
							</p>
							<input type="password" name="confirmPwd" class="form-control-table" placeholder="Confirm Password" required>
							<input type="email" name="email" class="form-control-table" placeholder="Email" value="<c:out value="${userInput['email']}"/>" required>
							<input type="text" name="contactNo" class="form-control-table" placeholder="Contact Number" value="<c:out value="${userInput['contactNo']}"/>"
								required
							>
							<p style="color: white">
								<font face="verdana" size='1'>No spacing between numbers</font>
							</p>
							<input type="text" name="companyCode" class="form-control-table" placeholder="Company Code"
								value="<c:out value="${userInput['companyCode']}"/>" required
							>
							<br>
						</div>
						<br>
						<button class="btn btn-primary btn-block" type="submit">
							<font face="verdana">Register</font>
						</button>
					</form>

					<form action="login.jsp">
						<button class="btn btn-primary btn-block" type="submit">
							<font face="verdana">Back to Sign In</font>
						</button>
					</form>
				</div>
				<!-- /container -->

				<!-- Error message handling -->
				<c:if test="${not empty sessionScope.error}">
					<div class="alert alert-danger" role="alert">
						<b>Error!</b>
						<br>
						<c:out value="${error}" />
					</div>
					<c:remove var="error" scope="session" />
				</c:if>

				<c:remove var="userInput" scope="session" />

				<div class="mastfoot">
					<div class="inner">
						<p>Created by Eureka!</p>
					</div>
				</div>
			</div>
		</div>
	</div>



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