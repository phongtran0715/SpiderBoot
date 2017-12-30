package spiderboot.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

public class RenderForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtOutputVideo;
	private JTextField txtIntro;
	private JTextField txtOutro;
	private JTextField txtVideoBg;
	private JTextField txtFilterImg;
	private JTextField txtLogo;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField txtLocation;
	private JTextField txtSize;
	private JTextField textField_9;
	private JTextField textField_8;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RenderForm frame = new RenderForm();
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
	public RenderForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RenderForm.class.getResource("/spiderboot/resources/resource/icon_24x24/auto-flash_24x24.png")));
		setTitle("Spider Render Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//set center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2- getSize().width/2, dim.height/2- getSize().height/2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(240, 5, 349, 133);
		panel_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblInput = new JLabel("Input ");
		lblInput.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblInput.setBounds(10, 30, 50, 25);
		panel_1.add(lblInput);

		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblOutput.setBounds(10, 84, 50, 25);
		panel_1.add(lblOutput);

		txtOutputVideo = new JTextField();
		txtOutputVideo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtOutputVideo.setBounds(55, 85, 235, 25);
		panel_1.add(txtOutputVideo);
		txtOutputVideo.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "File in computer", "Import Link", "Import link from text file", "Get Link"}));
		comboBox.setBounds(55, 33, 178, 25);
		panel_1.add(comboBox);

		JButton btnOutput = new JButton("New button");
		btnOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Output video folder path");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) { 
					txtOutputVideo.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnOutput.setBounds(300, 85, 37, 25);
		panel_1.add(btnOutput);

		JButton btnAddFiles = new JButton("Add Files");
		btnAddFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("~"));
				chooser.setDialogTitle("Select video ");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setMultiSelectionEnabled(true);
				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) { 
					File[] listFile = chooser.getSelectedFiles();
					for (File file : listFile) {
						System.out.println(file.getPath().toString());
					}
				}
			}
		});
		btnAddFiles.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAddFiles.setBounds(243, 32, 100, 30);
		panel_1.add(btnAddFiles);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(240, 150, 349, 105);
		panel_2.setLayout(null);
		panel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_2);

		JComboBox cbConfig = new JComboBox();
		cbConfig.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbConfig.setBounds(55, 26, 236, 25);
		panel_2.add(cbConfig);

		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(RenderForm.class.getResource("/spiderboot/resources/resource/icon_16x16/checked_16x16.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSave.setBounds(10, 66, 100, 30);
		panel_2.add(btnSave);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(RenderForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnDelete.setBounds(129, 66, 100, 30);
		panel_2.add(btnDelete);

		JButton btnExport = new JButton("Export");
		btnExport.setIcon(new ImageIcon(RenderForm.class.getResource("/spiderboot/resources/resource/icon_16x16/export_16x16.png")));
		btnExport.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnExport.setBounds(245, 66, 100, 30);
		panel_2.add(btnExport);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(599, 5, 459, 250);
		panel_3.setLayout(null);
		panel_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Features", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_3);

		txtIntro = new JTextField();
		txtIntro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtIntro.setColumns(10);
		txtIntro.setBounds(115, 25, 280, 25);
		panel_3.add(txtIntro);

		JButton btnIntro = new JButton("New button");
		btnIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("~"));
				chooser.setDialogTitle("Intro video");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) { 
					txtIntro.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnIntro.setBounds(407, 25, 37, 25);
		panel_3.add(btnIntro);

		JCheckBox checkIntro = new JCheckBox("Intro");
		checkIntro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkIntro.setBounds(10, 25, 100, 25);
		panel_3.add(checkIntro);

		JCheckBox checkOutro = new JCheckBox("Outro");
		checkOutro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkOutro.setBounds(10, 60, 90, 25);
		panel_3.add(checkOutro);

		txtOutro = new JTextField();
		txtOutro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtOutro.setColumns(10);
		txtOutro.setBounds(115, 61, 280, 25);
		panel_3.add(txtOutro);

		JButton btnOutro = new JButton("New button");
		btnOutro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("~"));
				chooser.setDialogTitle("Outro video");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) { 
					txtOutro.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnOutro.setBounds(407, 62, 37, 25);
		panel_3.add(btnOutro);

		JCheckBox checkVideoBg = new JCheckBox("Video BG");
		checkVideoBg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkVideoBg.setBounds(10, 99, 90, 25);
		panel_3.add(checkVideoBg);

		txtVideoBg = new JTextField();
		txtVideoBg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtVideoBg.setColumns(10);
		txtVideoBg.setBounds(115, 97, 280, 25);
		panel_3.add(txtVideoBg);

		JButton btnVideoBg = new JButton("New button");
		btnVideoBg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("~"));
				chooser.setDialogTitle("Video background");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) { 
					txtVideoBg.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnVideoBg.setBounds(407, 98, 37, 25);
		panel_3.add(btnVideoBg);

		JCheckBox checkFilterImg = new JCheckBox("Filter Image");
		checkFilterImg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkFilterImg.setBounds(10, 138, 100, 25);
		panel_3.add(checkFilterImg);

		txtFilterImg = new JTextField();
		txtFilterImg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtFilterImg.setColumns(10);
		txtFilterImg.setBounds(115, 136, 280, 25);
		panel_3.add(txtFilterImg);

		JButton btnImgFilter = new JButton("New button");
		btnImgFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("~"));
				chooser.setDialogTitle("Filter Image");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) { 
					txtFilterImg.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		btnImgFilter.setBounds(407, 137, 37, 25);
		panel_3.add(btnImgFilter);

		JCheckBox checkLogo = new JCheckBox("Add Logo");
		checkLogo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkLogo.setBounds(10, 175, 90, 25);
		panel_3.add(checkLogo);

		txtLogo = new JTextField();
		txtLogo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtLogo.setColumns(10);
		txtLogo.setBounds(115, 173, 280, 25);
		panel_3.add(txtLogo);

		JButton txtAddLogo = new JButton("New button");
		txtAddLogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("~"));
				chooser.setDialogTitle("Logo Image");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) { 
					txtLogo.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		txtAddLogo.setBounds(407, 174, 37, 25);
		panel_3.add(txtAddLogo);

		JLabel lblLogoSize = new JLabel("Logo Size");
		lblLogoSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLogoSize.setBounds(15, 207, 65, 25);
		panel_3.add(lblLogoSize);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_6.setColumns(10);
		textField_6.setBounds(115, 209, 90, 25);
		panel_3.add(textField_6);

		JLabel lblLogoLocation = new JLabel("Logo Location");
		lblLogoLocation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLogoLocation.setBounds(213, 207, 87, 25);
		panel_3.add(lblLogoLocation);

		textField_7 = new JTextField();
		textField_7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_7.setColumns(10);
		textField_7.setBounds(305, 210, 90, 25);
		panel_3.add(textField_7);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1069, 5, 319, 250);
		panel_4.setLayout(null);
		panel_4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Render Video", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_4);

		JLabel lblVideoSize = new JLabel("Video Size");
		lblVideoSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblVideoSize.setBounds(10, 27, 90, 25);
		panel_4.add(lblVideoSize);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(122, 28, 175, 25);
		panel_4.add(textField_1);

		JLabel lblVideoBitrate = new JLabel("Video Bitrate");
		lblVideoBitrate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblVideoBitrate.setBounds(10, 63, 90, 25);
		panel_4.add(lblVideoBitrate);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(122, 64, 175, 25);
		panel_4.add(textField_2);

		JLabel lblFps = new JLabel("FPS");
		lblFps.setHorizontalAlignment(SwingConstants.CENTER);
		lblFps.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFps.setBounds(10, 99, 90, 25);
		panel_4.add(lblFps);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(122, 100, 175, 25);
		panel_4.add(textField_3);

		JLabel lblAudioBitrate = new JLabel("Audio Bitrate");
		lblAudioBitrate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAudioBitrate.setBounds(10, 135, 90, 25);
		panel_4.add(lblAudioBitrate);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(122, 136, 175, 25);
		panel_4.add(textField_4);

		JLabel lblSampleBitrate = new JLabel("Sample Bitrate");
		lblSampleBitrate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSampleBitrate.setBounds(10, 170, 100, 25);
		panel_4.add(lblSampleBitrate);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(122, 171, 175, 25);
		panel_4.add(textField_5);

		JButton btnPrievew = new JButton("Preview");
		btnPrievew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//				try {
				//					//Process p = Runtime.getRuntime().exec("ffplay /home/phongtran0715/Downloads/Document/test_ffmpeg/input.mp4");
				//				} catch (IOException e) {
				//					// TODO Auto-generated catch block
				//					e.printStackTrace();
				//				}
				FFmpeg ffmpeg = null;
				FFprobe ffprobe = null;
				try {
					ffmpeg =  new FFmpeg();
					ffprobe = new FFprobe();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(ffmpeg != null)
				{
					FFmpegBuilder builder = new FFmpegBuilder();
					builder.setInput("/home/phongtran0715/Downloads/Document/test_ffmpeg/input.mp4");
					builder.setFormat("mp4");
					builder.addExtraArgs("-f", "mpegts");
					builder.addExtraArgs("-", "|");
					builder.addExtraArgs("ffplay", "-");
					FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
					executor.createJob(builder).run();
				}
			}
		});
		btnPrievew.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnPrievew.setBounds(10, 210, 90, 30);
		panel_4.add(btnPrievew);

		JButton btnStartAll = new JButton("Start All");
		btnStartAll.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnStartAll.setBounds(115, 210, 90, 30);
		panel_4.add(btnStartAll);

		JButton btnStopAll = new JButton("Stop All");
		btnStopAll.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnStopAll.setBounds(217, 210, 90, 30);
		panel_4.add(btnStopAll);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(240, 268, 1148, 357);
		panel_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Video Lists", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 340, 1124, -315);
		panel_5.add(scrollPane);
		String[] columnNames = {"STT",
				"Input Video",
				"Output Video",
		"Status"};
		Object[][] data = {
				{ "Row1/1", "Row1/2", "Row1/3", "Row1/4" }, 
				{ "Row1/1", "Row1/2", "Row1/3", "Row1/4" }, 
				{ "Row1/1", "Row1/2", "Row1/3", "Row1/4" }
		};
		table = new JTable(data, columnNames);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setBorder(new TitledBorder(null, "abc", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 220, 611);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		contentPane.add(tabbedPane);

		JPanel pnVideoOption = new JPanel();
		tabbedPane.addTab("Video Option", null, pnVideoOption, null);
		pnVideoOption.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_6.setBorder(new TitledBorder(null, "Crop", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(5, 5, 205, 122);
		pnVideoOption.add(panel_6);
		panel_6.setLayout(null);

		JCheckBox chckbxEnable = new JCheckBox("Enable");
		chckbxEnable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxEnable.setBounds(6, 18, 78, 25);
		panel_6.add(chckbxEnable);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLocation.setBounds(6, 80, 70, 25);
		panel_6.add(lblLocation);

		txtLocation = new JTextField();
		txtLocation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtLocation.setColumns(10);
		txtLocation.setBounds(73, 80, 122, 25);
		panel_6.add(txtLocation);

		JLabel lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSize.setBounds(6, 45, 50, 25);
		panel_6.add(lblSize);

		txtSize = new JTextField();
		txtSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtSize.setColumns(10);
		txtSize.setBounds(73, 45, 122, 25);
		panel_6.add(txtSize);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Scale", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(5, 129, 205, 85);
		pnVideoOption.add(panel_7);

		JCheckBox checkBox = new JCheckBox("Enable");
		checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkBox.setBounds(6, 18, 78, 25);
		panel_7.add(checkBox);

		JLabel label_1 = new JLabel("Size");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_1.setBounds(6, 45, 50, 25);
		panel_7.add(label_1);

		textField_9 = new JTextField();
		textField_9.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_9.setColumns(10);
		textField_9.setBounds(73, 45, 122, 25);
		panel_7.add(textField_9);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Blur", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_8.setBounds(5, 218, 205, 85);
		pnVideoOption.add(panel_8);

		JCheckBox checkBox_1 = new JCheckBox("Enable");
		checkBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkBox_1.setBounds(6, 18, 78, 25);
		panel_8.add(checkBox_1);

		JLabel lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLevel.setBounds(6, 45, 50, 25);
		panel_8.add(lblLevel);

		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner.setBounds(73, 45, 122, 25);
		panel_8.add(spinner);

		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Speed", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setBounds(5, 309, 205, 85);
		pnVideoOption.add(panel_9);

		JCheckBox checkBox_2 = new JCheckBox("Enable");
		checkBox_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkBox_2.setBounds(6, 18, 78, 25);
		panel_9.add(checkBox_2);

		JLabel label = new JLabel("Level");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label.setBounds(6, 45, 50, 25);
		panel_9.add(label);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_1.setBounds(73, 45, 122, 25);
		panel_9.add(spinner_1);

		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_10.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Miror", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_10.setBounds(5, 396, 205, 85);
		pnVideoOption.add(panel_10);

		JCheckBox checkBox_3 = new JCheckBox("Enable");
		checkBox_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkBox_3.setBounds(6, 18, 78, 25);
		panel_10.add(checkBox_3);

		JCheckBox chckbxHflip = new JCheckBox("HFlip");
		chckbxHflip.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxHflip.setBounds(27, 46, 78, 25);
		panel_10.add(chckbxHflip);

		JCheckBox chckbxVflip = new JCheckBox("VFlip");
		chckbxVflip.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxVflip.setBounds(121, 48, 78, 25);
		panel_10.add(chckbxVflip);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_11.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cut Video", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_11.setBounds(5, 492, 205, 85);
		pnVideoOption.add(panel_11);

		JCheckBox checkBox_4 = new JCheckBox("Enable");
		checkBox_4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkBox_4.setBounds(6, 18, 78, 25);
		panel_11.add(checkBox_4);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTime.setBounds(6, 50, 50, 25);
		panel_11.add(lblTime);

		textField_8 = new JTextField();
		textField_8.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textField_8.setColumns(10);
		textField_8.setBounds(73, 50, 122, 25);
		panel_11.add(textField_8);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Audio Option", null, panel, null);
		panel.setLayout(null);

		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_12.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Volume", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_12.setBounds(5, 5, 205, 88);
		panel.add(panel_12);

		JCheckBox chckbxVolumeAudio = new JCheckBox("Volume Audio");
		chckbxVolumeAudio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxVolumeAudio.setBounds(6, 18, 110, 25);
		panel_12.add(chckbxVolumeAudio);

		JLabel label_2 = new JLabel("Level");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		label_2.setBounds(6, 50, 50, 25);
		panel_12.add(label_2);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_2.setBounds(73, 50, 122, 25);
		panel_12.add(spinner_2);

		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_13.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Echo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_13.setBounds(5, 104, 205, 203);
		panel.add(panel_13);

		JCheckBox chckbxEnable_1 = new JCheckBox("Enable");
		chckbxEnable_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		chckbxEnable_1.setBounds(6, 18, 101, 25);
		panel_13.add(chckbxEnable_1);

		JLabel lblInGain = new JLabel("In Gain");
		lblInGain.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblInGain.setBounds(6, 50, 50, 25);
		panel_13.add(lblInGain);

		JSpinner spinner_3 = new JSpinner();
		spinner_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_3.setBounds(73, 50, 122, 25);
		panel_13.add(spinner_3);

		JLabel lblOutGain = new JLabel("Out Gain");
		lblOutGain.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblOutGain.setBounds(6, 86, 70, 25);
		panel_13.add(lblOutGain);

		JSpinner spinner_4 = new JSpinner();
		spinner_4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_4.setBounds(73, 86, 122, 25);
		panel_13.add(spinner_4);

		JLabel lblDelay = new JLabel("Delay");
		lblDelay.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDelay.setBounds(6, 122, 50, 25);
		panel_13.add(lblDelay);

		JSpinner spinner_5 = new JSpinner();
		spinner_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_5.setBounds(73, 122, 122, 25);
		panel_13.add(spinner_5);

		JLabel lblDecays = new JLabel("Decays");
		lblDecays.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDecays.setBounds(6, 158, 50, 25);
		panel_13.add(lblDecays);

		JSpinner spinner_6 = new JSpinner();
		spinner_6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_6.setBounds(73, 158, 122, 25);
		panel_13.add(spinner_6);

		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_14.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Equalizer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_14.setBounds(5, 313, 205, 203);
		panel.add(panel_14);

		JCheckBox checkBox_5 = new JCheckBox("Enable");
		checkBox_5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		checkBox_5.setBounds(6, 18, 101, 25);
		panel_14.add(checkBox_5);

		JLabel lblFrequency = new JLabel("Frequency");
		lblFrequency.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFrequency.setBounds(6, 50, 70, 25);
		panel_14.add(lblFrequency);

		JSpinner spinner_7 = new JSpinner();
		spinner_7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_7.setBounds(73, 50, 122, 25);
		panel_14.add(spinner_7);

		JLabel lblidth = new JLabel("Width");
		lblidth.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblidth.setBounds(6, 86, 50, 25);
		panel_14.add(lblidth);

		JSpinner spinner_8 = new JSpinner();
		spinner_8.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_8.setBounds(73, 86, 122, 25);
		panel_14.add(spinner_8);

		JLabel lblGain = new JLabel("Gain");
		lblGain.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblGain.setBounds(6, 122, 50, 25);
		panel_14.add(lblGain);

		JSpinner spinner_9 = new JSpinner();
		spinner_9.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_9.setBounds(73, 122, 122, 25);
		panel_14.add(spinner_9);

		JLabel lblFilterType = new JLabel("Filter Type");
		lblFilterType.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFilterType.setBounds(6, 158, 70, 25);
		panel_14.add(lblFilterType);

		JSpinner spinner_10 = new JSpinner();
		spinner_10.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		spinner_10.setBounds(73, 158, 122, 25);
		panel_14.add(spinner_10);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(RenderForm.class.getResource("/spiderboot/resources/resource/icon_16x16/delete_16x16.png")));
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnExit.setBounds(1298, 637, 90, 30);
		contentPane.add(btnExit);
	}
}
