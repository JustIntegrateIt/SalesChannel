package com.saleschannel.api.product;

import org.springframework.data.annotation.Id;

public class AttributeJsonModel {

	//SC Attribute Id
	@Id
	private String id;
	
	//SC Attribute Name
	private String name;
	
	//SC Attribute Description
	private String description;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
