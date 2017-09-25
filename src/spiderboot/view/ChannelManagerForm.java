package spiderboot.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class ChannelManagerForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChannelManagerForm frame = new ChannelManagerForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChannelManagerForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_32x32/playlist_32x32.png")));
		setTitle("Account Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 553);
		getContentPane().setLayout(null);
		
		JTabbedPane pnChannelManager = new JTabbedPane(JTabbedPane.TOP);
		pnChannelManager.setBounds(10, 11, 718, 492);
		getContentPane().add(pnChannelManager);
		
		JPanel pnHomeChannel = new JPanel();
		pnChannelManager.addTab("Home Spider Channel", new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/user_16x16.png")), pnHomeChannel, null);
		pnHomeChannel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Channel List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 693, 391);
		pnHomeChannel.add(panel_1);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		table_1.setBackground(SystemColor.inactiveCaption);
		panel_1.add(table_1);
		
		JButton button = new JButton("Add new");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(10, 413, 118, 38);
		pnHomeChannel.add(button);
		
		JButton button_1 = new JButton("Edit");
		button_1.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/edit_16x16.png")));
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_1.setBounds(170, 413, 118, 38);
		pnHomeChannel.add(button_1);
		
		JButton button_2 = new JButton("Delete");
		button_2.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/trash_16x16.png")));
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_2.setBounds(334, 413, 118, 38);
		pnHomeChannel.add(button_2);
		
		JButton button_3 = new JButton("Exit");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_3.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_3.setBounds(585, 413, 118, 38);
		pnHomeChannel.add(button_3);
		
		JPanel pnMonitorChannel = new JPanel();
		pnChannelManager.addTab("Monitor Spider Channel", new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/google-plus16x16.png")), pnMonitorChannel, null);
		pnMonitorChannel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Channel List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 693, 391);
		pnMonitorChannel.add(panel);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setBackground(SystemColor.inactiveCaption);
		panel.add(table);
		
		JButton btnNewButton = new JButton("Add new");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnNewButton.setBounds(10, 413, 118, 38);
		pnMonitorChannel.add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/edit_16x16.png")));
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnEdit.setBounds(170, 413, 118, 38);
		pnMonitorChannel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/trash_16x16.png")));
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDelete.setBounds(334, 413, 118, 38);
		pnMonitorChannel.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(585, 413, 118, 38);
		pnMonitorChannel.add(btnExit);
		
		JPanel pnMappingChannel = new JPanel();
		pnChannelManager.addTab("Home-Monitor Mapping", null, pnMappingChannel, null);
		pnMappingChannel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mapping List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 11, 693, 391);
		pnMappingChannel.add(panel_2);
		
		table_2 = new JTable();
		table_2.setFillsViewportHeight(true);
		table_2.setBackground(SystemColor.inactiveCaption);
		panel_2.add(table_2);
		
		JButton button_4 = new JButton("Exit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_4.setBounds(585, 413, 118, 38);
		pnMappingChannel.add(button_4);
		
	}
}
