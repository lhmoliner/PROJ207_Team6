
/*Project Workshop CMPP264 Java - Team 6
 * Instructor: Harvey Peters
 * Created by: Megha Patel
 * Date: March/23/2015
 * */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import java.sql.*;
import java.util.Properties;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PackageForm {

	private JFrame frmPackageDetails;
	private static JTextField txtPkgId;
	private static JTextField txtPkgName;
	private static JTextField txtPkgStartDate;
	private static JTextField txtEndDate;
	private static JTextField txtPkgDesc;
	private static JTextField txtPkgPrice;
	private static JTextField txtPkgAgnComm;
	private JScrollPane scrollPane; 
	private static JComboBox<String> cmbPkgSelect;
	private static String PkgName;
	private static JButton btnAdd;
	private static JButton btnEdit;
	private static JButton btnSave;
	private static JButton btnDelete;
	private static Connection conn;
	private static Statement stmt;
	private  static ResultSet rs;
	private JTable table;
	Package pkg = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PackageForm window = new PackageForm();
					window.frmPackageDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PackageForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmPackageDetails = new JFrame();
		frmPackageDetails.getContentPane().setBackground(new Color(128, 0, 128));
		frmPackageDetails.setTitle("PACKAGE DETAILS");
		frmPackageDetails.setBounds(100, 100, 921, 489);
		frmPackageDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPackageDetails.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 68, 496, 273);
		panel.setBackground(SystemColor.windowBorder);
		frmPackageDetails.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPackageId = new JLabel("Package ID");
		lblPackageId.setForeground(SystemColor.text);
		lblPackageId.setBounds(27, 67, 171, 24);
		panel.add(lblPackageId);
		
		JLabel lblPackageName = new JLabel("Package Name");
		lblPackageName.setForeground(SystemColor.text);
		lblPackageName.setBounds(27, 95, 171, 24);
		panel.add(lblPackageName);
		
		JLabel lblPackageStartDate = new JLabel("Package Start Date");
		lblPackageStartDate.setForeground(SystemColor.text);
		lblPackageStartDate.setBounds(27, 121, 171, 24);
		panel.add(lblPackageStartDate);
		
		JLabel lblPackageEndDate = new JLabel("Package End Date");
		lblPackageEndDate.setForeground(SystemColor.text);
		lblPackageEndDate.setBounds(27, 149, 171, 24);
		panel.add(lblPackageEndDate);
		
		JLabel lblPackageDescription = new JLabel("Package Description");
		lblPackageDescription.setForeground(SystemColor.text);
		lblPackageDescription.setBounds(27, 176, 171, 24);
		panel.add(lblPackageDescription);
		
		JLabel lblPackageBasePrice = new JLabel("Package Price");
		lblPackageBasePrice.setForeground(SystemColor.text);
		lblPackageBasePrice.setBounds(27, 205, 171, 24);
		panel.add(lblPackageBasePrice);
		
		JLabel lblPackageAgencyCommission = new JLabel("Package Agency Commission");
		lblPackageAgencyCommission.setForeground(SystemColor.text);
		lblPackageAgencyCommission.setBounds(27, 236, 171, 24);
		panel.add(lblPackageAgencyCommission);
		
		txtPkgId = new JTextField();
		txtPkgId.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		txtPkgId.setBounds(236, 68, 226, 22);
		panel.add(txtPkgId);
		txtPkgId.setColumns(10);
		
		txtPkgName = new JTextField();
		txtPkgName.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		txtPkgName.setColumns(10);
		txtPkgName.setBounds(236, 97, 226, 22);
		panel.add(txtPkgName);
		
		txtPkgStartDate = new JTextField();
		txtPkgStartDate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		txtPkgStartDate.setColumns(10);
		txtPkgStartDate.setBounds(236, 123, 226, 22);
		panel.add(txtPkgStartDate);
		
		txtEndDate = new JTextField();
		txtEndDate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		txtEndDate.setColumns(10);
		txtEndDate.setBounds(236, 150, 226, 22);
		panel.add(txtEndDate);
		
		txtPkgDesc = new JTextField();
		txtPkgDesc.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		txtPkgDesc.setColumns(10);
		txtPkgDesc.setBounds(236, 177, 226, 22);
		panel.add(txtPkgDesc);
		
		txtPkgPrice = new JTextField();
		txtPkgPrice.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		txtPkgPrice.setColumns(10);
		txtPkgPrice.setBounds(236, 206, 226, 22);
		panel.add(txtPkgPrice);
		
		txtPkgAgnComm = new JTextField();
		txtPkgAgnComm.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.MAGENTA));
		txtPkgAgnComm.setColumns(10);
		txtPkgAgnComm.setBounds(236, 235, 226, 22);
		panel.add(txtPkgAgnComm);
		
		//Load data form the database in combo box
		JComboBox cmbPkgSelect = new JComboBox();
		cmbPkgSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				try
				{
					PackageDB.getConnection();
					PreparedStatement pst = conn.prepareStatement("select * from packages where PkgName=?");//querying the DB
					String selectedpkgName = (String)cmbPkgSelect.getSelectedItem(); //Passing packages names to combo box string
					
					pst.setString(1, selectedpkgName);
					
				    while(rs.next())
				    {
				    	pkg = new Package();
						pkg.setPkgId(rs.getInt("PackageId"));
						pkg.setPackageName(rs.getString("PkgName"));
						pkg.setStartDate(rs.getDate("PkgStartDate"));
						pkg.setEndDate(rs.getDate("PkgEndDate"));
						pkg.setPackageDesc(rs.getString("PkgDesc"));
						pkg.setPackagePrice(rs.getDouble("PkgBasePrice"));
						pkg.setAgnComm(rs.getDouble("PkgAgencyCommission"));
						
				    	txtPkgId.setText("PackageID");
				    	txtPkgName.setText("PackageName");
				    	txtPkgStartDate.setText("PkgStartDate");
				    	txtEndDate.setText("PkgEndDate");
				    	txtPkgDesc.setText("PkgDesc");
				    	txtPkgPrice.setText("PkgBasePrice");
				    	txtPkgAgnComm.setText("PkgAgencyCommission");
				    	
				    	Vector data = new Vector();

						Vector columnNames = new Vector();
						columnNames.addElement("Product Name");
						columnNames.addElement("Supplier Name");
						table = new JTable(data,columnNames);
						
						scrollPane.setViewportView(table);

						stmt = conn.createStatement();
						
					    ResultSet res = stmt.executeQuery("select p.prodname, s.supname from Suppliers s join Products_Suppliers ps on s.SupplierId=ps.SupplierId join Products p on p.ProductId=ps.ProductId");
					    ResultSetMetaData metaData = res.getMetaData();
					    int columns = metaData.getColumnCount();
					    while (res.next()) {
					       Vector<Object> row = new Vector<Object>(columns);
					       for (int i = 1; i <= columns; i++) {
					        row.addElement(res.getObject(i));
					       }
					       data.addElement(row);
					    }
				    }
				    pst.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		cmbPkgSelect.setEnabled(true);
		cmbPkgSelect.setBounds(236, 13, 226, 24);
		panel.add(cmbPkgSelect);
		
		JLabel lblSelectPackage = new JLabel("Select Package");
		lblSelectPackage.setForeground(Color.WHITE);
		lblSelectPackage.setBounds(27, 17, 171, 24);
		panel.add(lblSelectPackage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 345, 496, 56);
		panel_1.setBackground(SystemColor.textInactiveText);
		frmPackageDetails.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtPkgName.setEnabled(true); //enabling the text fields to do add
				txtPkgStartDate.setEnabled(true);
				txtEndDate.setEnabled(true);
				txtPkgDesc.setEnabled(true);
				txtPkgPrice.setEnabled(true);
				txtPkgAgnComm.setEnabled(true);
				btnSave.setEnabled(true); //enabling SAVE button after edit
				
				try
				{
					
					PackageDB.getConnection();
					String inpkgName= txtPkgName.getText();
					String inpkgStartDate = txtPkgStartDate.getText();
					String inpkgEndDate = txtEndDate.getText();
					String inpkgDescription = txtPkgDesc.getText();
					String inpkgBasePrice = txtPkgPrice.getText();
					String inpkgAgencyCommission =txtPkgAgnComm.getText();
					
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
		btnAdd.setBounds(23, 13, 97, 25);
		panel_1.add(btnAdd);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPkgName.setEnabled(true); //enabling the text fields to do edit
				txtPkgStartDate.setEnabled(true);
				txtEndDate.setEnabled(true);
				txtPkgDesc.setEnabled(true);
				txtPkgPrice.setEnabled(true);
				txtPkgAgnComm.setEnabled(true);
				btnSave.setEnabled(true); //enabling SAVE button after edit
			}
		});
		btnEdit.setBounds(132, 13, 97, 25);
		panel_1.add(btnEdit);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					PackageDB.getConnection();
					String uppackageId = txtPkgId.getText();
					String uppkgName = txtPkgName.getText();
					String uppkgStartDate = txtPkgStartDate.getText();
					String uppkgEndDate = txtEndDate.getText();
					String uppkgDescription = txtPkgDesc.getText();
					//int upprice = Integer.parseInt(txtPackageBasePrice.getText());
					//String uppkgBasePrice = NumberFormat.getCurrencyInstance().format(upprice);
					String uppkgBasePrice = txtPkgPrice.getText();
					/*int upcomm = Integer.parseInt(txtPackageAgencyCommission.getText());
					String uppkgAgencyCommission =NumberFormat.getCurrenmcyInstance().format(upcomm) ;*/
					String uppkgAgencyCommission = txtPkgAgnComm.getText();
					
					
					String updateQuery =  "Update packages set PACKAGEID='"+uppackageId+"',PKGNAME='"+uppkgName+"',PKGSTARTDATE='"+uppkgStartDate+"',PKGENDDATE='"+uppkgEndDate+"',PKGDESC='"+uppkgDescription+"',PKGBASEPRICE='"+uppkgBasePrice+"',PKGAGENCYCOMMISSION='"+uppkgAgencyCommission+"' where PACKAGEID='"+uppackageId+"'";
					//PACKAGEID='"+uppackageId
					PreparedStatement pstmt = conn.prepareStatement(updateQuery);
					pstmt.execute();
					JOptionPane.showMessageDialog(null,"Data Updated Successfully!"); //displaying message window for action performed
					pstmt.close();
				}catch (NumberFormatException | SQLException ex)
				{
					
					System.out.println(ex);
					ex.printStackTrace();
				}
				txtPkgName.setEnabled(false);
				txtPkgStartDate.setEnabled(false);
				txtEndDate.setEnabled(false);
				txtPkgDesc.setEnabled(false);
				txtPkgPrice.setEnabled(false);
				txtPkgAgnComm.setEnabled(false);
				
				btnSave.setEnabled(false);
				
			}
		});
		btnSave.setBounds(252, 13, 97, 25);
		panel_1.add(btnSave);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPkgName.setEnabled(true); //enabling the text fields to do delete
				txtPkgStartDate.setEnabled(true);
				txtEndDate.setEnabled(true);
				txtPkgDesc.setEnabled(true);
				txtPkgPrice.setEnabled(true);
				txtPkgAgnComm.setEnabled(true);
				btnSave.setEnabled(true); //enabling SAVE button after edit
				
				try
				{
							
					PackageDB.getConnection();
					String deleteQuery = "DELETE FROM PACKAGES WHERE PKGNAME=?";
					PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
					String selectedpkgName = (String)cmbPkgSelect.getSelectedItem(); 
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
		btnDelete.setBounds(375, 13, 97, 25);
		panel_1.add(btnDelete);
		
		JLabel lblPackageInformation = new JLabel("Package Information");
		lblPackageInformation.setBounds(182, 26, 177, 29);
		lblPackageInformation.setForeground(new Color(128, 128, 128));
		lblPackageInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmPackageDetails.getContentPane().add(lblPackageInformation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(528, 68, 363, 333);
		frmPackageDetails.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBounds(582,68,363,333);
		scrollPane.setViewportView(table);
		
	}
}
