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

public class ModifyGoogleAccount extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtClientId;
	private JTextField txtClientSecret;
	private JTextField txtApiKey;
	private JTextField txtAppName;
	
	//Account information
	int id;
	String userName;
	String api;
	String clientId;
	String clientSecret;
	String accountType;
	
	public ModifyGoogleAccount(int id, String userName, String api, 
			String clientId, String clientSecret, String accountType, String appName){
		initialize();
		this.id = id;
		txtApiKey.setText(api);
		txtAppName.setText(appName);
		txtClientId.setText(clientId);
		txtClientSecret.setText(clientSecret);
		txtEmail.setText(userName);
	}
	
	public ModifyGoogleAccount(){
		//default construction
		initialize();
	}
	/**
	 * Create the frame.
	 */
	private void initialize() {
		setTitle("Modify Google App Account");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModifyGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_32x32/edit_32x32.png")));
		setBounds(100, 100, 477, 346);
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
		panel.setBounds(5, 5, 459, 248);
		contentPane.add(panel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblEmail.setBounds(5, 10, 75, 35);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(95, 10, 350, 30);
		panel.add(txtEmail);
		
		JLabel label_1 = new JLabel("Client ID");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_1.setBounds(5, 48, 75, 35);
		panel.add(label_1);
		
		txtClientId = new JTextField();
		txtClientId.setColumns(10);
		txtClientId.setBounds(95, 48, 350, 30);
		panel.add(txtClientId);
		
		JLabel label_2 = new JLabel("Client Secret");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_2.setBounds(5, 98, 75, 35);
		panel.add(label_2);
		
		txtClientSecret = new JTextField();
		txtClientSecret.setColumns(10);
		txtClientSecret.setBounds(95, 98, 350, 30);
		panel.add(txtClientSecret);
		
		JLabel label_3 = new JLabel("API Key");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_3.setBounds(5, 148, 75, 35);
		panel.add(label_3);
		
		txtApiKey = new JTextField();
		txtApiKey.setColumns(10);
		txtApiKey.setBounds(95, 148, 350, 30);
		panel.add(txtApiKey);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblAccount.setBounds(5, 198, 75, 35);
		panel.add(lblAccount);
		
		JComboBox cbAccType = new JComboBox();
		cbAccType.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbAccType.setModel(new DefaultComboBoxModel(new String[] {"gmail"}));
		cbAccType.setBounds(90, 198, 95, 30);
		panel.add(cbAccType);
		
		JLabel label_4 = new JLabel("App Name");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_4.setBounds(195, 198, 75, 35);
		panel.add(label_4);
		
		txtAppName = new JTextField();
		txtAppName.setColumns(10);
		txtAppName.setBounds(280, 201, 165, 30);
		panel.add(txtAppName);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = txtEmail.getText().trim();
				if(email.equals(null) || email.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Email field can not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					//update to database
					PreparedStatement preparedStm = null;
					String query = "UPDATE google_account SET UserName = ?, Api = ? , "
							+ "ClientID = ?, ClientSecret= ?, AccountType= ?, AppName = ? WHERE Id = ? " ;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						// execute insert SQL statement
						preparedStm.setString(1, txtEmail.getText().trim());
						preparedStm.setString(2, txtApiKey.getText().trim());
						preparedStm.setString(3, txtClientId.getText().trim());
						preparedStm.setString(4, txtClientSecret.getText().trim());
						preparedStm.setString(5, cbAccType.getSelectedItem().toString());
						preparedStm.setString(6, txtAppName.getText().trim());
						preparedStm.setInt(7, id);
						//preparedStm.executeUpdate();
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
		btnOk.setIcon(new ImageIcon(ModifyGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOk.setBounds(205, 265, 118, 38);
		contentPane.add(btnOk);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(ModifyGoogleAccount.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(346, 265, 118, 38);
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
