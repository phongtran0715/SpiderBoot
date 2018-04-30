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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * User database object creation dialog
 * 
 */
public class CreateGoogleAccoutDialog extends Dialog {
	private Text txtEmail;
	private Text txtClientSecret;
	private Text txtAppName;
	private Text txtApiKey;

	public CreateGoogleAccoutDialog(Shell parentShell) {
		super(parentShell);
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
		grpCreateNewAccount.setText("Create new account");
		grpCreateNewAccount.setBounds(5, 10, 516, 171);

		Label label = new Label(grpCreateNewAccount, SWT.NONE);
		label.setText("Email");
		label.setBounds(10, 31, 38, 17);

		txtEmail = new Text(grpCreateNewAccount, SWT.BORDER);
		txtEmail.setTextLimit(150);
		txtEmail.setBounds(101, 26, 320, 27);

		Label label_1 = new Label(grpCreateNewAccount, SWT.NONE);
		label_1.setText("Client Secret");
		label_1.setBounds(10, 65, 86, 17);

		txtClientSecret = new Text(grpCreateNewAccount, SWT.BORDER);
		txtClientSecret.setTextLimit(150);
		txtClientSecret.setBounds(101, 60, 320, 27);

		Label label_2 = new Label(grpCreateNewAccount, SWT.NONE);
		label_2.setText("App Name");
		label_2.setBounds(10, 101, 71, 17);

		txtAppName = new Text(grpCreateNewAccount, SWT.BORDER);
		txtAppName.setTextLimit(150);
		txtAppName.setBounds(101, 96, 320, 27);

		Label label_3 = new Label(grpCreateNewAccount, SWT.NONE);
		label_3.setText("API Key");
		label_3.setBounds(10, 134, 50, 17);

		txtApiKey = new Text(grpCreateNewAccount, SWT.BORDER);
		txtApiKey.setTextLimit(150);
		txtApiKey.setBounds(101, 129, 320, 27);

		Button btnClientSecret = new Button(grpCreateNewAccount, SWT.NONE);
		btnClientSecret.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setText("Select client secret file");
				fd.setFilterExtensions(new String[] {"*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				fd.setFilterNames(new String[] {
				"All file" });
				String fileName = fd.open();
				txtClientSecret.setText(fileName);
			}
		});
		btnClientSecret.setText("Browse...");
		btnClientSecret.setBounds(427, 60, 71, 29);
		//txtAppName.setLayoutData(gridData);

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
		newShell.setText("Create new google account");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		String email = txtEmail.getText();
		if(email == null || email.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Email field must not empty!");
			dialog.open();
			return;	
		}
		super.okPressed();
	}
}
