package ui;
/* 
 * Coded by:= Joel D'Arnot
 * ID:000531776
 *
 * */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.*;

public class NewAgentForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewFirstName;
	private JTextField txtNewMiddle;
	private JTextField txtNewLastName;
	private JTextField txtNewPhone;
	private JTextField txtNewEmail;
	private JTextField txtNewPosition;
	private static JButton btnSave;
	private static JButton btnCancel;
	public static JComboBox<String> cbAgencyID;
	private static ResultSet rs;
	private static String agencyId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAgentForm frame = new NewAgentForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void LoadData()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); //get connection using mysql database
			Properties info = new Properties();
			info.put("user", "root");// set username
			info.put("password", ""); //set password
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //get connection using connection path
			Statement stmt = conn.createStatement(); 
			rs = stmt.executeQuery("SELECT DISTINCT * FROM agencies"); //load agent table from the database
			//load agencyId into combobox
			while (rs.next())
			{
				agencyId = rs.getString("AGENCYID"); 				
				cbAgencyID.addItem(agencyId);			
			}
			agencyId = (String)cbAgencyID.getSelectedItem();
		} catch (ClassNotFoundException | SQLException ex) 
		{
			// TODO Auto-generated catch block
			System.out.println(ex);
		}
	//	cbAgencyID.setEnabled(false); //disable agencyid combobox (enable later to add)
	
	}

	/**
	 * Create the frame.
	 */
	public NewAgentForm() {
		setTitle("New Agent Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 474, 349);
		contentPane.add(panel);
		
		JLabel label_2 = new JLabel("Agent FirstName :");
		label_2.setForeground(new Color(165, 42, 42));
		label_2.setBounds(12, 13, 152, 26);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Agent MiddleInitial :");
		label_3.setForeground(new Color(165, 42, 42));
		label_3.setBounds(12, 55, 152, 26);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Agent LastName :");
		label_4.setForeground(new Color(165, 42, 42));
		label_4.setBounds(12, 97, 137, 26);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Agent Phone :");
		label_5.setForeground(new Color(165, 42, 42));
		label_5.setBounds(12, 139, 165, 26);
		panel.add(label_5);
		
		JLabel lblAgentEmail = new JLabel("Agent Email :");
		lblAgentEmail.setForeground(new Color(165, 42, 42));
		lblAgentEmail.setBounds(12, 181, 137, 26);
		panel.add(lblAgentEmail);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NewAgentForm.this.dispose(); // close new agent form
			}
		});
		btnCancel.setForeground(new Color(165, 42, 42));
		btnCancel.setEnabled(true);
		btnCancel.setBounds(387, 299, 75, 25);
		panel.add(btnCancel);
		
		txtNewFirstName = new JTextField();
		txtNewFirstName.setColumns(10);
		txtNewFirstName.setBounds(196, 15, 254, 19);
		panel.add(txtNewFirstName);
		
		txtNewMiddle = new JTextField();
		txtNewMiddle.setColumns(10);
		txtNewMiddle.setBounds(196, 56, 254, 19);
		panel.add(txtNewMiddle);
		
		txtNewLastName = new JTextField();
		txtNewLastName.setColumns(10);
		txtNewLastName.setBounds(196, 97, 254, 19);
		panel.add(txtNewLastName);
		
		txtNewPhone = new JTextField();
		txtNewPhone.setColumns(10);
		txtNewPhone.setBounds(196, 138, 254, 19);
		panel.add(txtNewPhone);
		
		txtNewEmail = new JTextField();
		txtNewEmail.setColumns(10);
		txtNewEmail.setBounds(196, 179, 254, 19);
		panel.add(txtNewEmail);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver"); //get connection using mysql database
					Properties info = new Properties();
					info.put("user", "root"); //set username
					info.put("password", "");//set password
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //take data using mysql driver path
				//	String agentId = txtNewAgentID.getText();
					String agentFName = txtNewFirstName.getText();
					String agentMidini = txtNewMiddle.getText();
					String agentLName = txtNewLastName.getText();
					String agentBusPhone = txtNewPhone.getText();
					String agentEMail = txtNewEmail.getText();
					String agentPosition =txtNewPosition.getText();
					String agnId = (String)cbAgencyID.getSelectedItem();
					
					
					//Update data using updatequery
					String updateQuery =  "INSERT INTO agents "
							+ "(AGENTID,AGTFIRSTNAME,AGTMIDDLEINITIAL,AGTLASTNAME,AGTBUSPHONE,AGTEMAIL,AGTPOSITION,AGENCYID,AGTSTATUS) "
							+ "VALUES "
							+ "(NULL,'"+agentFName+"','"+agentMidini+"','"+agentLName+"','"+agentBusPhone+"','"+agentEMail+"','"+agentPosition+"','"+agnId+"',DEFAULT)";
					PreparedStatement pst = conn.prepareStatement(updateQuery);
					
					// validating fields
					if (((agentFName.isEmpty())) || (agentLName.isEmpty()))
					{					
						JOptionPane.showMessageDialog(null, "First and Last names are required");
					}
					Pattern pattern = Pattern.compile("^\\(\\d{3}\\) ?\\d{3}( |-)?\\d{4}|^\\d{3}( |-)?\\d{3}( |-)?\\d{4}");
					Matcher matcher = pattern.matcher(txtNewPhone.getText());
					if(!(matcher.matches()))
					{
						JOptionPane.showMessageDialog(null, "Invalid phone number");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Your's Data is Updated!");
						NewAgentForm.this.dispose();
						// only executes and closes if everything validated properly
						pst.execute();
						pst.close();
					}

				}catch (Exception ex)
				{
					System.out.println(ex);
				}
				
			}
		});
		btnSave.setForeground(new Color(165, 42, 42));
		btnSave.setEnabled(true);
		btnSave.setBounds(300, 299, 75, 25);
		panel.add(btnSave);
		
		JLabel label_7 = new JLabel("Agency ID");
		label_7.setForeground(new Color(165, 42, 42));
		label_7.setBounds(12, 265, 70, 15);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Agent Position");
		label_8.setForeground(new Color(165, 42, 42));
		label_8.setBounds(12, 223, 137, 26);
		panel.add(label_8);
		
		txtNewPosition = new JTextField();
		txtNewPosition.setColumns(10);
		txtNewPosition.setBounds(196, 220, 180, 19);
		panel.add(txtNewPosition);
		
		cbAgencyID = new JComboBox();
		cbAgencyID.setBounds(196, 265, 74, 22);
		panel.add(cbAgencyID);
		LoadData(); // load data into combobox
	}
}
