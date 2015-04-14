package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewAgentForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewFirstName;
	private JTextField txtNewMiddle;
	private JTextField txtNewLastName;
	private JTextField txtNewPhone;
	private JTextField txtNewEmal;
	private JTextField txtNewPosition;
	private static JButton btnSave;
	private static JButton btnCancel;

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
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); // close new agent form
			}
		});
		btnCancel.setForeground(new Color(165, 42, 42));
		btnCancel.setEnabled(false);
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
		
		txtNewEmal = new JTextField();
		txtNewEmal.setColumns(10);
		txtNewEmal.setBounds(196, 179, 254, 19);
		panel.add(txtNewEmal);
		
		JButton button_1 = new JButton("Save");
		button_1.setForeground(new Color(165, 42, 42));
		button_1.setEnabled(false);
		button_1.setBounds(300, 299, 75, 25);
		panel.add(button_1);
		
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
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(196, 265, 74, 22);
		panel.add(comboBox_1);
	}
}
