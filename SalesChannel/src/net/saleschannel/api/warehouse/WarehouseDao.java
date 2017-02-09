package net.saleschannel.api.warehouse;

import java.util.List;

public interface WarehouseDao {

	public String createWarehouse(WarehouseJsonModel warehouseJsonModel);
	
	public boolean updateWarehouse(WarehouseJsonModel warehouseJsonModel);
	
	public WarehouseJsonModel getWarehouseById(String warehouseId);
	
	public WarehouseJsonModel checkWarehouseExist(String warehouseName);
	
	public List<WarehouseJsonModel> getAllWarehouses(String customerId);
	
	public boolean deleteWarehouseById(String warehouseId);
}
