package com.saleschannel.api.productcategory;

import org.springframework.data.annotation.Id;

public class ProductCategoryColumnValueJsonObject {

	//SC Product Category Column Value Id
	@Id
	private String id;
	
	//SC Product Id
	private String productId;
	
	//SC Product Category Column Value
	private String value;
	
	//SC Product Category Column Parameter Id
	private String categoryColumnParameterId;
	
	//SC Product Category Column Parameter Name
	private String categoryColumnParameterName;

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

	public String getCategoryColumnParameterName() {
		return categoryColumnParameterName;
	}

	public void setCategoryColumnParameterName(String categoryColumnParameterName) {
		this.categoryColumnParameterName = categoryColumnParameterName;
	}
}
