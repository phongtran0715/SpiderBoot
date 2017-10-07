package spiderboot.view;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import spiderboot.helper.ImagePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	LoggingForm logFr = null;
	SearchForm searchFrm = null;
	BackupRestoreForm bakRestoreFr = null;
	SystemConfigForm systrmConfigFr = null;
	AccountManagerForm accountMngFrm = null;
	ChannelManagerForm channelManagerFr = null;

	public HomeForm() {	
		initialize();
	}
	
	private void initialize() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/user_32x32.png")));
		setTitle("Spider Boot V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 707);
		contentPane = new ImagePanel(new ImageIcon(getClass().getClassLoader().getResource("bg4.jpg")).getImage());
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.getContentPane().add(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CHANNEL MANAGER", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(1056, 307, 850, 229);
		contentPane.add(panel);

		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(searchFrm == null)
				{
					searchFrm = new SearchForm();
				}
				searchFrm.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/search_32x32.png")));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button.setBounds(42, 38, 230, 70);
		panel.add(button);

		JButton button_1 = new JButton("Account Manager");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(accountMngFrm == null)
				{
					accountMngFrm = new AccountManagerForm();	
				}
				accountMngFrm.setVisible(true);
			}
		});
		button_1.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/user_32x32.png")));
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_1.setBounds(313, 38, 230, 70);
		panel.add(button_1);

		JButton button_2 = new JButton("Channel Manager");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(channelManagerFr == null){
					channelManagerFr = new ChannelManagerForm();
				}
				channelManagerFr.setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/playlist_32x32.png")));
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_2.setBounds(594, 38, 230, 70);
		panel.add(button_2);

		JButton btnSystemConfig = new JButton("System \r\nConfig");
		btnSystemConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(systrmConfigFr == null){
					systrmConfigFr = new SystemConfigForm();
				}
				systrmConfigFr.setVisible(true);
			}
		});
		btnSystemConfig.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/settings_32x32.png")));
		btnSystemConfig.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSystemConfig.setBounds(42, 130, 230, 70);
		panel.add(btnSystemConfig);

		JButton button_4 = new JButton("Logging");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(logFr == null){
					logFr = new LoggingForm();
				}
				logFr.setVisible(true);
			}
		});
		button_4.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/graph_32x32.png")));
		button_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_4.setBounds(313, 130, 230, 70);
		panel.add(button_4);

		JButton button_5 = new JButton("Back Up / Restore");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bakRestoreFr == null){
					bakRestoreFr = new BackupRestoreForm();
				}
				bakRestoreFr.setVisible(true);
			}
		});
		button_5.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/upload_32x32.png")));
		button_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_5.setBounds(594, 130, 230, 70);
		panel.add(button_5);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SEO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(1056, 547, 850, 214);
		contentPane.add(panel_1);

		JButton button_6 = new JButton("Playlist");
		button_6.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/playlist_32x32.png")));
		button_6.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_6.setBounds(47, 38, 230, 70);
		panel_1.add(button_6);

		JButton button_7 = new JButton("Seeding");
		button_7.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/like_32x32.png")));
		button_7.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_7.setBounds(313, 38, 230, 70);
		panel_1.add(button_7);

		JButton button_8 = new JButton("Google Plus");
		button_8.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/google-plus32x32.png")));
		button_8.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_8.setBounds(594, 38, 230, 70);
		panel_1.add(button_8);

		JButton button_9 = new JButton("Facebook");
		button_9.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/facebook32x32.png")));
		button_9.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_9.setBounds(47, 130, 230, 70);
		panel_1.add(button_9);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setOpaque(false);
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "TOOL", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(1056, 772, 850, 221);
		contentPane.add(panel_2);

		JButton button_10 = new JButton("Change MD5");
		button_10.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/email_32x32.png")));
		button_10.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_10.setBounds(47, 38, 230, 70);
		panel_2.add(button_10);

		JButton button_11 = new JButton("Spin Text");
		button_11.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/refresh_32x32.png")));
		button_11.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_11.setBounds(313, 38, 230, 70);
		panel_2.add(button_11);

		JButton button_12 = new JButton("Create Thumbnail");
		button_12.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/subscribe_32x32.png")));
		button_12.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_12.setBounds(594, 38, 230, 70);
		panel_2.add(button_12);

		JButton button_13 = new JButton("Exit");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to quit application? \n "
						+ "Application will run on backgroound.", "Information", JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION){
					System.exit(0);	
				}else{
					//do nothing
				}
			}
		});
		button_13.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_32x32/delete_32x32.png")));
		button_13.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		button_13.setBounds(594, 130, 230, 70);
		panel_2.add(button_13);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		menuBar.setLocation(new Point(2, 2));
		menuBar.setMaximumSize(new Dimension(0, 5));
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.setBackground(UIManager.getColor("info"));
		setJMenuBar(menuBar);
		
		JMenu mnTool = new JMenu("TOOL");
		mnTool.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mnTool.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_16x16/settings_16x16.png")));
		mnTool.setHorizontalAlignment(SwingConstants.CENTER);
		mnTool.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnTool);
		
		JMenuItem mntmViewChannelInfo = new JMenuItem("View Channel Info");
		mntmViewChannelInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewChannelInfo viewCInfoFrm = new ViewChannelInfo();
				viewCInfoFrm.setModalityType(ModalityType.APPLICATION_MODAL);
				viewCInfoFrm.setVisible(true);
			}
		});
		mntmViewChannelInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mntmViewChannelInfo.setIcon(new ImageIcon(HomeForm.class.getResource("/spiderboot/resources/resource/icon_16x16/eye_16x16.png")));
		mntmViewChannelInfo.setHorizontalAlignment(SwingConstants.CENTER);
		mntmViewChannelInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnTool.add(mntmViewChannelInfo);
	}
}

