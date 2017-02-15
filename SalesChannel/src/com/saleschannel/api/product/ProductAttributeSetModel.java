package com.saleschannel.api.product;

import java.util.List;

import com.saleschannel.api.inventory.BinContentJsonObject;

public class ProductAttributeSetModel {

	//SC Product Attribute Id
	private String productAttributeSetId;
	
	private List<ProductAttributeSetJsonObject> productAttributeSet;
	
	//POJO to map Product Accessories request
	private BinContentJsonObject binContent;

	public List<ProductAttributeSetJsonObject> getProductAttributeSet() {
		return productAttributeSet;
	}

	public void setProductAttributeSet(List<ProductAttributeSetJsonObject> productAttributeSet) {
		this.productAttributeSet = productAttributeSet;
	}

	public String getProductAttributeSetId() {
		return productAttributeSetId;
	}

	public void setProductAttributeSetId(String productAttributeSetId) {
		this.productAttributeSetId = productAttributeSetId;
	}

	public BinContentJsonObject getBinContent() {
		return binContent;
	}

	public void setBinContent(BinContentJsonObject binContent) {
		this.binContent = binContent;
	}
}
