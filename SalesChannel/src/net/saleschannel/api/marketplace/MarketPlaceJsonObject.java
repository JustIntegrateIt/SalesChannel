package net.saleschannel.api.marketplace;

import java.util.List;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class MarketPlaceJsonObject extends SalesChannelBaseJsonObject {

	//SC Market Place Id
	private String id;
	
	//SC Market Place Name
	private String marketPlaceName;
	
	//Market Place Regions
	private List<MarketPlaceRegionJsonObject> regions;
	
	//SC Market Place Name
	private List<MarketPlaceHeadersJsonObject> headers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarketPlaceName() {
		return marketPlaceName;
	}

	public void setMarketPlaceName(String marketPlaceName) {
		this.marketPlaceName = marketPlaceName;
	}

	public List<MarketPlaceHeadersJsonObject> getHeaders() {
		return headers;
	}

	public void setHeaders(List<MarketPlaceHeadersJsonObject> headers) {
		this.headers = headers;
	}

	public List<MarketPlaceRegionJsonObject> getRegions() {
		return regions;
	}

	public void setRegions(List<MarketPlaceRegionJsonObject> regions) {
		this.regions = regions;
	}

}
