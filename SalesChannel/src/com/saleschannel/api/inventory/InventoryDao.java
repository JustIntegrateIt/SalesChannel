package com.saleschannel.api.inventory;

import java.util.List;

public interface InventoryDao {
	
	//Inventory Operations
	public String createInventory(InventoryJsonModel inventoryJsonModel);
	
	public boolean updateInventory(InventoryJsonModel inventoryJsonModel);
	
	public InventoryJsonModel getInventoryById(String inventoryId);
	
	public List<InventoryJsonModel> getInventoriesByWarehouseId(String warehouseId);
	
	public InventoryJsonModel checkInventoryByCode(String inventoryCode);
	
	public boolean deleteInventoryByWareHouseId(String warehouseId);
	
	public boolean deleteInventoryById(String inventoryId);
	
	//Shelf Operations
	public String createShelf(ShelfJsonModel shelfJsonModel);
	
	public boolean updateShelf(ShelfJsonModel shelfJsonModel);
	
	public ShelfJsonModel getShelfById(String shelfId);
	
	public List<ShelfJsonModel> getShelfsByInventoryId(String inventoryId);
	
	public ShelfJsonModel checkShelfByCode(String shelfCode);
	
	public boolean deleteShelfByInventoryId(String inventoryId);
	
	public boolean deleteShelfById(String shelfId);
	
	//Bin Operations
	public String createBin(BinJsonModel binJsonModel);
	
	public boolean updateBin(BinJsonModel binJsonModel);
	
	public BinJsonModel getBinById(String binId);
	
	public List<BinJsonModel> getBinsByShelfId(String shelfId);
	
	public BinJsonModel checkBinByCode(String binCode);
	
	public boolean deleteBinByShelfId(String shelfId);
	
	public boolean deleteBinById(String binId);
	
	//Position Operations
	public String createPosition(PositionJsonModel positionJsonModel);
	
	public boolean updatePosition(PositionJsonModel positionJsonModel);
	
	public PositionJsonModel getPositionById(String positionId);
	
	public boolean deletePositionById(String positionId);
	
	//BinContent Operations
	public String createBinContent(BinContentJsonModel binContentJsonModel);
	
	public boolean updateBinContent(BinContentJsonModel binContentJsonModel);
	
	public BinContentJsonModel getBinContentById(String binContentId);
	
	public List<BinContentJsonModel> getBinContentsByBinId(String binId);
	
	public boolean deleteBinContentByBinId(String binId);
	
	public boolean deleteBinContentById(String binContentId);

}
