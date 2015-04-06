<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="agentGetInfo.js"></script>
</head>
<body>
   <img src="images/tebanner.jpg" width="100%" />
   <h1 align="center">Select a customer to list their information</h1>
   <h2 align="center">Notice that the page doesn't flicker as the data is displayed</h2>
   <form > 
      Select a Customer:
      <select name="agents"  onchange="showAgent(this.value)">
      <%
	      //get the database objects
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ICTOOSD","ICTOOSD");
		  Statement stmt1 = con1.createStatement();
	      //populate the prodtype dropdown
	      ResultSet rs1 = stmt1.executeQuery("select AGENTID, AGTFIRSTNAME, AGTMIDDLEINITIAL, AGTLASTNAME from AGENTS");
	      while (rs1.next())
	      {
	         out.println("<option value=\"" + rs1.getString(1) + "\">" + rs1.getString(2) + " " + rs1.getString(3) +" " + rs1.getString(4) + "</option>");
	      }
      %>
      </select>
   </form>

      <div id="txtHint"><b>Customer info will be listed here.</b></div>

</body>
</html>