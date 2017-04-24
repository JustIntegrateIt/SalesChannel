package com.saleschannel.api.productcategory;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class CategoryColumnValidValuesJsonObject extends SalesChannelBaseJsonObject {

	//SC Category Column Valid Values Id
	private String id;
	
	//Amazon Category Column Name
	private String columnName;
	
	//Amazon Category Name
	private String name;
	
	//Amazon Category Column Valid Values
	private List<String> validValues;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValidValues() {
		return validValues;
	}

	public void setValidValues(List<String> validValues) {
		this.validValues = validValues;
	}
}
