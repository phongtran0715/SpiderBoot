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
import org.netxms.ui.eclipse.spidermanager.dialogs.CreateGoogleAccoutDialog;
import org.netxms.ui.eclipse.spidermanager.dialogs.EditGoogleAccoutDialog;
import org.spider.client.GoogleAccountObject;
import org.spider.ui.eclipse.spidermanager.Activator;
import org.netxms.ui.eclipse.logviewer.views.LogViewer;

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

//public class GoogleAccountManagerView extends ViewPart {
public class GoogleAccountManagerView extends LogViewer {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.spider.ui.eclipse.spidermanager.views.GoogleAccountManager";

	private Action addNewAccount;
	private Action editAccount;
	private Action deleteAccount;
	private SessionListener sessionListener;

	// Columns
	public static final int COLUMN_ID 				= 0;
	public static final int COLUMN_USER_NAME 		= 1;
	public static final int COLUMN_API 				= 2;
	public static final int COLUMN_CLIENT_SECRET 	= 3;
	public static final int COLUMN_CLIENT_ID	 	= 4;
	public static final int COLUMN_ACCOUNT_TYPE 	= 5;
	public static final int COLUMN_APPNAME 			= 6;


	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		super.createPartControl(parent);
		// Listener for server's notifications
		sessionListener = new SessionListener() {
			@Override
			public void notificationHandler(final SessionNotification n) {
				if (n.getCode() == SessionNotification.GOOGLE_ACCOUNT_CHANGED) {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							System.out.println("GOOGLE_ACCOUNT_CHANGED");
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


	public GoogleAccountManagerView() {
		super();
	}


	@Override
	protected void createActions() {
		super.createActions();
		addNewAccount = new Action("Create new account", 
				Activator.getImageDescriptor("icons/account_add.png")) {
			public void run() {
				addNewAccount();
			}
		};
		addNewAccount.setToolTipText("Create new account");

		editAccount = new Action("edit_account", 
				Activator.getImageDescriptor("icons/account_edit.png")) {
			public void run() {
				modifyAccount();
			}
		};
		editAccount.setToolTipText("Modify account");

		deleteAccount = new Action("Delete account", 
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
	}

	@Override
	protected void fillLocalPullDown(IMenuManager manager) {
		super.fillLocalPullDown(manager);
		manager.add(addNewAccount);
		manager.add(new Separator());
		manager.add(editAccount);
		manager.add(new Separator());
		manager.add(deleteAccount);
	}

	@Override
	protected void fillContextMenu(IMenuManager manager) {
		super.fillContextMenu(manager);
		manager.add(addNewAccount);
		manager.add(editAccount);
		manager.add(deleteAccount);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	@Override
	protected void fillLocalToolBar(IToolBarManager manager) {
		super.fillLocalToolBar(manager);
		manager.add(addNewAccount);
		manager.add(editAccount);
		manager.add(deleteAccount);
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
							dlg.getClientSecret(), dlg.getClientId(), dlg.getAccountType(), dlg.getAppName());
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
		GoogleAccountObject selectedObj = new GoogleAccountObject(
				Integer.parseInt(selection[0].getText(COLUMN_ID)),
				selection[0].getText(COLUMN_USER_NAME),
				selection[0].getText(COLUMN_API),
				selection[0].getText(COLUMN_CLIENT_SECRET),
				selection[0].getText(COLUMN_CLIENT_ID),
				Integer.parseInt(selection[0].getText(COLUMN_ACCOUNT_TYPE)),
				selection[0].getText(COLUMN_APPNAME));

		final EditGoogleAccoutDialog dlg = new EditGoogleAccoutDialog(getViewSite().getShell(), selectedObj);	
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Modify google account",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					session.modifyGoogleAccount(dlg.getId(), dlg.getUserName(), dlg.getApiKey(), 
							dlg.getClientSecret(), dlg.getClientId(), dlg.getAccountType(),dlg.getAppName());
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
						System.out.println("delete home channel");
						System.out.println(((org.netxms.client.TableRow)object).get(COLUMN_ID).getValue());
						int id = Integer.parseInt(((org.netxms.client.TableRow)object).get(COLUMN_ID).getValue());
						session.deleteGoogleAccount(id);
						refreshData();
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