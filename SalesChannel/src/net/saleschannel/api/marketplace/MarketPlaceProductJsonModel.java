package net.saleschannel.api.marketplace;

import java.util.Date;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

import org.springframework.data.annotation.Id;

public class MarketPlaceProductJsonModel extends SalesChannelBaseJsonObject {

	//SC Market Place Product Id
	@Id
	private String id;
	
	//SC Product Id
	private String productId;
	
	//SC Product Place Id
	private String marketPlaceId;
	
	//SC Customer Id
	private String customerId;
	
	//Market Place Product Reference Id
	private String marketPlaceProductRefId;
	
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMarketPlaceId() {
		return marketPlaceId;
	}

	public void setMarketPlaceId(String marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMarketPlaceProductRefId() {
		return marketPlaceProductRefId;
	}

	public void setMarketPlaceProductRefId(String marketPlaceProductRefId) {
		this.marketPlaceProductRefId = marketPlaceProductRefId;
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
}
