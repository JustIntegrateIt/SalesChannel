package com.saleschannel.api.product;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class MarketPlaceProductSyncJsonObject extends SalesChannelBaseJsonObject {

	//Amazon MWS parameter
	private List<String> id;
	
	//Amazon MWS parameter
	private String idType;
	
	//Amazon MWS parameter
	private String marketPlaceRegionKey;

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getMarketPlaceRegionKey() {
		return marketPlaceRegionKey;
	}

	public void setMarketPlaceRegionKey(String marketPlaceRegionKey) {
		this.marketPlaceRegionKey = marketPlaceRegionKey;
	}
	
}
