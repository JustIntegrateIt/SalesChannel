package net.saleschannel.api.productcategory;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class ProductCategoryMappingJsonModel {

	//SC Product Category Mapping Id
	@Id
	private String id;
	
	//SC Customer Id;
	private String customerId;
	
	//SC Product Id;
	private String productId;
		
	//SC Product Category Id;
	private String productCategoryId;
	
	//Market Place Product Category Id;
	private String marketPlaceProductCategoryId;
	
	//SC Product Sync Status to Market Place
	private boolean isSync;
	
	private String createBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;

	public boolean isSync() {
		return isSync;
	}

	public void setSync(boolean isSync) {
		this.isSync = isSync;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getMarketPlaceProductCategoryId() {
		return marketPlaceProductCategoryId;
	}

	public void setMarketPlaceProductCategoryId(
			String marketPlaceProductCategoryId) {
		this.marketPlaceProductCategoryId = marketPlaceProductCategoryId;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
