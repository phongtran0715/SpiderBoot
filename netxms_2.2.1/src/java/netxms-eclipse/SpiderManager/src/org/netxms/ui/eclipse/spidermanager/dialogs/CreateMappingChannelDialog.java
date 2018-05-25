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

import java.io.Console;
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
import org.spider.client.MonitorChannelObject;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Link;

/**
 * User database object creation dialog
 * 
 */
public class CreateMappingChannelDialog extends Dialog {
	private Text txtTimeSync;
	private Combo cbHome;
	private Combo cbMonitor;
	private Combo cbStatus;
	Link linkHomeChanel;
	Link linkMonitorChanel;
	Object [] cHomeObject;
	Object [] cMoniorObject;
	private NXCSession session;
	private String homeChannelId;
	private String monitorChannelId;
	private int status;
	private long timeSync;


	public CreateMappingChannelDialog(Shell parentShell) {
		super(parentShell);
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
		grpCreateNewAccount.setText("Create new mapping channel");
		grpCreateNewAccount.setBounds(5, 10, 432, 256);

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
		lblGoogleAccount.setBounds(10, 140, 109, 17);

		txtTimeSync = new Text(grpCreateNewAccount, SWT.BORDER);
		txtTimeSync.setText("600");
		txtTimeSync.setTextLimit(150);
		txtTimeSync.setBounds(131, 135, 290, 27);

		Label lblVideoIntro = new Label(grpCreateNewAccount, SWT.NONE);
		lblVideoIntro.setAlignment(SWT.RIGHT);
		lblVideoIntro.setText("Sync Status");
		lblVideoIntro.setBounds(10, 200, 109, 17);

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
		cbStatus.setBounds(132, 195, 289, 29);
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
		cbMonitor.setBounds(132, 78, 289, 29);
		
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
		newShell.setText("Create new mapping channel");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
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
}
