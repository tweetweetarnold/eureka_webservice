<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/adminProtect.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DaBao - Admin Homepage</title>
<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/shop-homepage.css" rel="stylesheet">
</head>
<body>
	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/adminHeader.jsp" />

	<!-- Page Content -->
	<div class="container">
		<h1>Homepage</h1>

		<!-- Footer JSP Include -->
		<jsp:include page="headerfooter/footer.jsp" />

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<!-- Arnold Test JavaScript -->
	<!-- 	<script src="resources/js/pagedirect.js"></script> -->
</body>
</html>