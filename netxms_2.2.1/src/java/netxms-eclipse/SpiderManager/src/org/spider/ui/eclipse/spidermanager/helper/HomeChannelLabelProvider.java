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
import org.spider.ui.eclipse.spidermanager.views.HomeChannelManagerView;
import org.spider.client.*;
/**
 * Label provider for user manager
 */
public class HomeChannelLabelProvider extends DecoratingLabelProvider implements
		ITableLabelProvider {
	private static final String[] HOME_CHANNEL_METHOD = {
			"Id",
			"ChannelId",
			"ChannelName",
			"GoogleAccount",
			"VideoIntro",
			"VideoOutro",
			"Logo",
			"DescriptionTemplate",
			"TitleTemplate"};

	/**
	 * Constructor
	 */
	public HomeChannelLabelProvider() {
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
		case HomeChannelManagerView.COLUMN_ID:
			return Integer.toString(((HomeChannelObject) element).getId());
		case HomeChannelManagerView.COLUMN_CHANNEL_ID:
			return ((HomeChannelObject) element).getChannelId();
		case HomeChannelManagerView.COLUMN_CHANNEL_NAME:
			return ((HomeChannelObject) element).getChannelName();
		case HomeChannelManagerView.COLUMN_GOOGLE_ACCOUNT:
			return ((HomeChannelObject) element).getGoogleAccount();
		case HomeChannelManagerView.COLUMN_VIDEO_INTRO:
			return ((HomeChannelObject) element).getvIntro();
		case HomeChannelManagerView.COLUMN_VIDEO_OUTRO:
			return ((HomeChannelObject) element).getvOutro();
		case HomeChannelManagerView.COLUMN_LOGO:
			return ((HomeChannelObject) element).getLogo();
		case HomeChannelManagerView.COLUMN_DESCRIPTION:
			return ((HomeChannelObject) element).getDescTemp();
		case HomeChannelManagerView.COLUMN_TITLE:
			return ((HomeChannelObject) element).getTitleTemp();
		default:
			break;
		}
		return null;
	}
}
