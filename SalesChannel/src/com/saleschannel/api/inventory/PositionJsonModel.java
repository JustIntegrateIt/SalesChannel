package com.saleschannel.api.inventory;

import java.util.Date;

public class PositionJsonModel {

	//SC Position Id
	private String id;
	
	//SC Position Row/X
	private String rowPositionX;
	
	//SC Position Column/Y
	private String columnPositionY;
	
	//SC Position Z[X,Y]/Value
	private String thirdValueZ;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createdAt;
	
	private Date updatedAt;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
