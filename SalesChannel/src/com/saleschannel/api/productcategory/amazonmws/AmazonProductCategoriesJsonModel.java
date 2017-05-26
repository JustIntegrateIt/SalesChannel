package com.saleschannel.api.productcategory.amazonmws;

import java.util.List;

import org.springframework.data.annotation.Id;

public class AmazonProductCategoriesJsonModel {

	//SC Amazon Product Categories Id
	@Id
	private String id;
	
	//Amazon Product Pull Category Ids
	private List<String> amazonProductCategoryIds;
	
	//SC Amazon Product Category Id
	private String categoryId;
	
	//SC Amazon Product Category Name
	private String categoryName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getAmazonProductCategoryIds() {
		return amazonProductCategoryIds;
	}

	public void setAmazonProductCategoryIds(List<String> amazonProductCategoryIds) {
		this.amazonProductCategoryIds = amazonProductCategoryIds;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
