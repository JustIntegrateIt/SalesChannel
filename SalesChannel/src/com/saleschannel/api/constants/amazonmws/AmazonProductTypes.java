package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum AmazonProductTypes {
	
	/*ISBN (International Standard Book Number): 10 digits or 13 digits

	UPC (Universal Product Code): 12 digits

	EAN (European Article Number): 13 digits

	JAN (Japanese Article Number): 13 digits

	GTIN-14 (Global Trade Item Number): 14 digits*/
	
	_1("ASIN"),
	_2("ISBN"),
	_3("UPC"),
	_4("EAN");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private AmazonProductTypes(String s) {
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
