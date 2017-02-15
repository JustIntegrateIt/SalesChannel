package com.saleschannel.api.customer;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class CustomerLoginJsonModel extends SalesChannelBaseJsonObject {
	
	//SC Customer User Name
	private String userName;
	
	//SC Customer User Password
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
