<nav class="navbar navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
<!--                     <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"> -->
<!--                         <span class="sr-only">Toggle navigation</span> -->
<!--                         <span class="icon-bar"></span> -->
<!--                         <span class="icon-bar"></span> -->
<!--                         <span class="icon-bar"></span> -->
<!--                     </button> -->
                    <a class="navbar-brand" href="homepage.jsp">DABAO</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="homepage.jsp">Food Menu <span class="sr-only">(current)</span></a></li>
                        <li><a href="cart.jsp">Cart</a></li>
                        <li><a href="payment.jsp">Payment</a></li>
                        <li><a href="profile.jsp">History</a></li>
                        <li><a href="#">Settings</a></li>
                        <li><a href="#">Userguide</a></li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">

                        <li><a href="ProcessLogoutServlet">Logout</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>