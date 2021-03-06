package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

public class TransferCustomersFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInactiveAgent;
	private JLabel lblSelect;
	private static JTable table;
	private static JScrollPane scrollPane;
	private static String rs;
	private static String firstName;
	private static String lastName;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JButton btnLoad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferCustomersFrame frame = new TransferCustomersFrame();
					getAgents();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TransferCustomersFrame() {
		setTitle("Transfer Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInactiveAgent = new JLabel("Inactive Agent:");
		lblInactiveAgent.setBounds(10, 10, 92, 23);
		contentPane.add(lblInactiveAgent);
		
		txtInactiveAgent = new JTextField();
		txtInactiveAgent.setEnabled(false);
		txtInactiveAgent.setBounds(101, 10, 175, 22);
		contentPane.add(txtInactiveAgent);
		txtInactiveAgent.setColumns(10);
		
		lblSelect = new JLabel("Select an agent to transfer customers to:");
		lblSelect.setBounds(10, 53, 243, 23);
		contentPane.add(lblSelect);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(288, 52, 105, 25);
		contentPane.add(btnTransfer);
		
		JButton button = new JButton("Save");
		button.setForeground(new Color(165, 42, 42));
		button.setBounds(318, 297, 75, 25);
		contentPane.add(button);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(165, 42, 42));
		btnCancel.setBounds(10, 297, 75, 25);
		contentPane.add(btnCancel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 89, 383, 201);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		btnLoad = new JButton("Load");
		btnLoad.setBounds(312, 9, 81, 25);
		contentPane.add(btnLoad);
	}
	
	public static void getAgents()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); //get connection using mysql database
			Properties info = new Properties();
			info.put("user", "root");// set username
			info.put("password", ""); //set password
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //get connection using connection path 
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT AGENTID, AGTFIRSTNAME, AGTLASTNAME FROM Agents"); 
			ResultSetMetaData metaData = res.getMetaData();
						
		} catch (ClassNotFoundException | SQLException ex) 
		{
			// TODO Auto-generated catch block
			System.out.println(ex);
			System.out.println("Loaded");
		}
	}
}
