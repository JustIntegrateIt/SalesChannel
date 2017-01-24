package net.saleschannel.api.product;

import java.util.List;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ProductAccessoriesJsonObject extends SalesChannelBaseJsonObject {

	private List<ProductAttributeSetJsonObject> productAccessories;

	public List<ProductAttributeSetJsonObject> getProductAccessories() {
		return productAccessories;
	}

	public void setProductAccessories(List<ProductAttributeSetJsonObject> productAccessories) {
		this.productAccessories = productAccessories;
	}
}
