package ui;
/*Project Workshop CMPP264 Java - Team 6
 * Instructor: Harvey Peters
 * Created by: Megha Patel(000679647)
 * Date: March/23/2015
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;
import java.lang.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.*;

//import entities.Package;

import java.awt.*;

import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;


public class PackageMaintenance extends JFrame {

	private JPanel contentPane; //declaring private variables to use only in this class
	private JTextField txtPkgId;
	private JTextField txtPkgName;
	private JTextField txtPkgDescription;
	private JTextField txtPkgPrice;
	private JTextField txtPkgAgnCommission;
	private static JComboBox<String> cboPackage;
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
	private SqlDateModel m2;
	private SqlDateModel m1;
	private JTable table_1;
	
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
		
		//JDate
		m1 = new SqlDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(m1,p1);
		JDatePickerImpl startDate = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		startDate.getJFormattedTextField().setBorder(new LineBorder(Color.MAGENTA));
		
		startDate.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
		startDate.setLocation(196, 149);
		startDate.setSize(new Dimension(200, 23));
		contentPane.add(startDate);
		
		m2 = new SqlDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(m2,p2);
		JDatePickerImpl endDate = new JDatePickerImpl(datePanel2,new DateLabelFormatter());
		endDate.getJFormattedTextField().setBorder(new LineBorder(Color.MAGENTA));
		
		endDate.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
		endDate.setLocation(196, 183);
		endDate.setSize(new Dimension(200,25));
		contentPane.add(endDate);
		// end of JDate
	
		cboPackage = new JComboBox(); //creating combo box
		cboPackage.setBorder(new LineBorder(Color.MAGENTA));
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
					int pkgId = rs.getInt("PACKAGEID"); //storing values from DB to strings 
					String packageName = rs.getString("PKGNAME");
					Date packageStartDate = rs.getDate("PKGSTARTDATE");
					Date packageEndDate = rs.getDate("PKGENDDATE");
					String packageDesc = rs.getString("PKGDESC");
					double bsprice = rs.getDouble("PKGBASEPRICE");
					DecimalFormat df = new DecimalFormat("#.00");
					String packageBasePrice = df.format(bsprice);
					double agcomm = rs.getDouble("PKGAGENCYCOMMISSION");
					DecimalFormat df1 = new DecimalFormat("#.00");
					String packageAgencyCommission =  df1.format(agcomm) ;
									
					txtPkgId.setText(String.valueOf(pkgId)); //displaying DB values into text fields stored already in strings
					txtPkgName.setText(packageName);
					//txtPkgStartDate.setText(packageStartDate);
					//txtPkgEndDate.setText(packageEndDate);
					m1.setValue(packageStartDate);
					m2.setValue(packageEndDate);
					txtPkgDescription.setText(packageDesc);
					txtPkgPrice.setText(packageBasePrice);
					txtPkgAgnCommission.setText(packageAgencyCommission);
					
					Statement st;
					Vector data = new Vector();

					Vector columnNames = new Vector();
					columnNames.addElement("Products Name");
					columnNames.addElement("Supplier Name");
					table_1 = new JTable(data,columnNames);
					
					scrollPane.setViewportView(table_1);
					

					st = conn.createStatement();
					
				    ResultSet res = st.executeQuery("select distinct p.prodname, s.supname from Suppliers s join Products_Suppliers ps on s.SupplierId=ps.SupplierId join Products p on p.ProductId=ps.ProductId");
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
			
		}catch (SQLException | ClassNotFoundException e1) //catching the exceptions for above catch
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
		txtPkgName.setBorder(new LineBorder(Color.MAGENTA));
		txtPkgName.setForeground(new Color(165, 42, 42));
		txtPkgName.setEnabled(false);
		txtPkgName.setBounds(198, 113, 200, 22);
		contentPane.add(txtPkgName);
		txtPkgName.setColumns(10);
		
		txtPkgDescription = new JTextField();
		txtPkgDescription.setBorder(new LineBorder(Color.MAGENTA));
		txtPkgDescription.setForeground(new Color(165, 42, 42));
		txtPkgDescription.setEnabled(false);
		txtPkgDescription.setColumns(10);
		txtPkgDescription.setBounds(198, 225, 200, 22);
		contentPane.add(txtPkgDescription);
		
		txtPkgPrice = new JTextField();
		txtPkgPrice.setBorder(new LineBorder(Color.MAGENTA));
		txtPkgPrice.setForeground(new Color(165, 42, 42));
		txtPkgPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPkgPrice.setEnabled(false);
		txtPkgPrice.setColumns(10);
		txtPkgPrice.setBounds(198, 261, 200, 22);
		contentPane.add(txtPkgPrice);
		
		btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\save31 (2).png"));
		btnSave.setBorder(new LineBorder(Color.MAGENTA));
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
					Date uppkgStartDate = (Date)startDate.getModel().getValue();
					Date uppkgEndDate = (Date)endDate.getModel().getValue();
					String uppkgDescription = txtPkgDescription.getText();
					double uppkgBasePrice = Double.parseDouble(txtPkgPrice.getText());
					DecimalFormat df = new DecimalFormat("#.00");
					String upprice = df.format(uppkgBasePrice);
					
					double uppkgAgencyCommission = Double.parseDouble(txtPkgAgnCommission.getText()); 
					DecimalFormat df1 = new DecimalFormat("#.00");
					String uppAgncom =df1.format(uppkgBasePrice) ;
					
					String updateQuery =  "Update packages set PKGNAME='"+uppkgName+"',PKGSTARTDATE='"+uppkgStartDate+"',PKGENDDATE='"+uppkgEndDate+"',PKGDESC='"+uppkgDescription+"',PKGBASEPRICE='"+uppkgBasePrice+"',PKGAGENCYCOMMISSION='"+uppkgAgencyCommission+"' where PACKAGEID='"+uppackageId+"'";
					PreparedStatement pstmt = conn.prepareStatement(updateQuery);
					pstmt.execute();
					//when AgencyCommission, Package Name and Package Description not work at that it shows error message 
					if(uppkgBasePrice > uppkgAgencyCommission && !(uppkgName.isEmpty()) || (!(uppkgDescription.isEmpty())))
					{
						JOptionPane.showMessageDialog(null,"Data Updated Successfully!"); //displaying message window for action performed
						txtPkgName.setEnabled(false);
						startDate.setEnabled(false);
						endDate.setEnabled(false);
						txtPkgDescription.setEnabled(false);
						txtPkgPrice.setEnabled(false);
						txtPkgAgnCommission.setEnabled(false);
						btnSave.setEnabled(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Agency Commission never Higher then Base Price"+ "\n "+
					                                   "OR May be" +"\n"+ "Package Name and Paackage Desciption can not be left Blank");
					}
					pstmt.close();
				}catch (NumberFormatException | SQLException | ClassNotFoundException ex)
				{
					
					System.out.println(ex);
					ex.printStackTrace();
				}
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(442, 219, 101, 35);
		contentPane.add(btnSave);
		
		
		
		btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\door9.png"));
		btnExit.setBorder(new LineBorder(Color.MAGENTA));
		btnExit.setForeground(new Color(165, 42, 42));
		btnExit.setBounds(442, 278, 106, 35);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PackageMaintenance.this.dispose();
			}
		});
		
		btnLoad = new JButton("");
		btnLoad.setIcon(new ImageIcon("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\packages2.png"));
		btnLoad.setBorder(new LineBorder(Color.MAGENTA));
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
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //Connection string MySQL
					Statement stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from packages");
					while (rs.next())
					{
						pkgName = rs.getString("PKGNAME");
						cboPackage.addItem(pkgName);			
					}
					pkgName = (String)cboPackage.getSelectedItem();
				} catch (SQLException | ClassNotFoundException e1) 
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
		
		btnLoad.setBounds(410, 33, 101, 35);
		contentPane.add(btnLoad);
		
		
		lblPackageAgencyCommission = new JLabel("Agency Commission");
		lblPackageAgencyCommission.setForeground(new Color(165, 42, 42));
		lblPackageAgencyCommission.setBounds(29, 293, 157, 15);
		contentPane.add(lblPackageAgencyCommission);
		
		txtPkgAgnCommission = new JTextField();
		txtPkgAgnCommission.setBorder(new LineBorder(Color.MAGENTA));
		txtPkgAgnCommission.setForeground(new Color(165, 42, 42));
		txtPkgAgnCommission.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPkgAgnCommission.setEnabled(false);
		txtPkgAgnCommission.setColumns(10);
		txtPkgAgnCommission.setBounds(198, 295, 200, 22);
		contentPane.add(txtPkgAgnCommission);
		
		
		
		txtPkgId = new JTextField();
		txtPkgId.setBorder(new LineBorder(Color.MAGENTA));
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
		scrollPane.setBorder(new LineBorder(Color.MAGENTA));
		scrollPane.setBounds(29, 393, 531, 278);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setBounds(24,393,536,278);
		scrollPane.setViewportView(table_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 326, 526, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		//////////////////////////////////////////
		
		
		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\shopping-cart9.png"));
		btnAdd.setBorder(new LineBorder(Color.MAGENTA));
		btnAdd.setForeground(new Color(165, 42, 42));
		btnAdd.setBounds(12, 7, 97, 38);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtPkgName.setEnabled(true); //enabling the text fields to do add
				startDate.setEnabled(true);
				endDate.setEnabled(true);
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
					//String inpkgStartDate = (String)startDate.getModel().getValue();
					Date inpkgStartDate = (Date)startDate.getModel().getValue();
					Date inpkgEndDate = (Date)endDate.getModel().getValue();
					String inpkgDescription = txtPkgDescription.getText();
					double inpkgBasePrice = (Double.valueOf(txtPkgPrice.getText()));
					double inpkgAgencyCommission = (Double.valueOf(txtPkgAgnCommission.getText()));
					
					//String insertQuery =  ("insert into packages (PACKAGEID,PKGNAME, PKGSTARTDATE, PKGENDDATE, PKGDESC, PKGBASEPRICE, PKGAGENCYCOMMISSION) values (NULL,'"+inpkgName+"','"+inpkgStartDate+"','"+inpkgEndDate+"','"+inpkgDescription+"','"+inpkgBasePrice+"','"+inpkgAgencyCommission+"')");
					String insertQuery = ("insert into packages(PackageId,PkgName,PkgStartDate,PkgEndDate,PkgDesc,PkgBasePrice,PkgAgencyCommission) values(NULL,?,?,?,?,?,?)");
					PreparedStatement pstmt = conn.prepareStatement(insertQuery);
					
					//if BasePrice is lower then Agency commission it shows error message
					if(inpkgBasePrice > inpkgAgencyCommission && !(inpkgName.isEmpty()) || (!(inpkgDescription.isEmpty())))
					{
						pstmt.setString(1, inpkgName);
						pstmt.setDate(2, inpkgStartDate);
						pstmt.setDate(3, inpkgEndDate);
						pstmt.setString(4, inpkgDescription);
						pstmt.setDouble(5, inpkgBasePrice);
						pstmt.setDouble(6, inpkgAgencyCommission);
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"Data Inserted Successfully!"); //displaying message window for action performed
						btnAdd.setEnabled(true);
					}	
					else
					{
						JOptionPane.showMessageDialog(null,"Agency Commission Never Higher Then Base Price!!!! or Package Name and Package Description can not be null"); //displaying message window for action performed
					}
					pstmt.close();
				}catch (Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		
		btnEdit = new JButton("");
		btnEdit.setIcon(new ImageIcon("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\triangle7.png"));
		btnEdit.setBorder(new LineBorder(Color.MAGENTA));
		btnEdit.setForeground(new Color(165, 42, 42));
		btnEdit.setBounds(143, 7, 97, 38);
		panel.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPkgName.setEnabled(true); //enabling the text fields to do edit
				startDate.setEnabled(true);
				endDate.setEnabled(true);
				txtPkgDescription.setEnabled(true);
				txtPkgPrice.setEnabled(true);
				txtPkgAgnCommission.setEnabled(true);
				btnSave.setEnabled(true); //enabling SAVE button after edit
				}
			});
		
				btnEdit.setEnabled(false);

///////////////////////////////////////////////////////////////
btnDelete = new JButton("");
btnDelete.setIcon(new ImageIcon("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\rubbish.png"));
btnDelete.setBorder(new LineBorder(Color.MAGENTA));
btnDelete.setForeground(new Color(165, 42, 42));
btnDelete.setBounds(268, 7, 97, 38);
panel.add(btnDelete);
btnDelete.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		txtPkgName.setEnabled(true); //enabling the text fields to do delete
		startDate.setEnabled(true);
		endDate.setEnabled(true);
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

JButton btnClear = new JButton("");
btnClear.setIcon(new ImageIcon("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\three60.png"));
btnClear.setBorder(new LineBorder(Color.MAGENTA));
btnClear.setForeground(new Color(165, 42, 42));
btnClear.setBounds(394, 7, 97, 38);
panel.add(btnClear);
btnClear.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		txtPkgId.setText("");
		txtPkgName.setText("");
		startDate.getModel().setValue(null);
		endDate.getModel().setValue(null);
		txtPkgDescription.setText("");
		txtPkgPrice.setText("");
		txtPkgAgnCommission.setText("");
		
		scrollPane.setViewportView(null);
		
	}
});
btnClear.setEnabled(true);
	}

 public class DateLabelFormatter extends AbstractFormatter 
{
	
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	@Override
	public java.lang.Object stringToValue(String text)throws ParseException 
	{
		// TODO Auto-generated method stub
		return dateFormatter.parseObject(text);
	}
	
	@Override
	public String valueToString(java.lang.Object value)
			throws ParseException {
		// TODO Auto-generated method stub
		if(value !=null)
		{
			Calendar cal = (Calendar)value;
			return dateFormatter.format(cal.getTime());
		}
		return "";
	}
}
		}
	


