package net.saleschannel.api.productcategorymapping;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class ProductCategoryMappingJsonModel {

	//SC Product Category Mapping Id
	@Id
	private String id;
	
	//SC Customer Id;
	private String customerId;
	
	//SC Customer Product Category Id;
	private String customerProductCategoryId;
	
	//Market Place Product Category Id;
	private String marketPlaceProductCategoryId;
	
	//SC Product Sync Status to Market Place
	private boolean isSync;
	
	//SC Market Place Id
	private String marketPlaceId;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;

	public boolean isSync() {
		return isSync;
	}

	public void setSync(boolean isSync) {
		this.isSync = isSync;
	}

	public String getMarketPlaceProductCategoryId() {
		return marketPlaceProductCategoryId;
	}

	public void setMarketPlaceProductCategoryId(
			String marketPlaceProductCategoryId) {
		this.marketPlaceProductCategoryId = marketPlaceProductCategoryId;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerProductCategoryId() {
		return customerProductCategoryId;
	}

	public void setCustomerProductCategoryId(String customerProductCategoryId) {
		this.customerProductCategoryId = customerProductCategoryId;
	}

	public String getMarketPlaceId() {
		return marketPlaceId;
	}

	public void setMarketPlaceId(String marketPlaceId) {
		this.marketPlaceId = marketPlaceId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
