package spiderboot.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import spiderboot.databaseconnection.MySqlAccess;

public class AddSpiderBootAccount extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPass;
	private JPasswordField txtConfirmPass;
	private JTextField txtUserName;
	/**
	 * Create the frame.
	 */
	public AddSpiderBootAccount() {
		setTitle("Add New Spider Boot Account");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddSpiderBootAccount.class.getResource("/spiderboot/resources/resource/icon_32x32/add_32x32.png")));
		setBounds(100, 100, 478, 310);
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
		panel.setBounds(5, 5, 454, 210);
		contentPane.add(panel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmail.setBounds(4, 155, 75, 35);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(89, 155, 350, 30);
		panel.add(txtEmail);
		
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
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblUserName.setBounds(4, 11, 75, 35);
		panel.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(89, 11, 350, 30);
		panel.add(txtUserName);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					String query = "INSERT INTO spider_account (UserName, Password, Email) VALUES (?,?,?)";
					
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						// execute insert SQL statement
						preparedStm.setString(1, txtUserName.getText().trim());
						preparedStm.setString(2, password);
						preparedStm.setString(3, txtEmail.getText().trim());
						preparedStm.executeUpdate();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(contentPane, "Insert record false: " + e.toString());
						return;
					}
				
					JOptionPane.showMessageDialog(contentPane, "Insert record successfuly!");
					resetGUIData();
					dispose();
				}
			}
		});
		
		btnOk.setIcon(new ImageIcon(AddSpiderBootAccount.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOk.setBounds(200, 226, 118, 38);
		contentPane.add(btnOk);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(AddSpiderBootAccount.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(341, 226, 118, 38);
		contentPane.add(btnExit);
	}

	void resetGUIData(){
		txtEmail.setText("");
	}
}
