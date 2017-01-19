package net.saleschannel.api.product;

import org.springframework.data.annotation.Id;

public class ProductAccessoriesJsonModel {

	//SC Product Accessories Id
	@Id
	private String id;
	
	//SC Product Id
	private String productId;
	
	//SC Product Accessories Name
	private String name;
	
	//SC Product Accessories Name
	private String value;
	
	//SC Product Accessories Description
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
