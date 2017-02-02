package net.saleschannel.api.marketplace;

import org.springframework.data.annotation.Id;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class MarketPlaceRegionJsonObject extends SalesChannelBaseJsonObject {

	//SC Market Place Region Id
	@Id
	private String id;
	
	//Market Place Region Name
	private String marketPlaceRegionName;
	
	//Market Place Region Name
	private String marketPlaceSubRegionName;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarketPlaceRegionName() {
		return marketPlaceRegionName;
	}

	public void setMarketPlaceRegionName(String marketPlaceRegionName) {
		this.marketPlaceRegionName = marketPlaceRegionName;
	}

	public String getMarketPlaceSubRegionName() {
		return marketPlaceSubRegionName;
	}

	public void setMarketPlaceSubRegionName(String marketPlaceSubRegionName) {
		this.marketPlaceSubRegionName = marketPlaceSubRegionName;
	}

}
