package net.saleschannel.api.marketplace;

import java.sql.Timestamp;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

import org.springframework.data.annotation.Id;

public class MarketPlaceJsonModel extends SalesChannelBaseJsonObject {

	@Id
	private String id;
	
	private String marketPlaceName;
	
	private String beanClassId;
	
	private String baseURL;
	
	private String headerParameterName;
	
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

	public String getMarketPlaceName() {
		return marketPlaceName;
	}

	public void setMarketPlaceName(String marketPlaceName) {
		this.marketPlaceName = marketPlaceName;
	}

	public String getBeanClassId() {
		return beanClassId;
	}

	public void setBeanClassId(String beanClassId) {
		this.beanClassId = beanClassId;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getHeaderParameterName() {
		return headerParameterName;
	}

	public void setHeaderParameterName(String headerParameterName) {
		this.headerParameterName = headerParameterName;
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
