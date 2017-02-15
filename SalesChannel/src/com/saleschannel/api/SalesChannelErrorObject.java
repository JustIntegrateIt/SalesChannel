package com.saleschannel.api;

import java.io.Serializable;

public class SalesChannelErrorObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3881192306294476780L;
	
	/** The status code. */
	private Integer statusCode;
	/** The message. */
	private String message;
	/** The data. */
	private Object data;
	/** The field name. */
	private String fieldName;
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
