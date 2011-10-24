package com.tan.bean;

public class UserTab {
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return the rowCount
	 */
	public long getRowCount() {
		return rowCount;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	private String tableName;
	
	private long rowCount;
}
