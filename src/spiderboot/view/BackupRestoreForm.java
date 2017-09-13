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

public class BackupRestoreForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackupRestoreForm frame = new BackupRestoreForm();
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
	public BackupRestoreForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BackupRestoreForm.class.getResource("/spiderboot/resources/resource/icon_32x32/upload_32x32.png")));
		setTitle("Backup restore Data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 553);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("Exit");
		button.setIcon(new ImageIcon(BackupRestoreForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(610, 465, 118, 38);
		getContentPane().add(button);
		
	}
}
