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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.spider.client.MonitorChannelObject;

/**
 * User database object creation dialog
 * 
 */
public class EditMonitorChannelDialog extends Dialog {
	private Text txtChannelId;
	private Text txtChannelName;
	
	private int id;
	private String channelId;
	private String channelName;
	MonitorChannelObject object;

	public EditMonitorChannelDialog(Shell parentShell, MonitorChannelObject object) 
	{
		super(parentShell);
		this.object = object;
		id = object.getId();
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
		grpCreateNewAccount.setText("Edit monitor channel");
		grpCreateNewAccount.setBounds(5, 10, 436, 97);
		
		Label lblChannelId = new Label(grpCreateNewAccount, SWT.NONE);
		lblChannelId.setAlignment(SWT.RIGHT);
		lblChannelId.setText("Channel ID");
		lblChannelId.setBounds(10, 31, 109, 17);
		
		txtChannelId = new Text(grpCreateNewAccount, SWT.BORDER);
		txtChannelId.setTextLimit(150);
		txtChannelId.setBounds(131, 26, 290, 27);
		
		Label lblChannelName = new Label(grpCreateNewAccount, SWT.NONE);
		lblChannelName.setAlignment(SWT.RIGHT);
		lblChannelName.setText("Channel name");
		lblChannelName.setBounds(10, 64, 109, 17);
		
		txtChannelName = new Text(grpCreateNewAccount, SWT.BORDER);
		txtChannelName.setTextLimit(150);
		txtChannelName.setBounds(131, 59, 290, 27);
		
		//initial data
		txtChannelId.setText(object.getChannelId());
		txtChannelName.setText(object.getChannelName());
		
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
		newShell.setText("Edit monitor channel");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		channelId = txtChannelId.getText();
		channelName = txtChannelName.getText();
		if(channelId == null || channelId.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Channel ID must not empty!");
			dialog.open();
			return;
		}
		super.okPressed();
	}

	public String getChannelId() {
		return channelId;
	}

	public String getChannelName() {
		return channelName;
	}
	
	public int getId() {
		return id;
	}
}