package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import spiderboot.configuration.ConfigProperties;
import spiderboot.databaseconnection.MySqlAccess;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class DatabaseConfig extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtServerName;
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	public DatabaseConfig() {
		initialize();
	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatabaseConfig.class.getResource("/spiderboot/resources/resource/icon_32x32/settings_32x32.png")));
		setResizable(false);
		setFont(new Font("Dialog", Font.PLAIN, 13));
		setTitle("Database Configuration");
		setBounds(100, 100, 404, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 373, 165);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("DB Server");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 82, 14);
		panel.add(lblNewLabel);

		JLabel lblDbName = new JLabel("DB Name");
		lblDbName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDbName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDbName.setBounds(10, 51, 82, 14);
		panel.add(lblDbName);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblUserName.setBounds(10, 88, 82, 14);
		panel.add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPassword.setBounds(10, 125, 82, 14);
		panel.add(lblPassword);

		JComboBox cbDbSever = new JComboBox();
		cbDbSever.setModel(new DefaultComboBoxModel(new String[] {"localhost:3306"}));
		cbDbSever.setEditable(true);
		cbDbSever.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbDbSever.setBounds(121, 9, 227, 30);
		panel.add(cbDbSever);

		txtServerName = new JTextField();
		txtServerName.setText("spiderboot");
		txtServerName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtServerName.setBounds(121, 49, 227, 30);
		panel.add(txtServerName);
		txtServerName.setColumns(10);

		txtUserName = new JTextField();
		txtUserName.setText("root");
		txtUserName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtUserName.setColumns(10);
		txtUserName.setBounds(121, 86, 227, 30);
		panel.add(txtUserName);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPassword.setBounds(121, 123, 227, 30);
		panel.add(txtPassword);

		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(DatabaseConfig.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(260, 172, 118, 38);
		contentPanel.add(btnExit);

		JButton btnOK = new JButton("OK");
		btnOK.setIcon(new ImageIcon(DatabaseConfig.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dbServer = (String)cbDbSever.getSelectedItem();
				if(dbServer != null && dbServer.isEmpty()){
					JOptionPane.showMessageDialog(contentPanel, "Database server name must not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String dbName = txtServerName.getText().trim();
				if(dbName != null && dbName.isEmpty()){
					JOptionPane.showMessageDialog(contentPanel, "Database name must not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String userName = txtUserName.getText().trim();
				String password = new String(txtPassword.getPassword());
				int errCode = MySqlAccess.getInstance().DBConnect(dbServer.trim(), dbName, userName, password);
				if(errCode == 0){
					//save information to config file
					ConfigProperties.getInstance().writeProperties("DbServer", dbServer);
					ConfigProperties.getInstance().writeProperties("DbName", dbName);
					ConfigProperties.getInstance().writeProperties("DbUserName", userName);
					ConfigProperties.getInstance().writeProperties("DbPassword", password);
					LoginForm loginFrm = new LoginForm();
					setVisible(false);
					loginFrm.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(contentPanel, "Error code =  " + Integer.toString(errCode) + " : "
							+ "Can not connect to database server. "
							+ "\n Please check configuration parameter!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOK.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOK.setBounds(132, 172, 118, 38);
		contentPanel.add(btnOK);
	}
}
