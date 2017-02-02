package net.saleschannel.api.marketplace;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class MarketPlaceHeadersJsonModel {

	//SC Market Place Header Id
	@Id
	private String id;
	
	//SC Market Place Id
	private String marketPlaceId;
	
	//SC Market Place Region Id
	private String marketPlaceRegionId;
	
	//SC Market Place Header parameter name
	private String headerParamName;
	
	//SC Market Place Header parameter value
	private String headerParamValue;

	//SC Customer Id
	private String customerId;
	
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

	public String getMarketPlaceRegionId() {
		return marketPlaceRegionId;
	}

	public void setMarketPlaceRegionId(String marketPlaceRegionId) {
		this.marketPlaceRegionId = marketPlaceRegionId;
	}

	public String getHeaderParamName() {
		return headerParamName;
	}

	public void setHeaderParamName(String headerParamName) {
		this.headerParamName = headerParamName;
	}

	public String getHeaderParamValue() {
		return headerParamValue;
	}

	public void setHeaderParamValue(String headerParamValue) {
		this.headerParamValue = headerParamValue;
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
}

