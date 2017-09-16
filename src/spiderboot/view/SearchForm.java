package spiderboot.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.google.api.services.samples.youtube.cmdline.data.Search;

public class SearchForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKeyword;
	private JTextField txtChannelId;
	private JTextField txtPlaylistId;
	private JTextField txtMaxResult;
	private JTextField txtPublishBefore;
	private JTextField txtPublishAfter;
	private JTextField txtRegionCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchForm frame = new SearchForm();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public SearchForm() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setType(Type.POPUP);
		setTitle("Search");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchForm.class.getResource("/spiderboot/resources/resource/icon_32x32/search_32x32.png")));
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setBounds(100, 100, 792, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setIcon(new ImageIcon(SearchForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		button.setBounds(1780, 968, 118, 38);
		contentPane.add(button);
		
		JPanel pnSearchTerm = new JPanel();
		pnSearchTerm.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		pnSearchTerm.setBorder(new TitledBorder(null, "Search Term", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnSearchTerm.setBounds(10, 11, 452, 232);
		contentPane.add(pnSearchTerm);
		pnSearchTerm.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Keyword");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 21, 84, 24);
		pnSearchTerm.add(lblNewLabel);
		
		txtKeyword = new JTextField();
		txtKeyword.setBounds(8, 47, 434, 33);
		pnSearchTerm.add(txtKeyword);
		txtKeyword.setColumns(10);
		
		JLabel lblChannelId = new JLabel("Channel ID");
		lblChannelId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblChannelId.setBounds(12, 91, 84, 24);
		pnSearchTerm.add(lblChannelId);
		
		txtChannelId = new JTextField();
		txtChannelId.setColumns(10);
		txtChannelId.setBounds(10, 117, 434, 33);
		pnSearchTerm.add(txtChannelId);
		
		JLabel lblPlaylistId = new JLabel("Playlist ID");
		lblPlaylistId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPlaylistId.setBounds(12, 161, 84, 24);
		pnSearchTerm.add(lblPlaylistId);
		
		txtPlaylistId = new JTextField();
		txtPlaylistId.setColumns(10);
		txtPlaylistId.setBounds(10, 187, 434, 33);
		pnSearchTerm.add(txtPlaylistId);
		
		JPanel pnSearchOption = new JPanel();
		pnSearchOption.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		pnSearchOption.setBorder(new TitledBorder(null, "Search Option", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnSearchOption.setBounds(472, 11, 1426, 232);
		contentPane.add(pnSearchOption);
		pnSearchOption.setLayout(null);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblLanguage.setBounds(12, 21, 84, 24);
		pnSearchOption.add(lblLanguage);
		
		JLabel lblDefinition = new JLabel("Definition");
		lblDefinition.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDefinition.setBounds(294, 21, 84, 24);
		pnSearchOption.add(lblDefinition);
		
		JLabel lblDimension = new JLabel("Dimension");
		lblDimension.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDimension.setBounds(591, 21, 84, 24);
		pnSearchOption.add(lblDimension);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDuration.setBounds(900, 21, 84, 24);
		pnSearchOption.add(lblDuration);
		
		JLabel lblOrderBy = new JLabel("Order By");
		lblOrderBy.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblOrderBy.setBounds(1206, 21, 84, 24);
		pnSearchOption.add(lblOrderBy);
		
		JLabel lblMaxResult = new JLabel("Max result");
		lblMaxResult.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblMaxResult.setBounds(14, 91, 84, 24);
		pnSearchOption.add(lblMaxResult);
		
		txtMaxResult = new JTextField();
		txtMaxResult.setColumns(10);
		txtMaxResult.setBounds(12, 117, 200, 33);
		pnSearchOption.add(txtMaxResult);
		
		JLabel lblPublishBefore = new JLabel("Publish Before");
		lblPublishBefore.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPublishBefore.setBounds(296, 91, 84, 24);
		pnSearchOption.add(lblPublishBefore);
		
		txtPublishBefore = new JTextField();
		txtPublishBefore.setColumns(10);
		txtPublishBefore.setBounds(294, 117, 200, 33);
		pnSearchOption.add(txtPublishBefore);
		
		JLabel lblPublishAfter = new JLabel("Publish After");
		lblPublishAfter.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPublishAfter.setBounds(591, 91, 84, 24);
		pnSearchOption.add(lblPublishAfter);
		
		txtPublishAfter = new JTextField();
		txtPublishAfter.setColumns(10);
		txtPublishAfter.setBounds(589, 117, 200, 33);
		pnSearchOption.add(txtPublishAfter);
		
		JLabel lblLicense = new JLabel("License");
		lblLicense.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblLicense.setBounds(900, 91, 84, 24);
		pnSearchOption.add(lblLicense);
		
		JLabel lblRegionCode = new JLabel("Region Code");
		lblRegionCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblRegionCode.setBounds(1208, 91, 84, 24);
		pnSearchOption.add(lblRegionCode);
		
		txtRegionCode = new JTextField();
		txtRegionCode.setColumns(10);
		txtRegionCode.setBounds(1206, 117, 200, 33);
		pnSearchOption.add(txtRegionCode);
		
		JComboBox cbLanguage = new JComboBox();
		cbLanguage.setBounds(10, 47, 202, 33);
		pnSearchOption.add(cbLanguage);
		
		JComboBox cbDefinition = new JComboBox();
		cbDefinition.setBounds(294, 53, 202, 33);
		pnSearchOption.add(cbDefinition);
		
		JComboBox cbDimension = new JComboBox();
		cbDimension.setBounds(591, 47, 202, 33);
		pnSearchOption.add(cbDimension);
		
		JComboBox cbDuration = new JComboBox();
		cbDuration.setBounds(896, 47, 202, 33);
		pnSearchOption.add(cbDuration);
		
		JComboBox cbOrderBy = new JComboBox();
		cbOrderBy.setBounds(1206, 47, 202, 33);
		pnSearchOption.add(cbOrderBy);
		
		JComboBox cbLicense = new JComboBox();
		cbLicense.setBounds(896, 123, 202, 33);
		pnSearchOption.add(cbLicense);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String keyWork = txtKeyword.getText().trim();
				if(keyWork.equals("")){
					JOptionPane.showMessageDialog(contentPane, "You must put keywork to search",
								"Error", JOptionPane.OK_OPTION);
					return;
				}else{
					Search search = new Search();
					search.executeQuery(txtKeyword.getText().trim());
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(SearchForm.class.getResource("/spiderboot/resources/resource/icon_32x32/search_32x32.png")));
		btnNewButton.setBounds(12, 188, 124, 33);
		pnSearchOption.add(btnNewButton);
		
		JPanel pnResult = new JPanel();
		pnResult.setBorder(new TitledBorder(null, "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnResult.setBounds(10, 254, 1888, 708);
		contentPane.add(pnResult);
		pnResult.setLayout(null);
	}
}
