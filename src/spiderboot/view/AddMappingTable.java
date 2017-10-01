package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import spiderboot.databaseconnection.MySqlAccess;

public class AddMappingTable extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtHomeChannelID;
	private JTextField txtMonitorChannelID;
	private JTextField txtTimeSync;

	/**
	 * Create the dialog.
	 */
	public AddMappingTable() {
		setTitle("Add new mapping channel");
		setBounds(100, 100, 340, 249);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 313, 159);
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

		JLabel lblTimeSync = new JLabel("Time Sync (s)");
		lblTimeSync.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeSync.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblTimeSync.setBounds(10, 90, 121, 25);
		panel.add(lblTimeSync);

		txtHomeChannelID = new JTextField();
		txtHomeChannelID.setBounds(150, 10, 150, 25);
		panel.add(txtHomeChannelID);
		txtHomeChannelID.setColumns(10);

		txtMonitorChannelID = new JTextField();
		txtMonitorChannelID.setColumns(10);
		txtMonitorChannelID.setBounds(148, 50, 150, 25);
		panel.add(txtMonitorChannelID);

		txtTimeSync = new JTextField();
		txtTimeSync.setText("100");
		txtTimeSync.setColumns(10);
		txtTimeSync.setBounds(148, 90, 150, 25);
		panel.add(txtTimeSync);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cHomeId = txtHomeChannelID.getText().trim();
				String cMonitorId = txtMonitorChannelID.getText().trim();
				String timeSync = txtTimeSync.getText().trim();
				if(cHomeId.equals(null) || cHomeId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Home Channel ID field can not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(cMonitorId.equals(null) || cMonitorId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Monitor Channel ID field can not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(timeSync.equals(null) || timeSync.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Time Interval Sync field can not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					//insert to database
					PreparedStatement preparedStm = null;
					String query = "INSERT INTO home_monitor_channel_mapping (HomeChannelId, MonitorChannelId,"
							+ " TimeIntervalSync) VALUES (?,?,?)";
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setString(1, txtHomeChannelID.getText().trim());
						preparedStm.setString(2, txtMonitorChannelID.getText().trim());
						preparedStm.setInt(3, Integer.parseInt(txtTimeSync.getText().trim()));
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
					dispose();
				}
			}
		});
		btnOK.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOK.setBounds(90, 175, 100, 30);
		contentPanel.add(btnOK);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(218, 175, 100, 30);
		contentPanel.add(btnExit);
	}
}
