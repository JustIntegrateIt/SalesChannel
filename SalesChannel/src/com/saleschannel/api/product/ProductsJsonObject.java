package com.saleschannel.api.product;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ProductsJsonObject extends SalesChannelBaseJsonObject {

	private List<ProductJsonObject> productJsonObjects;

	public List<ProductJsonObject> getProductJsonObjects() {
		return productJsonObjects;
	}

	public void setProductJsonObjects(List<ProductJsonObject> productJsonObjects) {
		this.productJsonObjects = productJsonObjects;
	}
}
