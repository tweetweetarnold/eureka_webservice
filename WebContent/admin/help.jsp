<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">

<%@include file="/protect/adminProtect.jsp"%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title>LunchTime - Admin</title>

<link href="/eureka_webservice/resources/img/favicon/lunchtime_favicon.png" rel="shortcut icon">

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

</head>

<body>

	<div id="wrapper">

		<%@include file="/headerfooter/adminHeader2.jsp"%>


		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Help & Guide</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->


			<div class="row">
				<div class="col-lg-12">

					<div class="panel-group">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">Help & Guide</div>

							<div class="panel-body">

								<section style="padding-bottom: 20px;">
									<h2>Background</h2>
									<p>This guide serves to inform the user about this application (LunchTime).</p>
									<p>LunchTime was developed by a team of SMU SIS students as a project.</p>
								</section>


								<section>
									<h2>FAQ</h2>
									<p>
										<strong>Q: What is LunchTime?</strong>
										<br>
										A: LunchTime provides employees of participating companies with an easy and secure way to order their lunch.
									</p>

									<p>
										<strong>Q: What is an Order Period?</strong>
										<br>
										A: It is the time period where employees are able to place their lunch order. For example, if the order period
										is 8.30am – 10.30am, employees are able to place their lunch order via LunchTime anytime from 8.30am -
										10.30am.
									</p>

									<p>
										<strong>Q: How do I add an order period?</strong>
										<br>
										A: It is the time period where employees are able to place their lunch order. For example, if the order period
										is 8.30am – 10.30am, employees are able to place their lunch order via LunchTime anytime from 8.30am -
										10.30am.
										<br>
										Click on ‘Order Period’ on the left navigation panel at the side. Click on ‘Add New Order Period’ Fill in the
										order period details and click ‘Add New Order Window’ button

									</p>

									<p>
										<strong>Q: What happens if I entered the wrong start or end date/time?</strong>
										<br>
										A: *You are only able to edit the start or end date/time if the order period has not started yet. Navigate to
										view all order periods Click on the pencil icon to edit the order period Enter the new start and end date/time
										and click ‘Update’
									</p>


									<p>
										<strong>Q: How do I delete a user if he/she has left the company?</strong>
										<br>
										A: Click on ‘User’ on the left navigation panel at the side Click on the trash icon to delete the user
									</p>

									<p>
										<strong>Q: How do I change the status of the employee if he/she has cleared the outstanding payment
											in cash?</strong>
										<br>
										A: Click on ‘User’ on the left navigation panel at the side Click on the pencil icon to edit the O/S and
										status Click ‘Update’
									</p>

									<p>
										<strong>Q: Am I able to add and manage new canteens/stalls/food items?</strong>
										<br>
										A: Yes you are able to. Simply navigate to ‘Canteen’ on the left navigation panel at the side. Add, edit and
										delete canteen will be on this page. To add, edit or delete a stall from any of the canteens, click on the
										‘View all Stalls’ link for that canteen. To add, edit or delete a food item from any of the stalls in the
										canteen, click on the ‘View all Food’ link for that stall.
									</p>

								</section>










							</div>
						</div>
					</div>

				</div>
				<!-- /.col-lg-12 -->


			</div>
			<!-- /.row -->


		</div>
		<!-- /# page-wrapper -->

	</div>
	<!-- /#wrapper -->


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
