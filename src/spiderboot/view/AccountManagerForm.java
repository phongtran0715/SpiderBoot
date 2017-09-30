package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import spiderboot.databaseconnection.MySqlAccess;

public class AccountManagerForm extends JFrame {
	private static final long serialVersionUID = 1L;
	JTabbedPane pnAccountManager;
	private JTable tbGoogleApp;
	public DefaultTableModel tbGgAppMode = new DefaultTableModel();
	public DefaultTableModel tbSpiderMode = new DefaultTableModel();
	private JTable tbSpiderHome;

	/**
	 * Create the frame.
	 */
	public AccountManagerForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_32x32/user_32x32.png")));
		setTitle("Account Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 553);
		getContentPane().setLayout(null);
		
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		pnAccountManager = new JTabbedPane(JTabbedPane.TOP);
		pnAccountManager.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int tabIndex = pnAccountManager.getSelectedIndex();
				if(tabIndex == 0){ //Spider Boot Account
					loadSpiderBootAccount();
				}else if(tabIndex == 1){ //Google Account Manager
					loadGoogleAppAccount();
				}
			}
		});
		pnAccountManager.setBounds(10, 11, 718, 492);
		getContentPane().add(pnAccountManager);

		JPanel pnSpiderApp = new JPanel();
		pnAccountManager.addTab("SpiderBoot Account", new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/user_16x16.png")), pnSpiderApp, null);
		pnSpiderApp.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Account List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 0, 703, 402);
		pnSpiderApp.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 21, 683, 370);
		panel_1.add(scrollPane_1);

		tbSpiderHome = new JTable(tbSpiderMode);
		scrollPane_1.setViewportView(tbSpiderHome);
		tbSpiderHome.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbSpiderHome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tbSpiderHome.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(tbSpiderHome.getTableHeader(),BorderLayout.NORTH);

		JButton btnAddSpiderAcc = new JButton("Add new");
		btnAddSpiderAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewSpiderBootAccount newSpiderAccFrm = new AddNewSpiderBootAccount();
				newSpiderAccFrm.setModalityType(ModalityType.APPLICATION_MODAL);
				newSpiderAccFrm.setVisible(true);
				loadSpiderBootAccount();
			}
		});
		btnAddSpiderAcc.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		btnAddSpiderAcc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnAddSpiderAcc.setBounds(10, 413, 118, 38);
		pnSpiderApp.add(btnAddSpiderAcc);

		JButton btnEditSpiderAcc = new JButton("Edit");
		btnEditSpiderAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rIndex =  tbSpiderHome.getSelectedRow();
				if(rIndex == -1){
					JOptionPane.showMessageDialog(pnSpiderApp, "Please select one row to edit !");
					return;
				}else{
					int id = (Integer)tbSpiderHome.getValueAt(rIndex, tbSpiderHome.getColumn("Id").getModelIndex());
					String userName = (String)tbSpiderHome.getValueAt(rIndex, tbSpiderHome.getColumn("UserName").getModelIndex());
					String email = (String)tbSpiderHome.getValueAt(rIndex, tbSpiderHome.getColumn("Email").getModelIndex());
					String password = (String)tbSpiderHome.getValueAt(rIndex, tbSpiderHome.getColumn("Password").getModelIndex());
					ModifySpiderBootAccount modSpiderAccFrm = new ModifySpiderBootAccount(id, userName, password, email);
					modSpiderAccFrm.setModalityType(ModalityType.APPLICATION_MODAL);
					modSpiderAccFrm.setVisible(true);
					loadSpiderBootAccount();
				}
			}
		});
		btnEditSpiderAcc.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/edit_16x16.png")));
		btnEditSpiderAcc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnEditSpiderAcc.setBounds(170, 413, 118, 38);
		pnSpiderApp.add(btnEditSpiderAcc);

		JButton btnDeleteSpiderAcc = new JButton("Delete");
		btnDeleteSpiderAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tbSpiderHome.getSelectedRow();
				if(selectedRow == -1){
					return;
				}
				int result = JOptionPane.showConfirmDialog(pnAccountManager, "Are you sure to delete item?",
						"Question",JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION){
					int accId = (int) tbSpiderHome.getValueAt(selectedRow, 0);
					String query = "DELETE FROM spideraccount WHERE Id = ? ;";
					PreparedStatement preparedStm;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setInt(1, accId);
						preparedStm.executeUpdate();
						//reload jtable
						tbSpiderMode.removeRow(selectedRow);
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						JOptionPane.showInternalMessageDialog(pnAccountManager, "Delete item false! \n" + e.toString());
					}	
				}else{
					//do nothing
				}
			}
		});
		btnDeleteSpiderAcc.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/trash_16x16.png")));
		btnDeleteSpiderAcc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDeleteSpiderAcc.setBounds(334, 413, 118, 38);
		pnSpiderApp.add(btnDeleteSpiderAcc);

		JButton button_3 = new JButton("Exit");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_3.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_3.setBounds(585, 413, 118, 38);
		pnSpiderApp.add(button_3);

		JPanel pnGoogleApp = new JPanel();
		pnAccountManager.addTab("GoogleApp Account", new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/google-plus16x16.png")), pnGoogleApp, null);
		pnGoogleApp.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 703, 402);
		pnGoogleApp.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 683, 356);
		panel.add(scrollPane);

		tbGoogleApp = new JTable(tbGgAppMode){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
				return false;               
			};
		};
		tbGoogleApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					int rIndex = target.getSelectedRow();
					if(rIndex == -1){
						return;
					}else{
						int id = (Integer)tbGoogleApp.getValueAt(rIndex, tbGoogleApp.getColumn("Id").getModelIndex());
						String userName = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("UserName").getModelIndex());
						String api = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("Api").getModelIndex());
						String clientId = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("ClientId").getModelIndex());
						String clientSecret = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("ClientSecret").getModelIndex());
						String accountType = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("AccountType").getModelIndex());
						String password = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("Password").getModelIndex());
						String appName = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("AppName").getModelIndex());
						ModifyGoogleAccount modifyFrm = new ModifyGoogleAccount(id, userName, password, api, clientId, 
								clientSecret, accountType, appName);
						modifyFrm.setModalityType(ModalityType.APPLICATION_MODAL);
						modifyFrm.setVisible(true);
						loadGoogleAppAccount();	
					}
				}
			}
		});

		scrollPane.setViewportView(tbGoogleApp);
		tbGoogleApp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbGoogleApp.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbGoogleApp.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		JButton btnAddGoogleAcc = new JButton("Add new");
		btnAddGoogleAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewGoogleAccount addNewAccFrm = new AddNewGoogleAccount();
				addNewAccFrm.setModalityType(ModalityType.APPLICATION_MODAL);
				addNewAccFrm.setVisible(true);
				loadGoogleAppAccount();
			}
		});
		btnAddGoogleAcc.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		btnAddGoogleAcc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnAddGoogleAcc.setBounds(10, 413, 118, 38);
		pnGoogleApp.add(btnAddGoogleAcc);

		JButton btnEditGoogleAcc = new JButton("Edit");
		btnEditGoogleAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rIndex =  tbGoogleApp.getSelectedRow();
				if(rIndex == -1){
					JOptionPane.showMessageDialog(pnGoogleApp, "Please select one row to edit !");
					return;
				}else{
					int id = (Integer)tbGoogleApp.getValueAt(rIndex, tbGoogleApp.getColumn("Id").getModelIndex());
					String userName = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("UserName").getModelIndex());
					String api = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("Api").getModelIndex());
					String clientId = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("ClientId").getModelIndex());
					String clientSecret = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("ClientSecret").getModelIndex());
					String accountType = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("AccountType").getModelIndex());
					String password = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("Password").getModelIndex());
					String appName = (String)tbGgAppMode.getValueAt(rIndex, tbGoogleApp.getColumn("AppName").getModelIndex());
					ModifyGoogleAccount modifyFrm = new ModifyGoogleAccount(id, userName, password, api, clientId, 
							clientSecret, accountType, appName);
					modifyFrm.setModalityType(ModalityType.APPLICATION_MODAL);
					modifyFrm.setVisible(true);
					loadGoogleAppAccount();
				}
			}
		});
		btnEditGoogleAcc.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/edit_16x16.png")));
		btnEditGoogleAcc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnEditGoogleAcc.setBounds(170, 413, 118, 38);
		pnGoogleApp.add(btnEditGoogleAcc);

		JButton btnDeleteGoogleAcc = new JButton("Delete");
		btnDeleteGoogleAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = tbGoogleApp.getSelectedRow();
				if(selectedRow == -1){
					return;
				}
				int result = JOptionPane.showConfirmDialog(pnAccountManager, "Are you sure to delete item?",
						"Question",JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION){
					int accId = (int) tbGoogleApp.getValueAt(selectedRow, 0);
					String query = "DELETE FROM googleaccount WHERE Id = ? ;";
					PreparedStatement preparedStm;
					try {
						preparedStm = MySqlAccess.getInstance().connect.prepareStatement(query);
						preparedStm.setInt(1, accId);
						preparedStm.executeUpdate();
						//reload jtable
						//DefaultTableModel model = (DefaultTableModel)tbGoogleApp.getModel();
						tbGgAppMode.removeRow(selectedRow);
						//JOptionPane.showMessageDialog(pnAccountManager, "Delete item successfuly!");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showInternalMessageDialog(pnAccountManager, "Delete item false! \n" + e.toString());
					}	
				}else{
					//do nothing
				}
			}
		});
		btnDeleteGoogleAcc.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/trash_16x16.png")));
		btnDeleteGoogleAcc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDeleteGoogleAcc.setBounds(334, 413, 118, 38);
		pnGoogleApp.add(btnDeleteGoogleAcc);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(585, 413, 118, 38);
		pnGoogleApp.add(btnExit);
	}

	public void loadSpiderBootAccount(){
		tbSpiderMode = new DefaultTableModel();
		Statement stmt;
		try
		{
			String query = "SELECT Id, UserName, Password, Email FROM spideraccount WHERE Id != 0";
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
			tbSpiderMode.setDataVector(data, columnNames);
			tbSpiderHome.setModel(tbSpiderMode);
			//Hide password column
			tbSpiderHome.getColumnModel().getColumn(2).setMinWidth(0);
			tbSpiderHome.getColumnModel().getColumn(2).setMaxWidth(0);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void loadGoogleAppAccount(){
		Statement stmt;
		tbGgAppMode = new DefaultTableModel();
		try
		{
			String query = "SELECT Id, UserName, Password, Api, ClientId, ClientSecret, AccountType, AppName FROM googleaccount";
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
			tbGgAppMode.setDataVector(data, columnNames);
			tbGoogleApp.setModel(tbGgAppMode);
			//Hide password column
			tbGoogleApp.getColumnModel().getColumn(2).setMinWidth(0);
			tbGoogleApp.getColumnModel().getColumn(2).setMaxWidth(0);

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
