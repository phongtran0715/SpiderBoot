package spiderboot.view;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import spiderboot.helper.SearchExecute;

public class SearchForm extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKeyword;
	private JTextField txtChannelId;
	private JTextField txtPlaylistId;
	private JTextField txtMaxResult;
	private JTextField txtPublishBefore;
	private JTextField txtPublishAfter;
	private JTextField txtRegionCode;
	private JTable table;
	public DefaultTableModel tbResultMode = new DefaultTableModel();

	public SearchForm() {
		//setMaximumSize(new Dimension(500, 500));
		initialize();
	}

	private void initialize() {
		setTitle("Search");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchForm.class.getResource("/spiderboot/resources/resource/icon_32x32/search_32x32.png")));
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setBounds(100, 100, 1140, 800);
		contentPane = new JPanel();
		contentPane.setAlignmentX(1.0f);
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

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
		pnSearchTerm.setBounds(5, 5, 398, 235);
		contentPane.add(pnSearchTerm);
		pnSearchTerm.setLayout(null);

		JLabel lblNewLabel = new JLabel("Keyword");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 21, 84, 24);
		pnSearchTerm.add(lblNewLabel);

		txtKeyword = new JTextField();
		txtKeyword.setBounds(8, 47, 372, 33);
		pnSearchTerm.add(txtKeyword);
		txtKeyword.setColumns(10);

		JLabel lblChannelId = new JLabel("Channel ID");
		lblChannelId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblChannelId.setBounds(12, 91, 84, 24);
		pnSearchTerm.add(lblChannelId);

		txtChannelId = new JTextField();
		txtChannelId.setColumns(10);
		txtChannelId.setBounds(10, 117, 370, 33);
		pnSearchTerm.add(txtChannelId);

		JLabel lblPlaylistId = new JLabel("Playlist ID");
		lblPlaylistId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPlaylistId.setBounds(12, 161, 84, 24);
		pnSearchTerm.add(lblPlaylistId);

		txtPlaylistId = new JTextField();
		txtPlaylistId.setColumns(10);
		txtPlaylistId.setBounds(10, 187, 372, 33);
		pnSearchTerm.add(txtPlaylistId);

		JPanel pnResult = new JPanel();
		pnResult.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Result", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnResult.setBounds(5, 241, 1123, 475);
		contentPane.add(pnResult);
		pnResult.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 15, 1109, 456);
		pnResult.add(scrollPane);

		tbResultMode = new DefaultTableModel(new Object[][] {
		},new String[] {
				"STT", "Video ID", "Title", "Thumbnail"
		});

		table = new JTable(tbResultMode) {
			private static final long serialVersionUID = 1L;

			//			public boolean isCellEditable(int row, int column) {
			//				return false;
			//			}
		};
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel pnSearchOption = new JPanel();
		pnSearchOption.setBounds(415, 5, 713, 235);
		contentPane.add(pnSearchOption);
		pnSearchOption.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		pnSearchOption.setBorder(new TitledBorder(null, "Search Option", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnSearchOption.setLayout(null);

		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblLanguage.setBounds(12, 21, 84, 24);
		pnSearchOption.add(lblLanguage);

		JLabel lblDefinition = new JLabel("Definition");
		lblDefinition.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDefinition.setBounds(189, 21, 84, 24);
		pnSearchOption.add(lblDefinition);

		JLabel lblDimension = new JLabel("Dimension");
		lblDimension.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDimension.setBounds(368, 27, 84, 24);
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
		txtMaxResult.setBounds(12, 117, 150, 33);
		pnSearchOption.add(txtMaxResult);

		JLabel lblPublishBefore = new JLabel("Publish Before");
		lblPublishBefore.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPublishBefore.setBounds(191, 91, 84, 24);
		pnSearchOption.add(lblPublishBefore);

		txtPublishBefore = new JTextField();
		txtPublishBefore.setColumns(10);
		txtPublishBefore.setBounds(189, 117, 150, 33);
		pnSearchOption.add(txtPublishBefore);

		JLabel lblPublishAfter = new JLabel("Publish After");
		lblPublishAfter.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblPublishAfter.setBounds(550, 27, 84, 24);
		pnSearchOption.add(lblPublishAfter);

		txtPublishAfter = new JTextField();
		txtPublishAfter.setColumns(10);
		txtPublishAfter.setBounds(366, 117, 150, 33);
		pnSearchOption.add(txtPublishAfter);

		JLabel lblLicense = new JLabel("License");
		lblLicense.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblLicense.setBounds(550, 91, 84, 24);
		pnSearchOption.add(lblLicense);

		JLabel lblRegionCode = new JLabel("Region Code");
		lblRegionCode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblRegionCode.setBounds(368, 91, 84, 24);
		pnSearchOption.add(lblRegionCode);

		txtRegionCode = new JTextField();
		txtRegionCode.setColumns(10);
		txtRegionCode.setBounds(1206, 117, 200, 33);
		pnSearchOption.add(txtRegionCode);

		JComboBox cbLanguage = new JComboBox();
		cbLanguage.setBounds(10, 47, 150, 33);
		pnSearchOption.add(cbLanguage);

		JComboBox cbDefinition = new JComboBox();
		cbDefinition.setBounds(189, 53, 150, 33);
		pnSearchOption.add(cbDefinition);

		JComboBox cbDimension = new JComboBox();
		cbDimension.setBounds(368, 53, 150, 33);
		pnSearchOption.add(cbDimension);

		JComboBox cbDuration = new JComboBox();
		cbDuration.setBounds(550, 53, 150, 33);
		pnSearchOption.add(cbDuration);

		JComboBox cbOrderBy = new JComboBox();
		cbOrderBy.setBounds(1206, 47, 202, 33);
		pnSearchOption.add(cbOrderBy);

		JComboBox cbLicense = new JComboBox();
		cbLicense.setBounds(550, 117, 150, 33);
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
					SearchExecute searchExe = new SearchExecute(keyWork);
					Thread searchThread = new Thread(searchExe);
					searchThread.start();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(SearchForm.class.getResource("/spiderboot/resources/resource/icon_32x32/search_32x32.png")));
		btnNewButton.setBounds(12, 190, 124, 33);
		pnSearchOption.add(btnNewButton);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(SearchForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.setBounds(1004, 728, 124, 33);
		contentPane.add(btnExit);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}
}
