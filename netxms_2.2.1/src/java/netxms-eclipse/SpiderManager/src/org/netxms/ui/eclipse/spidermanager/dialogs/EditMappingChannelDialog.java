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
import org.spider.client.HomeChannelObject;
import org.spider.client.MappingChannelObject;
import org.spider.client.MonitorChannelObject;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Link;

/**
 * User database object creation dialog
 * 
 */
public class EditMappingChannelDialog extends Dialog {
	private Text txtTimeSync;
	private Combo cbHome;
	private Combo cbMonitor;
	private Combo cbStatus;
	MappingChannelObject object;
	private NXCSession session;
	Link linkHomeChanel;
	Link linkMonitorChanel;

	private int id;
	private String homeChannelId;
	private String monitorChannelId;
	private int status;
	private long timeSync;
	Object [] cHomeObject;
	Object [] cMoniorObject;

	public EditMappingChannelDialog(Shell parentShell, MappingChannelObject object) {
		super(parentShell);
		this.object = object;
		id = object.getId();
		session = ConsoleSharedData.getSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets
	 * .Composite)
	 */
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
		grpCreateNewAccount.setText("Edit mapping channel");
		grpCreateNewAccount.setBounds(5, 10, 437, 250);

		Label lblChannelId = new Label(grpCreateNewAccount, SWT.NONE);
		lblChannelId.setAlignment(SWT.RIGHT);
		lblChannelId.setText("C Home ID");
		lblChannelId.setBounds(10, 31, 109, 17);

		Label lblChannelName = new Label(grpCreateNewAccount, SWT.NONE);
		lblChannelName.setAlignment(SWT.RIGHT);
		lblChannelName.setText("C Monitor ID");
		lblChannelName.setBounds(10, 87, 109, 17);

		Label lblGoogleAccount = new Label(grpCreateNewAccount, SWT.NONE);
		lblGoogleAccount.setAlignment(SWT.RIGHT);
		lblGoogleAccount.setText("Time Sync");
		lblGoogleAccount.setBounds(10, 141, 109, 17);

		txtTimeSync = new Text(grpCreateNewAccount, SWT.BORDER);
		txtTimeSync.setTextLimit(150);
		txtTimeSync.setBounds(131, 136, 290, 27);

		Label lblVideoIntro = new Label(grpCreateNewAccount, SWT.NONE);
		lblVideoIntro.setAlignment(SWT.RIGHT);
		lblVideoIntro.setText("Sync Status");
		lblVideoIntro.setBounds(10, 199, 109, 17);
		
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
		cbStatus.setBounds(132, 194, 289, 29);

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
		
		linkHomeChanel = new Link(grpCreateNewAccount, 0);
		linkHomeChanel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(cbHome.getText().isEmpty() == false)
				{
					Program.launch("https://www.youtube.com/channel/" + cbHome.getText());	
				}
			}
		});
		linkHomeChanel.setText("<a></a>");
		linkHomeChanel.setBounds(131, 54, 290, 17);
		
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

		initialData();
		return dialogArea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Edit mapping channel");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		homeChannelId = cbHome.getText();
		monitorChannelId = cbMonitor.getText();
		if(cbStatus.getText().equals("disable"))
		{
			status = 0;
		}
		else{
			status = 1;
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
		timeSync = Integer.parseInt(txtTimeSync.getText());
		
		if(homeChannelId == null || homeChannelId.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Home channel ID must not empty!");
			dialog.open();
			return;
		}
		
		if(monitorChannelId == null || monitorChannelId.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Monitor channel ID must not empty!");
			dialog.open();
			return;
		}
		
		super.okPressed();
	}

	public int getId() {
		return id;
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

	private void initialData()
	{
		//Initial data
		cbHome.setText(this.object.getHomeId());
		cbMonitor.setText(this.object.getMonitorId());
		
		if(this.object.getStatusSync() == 0)
		{
			cbStatus.setText("disable");
		}else{
			cbStatus.setText("enable");
		}
		txtTimeSync.setText(Long.toString(this.object.getTimeIntervalSync()));
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
		
		String cHomeName = getHomeChannelName(this.object.getHomeId());
		linkHomeChanel.setText("Go to <a href=\"https://www.youtube.com/channel\">" + cHomeName + "</a> channel." );
		
		String cMonitorName = getMonitorChannelName(this.object.getMonitorId());
		linkMonitorChanel.setText("Go to <a href=\"https://www.youtube.com/channel\">" + cMonitorName + "</a> channel." );
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
	
	private String getHomeChannelName(String cHomeID)
	{
		String result = "";
		if(cHomeID != null)
		{
			for (Object it : cHomeObject) {
				if(it instanceof HomeChannelObject)
				{
					if(((HomeChannelObject) it).getChannelId().equals(cHomeID))
					{
						result = ((HomeChannelObject) it).getChannelName();
					}
				}
			}	
		}
		return result;
	}
	
	private String getMonitorChannelName(String cMonitorID)
	{
		String result = "";
		if(cMonitorID != null)
		{
			for (Object it : cMoniorObject) {
				if(it instanceof MonitorChannelObject)
				{
					if(((MonitorChannelObject) it).getChannelId().equals(cMonitorID))
					{
						result = ((MonitorChannelObject) it).getChannelName();
					}
				}
			}	
		}
		return result;
	}
}
