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

public class AddGoogleAccount extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtClientId;
	private JTextField txtClientSecret;
	private JTextField txtApiKey;
	private JTextField txtAppName;
	/**
	 * Create the frame.
	 */
	public AddGoogleAccount() {
		setTitle("Add new Google App Account");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_32x32/add_32x32.png")));
		setBounds(100, 100, 478, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, -18, 476, 283);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(12, 31, 75, 33);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(97, 29, 350, 30);
		txtEmail.setColumns(10);
		panel.add(txtEmail);
		
		JLabel label_1 = new JLabel("Client ID");
		label_1.setBounds(12, 76, 75, 35);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel.add(label_1);
		
		txtClientId = new JTextField();
		txtClientId.setBounds(97, 76, 350, 30);
		txtClientId.setColumns(10);
		panel.add(txtClientId);
		
		JLabel label_2 = new JLabel("Client Secret");
		label_2.setBounds(12, 126, 75, 35);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel.add(label_2);
		
		txtClientSecret = new JTextField();
		txtClientSecret.setBounds(97, 126, 350, 30);
		txtClientSecret.setColumns(10);
		panel.add(txtClientSecret);
		
		JLabel label_3 = new JLabel("API Key");
		label_3.setBounds(12, 176, 75, 35);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel.add(label_3);
		
		txtApiKey = new JTextField();
		txtApiKey.setBounds(97, 176, 350, 30);
		txtApiKey.setColumns(10);
		panel.add(txtApiKey);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setBounds(12, 222, 75, 35);
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel.add(lblAccount);
		
		JComboBox cbAccType = new JComboBox();
		cbAccType.setBounds(97, 222, 95, 30);
		cbAccType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbAccType.setModel(new DefaultComboBoxModel(new String[] {"gmail"}));
		panel.add(cbAccType);
		
		JLabel label_4 = new JLabel("App Name");
		label_4.setBounds(202, 222, 75, 35);
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel.add(label_4);
		
		txtAppName = new JTextField();
		txtAppName.setBounds(287, 225, 160, 30);
		txtAppName.setColumns(10);
		panel.add(txtAppName);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = txtEmail.getText().trim();
				if(email.equals(null) || email.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Email field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					//insert to database
					PreparedStatement preparedStm = null;
					String query = "INSERT INTO google_account (UserName, Api, ClientID, ClientSecret, AccountType, AppName) "
							+ " VALUES (?,?,?,?,?,?)";
					
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						// execute insert SQL statement
						preparedStm.setString(1, txtEmail.getText().trim());
						preparedStm.setString(2, txtApiKey.getText().trim());
						preparedStm.setString(3, txtClientId.getText().trim());
						preparedStm.setString(4, txtClientSecret.getText().trim());
						preparedStm.setString(5, cbAccType.getSelectedItem().toString());
						preparedStm.setString(6, txtAppName.getText().trim());
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
		btnOk.setIcon(new ImageIcon(AddGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOk.setBounds(200, 277, 118, 38);
		contentPane.add(btnOk);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(AddGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(341, 277, 118, 38);
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
