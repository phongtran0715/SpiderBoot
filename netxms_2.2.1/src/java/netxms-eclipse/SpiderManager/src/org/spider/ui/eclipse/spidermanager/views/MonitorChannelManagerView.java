package org.spider.ui.eclipse.spidermanager.views;

import java.io.IOException;

import org.eclipse.swt.program.Program;
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
import org.netxms.ui.eclipse.spidermanager.dialogs.CreateMonitorChannelDialog;
import org.netxms.ui.eclipse.spidermanager.dialogs.EditMonitorChannelDialog;
import org.spider.client.MonitorChannelObject;
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

public class MonitorChannelManagerView extends LogViewer {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.spider.ui.eclipse.spidermanager.views.MonitorChannelManager";

	private Action actAddMonitorChannel;
	private Action actEditMonitorChannel;
	private Action actDeleteMonitorChannel;
	private Action actViewChannel;
	private SessionListener sessionListener;

	public static final int COLUMN_ID 				= 0;
	public static final int COLUMN_CHANNEL_ID 		= 1;
	public static final int COLUMN_CHANNEL_NAME 	= 2;

	public MonitorChannelManagerView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		super.createPartControl(parent);
		// Listener for server's notifications
		sessionListener = new SessionListener() {
			@Override
			public void notificationHandler(final SessionNotification n) {
				if (n.getCode() == SessionNotification.MONITOR_CHANNEL_CHANGED) {
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
						MonitorChannelManagerView.this.getViewSite().getPage()
						.hideView(MonitorChannelManagerView.this);
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
		manager.add(actAddMonitorChannel);
		manager.add(new Separator());
		manager.add(actEditMonitorChannel);
		manager.add(new Separator());
		manager.add(actDeleteMonitorChannel);
	}

	@Override
	protected void fillContextMenu(IMenuManager manager) {
		super.fillContextMenu(manager);
		manager.add(actAddMonitorChannel);
		manager.add(actEditMonitorChannel);
		manager.add(actDeleteMonitorChannel);
		manager.add(new Separator());
		manager.add(actViewChannel);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	protected void fillLocalToolBar(IToolBarManager manager) {
		super.fillLocalToolBar(manager);
		manager.add(actAddMonitorChannel);
		manager.add(actEditMonitorChannel);
		manager.add(actDeleteMonitorChannel);
	}


	@Override
	protected void createActions() {
		// TODO Auto-generated method stub
		super.createActions();
		actAddMonitorChannel = new Action("Add new monitor channel", 
				Activator.getImageDescriptor("icons/account_add.png")) {
			public void run() {
				addMonitorChannel();
			}
		};
		actAddMonitorChannel.setToolTipText("Add new monitor channel");

		actEditMonitorChannel = new Action("Edit monitor channel", 
				Activator.getImageDescriptor("icons/account_edit.png")) {
			public void run() {
				modifyMonitorChannel();
			}
		};
		actEditMonitorChannel.setToolTipText("Edit monitor channel");

		actDeleteMonitorChannel = new Action("Delete monitor channel", 
				Activator.getImageDescriptor("icons/account_delete.png")) {
			public void run() {
				try {
					deleteMonitorChannel();
				} catch (IOException | NXCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		actDeleteMonitorChannel.setToolTipText("Delete monitor channel");
		
		actViewChannel = new Action("View monitor channel", 
				Activator.getImageDescriptor("icons/eye_16x16.png")) {
			public void run() {
				viewMonitorChannel();
			}
		};
		actDeleteMonitorChannel.setToolTipText("View monitor channel");
	}

	private void addMonitorChannel()
	{
		final CreateMonitorChannelDialog dlg = new CreateMonitorChannelDialog(getViewSite().getShell());
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Create monitor channel",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					session.createMonitorChannel(dlg.getChannelId(), dlg.getChannelName());
				}

				@Override
				protected String getErrorMessage() {
					return "Can not create monitor channel";
				}
			}.start();
		}
	}

	private void modifyMonitorChannel()
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
		MonitorChannelObject selectedObj = new MonitorChannelObject(
				Integer.parseInt(selection[0].getText(COLUMN_ID)),
				selection[0].getText(COLUMN_CHANNEL_ID), 
				selection[0].getText(COLUMN_CHANNEL_NAME));

		final EditMonitorChannelDialog dlg = new EditMonitorChannelDialog(getViewSite().getShell(), selectedObj);
		if (dlg.open() == Window.OK) {
			try {
				session.modifyMonitorChannel(dlg.getId(), dlg.getChannelId(), dlg.getChannelName());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NXCException e) {
				e.printStackTrace();
			}
		}
	}

	private void deleteMonitorChannel() throws IOException, NXCException
	{
		final IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
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
			new ConsoleJob("Delete monitor channel", this,
					Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					for (Object object : selection.toList()) {
						int id = Integer.parseInt(((org.netxms.client.TableRow)object).get(COLUMN_ID).getValue());
						String channelId = ((org.netxms.client.TableRow)object).get(COLUMN_CHANNEL_ID).getValue();
						session.deleteMonitorChannel(id, channelId);
						refreshData();
					}
				}

				@Override
				protected String getErrorMessage() {
					return "Can not delete monitor channel";
				}
			}.start();
		}
	}
	
	private void viewMonitorChannel()
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
		String channelId = selection[0].getText(COLUMN_CHANNEL_ID);
		System.out.println("Channel Id = " + channelId);
		Program.launch("https://www.youtube.com/channel/" + channelId);
	}
}