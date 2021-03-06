package com.amazonaws.mws.model;

import java.util.Collection;
import java.util.HashSet;

/*
 * Enumeration of valid content types for a feed submission.
 */
public enum ContentType {
	/*
	 * Feed is sent as a stream of bytes ("application/octet-stream").
	 * Currently this is the only content type supported by MWS.
	 */
	OctetStream("application/octet-stream"),
	TextXML("text/tab-separated-values; charset=iso-8859-1");
	
	private String value;
	private Collection<TypeParameter> parameters = new HashSet<TypeParameter>();

	private ContentType(String s) {
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
