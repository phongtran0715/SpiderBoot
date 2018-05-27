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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Link;

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
	
	private String homeChannelId;
	private String monitorChannelId;
	private int status;
	private long timeSync;
	private String downloadClusterId;
	private String renderClusterId;
	private String uploadClusterId;



	public CreateMappingChannelDialog(Shell parentShell) {
		super(parentShell);
		session = ConsoleSharedData.getSession();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		GridData gridData;
		dialogArea.setLayout(null);
		gridData = new GridData(GridData.VERTICAL_ALIGN_END);
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 4;
		gridData = new GridData(GridData.VERTICAL_ALIGN_END);
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;

		Group grpCreateNewAccount = new Group(dialogArea, SWT.NONE);
		grpCreateNewAccount.setText("Create new mapping channel");
		grpCreateNewAccount.setBounds(5, 10, 432, 372);

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
		homeChannelId = cbHome.getText();
		if(homeChannelId == null || homeChannelId.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Home channel ID must not empty!");
			dialog.open();
			return;
		}
		
		monitorChannelId = cbMonitor.getText();
		if(monitorChannelId == null || monitorChannelId.isEmpty())
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
		timeSync = Integer.parseInt(strTime);
		
		if(cbStatus.getText().equals("disable"))
		{
			status = 0;
		}
		else{
			status = 1;
		}
		downloadClusterId = cbDownload.getText();
		renderClusterId = cbRender.getText();
		uploadClusterId = cbUpload.getText();
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
	
	public String getHomeChannelId() {
		return homeChannelId;
	}

	public String getMonitorChannelId() {
		return monitorChannelId;
	}

	public int getStatus() {
		return status;
	}

	public long getTimeSync() {
		return timeSync;
	}

	public String getDownloadClusterId() {
		return downloadClusterId;
	}

	public String getRenderClusterId() {
		return renderClusterId;
	}

	public String getUploadClusterId() {
		return uploadClusterId;
	}
	
}
