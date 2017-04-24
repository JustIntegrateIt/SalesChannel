package com.saleschannel.api.productcategory;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class ProductCategoryColumnParametersJsonModel {

	//SC Product Category Column Parameters Id
	@Id
	private String id;
	
	//Amazon Product Category Column Parameter Name
	private String columnName;
	
	//Amazon Product Category Column Name
	private String name;
		
	//SC Category Id
	private String categoryId;
	
	//SC Market Place Id
	private String marketPlaceId;
	
	//Field isRequired
	private boolean isRequired;
	
	//Field Data Type isRequired
	private String fieldType;
	
	//Field Maximum length
	private int maxLength;
		
	//List of Valid Values
	private List<String> validValues;
	
	//Is Amazon Product Category Column Valid Values Available
	private boolean defaultValues;
	
	//Is Amazon Product Category Column Value allows decimal point
	private boolean isDecimal;
	
	//Is Amazon Product Category Column Value allows digits before decimal point
	private int before;
	
	//Is Amazon Product Category Column Value allows digits after decimal point
	private int after;
	
	//List of sub categories that required this column Value
	private List<String> feedProductType;
	
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getMarketPlaceId() {
		return marketPlaceId;
	}

	public void setMarketPlaceId(String marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setIsRequired(boolean isRequired) {
		this.isRequired = isRequired;
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

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public List<String> getValidValues() {
		return validValues;
	}

	public void setValidValues(List<String> validValues) {
		this.validValues = validValues;
	}

	public boolean isDefaultValues() {
		return defaultValues;
	}

	public void setDefaultValues(boolean defaultValues) {
		this.defaultValues = defaultValues;
	}

	public boolean isDecimal() {
		return isDecimal;
	}

	public void setIsDecimal(boolean isDecimal) {
		this.isDecimal = isDecimal;
	}

	public int getBefore() {
		return before;
	}

	public void setBefore(int before) {
		this.before = before;
	}

	public int getAfter() {
		return after;
	}

	public void setAfter(int after) {
		this.after = after;
	}

	public List<String> getFeedProductType() {
		return feedProductType;
	}

	public void setFeedProductType(List<String> feedProductType) {
		this.feedProductType = feedProductType;
	}
}
