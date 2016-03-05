<!-- library import for JSTL -->

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a target="_self" class="navbar-brand" href="/eureka_webservice/LoadOrderPeriodActiveServlet">LunchTime</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">

		<li class="dropdown">
			<a target="_self" class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-cog fa-fw"></i>
				Settings
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu">
				<li>
					<a target="_self" href="#">
						<i class="fa fa-play fa-fw"></i>
						Run Quartz
					</a>
				</li>
			</ul>
			<!-- /.dropdown-menu -->
		</li>
		<!-- /.dropdown -->


		<li class="dropdown">
			<a target="_self" class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-sign-out fa-fw"></i>
				Logout
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu">
				<li>
					<a target="_self" href="/eureka_webservice/ProcessAdminLogoutServlet">
						<i class="fa fa-sign-out fa-fw"></i>
						Logout
					</a>
				</li>
			</ul>
			<!-- /.dropdown-menu -->
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
					<a target="_self" href="#">
						<i class="fa fa-bar-chart fa-fw"></i>
						Analytics
					</a>
				</li>

				<li>
					<a target="_self" href="#">
						<i class="fa fa-windows fa-fw"></i>
						Order Periods
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a target="_self" href="/eureka_webservice/admin/orderperiod/view.jsp">View: All</a>

						</li>
						<li>
							<a target="_self" href="/eureka_webservice/LoadOrderPeriodActiveServlet">View: Active</a>

						</li>
						<li>
							<a target="_self" href="/eureka_webservice/LoadAdminViewOrderPeriodsArchivedServlet">View: Archieved</a>

						</li>
						<li>
							<a target="_self" href="/eureka_webservice/LoadAdminAddNewPeriodServlet">Set Order Period</a>
						</li>

					</ul>
					<!-- /.nav-second-level -->
				</li>

				<li>
					<a target="_self" href="#">
						<i class="fa fa-money fa-fw"></i>
						Payment
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a target="_self" href="/eureka_webservice/LoadAdminViewUsersWithOutstandingPaymentServlet">Outstanding</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>

				<li>
					<a target="_self" href="/eureka_webservice/admin/company/view.jsp">
						<i class="fa fa-building fa-fw"></i>
						Company
					</a>
				</li>

				<li>
					<a target="_self" href="/eureka_webservice/admin/user/view.jsp">
						<i class="fa fa-user fa-fw"></i>
						User
					</a>
				</li>

				<li>
					<a target="_self" href="/eureka_webservice/admin/canteen/view.jsp">
						<i class="fa fa-cutlery fa-fw"></i>
						Canteen
					</a>
				</li>

				<!-- 				<li> -->
				<!-- 					<a target="_self" href="/eureka_webservice/admin/file-upload.jsp"> -->
				<!-- 						<i class="fa fa-cloud-upload fa-fw"></i> -->
				<!-- 						File Upload -->
				<!-- 						<span class="fa arrow"></span> -->
				<!-- 					</a> -->
				<!-- 				</li> -->



				<li>
					<a target="_self" href="/eureka_webservice/admin/help.jsp">
						<i class="fa fa-info fa-fw"></i>
						Help & Guides
					</a>
				</li>


			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
<!-- /.navigation -->