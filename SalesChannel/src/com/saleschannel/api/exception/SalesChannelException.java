package com.saleschannel.api.exception;

public class SalesChannelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3632923089215931903L;

	String message;
	
	public SalesChannelException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() { 
		return ("Output String = "+message);
	}
}
