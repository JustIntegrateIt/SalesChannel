package com.saleschannel.api.marketplace;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class MarketPlaceCustomerRegionJsonObject extends SalesChannelBaseJsonObject {

	//SC Market Place Customer Region Id
	private String id;
	
	//Market Place Name
	private String marketPlace;
	
	//Market Place Region Name
	private String marketPlaceRegionId;
		
	//Market Place Region Name
	private String marketPlaceRegion;
	
	//Market Place SubRegion Name
	private String marketPlaceSubRegion;
	
	//SC Customer Id
	private String customerId;
	
	//Market Place Customer Active Status
	private boolean isActive;
	
	//SC Market Place Headers
	private List<MarketPlaceHeadersJsonObject> headers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarketPlaceRegion() {
		return marketPlaceRegion;
	}

	public void setMarketPlaceRegion(String marketPlaceRegion) {
		this.marketPlaceRegion = marketPlaceRegion;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<MarketPlaceHeadersJsonObject> getHeaders() {
		return headers;
	}

	public void setHeaders(List<MarketPlaceHeadersJsonObject> headers) {
		this.headers = headers;
	}

	public String getMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}

	public String getMarketPlaceSubRegion() {
		return marketPlaceSubRegion;
	}

	public void setMarketPlaceSubRegion(String marketPlaceSubRegion) {
		this.marketPlaceSubRegion = marketPlaceSubRegion;
	}

	public String getMarketPlaceRegionId() {
		return marketPlaceRegionId;
	}

	public void setMarketPlaceRegionId(String marketPlaceRegionId) {
		this.marketPlaceRegionId = marketPlaceRegionId;
	}
	
}
