package com.tan.swing;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tan.bean.UserTab;
import com.tan.dbunit.oracle.OracleUtil;

public class ListTest extends JFrame {
	private static final long serialVersionUID = 2895333976395911101L;

	private List<UserTab> tabs;
	private JScrollPane scrollpane;
	
	public ListTest() {
		// init tables
		initTables();

		initComponents();
	}


	private void initTables() {
		tabs = new OracleUtil().allTableNameAndRowCount();
		String[] headers = { "表名", "记录数" };
		Object[][] cellData = null;
		DefaultTableModel model = new DefaultTableModel(cellData, headers) {
			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};
		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		if (null != tabs && tabs.size() > 0) {
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setRowCount(0);// 清除原有行

			// fill data.
			for (final UserTab tab : tabs) {
				String[] arr = new String[2];

				arr[0] = tab.getTableName();
				arr[1] = String.valueOf(tab.getRowCount());

				tableModel.addRow(arr);
			}

			// 更新表格
			table.invalidate();
		}
		scrollpane = new JScrollPane(table);
	}

	private void initComponents() {
		setVisible(true);
		setBounds(533, 333, 400, 500);
		add(scrollpane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ListTest();
	}

}