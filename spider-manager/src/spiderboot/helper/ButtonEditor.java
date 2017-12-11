package spiderboot.helper;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import spiderboot.view.ChannelManagerForm;

//BUTTON EDITOR CLASS
public class ButtonEditor extends DefaultCellEditor
{
	private static final long serialVersionUID = -2742619341932446079L;
	protected JButton btn;
	private String lbl;
	private Boolean clicked;
	ChannelManagerForm cManagerFrm;

	public ButtonEditor(JTextField txt, ChannelManagerForm cManagerFrm) {
		super(txt);

		this.cManagerFrm = cManagerFrm;
		btn=new JButton();
		btn.setOpaque(true);

		//WHEN BUTTON IS CLICKED
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	//OVERRIDE A COUPLE OF METHODS
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj,
			boolean selected, int row, int col) {
		//SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
		lbl=(obj==null) ? "":obj.toString();
		btn.setText(lbl);
		clicked=true;
		return btn;
	}
	//IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
	@Override
	public Object getCellEditorValue() {
		if(clicked)
		{
			//SHOW US SOME MESSAGE
			//JOptionPane.showMessageDialog(btn, lbl+" Clicked");
		}
		//SET IT TO FALSE NOW THAT ITS CLICKED
		clicked=false;
		return new String(lbl);
	}

	@Override
	public boolean stopCellEditing() {
		//SET CLICKED TO FALSE FIRST
		clicked=false;
		return super.stopCellEditing();
	}

	@Override
	protected void fireEditingStopped() {
		// TODO Auto-generated method stub
		super.fireEditingStopped();
		boolean isSuccess;
		String status = btn.getText().trim();
		if(status.equals("Run")){
			//Start sync thread
			isSuccess = cManagerFrm.startSyncThread();
			if(isSuccess){
				btn.setText("Stop");
			}
		}else{
			//Stop sync thread
			isSuccess = cManagerFrm.stopSyncThread();
			if(isSuccess){
				btn.setText("Run");
			}
		}
	}
}