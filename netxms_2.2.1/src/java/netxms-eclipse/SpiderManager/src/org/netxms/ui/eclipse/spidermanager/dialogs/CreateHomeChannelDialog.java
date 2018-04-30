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
public class CreateHomeChannelDialog extends Dialog {
	private Text txtChannelId;
	private Text txtChannelName;
	private Text txtGoogleAccount;
	private Text txtVideoIntro;
	private Text txtVideoOutro;
	private Text txtLogo;
	private Text txtTitleTemplate;
	private Text txtDesTemplate;

	public CreateHomeChannelDialog(Shell parentShell) {
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
		grpCreateNewAccount.setText("Create new home channel");
		grpCreateNewAccount.setBounds(5, 10, 516, 304);

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

		txtGoogleAccount = new Text(grpCreateNewAccount, SWT.BORDER);
		txtGoogleAccount.setTextLimit(150);
		txtGoogleAccount.setBounds(131, 92, 290, 27);

		Label lblVideoIntro = new Label(grpCreateNewAccount, SWT.NONE);
		lblVideoIntro.setAlignment(SWT.RIGHT);
		lblVideoIntro.setText("Video Intro");
		lblVideoIntro.setBounds(10, 130, 109, 17);

		txtVideoIntro = new Text(grpCreateNewAccount, SWT.BORDER);
		txtVideoIntro.setTextLimit(150);
		txtVideoIntro.setBounds(131, 125, 290, 27);

		Button btnVideoIntro = new Button(grpCreateNewAccount, SWT.NONE);
		btnVideoIntro.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setText("Select client secret file");
				fd.setFilterExtensions(new String[] {"*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				fd.setFilterNames(new String[] {
				"All file" });
				String fileName = fd.open();
				txtVideoIntro.setText(fileName);
			}
		});
		btnVideoIntro.setText("Browse...");
		btnVideoIntro.setBounds(427, 123, 79, 29);

		Label lblVideoOutro = new Label(grpCreateNewAccount, SWT.NONE);
		lblVideoOutro.setAlignment(SWT.RIGHT);
		lblVideoOutro.setText("Video Outro");
		lblVideoOutro.setBounds(10, 163, 109, 17);

		txtVideoOutro = new Text(grpCreateNewAccount, SWT.BORDER);
		txtVideoOutro.setTextLimit(150);
		txtVideoOutro.setBounds(131, 158, 290, 27);

		Label lblLogo = new Label(grpCreateNewAccount, SWT.NONE);
		lblLogo.setAlignment(SWT.RIGHT);
		lblLogo.setText("Logo");
		lblLogo.setBounds(10, 196, 109, 17);

		txtLogo = new Text(grpCreateNewAccount, SWT.BORDER);
		txtLogo.setTextLimit(150);
		txtLogo.setBounds(131, 191, 290, 27);

		Label lblTitleTelplate = new Label(grpCreateNewAccount, SWT.NONE);
		lblTitleTelplate.setText("Title Telplate");
		lblTitleTelplate.setAlignment(SWT.RIGHT);
		lblTitleTelplate.setBounds(10, 229, 109, 17);

		txtTitleTemplate = new Text(grpCreateNewAccount, SWT.BORDER);
		txtTitleTemplate.setTextLimit(150);
		txtTitleTemplate.setBounds(131, 224, 290, 27);

		Label lblDescTemplate = new Label(grpCreateNewAccount, SWT.NONE);
		lblDescTemplate.setText("Desc Template");
		lblDescTemplate.setAlignment(SWT.RIGHT);
		lblDescTemplate.setBounds(10, 262, 109, 17);

		txtDesTemplate = new Text(grpCreateNewAccount, SWT.BORDER);
		txtDesTemplate.setTextLimit(150);
		txtDesTemplate.setBounds(131, 257, 290, 27);

		Button btnVideoOutro = new Button(grpCreateNewAccount, SWT.NONE);
		btnVideoOutro.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setText("Select client secret file");
				fd.setFilterExtensions(new String[] {"*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				fd.setFilterNames(new String[] {
				"All file" });
				String fileName = fd.open();
				txtVideoOutro.setText(fileName);
			}
		});
		btnVideoOutro.setText("Browse...");
		btnVideoOutro.setBounds(427, 156, 79, 29);

		Button btnLogo = new Button(grpCreateNewAccount, SWT.NONE);
		btnLogo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setText("Select client secret file");
				fd.setFilterExtensions(new String[] {"*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				fd.setFilterNames(new String[] {
				"All file" });
				String fileName = fd.open();
				txtLogo.setText(fileName);
			}
		});
		btnLogo.setText("Browse...");
		btnLogo.setBounds(427, 191, 79, 29);

		Button btnTitle = new Button(grpCreateNewAccount, SWT.NONE);
		btnTitle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setText("Select client secret file");
				fd.setFilterExtensions(new String[] {"*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				fd.setFilterNames(new String[] {
				"All file" });
				String fileName = fd.open();
				txtTitleTemplate.setText(fileName);
			}
		});
		btnTitle.setText("Browse...");
		btnTitle.setBounds(427, 222, 79, 29);

		Button btnDesc = new Button(grpCreateNewAccount, SWT.NONE);
		btnDesc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setText("Select client secret file");
				fd.setFilterExtensions(new String[] {"*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				fd.setFilterNames(new String[] {
				"All file" });
				String fileName = fd.open();
				txtDesTemplate.setText(fileName);
			}
		});
		btnDesc.setText("Browse...");
		btnDesc.setBounds(427, 257, 79, 29);
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
		newShell.setText("Create new home channel");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		String channelID = txtChannelId.getText();
		if(channelID == null || channelID.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Channel ID must not empty!");
			dialog.open();
			return;
		}
		String googleAccount = txtGoogleAccount.getText();
		if(googleAccount == null || googleAccount.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("Google account must not empty!");
			dialog.open();
			return;
		}	
		super.okPressed();
	}
}
