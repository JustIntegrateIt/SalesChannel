package net.saleschannel.api.productcategorymapping;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ProductCategoryMappingJsonObject extends SalesChannelBaseJsonObject {

	//SC Product Category Mapping Id
	private String id;
	
	//SC Customer Category Id
	private String customerCategoryId;
	
	//SC Customer Category Name
	private String customerCategoryName;
		
	//SC Market Place Product Category Id
	private String marketPlaceCategoryId;
	
	//SC Market Place Product Category Name
	private String marketPlaceCategoryName;
		
	public String getCustomerCategoryId() {
		return customerCategoryId;
	}

	public void setCustomerCategoryId(String customerCategoryId) {
		this.customerCategoryId = customerCategoryId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarketPlaceCategoryId() {
		return marketPlaceCategoryId;
	}

	public void setMarketPlaceCategoryId(String marketPlaceCategoryId) {
		this.marketPlaceCategoryId = marketPlaceCategoryId;
	}

	public String getMarketPlaceCategoryName() {
		return marketPlaceCategoryName;
	}

	public void setMarketPlaceCategoryName(String marketPlaceCategoryName) {
		this.marketPlaceCategoryName = marketPlaceCategoryName;
	}

	public String getCustomerCategoryName() {
		return customerCategoryName;
	}

	public void setCustomerCategoryName(String customerCategoryName) {
		this.customerCategoryName = customerCategoryName;
	}
}
