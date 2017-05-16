package com.saleschannel.api.product;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ProductSyncJsonObject extends SalesChannelBaseJsonObject {

	private List<String> ids;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
}
