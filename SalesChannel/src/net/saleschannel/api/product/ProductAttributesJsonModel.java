package net.saleschannel.api.product;

import org.springframework.data.annotation.Id;

public class ProductAttributesJsonModel {

	//SC Product Attributes Id
	@Id
	private String id;
	
	//SC Product Id
	private String productId;
	
	//SC Product Quantity
	private Integer quantity;
	
	//SC Product Cost
	private Integer cost;
	
	//SC Product skuId
	private String skuId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
