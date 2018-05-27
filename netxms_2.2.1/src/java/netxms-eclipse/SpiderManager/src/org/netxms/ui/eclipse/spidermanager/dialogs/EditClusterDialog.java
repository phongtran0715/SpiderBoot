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
import org.spider.client.ClusterObject;

/**
 * User database object creation dialog
 * 
 */
public class EditClusterDialog extends Dialog {
	private ClusterObject object;
	private int recordId;
	private String clusterId;
	private String clusterName;
	private String ipAddress;
	private int port;
	private Text txtClusterId;
	private Text txtClusterName;
	private Text txtIpAddress;
	private Text txtPort;
	private String title;

	public EditClusterDialog(Shell parentShell, ClusterObject object, String title) {
		super(parentShell);
		this.object = object;
		recordId = object.getRecordID();
		this.title = title;
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
		grpCreateNewAccount.setText("Create cluster");
		grpCreateNewAccount.setBounds(5, 10, 437, 208);

		Label lblChannelId = new Label(grpCreateNewAccount, SWT.NONE);
		lblChannelId.setAlignment(SWT.RIGHT);
		lblChannelId.setText("Cluters ID");
		lblChannelId.setBounds(10, 31, 109, 17);

		Label lblChannelName = new Label(grpCreateNewAccount, SWT.NONE);
		lblChannelName.setAlignment(SWT.RIGHT);
		lblChannelName.setText("Cluster Name");
		lblChannelName.setBounds(10, 76, 109, 17);

		Label lblGoogleAccount = new Label(grpCreateNewAccount, SWT.NONE);
		lblGoogleAccount.setAlignment(SWT.RIGHT);
		lblGoogleAccount.setText("IP Address");
		lblGoogleAccount.setBounds(10, 119, 109, 17);
		
		txtClusterId = new Text(grpCreateNewAccount, SWT.BORDER);
		txtClusterId.setTextLimit(150);
		txtClusterId.setBounds(131, 21, 290, 27);
		
		txtClusterName = new Text(grpCreateNewAccount, SWT.BORDER);
		txtClusterName.setTextLimit(150);
		txtClusterName.setBounds(131, 66, 290, 27);
		
		txtIpAddress = new Text(grpCreateNewAccount, SWT.BORDER);
		txtIpAddress.setTextLimit(150);
		txtIpAddress.setBounds(131, 109, 290, 27);
		
		txtPort = new Text(grpCreateNewAccount, SWT.BORDER);
		txtPort.setTextLimit(150);
		txtPort.setBounds(131, 154, 290, 27);
		
		Label lblPort = new Label(grpCreateNewAccount, SWT.NONE);
		lblPort.setText("Port");
		lblPort.setAlignment(SWT.RIGHT);
		lblPort.setBounds(10, 164, 109, 17);

		initialData();
		return dialogArea;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(this.title);
	}

	@Override
	protected void okPressed() {
		clusterId = txtClusterId.getText();
		clusterName = txtClusterName.getText();
		if(clusterId == null || clusterId.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Cluster ID must not empty!");
			dialog.open();
			return;
		}
		
		ipAddress = txtIpAddress.getText();
		if(ipAddress == null || ipAddress.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("IP Address must not empty!");
			dialog.open();
			return;
		}
		port = Integer.parseInt(txtPort.getText());
		
		super.okPressed();
	}

	private void initialData()
	{
		txtClusterId.setText(object.getClusterId());
		txtClusterName.setText(object.getClusterName());
		txtIpAddress.setText(object.getIpAddress());
		txtPort.setText(Integer.toString(object.getPort()));
	}

	public String getClusterId() {
		return clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public int getPort() {
		return port;
	}

	public int getRecordId() {
		return recordId;
	}
	
}
