package com.saleschannel.api.flatfile;

import java.util.List;
import java.util.Map;

public class FlatFileJsonModel {

	private String id;
	
	private String productCategoryId;
	
	private String type;
	
	private List<Map<String, ColumnsJsonModel>> columns;
	
	private List<Map<String, List<MergeCellsRowJsonModel>>> mergeCells; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Map<String, List<MergeCellsRowJsonModel>>> getMergeCells() {
		return mergeCells;
	}

	public void setMergeCells(List<Map<String, List<MergeCellsRowJsonModel>>> mergeCells) {
		this.mergeCells = mergeCells;
	}

	public List<Map<String, ColumnsJsonModel>> getColumns() {
		return columns;
	}

	public void setColumns(List<Map<String, ColumnsJsonModel>> columns) {
		this.columns = columns;
	}
}
