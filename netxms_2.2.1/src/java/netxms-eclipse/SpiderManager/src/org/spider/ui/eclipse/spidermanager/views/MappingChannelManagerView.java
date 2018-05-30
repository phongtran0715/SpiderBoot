package org.spider.ui.eclipse.spidermanager.views;

import java.io.IOException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.action.*;
import org.eclipse.ui.*;
import org.eclipse.swt.SWT;
import org.netxms.client.NXCException;
import org.netxms.client.SessionListener;
import org.netxms.client.SessionNotification;
import org.netxms.ui.eclipse.jobs.ConsoleJob;
import org.netxms.ui.eclipse.logviewer.views.LogViewer;
import org.netxms.ui.eclipse.spidermanager.dialogs.CreateMappingChannelDialog;
import org.netxms.ui.eclipse.spidermanager.dialogs.EditMappingChannelDialog;
import org.spider.client.MappingChannelObject;
import org.spider.ui.eclipse.spidermanager.Activator;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class MappingChannelManagerView extends LogViewer {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.spider.ui.eclipse.spidermanager.views.MappingChannelManager";

	private Action actAddMapping;
	private Action actEditMapping;
	private Action actDeleteMapping;
	private SessionListener sessionListener;

	public static final int COLUMN_ID 					= 0;
	public static final int COLUMN_HOME_CHANNEL_ID 		= 1;
	public static final int COLUMN_MONITOR_CHANNEL_ID 	= 2;
	public static final int COLUMN_TIME_SYNC 			= 3;
	public static final int COLUMN_STATUS_SYNC 			= 4;
	public static final int COLUMN_LAST_SYNC_TIME 		= 5;
	public static final int COLUMN_DOWNLOAD_ID		 	= 6;
	public static final int COLUMN_RENDER_ID		 	= 7;
	public static final int COLUMN_UPLOAD_ID		 	= 8;

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		super.createPartControl(parent);
		// Listener for server's notifications
		sessionListener = new SessionListener() {
			@Override
			public void notificationHandler(final SessionNotification n) {
				if (n.getCode() == SessionNotification.MAPPING_CHANNEL_CHANGED) {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							doQuery();
						}
					});
				}
			}
		};
		// Request server to lock user database, and on success refresh view
		new ConsoleJob("", this,
				Activator.PLUGIN_ID, null) {
			@Override
			protected void runInternal(IProgressMonitor monitor)
					throws Exception {
				runInUIThread(new Runnable() {
					@Override
					public void run() {
						session.addListener(sessionListener);
					}
				});
			}

			@Override
			protected void jobFailureHandler() {
				runInUIThread(new Runnable() {
					@Override
					public void run() {
						MappingChannelManagerView.this.getViewSite().getPage()
						.hideView(MappingChannelManagerView.this);
					}
				});
			}

			@Override
			protected String getErrorMessage() {
				return "Open monitor channel error!";
			}
		}.start();
	}
	
	@Override
	protected void fillLocalPullDown(IMenuManager manager) {
		super.fillLocalPullDown(manager);
		manager.add(actAddMapping);
		manager.add(new Separator());
		manager.add(actEditMapping);
		manager.add(new Separator());
		manager.add(actDeleteMapping);
	}

	@Override
	protected void fillContextMenu(IMenuManager manager) {
		super.fillContextMenu(manager);
		manager.add(actAddMapping);
		manager.add(actEditMapping);
		manager.add(actDeleteMapping);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	protected void fillLocalToolBar(IToolBarManager manager) {
		super.fillLocalToolBar(manager);
		manager.add(actAddMapping);
		manager.add(actEditMapping);
		manager.add(actDeleteMapping);
	}

	@Override
	protected void createActions() {
		// TODO Auto-generated method stub
		super.createActions();
		actAddMapping = new Action("Add new mapping channel", 
				Activator.getImageDescriptor("icons/account_add.png")) {
			public void run() {
				addMappingChannel();
			}
		};
		actAddMapping.setToolTipText("Add new mapping channel");

		actEditMapping = new Action("Edit mapping channel", 
				Activator.getImageDescriptor("icons/account_edit.png")) {
			public void run() {
				editMappingChannel();
			}
		};
		actEditMapping.setToolTipText("Edit mapping channel");

		actDeleteMapping = new Action("Delete mapping channel", 
				Activator.getImageDescriptor("icons/account_delete.png")) {
			public void run() {
				deleteMappingChannel();
			}
		};
		actDeleteMapping.setToolTipText("Delete mapping channel");
	}

	private void addMappingChannel()
	{
		final CreateMappingChannelDialog dlg = new CreateMappingChannelDialog(getViewSite().getShell());
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Create mapping channel",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					session.createMappingChannel(dlg.getHomeChannelId(), dlg.getMonitorChannelId(), (int)dlg.getTimeSync(), 
							dlg.getStatus(), 0, dlg.getDownloadClusterId(), dlg.getRenderClusterId(), dlg.getUploadClusterId());
				}

				@Override
				protected String getErrorMessage() {
					return "Can not create mapping channel";
				}
			}.start();
		}
	}

	private void editMappingChannel()
	{
		final TableItem[] selection = viewer.getTable().getSelection();
		if(selection.length <= 0)
		{
			MessageBox dialog =
					new MessageBox(getViewSite().getShell(), SWT.ICON_WARNING | SWT.OK);
			dialog.setText("Warning");
			dialog.setMessage("You must select at least one item to delete!");
			dialog.open();
			return;
		}
		MappingChannelObject objSelected = new MappingChannelObject(
				Integer.parseInt(selection[0].getText(COLUMN_ID)), 
				selection[0].getText(COLUMN_HOME_CHANNEL_ID),
				selection[0].getText(COLUMN_MONITOR_CHANNEL_ID),
				Long.parseLong(selection[0].getText(COLUMN_TIME_SYNC)),
				Integer.parseInt(selection[0].getText(COLUMN_STATUS_SYNC)),
				selection[0].getText(COLUMN_LAST_SYNC_TIME), 
				selection[0].getText(COLUMN_DOWNLOAD_ID), 
				selection[0].getText(COLUMN_RENDER_ID), 
				selection[0].getText(COLUMN_UPLOAD_ID));

		final EditMappingChannelDialog dlg = new EditMappingChannelDialog(getViewSite().getShell(), objSelected);
		if (dlg.open() == Window.OK) {
			try {
				session.modifyMappingChannel(dlg.getRecordId(), dlg.getHomeChannelId(), 
						dlg.getMonitorChannelId(), (int)dlg.getTimeSync(),dlg.getStatus(), 
						dlg.getDownloadClusterId(), dlg.getRenderClusterId(), dlg.getUploadClusterId());
			} catch (IOException | NXCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void deleteMappingChannel()
	{
		final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if(selection.size() <= 0)
		{
			MessageBox dialog =
					new MessageBox(getViewSite().getShell(), SWT.ICON_WARNING | SWT.OK);
			dialog.setText("Warning");
			dialog.setMessage("You must select at least one item to delete!");
			dialog.open();
			return;
		}
		MessageBox dialog =
				new MessageBox(getViewSite().getShell(), SWT.ICON_QUESTION | SWT.OK| SWT.CANCEL);
		dialog.setText("Confirm to delete item");
		dialog.setMessage("Do you really want to do delete this item?");
		if (dialog.open() == SWT.OK) {
			new ConsoleJob("Delete mapping channel", this,
					Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					for (Object object : selection.toList()) {
						int id = Integer.parseInt(((org.netxms.client.TableRow)object).get(COLUMN_ID).getValue());
						String downloadCluster = ((org.netxms.client.TableRow)object).get(COLUMN_DOWNLOAD_ID).getValue();
						session.deleteMappingChannel(id, downloadCluster);
						refreshData();
					}
				}

				@Override
				protected String getErrorMessage() {
					return "Can not delete mapping channel";
				}
			}.start();
		}
	}
}