package net.saleschannel.api.marketplace;

import java.util.Date;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

import org.springframework.data.annotation.Id;

public class MarketPlaceJsonModel extends SalesChannelBaseJsonObject {

	//SC Market Place Id
	@Id
	private String id;
	
	//SC Market Place Name
	private String marketPlaceName;
	
	//SC Market Place bean class
	private String beanClassId;
	
	//Market Place base URL
	private String baseURL;
	
	//Market Place Header Parameter Name
	private String headerParameterName;
	
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
