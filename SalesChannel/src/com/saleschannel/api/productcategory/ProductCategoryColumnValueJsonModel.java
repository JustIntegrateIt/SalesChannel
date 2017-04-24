package com.saleschannel.api.productcategory;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class ProductCategoryColumnValueJsonModel {

	//SC Product Category Column Value Id
	@Id
	private String id;
	
	//SC Product Id
	private String productId;
	
	//SC Product Category Column Value
	private String value;
	
	//SC Product Category Column Parameter Id
	private String categoryColumnParameterId;
	
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCategoryColumnParameterId() {
		return categoryColumnParameterId;
	}

	public void setCategoryColumnParameterId(String categoryColumnParameterId) {
		this.categoryColumnParameterId = categoryColumnParameterId;
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
