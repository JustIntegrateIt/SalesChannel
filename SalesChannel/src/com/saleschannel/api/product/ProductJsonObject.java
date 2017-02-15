package com.saleschannel.api.product;

import java.util.List;

public class ProductJsonObject {

	//SC Product Id
	private String id;
	
	//SC Customer Id
	private String customerId;

	//SC Product Name
	private String productName;

	//SC Product Type
	private String productType;
	
	//SC Product Category
	private String productCategory;
	
	//SC Product Description
	private String description;
	
	//SC Product Quantity
	private Integer quantity;
	
	//SC Product Cost
	private Integer cost;
	
	//SC Product skuId
	private String skuId;
	
	//SC Product image
	private String image;
	
	//POJO to map Product Attributes request
	private List<ProductAttributeSetModel> productAttributes;
	
	//POJO to map Product Accessories request
	private List<ProductAttributeSetJsonObject> productAccessories;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public List<ProductAttributeSetModel> getProductAttributes() {
		return productAttributes;
	}

	public void setProductAttributes(
			List<ProductAttributeSetModel> productAttributes) {
		this.productAttributes = productAttributes;
	}

	public List<ProductAttributeSetJsonObject> getProductAccessories() {
		return productAccessories;
	}

	public void setProductAccessories(
			List<ProductAttributeSetJsonObject> productAccessories) {
		this.productAccessories = productAccessories;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
