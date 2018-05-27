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
import org.netxms.ui.eclipse.spidermanager.dialogs.CreateGoogleAccoutDialog;
import org.netxms.ui.eclipse.spidermanager.dialogs.EditGoogleAccoutDialog;
import org.netxms.ui.eclipse.widgets.SortableTableViewer;
import org.spider.base.SpiderCodes;
import org.spider.client.ClusterObject;
import org.spider.client.GoogleAccountObject;
import org.spider.ui.eclipse.spidermanager.Activator;
import org.spider.ui.eclipse.spidermanager.helper.GoogleAccountLabelProvider;

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

public class GoogleAccountManagerView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.spider.ui.eclipse.spidermanager.views.GoogleAccountManager";

	private SortableTableViewer viewer;
	private Action addNewAccount;
	private Action editAccount;
	private Action deleteAccount;
	private RefreshAction actionRefresh;
	private NXCSession session;
	private SessionListener sessionListener;

	// Columns
	public static final int COLUMN_STT 				= 0;
	public static final int COLUMN_ID 				= 1;
	public static final int COLUMN_USER_NAME 		= 2;
	public static final int COLUMN_API 				= 3;
	public static final int COLUMN_CLIENT_SECRET 	= 4;
	public static final int COLUMN_ACCOUNT_TYPE 	= 5;
	public static final int COLUMN_APPNAME 			= 6;

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
	public GoogleAccountManagerView() {
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
				"UserName",
				"Api",
				"ClientSecrect",
				"AccountType",
		"AppName"};
		final int[] widths = { 60, 0, 160, 160, 160, 160, 160 };
		viewer = new SortableTableViewer(parent, names, widths, 0, SWT.UP, SortableTableViewer.DEFAULT_STYLE);

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new GoogleAccountLabelProvider());
		//viewer.setSorter(new NameSorter());
		try {
			viewer.setInput(session.getGoogleAccount());
		} catch (IOException | NXCException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Create the help context id for the viewer's control
		PlatformUI
		.getWorkbench()
		.getHelpSystem()
		.setHelp(viewer.getControl(),
				"org.spider.ui.eclipse.spider.viewer");
		makeActions();
		hookContextMenu();
		contributeToActionBars();

		// Listener for server's notifications
		sessionListener = new SessionListener() {
			@Override
			public void notificationHandler(final SessionNotification n) {
				if (n.getCode() == SessionNotification.GOOGLE_ACCOUNT_CHANGED) {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							try {
								viewer.setInput(session.getGoogleAccount());
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
		new ConsoleJob("", this,
				Activator.PLUGIN_ID, null) {
			@Override
			protected void runInternal(IProgressMonitor monitor)
					throws Exception {
				runInUIThread(new Runnable() {
					@Override
					public void run() {
						try {
							viewer.setInput(session.getGoogleAccount());
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
						GoogleAccountManagerView.this.getViewSite().getPage()
						.hideView(GoogleAccountManagerView.this);
					}
				});
			}

			@Override
			protected String getErrorMessage() {
				return "Open google account error!";
			}
		}.start();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				GoogleAccountManagerView.this.fillContextMenu(manager);
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
		manager.add(addNewAccount);
		manager.add(new Separator());
		manager.add(editAccount);
		manager.add(new Separator());
		manager.add(deleteAccount);
		manager.add(new Separator());
		manager.add(actionRefresh);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(addNewAccount);
		manager.add(editAccount);
		manager.add(deleteAccount);
		manager.add(actionRefresh);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(addNewAccount);
		manager.add(editAccount);
		manager.add(deleteAccount);
		manager.add(actionRefresh);
	}

	private void makeActions() {
		addNewAccount = new Action("add_account", 
				Activator.getImageDescriptor("icons/account_add.png")) {
			public void run() {
				addNewAccount();
			}
		};
		addNewAccount.setToolTipText("Add account");

		editAccount = new Action("edit_account", 
				Activator.getImageDescriptor("icons/account_edit.png")) {
			public void run() {
				modifyAccount();
			}
		};
		editAccount.setToolTipText("Edit account");

		deleteAccount = new Action("delete_account", 
				Activator.getImageDescriptor("icons/account_delete.png")) {
			public void run() {
				try {
					deleteAccount();
				} catch (NXCException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		deleteAccount.setToolTipText("Delete account");

		actionRefresh = new RefreshAction(this) {
			@Override
			public void run() {
				try {
					viewer.setInput(session.getGoogleAccount());
				} catch (IOException | NXCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * Add new account
	 */
	private void addNewAccount() {
		final CreateGoogleAccoutDialog dlg = new CreateGoogleAccoutDialog(getViewSite().getShell());
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Create user",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					session.createGoogleAccount(dlg.getUserName(), dlg.getApiKey(), 
							dlg.getClientSecret(), dlg.getAccountType(), dlg.getAppName());
				}

				@Override
				protected String getErrorMessage() {
					System.out.println("Can not create user");
					return "Can not create user";
				}
			}.start();
		}
	}

	/**
	 * Edit account
	 */
	private void modifyAccount() {
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
		final Object firstElement = selection.getFirstElement();
		final EditGoogleAccoutDialog dlg = new EditGoogleAccoutDialog(getViewSite().getShell(), (GoogleAccountObject)firstElement);	
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Modify google account",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					session.modifyGoogleAccount(dlg.getId(), dlg.getUserName(), dlg.getApiKey(), 
							dlg.getClientSecret(), dlg.getAccountType(),dlg.getAppName());
				}

				@Override
				protected String getErrorMessage() {
					System.out.println("Cannot modify google account");
					return "Cannot modify google account";
				}
			}.start();
		}
	}

	private void deleteAccount() throws NXCException, IOException
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
			new ConsoleJob("Delete google account", this,
					Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					for (Object object : selection.toList()) {					
						session.deleteCluster(((ClusterObject)object).getRecordID(), 
								SpiderCodes.CLUSTER_DOWNLOAD);
						session.deleteGoogleAccount(((GoogleAccountObject)object).getId());
					}
				}

				@Override
				protected String getErrorMessage() {
					return "Can not delete google account";
				}
			}.start();
		}
	}
}