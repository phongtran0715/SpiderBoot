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
import org.spider.ui.eclipse.spidermanager.views.DownloadClusterManagerView;
import org.spider.client.*;
/**
 * Label provider for user manager
 */
public class ClusterLabelProvider extends DecoratingLabelProvider implements
		ITableLabelProvider {
	private static final String[] CLUSTER_METHOD = {
			"STT",
			"ID",
			"Cluster ID",
			"Cluster Name",
			"IP Address",
			"Port"
			};

	/**
	 * Constructor
	 */
	public ClusterLabelProvider() {
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
		case DownloadClusterManagerView.COLUMN_STT:
			return Integer.toString(((ClusterObject)element).getStt());
		case DownloadClusterManagerView.COLUMN_RECORD_ID:
			return Integer.toString(((ClusterObject) element).getRecordID());
		case DownloadClusterManagerView.COLUMN_CLUSTER_ID:
			return ((ClusterObject) element).getClusterId();
		case DownloadClusterManagerView.COLUMN_CLUSTER_NAME:
			return ((ClusterObject) element).getClusterName();
		case DownloadClusterManagerView.COLUMN_IP_ADDRESS:
			return ((ClusterObject) element).getIpAddress();
		case DownloadClusterManagerView.COLUMN_PORT:
			return Integer.toString(((ClusterObject) element).getPort());
		default:
			break;
		}
		return null;
	}
}
