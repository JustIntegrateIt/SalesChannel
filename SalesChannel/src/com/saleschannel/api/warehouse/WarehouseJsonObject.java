package com.saleschannel.api.warehouse;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;
import com.saleschannel.api.inventory.InventoryJsonObject;

public class WarehouseJsonObject extends SalesChannelBaseJsonObject {

	//SC Warehouse Id
	private String id;
	
	//SC Warehouse Name
	private String warehouseName;
	
	//SC Warehouse Address1
	private String address1;
	
	//SC Warehouse Address2
	private String address2;
	
	//SC Warehouse City
	private String city;
	
	//SC Warehouse Province
	private String province;
	
	//SC Warehouse PostalCode
	private String postalCode;
	
	//SC Warehouse Country
	private String country;
	
	private List<InventoryJsonObject> inventoryList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public List<InventoryJsonObject> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<InventoryJsonObject> inventoryList) {
		this.inventoryList = inventoryList;
	}
}
