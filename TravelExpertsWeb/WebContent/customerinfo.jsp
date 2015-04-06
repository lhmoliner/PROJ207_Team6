<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

	<jsp:include page="header.jsp" />
	<script src="customergetinfo.js"></script>
	<body>
	   <form > 
	      Select a Customer:
	      <select name="customers" onchange="showCustomer(this.value)">
	      <%
		      //get the database objects
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ICTOOSD","ICTOOSD");
			  Statement stmt1 = con1.createStatement();
		      //populate the prodtype dropdown
		      ResultSet rs1 = stmt1.executeQuery("select CUSTOMERID, CUSTFIRSTNAME, CUSTLASTNAME from CUSTOMERS");
		      while (rs1.next())
		      {
		         out.println("<option value=\"" + rs1.getString(1) + "\">" + rs1.getString(2) + " " + rs1.getString(3) + "</option>");
		      }
	      %>
	      </select>
	   </form>
	   <div id="txtHint"><b>Customer info will be listed here.</b></div>
	</body>
</html>