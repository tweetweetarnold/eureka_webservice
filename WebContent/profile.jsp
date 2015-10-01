<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DaBao - Profile</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/shop-homepage.css" rel="stylesheet">

</head>
<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/header.jsp" />


	<div class="container">
		<div align="center">
			<h1>My Profile</h1>

			<table style="width: 50%;
	font-size: 15px;" border="1">
				<tr>
					<td>Name:</td>
					<td>Arnold</td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td>Male</td>
				</tr>
				<tr>
					<td>Credit:</td>
					<td>$200.00</td>
				</tr>
			</table>

			<br>
			<br>
			check it out

		</div>
		<!-- center align -->
		
		<!-- Footer JSP Include -->
			<jsp:include page="headerfooter/footer.jsp" />
			
	</div>
	<!-- container -->


</body>


</html>