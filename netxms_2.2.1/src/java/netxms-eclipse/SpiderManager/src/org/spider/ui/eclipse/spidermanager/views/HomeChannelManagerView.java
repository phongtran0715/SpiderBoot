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
import org.netxms.ui.eclipse.spidermanager.dialogs.CreateHomeChannelDialog;
import org.netxms.ui.eclipse.spidermanager.dialogs.EditHomeChannelDialog;
import org.netxms.ui.eclipse.widgets.SortableTableViewer;
import org.spider.client.HomeChannelObject;
import org.spider.client.MonitorChannelObject;
import org.spider.ui.eclipse.spidermanager.Activator;
import org.spider.ui.eclipse.spidermanager.helper.HomeChannelLabelProvider;

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

public class HomeChannelManagerView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.spider.ui.eclipse.spidermanager.views.HomeChannelManager";

	private TableViewer viewer;
	private Action actAddHomeChannel;
	private Action actEditHomeChannel;
	private Action actDeleteHomeChannel;
	private RefreshAction actionRefresh;
	private NXCSession session;
	private SessionListener sessionListener;
	
	public static final int COLUMN_STT 				= 0;
	public static final int COLUMN_ID 				= 1;
	public static final int COLUMN_CHANNEL_ID 		= 2;
	public static final int COLUMN_CHANNEL_NAME 	= 3;
	public static final int COLUMN_GOOGLE_ACCOUNT 	= 4;
	public static final int COLUMN_VIDEO_INTRO 		= 5;
	public static final int COLUMN_VIDEO_OUTRO 		= 6;
	public static final int COLUMN_LOGO 			= 7;
	public static final int COLUMN_DESCRIPTION 		= 8;
	public static final int COLUMN_TITLE 			= 9;
	public static final int COLUMN_TAGS 			= 10;

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
	public HomeChannelManagerView() {
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
				"ChannelId",
				"ChannelName",
				"GoogleAccount",
				"VideoIntro",
				"VideoOutro",
				"Logo",
				"DescriptionTemplate", 
				"TitleTemplate",
				"TagsTemplate"
				};
		final int[] widths = { 60, 0, 160, 160, 160, 200, 200, 200, 200, 200, 200 };
		viewer = new SortableTableViewer(parent, names, widths, 0, SWT.UP, SortableTableViewer.DEFAULT_STYLE);

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new HomeChannelLabelProvider());
		viewer.setSorter(new NameSorter());
		try {
			viewer.setInput(session.getHomeChannel());
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
				if (n.getCode() == SessionNotification.HOME_CHANNEL_CHANGED) {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							try {
								viewer.setInput(session.getHomeChannel());
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
		new ConsoleJob("Refresh home channel list", this,
				Activator.PLUGIN_ID, null) {
			@Override
			protected void runInternal(IProgressMonitor monitor)
					throws Exception {
				runInUIThread(new Runnable() {
					@Override
					public void run() {
						try {
							viewer.setInput(session.getHomeChannel());
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
						HomeChannelManagerView.this.getViewSite().getPage()
						.hideView(HomeChannelManagerView.this);
					}
				});
			}

			@Override
			protected String getErrorMessage() {
				return "Open home channel error!";
			}
		}.start();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				HomeChannelManagerView.this.fillContextMenu(manager);
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
		manager.add(actAddHomeChannel);
		manager.add(new Separator());
		manager.add(actEditHomeChannel);
		manager.add(new Separator());
		manager.add(actDeleteHomeChannel);
		manager.add(new Separator());
		manager.add(actionRefresh);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(actAddHomeChannel);
		manager.add(actEditHomeChannel);
		manager.add(actDeleteHomeChannel);
		manager.add(actionRefresh);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actAddHomeChannel);
		manager.add(actEditHomeChannel);
		manager.add(actDeleteHomeChannel);
		manager.add(actionRefresh);
	}

	private void makeActions() {
		actAddHomeChannel = new Action("Add new home channel", 
				Activator.getImageDescriptor("icons/account_add.png")) {
			public void run() {
				addHomeChannel();
			}
		};
		actAddHomeChannel.setToolTipText("Add new home channel");

		actEditHomeChannel = new Action("Edit home channel", 
				Activator.getImageDescriptor("icons/account_edit.png")) {
			public void run() {
				modifyHomeChannel();
			}
		};
		actEditHomeChannel.setToolTipText("Edit home channel");

		actDeleteHomeChannel = new Action("Delete home channel", 
				Activator.getImageDescriptor("icons/account_delete.png")) {
			public void run() {
				try {
					deleteHomeChannel();
				} catch (IOException | NXCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		actDeleteHomeChannel.setToolTipText("Delete home channel");
		
		actionRefresh = new RefreshAction(this) {
			@Override
			public void run() {
				try {
					viewer.setInput(session.getHomeChannel());
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
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * Create new home channel
	 */
	private void addHomeChannel()
	{
		final CreateHomeChannelDialog dlg = new CreateHomeChannelDialog(getViewSite().getShell());
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Create home channel",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					session.createHomeCHannel(dlg.getcId(), dlg.getcName(), dlg.getgAccount(), 
							dlg.getvIntro(),dlg.getvOutro(),dlg.getLogo(),dlg.getDesc(),dlg.getTitle(), dlg.getTags());
				}

				@Override
				protected String getErrorMessage() {
					return "Can not create home channel";
				}
			}.start();
		}
	}

	/**
	 * Edit home channel
	 */
	private void modifyHomeChannel()
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
		if(firstElement instanceof HomeChannelObject)
		{
			final EditHomeChannelDialog dlg = new EditHomeChannelDialog(getViewSite().getShell(), 
					(HomeChannelObject)firstElement);
			if (dlg.open() == Window.OK) {
				new ConsoleJob("Edit home channel",
						this, Activator.PLUGIN_ID, null) {
					@Override
					protected void runInternal(IProgressMonitor monitor)
							throws Exception {
						session.modifyHomeCHannel(dlg.getId(), dlg.getcId(), dlg.getcName(), dlg.getgAccount(), 
								dlg.getvIntro(), dlg.getvOutro(), dlg.getLogo(), dlg.getDesc(), dlg.getTitle(),  "");
					}

					@Override
					protected String getErrorMessage() {
						return "Can not edit home channel";
					}
				}.start();
			}
		}

	}

	/**
	 * Delete home channel
	 * @throws NXCException 
	 * @throws IOException 
	 */
	private void deleteHomeChannel() throws IOException, NXCException
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
			new ConsoleJob("Delete home mapping", this,
					Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					for (Object object : selection.toList()) {					
						session.deleteHomeChannel(((HomeChannelObject)object).getId(), 
								((HomeChannelObject)object).getChannelId());
					}
				}

				@Override
				protected String getErrorMessage() {
					return "Can not delete home channel";
				}
			}.start();
		}
	}
}