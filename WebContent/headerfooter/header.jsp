<script type="text/javascript">
	window.onload = function() {
		var fullPath = window.location.toString();
		var endPath = fullPath.substring(fullPath.lastIndexOf("/") + 1,
				fullPath.length);

		var pages = [ [ "cart.jsp", "Cart", "cart.jsp" ],
				[ "payment.jsp", "Payment", "payment.jsp" ],
				[ "profile.jsp", "Profile", "RenderProfileServlet" ],
				[ "orderHistory.jsp", "History", "RenderOrderHistoryServlet" ] ];

		var html = '<ul class="nav navbar-nav">';

		for (var i = 0; i < pages.length; i++) {
			var record = pages[i];
			var path = record[0];
			var name = record[1];
			var link = record[2];

			if (endPath == path) {
				html += '<li class="active"><a href="' + link + '">' + name
						+ '</a></li>';
			} else {
				html += '<li><a href="' + link + '">' + name + '</a></li>';
			}
		}

		document.getElementById("#myNav").innerHTML = html;
	};
</script>

<nav class="navbar navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<!--                     <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"> -->
			<!--                         <span class="sr-only">Toggle navigation</span> -->
			<!--                         <span class="icon-bar"></span> -->
			<!--                         <span class="icon-bar"></span> -->
			<!--                         <span class="icon-bar"></span> -->
			<!--                     </button> -->
			<a class="navbar-brand" href="homepage.jsp"> DABAO </a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<div id="#myNav"></div>

			<!--                     <form class="navbar-form navbar-left" role="search"> -->
			<!--                         <div class="form-group"> -->
			<!--                             <input type="text" class="form-control" placeholder="Search"> -->
			<!--                         </div> -->
			<!--                         <button type="submit" class="btn btn-default">Submit</button> -->
			<!--                     </form> -->

			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="ProcessLogoutServlet">Logout</a>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
