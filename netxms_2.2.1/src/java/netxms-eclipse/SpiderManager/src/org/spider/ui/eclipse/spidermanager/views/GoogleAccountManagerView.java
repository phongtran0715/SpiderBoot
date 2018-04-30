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
import org.netxms.ui.eclipse.jobs.ConsoleJob;
import org.netxms.ui.eclipse.shared.ConsoleSharedData;
import org.netxms.ui.eclipse.spidermanager.dialogs.CreateGoogleAccoutDialog;
import org.netxms.ui.eclipse.spidermanager.dialogs.EditGoocleAccountDialog;
import org.netxms.ui.eclipse.widgets.SortableTableViewer;
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

public class GoogleAccountManagerView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.spider.ui.eclipse.spidermanager.views.GoogleAccountManager";

	private SortableTableViewer viewer;
	private Action addNewAccount;
	private Action editAccount;
	private Action deleteAccount;
	private NXCSession session;
	private SessionListener sessionListener;

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
		final String[] names = { "Id",
				"UserName",
				"Api",
				"ClientSecrect",
				"AccountType",
		"AppName"};
		final int[] widths = { 80, 160, 160, 160, 160, 160 };
		viewer = new SortableTableViewer(parent, names, widths, 0, SWT.UP, SortableTableViewer.DEFAULT_STYLE);

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());

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
				if (n.getCode() == SessionNotification.USER_DB_CHANGED) {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							//TODO : implement to handle server information
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
			}
		};
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
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(addNewAccount);
		manager.add(editAccount);
		manager.add(deleteAccount);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(addNewAccount);
		manager.add(editAccount);
		manager.add(deleteAccount);
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
				editAccount();
			}
		};
		editAccount.setToolTipText("Edit account");

		deleteAccount = new Action("delete_account", 
				Activator.getImageDescriptor("icons/account_delete.png")) {
			public void run() {
				deleteAccount();
			}
		};
		deleteAccount.setToolTipText("Delete account");
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
					//TODO : implement to send information to server
					//session.createUser(dlg.getLoginName());
				}

				@Override
				protected String getErrorMessage() {
					return "Can not create user";
				}
			}.start();
		}
	}

	/**
	 * Edit account
	 */
	private void editAccount() {
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

		final EditGoocleAccountDialog dlg = new EditGoocleAccountDialog(getViewSite().getShell());	
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Edit google account",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					//TODO : implement to send information to server
					//session.createUser(dlg.getLoginName());
				}

				@Override
				protected String getErrorMessage() {
					return "Cannot edit user";
				}
			}.start();
		}
	}

	/**
	 * Delete account
	 */
	private void deleteAccount()
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
		if (dialog.open() == Window.OK) {
		}
	}
}