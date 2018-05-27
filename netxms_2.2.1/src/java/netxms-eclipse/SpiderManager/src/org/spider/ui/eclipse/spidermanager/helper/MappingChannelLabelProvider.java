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
import org.spider.ui.eclipse.spidermanager.views.MappingChannelManagerView;
import org.spider.client.*;
/**
 * Label provider for user manager
 */
public class MappingChannelLabelProvider extends DecoratingLabelProvider implements
		ITableLabelProvider {

	/**
	 * Constructor
	 */
	public MappingChannelLabelProvider() {
		super(new WorkbenchLabelProvider(), PlatformUI.getWorkbench()
				.getDecoratorManager().getLabelDecorator());
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return (columnIndex == 0) ? getImage(element) : null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		switch (columnIndex) {
		case MappingChannelManagerView.COLUMN_STT:
		return Integer.toString(((MappingChannelObject)element).getStt());
		case MappingChannelManagerView.COLUMN_ID:
			return Integer.toString(((MappingChannelObject) element).getId());
		case MappingChannelManagerView.COLUMN_HOME_CHANNEL_ID:
			return ((MappingChannelObject) element).getHomeId();
		case MappingChannelManagerView.COLUMN_HOME_CHANNEL_NAME:
			return ((MappingChannelObject) element).getHomeName();
		case MappingChannelManagerView.COLUMN_MONITOR_CHANNEL_ID:
			return ((MappingChannelObject) element).getMonitorId();
		case MappingChannelManagerView.COLUMN_MONITOR_CHANNEL_NAME:
			return ((MappingChannelObject) element).getMonitorName();
		case MappingChannelManagerView.COLUMN_TIME_SYNC:
			return Long.toString(((MappingChannelObject) element).getTimeIntervalSync());
		case MappingChannelManagerView.COLUMN_STATUS_SYNC:
		{
			if(((MappingChannelObject) element).getStatusSync() == 0)
			{
				return "disable";
			}
			else{
				return "enable";
			}
		}
		case MappingChannelManagerView.COLUMN_LAST_SYNC_TIME:
			return ((MappingChannelObject) element).getLastSyncTime();
		case MappingChannelManagerView.COLUMN_DOWNLOAD_ID:
			return ((MappingChannelObject) element).getDownloadClusterId();
		case MappingChannelManagerView.COLUMN_RENDER_ID:
			return ((MappingChannelObject) element).getRenderClusterId();
		case MappingChannelManagerView.COLUMN_UPLOAD_ID:
			return ((MappingChannelObject) element).getUploadClusterId();
		default:
			break;
		}
		return null;
	}
}
