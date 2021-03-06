package com.saleschannel.api.marketplace;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class MarketPlaceCustomerRegionJsonModel {

	//SC Market Place Customer Region Id
	@Id
	private String id;
	
	//SC Market Place Region Id
	private String marketPlaceRegionId;
	
	//SC Market Place Id
	private String marketPlaceId;
	
	//SC Customer Id
	private String customerId;
	
	//Market Place Customer Active Status
	private boolean isActive;
		
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

	public String getMarketPlaceRegionId() {
		return marketPlaceRegionId;
	}

	public void setMarketPlaceRegionId(String marketPlaceRegionId) {
		this.marketPlaceRegionId = marketPlaceRegionId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getMarketPlaceId() {
		return marketPlaceId;
	}

	public void setMarketPlaceId(String marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}
}
