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
public class EditGoocleAccountDialog extends Dialog {
	private Text txtEmail;
	private Text txtClientSecret;
	private Text txtAppName;
	private Text txtApiKey;

	public EditGoocleAccountDialog(Shell parentShell) {
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
		grpCreateNewAccount.setText("Edit account");
		grpCreateNewAccount.setBounds(5, 10, 516, 171);
		grpCreateNewAccount.setLayout(null);

		Label label = new Label(grpCreateNewAccount, SWT.NONE);
		label.setBounds(10, 31, 38, 17);
		label.setText("Email");

		txtEmail = new Text(grpCreateNewAccount, SWT.BORDER);
		txtEmail.setBounds(101, 26, 320, 27);
		txtEmail.setTextLimit(150);

		Label label_1 = new Label(grpCreateNewAccount, SWT.NONE);
		label_1.setBounds(10, 64, 86, 17);
		label_1.setText("Client Secret");

		txtClientSecret = new Text(grpCreateNewAccount, SWT.BORDER);
		txtClientSecret.setBounds(101, 59, 320, 27);
		txtClientSecret.setTextLimit(150);

		Label label_2 = new Label(grpCreateNewAccount, SWT.NONE);
		label_2.setBounds(10, 97, 71, 17);
		label_2.setText("App Name");

		txtAppName = new Text(grpCreateNewAccount, SWT.BORDER);
		txtAppName.setBounds(101, 92, 320, 27);
		txtAppName.setTextLimit(150);

		Label label_3 = new Label(grpCreateNewAccount, SWT.NONE);
		label_3.setBounds(10, 130, 50, 17);
		label_3.setText("API Key");

		txtApiKey = new Text(grpCreateNewAccount, SWT.BORDER);
		txtApiKey.setBounds(101, 125, 320, 27);
		txtApiKey.setTextLimit(150);

		Button btnBrowse = new Button(grpCreateNewAccount, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
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
		btnBrowse.setBounds(427, 57, 71, 29);
		btnBrowse.setText("Browse...");

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
		newShell.setText("Edit google account");
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
