package net.saleschannel.api.inventory;

import net.saleschannel.api.base.SalesChannelBaseJsonObject;

public class ShelfJsonObject extends SalesChannelBaseJsonObject {

	//SC Shelf Id
	private String id;

	//SC Shelf Code
	private String shelfCode;
	
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
}
