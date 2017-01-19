package net.saleschannel.api.product;

import org.springframework.data.annotation.Id;

public class ProductAttributeCombinationJsonModel {

	//SC Product Attribute Combination Id
	@Id
	private String id;
	
	//SC Product Attribute Id
	private String productAttributeId;
	
	//SC Attribute Id
	private String AttributeId;
	
	//SC Product Id
	private String productId;
	
	//SC Attribute Value
	private String value;

	public String getProductAttributeId() {
		return productAttributeId;
	}

	public void setProductAttributeId(String productAttributeId) {
		this.productAttributeId = productAttributeId;
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

	public String getAttributeId() {
		return AttributeId;
	}

	public void setAttributeId(String attributeId) {
		AttributeId = attributeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
