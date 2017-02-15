package com.saleschannel.api.inventory;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

import org.springframework.data.annotation.Id;

public class BinJsonObject extends SalesChannelBaseJsonObject {

	//SC Bin Id
	@Id
	private String id;
		
	//SC Bin Code
	private String binCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBinCode() {
		return binCode;
	}

	public void setBinCode(String binCode) {
		this.binCode = binCode;
	}
}
