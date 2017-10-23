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
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import spiderboot.databaseconnection.MySqlAccess;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class BackupRestoreForm extends JFrame {
	private static final long serialVersionUID = 1L;
	public DefaultTableModel tbBackupInfoModel = new DefaultTableModel();
	private JTable tbBackup;

	public BackupRestoreForm() {
		initialize();
		loadLogging();
	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BackupRestoreForm.class.getResource("/spiderboot/resources/resource/icon_32x32/upload_32x32.png")));
		setTitle("Backup restore Data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 485);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JButton button = new JButton("Exit");
		button.setBounds(610, 399, 118, 38);
		button.setIcon(new ImageIcon(BackupRestoreForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().setLayout(null);
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		getContentPane().add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(5, 133, 730, 255);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 730, 254);
		panel_1.add(scrollPane);
		
		tbBackup = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(tbBackup);
		tbBackup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbBackup.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tbBackup.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tbBackup.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		panel_1.add(tbBackup.getTableHeader(),BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 11, 730, 120);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnBackup = new JButton("Backup");
		btnBackup.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnBackup.setBounds(10, 41, 118, 38);
		panel.add(btnBackup);
		
		JButton btnRestore = new JButton("Restore");
		btnRestore.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnRestore.setBounds(164, 41, 118, 38);
		panel.add(btnRestore);
		
	}

	private void loadLogging() {
		tbBackupInfoModel = new DefaultTableModel();
		Statement stmt;
		try
		{
			String query = "SELECT * FROM backup_info";
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
			tbBackupInfoModel.setDataVector(data, columnNames);
			tbBackup.setModel(tbBackupInfoModel);
			//Hide id column
			tbBackup.getColumnModel().getColumn(0).setMinWidth(0);
			tbBackup.getColumnModel().getColumn(0).setMaxWidth(0);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
