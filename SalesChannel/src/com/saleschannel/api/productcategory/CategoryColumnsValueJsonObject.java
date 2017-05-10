package com.saleschannel.api.productcategory;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class CategoryColumnsValueJsonObject extends SalesChannelBaseJsonObject {

	private List<ProductCategoryColumnValueJsonObject> categoryColumnsValue;

	public List<ProductCategoryColumnValueJsonObject> getCategoryColumnsValue() {
		return categoryColumnsValue;
	}

	public void setCategoryColumnsValue(List<ProductCategoryColumnValueJsonObject> categoryColumnsValue) {
		this.categoryColumnsValue = categoryColumnsValue;
	}
}
