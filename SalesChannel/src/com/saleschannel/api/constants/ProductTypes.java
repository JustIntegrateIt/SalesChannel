package com.saleschannel.api.constants;

public enum ProductTypes {
	
	Simple("Simple"),
	Configurable("Configurable"),
	Virtual("Virtual"),
	Downloadable("Downloadable"),
	Grouped("Grouped"),
	Bundled("Bundled");
	
	private String name;

	private ProductTypes(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static ProductTypes getByName(String name){
		for(ProductTypes productType : values()){
			if(productType.getName().toString().equals(name)){
				return productType;
			}
		}
		return null;
	}
	
}
