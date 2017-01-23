package net.saleschannel.api.marketplace;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class MarketPlaceJsonObject extends SalesChannelBaseJsonObject {

	//SC Market Place Id
	private String id;
	
	//SC Market Place Name
	private String marketPlaceName;

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
}
