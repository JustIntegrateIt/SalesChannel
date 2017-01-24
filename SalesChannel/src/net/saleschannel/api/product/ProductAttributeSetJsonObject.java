package net.saleschannel.api.product;

public class ProductAttributeSetJsonObject {
	
	//POJO to map ProductAttributes
	private String valueId;
	
	private String name;
	
	private String value;
	
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

}
