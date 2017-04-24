package com.saleschannel.api.productcategory;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class CategoryColumnValidValuesJsonModel {

	//SC Category Column Valid Values Id
	@Id
	private String id;
	
	//Amazon Category Column Name
	private String columnName;
	
	//Amazon Category Name
	private String name;
	
	//Amazon Category Column Valid Values
	private List<String> validValues;

	private String createBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;
	
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
