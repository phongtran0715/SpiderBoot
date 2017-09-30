package spiderboot.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BackupRestoreForm extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public BackupRestoreForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BackupRestoreForm.class.getResource("/spiderboot/resources/resource/icon_32x32/upload_32x32.png")));
		setTitle("Backup restore Data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 553);
		getContentPane().setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);
		
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
