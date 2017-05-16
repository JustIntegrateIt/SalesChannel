package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum SalesTaxReportsType {

	/*Available SalesTax Reports Type*/
	GET_FLAT_FILE_SALES_TAX_DATA("_GET_FLAT_FILE_SALES_TAX_DATA_");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private SalesTaxReportsType(String s) {
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
