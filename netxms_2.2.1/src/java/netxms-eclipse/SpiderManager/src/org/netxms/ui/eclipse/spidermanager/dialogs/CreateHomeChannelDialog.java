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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.netxms.client.NXCException;
import org.netxms.client.NXCSession;
import org.netxms.ui.eclipse.shared.ConsoleSharedData;
import org.spider.client.GoogleAccountObject;

/**
 * User database object creation dialog
 * 
 */
public class CreateHomeChannelDialog extends Dialog {
	private Text txtChannelId;
	private Text txtChannelName;
	private Combo cbGoogleAccount;
	
	private String cId;
	private String cName; 
	private String gAccount;
	private int accountId;
	private NXCSession session;
	Object[] objGoogleAccount;
	
	public CreateHomeChannelDialog(Shell parentShell) {
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
		grpCreateNewAccount.setText("Create new home channel");
		grpCreateNewAccount.setBounds(5, 10, 516, 139);

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

		Label lblGoogleAccount = new Label(grpCreateNewAccount, SWT.NONE);
		lblGoogleAccount.setAlignment(SWT.RIGHT);
		lblGoogleAccount.setText("Google Account");
		lblGoogleAccount.setBounds(10, 97, 109, 17);
		
		Button btnView = new Button(grpCreateNewAccount, SWT.NONE);
		btnView.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Program.launch("https://www.youtube.com/channel/" + txtChannelId.getText());	
			}
		});
		btnView.setText("View");
		btnView.setBounds(427, 26, 79, 29);
		
		cbGoogleAccount = new Combo(grpCreateNewAccount, SWT.NONE);
		cbGoogleAccount.setBounds(131, 92, 290, 29);
		
		initData();
		return dialogArea;
	}
	
	private void initData()
	{
		try {
			if(objGoogleAccount == null)
			{
				objGoogleAccount = session.getGoogleAccount();
				setGoogleAccount();
			}
		} catch (IOException | NXCException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void setGoogleAccount()
	{
		if(objGoogleAccount != null)
		{
			for(int i = 0; i < objGoogleAccount.length; i++)
			{
				Object homeObj = objGoogleAccount[i];
				if(homeObj instanceof GoogleAccountObject)
				{
					cbGoogleAccount.add(((GoogleAccountObject) homeObj).getUserName());
				}
			}
		}
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Create new home channel");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		cId = txtChannelId.getText();
		cName = txtChannelName.getText();
		gAccount = cbGoogleAccount.getText();
		accountId = getIdByAccount(gAccount);
		
		if(cId == null || cId.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Channel ID must not empty!");
			dialog.open();
			return;
		}
		String googleAccount = cbGoogleAccount.getText();
		if(googleAccount == null || googleAccount.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Google account must not empty!");
			dialog.open();
			return;
		}
		if(accountId == -1)
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Google account does not exist!");
			dialog.open();
			return;
		}
		super.okPressed();
	}
	
	private int getIdByAccount(String googleAccount)
	{
		int id = -1;
		for ( Object it : objGoogleAccount) {
			if(it instanceof GoogleAccountObject)
			{
				if(((GoogleAccountObject)it).getUserName().equals(googleAccount))
				{
					id = ((GoogleAccountObject)it).getId();	
				}
			}
		}
		return id;
	}
	
	public String getcId() {
		return cId;
	}

	public String getcName() {
		return cName;
	}

	public String getgAccount() {
		return gAccount;
	}

	public int getAccountId() {
		return accountId;
	}	
	
}
