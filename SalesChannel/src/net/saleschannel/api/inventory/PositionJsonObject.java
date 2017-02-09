package net.saleschannel.api.inventory;

public class PositionJsonObject {

	//SC Position Id
	private String id;
	
	//SC Position Row/X
	private String rowPositionX;
	
	//SC Position Column/Y
	private String columnPositionY;
	
	//SC Position Z[X,Y]/Value
	private String thirdValueZ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRowPositionX() {
		return rowPositionX;
	}

	public void setRowPositionX(String rowPositionX) {
		this.rowPositionX = rowPositionX;
	}

	public String getColumnPositionY() {
		return columnPositionY;
	}

	public void setColumnPositionY(String columnPositionY) {
		this.columnPositionY = columnPositionY;
	}

	public String getThirdValueZ() {
		return thirdValueZ;
	}

	public void setThirdValueZ(String thirdValueZ) {
		this.thirdValueZ = thirdValueZ;
	}
}
