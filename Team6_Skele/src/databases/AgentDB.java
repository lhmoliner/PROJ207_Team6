package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class AgentDB {
	
	public static dbConnection()
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521:xe","ictoosd","ictoosd");
		String sql = "SELECT * FROM agents";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		while (rs.next())
		{
			for(int i=1;i<=rsmd.getColumnCount();i++)
			{
				System.out.print(rs.getString(i) + "\t");
			}
			System.out.println();
		}
		conn.close();
	}
	
	public static insertAgent()
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521:xe","ictoosd","ictoosd");
		String sql = "INSERT INTO agents "
				+ "(AGTFIRSTNAME, AGTMIDDLEINITIAL, AGTLASTNAME, AGTBUSPHONE, AGTEMAIL, AGTPOSITION, AGENCYID) "
				+ "VALUES "
				+ "(?,?,?,?,?,?,?)";
		Statement stmt = conn.createStatement();
		conn.close();
	}

}
