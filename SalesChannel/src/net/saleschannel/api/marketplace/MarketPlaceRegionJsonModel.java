package net.saleschannel.api.marketplace;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class MarketPlaceRegionJsonModel {

	//SC Market Place Region Id
	@Id
	private String id;
	
	//SC Market Place Id
	private String marketPlaceId;
	
	//Market Place Region Id
	private String marketPlaceRegionKey;
	
	//Market Place Region Name
	private String marketPlaceRegionName;
	
	//Market Place Sub Region Name
	private String marketPlaceSubRegionName;
	
	//Market Place End point
	private String marketPlaceEndPoint;
	
	//SC Market Place Locale
	private String marketPlaceLocale;
	
	private String createBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;

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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getMarketPlaceLocale() {
		return marketPlaceLocale;
	}

	public void setMarketPlaceLocale(String marketPlaceLocale) {
		this.marketPlaceLocale = marketPlaceLocale;
	}

	public String getMarketPlaceRegionKey() {
		return marketPlaceRegionKey;
	}

	public void setMarketPlaceRegionKey(String marketPlaceRegionKey) {
		this.marketPlaceRegionKey = marketPlaceRegionKey;
	}
}

