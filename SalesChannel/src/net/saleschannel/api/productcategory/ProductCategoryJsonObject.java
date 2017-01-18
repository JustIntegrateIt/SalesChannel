package net.saleschannel.api.productcategory;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ProductCategoryJsonObject extends SalesChannelBaseJsonObject {

	//SC Product Category Id
	private String id;
	
	//SC Product Category Name
	private String categoryName;
	
	//SC Product Parent Category Id
	private String parentId;
	
	//SC Product Parent Category Name
	private String parentCategoryName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	
}
