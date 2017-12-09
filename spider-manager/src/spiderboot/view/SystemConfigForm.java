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

public class SystemConfigForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtVideoFolderPath;

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

		JPanel pnSpiderApp = new JPanel();
		pnAccountManager.addTab("System Config", new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_16x16/user_16x16.png")), pnSpiderApp, null);
		pnSpiderApp.setLayout(null);

		JLabel lblNewLabel = new JLabel("Video Folder");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 14, 85, 16);
		pnSpiderApp.add(lblNewLabel);

		txtVideoFolderPath = new JTextField();
		txtVideoFolderPath.setEditable(false);
		txtVideoFolderPath.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtVideoFolderPath.setBounds(99, 10, 505, 25);
		pnSpiderApp.add(txtVideoFolderPath);
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
		pnSpiderApp.add(btnNewButton);

		JPanel pnGoogleApp = new JPanel();
		pnAccountManager.addTab("Database Config", new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_16x16/google-plus16x16.png")), pnGoogleApp, null);
		pnGoogleApp.setLayout(null);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(585, 413, 118, 38);
		pnGoogleApp.add(btnExit);

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
	}
	
	private void loadCOnfigurationData() {
		txtVideoFolderPath.setText(ConfigProperties.getInstance().getProperties("VideoFolderPath", ""));
	}
}
