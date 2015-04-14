package ui;
/* 
 * Coded by:= Joel D'Arnot
 * ID:000531776
 *
 * */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

//Class start from here
public class AgentFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public static JTextField txtAgentID;
	public static JTextField txtFirstName;
	public static JTextField txtMiddle;
	public static JTextField txtLastName;
	public static JTextField txtPhone;
	public static JTextField txtEmail;
	private static JButton btnEdit;
	private static JButton btnAdd;
	public static JComboBox<String> cbAgent;
	public static JComboBox<String> cbAgencyID;
	private static JButton btnSave;
	private static JButton btnCancel;
	public static String firstName;
	public static String lastName;
	public static String agencyId;
	public static JTextField txtPosition;
	private static String fullName;
	private static ResultSet rs;
	private JTextField txtAgencyID;
	private JTable table;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentFrame frame = new AgentFrame();
					
					//method call when application is run
					LoadData();
					frame.setVisible(true);
				
				}
				 catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Dispaly data into ComboBox when load the application 
	public static void LoadData()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver"); //get connection using oracle database
			Properties info = new Properties();
			info.put("user", "root");// set username
			info.put("password", ""); //set password
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //get connection using connection path
			Statement stmt = conn.createStatement(); 
			rs = stmt.executeQuery("SELECT * FROM agents"); //load agent table from the database
			//load firstname and lastname into combobox
			while (rs.next())
			{
				firstName = rs.getString("AGTFIRSTNAME"); 
				//System.out.println(firstName);
				lastName = rs.getString ("AGTLASTNAME");
				fullName = firstName +" "+ lastName;
				cbAgent.addItem(fullName);			
			}
			firstName = (String)cbAgent.getSelectedItem();
		} catch (ClassNotFoundException | SQLException ex) 
		{
			// TODO Auto-generated catch block
			System.out.println(ex);
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); //get connection using oracle database
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
		
		//btnLoad.setEnabled(false);
		btnCancel.setVisible(false);
		btnEdit.setEnabled(true);
		cbAgent.setEnabled(true);
		cbAgencyID.setVisible(false); //disable agencyid combobox (enable later to add)
	
	}
	/**
	 * Create the frame.
	 */
	public AgentFrame() {
		setTitle("AGENT INFORMATION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Agent comboBox label
		JLabel lblAgentName = new JLabel("Select Agent :");
		lblAgentName.setForeground(new Color(165, 42, 42));
		lblAgentName.setBounds(23, 16, 152, 33);
		contentPane.add(lblAgentName);
		
		//AgentID 
		JLabel lblAgentId = new JLabel("AgentID :");
		lblAgentId.setForeground(new Color(165, 42, 42));
		lblAgentId.setBounds(23, 65, 116, 26);
		contentPane.add(lblAgentId);
		
		//AgentFirstName
		JLabel lblAgentFirstName = new JLabel("Agent FirstName :");
		lblAgentFirstName.setForeground(new Color(165, 42, 42));
		lblAgentFirstName.setBounds(23, 107, 152, 26);
		contentPane.add(lblAgentFirstName);
		
		//AgentMiddleInitial
		JLabel lblAgentMiddleInitial = new JLabel("Agent MiddleInitial :");
		lblAgentMiddleInitial.setForeground(new Color(165, 42, 42));
		lblAgentMiddleInitial.setBounds(23, 149, 152, 26);
		contentPane.add(lblAgentMiddleInitial);
		
		//AgentLastName
		JLabel lblAgentLastName = new JLabel("Agent LastName :");
		lblAgentLastName.setForeground(new Color(165, 42, 42));
		lblAgentLastName.setBounds(23, 191, 137, 26);
		contentPane.add(lblAgentLastName);
		
		//Agent Business Phone
		JLabel lblAgentBusPhone = new JLabel("Agent Phone :");
		lblAgentBusPhone.setForeground(new Color(165, 42, 42));
		lblAgentBusPhone.setBounds(23, 233, 165, 26);
		contentPane.add(lblAgentBusPhone);
		
		//Agent Email
		JLabel lblAgentEmail = new JLabel("Agent Email :");
		lblAgentEmail.setForeground(new Color(165, 42, 42));
		lblAgentEmail.setBounds(23, 275, 137, 26);
		contentPane.add(lblAgentEmail);
		
		//Load data into ComboBox
		cbAgent = new JComboBox();
		cbAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver"); //get connection using oracle database
					Properties info = new Properties();
					info.put("user", "root");
					info.put("password", "");
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //take data using oracle driver path 
					Statement stmt = conn.createStatement();
					PreparedStatement pst = conn.prepareStatement("select * from agents where agtfirstname=?");//using prepared object display agent firstname into comboBox
					String selectedName = (String)cbAgent.getSelectedItem();
					String [] names = selectedName.split(" "); //using split() matches argument with database
					firstName = names[0];
					pst.setString(1, firstName);
					
					ResultSet rs1 = pst.executeQuery();
					while (rs1.next())
						{
							String agentId = rs1.getString("agentid");
							String agentFName = rs1.getString("agtfirstname");
							String agentMidini = rs1.getString("agtmiddleInitial");
							String agentLName = rs1.getString("agtlastname");
							String agentBusPhone = rs1.getString("agtbusphone");
							String agentEMail = rs1.getString("agtemail");
							String agentPosition = rs1.getString("agtposition");
							String agnId = rs1.getString("agencyid");
											
							txtAgentID.setText(agentId);
							txtFirstName.setText(agentFName);
							txtMiddle.setText(agentMidini);
							txtLastName.setText(agentLName);
							txtPhone.setText(agentBusPhone);
							txtEmail.setText(agentEMail);
							txtPosition.setText(agentPosition);
							txtAgencyID.setText(agnId);
							
							Statement st;
							Vector data = new Vector();
							Vector columnNames = new Vector();
							columnNames.addElement("Agent ID");
							columnNames.addElement("First Name");
							columnNames.addElement("Last Name");
							table = new JTable(data,columnNames);
							scrollPane.setViewportView(table);						
							st = conn.createStatement();
							String getAgent = txtAgentID.getText();
						    ResultSet res = st.executeQuery("SELECT Agents.AGENTID, Customers.CUSTFIRSTNAME, Customers.CUSTLASTNAME "
						    							+"	FROM Agents "
						    							+"	INNER JOIN Customers "
						    							+"	ON Agents.AGENTID=Customers.AGENTID where Agents.AGENTID='"+getAgent+"';"); 
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
					
				}catch (ClassNotFoundException | SQLException e1) 
				{
					// TODO Auto-generated catch block
					System.out.println(e1);
					e1.printStackTrace();
				}
				
			}
		});
		cbAgent.setEnabled(false);
		cbAgent.setBounds(207, 22, 254, 24);
		contentPane.add(cbAgent);
		
		//Edit data using btnEdit
		btnEdit = new JButton("Edit");
		btnEdit.setForeground(new Color(165, 42, 42));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFirstName.setEnabled(true);
				txtMiddle.setEnabled(true);
				txtLastName.setEnabled(true);
				txtPhone.setEnabled(true);
				txtEmail.setEnabled(true);
				txtPosition.setEnabled(true);
				cbAgencyID.setVisible(true);
				btnSave.setEnabled(true);
				btnCancel.setVisible(true);
				btnEdit.setVisible(false);
				txtAgencyID.setVisible(false);
				
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(395, 402, 75, 25);
		contentPane.add(btnEdit);
		
		//AgentId textField
		txtAgentID = new JTextField();
		txtAgentID.setEnabled(false);
		txtAgentID.setEditable(false);
		txtAgentID.setBounds(207, 68, 74, 19);
		contentPane.add(txtAgentID);
		txtAgentID.setColumns(10);
		
		//AgentFirstName TextField
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(207, 109, 254, 19);
		contentPane.add(txtFirstName);
		
		//AgentMiddleInitial TextField
		txtMiddle = new JTextField();
		txtMiddle.setColumns(10);
		txtMiddle.setBounds(207, 150, 254, 19);
		contentPane.add(txtMiddle);
		
		//AgentLastName TextField
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(207, 191, 254, 19);
		contentPane.add(txtLastName);
		
		//AgentBusiness phone TextField
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(207, 232, 254, 19);
		contentPane.add(txtPhone);
		
		//AgentEmail TextField
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(207, 273, 254, 19);
		contentPane.add(txtEmail);
		
		//Save data using btnSave
		btnSave = new JButton("Save");
		btnSave.setForeground(new Color(165, 42, 42));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver"); //get connection using oracle database
					Properties info = new Properties();
					info.put("user", "root"); //set username
					info.put("password", "");//set password
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", info); //take data using oracle driver path
					String agentId = txtAgentID.getText();
					String agentFName = txtFirstName.getText();
					String agentMidini = txtMiddle.getText();
					String agentLName = txtLastName.getText();
					String agentBusPhone = txtPhone.getText();
					String agentEMail = txtEmail.getText();
					String agentPosition =txtPosition.getText();
					String agnId = (String)cbAgencyID.getSelectedItem();
					
					
					//Update data using updatequery
					String updateQuery =  "Update agents set AGENTID='"+agentId+"',AGTFIRSTNAME='"+agentFName+"',AGTMIDDLEINITIAL='"+agentMidini+"',AGTLASTNAME='"+agentLName+"',AGTBUSPHONE='"+agentBusPhone+"',AGTEMAIL='"+agentEMail+"',AGTPOSITION='"+agentPosition+"',AGENCYID='"+agnId+"' where AGENTID='"+agentId+"'";
					PreparedStatement pst = conn.prepareStatement(updateQuery);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Your's Data is Updated!");
					pst.close();
				}catch (Exception ex)
				{
					System.out.println(ex);
				}
				//when click on save button it's automatic setenabled false all the textfield
				txtFirstName.setEnabled(false);
				txtMiddle.setEnabled(false);
				txtLastName.setEnabled(false);
				txtPhone.setEnabled(false);
				txtEmail.setEnabled(false);
				txtPosition.setEnabled(false);
				btnSave.setEnabled(false);
				cbAgencyID.setVisible(false);
				txtAgencyID.setVisible(true);
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(300, 402, 75, 25);
		contentPane.add(btnSave);

		//Lable AgencyId
		JLabel lblAgencyId = new JLabel("Agency ID");
		lblAgencyId.setForeground(new Color(165, 42, 42));
		lblAgencyId.setBounds(23, 359, 70, 15);
		contentPane.add(lblAgencyId);
		
		//Lable Agent Position
		JLabel lblAgentPosition = new JLabel("Agent Position");
		lblAgentPosition.setForeground(new Color(165, 42, 42));
		lblAgentPosition.setBounds(23, 317, 137, 26);
		contentPane.add(lblAgentPosition);
		
		//Agency Position TextField
		txtPosition = new JTextField();
		txtPosition.setBounds(207, 314, 180, 19);
		contentPane.add(txtPosition);
		txtPosition.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(165, 42, 42));
		btnAdd.setEnabled(true);
		btnAdd.setBounds(208, 402, 75, 25);
		contentPane.add(btnAdd);
		
		txtAgencyID = new JTextField();
		txtAgencyID.setEnabled(false);
		txtAgencyID.setEditable(false);
		txtAgencyID.setColumns(10);
		txtAgencyID.setBounds(207, 355, 74, 19);
		contentPane.add(txtAgencyID);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(482, 36, 308, 391);
		contentPane.add(scrollPane);
		
		cbAgencyID = new JComboBox();
		cbAgencyID.setBounds(207, 355, 74, 22);
		contentPane.add(cbAgencyID);
		
		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setBounds(609, 16, 85, 16);
		lblCustomers.setForeground(new Color(165, 42, 42));
		contentPane.add(lblCustomers);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEdit.setVisible(true);
				cbAgencyID.setVisible(false);
				txtAgencyID.setVisible(true);
			}
		});
		btnCancel.setForeground(new Color(165, 42, 42));
		btnCancel.setEnabled(true);
		btnCancel.setBounds(395, 402, 75, 25);
		contentPane.add(btnCancel);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new NewAgentForm().setVisible(true); // load NewAgentForm when btnAdd is pressed
				
				// testing code
/*				txtFirstName.setEnabled(true); //enabling the text fields to do add
				txtMiddle.setEnabled(true);
				txtLastName.setEnabled(true);
				txtPhone.setEnabled(true);
				txtEmail.setEnabled(true);
				btnSave.setEnabled(true); //enabling SAVE button after edit
*/				

				
			}
		});
	}
}
