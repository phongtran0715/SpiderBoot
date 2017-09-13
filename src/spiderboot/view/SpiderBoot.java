package spiderboot.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

public class SpiderBoot {

	private JFrame frmSpiderBoot;
	LoggingForm logFr = null;
	SearchForm searchFrm = null;
	BackupRestoreForm bakRestoreFr = null;
	SystemConfigForm systrmConfigFr = null;
	AccountManagerForm accountMngFrm = null;
	ChannelManagerForm channelManagerFr = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpiderBoot window = new SpiderBoot();
					window.frmSpiderBoot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpiderBoot() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpiderBoot = new JFrame();
		frmSpiderBoot.getContentPane().setBackground(SystemColor.control);
		frmSpiderBoot.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmSpiderBoot.setFont(new Font("Dialog", Font.PLAIN, 13));
		frmSpiderBoot.setBackground(SystemColor.inactiveCaption);
		frmSpiderBoot.setTitle("Spider Boot 1.0");
		frmSpiderBoot.setIconImage(Toolkit.getDefaultToolkit().getImage(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/eye_32x32.png")));
		frmSpiderBoot.setBounds(100, 100, 855, 514);
		frmSpiderBoot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpiderBoot.setLocationRelativeTo(null);
		frmSpiderBoot.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CHANNEL MANAGER", TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.PLAIN, 18), new Color(0, 0, 0)));
		panel.setBounds(1048, 270, 850, 229);
		frmSpiderBoot.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchFrm == null)
				{
					searchFrm = new SearchForm();
				}
				searchFrm.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/search_32x32.png")));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnNewButton.setBounds(42, 38, 230, 70);
		panel.add(btnNewButton);
		
		JButton btnAccountManager = new JButton("Account Manager");
		btnAccountManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(accountMngFrm == null)
				{
					accountMngFrm = new AccountManagerForm();	
				}
				accountMngFrm.setVisible(true);
			}
		});
		btnAccountManager.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/list_32x32.png")));
		btnAccountManager.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnAccountManager.setBounds(313, 38, 230, 70);
		panel.add(btnAccountManager);
		
		JButton btnChannelManager = new JButton("Channel Manager");
		btnChannelManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(channelManagerFr == null){
					channelManagerFr = new ChannelManagerForm();
				}
				channelManagerFr.setVisible(true);
			}
		});
		btnChannelManager.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/playlist_32x32.png")));
		btnChannelManager.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnChannelManager.setBounds(594, 38, 230, 70);
		panel.add(btnChannelManager);
		
		JButton btnSystemconfiguration = new JButton("System \r\nConfiguration");
		btnSystemconfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(systrmConfigFr == null){
					systrmConfigFr = new SystemConfigForm();
				}
				systrmConfigFr.setVisible(true);
			}
		});
		btnSystemconfiguration.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/settings_16x16.png")));
		btnSystemconfiguration.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSystemconfiguration.setBounds(42, 130, 230, 70);
		panel.add(btnSystemconfiguration);
		
		JButton btnLogging = new JButton("Logging");
		btnLogging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(logFr == null){
					logFr = new LoggingForm();
				}
				logFr.setVisible(true);
			}
		});
		btnLogging.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/edit_32x32.png")));
		btnLogging.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnLogging.setBounds(313, 130, 230, 70);
		panel.add(btnLogging);
		
		JButton btnBackUp = new JButton("Back Up / Restore");
		btnBackUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bakRestoreFr == null){
					bakRestoreFr = new BackupRestoreForm();
				}
				bakRestoreFr.setVisible(true);
			}
		});
		btnBackUp.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/export_32x32.png")));
		btnBackUp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnBackUp.setBounds(594, 130, 230, 70);
		panel.add(btnBackUp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SEO", TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.PLAIN, 18), new Color(0, 0, 0)));
		panel_1.setBounds(1048, 510, 850, 214);
		frmSpiderBoot.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnPlaylist = new JButton("Playlist");
		btnPlaylist.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/playlist_32x32.png")));
		btnPlaylist.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnPlaylist.setBounds(47, 38, 230, 70);
		panel_1.add(btnPlaylist);
		
		JButton btnSeeding = new JButton("Seeding");
		btnSeeding.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/like_32x32.png")));
		btnSeeding.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSeeding.setBounds(313, 38, 230, 70);
		panel_1.add(btnSeeding);
		
		JButton btnGooglePlus = new JButton("Google Plus");
		btnGooglePlus.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/google-plus32x32.png")));
		btnGooglePlus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnGooglePlus.setBounds(594, 38, 230, 70);
		panel_1.add(btnGooglePlus);
		
		JButton btnFacebook = new JButton("Facebook");
		btnFacebook.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/facebook32x32.png")));
		btnFacebook.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnFacebook.setBounds(47, 130, 230, 70);
		panel_1.add(btnFacebook);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.text);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "TOOL", TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.PLAIN, 18), new Color(0, 0, 0)));
		panel_2.setBounds(1048, 735, 850, 221);
		frmSpiderBoot.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnChangeMd = new JButton("Change MD5");
		btnChangeMd.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/chat_32x32.png")));
		btnChangeMd.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnChangeMd.setBounds(47, 38, 230, 70);
		panel_2.add(btnChangeMd);
		
		JButton btnSpinText = new JButton("Spin Text");
		btnSpinText.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/refresh_32x32.png")));
		btnSpinText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSpinText.setBounds(313, 38, 230, 70);
		panel_2.add(btnSpinText);
		
		JButton btnCreateThumbnail = new JButton("Create Thumbnail");
		btnCreateThumbnail.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/eye_32x32.png")));
		btnCreateThumbnail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnCreateThumbnail.setBounds(594, 38, 230, 70);
		panel_2.add(btnCreateThumbnail);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_32x32/delete_32x32.png")));
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnExit.setBounds(594, 130, 230, 70);
		panel_2.add(btnExit);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		menuBar.setBorder(new TitledBorder(new CompoundBorder(null, UIManager.getBorder("CheckBoxMenuItem.border")), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		menuBar.setBackground(Color.WHITE);
		menuBar.setForeground(SystemColor.activeCaption);
		frmSpiderBoot.setJMenuBar(menuBar);
		
		JMenu mnTool = new JMenu("Tool");
		mnTool.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/settings_16x16.png")));
		mnTool.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnTool);
		
		JMenuItem mntmTmVPhn = new JMenuItem("T\u00ECm v\u00E0 ph\u00E2n t\u00EDch video");
		mntmTmVPhn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmTmVPhn.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/search_16x16.png")));
		mnTool.add(mntmTmVPhn);
		
		JMenuItem mntmXemThngTin = new JMenuItem("Xem th\u00F4ng tin k\u00EAnh");
		mntmXemThngTin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmXemThngTin.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/eye_16x16.png")));
		mnTool.add(mntmXemThngTin);
		
		JMenu mnPlaylist = new JMenu("Playlist");
		mnPlaylist.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/playlist_16x16.png")));
		mnPlaylist.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnPlaylist);
		
		JMenuItem mntmPlaylistCaTi = new JMenuItem("Playlist c\u1EE7a t\u00F4i");
		mntmPlaylistCaTi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnPlaylist.add(mntmPlaylistCaTi);
		
		JMenuItem mntmToPlaylist = new JMenuItem("T\u1EA1o playlist");
		mntmToPlaylist.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		mntmToPlaylist.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnPlaylist.add(mntmToPlaylist);
		
		JMenu mnNewMenu_1 = new JMenu("Seeding");
		mnNewMenu_1.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/auto-flash_16x16.png")));
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmTiKhonSeeding = new JMenuItem("T\u00E0i kho\u1EA3n seeding");
		mntmTiKhonSeeding.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu_1.add(mntmTiKhonSeeding);
		
		JMenuItem mntmLikeVideo = new JMenuItem("Like Video");
		mntmLikeVideo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu_1.add(mntmLikeVideo);
		
		JMenuItem mntmCommentVideo = new JMenuItem("Comment Video");
		mntmCommentVideo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu_1.add(mntmCommentVideo);
		
		JMenuItem mntmSubscribeKnh = new JMenuItem("Subscribe k\u00EAnh");
		mntmSubscribeKnh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu_1.add(mntmSubscribeKnh);
		
		JMenu mnDanhSchKnh = new JMenu("Danh s\u00E1ch k\u00EAnh");
		mnDanhSchKnh.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/list_16x16.png")));
		mnDanhSchKnh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnDanhSchKnh);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Danh s\u00E1ch k\u00EAnh");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnDanhSchKnh.add(mntmNewMenuItem);
		
		JMenuItem mntmThngKKnh = new JMenuItem("Th\u1ED1ng k\u00EA k\u00EAnh");
		mntmThngKKnh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnDanhSchKnh.add(mntmThngKKnh);
		
		JMenuItem mntmThmKnh = new JMenuItem("Th\u00EAm k\u00EAnh");
		mntmThmKnh.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnDanhSchKnh.add(mntmThmKnh);
		
		JMenu mnTiKhon = new JMenu("T\u00E0i kho\u1EA3n");
		mnTiKhon.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/user_16x16.png")));
		mnTiKhon.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnTiKhon);
		
		JMenuItem mntmTiKhonSpider = new JMenuItem("T\u00E0i kho\u1EA3n Spider Boot");
		mntmTiKhonSpider.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnTiKhon.add(mntmTiKhonSpider);
		
		JMenuItem mntmTiKhonGoogle = new JMenuItem("T\u00E0i kho\u1EA3n Google App");
		mntmTiKhonGoogle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnTiKhon.add(mntmTiKhonGoogle);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Tho\u00E1t");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(SpiderBoot.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		menuBar.add(mntmNewMenuItem_1);
	}
}
