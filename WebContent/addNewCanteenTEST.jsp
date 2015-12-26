
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
<body>
</head>
<div class="container">

	<div style="max-width: 500px; margin-left: auto; margin-right: auto;">




		<form class="form-newCanteen" action="AddNewCanteenServlet"
			method="post" style="max-width: 500px;">
			<h2 class="form-newCanteen-heading">Add a new canteen:</h2>
			<br> <br> <br>

			<c:set value="${sessionScope.userInput}" var="userInput" />

			<!-- User input -->
			<table>
				<tr>
					<td>Canteen Name</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Canteen Address</td>
					<td><input type="text" name="address"></td>
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
		</form>

	</div>
	</body>
</html>