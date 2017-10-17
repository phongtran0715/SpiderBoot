package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import spiderboot.databaseconnection.MySqlAccess;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ModifyMappingTable extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtHomeChannelID;
	private JTextField txtMonitorChannelId;
	private JTextField txtTimeSync;
	int id;

	public ModifyMappingTable() {
		initialize();
	}

	public ModifyMappingTable(int id, String cHomeId, String cMonitorId, int timeSync) {
		initialize();
		this.id = id;
		txtHomeChannelID.setText(cHomeId);
		txtMonitorChannelId.setText(cMonitorId);
		txtTimeSync.setText(Integer.toString(timeSync));
	}
	
	private void initialize() {
		setTitle("Modify mapping channel");
		setBounds(100, 100, 353, 287);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 313, 200);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Home Channel ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 10, 120, 25);
		panel.add(lblNewLabel);

		JLabel lblMoonitorChannelId = new JLabel("Monitor Channel ID");
		lblMoonitorChannelId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoonitorChannelId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblMoonitorChannelId.setBounds(10, 50, 121, 25);
		panel.add(lblMoonitorChannelId);

		JLabel lblTimeSync = new JLabel("Time Sync");
		lblTimeSync.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeSync.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTimeSync.setBounds(10, 90, 121, 25);
		panel.add(lblTimeSync);

		txtHomeChannelID = new JTextField();
		txtHomeChannelID.setBounds(150, 10, 150, 25);
		panel.add(txtHomeChannelID);
		txtHomeChannelID.setColumns(10);

		txtMonitorChannelId = new JTextField();
		txtMonitorChannelId.setColumns(10);
		txtMonitorChannelId.setBounds(148, 50, 150, 25);
		panel.add(txtMonitorChannelId);

		txtTimeSync = new JTextField();
		txtTimeSync.setColumns(10);
		txtTimeSync.setBounds(148, 90, 150, 25);
		panel.add(txtTimeSync);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cHomeId = txtHomeChannelID.getText().trim();
				String cMonitorId = txtMonitorChannelId.getText().trim();
				String timeSync = txtTimeSync.getText().trim();
				if(cHomeId.equals(null) || cHomeId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Home Channel ID field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(cMonitorId.equals(null) || cMonitorId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Monitor Channel ID field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(timeSync.equals(null) || timeSync.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Time Interval Sync field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					//insert to database
					PreparedStatement preparedStm = null;
					String query = "UPDATE home_monitor_channel_mapping SET HomeChannelId = ?, MonitorChannelId = ?, "
							+ "TimeIntervalSync = ?  WHERE Id = ? " ;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setString(1, txtHomeChannelID.getText().trim());
						preparedStm.setString(2, txtMonitorChannelId.getText().trim());
						preparedStm.setInt(3, Integer.parseInt(txtTimeSync.getText().trim()));
						preparedStm.setInt(4, id);
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
		btnOK.setBounds(99, 216, 100, 30);
		contentPanel.add(btnOK);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(227, 216, 100, 30);
		contentPanel.add(btnExit);
	}
}
