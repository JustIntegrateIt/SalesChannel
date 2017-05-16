package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum OrderTrackingReportsType {

	/*Available Order Tracking Reports Type*/
	GET_FLAT_FILE_ALL_ORDERS_DATA_BY_LAST_UPDATE("_GET_FLAT_FILE_ALL_ORDERS_DATA_BY_LAST_UPDATE_"),
	GET_FLAT_FILE_ALL_ORDERS_DATA_BY_ORDER_DATE("_GET_FLAT_FILE_ALL_ORDERS_DATA_BY_ORDER_DATE_"),
	GET_XML_ALL_ORDERS_DATA_BY_LAST_UPDATE("_GET_XML_ALL_ORDERS_DATA_BY_LAST_UPDATE_"),
	GET_XML_ALL_ORDERS_DATA_BY_ORDER_DATE("_GET_XML_ALL_ORDERS_DATA_BY_ORDER_DATE_");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private OrderTrackingReportsType(String s) {
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
