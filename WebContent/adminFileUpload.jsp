
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<%@include file="protect/adminProtect.jsp"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>DABAO</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet"
>

<!-- MetisMenu CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet"
>

<!-- Timeline CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"
>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
<div id="wrapper">
<%@include file="headerfooter/adminHeader2.jsp"%>

<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">File Upload</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->


			<div class="row">
				<div class="col-lg-12">
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">




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
	<br>
	<h5> Upload Food.csv</h5>
	<form action="FileUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" style="width:228px;" required/>
		
		<input type="submit" value="upload"/>
	
	</form>
	
	<br>
	<h5> Upload Modifier.csv</h5>
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
</div>
</div>
</div>
</div>
</div>


<!-- jQuery -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/raphael/raphael-min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>
</body>
</html>