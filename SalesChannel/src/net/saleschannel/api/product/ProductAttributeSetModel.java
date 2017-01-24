package net.saleschannel.api.product;

import java.util.List;

public class ProductAttributeSetModel {

	//SC Product Attribute Id
	private String productAttributeSetId;
	
	private List<ProductAttributeSetJsonObject> productAttributeSet;

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
}
