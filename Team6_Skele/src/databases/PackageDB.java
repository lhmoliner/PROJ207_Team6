/*
 * Coded by Megha Patel(000679647)
 * Package connection class which get Package Information form the database
 * Date:03/04/2015
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/*
 * Project Workshop CMPP264 Java - Team 6
 * Instructor: Harvey Peters
 * Created by: Megha Patel(000679647)
 * Date: March/23/2015
 * */


public class PackageDB {
	//static reference to itself
	private static PackageDB instance = new PackageDB();
	
	public static final String URL = "jdbc:mysql://localhost:3306/travelexperts";
	public static final String USER ="root";
	public static final String PASSWORD = "";
	public static final String DRIVER_CLASS ="com.mysql.jdbc.Driver";
	
	//private Constructor
	private PackageDB(){
		try
		{
			Class.forName(DRIVER_CLASS);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private Connection createConnection()
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		}
		catch(SQLException e)
		{
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return conn;
	}//end CreateConnection();
	public static Connection getConnection()
	{
		return instance.createConnection();
	}
	public static void close(Connection conn)
	{
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				/*Ignore*/
			}
		}
	}//end Close()
	public static void close(Statement stmt)
	{
		if(stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch(SQLException e)
			{
				/*Ignore*/
			}
		}
	}//end close for Statement
	public static void close(ResultSet rs)
	{
		if(rs != null)
		{
			try
			{
				rs.close();
			}
			catch(SQLException e)
			{
				/*Ignore*/
			}
		}
	}//end close for Result Set
}//end PackagDB



















