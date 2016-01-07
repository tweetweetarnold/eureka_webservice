<!DOCTYPE html>

<html lang="en">
<%@include file="protect/protect.jsp"%>
<head>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<meta name="description" content="">
<meta name="author" content="">

<title>DABAO</title>

<!-- library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="resources/js/jquery-1.11.3.js"></script>
<script src="resources/js/dabao/dabao.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<link href="resources/css/dabao/dabao.css" rel="stylesheet">
<link href="resources/css/dabao/starter-template.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/header.jsp" />


	<div class="container">
		<div class="row center">
			<div class="col-xs-8 center">
				<div class="panel panel-info">
					<br>
					<br>
					<br>
					<br>

					<form action="SetUserPasswordServlet" method="post">
						<label>Old Password: </label>
						<input name="oldPassword" type="password" required>
						<br>
						<label>New Password: </label>
						<input name="newPassword" type="password" required>
						<br>
						<label>Confirm new Password: </label>
						<input name="confirmNewPassword" type="password" required>
						<br>
						<button type="submit">Update</button>
					</form>


					<!-- Success message handling -->
					<c:if test="${not empty sessionScope.success}">
						<div class="alert alert-success" role="alert">
							<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
							<span class="sr-only">Success:</span>
							<c:out value="${success}" />
						</div>
						<c:remove var="success" scope="session" />
					</c:if>

					<!-- Error message handling -->
					<c:if test="${not empty sessionScope.error}">
						<div class="alert alert-danger" role="alert">
							<b>Error!</b>
							<br>
							<c:out value="${error}" />
						</div>
						<c:remove var="error" scope="session" />
					</c:if>


				</div>
			</div>
		</div>
	</div>



	<!-- no image error -->
	<script src="resources/js/myerrors.js"></script>


</body>
</html>