package com.saleschannel.api.inventory;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class BinContentJsonModel {

	//SC Bin Content Id
	@Id
	private String id;
	
	//SC Product Id
	private String productId;
	
	//SC Bin Id
	private String binId;

	//SC Product Quantity
	private Integer quantity;
	
	//SC Product cost
	private Integer cost;
	
	//SC Product Attributes Id
	private String productAttributesId;
	
	//SC Customer Id
	private String customerId;

	private String createdBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getBinId() {
		return binId;
	}

	public void setBinId(String binId) {
		this.binId = binId;
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

	public String getProductAttributesId() {
		return productAttributesId;
	}

	public void setProductAttributesId(String productAttributesId) {
		this.productAttributesId = productAttributesId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
