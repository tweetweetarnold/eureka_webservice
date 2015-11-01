
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

<!-- Javascript imports -->
<script src="resources/js/jquery-1.11.3.js"></script>
<script src="resources/js/dabao/dabao.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/dabao/transition.js"></script>
<script src="resources/js/dabao/carousel.js"></script>
<script src="resources/js/dabao/collapse.js"></script>


<!-- Bootstrap core CSS -->
<link href="resources/css/dabao/dabao.css" rel="stylesheet">
<link href="resources/css/dabao/carousel.css" rel="stylesheet">
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


	<div class="container">
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne"
						> Month Of October 2015 </a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<img src="resources/img/img-outstandingPayment.png" alt="" style="width: 536px;
	height: 489px;">
						<img src="resources/img/img-outstandingPayment.png" alt="" style="width: 536px;
	height: 489px;">
						<img src="resources/img/img-outstandingPayment.png" alt="" style="width: 536px;
	height: 489px;">
						<img src="resources/img/img-outstandingPayment.png" alt="" style="width: 536px;
	height: 489px;">
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingTwo">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"
							aria-expanded="false" aria-controls="collapseTwo"
						> Month Of November 2015 </a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
					<div class="panel-body"></div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingThree">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"
							aria-expanded="false" aria-controls="collapseThree"
						> Month Of December 2015 </a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body"></div>
				</div>
			</div>
		</div>
	</div>



	<!-- Bootstrap core JavaScript
        ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
<!-- 	<script src="../../dist/js/bootstrap.min.js"></script> -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!-- 	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->


</body>
</html>