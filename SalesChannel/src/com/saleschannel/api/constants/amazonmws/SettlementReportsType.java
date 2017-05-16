package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum SettlementReportsType {

	/*Available Settlement Reports Type*/
	GET_V2_SETTLEMENT_REPORT_DATA_FLAT_FILE("_GET_V2_SETTLEMENT_REPORT_DATA_FLAT_FILE_"),
	GET_V2_SETTLEMENT_REPORT_DATA_XML("_GET_V2_SETTLEMENT_REPORT_DATA_XML_"),
	GET_V2_SETTLEMENT_REPORT_DATA_FLAT_FILE_V2("_GET_V2_SETTLEMENT_REPORT_DATA_FLAT_FILE_V2_");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private SettlementReportsType(String s) {
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
