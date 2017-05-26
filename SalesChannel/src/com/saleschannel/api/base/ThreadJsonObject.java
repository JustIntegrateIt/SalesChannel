package com.saleschannel.api.base;

import java.util.List;

public class ThreadJsonObject {

	//thread name to identify the process type
	private String threadName;
	
	private List<String> responseIds;
	
	//marketPlaceId or marketplace region key
	private List<String> marketplacesIds;
	
	//SalesChannel customerId
	private String customerId;

	public List<String> getResponseIds() {
		return responseIds;
	}

	public void setResponseIds(List<String> responseIds) {
		this.responseIds = responseIds;
	}

	public List<String> getMarketplacesIds() {
		return marketplacesIds;
	}

	public void setMarketplacesIds(List<String> marketplacesIds) {
		this.marketplacesIds = marketplacesIds;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}
