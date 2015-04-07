<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

	<jsp:include page="header.jsp" />
	<script src="customergetinfo.js"></script>
	
	<body>
	    <div class="container">
	
	      <!-- Jumbotron -->
	      <div class="jumbotron">
	        <h2>Know Us</h2>
	        <p style="font-size: 120%;">There are as many travel companies to explore the world with as there are places to explore. 
	        	G Adventures travellers are looking for something more than a plane ticket, a hotel room, 
	        	and a seat on a bus. We make travel meaningful by thinking small, thinking local, 
	        	and thinking about what benefits the traveller, the trip, and the planet best.</p>
	        <a class="btn btn-large btn-success" href="http://getbootstrap.com/2.3.2/examples/justified-nav.html#">Get started today</a>
	      </div>
	
	      <hr>
	      <!-- Example row of columns -->
	      <div class="row-fluid">
	        <div class="span4">
	          <h3>Joanne Smith</h3>
	          <p style="font-size: 120%;">Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	          <p><a class="btn" href="http://getbootstrap.com/2.3.2/examples/justified-nav.html#">View details »</a></p>
	        </div>
	        <div class="span4">
	          <h3>Harv Thomas</h3>
	          <p style="font-size: 120%;">Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	          <p><a class="btn" href="http://getbootstrap.com/2.3.2/examples/justified-nav.html#">View details »</a></p>
	       </div>
	        <div class="span4">
	          <h3>Dennis Alvarez</h3>
	          <p style="font-size: 120%;">Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa.</p>
	          <p><a class="btn" href="http://getbootstrap.com/2.3.2/examples/justified-nav.html#">View details »</a></p>
	        </div>
	      </div>
	      <hr>
    	</div> <!-- /container -->
    	
		<jsp:include page="footer.jsp" />