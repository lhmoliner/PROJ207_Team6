


import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetAgent
 */
public class GetAgent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String agentid;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAgent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doStuff(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doStuff(request, response);
	}

	private void doStuff(HttpServletRequest request,
			HttpServletResponse response)
	{
		// TODO Auto-generated method stub

	    //if the employee ID was selected, populate the Employee list
		agentid = request.getParameter("agentid");
	    if (agentid != null)
	    {
			PrintWriter out;
			try {
				 out = response.getWriter();
			     //get the database objects
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ICTOOSD","ICTOOSD");
				Statement stmt1 = con1.createStatement();
				ResultSet rs = stmt1.executeQuery("SELECT * FROM agents where agentid = '" + agentid + "'");
			    
				//print the start of the display table and open the first row
				out.println("<table>");
				
			    ResultSetMetaData rsmd = rs.getMetaData();
			    while (rs.next())
			    {
			       for (int i=1; i<=rsmd.getColumnCount(); i++)
			       {
			          out.println("<tr><td>" + rsmd.getColumnName(i) + "</td><td>" + rs.getString(i) + "</td></tr>");
			       }
			    }
			    out.println("</table>");
			    
			    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
}