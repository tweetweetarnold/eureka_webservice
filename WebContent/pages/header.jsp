<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/eureka_webservice/pages/homepage.jsp" style="font-size: x-large;">
				<i class="fa fa-cutlery"></i>
				LunchTime
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="/eureka_webservice/pages/cart.jsp">
						Cart
						<i class="fa fa-shopping-cart fa-lg"></i>
					</a>
				</li>
				<li>
					<a href="/eureka_webservice/LoadUserOrderHistoryServlet">
						History
						<i class="fa fa-bars fa-lg"></i>
					</a>
				</li>

				<li>
					<a href="/eureka_webservice/LoadUserPaymentServlet">
						Payment
						<i class="fa fa-credit-card fa-lg"></i>
					</a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						User
						<i class="fa fa-user fa-lg"></i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="/eureka_webservice/pages/profile.jsp">
								<i class="fa fa-user fa-fw"></i>
								Profile
							</a>
						</li>
						<li>
							<a href="/eureka_webservice/pages/contact-us.jsp">
								<i class="fa fa-envelope fa-fw"></i>
								Contact Us
							</a>
						</li>
						<li>
							<hr>
						</li>
						<li>
							<a href="#" data-toggle="modal" data-target="#modalLogout">
								<i class="fa fa-sign-out fa-fw"></i>
								Logout
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>


<!-- logout Modal -->
<div class="modal fade" id="modalLogout" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<form action="/eureka_webservice/ProcessLogoutServlet">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title text-center" id="myModalLabel">Logout Confirmation</h4>
				</div>
				<!-- / modal header -->
				<div class="modal-body text-center">Are you sure you want to logout?</div>
				<!-- / modal body -->

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No, I want to stay!</button>
					<button type="submit" class="btn btn-success">Yes, get me out of here!</button>
				</div>
				<!-- / modal footer -->
			</div>
			<!-- / modal content -->
		</form>
	</div>
</div>
<!-- / logout Modal -->