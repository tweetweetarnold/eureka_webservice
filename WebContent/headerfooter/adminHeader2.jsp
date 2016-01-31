<!-- library import for JSTL -->

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<!-- <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button> -->
		<a class="navbar-brand" href="/eureka_webservice/LoadOrderWindowOpenedServlet">LunchTime</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-gear fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li>
					<a href="/eureka_webservice/ProcessAdminLogoutServlet">
						<i class="fa fa-sign-out fa-fw"></i>
						Logout
					</a>
				</li>
			</ul>
			<!-- /.dropdown-user -->
		</li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->


	<!-- Sidebar -->
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">

				<!-- 				<li class="sidebar-search"> -->
				<!-- 					<div class="input-group custom-search-form"> -->
				<!-- 						<input type="text" class="form-control" placeholder="Search..."> -->
				<!-- 						<span class="input-group-btn"> -->
				<!-- 							<button class="btn btn-default" type="button"> -->
				<!-- 								<i class="fa fa-search"></i> -->
				<!-- 							</button> -->
				<!-- 						</span> -->
				<!-- 					</div> -->
				<!-- 					/input-group -->
				<!-- 				</li> -->

				<li>
					<a href="/eureka_webservice/LoadOrderWindowOpenedServlet">
						<i class="fa fa-dashboard fa-fw"></i>
						Homepage
					</a>
				</li>

				<li>
					<a href="#">
						<i class="fa fa-windows fa-fw"></i>
						Order Windows
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/eureka_webservice/LoadAdminViewOrderWindowsServlet">View: All</a>

						</li>
						<li>
							<a href="/eureka_webservice/LoadOrderWindowOpenedServlet">View: Opened</a>

						</li>
						<li>
							<a href="/eureka_webservice/LoadAdminViewOrderWindowsClosedServlet">View: Closed</a>

						</li>
						<li>
							<a href="/eureka_webservice/LoadAdminAddNewWindowServlet">Add New Window</a>
						</li>

					</ul>
					<!-- /.nav-second-level -->
				</li>

				<li>
					<a href="#">
						<i class="fa fa-money fa-fw"></i>
						Payment
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/eureka_webservice/LoadAdminViewUsersWithOutstandingPaymentServlet">Outstanding</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>

				<li>
					<a href="#">
						<i class="fa fa-building fa-fw"></i>
						Company
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/eureka_webservice/LoadAdminViewCompaniesServlet">View: All</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>

				<li>
					<a href="#">
						<i class="fa fa-user fa-fw"></i>
						User
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/eureka_webservice/LoadAdminViewUsersServlet">View: All</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>

				<li>
					<a href="#">
						<i class="fa fa-cutlery fa-fw"></i>
						Canteen
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/eureka_webservice/LoadViewCanteenServlet">View: All</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>

				<li>
					<a href="#">
						<i class="fa fa-cloud-upload fa-fw"></i>
						File Upload
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/eureka_webservice/admin/file-upload.jsp">Upload files</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>


			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
<!-- /.navigation -->