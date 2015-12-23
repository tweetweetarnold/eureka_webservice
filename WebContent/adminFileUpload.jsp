
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>DABAO</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/signin.css" rel="stylesheet">

<script src="resources/js/ie-emulation-modes-warning.js"></script>
</head>
<body>
	<h3>Select a file to upload</h3>
	<h5> Upload Canteen.csv</h5>
	<form action="FileUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" style="width:228px;" required/><input type="submit" value="upload"/>
	
	</form>
	<br>
	<h5> Upload Stall.csv</h5>
	<form action="FileUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" style="width:228px;" required/>
		
		<input type="submit" value="upload"/>
	
	</form>
	
	
	<!-- Success message handling -->
			<c:if test="${not empty sessionScope.message}">
				<div class="alert alert-success">
					<b>Success!</b>
					<br>
					<c:out value="${message}" />
				</div>
				<c:remove var="message" scope="session" />
			</c:if>
			<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>