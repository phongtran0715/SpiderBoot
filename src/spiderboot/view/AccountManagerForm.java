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

public class AccountManagerForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountManagerForm frame = new AccountManagerForm();
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
	public AccountManagerForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_32x32/user_32x32.png")));
		setTitle("Account Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 553);
		getContentPane().setLayout(null);
		
		JTabbedPane pnAccountManager = new JTabbedPane(JTabbedPane.TOP);
		pnAccountManager.setBounds(10, 11, 718, 492);
		getContentPane().add(pnAccountManager);
		
		JPanel pnSpiderApp = new JPanel();
		pnAccountManager.addTab("SpiderBoot Account", new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/user_16x16.png")), pnSpiderApp, null);
		pnSpiderApp.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Account List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 693, 391);
		pnSpiderApp.add(panel_1);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		table_1.setBackground(SystemColor.inactiveCaption);
		panel_1.add(table_1);
		
		JButton button = new JButton("Add new");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewAccountForm addNewAccFrm =  new AddNewAccountForm();
				addNewAccFrm.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(10, 413, 118, 38);
		pnSpiderApp.add(button);
		
		JButton button_1 = new JButton("Edit");
		button_1.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/edit_16x16.png")));
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_1.setBounds(170, 413, 118, 38);
		pnSpiderApp.add(button_1);
		
		JButton button_2 = new JButton("Delete");
		button_2.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/trash_16x16.png")));
		button_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_2.setBounds(334, 413, 118, 38);
		pnSpiderApp.add(button_2);
		
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
		panel.setBounds(10, 11, 693, 391);
		pnGoogleApp.add(panel);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setBackground(SystemColor.inactiveCaption);
		panel.add(table);
		
		JButton btnNewButton = new JButton("Add new");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewAccountForm addNewAccFrm =  new AddNewAccountForm();
				addNewAccFrm.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/add_16x16.png")));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnNewButton.setBounds(10, 413, 118, 38);
		pnGoogleApp.add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/edit_16x16.png")));
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnEdit.setBounds(170, 413, 118, 38);
		pnGoogleApp.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(AccountManagerForm.class.getResource("/spiderboot/resources/resource/icon_16x16/trash_16x16.png")));
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnDelete.setBounds(334, 413, 118, 38);
		pnGoogleApp.add(btnDelete);
		
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
}
