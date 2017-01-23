package net.saleschannel.api.marketplace;

import org.springframework.data.annotation.Id;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class MarketPlaceRegionJsonObject extends SalesChannelBaseJsonObject {

	//SC Market Place Region Id
	@Id
	private String id;
	
	//SC Market Place Id
	private String marketPlaceId;
	
	//Market Place Region Id
	private String marketPlaceRegionId;
	
	//Market Place Region Name
	private String marketPlaceRegionName;
	
	//Market Place Sub Region Name
	private String marketPlaceSubRegionName;
	
	//Market Place End point
	private String marketPlaceEndPoint;
	
	//SC Market Place Locale
	private String marketPlaceLocale;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarketPlaceId() {
		return marketPlaceId;
	}

	public void setMarketPlaceId(String marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}

	public String getMarketPlaceRegionId() {
		return marketPlaceRegionId;
	}

	public void setMarketPlaceRegionId(String marketPlaceRegionId) {
		this.marketPlaceRegionId = marketPlaceRegionId;
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

	public String getMarketPlaceEndPoint() {
		return marketPlaceEndPoint;
	}

	public void setMarketPlaceEndPoint(String marketPlaceEndPoint) {
		this.marketPlaceEndPoint = marketPlaceEndPoint;
	}

	public String getMarketPlaceLocale() {
		return marketPlaceLocale;
	}

	public void setMarketPlaceLocale(String marketPlaceLocale) {
		this.marketPlaceLocale = marketPlaceLocale;
	}
}
