/**
 * NetXMS - open source network management system
 * Copyright (C) 2003-2014 Victor Kirhenshtein
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
package org.spider.ui.eclipse.spidermanager.helper;

import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.spider.ui.eclipse.spidermanager.views.GoogleAccountManagerView;
import org.spider.client.*;
/**
 * Label provider for user manager
 */
public class GoogleAccountLabelProvider extends DecoratingLabelProvider implements
		ITableLabelProvider {
	private static final String[] GOOGLE_METHOD = {
			"Id",
			"Username",
			"ClientSecret",
			"AccountType",
			"AppName" };

	/**
	 * Constructor
	 */
	public GoogleAccountLabelProvider() {
		super(new WorkbenchLabelProvider(), PlatformUI.getWorkbench()
				.getDecoratorManager().getLabelDecorator());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang
	 * .Object, int)
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return (columnIndex == 0) ? getImage(element) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang
	 * .Object, int)
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case GoogleAccountManagerView.COLUMN_ID:
			return Integer.toString(((GoogleAccountObject) element).getId());
		case GoogleAccountManagerView.COLUMN_USER_NAME:
			return ((GoogleAccountObject) element).getUserName();
		case GoogleAccountManagerView.COLUMN_API:
			return ((GoogleAccountObject) element).getApi();
		case GoogleAccountManagerView.COLUMN_CLIENT_SECRET:
			return ((GoogleAccountObject) element).getClientSecret();
		case GoogleAccountManagerView.COLUMN_ACCOUNT_TYPE:
			return ((GoogleAccountObject) element).getAccountType();
		case GoogleAccountManagerView.COLUMN_APPNAME:
			return ((GoogleAccountObject) element).getAppName();
		default:
			break;
		}
		return null;
	}
}
