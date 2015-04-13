package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AgentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtAgencyId;
	private JTextField txtPosition;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtLastName;
	private JTextField txtMiddle;
	private JTextField txtFirstName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{ // gets connection
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521:xe","ictoosd","ictoosd");
					String sql = "SELECT * FROM agents";
					Statement stmt = conn.createStatement();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					AgentFrame frame = new AgentFrame();
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
	public AgentFrame() {
		setTitle("Agents");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectAnAgent = new JLabel("Select an Agent:");
		lblSelectAnAgent.setBounds(12, 13, 95, 16);
		contentPane.add(lblSelectAnAgent);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		comboBox.setBounds(119, 10, 116, 22);
		contentPane.add(comboBox);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(12, 42, 95, 16);
		contentPane.add(lblFirstName);
		
		JLabel lblMiddleInitial = new JLabel("Middle Initial:");
		lblMiddleInitial.setBounds(12, 71, 95, 16);
		contentPane.add(lblMiddleInitial);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(12, 100, 95, 16);
		contentPane.add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(12, 129, 95, 16);
		contentPane.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 158, 95, 16);
		contentPane.add(lblEmail);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(12, 187, 95, 16);
		contentPane.add(lblPosition);
		
		JLabel lblAgencyId = new JLabel("Agency ID:");
		lblAgencyId.setBounds(12, 216, 95, 16);
		contentPane.add(lblAgencyId);
		
		txtAgencyId = new JTextField();
		txtAgencyId.setBounds(119, 213, 116, 22);
		contentPane.add(txtAgencyId);
		txtAgencyId.setColumns(10);
		
		txtPosition = new JTextField();
		txtPosition.setColumns(10);
		txtPosition.setBounds(119, 181, 116, 22);
		contentPane.add(txtPosition);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(119, 155, 116, 22);
		contentPane.add(txtEmail);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(119, 126, 116, 22);
		contentPane.add(txtPhone);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(119, 97, 116, 22);
		contentPane.add(txtLastName);
		
		txtMiddle = new JTextField();
		txtMiddle.setColumns(10);
		txtMiddle.setBounds(119, 68, 116, 22);
		contentPane.add(txtMiddle);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(119, 39, 116, 22);
		contentPane.add(txtFirstName);
		
		JButton btnAddNewAgent = new JButton("New Agent");
		btnAddNewAgent.setBounds(247, 9, 95, 25);
		contentPane.add(btnAddNewAgent);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(10, 245, 97, 25);
		contentPane.add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(138, 245, 97, 25);
		contentPane.add(btnSave);
	}
}
