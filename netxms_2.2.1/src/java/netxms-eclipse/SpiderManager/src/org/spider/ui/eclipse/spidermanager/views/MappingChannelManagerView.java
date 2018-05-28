package org.spider.ui.eclipse.spidermanager.views;

import java.io.IOException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.part.*;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.netxms.client.NXCException;
import org.netxms.client.NXCSession;
import org.netxms.client.SessionListener;
import org.netxms.client.SessionNotification;
import org.netxms.ui.eclipse.actions.RefreshAction;
import org.netxms.ui.eclipse.jobs.ConsoleJob;
import org.netxms.ui.eclipse.shared.ConsoleSharedData;
import org.netxms.ui.eclipse.spidermanager.dialogs.CreateMappingChannelDialog;
import org.netxms.ui.eclipse.spidermanager.dialogs.EditMappingChannelDialog;
import org.netxms.ui.eclipse.widgets.SortableTableViewer;
import org.spider.client.HomeChannelObject;
import org.spider.client.MappingChannelObject;
import org.spider.client.MonitorChannelObject;
import org.spider.ui.eclipse.spidermanager.Activator;
import org.spider.ui.eclipse.spidermanager.helper.MappingChannelLabelProvider;

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

public class MappingChannelManagerView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.spider.ui.eclipse.spidermanager.views.MappingChannelManager";

	private TableViewer viewer;
	private Action actAddMapping;
	private Action actEditMapping;
	private Action actDeleteMapping;
	private RefreshAction actionRefresh;
	private NXCSession session;
	private SessionListener sessionListener;

	public static final int COLUMN_STT 					= 0;
	public static final int COLUMN_ID 					= 1;
	public static final int COLUMN_HOME_CHANNEL_ID 		= 2;
	public static final int COLUMN_HOME_CHANNEL_NAME 	= 3;
	public static final int COLUMN_MONITOR_CHANNEL_ID 	= 4;
	public static final int COLUMN_MONITOR_CHANNEL_NAME = 5;
	public static final int COLUMN_TIME_SYNC 			= 6;
	public static final int COLUMN_STATUS_SYNC 			= 7;
	public static final int COLUMN_LAST_SYNC_TIME 		= 8;
	public static final int COLUMN_DOWNLOAD_ID		 	= 9;
	public static final int COLUMN_RENDER_ID		 	= 10;
	public static final int COLUMN_UPLOAD_ID		 	= 11;

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */

	class ViewLabelProvider extends LabelProvider implements
	ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public MappingChannelManagerView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		session = ConsoleSharedData.getSession();
		final String[] names = { 
				"STT",
				"Id",
				"HomeChannelId",
				"HomeChannelName",
				"MonitorChannelId",
				"MonitorChannelName",
				"TimeInterval",
				"StatusSync",
				"LastSyncTime",
				"DownloadClusterId", 
				"ProcessClusterId",
		"UploadClusterId"};
		final int[] widths = {60, 0, 240, 240, 240, 240, 120, 120, 160, 160, 160, 160 };
		viewer = new SortableTableViewer(parent, names, widths, 0, SWT.UP, SortableTableViewer.DEFAULT_STYLE);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new MappingChannelLabelProvider());
		viewer.setSorter(new NameSorter());
		try {
			viewer.setInput(session.getMappingChannelList());
		} catch (IOException | NXCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create the help context id for the viewer's control
		PlatformUI
		.getWorkbench()
		.getHelpSystem()
		.setHelp(viewer.getControl(),
				"org.spider.ui.eclipse.spider.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		// Listener for server's notifications
		sessionListener = new SessionListener() {
			@Override
			public void notificationHandler(final SessionNotification n) {
				if (n.getCode() == SessionNotification.MAPPING_CHANNEL_CHANGED) {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							try {
								viewer.setInput(session.getMappingChannelList());
							} catch (IOException | NXCException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			}
		};

		// Request server to lock user database, and on success refresh view
		new ConsoleJob("Refresh mapping channel list", this,
				Activator.PLUGIN_ID, null) {
			@Override
			protected void runInternal(IProgressMonitor monitor)
					throws Exception {
				runInUIThread(new Runnable() {
					@Override
					public void run() {
						try {
							viewer.setInput(session.getMappingChannelList());
						} catch (IOException | NXCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
				return "Open mapping channel error!";
			}
		}.start();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				MappingChannelManagerView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(actAddMapping);
		manager.add(new Separator());
		manager.add(actEditMapping);
		manager.add(new Separator());
		manager.add(actDeleteMapping);
		manager.add(new Separator());
		manager.add(actionRefresh);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(actAddMapping);
		manager.add(actEditMapping);
		manager.add(actDeleteMapping);
		manager.add(actionRefresh);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actAddMapping);
		manager.add(actEditMapping);
		manager.add(actDeleteMapping);
		manager.add(actionRefresh);
	}

	private void makeActions() {
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

		actionRefresh = new RefreshAction(this) {
			@Override
			public void run() {
				try {
					viewer.setInput(session.getMappingChannelList());
				} catch (IOException | NXCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
			}
		});
	}

	/**
	 * Create new mapping channel
	 */
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

	/**
	 * Edit mapping channel
	 */
	private void editMappingChannel()
	{
		final IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if(selection.size() <= 0)
		{
			MessageBox dialog =
					new MessageBox(getViewSite().getShell(), SWT.ICON_WARNING | SWT.OK);
			dialog.setText("Warning");
			dialog.setMessage("You must select at least one item to modify!");
			dialog.open();
			return;
		}
		Object firstElement = selection.getFirstElement();
		if(firstElement instanceof MappingChannelObject)
		{
			final EditMappingChannelDialog dlg = new EditMappingChannelDialog(getViewSite().getShell(), 
					(MappingChannelObject)firstElement);
			if (dlg.open() == Window.OK) {
				new ConsoleJob("Edit mapping channel",
						this, Activator.PLUGIN_ID, null) {
					@Override
					protected void runInternal(IProgressMonitor monitor)
							throws Exception {
						session.modifyMappingChannel(dlg.getRecordId(), dlg.getHomeChannelId(), 
								dlg.getMonitorChannelId(), (int)dlg.getTimeSync(),dlg.getStatus(), 
								dlg.getDownloadClusterId(), dlg.getRenderClusterId(), dlg.getUploadClusterId());
					}

					@Override
					protected String getErrorMessage() {
						return "Can not edit mapping channel";
					}
				}.start();
			}
		}
	}

	/**
	 * Delete home channel
	 */
	private void deleteMappingChannel()
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
			Object firstElement = selection.getFirstElement();
			if(firstElement instanceof MappingChannelObject)
			{
				MappingChannelObject object = (MappingChannelObject)firstElement;
				if(object.getStatusSync() == 1)
				{
					dialog =
							new MessageBox(getViewSite().getShell(), SWT.ICON_WARNING | SWT.OK);
					dialog.setText("Error");
					dialog.setMessage("Can not delete mapping channel with sync status is enable. " +
							"Please set sync status to disable and try again!");
					dialog.open();
					return;	
				}
				
				new ConsoleJob("Delete channel mapping", this,
						Activator.PLUGIN_ID, null) {
					@Override
					protected void runInternal(IProgressMonitor monitor)
							throws Exception {
						for (Object object : selection.toList()) {					
							session.deleteMappingChannel(((MappingChannelObject)object).getId(), ((MappingChannelObject)object).getDownloadClusterId());
						}
					}

					@Override
					protected String getErrorMessage() {
						return "Can not delete channel mapping";
					}
				}.start();
			}
		}
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}