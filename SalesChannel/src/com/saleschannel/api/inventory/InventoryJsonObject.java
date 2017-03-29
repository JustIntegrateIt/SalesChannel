package com.saleschannel.api.inventory;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class InventoryJsonObject extends SalesChannelBaseJsonObject {

	//SC Inventory Id
	private String id;
	
	//SC Inventory Code
	private String inventoryCode;
	
	//SC Shelf List
	private List<ShelfJsonObject> shelfList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public List<ShelfJsonObject> getShelfList() {
		return shelfList;
	}

	public void setShelfList(List<ShelfJsonObject> shelfList) {
		this.shelfList = shelfList;
	}
}
