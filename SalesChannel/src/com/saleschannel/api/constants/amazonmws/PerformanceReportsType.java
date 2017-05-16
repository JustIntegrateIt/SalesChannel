package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum PerformanceReportsType {

	/*Available Performance Reports Type*/
	GET_SELLER_FEEDBACK_DATA("_GET_SELLER_FEEDBACK_DATA_"),
	GET_V1_SELLER_PERFORMANCE_REPORT("_GET_V1_SELLER_PERFORMANCE_REPORT_");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private PerformanceReportsType(String s) {
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
