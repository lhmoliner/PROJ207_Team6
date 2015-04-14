/*Project Workshop CMPP264 Java - Team 6
 * Instructor: Harvey Peters
 * Created by: Megha Patel(000679647)
 * Date: March/23/2015
 * */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;


public class PackageMaintenance extends JFrame {

	private JPanel contentPane; //declaring private variables to use only in this class
	private JTextField txtPkgId;
	private JTextField txtPkgName;
	private JTextField txtPkgStartDate;
	private JTextField txtPkgEndDate;
	private JTextField txtPkgDescription;
	private JTextField txtPkgPrice;
	private JTextField txtPkgAgnCommission;
	private JComboBox<String> cboPackage;
	private JButton btnLoad;
	private JButton btnSave;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnExit;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pst;
	private ResultSetMetaData rsmd;
	private JLabel lblSelectPackage;
	private String pkgName; //first
	private String packageName; //full
	private JLabel lblPackageAgencyCommission;
	private JScrollPane scrollPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(String[] args) { //the main method for the class
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PackageMaintenance frame = new PackageMaintenance();
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public PackageMaintenance() throws ClassNotFoundException { //creating the form
		setTitle("Packages Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 729);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPackageId = new JLabel("Package ID"); //displaying field information using label 
		lblPackageId.setForeground(new Color(165, 42, 42));
		lblPackageId.setBounds(29, 71, 118, 30);
		contentPane.add(lblPackageId);
		
		JLabel lblPackageName = new JLabel("Package Name");
		lblPackageName.setForeground(new Color(165, 42, 42));
		lblPackageName.setBounds(29, 103, 118, 30);
		contentPane.add(lblPackageName);
		
		JLabel lblPackageStartDate = new JLabel("Package Start Date");
		lblPackageStartDate.setForeground(new Color(165, 42, 42));
		lblPackageStartDate.setBounds(29, 142, 118, 30);
		contentPane.add(lblPackageStartDate);
		
		JLabel lblPackageEndDate = new JLabel("Package End Date");
		lblPackageEndDate.setForeground(new Color(165, 42, 42));
		lblPackageEndDate.setBounds(29, 178, 118, 30);
		contentPane.add(lblPackageEndDate);
		
		JLabel lblPackageDescription = new JLabel("Package Description");
		lblPackageDescription.setForeground(new Color(165, 42, 42));
		lblPackageDescription.setBounds(29, 215, 118, 30);
		contentPane.add(lblPackageDescription);
		
		JLabel lblPackageBasePrice = new JLabel("Package Price");
		lblPackageBasePrice.setForeground(new Color(165, 42, 42));
		lblPackageBasePrice.setBounds(29, 251, 118, 30);
		contentPane.add(lblPackageBasePrice);
		
		cboPackage = new JComboBox(); //creating combo box
		cboPackage.setForeground(new Color(165, 42, 42));
		cboPackage.addActionListener(new ActionListener() { //creating action listner for combo box
		public void actionPerformed(ActionEvent e) { //Through this action event, connecting to DB and retrieving package name
			Package pkg = null;		
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); //MySql driver
			
			Properties info = new Properties();
			info.put("user", "root"); //user name to connect to DB
			info.put("password", ""); //password to connect to DB
			
			Connection conn = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //Connection string MySQL
			
			
			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement("select * from packages where PkgName=?");//querying the DB
			String selectedpkgName = (String)cboPackage.getSelectedItem(); //Passing packages names to combo box string
			
			pstmt.setString(1, selectedpkgName);
			
			
				  
			
			ResultSet rs = pstmt.executeQuery(); //Execute query is for the select statement to get the data from DB
			while (rs.next())
				{
					String pkgId = rs.getString("PACKAGEID"); //storing values from DB to strings 
					String packageName = rs.getString("PKGNAME");
					String packageStartDate = rs.getString("PKGSTARTDATE");
					String packageEndDate = rs.getString("PKGENDDATE");
					String packageDesc = rs.getString("PKGDESC");
					//String packageBasePrice = rs.getString("PKGBASEPRICE");
					double bsprice = rs.getDouble("PKGBASEPRICE");
					String packageBasePrice = NumberFormat.getCurrencyInstance().format(bsprice);
					
					//double agcomm = rs.getDouble("PKGAGENCYCOMMISSION");
					//String packageAgencyCommission = NumberFormat.getCurrencyInstance().format(agcomm) ;
					
					String packageAgencyCommission = rs.getString("PKGAGENCYCOMMISSION");
	
									
					txtPkgId.setText(pkgId); //displaying DB values into text fields stored already in strings
					txtPkgName.setText(packageName);
					txtPkgStartDate.setText(packageStartDate);
					txtPkgEndDate.setText(packageEndDate);
					txtPkgDescription.setText(packageDesc);
					txtPkgPrice.setText(packageBasePrice);
					txtPkgAgnCommission.setText(packageAgencyCommission);
					
					
					/*pkg = new Package();
					pkg.setPkgId(rs.getInt("PackageId"));
					pkg.setPackageName(rs.getString("PkgName"));
					pkg.setStartDate(startDate);*/
					Statement st;
					Vector data = new Vector();

					Vector columnNames = new Vector();
					columnNames.addElement("Products Name");
					columnNames.addElement("Supplier Name");
					table = new JTable(data,columnNames);
					
					scrollPane.setViewportView(table);
					

					st = conn.createStatement();
					
				    ResultSet res = st.executeQuery("select p.prodname, s.supname from Suppliers s join Products_Suppliers ps on s.SupplierId=ps.SupplierId join Products p on p.ProductId=ps.ProductId");
				    ResultSetMetaData metaData = res.getMetaData();
				    int columns = metaData.getColumnCount();
				    while (res.next()) {
				       Vector row = new Vector(columns);
				       for (int i = 1; i <= columns; i++) {
				        row.addElement(res.getObject(i));
				       }
				       data.addElement(row);
				    }
				}
			
		}catch (ClassNotFoundException | SQLException e1) //catching the exceptions for above catch
		{
			System.out.println(e1);
						e1.printStackTrace();
			
		}
		
	}
});
cboPackage.setEnabled(true); //disabling the combo box
cboPackage.setBounds(198, 34, 171, 22);
contentPane.add(cboPackage);




				
		txtPkgName = new JTextField();
		txtPkgName.setForeground(new Color(165, 42, 42));
		txtPkgName.setEnabled(false);
		txtPkgName.setBounds(198, 113, 200, 22);
		contentPane.add(txtPkgName);
		txtPkgName.setColumns(10);
		
		txtPkgStartDate = new JTextField();
		txtPkgStartDate.setForeground(new Color(165, 42, 42));
		txtPkgStartDate.setEnabled(false);
		txtPkgStartDate.setColumns(10);
		txtPkgStartDate.setBounds(198, 152, 200, 22);
		contentPane.add(txtPkgStartDate);
		
		txtPkgEndDate = new JTextField();
		txtPkgEndDate.setForeground(new Color(165, 42, 42));
		txtPkgEndDate.setEnabled(false);
		txtPkgEndDate.setColumns(10);
		txtPkgEndDate.setBounds(198, 188, 200, 22);
		contentPane.add(txtPkgEndDate);
		
		txtPkgDescription = new JTextField();
		txtPkgDescription.setForeground(new Color(165, 42, 42));
		txtPkgDescription.setEnabled(false);
		txtPkgDescription.setColumns(10);
		txtPkgDescription.setBounds(198, 225, 200, 22);
		contentPane.add(txtPkgDescription);
		
		txtPkgPrice = new JTextField();
		txtPkgPrice.setForeground(new Color(165, 42, 42));
		txtPkgPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPkgPrice.setEnabled(false);
		txtPkgPrice.setColumns(10);
		txtPkgPrice.setBounds(198, 261, 200, 22);
		contentPane.add(txtPkgPrice);
		
		btnSave = new JButton("Save");
		btnSave.setForeground(new Color(165, 42, 42));
		btnSave.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) throws NumberFormatException { //method to save record in the DB
				try
				{
					Class.forName("com.mysql.jdbc.Driver"); //MySql driver
					
					Properties info = new Properties();
					info.put("user", "root");
					info.put("password", "");
					
					Connection conn = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //Connection string MySQL
					String uppackageId = txtPkgId.getText();
					String uppkgName = txtPkgName.getText();
					String uppkgStartDate = txtPkgStartDate.getText();
					String uppkgEndDate = txtPkgEndDate.getText();
					String uppkgDescription = txtPkgDescription.getText();
					//int upprice = Integer.parseInt(txtPackageBasePrice.getText());
					//String uppkgBasePrice = NumberFormat.getCurrencyInstance().format(upprice);
					String uppkgBasePrice = txtPkgPrice.getText();
					/*int upcomm = Integer.parseInt(txtPackageAgencyCommission.getText());
					String uppkgAgencyCommission =NumberFormat.getCurrenmcyInstance().format(upcomm) ;*/
					String uppkgAgencyCommission = txtPkgAgnCommission.getText();
					
					
					
					
					String updateQuery =  "Update packages set PACKAGEID='"+uppackageId+"',PKGNAME='"+uppkgName+"',PKGSTARTDATE='"+uppkgStartDate+"',PKGENDDATE='"+uppkgEndDate+"',PKGDESC='"+uppkgDescription+"',PKGBASEPRICE='"+uppkgBasePrice+"',PKGAGENCYCOMMISSION='"+uppkgAgencyCommission+"' where PACKAGEID='"+uppackageId+"'";
					//PACKAGEID='"+uppackageId
					PreparedStatement pstmt = conn.prepareStatement(updateQuery);
					pstmt.execute();
					JOptionPane.showMessageDialog(null,"Data Updated Successfully!"); //displaying message window for action performed
					pstmt.close();
				}catch (ClassNotFoundException | NumberFormatException | SQLException ex)
				{
					
					System.out.println(ex);
					ex.printStackTrace();
				}
				txtPkgName.setEnabled(false);
				txtPkgStartDate.setEnabled(false);
				txtPkgEndDate.setEnabled(false);
				txtPkgDescription.setEnabled(false);
				txtPkgPrice.setEnabled(false);
				txtPkgAgnCommission.setEnabled(false);
				
				btnSave.setEnabled(false);
				
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(442, 224, 118, 25);
		contentPane.add(btnSave);
		
		
		
		btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(165, 42, 42));
		btnExit.setBounds(442, 288, 123, 25);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ExitListener());
		
		btnLoad = new JButton("Click Here for Packages");
		btnLoad.setForeground(new Color(165, 42, 42));
		btnLoad.addActionListener(new ActionListener() {
			ResultSet rs = null;
			
			public void actionPerformed(ActionEvent e) { //method to load data from database with reference to package name
				
				try 
				{
					Class.forName("com.mysql.jdbc.Driver"); //MySql driver
					Properties info = new Properties();
					info.put("user", "root");
					info.put("password", "");
					
					Connection conn = 
							DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //Connection string MySQL
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from packages");
					while (rs.next())
					{
						pkgName = rs.getString("PKGNAME");
						
						cboPackage.addItem(pkgName);			
					}
					pkgName = (String)cboPackage.getSelectedItem();
				} catch (ClassNotFoundException | SQLException e1) 
				{
					
					System.out.println(e1);
				}
				
				
				btnLoad.setEnabled(true); //disabling load button after retrieving data from DB
				btnEdit.setEnabled(true); //enabling edit button to make modifications
				btnAdd.setEnabled(true); //enabling Add button to append new record
				btnDelete.setEnabled(true); //enabling Delete button to delete records
				
				cboPackage.setEnabled(true);
			
			}
		});
		
		btnLoad.setBounds(410, 33, 175, 25);
		contentPane.add(btnLoad);
		
		
		lblPackageAgencyCommission = new JLabel("Agency Commission");
		lblPackageAgencyCommission.setForeground(new Color(165, 42, 42));
		lblPackageAgencyCommission.setBounds(29, 293, 157, 15);
		contentPane.add(lblPackageAgencyCommission);
		
		txtPkgAgnCommission = new JTextField();
		txtPkgAgnCommission.setForeground(new Color(165, 42, 42));
		txtPkgAgnCommission.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPkgAgnCommission.setEnabled(false);
		txtPkgAgnCommission.setColumns(10);
		txtPkgAgnCommission.setBounds(198, 295, 200, 22);
		contentPane.add(txtPkgAgnCommission);
		
		
		
		txtPkgId = new JTextField();
		txtPkgId.setForeground(new Color(165, 42, 42));
		txtPkgId.setEnabled(false);
		txtPkgId.setEditable(false);
		txtPkgId.setColumns(10);
		txtPkgId.setBounds(198, 77, 101, 22);
		contentPane.add(txtPkgId);
		
		lblSelectPackage = new JLabel("Select Package:");
		lblSelectPackage.setForeground(new Color(165, 42, 42));
		lblSelectPackage.setBounds(29, 29, 118, 30);
		contentPane.add(lblSelectPackage);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 393, 536, 278);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 326, 526, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		//////////////////////////////////////////
		
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(165, 42, 42));
		btnAdd.setBounds(12, 13, 97, 25);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtPkgName.setEnabled(true); //enabling the text fields to do add
				txtPkgStartDate.setEnabled(true);
				txtPkgEndDate.setEnabled(true);
				txtPkgDescription.setEnabled(true);
				txtPkgPrice.setEnabled(true);
				txtPkgAgnCommission.setEnabled(true);
				btnSave.setEnabled(true); //enabling SAVE button after edit
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver"); //MySql driver
					
					Properties info = new Properties();
					info.put("user", "root");
					info.put("password", "");
					
					Connection conn = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //Connection string MySQL
					
					String inpkgName = txtPkgName.getText();
					String inpkgStartDate = txtPkgStartDate.getText();
					String inpkgEndDate = txtPkgEndDate.getText();
					String inpkgDescription = txtPkgDescription.getText();
					String inpkgBasePrice = txtPkgPrice.getText();
					String inpkgAgencyCommission =txtPkgAgnCommission.getText();
					
					String insertQuery =  ("insert into packages (PKGNAME, PKGSTARTDATE, PKGENDDATE, PKGDESC, PKGBASEPRICE, PKGAGENCYCOMMISSION) values ('"+inpkgName+"','"+inpkgStartDate+"','"+inpkgEndDate+"','"+inpkgDescription+"','"+inpkgBasePrice+"','"+inpkgAgencyCommission+"')");
					PreparedStatement pstmt = conn.prepareStatement(insertQuery);
					
					
					
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data Inserted Successfully!"); //displaying message window for action performed
					pstmt.close();
				}catch (Exception ex)
				{
					System.out.println(ex);
				}
				
				
				
			}
		});
		btnAdd.setEnabled(true);
		
		btnEdit = new JButton("Edit");
		btnEdit.setForeground(new Color(165, 42, 42));
		btnEdit.setBounds(143, 13, 97, 25);
		panel.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPkgName.setEnabled(true); //enabling the text fields to do edit
				txtPkgStartDate.setEnabled(true);
				txtPkgEndDate.setEnabled(true);
				txtPkgDescription.setEnabled(true);
				txtPkgPrice.setEnabled(true);
				txtPkgAgnCommission.setEnabled(true);
				btnSave.setEnabled(true); //enabling SAVE button after edit
			}
					});
		

