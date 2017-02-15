package com.saleschannel.api.inventory;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class BinContentJsonObject extends SalesChannelBaseJsonObject {

	//SC Bin Content Id
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
}
