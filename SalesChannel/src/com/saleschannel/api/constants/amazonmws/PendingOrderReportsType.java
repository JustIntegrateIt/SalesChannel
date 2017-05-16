package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum PendingOrderReportsType {

	/*Available Pending Order Tracking Reports Type*/
	GET_FLAT_FILE_PENDING_ORDERS_DATA("_GET_FLAT_FILE_PENDING_ORDERS_DATA_"),
	GET_PENDING_ORDERS_DATA("_GET_PENDING_ORDERS_DATA_"),
	GET_CONVERGED_FLAT_FILE_PENDING_ORDERS_DATA("_GET_CONVERGED_FLAT_FILE_PENDING_ORDERS_DATA_");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private PendingOrderReportsType(String s) {
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
