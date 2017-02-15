package com.saleschannel.api.product;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ProductAttributesJsonObject extends SalesChannelBaseJsonObject {

	//SC Product Attribute Id
	private String productAttributeId;
	
	private List<ProductAttributeSetJsonObject> productAttributes;

	public String getProductAttributeId() {
		return productAttributeId;
	}

	public void setProductAttributeId(String productAttributeId) {
		this.productAttributeId = productAttributeId;
	}

	public List<ProductAttributeSetJsonObject> getProductAttributes() {
		return productAttributes;
	}

	public void setProductAttributes(List<ProductAttributeSetJsonObject> productAttributes) {
		this.productAttributes = productAttributes;
	}

}
