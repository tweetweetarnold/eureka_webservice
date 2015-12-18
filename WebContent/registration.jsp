
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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

		<div style="max-width: 500px;
	margin-left: auto;
	margin-right: auto;">
			<form class="form-signin" method="post" action="ProcessRegistrationServlet" style="max-width: 500px;">
				<h2 class="form-signin-heading">Create an account:</h2>
				<br>
				<br>
				<br>

				<c:set value="${sessionScope.userInput}" var="userInput" />

				<!-- User input -->
				<table>
					<tr>
						<td>Email</td>
						<td>
							<input type="email" name="email" class="form-control-table" placeholder="Email"
								value="<c:out value="${userInput['email']}"/>" required
							>
						</td>
					</tr>
					<tr>
						<td>Password</td>
						<td>
							<input type="password" name="password" class="form-control-table"
								placeholder="Password (Min length 7 characters)" onfocus="" required
							>
						</td>
					</tr>
					<tr>
						<td>Confirm Password</td>
						<td>
							<input type="password" name="confirmPwd" class="form-control-table" placeholder="Confirm Password" required>
						</td>
					</tr>
					<tr>
						<td>Name</td>
						<td>
							<input type="text" name="name" class="form-control-table" placeholder="Name"
								value="<c:out value="${userInput['name']}"/>" required
							>
						</td>
					</tr>
					<tr>
						<td>Contact No</td>
						<td>
							<input type="text" name="contactNo" class="form-control-table" placeholder="Contact Number (8 Digits)"
								value="<c:out value="${userInput['contactNo']}"/>" required
							>
						</td>
					</tr>
					<tr>
						<td>Company Code</td>
						<td>
							<input type="text" name="companyCode" class="form-control-table" placeholder="Company Code"
								value="<c:out value="${userInput['companyCode']}"/>" required
							>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
					</tr>
					<tr>
						<td>Terms and Conditions</td>
						<td>
							<div style="margin: 0px 32px;">
								<input type="checkbox" name="tc" required />
								<small>
									I agree to the
									<a href="#">terms and conditions</a>
									of using DABAO.
								</small>
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
					</tr>

					<tr>
						<td></td>
						<td>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
								<font face="verdana">Next</font>
							</button>
						</td>
					</tr>
				</table>

				<br>
				<div align="center">
					<a href="login.jsp">Back to Login Page</a>
				</div>
			</form>
		</div>

		<!-- 		<form action="login.jsp" class="form-signin"> -->
		<!-- 			<button class="btn btn-primary btn-block"> -->
		<!-- 				<font face="verdana"></font> -->
		<!-- 			</button> -->
		<!-- 		</form> -->

		<br>

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