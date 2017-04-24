package com.saleschannel.api.product;

import java.util.List;

public class ProductFulfilledStatusJsonObject {

	private List<ProductJsonObject> fulfilledProducts;
	
	private List<ProductJsonObject> unfulfilledProducts;
	
	public List<ProductJsonObject> getFulfilledProducts() {
		return fulfilledProducts;
	}
	
	public void setFulfilledProducts(List<ProductJsonObject> fulfilledProducts) {
		this.fulfilledProducts = fulfilledProducts;
	}
	
	public List<ProductJsonObject> getUnfulfilledProducts() {
		return unfulfilledProducts;
	}
	
	public void setUnfulfilledProducts(List<ProductJsonObject> unfulfilledProducts) {
		this.unfulfilledProducts = unfulfilledProducts;
	}
}
