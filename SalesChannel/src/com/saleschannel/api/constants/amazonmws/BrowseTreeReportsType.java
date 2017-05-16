package com.saleschannel.api.constants.amazonmws;

import java.util.Collection;
import java.util.HashSet;

import com.amazonaws.mws.model.TypeParameter;

public enum BrowseTreeReportsType {

	/*Available BrowseTree Reports Type*/
	GET_XML_BROWSE_TREE_DATA("_GET_XML_BROWSE_TREE_DATA_");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private BrowseTreeReportsType(String s) {
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
