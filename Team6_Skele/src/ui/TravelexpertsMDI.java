import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JProgressBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import DatabaseModel.PackageDB;
import javax.swing.JMenuItem;
import javax.swing.JMenu;



public class TravelexpertsMDI {

	private JFrame frmTravelExpertsManagement;
	private Connection conn;
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
		frmTravelExpertsManagement = new JFrame();
		frmTravelExpertsManagement.setTitle("Travel Experts Management");
		frmTravelExpertsManagement.setBounds(100, 100, 839, 580);
		frmTravelExpertsManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTravelExpertsManagement.getContentPane().setLayout(null);
		
		MenuBar = new JMenuBar();
		MenuBar.setBounds(0, 0, 821, 26);
		frmTravelExpertsManagement.getContentPane().add(MenuBar);
		
		JMenu mnDisplayItems = new JMenu("Display Items");
		MenuBar.add(mnDisplayItems);
		JMenuItem mntmAgents = new JMenuItem("Agents");
		mnDisplayItems.add(mntmAgents);
		
		JMenuItem mntmPackages = new JMenuItem("Packages");
		mnDisplayItems.add(mntmPackages);
	}
}
