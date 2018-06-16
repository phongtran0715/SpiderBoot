/**
 * NetXMS - open source network management system
 * Copyright (C) 2003-2009 Victor Kirhenshtein
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.netxms.ui.eclipse.spidermanager.dialogs;

import java.io.IOException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.netxms.client.NXCException;
import org.netxms.client.NXCSession;
import org.netxms.ui.eclipse.shared.ConsoleSharedData;
import org.spider.base.SpiderCodes;
import org.spider.client.ClusterObject;
import org.spider.client.HomeChannelObject;
import org.spider.client.MonitorChannelObject;
import org.spider.client.SpiderDefine;
import org.spider.client.SpiderDefine.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Button;

import spider.org.eclipse.wb.swt.ResourceManager;

public class CreateMappingChannelDialog extends Dialog {
	private Text txtTimeSync;
	private Combo cbHome;
	private Combo cbMonitor;
	private Combo cbStatus;
	private Combo cbDownload;
	private Label lblDownloadCluster;
	private Label lblRenderCid;
	private Combo cbRender;
	private Label lblUploadCid;
	private Combo cbUpload;
	Link linkHomeChanel;
	Link linkMonitorChanel;
	Object [] cHomeObject;
	Object [] cMoniorObject;
	Object [] downloadClusters;
	Object [] renderClusters;
	Object [] uploadClusters;
	private NXCSession session;
	private TabFolder tabFolder;
	private TabItem tbtmUploadConfig;
	private TabItem tbtmMappingConfig;
	private Group grpAbc;
	private Text txtTitle;
	private Label lblDescTemplate;
	private Button cbDesc;
	private Label lblTagsTemplate;
	private Button cbTag;
	private Text txtDesc;
	private Text txtTags;
	private TabItem tbtmRenderConfig;
	private Group grpRender;
	private Text txtVideoIntro;
	private Button cbIntro;
	private Label lblVideoOutro;
	private Text txtVideoOutro;
	private Button cbOutro;
	private Label lblVideoLogo;
	private Text txtLogo;
	private Button cbLogo;
	Button cbTitle;
	private Label lblLogoPosition;
	private Text txtLogoPosX;
	private Text txtLogoPosY;
	private Label label;

	SpiderDefine spiderDefine = new SpiderDefine();
	MappingConfig mappingConfig = spiderDefine.new MappingConfig();
	RenderConfig renderConfig = spiderDefine.new RenderConfig();
	UploadConfig uploadConfig = spiderDefine.new UploadConfig();

	public CreateMappingChannelDialog(Shell parentShell) {
		super(parentShell);
		session = ConsoleSharedData.getSession();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		GridData gridData;
		gridData = new GridData(GridData.VERTICAL_ALIGN_END);
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 4;
		gridData = new GridData(GridData.VERTICAL_ALIGN_END);
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		dialogArea.setLayout(null);

		tabFolder = new TabFolder(dialogArea, SWT.NONE);
		tabFolder.setBounds(10, 10, 509, 414);

		tbtmMappingConfig = new TabItem(tabFolder, SWT.NONE);
		tbtmMappingConfig.setImage(ResourceManager.getPluginImage("org.spider.ui.eclipse.spidermanager", "icons/settings_16x16.png"));
		tbtmMappingConfig.setText("Mapping Config");

		Group grpCreateNewAccount = new Group(tabFolder, SWT.NONE);
		tbtmMappingConfig.setControl(grpCreateNewAccount);
		grpCreateNewAccount.setText("Create new mapping channel");

		Label lblChannelId = new Label(grpCreateNewAccount, SWT.NONE);
		lblChannelId.setAlignment(SWT.RIGHT);
		lblChannelId.setText("C Home ID");
		lblChannelId.setBounds(10, 31, 109, 17);

		Label lblChannelName = new Label(grpCreateNewAccount, SWT.NONE);
		lblChannelName.setAlignment(SWT.RIGHT);
		lblChannelName.setText("C Monitor ID");
		lblChannelName.setBounds(10, 88, 109, 17);

		Label lblGoogleAccount = new Label(grpCreateNewAccount, SWT.NONE);
		lblGoogleAccount.setAlignment(SWT.RIGHT);
		lblGoogleAccount.setText("Time Sync");
		lblGoogleAccount.setBounds(10, 141, 109, 17);

		txtTimeSync = new Text(grpCreateNewAccount, SWT.BORDER);
		txtTimeSync.setText("600");
		txtTimeSync.setTextLimit(150);
		txtTimeSync.setBounds(131, 136, 290, 27);

		Label lblVideoIntro = new Label(grpCreateNewAccount, SWT.NONE);
		lblVideoIntro.setAlignment(SWT.RIGHT);
		lblVideoIntro.setText("Sync Status");
		lblVideoIntro.setBounds(10, 187, 109, 17);

		cbHome = new Combo(grpCreateNewAccount, SWT.NONE);
		cbHome.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cName = getHomeChannelName(cbHome.getText());
				linkHomeChanel.setText("Go to <a href=\"https://www.youtube.com/channel\">" + cName + "</a> channel." );
			}
		});
		cbHome.setItems(new String[] {});
		cbHome.setBounds(132, 19, 289, 29);

		cbStatus = new Combo(grpCreateNewAccount, SWT.NONE);
		cbStatus.setItems(new String[] {"disable", "enable"});
		cbStatus.setBounds(132, 182, 289, 29);
		cbStatus.select(0);

		cbMonitor = new Combo(grpCreateNewAccount, SWT.NONE);
		cbMonitor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cName = getMonitorChannelName(cbMonitor.getText());
				linkMonitorChanel.setText("Go to <a href=\"https://www.youtube.com/channel\">" + cName + "</a> channel." );
			}
		});
		cbMonitor.setItems(new String[] {});
		cbMonitor.setBounds(132, 77, 289, 29);

		linkHomeChanel = new Link(grpCreateNewAccount, SWT.NONE);
		linkHomeChanel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(cbHome.getText().isEmpty() == false)
				{
					Program.launch("https://www.youtube.com/channel/" + cbHome.getText());	
				}
			}
		});
		linkHomeChanel.setBounds(131, 54, 290, 17);
		linkHomeChanel.setText("<a></a>");

		linkMonitorChanel = new Link(grpCreateNewAccount, 0);
		linkMonitorChanel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(cbMonitor.getText().isEmpty() == false)
				{
					Program.launch("https://www.youtube.com/channel/" + cbMonitor.getText());	
				}
			}
		});
		linkMonitorChanel.setText("<a></a>");
		linkMonitorChanel.setBounds(131, 113, 290, 17);

		cbDownload = new Combo(grpCreateNewAccount, SWT.NONE);
		cbDownload.setItems(new String[] {});
		cbDownload.setBounds(132, 228, 289, 29);

		lblDownloadCluster = new Label(grpCreateNewAccount, SWT.NONE);
		lblDownloadCluster.setText("Download CID");
		lblDownloadCluster.setAlignment(SWT.RIGHT);
		lblDownloadCluster.setBounds(10, 240, 109, 17);

		lblRenderCid = new Label(grpCreateNewAccount, SWT.NONE);
		lblRenderCid.setText("Render CID");
		lblRenderCid.setAlignment(SWT.RIGHT);
		lblRenderCid.setBounds(10, 286, 109, 17);

		cbRender = new Combo(grpCreateNewAccount, SWT.NONE);
		cbRender.setItems(new String[] {});
		cbRender.setBounds(132, 274, 289, 29);

		lblUploadCid = new Label(grpCreateNewAccount, SWT.NONE);
		lblUploadCid.setText("Upload CID");
		lblUploadCid.setAlignment(SWT.RIGHT);
		lblUploadCid.setBounds(10, 329, 109, 17);

		cbUpload = new Combo(grpCreateNewAccount, SWT.NONE);
		cbUpload.setItems(new String[] {});
		cbUpload.setBounds(132, 317, 289, 29);

		tbtmRenderConfig = new TabItem(tabFolder, SWT.NONE);
		tbtmRenderConfig.setImage(ResourceManager.getPluginImage("org.spider.ui.eclipse.spidermanager", "icons/render.png"));
		tbtmRenderConfig.setText("Render Config");

		grpRender = new Group(tabFolder, SWT.NONE);
		grpRender.setText("Render");
		tbtmRenderConfig.setControl(grpRender);
		grpRender.setLayout(null);

		Label lblNewLabel_1 = new Label(grpRender, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setBounds(10, 27, 96, 17);
		lblNewLabel_1.setText("Video Intro");

		txtVideoIntro = new Text(grpRender, SWT.BORDER);
		txtVideoIntro.setBounds(112, 22, 342, 27);

		cbIntro = new Button(grpRender, SWT.CHECK);
		cbIntro.setBounds(460, 22, 20, 24);

		lblVideoOutro = new Label(grpRender, SWT.NONE);
		lblVideoOutro.setText("Video Outro");
		lblVideoOutro.setAlignment(SWT.RIGHT);
		lblVideoOutro.setBounds(10, 66, 96, 17);

		txtVideoOutro = new Text(grpRender, SWT.BORDER);
		txtVideoOutro.setBounds(112, 61, 342, 27);

		cbOutro = new Button(grpRender, SWT.CHECK);
		cbOutro.setBounds(460, 61, 20, 24);

		lblVideoLogo = new Label(grpRender, SWT.NONE);
		lblVideoLogo.setText("Video Logo");
		lblVideoLogo.setAlignment(SWT.RIGHT);
		lblVideoLogo.setBounds(10, 105, 96, 17);

		txtLogo = new Text(grpRender, SWT.BORDER);
		txtLogo.setBounds(112, 100, 342, 27);

		cbLogo = new Button(grpRender, SWT.CHECK);
		cbLogo.setBounds(460, 100, 20, 24);

		lblLogoPosition = new Label(grpRender, SWT.NONE);
		lblLogoPosition.setText("Logo Position");
		lblLogoPosition.setAlignment(SWT.RIGHT);
		lblLogoPosition.setBounds(10, 148, 96, 17);

		txtLogoPosX = new Text(grpRender, SWT.BORDER);
		txtLogoPosX.setBounds(112, 142, 96, 27);

		txtLogoPosY = new Text(grpRender, SWT.BORDER);
		txtLogoPosY.setBounds(243, 142, 96, 27);

		label = new Label(grpRender, SWT.NONE);
		label.setText(":");
		label.setAlignment(SWT.CENTER);
		label.setBounds(217, 148, 20, 17);
		
		Button btnDefault = new Button(grpRender, SWT.NONE);
		btnDefault.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtVideoIntro.setText("/home/phongtran0715/Downloads/Video/test/render_resource/intro.mp4");
				txtVideoOutro.setText("/home/phongtran0715/Downloads/Video/test/render_resource/outro.mp4");
				txtLogo.setText("/home/phongtran0715/Downloads/Video/test/render_resource/logo.png");
				cbIntro.setSelection(true);
				cbOutro.setSelection(true);
				cbLogo.setSelection(false);
			}
		});
		btnDefault.setBounds(113, 283, 95, 29);
		btnDefault.setText("default");

		tbtmUploadConfig = new TabItem(tabFolder, SWT.NONE);
		tbtmUploadConfig.setImage(ResourceManager.getPluginImage("org.spider.ui.eclipse.spidermanager", "icons/upload.png"));
		tbtmUploadConfig.setText("Upload Config");

		grpAbc = new Group(tabFolder, SWT.NONE);
		grpAbc.setText("Upload");
		tbtmUploadConfig.setControl(grpAbc);
		grpAbc.setLayout(null);

		Label lblNewLabel = new Label(grpAbc, SWT.CENTER);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(23, 74, 109, 17);
		lblNewLabel.setText("Title template");

		txtTitle = new Text(grpAbc, SWT.BORDER | SWT.WRAP);
		txtTitle.setBounds(138, 31, 301, 102);

		cbTitle = new Button(grpAbc, SWT.CHECK);
		cbTitle.setBounds(445, 78, 26, 24);

		lblDescTemplate = new Label(grpAbc, SWT.CENTER);
		lblDescTemplate.setText("Desc template");
		lblDescTemplate.setAlignment(SWT.RIGHT);
		lblDescTemplate.setBounds(23, 192, 109, 17);

		cbDesc = new Button(grpAbc, SWT.CHECK);
		cbDesc.setBounds(445, 192, 26, 24);

		lblTagsTemplate = new Label(grpAbc, SWT.CENTER);
		lblTagsTemplate.setText("Tags template");
		lblTagsTemplate.setAlignment(SWT.RIGHT);
		lblTagsTemplate.setBounds(23, 319, 109, 17);

		cbTag = new Button(grpAbc, SWT.CHECK);
		cbTag.setBounds(445, 312, 26, 24);

		txtDesc = new Text(grpAbc, SWT.BORDER | SWT.WRAP);
		txtDesc.setBounds(138, 148, 301, 102);

		txtTags = new Text(grpAbc, SWT.BORDER | SWT.WRAP);
		txtTags.setBounds(138, 271, 301, 102);

		initialData();
		return dialogArea;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Create new mapping channel");
	}

	@Override
	protected void okPressed() {
		mappingConfig.cHomeId = cbHome.getText();
		if(mappingConfig.cHomeId == null || mappingConfig.cHomeId.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Home channel ID must not empty!");
			dialog.open();
			return;
		}

		mappingConfig.cMonitorId = cbMonitor.getText();
		if(mappingConfig.cMonitorId == null || mappingConfig.cMonitorId.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Monitor channel ID must not empty!");
			dialog.open();
			return;
		}

		String strTime = txtTimeSync.getText();
		if(strTime == null || strTime.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Time sync interval must not empty!");
			dialog.open();
			return;
		}
		mappingConfig.timeSync = Integer.parseInt(strTime);

		if(cbStatus.getText().equals("disable"))
		{
			mappingConfig.statusSync = 0;
		}
		else{
			mappingConfig.statusSync = 1;
		}
		mappingConfig.downloadClusterId = cbDownload.getText();
		mappingConfig.renderClusterId = cbRender.getText();
		mappingConfig.uploadClusterId = cbUpload.getText();
		
		renderConfig.vIntro = txtVideoIntro.getText();
		renderConfig.vOutro = txtVideoOutro.getText();
		renderConfig.vLogo = txtLogo.getText();
		renderConfig.enableIntro = cbIntro.getSelection();
		renderConfig.enableOutro = cbOutro.getSelection();
		renderConfig.enableLogo = cbLogo.getSelection();
		
		uploadConfig.titleTemp = txtTitle.getText();
		uploadConfig.descTemp = txtDesc.getText();
		uploadConfig.tagTemp = txtTags.getText();
		uploadConfig.enableTitle = cbTitle.getSelection();
		uploadConfig.enableDesc = cbDesc.getSelection();
		uploadConfig.enableTag = cbTag.getSelection();
		
		super.okPressed();
	}

	private void initialData()
	{
		//Initial data
		try {
			if(cHomeObject == null)
			{
				cHomeObject = session.getHomeChannel();
				setHomeChannelData();
			}
		} catch (IOException | NXCException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(cMoniorObject == null)
		{
			try {
				cMoniorObject = session.getMonitorChannelList();
				setMonitorChannelData();
			} catch (IOException | NXCException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if(downloadClusters == null)
		{
			try {
				downloadClusters = session.getCluster(SpiderCodes.CLUSTER_DOWNLOAD);
				setDownloadCluster();
			} catch (IOException | NXCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(renderClusters == null)
		{
			try {
				renderClusters = session.getCluster(SpiderCodes.CLUSTER_RENDER);
				setRenderCluster();
			} catch (IOException | NXCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(uploadClusters == null)
		{
			try {
				uploadClusters = session.getCluster(SpiderCodes.CLUSTER_UPLOAD);
				setUploadCluster();
			} catch (IOException | NXCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setHomeChannelData()
	{
		if(cHomeObject != null)
		{
			for(int i = 0; i < cHomeObject.length; i++)
			{
				Object homeObj = cHomeObject[i];
				if(homeObj instanceof HomeChannelObject)
				{
					cbHome.add(((HomeChannelObject) homeObj).getChannelId());
				}
			}
		}
	}

	private void setMonitorChannelData()
	{
		if(cMoniorObject != null)
		{
			for(int i = 0; i < cMoniorObject.length; i++)
			{
				Object monitorObj = cMoniorObject[i];
				if(monitorObj instanceof MonitorChannelObject)
				{
					cbMonitor.add(((MonitorChannelObject) monitorObj).getChannelId());
				}
			}
		}
	}

	private void setDownloadCluster()
	{
		if(downloadClusters != null)
		{
			for(int i = 0; i < downloadClusters.length; i++)
			{
				Object object = downloadClusters[i];
				if(object instanceof ClusterObject)
				{
					cbDownload.add(((ClusterObject) object).getClusterId());
				}
			}
		}
	}

	private void setRenderCluster()
	{
		if(renderClusters != null)
		{
			for(int i = 0; i < renderClusters.length; i++)
			{
				Object object = renderClusters[i];
				if(object instanceof ClusterObject)
				{
					cbRender.add(((ClusterObject) object).getClusterId());
				}
			}
		}
	}

	private void setUploadCluster()
	{
		if(uploadClusters != null)
		{
			for(int i = 0; i < uploadClusters.length; i++)
			{
				Object object = uploadClusters[i];
				if(object instanceof ClusterObject)
				{
					cbUpload.add(((ClusterObject) object).getClusterId());
				}
			}
		}
	}

	private String getHomeChannelName(String cHomeID)
	{
		String result = "";
		for (Object it : cHomeObject) {
			if(it instanceof HomeChannelObject)
			{
				if(((HomeChannelObject) it).getChannelId().equals(cHomeID))
				{
					result = ((HomeChannelObject) it).getChannelName();
				}
			}
		}
		return result;
	}

	private String getMonitorChannelName(String cMonitorID)
	{
		String result = "";
		for (Object it : cMoniorObject) {
			if(it instanceof MonitorChannelObject)
			{
				if(((MonitorChannelObject) it).getChannelId().equals(cMonitorID))
				{
					result = ((MonitorChannelObject) it).getChannelName();
				}
			}
		}
		return result;
	}

	public MappingConfig getMappingConfig() {
		return mappingConfig;
	}

	public void setMappingConfig(MappingConfig mappingConfig) {
		this.mappingConfig = mappingConfig;
	}

	public RenderConfig getRenderConfig() {
		return renderConfig;
	}

	public void setRenderConfig(RenderConfig renderConfig) {
		this.renderConfig = renderConfig;
	}

	public UploadConfig getUploadConfig() {
		return uploadConfig;
	}

	public void setUploadConfig(UploadConfig uploadConfig) {
		this.uploadConfig = uploadConfig;
	}
}
