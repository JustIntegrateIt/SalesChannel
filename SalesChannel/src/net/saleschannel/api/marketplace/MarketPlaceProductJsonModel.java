package net.saleschannel.api.marketplace;

import java.sql.Timestamp;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

import org.springframework.data.annotation.Id;

public class MarketPlaceProductJsonModel extends SalesChannelBaseJsonObject {

	@Id
	private String id;
	
	private String productId;
	
	private String marketPlaceId;
	
	private String customerId;
	
	private String marketPlaceProductRefId;
	
	private String createBy;
	
	private String updatedBy;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;

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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
