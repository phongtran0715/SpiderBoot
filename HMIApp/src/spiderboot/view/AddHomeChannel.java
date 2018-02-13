package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import spiderboot.databaseconnection.MySqlAccess;
import javax.swing.ImageIcon;

public class AddHomeChannel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtChannelId;
	private JTextField txtChannelName;
	private JTextField txtGoogleAcc;
	private JTextField txtVideoIntro;
	private JTextField txtVideoOutro;
	private JTextField txtLogo;
	private JTextField txtTitleTemp;
	private JTextField txtDescTemp;

	/**
	 * Create the dialog.
	 */
	public AddHomeChannel() {
		setTitle("Add new home channel");
		setBounds(100, 100, 582, 514);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 561, 412);
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

		JLabel lblGoogleAccount = new JLabel("Google Account");
		lblGoogleAccount.setBounds(10, 96, 103, 28);
		panel.add(lblGoogleAccount);
		lblGoogleAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGoogleAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtGoogleAcc = new JTextField();
		txtGoogleAcc.setBounds(130, 96, 300, 25);
		panel.add(txtGoogleAcc);
		txtGoogleAcc.setColumns(10);

		JLabel lblVideoIntro = new JLabel("Video Intro");
		lblVideoIntro.setBounds(10, 136, 103, 29);
		panel.add(lblVideoIntro);
		lblVideoIntro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVideoIntro.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtVideoIntro = new JTextField();
		txtVideoIntro.setBounds(130, 136, 300, 25);
		panel.add(txtVideoIntro);
		txtVideoIntro.setColumns(10);

		JLabel lblVideoOutro = new JLabel("Video Outro");
		lblVideoOutro.setBounds(10, 176, 103, 25);
		panel.add(lblVideoOutro);
		lblVideoOutro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVideoOutro.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtVideoOutro = new JTextField();
		txtVideoOutro.setBounds(130, 176, 300, 25);
		panel.add(txtVideoOutro);
		txtVideoOutro.setColumns(10);

		JButton btnBrowseVideoIntro = new JButton("Browse ...");
		btnBrowseVideoIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(panel);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					txtVideoIntro.setText(file.getPath());
				}
			}
		});
		btnBrowseVideoIntro.setBounds(452, 176, 89, 25);
		panel.add(btnBrowseVideoIntro);
		btnBrowseVideoIntro.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JLabel lblLogo = new JLabel("Logo Channel");
		lblLogo.setBounds(10, 216, 103, 27);
		panel.add(lblLogo);
		lblLogo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtLogo = new JTextField();
		txtLogo.setBounds(130, 216, 300, 25);
		panel.add(txtLogo);
		txtLogo.setColumns(10);

		JButton btnBrowseLogo = new JButton("Browse ...");
		btnBrowseLogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(panel);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					txtLogo.setText(file.getPath());
				}
			}
		});
		btnBrowseLogo.setBounds(452, 219, 89, 25);
		panel.add(btnBrowseLogo);
		btnBrowseLogo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JLabel lblTitleTemplate = new JLabel("Title Template");
		lblTitleTemplate.setBounds(10, 279, 103, 18);
		panel.add(lblTitleTemplate);
		lblTitleTemplate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitleTemplate.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtTitleTemp = new JTextField();
		txtTitleTemp.setBounds(130, 256, 300, 66);
		panel.add(txtTitleTemp);
		txtTitleTemp.setColumns(10);

		JLabel lblDescriptionTemplate = new JLabel("Desc Template");
		lblDescriptionTemplate.setBounds(27, 359, 86, 18);
		panel.add(lblDescriptionTemplate);
		lblDescriptionTemplate.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		txtDescTemp = new JTextField();
		txtDescTemp.setBounds(130, 336, 300, 66);
		panel.add(txtDescTemp);
		txtDescTemp.setColumns(10);

		JButton btnBrowseDescTemp = new JButton("Browse ...");
		btnBrowseDescTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(panel);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					txtDescTemp.setText(file.getPath());
				}
			}
		});
		btnBrowseDescTemp.setBounds(452, 358, 89, 25);
		panel.add(btnBrowseDescTemp);
		btnBrowseDescTemp.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JButton btnBrowseTitleTemp = new JButton("Browse ...");
		btnBrowseTitleTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(panel);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					//TODO: process tile template
					//File file = fc.getSelectedFile();
				}
			}
		});
		btnBrowseTitleTemp.setBounds(452, 278, 89, 25);
		panel.add(btnBrowseTitleTemp);
		btnBrowseTitleTemp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		JButton btnIntro = new JButton("Browse ...");
		btnIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(panel);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					txtVideoIntro.setText(file.getPath());
				}
			}
		});
		btnIntro.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnIntro.setBounds(452, 138, 89, 25);
		panel.add(btnIntro);

		JButton btnOk = new JButton("OK");
		btnOk.setIcon(new ImageIcon(AddHomeChannel.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		btnOk.setBounds(310, 428, 118, 38);
		contentPanel.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cId = txtChannelId.getText().trim();
				if(cId.equals(null) || cId.equals("")){
					JOptionPane.showMessageDialog(contentPanel, "Channel ID field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					//Check Channel is existed
					
					boolean isExisted = checkChannelExisted(cId);
					if(isExisted){
						JOptionPane.showMessageDialog(contentPanel, "This channel have already existed. Please add another channel ID");
						return;
					}
					//insert to database
					PreparedStatement preparedStm = null;
					String query = "INSERT INTO home_channel_list (ChannelId, ChannelName, GoogleAccount,"
							+ "VideoIntro, VideoOutro, Logo, DescriptionTemplate, TitleTemplate) "
							+ " VALUES (?,?,?,?,?,?,?,?)";
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setString(1, txtChannelId.getText().trim());
						preparedStm.setString(2, txtChannelName.getText().trim());
						preparedStm.setString(3, txtGoogleAcc.getText().trim());
						preparedStm.setString(4, txtVideoIntro.getText().trim());
						preparedStm.setString(5, txtVideoOutro.getText().trim());
						preparedStm.setString(6, txtLogo.getText().trim());
						preparedStm.setString(7, txtDescTemp.getText().trim());
						preparedStm.setString(8, txtTitleTemp.getText().trim());
						// execute insert SQL statement
						preparedStm.executeUpdate();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(contentPanel, "Add new home channel false " + ex.getMessage(), 
								"Error", JOptionPane.ERROR);
						return;
					}
					dispose();
				}
			}
		});
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(AddHomeChannel.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.setBounds(438, 428, 118, 38);
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
		String query = "SELECT COUNT(*) FROM home_channel_list WHERE ChannelId = '" + cId + "';";
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
