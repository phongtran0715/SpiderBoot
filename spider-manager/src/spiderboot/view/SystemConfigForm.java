package spiderboot.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import spiderboot.configuration.ConfigProperties;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class SystemConfigForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtVideoFolderPath;
	private JTextField txtMaxLogDay;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public SystemConfigForm() {
		initialize();
		loadCOnfigurationData();
	}
	
	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_32x32/settings_32x32.png")));
		setTitle("System Configuration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 553);
		getContentPane().setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JTabbedPane pnAccountManager = new JTabbedPane(JTabbedPane.TOP);
		pnAccountManager.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnAccountManager.setBounds(10, 11, 718, 444);
		getContentPane().add(pnAccountManager);

		JPanel pnSysConfig = new JPanel();
		pnAccountManager.addTab("System Config", new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_16x16/user_16x16.png")), pnSysConfig, null);
		pnSysConfig.setLayout(null);

		JLabel lblNewLabel = new JLabel("Video Folder");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 14, 85, 16);
		pnSysConfig.add(lblNewLabel);

		txtVideoFolderPath = new JTextField();
		txtVideoFolderPath.setEditable(false);
		txtVideoFolderPath.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtVideoFolderPath.setBounds(154, 10, 450, 25);
		pnSysConfig.add(txtVideoFolderPath);
		txtVideoFolderPath.setColumns(10);

		JButton btnNewButton = new JButton("Browser...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Video Folder Path");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(pnAccountManager) == JFileChooser.APPROVE_OPTION) { 
					txtVideoFolderPath.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnNewButton.setBounds(614, 10, 89, 25);
		pnSysConfig.add(btnNewButton);
		
		JLabel lblDbConfiguration = new JLabel("Db Config");
		lblDbConfiguration.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDbConfiguration.setBounds(21, 67, 63, 16);
		pnSysConfig.add(lblDbConfiguration);
		
		JButton btnConfigDatabase = new JButton("Config Database");
		btnConfigDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseConfig dbConfig = new DatabaseConfig();
				dbConfig.setVisible(true);
			}
		});
		btnConfigDatabase.setIcon(new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_24x24/settings_24x24.png")));
		btnConfigDatabase.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnConfigDatabase.setBounds(154, 63, 161, 25);
		pnSysConfig.add(btnConfigDatabase);
		
		JLabel lblSavedLogMax = new JLabel("Saved log max");
		lblSavedLogMax.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSavedLogMax.setBounds(10, 117, 85, 16);
		pnSysConfig.add(lblSavedLogMax);
		
		txtMaxLogDay = new JTextField();
		txtMaxLogDay.setText((String) null);
		txtMaxLogDay.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtMaxLogDay.setEditable(false);
		txtMaxLogDay.setColumns(10);
		txtMaxLogDay.setBounds(154, 108, 161, 25);
		pnSysConfig.add(txtMaxLogDay);
		
		JLabel lblDay = new JLabel("days");
		lblDay.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDay.setBounds(336, 117, 85, 16);
		pnSysConfig.add(lblDay);
		
		JLabel lblMaxRowTable = new JLabel("Max row table");
		lblMaxRowTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMaxRowTable.setBounds(10, 170, 85, 16);
		pnSysConfig.add(lblMaxRowTable);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(154, 161, 161, 25);
		pnSysConfig.add(textField);
		
		JLabel lblRows = new JLabel("rows");
		lblRows.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblRows.setBounds(336, 170, 85, 16);
		pnSysConfig.add(lblRows);
		
		JLabel lblDateTimeFormat = new JLabel("Date time format");
		lblDateTimeFormat.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDateTimeFormat.setBounds(10, 225, 101, 16);
		pnSysConfig.add(lblDateTimeFormat);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBox.setBounds(154, 221, 161, 25);
		pnSysConfig.add(comboBox);

		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setIcon(new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_24x24/delete_24x24.png")));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(610, 465, 118, 38);
		getContentPane().add(button);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//save configuration file
				ConfigProperties.getInstance().writeProperties("VideoFolderPath", txtVideoFolderPath.getText().trim());
				dispose();
			}
		});
		btnSave.setIcon(new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_24x24/checked_24x24.png")));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnSave.setBounds(470, 465, 118, 38);
		getContentPane().add(btnSave);
		
		JButton btnDefaultCnig = new JButton("Default Config");
		btnDefaultCnig.setIcon(new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_24x24/refresh_24x24.png")));
		btnDefaultCnig.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDefaultCnig.setBounds(292, 466, 152, 38);
		getContentPane().add(btnDefaultCnig);
	}
	
	private void loadCOnfigurationData() {
		txtVideoFolderPath.setText(ConfigProperties.getInstance().getProperties("VideoFolderPath", ""));
	}
}
