package net.saleschannel.api.product;

import java.util.List;

public class ProductAttributeSetModel {

	private List<ProductAttributeSetJsonObject> productAttributeSet;

	public List<ProductAttributeSetJsonObject> getProductAttributeSet() {
		return productAttributeSet;
	}

	public void setProductAttributeSet(List<ProductAttributeSetJsonObject> productAttributeSet) {
		this.productAttributeSet = productAttributeSet;
	}
}
