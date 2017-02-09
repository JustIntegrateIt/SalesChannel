package net.saleschannel.api.inventory;

import java.util.List;

public interface InventoryService {

	//Inventory Operations
	public InventoryJsonModel convertInventoryJsonObjectToModel(InventoryJsonObject inventoryJsonObject);
	
	public InventoryJsonObject convertInventoryJsonModelToObject(InventoryJsonModel inventoryJsonModel);
	
	public String createInventory(InventoryJsonObject inventoryJsonObject, String warehouseId);
	
	public boolean updateInventory(InventoryJsonObject inventoryJsonObject, String warehouseId);
	
	public InventoryJsonObject getInventoryById(String inventoryId);
	
	public List<InventoryJsonObject> getInventoriesByWarehouseId(String warehouseId);
	
	public InventoryJsonModel checkInventoryById(String inventoryId);
	
	public InventoryJsonModel checkInventoryByCode(String inventoryCode);
	
	public boolean deleteInventoryByWareHouseId(String warehouseId);
	
	public boolean deleteInventoryById(String inventoryId);
	
	//Shelf Operations
	public ShelfJsonModel convertShelfJsonObjectToModel(ShelfJsonObject shelfJsonObject);
	
	public ShelfJsonObject convertShelfJsonModelToObject(ShelfJsonModel shelfJsonModel);
	
	public String createShelf(ShelfJsonObject shelfJsonObject, String inventoryId);
	
	public boolean updateShelf(ShelfJsonObject shelfJsonObject, String inventoryId);
	
	public ShelfJsonObject getShelfById(String shelfId);
	
	public List<ShelfJsonObject> getShelfsByWarehouseId(String warehouseId);
	
	public List<ShelfJsonObject> getShelfsByInventoryId(String inventoryId);
	
	public ShelfJsonModel checkShelfById(String shelfId);
	
	public ShelfJsonModel checkShelfByCode(String shelfCode);
	
	public boolean deleteShelfByWareHouseId(String warehouseId);
	
	public boolean deleteShelfByInventoryId(String inventoryId);
	
	public boolean deleteShelfById(String shelfId);
	
	//Bin Operations
	public BinJsonModel convertBinJsonObjectToModel(BinJsonObject binJsonObject);
	
	public BinJsonObject convertBinJsonModelToObject(BinJsonModel binJsonModel);
	
	public String createBin(BinJsonObject binJsonObject, String shelfId);
	
	public boolean updateBin(BinJsonObject binJsonObject, String shelfId);
	
	public BinJsonObject getBinById(String binId);
	
	public List<BinJsonObject> getBinsByShelfId(String shelfId);
	
	public List<BinJsonObject> getBinsByInventoryId(String inventoryId);
	
	public BinJsonModel checkBinById(String binId);
	
	public BinJsonModel checkBinByCode(String binCode);
	
	public boolean deleteBinByShelfId(String shelfId);
	
	public boolean deleteBinByInventoryId(String inventoryId);
	
	public boolean deleteBinById(String binId);
	
	//Position Operations
	public PositionJsonModel convertPositionJsonObjectToModel(PositionJsonObject positionJsonObject);
	
	public PositionJsonObject convertPositionJsonModelToObject(PositionJsonModel positionJsonModel);
	
	public String createPosition(PositionJsonObject positionJsonObject);
	
	public boolean updatePosition(PositionJsonObject positionJsonObject);
	
	public PositionJsonObject getPositionById(String positionId);
	
	public boolean deletePositionById(String positionId);
}
