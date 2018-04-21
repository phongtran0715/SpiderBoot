package spiderboot.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import spiderboot.configuration.ConfigProperties;
import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.helper.ImagePanel;

public class LoginForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	static LoginForm frame;

	public LoginForm() {
		initialize();
	}

	private void initialize(){
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/spiderboot/resources/resource/icon_32x32/user_32x32.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel = new ImagePanel(new ImageIcon(getClass().getClassLoader().getResource("banner1.png")).getImage());
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 528, 108);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Account Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(181, 115, 352, 165);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 26, 73, 30);
		panel_1.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPassword.setBounds(10, 80, 73, 30);
		panel_1.add(lblPassword);

		txtUserName = new JTextField();
		txtUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					//check account information
					String userName = txtUserName.getText().trim();
					String password = new String(txtPassword.getPassword());
					if(userName.equals("")){
						JOptionPane.showMessageDialog(contentPane, "User name must not empty.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}else{
						Statement stm = null;
						String query = "SELECT COUNT(*) FROM spider_account WHERE UserName = '" 
								+ userName + "' AND Password = '" + password +"';";
						try{
							stm = MySqlAccess.getInstance().connect.createStatement();
							ResultSet rs = stm.executeQuery(query);
							while(rs.next()){
								int numRow = rs.getInt(1);
								if(numRow == 0){
									JOptionPane.showMessageDialog(contentPane, "User or Password is incorrect!", 
											"Login false", JOptionPane.ERROR_MESSAGE);
									txtUserName.setText("");
									txtPassword.setText("");
									return;
								}else{
									break;
								}
							}

						}catch(SQLException ex){
							System.out.println(ex.toString());
						}
						HomeForm window = new HomeForm();
						window.setVisible(true);
						setVisible(false);
					}
				}
			}
		});
		txtUserName.setBounds(93, 25, 250, 30);
		panel_1.add(txtUserName);
		txtUserName.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					//check account information
					String userName = txtUserName.getText().trim();
					String password = new String(txtPassword.getPassword());
					if(userName.equals("")){
						JOptionPane.showMessageDialog(contentPane, "User name must not empty.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}else{
						Statement stm = null;
						String query = "SELECT COUNT(*) FROM spider_account WHERE UserName = '" 
								+ userName + "' AND Password = '" + password +"';";
						try{
							stm = MySqlAccess.getInstance().connect.createStatement();
							ResultSet rs = stm.executeQuery(query);
							while(rs.next()){
								int numRow = rs.getInt(1);
								if(numRow == 0){
									JOptionPane.showMessageDialog(contentPane, "User or Password is incorrect!", 
											"Login false", JOptionPane.ERROR_MESSAGE);
									txtUserName.setText("");
									txtPassword.setText("");
									return;
								}else{
									break;
								}
							}

						}catch(SQLException ex){
							System.out.println(ex.toString());
						}
						HomeForm window = new HomeForm();
						window.setVisible(true);
						setVisible(false);
					}
				}
			}
		});
		txtPassword.setBounds(93, 80, 250, 30);
		panel_1.add(txtPassword);

		JCheckBox cbRemember = new JCheckBox("Remember me");
		cbRemember.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbRemember.setBounds(93, 130, 147, 23);
		panel_1.add(cbRemember);

		JPanel panel_2 = new ImagePanel(new ImageIcon(getClass().getClassLoader().getResource("logo1.png")).getImage());
		panel_2.setBackground(SystemColor.info);
		panel_2.setBorder(null);
		panel_2.setBounds(5, 115, 175, 165);
		contentPane.add(panel_2);

		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setIcon(new ImageIcon(LoginForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(413, 285, 118, 38);
		contentPane.add(button);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//check account information
				String userName = txtUserName.getText().trim();
				String password = new String(txtPassword.getPassword());
				if(userName.equals("")){
					JOptionPane.showMessageDialog(contentPane, "User name must not empty.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					Statement stm = null;
					String query = "SELECT COUNT(*) FROM spider_account WHERE UserName = '" 
							+ userName + "' AND Password = '" + password +"';";
					try{
						stm = MySqlAccess.getInstance().connect.createStatement();
						ResultSet rs = stm.executeQuery(query);
						while(rs.next()){
							int numRow = rs.getInt(1);
							if(numRow == 0){
								JOptionPane.showMessageDialog(contentPane, "User or Password is incorrect!", 
										"Login false", JOptionPane.ERROR_MESSAGE);
								txtUserName.setText("");
								txtPassword.setText("");
								return;
							}else{
								break;
							}
						}

					}catch(SQLException ex){
						System.out.println(ex.toString());
					}
					//save configuration file
					if(cbRemember.isSelected()){
						ConfigProperties.getInstance().writeProperties("RememberMe", "Yes");
						ConfigProperties.getInstance().writeProperties("LastUserName", txtUserName.getText().trim());
					}else{
						ConfigProperties.getInstance().writeProperties("RememberMe", "No");
					}

					HomeForm window = new HomeForm();
					window.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnOk.setIcon(new ImageIcon(LoginForm.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnOk.setBounds(274, 285, 118, 38);
		contentPane.add(btnOk);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				String rememberMe = ConfigProperties.getInstance().getProperties("RememberMe", null);
				String lastUserName =  ConfigProperties.getInstance().getProperties("LastUserName", null);
				if(rememberMe == null){
					return;
				}
				if(rememberMe.equals("Yes")){
					cbRemember.setSelected(true);
					txtUserName.setText(lastUserName);
				}else{
					cbRemember.setSelected(false);
				}
			}
		});
	}
}
