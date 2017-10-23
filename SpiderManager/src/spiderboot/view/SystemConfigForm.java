package spiderboot.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SystemConfigForm extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public SystemConfigForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_32x32/settings_32x32.png")));
		setTitle("System Configuration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 553);
		getContentPane().setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);
		
		JTabbedPane pnAccountManager = new JTabbedPane(JTabbedPane.TOP);
		pnAccountManager.setBounds(10, 11, 718, 492);
		getContentPane().add(pnAccountManager);
		
		JPanel pnSpiderApp = new JPanel();
		pnAccountManager.addTab("System Config", new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_16x16/user_16x16.png")), pnSpiderApp, null);
		pnSpiderApp.setLayout(null);
		
		JButton button_3 = new JButton("Exit");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_3.setIcon(new ImageIcon(SystemConfigForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_3.setBounds(585, 413, 118, 38);
		pnSpiderApp.add(button_3);
		
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
		
	}
}
