package spiderboot.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import spiderboot.databaseconnection.MySqlAccess;
import spiderboot.helper.ButtonEditor;
import spiderboot.helper.ButtonRenderer;
import spiderboot.helper.SyncTimerTask;
import spiderboot.helper.Util;

public class ChannelManagerForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable tbMapChannel = new JTable();
	private JTable tbMonitorChannel = new JTable();
	private JTable tbHomeChannel = new JTable();
	public DefaultTableModel tbHomeChannelMode = new DefaultTableModel();
	public DefaultTableModel tbMonitorChanelMode = new DefaultTableModel();
	public DefaultTableModel tbMapChanelMode = new DefaultTableModel();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	HashMap<Integer, Timer> timerMap = new HashMap<Integer, Timer>();

	public ChannelManagerForm() {
		initialize();
		loadHomeChannel();
	}

	private void initialize()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_32x32/playlist_32x32.png")));
		setTitle("Account Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 651);
		getContentPane().setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JTabbedPane pnChannelManager = new JTabbedPane(JTabbedPane.TOP);
		pnChannelManager.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		pnChannelManager.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int tabIndex = pnChannelManager.getSelectedIndex();
				if(tabIndex == 0){
					loadHomeChannel();
				}else if(tabIndex == 1){
					loadMonitorChannel();
				}else if(tabIndex == 2){
					loadMappingTable();
				}else {
					//do nothing
				}
			}
		});
		pnChannelManager.setBounds(5, 5, 1012, 596);
		getContentPane().add(pnChannelManager);

		JPanel pnHomeChannel = new JPanel();
		pnChannelManager.addTab("Home Spider Channel", new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/user_16x16.png")), pnHomeChannel, null);
		pnHomeChannel.setLayout(null);

		JPanel pnHomeChannelList = new JPanel();
		pnHomeChannelList.setBorder(new TitledBorder(null, "Channel List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnHomeChannelList.setBounds(5, 5, 992, 501);
		pnHomeChannel.add(pnHomeChannelList);
		pnHomeChannelList.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addKeyListener(new KeyAdapter() {
		});
		scrollPane_1.setBounds(10, 21, 970, 470);
		pnHomeChannelList.add(scrollPane_1);

		tbHomeChannel = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		tbHomeChannel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		tbHomeChannel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int rIndex =  tbHomeChannel.getSelectedRow();
					if(rIndex == -1){
						JOptionPane.showMessageDialog(pnHomeChannel, "Please select one row to edit !");
						return;
					}else{
						int id = (Integer)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("Id").getModelIndex());
						String cId = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("ChannelId").getModelIndex());
						String cName = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("ChannelName").getModelIndex());
						String googleAcc = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("GoogleAccount").getModelIndex());
						String vIntro = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("VideoIntro").getModelIndex());
						String vOutro = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("VideoOutro").getModelIndex());
						String logo = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("Logo").getModelIndex());
						String descTemp = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("DescriptionTemplate").getModelIndex());
						String titleTemp = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("TitleTemplate").getModelIndex());
						ModifyHomeChannel modHmoeChFrm = new ModifyHomeChannel(id, cId, cName, googleAcc, vIntro, vOutro, logo, descTemp, titleTemp);
						modHmoeChFrm.setModalityType(ModalityType.APPLICATION_MODAL);
						modHmoeChFrm.setVisible(true);
						loadHomeChannel();
					}
				}
			}
		});
		scrollPane_1.setViewportView(tbHomeChannel);
		tbHomeChannel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbHomeChannel.setRowHeight(25);
		//center data in jtable cell
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i = 0; i < tbHomeChannel.getColumnCount(); i++){
			tbHomeChannel.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );	
		}

		JButton btnAddHomeChannel = new JButton("Add new");
		btnAddHomeChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddHomeChannel newHomeChannelFrm = new AddHomeChannel();
				newHomeChannelFrm.setModalityType(ModalityType.APPLICATION_MODAL);
				newHomeChannelFrm.setVisible(true);
				loadHomeChannel();
			}
		});
		btnAddHomeChannel.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		btnAddHomeChannel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnAddHomeChannel.setBounds(10, 517, 118, 38);
		pnHomeChannel.add(btnAddHomeChannel);

		JButton btnModifyHomeChannel = new JButton("Edit");
		btnModifyHomeChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rIndex =  tbHomeChannel.getSelectedRow();
				if(rIndex == -1){
					JOptionPane.showMessageDialog(pnHomeChannel, "Please select one row to edit !");
					return;
				}else{
					int id = (Integer)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("Id").getModelIndex());
					String cId = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("ChannelId").getModelIndex());
					String cName = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("ChannelName").getModelIndex());
					String googleAcc = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("GoogleAccount").getModelIndex());
					String vIntro = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("VideoIntro").getModelIndex());
					String vOutro = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("VideoOutro").getModelIndex());
					String logo = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("Logo").getModelIndex());
					String descTemp = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("DescriptionTemplate").getModelIndex());
					String titleTemp = (String)tbHomeChannel.getValueAt(rIndex, tbHomeChannel.getColumn("TitleTemplate").getModelIndex());
					ModifyHomeChannel modHmoeChFrm = new ModifyHomeChannel(id, cId, cName, googleAcc, vIntro, vOutro, logo, descTemp, titleTemp);
					modHmoeChFrm.setModalityType(ModalityType.APPLICATION_MODAL);
					modHmoeChFrm.setVisible(true);
					loadHomeChannel();
				}
			}
		});
		btnModifyHomeChannel.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/edit_16x16.png")));
		btnModifyHomeChannel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnModifyHomeChannel.setBounds(170, 517, 118, 38);
		pnHomeChannel.add(btnModifyHomeChannel);

		JButton btnDeleteHomeChannel = new JButton("Delete");
		btnDeleteHomeChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tbHomeChannel.getSelectedRow();
				if(selectedRow == -1){
					return;
				}
				int result = JOptionPane.showConfirmDialog(pnHomeChannel, "Are you sure to delete item?",
						"Question",JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION){
					int accId = (int) tbHomeChannel.getValueAt(selectedRow, 0);
					String query = "DELETE FROM home_channel_list WHERE Id = ? ;";
					PreparedStatement preparedStm;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setInt(1, accId);
						preparedStm.executeUpdate();
						//reload jtable
						tbHomeChannelMode.removeRow(selectedRow);
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						JOptionPane.showInternalMessageDialog(pnHomeChannel, "Delete item false! \n" + e.toString(),
								"Error", JOptionPane.ERROR_MESSAGE);
					}	
				}else{
					//do nothing
				}
			}
		});
		btnDeleteHomeChannel.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/trash_16x16.png")));
		btnDeleteHomeChannel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDeleteHomeChannel.setBounds(334, 517, 118, 38);
		pnHomeChannel.add(btnDeleteHomeChannel);

		JButton button_3 = new JButton("Exit");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_3.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_3.setBounds(879, 517, 118, 38);
		pnHomeChannel.add(button_3);

		JPanel pnMonitorChannel = new JPanel();
		pnChannelManager.addTab("Monitor Spider Channel", new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/google-plus16x16.png")), pnMonitorChannel, null);
		pnMonitorChannel.setLayout(null);

		JPanel pnMonitorChannelList = new JPanel();
		pnMonitorChannelList.setBorder(new TitledBorder(null, "Channel List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnMonitorChannelList.setBounds(5, 5, 992, 500);
		pnMonitorChannel.add(pnMonitorChannelList);
		pnMonitorChannelList.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 970, 470);
		pnMonitorChannelList.add(scrollPane);

		tbMonitorChannel = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		tbMonitorChannel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		tbMonitorChannel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int rIndex =  tbMonitorChannel.getSelectedRow();
					if(rIndex == -1){
						JOptionPane.showMessageDialog(pnHomeChannel, "Please select one row to edit !");
						return;
					}else{
						int id = (int)tbMonitorChannel.getValueAt(rIndex, tbMonitorChannel.getColumn("Id").getModelIndex());
						String cId = (String)tbMonitorChannel.getValueAt(rIndex, tbMonitorChannel.getColumn("ChannelId").getModelIndex());
						String cName = (String)tbMonitorChannel.getValueAt(rIndex, tbMonitorChannel.getColumn("ChannelName").getModelIndex());					
						ModifyMonitorChannel modMonitorChFrm = new ModifyMonitorChannel(id, cId, cName);
						modMonitorChFrm.setModalityType(ModalityType.APPLICATION_MODAL);
						modMonitorChFrm.setVisible(true);
						loadMonitorChannel();
					}
				}
			}
		});
		scrollPane.setViewportView(tbMonitorChannel);
		tbMonitorChannel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbMonitorChannel.setRowHeight(25);
		//center data in jtable cell

		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i = 0; i < tbMonitorChannel.getColumnCount(); i++){
			tbMonitorChannel.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );	
		}

		JButton btnAddMonitorChannel = new JButton("Add new");
		btnAddMonitorChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMonitorChannel newMonitorChannelFrm = new AddMonitorChannel();
				newMonitorChannelFrm.setModalityType(ModalityType.APPLICATION_MODAL);
				newMonitorChannelFrm.setVisible(true);
				loadMonitorChannel();
			}
		});
		btnAddMonitorChannel.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		btnAddMonitorChannel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnAddMonitorChannel.setBounds(10, 517, 118, 38);
		pnMonitorChannel.add(btnAddMonitorChannel);

		JButton btnModifyMonitorChannel = new JButton("Edit");
		btnModifyMonitorChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rIndex =  tbMonitorChannel.getSelectedRow();
				if(rIndex == -1){
					JOptionPane.showMessageDialog(pnHomeChannel, "Please select one row to edit !");
					return;
				}else{
					int id = (int)tbMonitorChannel.getValueAt(rIndex, tbMonitorChannel.getColumn("Id").getModelIndex());
					String cId = (String)tbMonitorChannel.getValueAt(rIndex, tbMonitorChannel.getColumn("ChannelId").getModelIndex());
					String cName = (String)tbMonitorChannel.getValueAt(rIndex, tbMonitorChannel.getColumn("ChannelName").getModelIndex());					
					ModifyMonitorChannel modMonitorChFrm = new ModifyMonitorChannel(id, cId, cName);
					modMonitorChFrm.setModalityType(ModalityType.APPLICATION_MODAL);
					modMonitorChFrm.setVisible(true);
					loadMonitorChannel();
				}
			}
		});
		btnModifyMonitorChannel.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/edit_16x16.png")));
		btnModifyMonitorChannel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnModifyMonitorChannel.setBounds(170, 517, 118, 38);
		pnMonitorChannel.add(btnModifyMonitorChannel);

		JButton btnDeleteMonitorChannel = new JButton("Delete");
		btnDeleteMonitorChannel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tbMonitorChannel.getSelectedRow();
				if(selectedRow == -1){
					return;
				}
				int result = JOptionPane.showConfirmDialog(pnHomeChannel, "Are you sure to delete item?",
						"Question",JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION){
					int accId = (int) tbMonitorChannel.getValueAt(selectedRow, 0);
					String query = "DELETE FROM monitor_channel_list WHERE Id = ? ;";
					PreparedStatement preparedStm;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setInt(1, accId);
						preparedStm.executeUpdate();
						//reload jtable
						tbMonitorChanelMode.removeRow(selectedRow);
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						JOptionPane.showInternalMessageDialog(pnHomeChannel, "Delete item false! \n" + e.toString());
					}	
				}else{
					//do nothing
				}
			}
		});
		btnDeleteMonitorChannel.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/trash_16x16.png")));
		btnDeleteMonitorChannel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDeleteMonitorChannel.setBounds(334, 517, 118, 38);
		pnMonitorChannel.add(btnDeleteMonitorChannel);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(ChannelManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(879, 517, 118, 38);
		pnMonitorChannel.add(btnExit);

		JPanel pnMappingChannel = new JPanel();
		pnChannelManager.addTab("Home-Monitor Mapping", null, pnMappingChannel, null);
		pnMappingChannel.setLayout(null);

		JPanel pnMapChannel = new JPanel();
		pnMapChannel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mapping List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnMapChannel.setBounds(5, 5, 992, 506);
		pnMappingChannel.add(pnMapChannel);
		pnMapChannel.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 21, 970, 470);
		pnMapChannel.add(scrollPane_2);

		tbMapChannel = new JTable(){
			private static final long serialVersionUID = 1L;

			//			public boolean isCellEditable(int row, int column) {                
			//				return false;               
			//			};
		};
		tbMapChannel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int sel = tbMapChannel.getSelectedRow();
			}
		});
		tbMapChannel.setColumnSelectionAllowed(true);
		tbMapChannel.setCellSelectionEnabled(true);
		tbMapChannel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tbMapChannel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbMapChannel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		scrollPane_2.setViewportView(tbMapChannel);
		tbMapChannel.setFillsViewportHeight(true);
		tbMapChannel.setRowHeight(25);
		tbMapChannel.setBackground(UIManager.getColor("Button.disabledShadow"));
		//center data in jtable cell
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int i = 0; i < tbMapChannel.getColumnCount(); i++){
			tbMapChannel.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );	
		}

		JButton button_4 = new JButton("Exit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_4.setBounds(879, 517, 118, 38);
		pnMappingChannel.add(button_4);

		JButton btnAddMappingTable = new JButton("Add new");
		btnAddMappingTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMappingTable addMapChannelFrm = new AddMappingTable();
				addMapChannelFrm.setModalityType(ModalityType.APPLICATION_MODAL);
				addMapChannelFrm.setVisible(true);
				loadMappingTable();
			}
		});
		btnAddMappingTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnAddMappingTable.setBounds(5, 517, 118, 38);
		pnMappingChannel.add(btnAddMappingTable);

		JButton btnEditMappingTable = new JButton("Edit");
		btnEditMappingTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rIndex =  tbMapChannel.getSelectedRow();
				if(rIndex == -1){
					JOptionPane.showMessageDialog(pnHomeChannel, "Please select one row to edit !");
					return;
				}else{
					//Check sync task is running
					String syncStatus = (String)tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("StatusSync").getModelIndex());
					if(syncStatus.equals("Running")){
						JOptionPane.showMessageDialog(pnChannelManager, "Can not modify.This task is running. Please stop it before editing.");
						return;
					}
					
					int id = (int)tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("Id").getModelIndex());
					String cHomeID = (String)tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("HomeChannelId").getModelIndex());
					String cMonitorId = (String)tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("MonitorChannelId").getModelIndex());
					int timeSync = (int)tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("TimeIntervalSync").getModelIndex());
					ModifyMappingTable modMapTableFrm = new ModifyMappingTable(id, cHomeID, cMonitorId, timeSync);
					modMapTableFrm.setModalityType(ModalityType.APPLICATION_MODAL);
					modMapTableFrm.setVisible(true);
					loadMappingTable();
				}
			}
		});
		btnEditMappingTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnEditMappingTable.setBounds(165, 517, 118, 38);
		pnMappingChannel.add(btnEditMappingTable);

		JButton btnDeleteMappingTable = new JButton("Delete");
		btnDeleteMappingTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rIndex = tbMapChannel.getSelectedRow();
				if(rIndex == -1){
					return;
				}
				int result = JOptionPane.showConfirmDialog(pnHomeChannel, "Are you sure to delete item?",
						"Question",JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION){
					int accId = (int) tbMapChannel.getValueAt(rIndex, 0);
					String cHomeID = (String)tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("HomeChannelId").getModelIndex());
					String cMonitorId = (String)tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("MonitorChannelId").getModelIndex());
					String query = "DELETE FROM home_monitor_channel_mapping WHERE Id = ? ;";
					PreparedStatement preparedStm;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setInt(1, accId);
						preparedStm.executeUpdate();
						//reload jtable
						tbMapChanelMode.removeRow(rIndex);
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						JOptionPane.showInternalMessageDialog(pnHomeChannel, "Delete item false! \n" + e.toString());
						return;
					}
					//delete data folder
					new Util().deleteFolder(cHomeID + "-" + cMonitorId);
				}
			}
		});
		btnDeleteMappingTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDeleteMappingTable.setBounds(329, 517, 118, 38);
		pnMappingChannel.add(btnDeleteMappingTable);
	}

	private void loadHomeChannel() {
		tbHomeChannelMode = new DefaultTableModel();
		Statement stmt;
		try
		{
			String query = "SELECT * FROM home_channel_list;";
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metaData = rs.getMetaData();

			// Names of columns
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(metaData.getColumnName(i));
			}

			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= columnCount; i++) {
					vector.add(rs.getObject(i));	
				}
				data.add(vector);
			}
			tbHomeChannelMode.setDataVector(data, columnNames);
			if(tbHomeChannelMode != null){
				tbHomeChannel.setModel(tbHomeChannelMode);
				//Hide id column
				tbHomeChannel.getColumnModel().getColumn(0).setMinWidth(0);
				tbHomeChannel.getColumnModel().getColumn(0).setMaxWidth(0);	
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void loadMonitorChannel() {
		tbMonitorChanelMode = new DefaultTableModel();
		Statement stmt;
		try
		{
			String query = "SELECT * FROM monitor_channel_list;";
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metaData = rs.getMetaData();

			// Names of columns
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(metaData.getColumnName(i));
			}

			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= columnCount; i++) {
					vector.add(rs.getObject(i));	
				}
				data.add(vector);
			}
			tbMonitorChanelMode.setDataVector(data, columnNames);
			if(tbMonitorChanelMode != null){
				tbMonitorChannel.setModel(tbMonitorChanelMode);
				//Hide id column
				tbMonitorChannel.getColumnModel().getColumn(0).setMinWidth(0);
				tbMonitorChannel.getColumnModel().getColumn(0).setMaxWidth(0);	
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void loadMappingTable() {
		tbMapChanelMode = new DefaultTableModel();
		Statement stmt;
		try
		{
			String query = "SELECT map.Id, map.HomeChannelId, home.ChannelName, map.MonitorChannelId,"
					+ " monitor.ChannelName, map.TimeIntervalSync, map.StatusSync, map.Action"
					+ " FROM home_monitor_channel_mapping AS map"
					+ " JOIN home_channel_list AS home ON(map.HomeChannelId = home.ChannelId)"
					+ " JOIN monitor_channel_list AS monitor ON (map.MonitorChannelId = monitor.ChannelId);";
			stmt = MySqlAccess.getInstance().connect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metaData = rs.getMetaData();

			// Names of columns
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(metaData.getColumnName(i));
			}

			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= columnCount; i++) {
					String name = metaData.getColumnName(i);
					if(name.equals("StatusSync")){
						int value = (int)rs.getObject(i);
						if(value == 0){
							vector.add("Stopped");
						}else{
							vector.add("Running");
						}
					}else if(name.equals("Action")){
						int value = (int)rs.getObject(i);
						if(value == 1){
							vector.add("Run");
						}else{
							vector.add("Stop");
						}
					}else{
						vector.add(rs.getObject(i));	
					}
				}
				data.add(vector);
			}
			tbMapChanelMode.setDataVector(data, columnNames);
			if(tbMapChanelMode != null){
				tbMapChannel.setModel(tbMapChanelMode);
				//Hide id column
				tbMapChannel.getColumnModel().getColumn(0).setMinWidth(0);
				tbMapChannel.getColumnModel().getColumn(0).setMaxWidth(0);
				//SET CUSTOM RENDERER TO TEAMS COLUMN
				tbMapChannel.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
				//SET CUSTOM EDITOR TO TEAMS COLUMN
				tbMapChannel.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField(), this));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public boolean startSyncThread() {
		boolean isSuccess = false;
		int rIndex =  tbMapChannel.getSelectedRow();
		if(rIndex == -1){
			return isSuccess;
		}else{
			int id = (Integer) tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("Id").getModelIndex());
			SyncTaskManager.getInstance().startSyncThread(Integer.toString(id), 10*1000);
			tbMapChannel.getModel().setValueAt("Running", rIndex, 6);
			tbMapChannel.getModel().setValueAt("Stop", rIndex, 7);
			//update database
			String query = "UPDATE home_monitor_channel_mapping SET StatusSync = ? , Action = ? WHERE Id = ? ;";
			try {
				PreparedStatement preStm = MySqlAccess.getInstance().connect.prepareStatement(query);
				preStm.setInt(1, 1);
				preStm.setInt(2, 0);
				preStm.setInt(3, id);
				preStm.executeUpdate();
				isSuccess = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isSuccess;
	}

	public boolean stopSyncThread() {
		boolean isSuccess = false;
		int rIndex =  tbMapChannel.getSelectedRow();
		if(rIndex == -1){
			return isSuccess;
		}else{
			int id = (Integer) tbMapChannel.getValueAt(rIndex, tbMapChannel.getColumn("Id").getModelIndex());
			SyncTaskManager.getInstance().stopSyncThread(Integer.toString(id));
			tbMapChannel.getModel().setValueAt("Stopped", rIndex, 6);
			tbMapChannel.getModel().setValueAt("Run", rIndex, 7);
			//update database 
			String query = "UPDATE home_monitor_channel_mapping SET StatusSync = ? , Action = ? WHERE Id = ? ;";
			try {
				PreparedStatement preStm = MySqlAccess.getInstance().connect.prepareStatement(query);
				preStm.setInt(1, 0);
				preStm.setInt(2, 1);
				preStm.setInt(3, id);
				preStm.executeUpdate();
				isSuccess = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
}
