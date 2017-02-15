package com.saleschannel.api.warehouse;

import java.util.List;

public interface WarehouseService {

	public WarehouseJsonModel convertWarehouseJsonObjectToModel(WarehouseJsonObject warehouseJsonObject);
	
	public WarehouseJsonObject convertWarehouseJsonModelToObject(WarehouseJsonModel warehouseJsonModel);
	
	public String createWarehouse(WarehouseJsonObject warehouseJsonObject);
	
	public boolean updateWarehouse(WarehouseJsonObject warehouseJsonObject);
	
	public WarehouseJsonObject getWarehouseById(String warehouseId);
	
	public WarehouseJsonModel checkWarehouseById(String warehouseId);
	
	public WarehouseJsonModel checkWarehouseExist(String warehouseName);
	
	public List<WarehouseJsonObject> getAllWarehouses(String customerId);
	
	public boolean deleteWarehouseById(String warehouseId);
}
