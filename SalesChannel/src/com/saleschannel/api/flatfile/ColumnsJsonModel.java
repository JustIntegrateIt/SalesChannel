package com.saleschannel.api.flatfile;

public class ColumnsJsonModel {

	private String row1;
	
	private String row2;
	
	private String row3;
	
	private String dbModelName;
	
	private boolean isRequired;

	public String getRow1() {
		return row1;
	}

	public void setRow1(String row1) {
		this.row1 = row1;
	}

	public String getRow2() {
		return row2;
	}

	public void setRow2(String row2) {
		this.row2 = row2;
	}

	public String getRow3() {
		return row3;
	}

	public void setRow3(String row3) {
		this.row3 = row3;
	}

	public boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public String getDbModelName() {
		return dbModelName;
	}

	public void setDbModelName(String dbModelName) {
		this.dbModelName = dbModelName;
	}
}
