
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetHint
 */
public class GetHint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String[] names = {"Anna", "Brittany", "Cindy", "Cinderella", "Diana", "Eva", "Fiona", "Holly",
			"Ingrid", "Joanne", "Kim", "Linda", "Nina", "Ophelia", "Petunia", "Amanda", "Raquel", "Doris",
			"Eve", "Evita", "Violet", "Liza", "Elizabeth", "Ellen", "Wendy", "Vicky"};

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHint() {
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

	private void doStuff(HttpServletRequest request, HttpServletResponse response)
	{
		PrintWriter out;
		try {
			out = response.getWriter();
			//get the parameter from URL
			String name = "";
			if (request.getParameter("name") != null)
			{
				name = request.getParameter("name");
			}
			String hint = "";
			String responseString = "";
	
			//lookup all hints from array if length of q>0
			if (name.length() > 0)
			{
				for (int i = 0; i < names.length; i++)
			    {
				   if ((name.length() < names[i].length()) && (name.toLowerCase().equals(names[i].substring(0, name.length()).toLowerCase())))
				   {
					    if (hint.equals(""))
					    {
					       hint = names[i];
					    }
					    else
					    {
					       hint = hint + " , " + names[i];
					    }
					}
			     }
			 }
	
			 // Set output to "no suggestion" if no hint where found
			 // or to the correct values
			 if (hint.equals(""))
			 {
				 responseString = "no suggestion";
			 }
			 else
			 {
				 responseString = hint;
			 }
	
			 //output the response
			 out.print(responseString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

