package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum AmazonItemCondition {

	_1("Used"), //Like New
	_2("Used"), //Very Good
	_3("Used"), //Good
	_4("Used"), //Acceptable
	_5("Collectible"), //Like New
	_6("Collectible"), //Very Good
	_7("Collectible"), //Good
	_8("Collectible"), //Acceptable
	_9("Not used"),
	_10("Refurbished"),
	_11("New");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private AmazonItemCondition(String s) {
		this.value = s;
	}
	
	@SuppressWarnings("unused")
	private void addParameter(TypeParameter parameter) {
		parameters.add(parameter);		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.value);
		
		for(TypeParameter p : parameters) {
			sb.append(";").append(p);
		}
		
		return sb.toString();
	}
}
