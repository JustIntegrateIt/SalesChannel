package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum ListingsReportsType {

	/*Available Listings Reports Type*/
	GET_FLAT_FILE_OPEN_LISTINGS_DATA("_GET_FLAT_FILE_OPEN_LISTINGS_DATA_"),
	GET_MERCHANT_LISTINGS_ALL_DATA("_GET_MERCHANT_LISTINGS_ALL_DATA_"),
	GET_MERCHANT_LISTINGS_DATA("_GET_MERCHANT_LISTINGS_DATA_"),
	GET_MERCHANT_LISTINGS_INACTIVE_DATA("_GET_MERCHANT_LISTINGS_INACTIVE_DATA_"),
	GET_MERCHANT_LISTINGS_DATA_BACK_COMPAT("_GET_MERCHANT_LISTINGS_DATA_BACK_COMPAT_"),
	GET_MERCHANT_LISTINGS_DATA_LITE("GET_MERCHANT_LISTINGS_DATA_LITE"),
	GET_MERCHANT_LISTINGS_DATA_LITER("_GET_MERCHANT_LISTINGS_DATA_LITER_"),
	GET_MERCHANT_CANCELLED_LISTINGS_DATA("_GET_MERCHANT_CANCELLED_LISTINGS_DATA_"),
	GET_CONVERGED_FLAT_FILE_SOLD_LISTINGS_DATA("_GET_CONVERGED_FLAT_FILE_SOLD_LISTINGS_DATA_"),
	GET_MERCHANT_LISTINGS_DEFECT_DATA("_GET_MERCHANT_LISTINGS_DEFECT_DATA_");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private ListingsReportsType(String s) {
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
