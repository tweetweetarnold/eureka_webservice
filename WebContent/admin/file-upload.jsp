
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<%@include file="/protect/adminProtect.jsp"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link href="/eureka_webservice/resources/img/favicon/lunchtime_favicon.png" rel="shortcut icon">
<title>LunchTime - Admin</title>

<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet"
>
<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet"
>
<link href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/timeline.css" rel="stylesheet">
<link href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/css/sb-admin-2.css" rel="stylesheet">
<link href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/morrisjs/morris.css"
	rel="stylesheet"
>
<link
	href="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"
>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
	<fmt:setTimeZone value="GMT+8" />

	<div id="wrapper">
		<%@include file="/admin/adminHeader.jsp"%>

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

					<p>File Upload allows you to add new data into the database via a CSV file. You can add data into the following
						tables:</p>
					<ol>
						<li>
							<a href="/eureka_webservice/admin/downloads/canteen.csv">canteen</a>
						</li>
						<li>
							<a href="/eureka_webservice/admin/downloads/stall.csv">stall</a>
						</li>
						<li>
							<a href="/eureka_webservice/admin/downloads/food.csv">food</a>
						</li>
						<li>
							<a href="/eureka_webservice/admin/downloads/modifiersection.csv">modifiersection</a>
						</li>
						<li>
							<a href="/eureka_webservice/admin/downloads/modifier.csv">modifier</a>
						</li>
					</ol>

					<p>
						To upload a CSV file, ensure that the
						<b>
							<u>file name is the same as the table</u>
						</b>
						you want to insert into.
						<br>
						Eg. Canteen.csv for Canteen table
					</p>

					<p>Ensure that your data is well formed and meet the format as given for each table. Click on the table name
						above to view the sample file.</p>

					<br>
					<p>Once you are ready, start uploading data below!</p>

					<hr>


					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">


						<h3>Select a file to upload</h3>
						<p>
							<i>Make sure your CSV file meets the criteria and standards stated above.</i>
						</p>
						<br>


						<form action="/eureka_webservice/FileUploadServlet" method="post" enctype="multipart/form-data">
							<input type="file" name="file" style="width: 228px;" required />
							<br>
							<input type="submit" class="btn btn-primary" value="Upload" />

						</form>

						<!-- Success message handling -->
						<c:if test="${not empty sessionScope.success}">
							<br>
							<div class="alert alert-success" role="alert" style="max-width: 50%">
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
								<span class="sr-only">Success:</span>
								<c:out value="${success}" />
							</div>
							<c:remove var="success" scope="session" />
						</c:if>
					</div>

					<!-- Error message handling -->
					<c:if test="${not empty sessionScope.error}">
						<div class="alert alert-danger" role="alert" style="max-width: 50%">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							<span class="sr-only">Error:</span>

							<c:out value="${error}" />
							<br>
							<c:if test="${not empty sessionScope.errorList}">
								<c:forEach items="${sessionScope.errorList}" var="errorList" varStatus="errorLoop">
									<c:out value="${errorList}" />
									<br>
								</c:forEach>

							</c:if>

						</div>
						<c:remove var="error" scope="session" />
					</c:if>




				</div>
			</div>
		</div>
	</div>


	<script
		src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/jquery/dist/jquery.min.js"
	></script>
	<script
		src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/bootstrap/dist/js/bootstrap.min.js"
	></script>
	<script
		src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/metisMenu/dist/metisMenu.min.js"
	></script>
	<script src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/bower_components/raphael/raphael-min.js"></script>
	<script src="/eureka_webservice/resources/css/startbootstrap-sb-admin-2-1.0.7/dist/js/sb-admin-2.js"></script>
</body>
</html>