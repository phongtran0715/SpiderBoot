package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import spiderboot.databaseconnection.MySqlAccess;

public class AddMonitorChannel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtChannelId;
	private JTextField txtChannelName;

	/**
	 * Create the dialog.
	 */
	public AddMonitorChannel() {
		setTitle("Add new monitor channel");
		setBounds(100, 100, 465, 186);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 441, 95);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Channel ID");
		lblNewLabel.setBounds(10, 16, 103, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtChannelId = new JTextField();
		txtChannelId.setBounds(130, 11, 300, 30);
		panel.add(txtChannelId);
		txtChannelId.setColumns(10);

		JLabel lblChannelName = new JLabel("Channel Name");
		lblChannelName.setBounds(10, 56, 103, 25);
		panel.add(lblChannelName);
		lblChannelName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChannelName.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtChannelName = new JTextField();
		txtChannelName.setBounds(130, 56, 300, 30);
		panel.add(txtChannelName);
		txtChannelName.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(200, 105, 118, 38);
		contentPanel.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cId = txtChannelId.getText().trim();
				if(cId.equals(null) || cId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Channel ID field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					//check channel is existed
					boolean isExisted = checkChannelExisted(cId);
					if(isExisted){
						JOptionPane.showMessageDialog(contentPanel, "This channel have already existed. Please add another channel ID");
						return;
					}
					//insert to database
					PreparedStatement preparedStm = null;
					String query = "INSERT INTO monitor_channel_list (ChannelId, ChannelName)"
							+ " VALUES (?,?)";
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setString(1, txtChannelId.getText().trim());
						preparedStm.setString(2, txtChannelName.getText().trim());
						// execute insert SQL statement
						preparedStm.executeUpdate();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(contentPanel, "Insert record false: " + ex.getMessage(), 
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					dispose();
				}
			}
		});
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(328, 105, 118, 38);
		contentPanel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	}
	
	private boolean checkChannelExisted(String cId) {
		boolean result = false;
		String query = "SELECT COUNT(*) FROM monitor_channel_list WHERE ChannelId = '" + cId + "';";
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
