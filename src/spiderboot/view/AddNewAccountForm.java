package spiderboot.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AddNewAccountForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewAccountForm frame = new AddNewAccountForm();
					frame.pack();
				    frame.setLocationRelativeTo(null);
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
	public AddNewAccountForm() {
		setTitle("Add new Account");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddNewAccountForm.class.getResource("/spiderboot/resources/resource/icon_32x32/add_32x32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 518, 275);
		contentPane.add(panel);
		
		JLabel label = new JLabel("App Name");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label.setBounds(10, 11, 75, 35);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(97, 11, 406, 35);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Client ID");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_1.setBounds(10, 68, 75, 35);
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(97, 68, 406, 35);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("Client Secret");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_2.setBounds(10, 129, 75, 35);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(97, 129, 406, 35);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("API Key");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		label_3.setBounds(10, 190, 75, 35);
		panel.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(97, 190, 406, 35);
		panel.add(textField_3);
		
		JButton button = new JButton("OK");
		button.setIcon(new ImageIcon(AddNewAccountForm.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(269, 297, 118, 38);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Exit");
		button_1.setIcon(new ImageIcon(AddNewAccountForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button_1.setBounds(410, 297, 118, 38);
		contentPane.add(button_1);
	}

}
