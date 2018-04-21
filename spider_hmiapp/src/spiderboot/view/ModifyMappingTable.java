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
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

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
import javax.swing.ImageIcon;

public class ModifyMappingTable extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JComboBox cbHome;
	JComboBox cbMonitor;
	List<String> homeTooltips = new ArrayList<String>();
	List<String> monitorTooltips = new ArrayList<String>();
	private JTextField txtTimeSync;
	int id;
	final int HOME_CHANNEL = 0;
	final int MONITOR_CHANNEL = 1;

	public ModifyMappingTable() {
		initialize();
	}

	public ModifyMappingTable(int id, String cHomeId, String cMonitorId, int timeSync) {
		initialize();
		this.id = id;
		//configuration for combo box
		ComboboxToolTipRenderer homeRenderer = new ComboboxToolTipRenderer();
		ComboboxToolTipRenderer monitorRenderer = new ComboboxToolTipRenderer();	
		Vector<String> cHomeIdList = getChannelIdList("home_channel_list");
		Vector<String> cMonitorIdList = getChannelIdList("monitor_channel_list");
		Enumeration eHome = cHomeIdList.elements();
		while (eHome.hasMoreElements()) {
			String cId = (String)eHome.nextElement();
			cbHome.addItem(cId);
			String cName = getChannelNameById(cId, "home_channel_list");
			homeTooltips.add(cName);
		}
		Enumeration eMonitor = cMonitorIdList.elements();
		while (eMonitor.hasMoreElements()) {
			String cId = (String)eMonitor.nextElement();
			cbMonitor.addItem(cId);
			String cName = getChannelNameById(cId, "monitor_channel_list");
			monitorTooltips.add(cName);
		}
		cbHome.setRenderer(homeRenderer);
		cbMonitor.setRenderer(monitorRenderer);
		homeRenderer.setTooltips(homeTooltips);
		monitorRenderer.setTooltips(monitorTooltips);

		cbHome.getEditor().setItem(cHomeId);
		cbMonitor.getEditor().setItem(cMonitorId);
		txtTimeSync.setText(Integer.toString(timeSync));
	}

	private void initialize() {
		setTitle("Modify mapping channel");
		setBounds(100, 100, 400, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 377, 133);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Home Channel ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(0, 11, 120, 25);
		panel.add(lblNewLabel);

		JLabel lblMoonitorChannelId = new JLabel("Monitor Channel ID");
		lblMoonitorChannelId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoonitorChannelId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblMoonitorChannelId.setBounds(0, 51, 121, 25);
		panel.add(lblMoonitorChannelId);

		JLabel lblTimeSync = new JLabel("Time Sync (s)");
		lblTimeSync.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeSync.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTimeSync.setBounds(0, 91, 121, 25);
		panel.add(lblTimeSync);

		txtTimeSync = new JTextField();
		txtTimeSync.setColumns(10);
		txtTimeSync.setBounds(138, 91, 230, 30);
		panel.add(txtTimeSync);

		cbHome = new JComboBox();
		cbHome.setEditable(true);
		cbHome.setBounds(138, 14, 230, 30);
		panel.add(cbHome);

		cbMonitor = new JComboBox();
		cbMonitor.setEditable(true);
		cbMonitor.setBounds(138, 54, 230, 30);
		panel.add(cbMonitor);

		JButton btnOK = new JButton("OK");
		btnOK.setIcon(new ImageIcon(ModifyMappingTable.class.getResource("/spiderboot/resources/resource/icon_24x24/checked_24x24.png")));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cHomeId = (String) cbHome.getSelectedItem();		
				String cMonitorId = (String)cbMonitor.getSelectedItem();		
				String timeSync = txtTimeSync.getText().trim();
				if(cHomeId.equals(null) || cHomeId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Home Channel ID field can not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}else if(cMonitorId.equals(null) || cMonitorId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Monitor Channel ID field can not be empty!", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}else if(timeSync.equals(null) || timeSync.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Time Interval Sync field can not be empty!", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}else{
					//check channel is valid
					boolean isCHomeValid = isChannelValid(cHomeId, HOME_CHANNEL);
					if(!isCHomeValid) {
						JOptionPane.showMessageDialog(contentPanel, "Home channel does exist in database. Please insert home channel first!", 
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					boolean isCMonitorValid = isChannelValid(cMonitorId, MONITOR_CHANNEL);
					if(!isCMonitorValid) {
						JOptionPane.showMessageDialog(contentPanel, "Monitor channel does exist in database. Please insert Monitor channel first!", 
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//insert to database
					PreparedStatement preparedStm = null;
					String query = "UPDATE home_monitor_channel_mapping SET HomeChannelId = ?, MonitorChannelId = ?, "
							+ "TimeIntervalSync = ?  WHERE Id = ? " ;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setString(1, (String)cbHome.getSelectedItem());
						preparedStm.setString(2, (String)cbMonitor.getSelectedItem());
						preparedStm.setInt(3, Integer.parseInt(txtTimeSync.getText().trim()));
						preparedStm.setInt(4, id);
						System.out.println(query);
						// execute insert SQL statement
						preparedStm.executeUpdate();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(contentPanel, "Modify mapping channel false " + ex.getMessage(), 
								"Error", JOptionPane.ERROR);
						return;
					}
					dispose();
				}
			}
		});
		btnOK.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOK.setBounds(146, 141, 100, 30);
		contentPanel.add(btnOK);

		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(ModifyMappingTable.class.getResource("/spiderboot/resources/resource/icon_24x24/delete_24x24.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(274, 141, 100, 30);
		contentPanel.add(btnExit);
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

	private boolean isChannelValid(String cId, int channelType) {
		boolean isValid = false;
		String tbName = "";
		if(channelType == HOME_CHANNEL) {
			tbName = "home_channel_list";
		}else if(channelType == MONITOR_CHANNEL) {
			tbName = "monitor_channel_list";
		}else {

		}
		String query = "SELECT COUNT(*) FROM " + tbName +" WHERE ChannelId = '" + cId + "';";
		Statement stmt;
		try{
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int count = rs.getInt(1);
				if(count == 0) {
					isValid = false;	
				}else {
					isValid = true;
				}
			}
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		return isValid;
	}
}
