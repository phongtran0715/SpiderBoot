package spiderboot.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class LoggingForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public LoggingForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoggingForm.class.getResource("/spiderboot/resources/resource/icon_32x32/user_32x32.png")));
		setTitle("System Logging");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 553);
		getContentPane().setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);
		
		JButton button = new JButton("Exit");
		button.setIcon(new ImageIcon(LoggingForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(610, 465, 118, 38);
		getContentPane().add(button);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Quick Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 0, 191, 151);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(201, 0, 525, 151);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(5, 148, 723, 299);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 101, 177, -79);
		panel_2.add(table);
		
	}
}
