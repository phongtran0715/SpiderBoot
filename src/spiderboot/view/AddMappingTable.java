package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.helper.ComboboxToolTipRenderer;
import spiderboot.helper.Util;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AddMappingTable extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTimeSync;
	JComboBox cbHomeC;
	JComboBox cbMonitorC;
	JLabel lbHome;
	JLabel lbMonitor;
	List<String> homeTooltips = new ArrayList<String>();
	List<String> monitorTooltips = new ArrayList<String>();
	final String HOME_CHANNEL_TABLE_NAME = "home_channel_list";
	final String MONITOR_CHANNEL_TABLE_NAME = "monitor_channel_list";

	public AddMappingTable() {
		inititalize();
		//configuration for combo box
		ComboboxToolTipRenderer homeRenderer = new ComboboxToolTipRenderer();
		ComboboxToolTipRenderer monitorRenderer = new ComboboxToolTipRenderer();		
		cbHomeC.addItem("");
		homeTooltips.add("Insert home channel ID");
		cbMonitorC.addItem("");
		monitorTooltips.add("Insert monitor channel ID");
		Vector<String> cHomeIdList = getChannelIdList(HOME_CHANNEL_TABLE_NAME);
		Vector<String> cMonitorIdList = getChannelIdList(MONITOR_CHANNEL_TABLE_NAME);
		Enumeration eHome = cHomeIdList.elements();
		while (eHome.hasMoreElements()) {
			String cId = (String)eHome.nextElement();
			cbHomeC.addItem(cId);
			String cName = getChannelNameById(cId, HOME_CHANNEL_TABLE_NAME);
			homeTooltips.add(cName);
		}
		Enumeration eMonitor = cMonitorIdList.elements();
		while (eMonitor.hasMoreElements()) {
			String cId = (String)eMonitor.nextElement();
			cbMonitorC.addItem(cId);
			String cName = getChannelNameById(cId, MONITOR_CHANNEL_TABLE_NAME);
			monitorTooltips.add(cName);
		}
		cbHomeC.setRenderer(homeRenderer);
		cbMonitorC.setRenderer(monitorRenderer);
		homeRenderer.setTooltips(homeTooltips);
		monitorRenderer.setTooltips(monitorTooltips);
	}

	private void inititalize() {
		setTitle("Add new mapping channel");
		setBounds(100, 100, 486, 324);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 455, 228);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Home Channel ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(11, 16, 120, 25);
		panel.add(lblNewLabel);

		JLabel lblMoonitorChannelId = new JLabel("Monitor Channel ID");
		lblMoonitorChannelId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoonitorChannelId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblMoonitorChannelId.setBounds(10, 69, 121, 25);
		panel.add(lblMoonitorChannelId);

		JLabel lblTimeSync = new JLabel("Time Sync (s)");
		lblTimeSync.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeSync.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTimeSync.setBounds(10, 136, 121, 25);
		panel.add(lblTimeSync);

		txtTimeSync = new JTextField();
		txtTimeSync.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtTimeSync.setText("100");
		txtTimeSync.setColumns(10);
		txtTimeSync.setBounds(148, 136, 297, 30);
		panel.add(txtTimeSync);

		JLabel lblStatusSync = new JLabel("Status Sync");
		lblStatusSync.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusSync.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblStatusSync.setBounds(10, 184, 121, 25);
		panel.add(lblStatusSync);

		JComboBox cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Stop", "Run"}));
		cbStatus.setSelectedIndex(0);
		cbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbStatus.setBounds(150, 187, 295, 30);
		panel.add(cbStatus);

		cbHomeC = new JComboBox();
		cbHomeC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cId = (String)cbHomeC.getSelectedItem();
				String cName = getChannelNameById(cId, HOME_CHANNEL_TABLE_NAME);
				lbHome.setText(cName);
			}
		});
		cbHomeC.setEditable(true);
		cbHomeC.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbHomeC.setBounds(148, 13, 297, 30);
		panel.add(cbHomeC);

		cbMonitorC = new JComboBox();
		cbMonitorC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cId = (String)cbMonitorC.getSelectedItem();
				String cName = getChannelNameById(cId, MONITOR_CHANNEL_TABLE_NAME);
				lbMonitor.setText(cName);
			}
		});
		cbMonitorC.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbMonitorC.setEditable(true);
		cbMonitorC.setBounds(148, 72, 297, 30);
		panel.add(cbMonitorC);

		lbHome = new JLabel("home name");
		lbHome.setForeground(SystemColor.textHighlight);
		lbHome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbHome.setBounds(148, 47, 297, 14);
		panel.add(lbHome);

		lbMonitor = new JLabel("monitor name");
		lbMonitor.setForeground(SystemColor.textHighlight);
		lbMonitor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbMonitor.setBounds(148, 113, 297, 14);
		panel.add(lbMonitor);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cHomeId = (String)cbHomeC.getSelectedItem();
				String cMonitorId = (String)cbMonitorC.getSelectedItem();
				System.out.println("Home channel : " + cHomeId);
				System.out.println("Monitor channel : " + cMonitorId);
				String timeSync = txtTimeSync.getText().trim();
				if(cHomeId == null || cHomeId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Home Channel ID field can not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(cMonitorId == null || cMonitorId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Monitor Channel ID field can not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(timeSync == null || timeSync.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Time Interval Sync field can not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					//check home channel is exist
					boolean isExist = checkChannelExist(cHomeId, HOME_CHANNEL_TABLE_NAME);
					if(!isExist){
						JOptionPane.showMessageDialog(contentPanel, "Home Channel ID is not exist",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//check monitor channel is exist
					isExist = checkChannelExist(cMonitorId, MONITOR_CHANNEL_TABLE_NAME);
					if(!isExist){
						JOptionPane.showMessageDialog(contentPanel, "Monitor Channel ID is not exist",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//check mapped is existed
					boolean isMapped = checkMappingExisted(cHomeId, cMonitorId);
					if(isMapped){
						JOptionPane.showMessageDialog(contentPanel, "This mapped item is existed. Please change to another channel ID!");
						return;
					}
					//insert to database
					PreparedStatement preparedStm = null;
					String query = "INSERT INTO home_monitor_channel_mapping (HomeChannelId, MonitorChannelId,"
							+ " TimeIntervalSync, StatusSync, Action) VALUES (?,?,?,?,?)";
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setString(1, cHomeId);
						preparedStm.setString(2, cMonitorId);
						preparedStm.setInt(3, Integer.parseInt(txtTimeSync.getText().trim()));
						preparedStm.setInt(4,cbStatus.getSelectedIndex());
						preparedStm.setInt(5,1 - cbStatus.getSelectedIndex());
						// execute insert SQL statement
						preparedStm.executeUpdate();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(contentPanel, "Add new mapping channel false " + ex.getMessage(), 
								"Error", JOptionPane.ERROR);
						return;
					}
					//create folder if it does not exist
					new Util().createFolder(cHomeId + "-" + cMonitorId);

					dispose();
				}
			}
		});
		btnOK.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOK.setBounds(232, 244, 100, 30);
		contentPanel.add(btnOK);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(360, 244, 100, 30);
		contentPanel.add(btnExit);
	}

	private boolean checkChannelExist(String cId, String tbName) {
		boolean isExist = false;
		Statement stmt;
		String query = "SELECT COUNT(*) FROM " + tbName + " WHERE ChannelId = '" + cId + "';";
		try {
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				int count = rs.getInt(1);
				if(count > 0){
					isExist = true;
				}
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		return isExist;
	}

	private Vector<String> getChannelIdList(String tbName) {
		Vector<String> vector = new Vector<String>();
		String query = "SELECT ChannelId FROM " + tbName + ";";
		Statement stmt;
		try{
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				vector.add((String)rs.getObject(1));
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		return vector;
	}
	private String getChannelNameById(String cId , String tbName) {
		String cName = "";
		String query = "SELECT ChannelName FROM " + tbName + " WHERE ChannelId = '"+ cId +"';";
		Statement stmt;
		try{
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				cName = (String)rs.getObject(1);
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		return cName;
	}
	
	private boolean checkMappingExisted(String cHomeId, String cMoniotorId) {
		boolean result = false;
		String query = "SELECT COUNT(*) FROM home_monitor_channel_mapping WHERE HomeChannelId = '" 
						+ cHomeId + "' AND MonitorChannelId = '"+ cMoniotorId +"';";
		Statement stmt;
		try{
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				long nCount = (long)rs.getObject(1);
				if(nCount == 1){
					result = true;
				}
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		return result;
	}
}
