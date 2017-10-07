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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import spiderboot.databaseconnection.MySqlAccess;

public class ModifyMonitorChannel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtChannelId;
	private JTextField txtChannelName;
	int id;

	/**
	 * Create the dialog.
	 */
	public ModifyMonitorChannel() {
		initialiaze();
	}
	
	public ModifyMonitorChannel(int id, String channelId, String channelName) {
		initialiaze();
		this.id = id;
		txtChannelId.setText(channelId);
		txtChannelName.setText(channelName);
	}

	private void initialiaze() {
		setTitle("Modify monitor channel");
		setBounds(100, 100, 466, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 442, 115);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Channel ID");
		lblNewLabel.setBounds(10, 16, 103, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtChannelId = new JTextField();
		txtChannelId.setBounds(130, 11, 300, 25);
		panel.add(txtChannelId);
		txtChannelId.setColumns(10);

		JLabel lblChannelName = new JLabel("Channel Name");
		lblChannelName.setBounds(10, 56, 103, 25);
		panel.add(lblChannelName);
		lblChannelName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChannelName.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtChannelName = new JTextField();
		txtChannelName.setBounds(130, 56, 300, 25);
		panel.add(txtChannelName);
		txtChannelName.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(201, 131, 118, 38);
		contentPanel.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cId = txtChannelId.getText().trim();
				if(cId.equals(null) || cId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Channel Id field can not be empty!", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					//update to database
					PreparedStatement preparedStm = null;
					String query = "UPDATE monitor_channel_list SET ChannelId = ?, ChannelName = ? WHERE Id = ? " ;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						// execute insert SQL statement
						preparedStm.setString(1, txtChannelId.getText().trim());
						preparedStm.setString(2, txtChannelName.getText().trim());
						preparedStm.setInt(3, id);
						//preparedStm.executeUpdate();
						preparedStm.executeUpdate();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(contentPanel, "Modify monitor channel false: " + ex.getMessage(), 
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					dispose();
				}
			}
		});
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(329, 131, 118, 38);
		contentPanel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	}
}
