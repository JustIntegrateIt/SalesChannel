package com.saleschannel.api.constants;

public enum CategoryColumnFieldType {

	number("number"),
	string("string"),
	alphanumeric("alphanumeric");
	
	private String name;

	private CategoryColumnFieldType(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static CategoryColumnFieldType getByName(String name){
		for(CategoryColumnFieldType categoryColumnFieldType : values()){
			if(categoryColumnFieldType.getName().toString().equals(name)){
				return categoryColumnFieldType;
			}
		}
		return null;
	}
}
