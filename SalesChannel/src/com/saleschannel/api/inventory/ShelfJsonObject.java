package com.saleschannel.api.inventory;

import java.util.List;

import com.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ShelfJsonObject extends SalesChannelBaseJsonObject {

	//SC Shelf Id
	private String id;

	//SC Shelf Code
	private String shelfCode;
	
	//SC Bin List
	private List<BinJsonObject> binList;
	
	//SC Shelf Position
	private PositionJsonObject position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public PositionJsonObject getPosition() {
		return position;
	}

	public void setPosition(PositionJsonObject position) {
		this.position = position;
	}

	public List<BinJsonObject> getBinList() {
		return binList;
	}

	public void setBinList(List<BinJsonObject> binList) {
		this.binList = binList;
	}
}
