package spiderboot.helper;

import javax.swing.JTable;

public class TableHelper {
	public void setColumnWidth(JTable table, String columnName, int minWidth, int maxwidth){
		table.getColumn(columnName).setMinWidth(minWidth);
		table.getColumn(columnName).setMaxWidth(maxwidth);
	}
}

