package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.google.api.services.samples.youtube.cmdline.data.ChannelInfo;
import com.google.api.services.youtube.model.Channel;

import spiderboot.databaseconnection.MySqlAccess;

import javax.swing.ImageIcon;

public class ViewChannelInfo extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tbResult;
	private JTextField txtChannelId;
	public DefaultTableModel tbResultMode = new DefaultTableModel();

	public ViewChannelInfo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewChannelInfo.class.getResource("/spiderboot/resources/resource/icon_32x32/eye_32x32.png")));
		initialize();
	}

	private void initialize(){
		setFont(new Font("Segoe UI", Font.PLAIN, 13));
		setTitle("Channel Information");
		setBounds(100, 100, 1060, 679);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel pnOption = new JPanel();
		pnOption.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnOption.setBounds(5, 24, 1029, 112);
		contentPanel.add(pnOption);
		pnOption.setLayout(null);

		JLabel lblNewLabel = new JLabel("Channel ID (Saparate multiple channel Id by  ' ; ' )");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 331, 20);
		pnOption.add(lblNewLabel);

		txtChannelId = new JTextField();
		txtChannelId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtChannelId.setBounds(10, 36, 1009, 30);
		pnOption.add(txtChannelId);
		txtChannelId.setColumns(10);

		JButton btnView = new JButton("View");
		btnView.setIcon(new ImageIcon(ViewChannelInfo.class.getResource("/spiderboot/resources/resource/icon_16x16/eye_16x16.png")));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cIds = txtChannelId.getText().trim();
				if(cIds == null || cIds.isEmpty()){
					JOptionPane.showMessageDialog(contentPanel, "Channel ID field must not be empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				tbResultMode.setRowCount(0);
				String [] lists = cIds.split(";");
				ChannelInfo cInfo = new ChannelInfo();
				for(int i = 0; i < lists.length; i++){
					List<Channel> cResult = cInfo.getChannelInfor(lists[i].trim());
					if(cResult != null){
						Iterator<Channel> channelEntries = cResult.iterator();
						int count = 1;
						while (channelEntries.hasNext()) {
							Channel channelItem = channelEntries.next();
							String cId = lists[i].trim();
							String cName = channelItem.getSnippet().getTitle();
							String viewCount = channelItem.getStatistics().getViewCount().toString();
							String totalSubs = channelItem.getStatistics().getSubscriberCount().toString();
							String totalVideo = channelItem.getStatistics().getVideoCount().toString();
							tbResultMode.addRow(new Object[]{Integer.toString(count++) , cId, cName, viewCount, totalSubs, totalVideo});
						}
					}
				}
			}
		});
		btnView.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnView.setBounds(10, 77, 109, 30);
		pnOption.add(btnView);

		JPanel pnTable = new JPanel();
		pnTable.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnTable.setBounds(5, 133, 1029, 470);
		contentPanel.add(pnTable);
		pnTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 1014, 454);
		pnTable.add(scrollPane);

		tbResultMode = new DefaultTableModel(new Object[][] {
		},new String[] {
				"STT", "Channel ID", "Channel Name", "View Count", "Total Subscriber", "Total Video"
		});

		tbResult = new JTable(tbResultMode);
		tbResult.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tbResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbResult.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbResult.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		scrollPane.setViewportView(tbResult);

		TableColumn column = null;
		column = tbResult.getColumnModel().getColumn(1);
		column.setPreferredWidth(10);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(5, 3, 1030, 20);
		contentPanel.add(panel);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 45, 21);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Option");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("View Spider Channel");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChannelId.setText("");
				Vector<String> vListId = getSpiderCId();
				System.out.println(vListId);
				String data = "";
				for(int i = 0; i< vListId.size() ; i++){
					data += vListId.get(i) + ";";
				}
				txtChannelId.setText(data);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("View Monitor Channel");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChannelId.setText("");
				Vector<String> vListId = getMonitorCId();
				System.out.println(vListId);
				String data = "";
				for(int i = 0; i< vListId.size() ; i++){
					data += vListId.get(i) + ";";
				}
				txtChannelId.setText(data);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu.add(mntmNewMenuItem_1);

		JButton button = new JButton("Exit");
		button.setIcon(new ImageIcon(ViewChannelInfo.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(925, 605, 109, 30);
		contentPanel.add(button);
	}

	private Vector<String> getSpiderCId(){
		Vector<String> cIdList =  null;
		Statement stmt;
		try
		{
			String query = "SELECT ChannelId FROM home_channel_list;";
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// Data of the table
			cIdList = new Vector<String>();
			while (rs.next()) {
				String id = rs.getString("ChannelId");
				System.out.println(id);
				cIdList.add(id.trim());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return cIdList;
	}

	private Vector<String> getMonitorCId() {
		Vector<String> cIdList =  null;
		Statement stmt;
		try
		{
			String query = "SELECT ChannelId FROM monitor_channel_list;";
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// Data of the table
			cIdList = new Vector<String>();
			while (rs.next()) {
				String id = rs.getString("ChannelId");
				System.out.println(id);
				cIdList.add(id.trim());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return cIdList;
	}
}
