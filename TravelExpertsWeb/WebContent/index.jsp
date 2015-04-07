<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Welcome to Travel Experts</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/css/signin.css" rel="stylesheet">
    <link href="bootstrap/css/cover.css" rel="stylesheet">

    <!-- Just for debugging purposes -->
    <script src="bootstrap/js/ie-emulation-modes-warning.js"></script>

  </head>
  <body>
    <div class="site-wrapper">
      <div class="site-wrapper-inner">
        <div class="cover-container">
          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">Travel Experts</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a data-toggle="collapse" data-target="#customers">Customers</a></li>
                  <li><a data-toggle="collapse" data-target="#agents">Agents</a></li>
                  <li><a data-toggle="collapse" data-target="#suppliers">Suppliers</a></li>
                </ul>
              </nav>
            </div>
          </div>
          
          <div id="customers" class="collapse" style="background-color: #339933; border-radius:10px;">            
			<form class="form-signin" action="customerinfo.jsp">
		        <h2 class="form-signin-heading">Please sign in</h2>
		        <label for="inputEmail" class="sr-only">Email address</label>
		        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
		        <label for="inputPassword" class="sr-only">Password</label>
		        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
		        <div class="checkbox">
		          <label>
		            <input type="checkbox" value="remember-me"> Remember me
		          </label>
       			</div>
       			<button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #666666;">Sign in</button>
   			</form>
   		</div>
     		
      		<div id="agents" class="collapse" style="background-color: #00FFFF; border-radius:10px;">
			      <form class="form-signin" action="agentInfo.jsp">
			        <h2 class="form-signin-heading">Please sign in</h2>
			        <label for="inputEmail" class="sr-only">Email address</label>
			        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
			        <label for="inputPassword" class="sr-only">Password</label>
			        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
			        <div class="checkbox">
			          <label>
			            <input type="checkbox" value="remember-me"> Remember me
			          </label>
        			</div>
        			<button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #666666;">Sign in</button>
      			</form>
      		</div>
      		
      		<div id="suppliers" class="collapse" style="background-color: #CC33FF; border-radius:10px;">
			      <form class="form-signin" action="supplierinfo.jsp">
			        <h2 class="form-signin-heading">Please sign in</h2>
			        <label for="inputEmail" class="sr-only">Email address</label>
			        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
			        <label for="inputPassword" class="sr-only">Password</label>
			        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
			        <div class="checkbox">
			          <label>
			            <input type="checkbox" value="remember-me"> Remember me
			          </label>
        			</div>
        			<button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color: #666666;">Sign in</button>
      			</form>
      		</div>

          <div class="inner cover">
          	
            <h1 class="cover-heading">Welcome to your Travel Experts</h1>
            <p class="lead">Please be advised that in order to proceed you must have an account with Travel Experts. If you are not part of our growing family yet please click the link below to access the registration with our main website</p>
            <p class="lead">
              <a href="registration.jsp" class="btn btn-lg btn-default">Register</a>
            </p>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>All rights reserved by <a href="http://getbootstrap.com">Travel Experts</a>, by <a href="https://twitter.com/mdo">2015</a>.</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>