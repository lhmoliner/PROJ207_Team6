package ui;
import java.awt.EventQueue;
import java.awt.Window;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JProgressBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Color;
import java.io.File;
import java.io.IOException;



public class TravelexpertsMDI {

	private JFrame frmTravelExpertsManagement;
	private JScrollPane scrollPane;
	private JMenuBar MenuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TravelexpertsMDI window = new TravelexpertsMDI();
					window.frmTravelExpertsManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TravelexpertsMDI() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @return 
	 * @return 
	 */
	private void initialize() {
/*		ImageIcon icon = new ImageIcon("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\three60.png"); 
		JLabel backgrnd = new JLabel();
		backgrnd.setIcon(icon);*/
		frmTravelExpertsManagement = new JFrame();
		frmTravelExpertsManagement.getContentPane().setBackground(Color.LIGHT_GRAY);
	//	frmTravelExpertsManagement.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Joel\\workspace\\PROJ207_Team6\\images\\background.png")))));
		
		frmTravelExpertsManagement.setTitle("Travel Experts Management");
		frmTravelExpertsManagement.setBounds(100, 100, 839, 580);
		frmTravelExpertsManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTravelExpertsManagement.getContentPane().setLayout(null);
		
		MenuBar = new JMenuBar();
		MenuBar.setBounds(0, 0, 102, 26);
		frmTravelExpertsManagement.getContentPane().add(MenuBar);
		
		JMenu mnDisplayItems = new JMenu("Display Items");
		MenuBar.add(mnDisplayItems);
		JMenuItem mntmAgents = new JMenuItem("Agents");
		mntmAgents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AgentFrame().setVisible(true);
			}
		});
		mnDisplayItems.add(mntmAgents);
		
		JMenuItem mntmPackages = new JMenuItem("Packages");
		mntmPackages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PackageMaintenance().setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnDisplayItems.add(mntmPackages);
	}
}
