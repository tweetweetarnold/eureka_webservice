
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>DABAO</title>

<link rel="icon" href="../../favicon.ico">

<!-- Library import for JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- JavaScript imports -->
<script src="resources/js/jquery-1.11.3.js"></script>
<script src="resources/js/dabao/dabao.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<!-- CSS imports -->
<link href="resources/css/dabao/dabao.css" rel="stylesheet">
<link href="resources/css/bootstap.min.css" rel="stylesheet">
<link href="resources/css/dabao/starter-template.css" rel="stylesheet">


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>

	<!-- Headerbar JSP Include -->
	<jsp:include page="headerfooter/header.jsp" />

	<br>
	<br>

	<c:set value="${sessionScope.user}" var="user" />

	<div class="container">
		<div class="row">
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad"
			>

				<div class="panel panel-info">
					<div class="panel-heading">
						<!-- 						<h3 class="panel-title">JOAN TAN</h3> -->
					</div>

					<div class="panel-body">

						<table class="table table-striped">
							<tr>
								<td>
									<strong>Name:</strong>
								</td>
								<td>
									<c:out value="${user.name}" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Company:</strong>
								</td>
								<td>
									<c:out value="${user.company.name}" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Email:</strong>
								</td>
								<td>
									<c:out value="${user.email}" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Phone No:</strong>
								</td>
								<td>
									<c:out value="${user.contactNo}" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Current spending:</strong>
								</td>
								<td>
									<c:out value="${user.amountOwed}" />
								</td>
							</tr>
						</table>

					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>