btnEdit.setEnabled(false);




///////////////////////////////////////////////////////////////
btnDelete = new JButton("Delete");
btnDelete.setForeground(new Color(165, 42, 42));
btnDelete.setBounds(268, 13, 97, 25);
panel.add(btnDelete);
btnDelete.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		txtPkgName.setEnabled(true); //enabling the text fields to do delete
		txtPkgStartDate.setEnabled(true);
		txtPkgEndDate.setEnabled(true);
		txtPkgDescription.setEnabled(true);
		txtPkgPrice.setEnabled(true);
		txtPkgAgnCommission.setEnabled(true);
		btnSave.setEnabled(true); //enabling SAVE button after edit
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); //MySql driver
			
			Properties info = new Properties();
			info.put("user", "root");
			info.put("password", "");
			
			Connection conn = 
			DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //Connection string MySQL
			
			
			String deleteQuery = "DELETE FROM PACKAGES WHERE PKGNAME=?";
			PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
			String selectedpkgName = (String)cboPackage.getSelectedItem(); 
			pstmt.setString(1, selectedpkgName);
			
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"Data Deleted Successfully!");
			
		
		}
		catch (Exception ex)
		{
			
			System.out.println(ex);
		}
		
	}
});
btnDelete.setEnabled(false);

JButton btnClear = new JButton("Clear");
btnClear.setForeground(new Color(165, 42, 42));
btnClear.setBounds(394, 13, 97, 25);
panel.add(btnClear);
btnClear.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		txtPkgId.setText("");
		txtPkgName.setText("");
		txtPkgDescription.setText("");
		txtPkgStartDate.setText("");
		txtPkgEndDate.setText("");
		txtPkgPrice.setText("");
		txtPkgAgnCommission.setText("");
		
		scrollPane.setViewportView(null);
		
	}
});
btnClear.setEnabled(true);
		
		
		
		}
	}

