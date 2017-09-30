package spiderboot.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import spiderboot.databaseconnection.MySqlAccess;
import javax.swing.JPasswordField;

public class AddNewGoogleAccount extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtClientId;
	private JTextField txtClientSecret;
	private JTextField txtApiKey;
	private JTextField txtAppName;
	private JPasswordField txtPass;
	private JPasswordField txtConfirmPass;
	/**
	 * Create the frame.
	 */
	public AddNewGoogleAccount() {
		setTitle("Add new Google App Account");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddNewGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_32x32/add_32x32.png")));
		setBounds(100, 100, 478, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 454, 368);
		contentPane.add(panel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmail.setBounds(5, 10, 75, 35);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(90, 10, 350, 30);
		panel.add(txtEmail);
		
		JLabel label_1 = new JLabel("Client ID");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_1.setBounds(5, 161, 75, 35);
		panel.add(label_1);
		
		txtClientId = new JTextField();
		txtClientId.setColumns(10);
		txtClientId.setBounds(90, 161, 350, 30);
		panel.add(txtClientId);
		
		JLabel label_2 = new JLabel("Client Secret");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_2.setBounds(5, 211, 75, 35);
		panel.add(label_2);
		
		txtClientSecret = new JTextField();
		txtClientSecret.setColumns(10);
		txtClientSecret.setBounds(90, 211, 350, 30);
		panel.add(txtClientSecret);
		
		JLabel label_3 = new JLabel("API Key");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_3.setBounds(5, 261, 75, 35);
		panel.add(label_3);
		
		txtApiKey = new JTextField();
		txtApiKey.setColumns(10);
		txtApiKey.setBounds(90, 261, 350, 30);
		panel.add(txtApiKey);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblAccount.setBounds(5, 307, 75, 35);
		panel.add(lblAccount);
		
		JComboBox cbAccType = new JComboBox();
		cbAccType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbAccType.setModel(new DefaultComboBoxModel(new String[] {"gmail"}));
		cbAccType.setBounds(90, 307, 95, 30);
		panel.add(cbAccType);
		
		JLabel label_4 = new JLabel("App Name");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_4.setBounds(195, 307, 75, 35);
		panel.add(label_4);
		
		txtAppName = new JTextField();
		txtAppName.setColumns(10);
		txtAppName.setBounds(280, 310, 160, 30);
		panel.add(txtAppName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPassword.setBounds(5, 60, 75, 35);
		panel.add(lblPassword);
		
		JLabel lblConfirmPass = new JLabel("Confirm Pass");
		lblConfirmPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblConfirmPass.setBounds(-6, 106, 86, 35);
		panel.add(lblConfirmPass);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(90, 63, 350, 30);
		panel.add(txtPass);
		
		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setBounds(90, 106, 350, 30);
		panel.add(txtConfirmPass);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = txtEmail.getText().trim();
				String password = new String(txtPass.getPassword());
				String passwordConfirm = new String(txtConfirmPass.getPassword());
				if(email.equals(null) || email.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Email field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(!password.equals(passwordConfirm)){
					JOptionPane.showMessageDialog(contentPane, "Password and Confirm password do not match!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					//insert to database
					PreparedStatement preparedStm = null;
					String query = "INSERT INTO googleaccount (	UserName, Password, Api, ClientID, ClientSecret, AccountType, AppName) "
							+ " VALUES (?,?,?,?,?,?,?)";
					
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						// execute insert SQL statement
						preparedStm.setString(1, txtEmail.getText().trim());
						preparedStm.setString(2, password);
						preparedStm.setString(3, txtApiKey.getText().trim());
						preparedStm.setString(4, txtClientId.getText().trim());
						preparedStm.setString(5, txtClientSecret.getText().trim());
						preparedStm.setString(6, cbAccType.getSelectedItem().toString());
						preparedStm.setString(7, txtAppName.getText().trim());
						preparedStm.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(contentPane, "Insert record false: " + e.toString());
						return;
					}
				
					JOptionPane.showMessageDialog(contentPane, "Insert record successfuly!");
					resetGUIData();
					dispose();
				}
			}
		});
		btnOk.setIcon(new ImageIcon(AddNewGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOk.setBounds(200, 394, 118, 38);
		contentPane.add(btnOk);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(AddNewGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(341, 394, 118, 38);
		contentPane.add(btnExit);
	}

	void resetGUIData(){
		txtEmail.setText("");
		txtApiKey.setText("");
		txtAppName.setText("");
		txtClientId.setText("");
		txtClientSecret.setText("");
	}
}
