package net.saleschannel.api.inventory;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class InventoryJsonObject extends SalesChannelBaseJsonObject {

	//SC Inventory Id
	private String id;
	
	//SC Inventory Code
	private String inventoryCode;
	
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
}
