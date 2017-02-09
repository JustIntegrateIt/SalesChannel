package net.saleschannel.api.inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class InventoryServiceImpl implements InventoryService {

	private static final Logger LOGGERS = Logger.getLogger(InventoryServiceImpl.class);
	
	private InventoryDaoImpl inventoryDao;

	public InventoryDaoImpl getInventoryDao() {
		return inventoryDao;
	}

	public void setInventoryDao(InventoryDaoImpl inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
	
	public InventoryJsonModel convertInventoryJsonObjectToModel(InventoryJsonObject inventoryJsonObject) {
		InventoryJsonModel inventoryJsonModel = null;
		try {
			if(inventoryJsonObject != null) {
				if(inventoryJsonObject.getId() != null && !inventoryJsonObject.getId().isEmpty()) {
					inventoryJsonModel = inventoryDao.getInventoryById(inventoryJsonObject.getId());
					inventoryJsonModel.setInventoryCode(inventoryJsonObject.getInventoryCode());
					inventoryJsonModel.setUpdatedAt(new Date());
					inventoryJsonModel.setUpdatedBy(inventoryJsonObject.getCustomerId());
				} else {
					inventoryJsonModel = new InventoryJsonModel();
					inventoryJsonModel.setInventoryCode(inventoryJsonObject.getInventoryCode());
					inventoryJsonModel.setCustomerId(inventoryJsonObject.getCustomerId());
					inventoryJsonModel.setCreatedAt(new Date());
					inventoryJsonModel.setCreatedBy(inventoryJsonObject.getCustomerId());
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert Inventory JsonObject To Model");
			e.printStackTrace();
		}
		return inventoryJsonModel;
	}
	
	public InventoryJsonObject convertInventoryJsonModelToObject(InventoryJsonModel inventoryJsonModel) {
		InventoryJsonObject InventoryJsonObject = null;
		try {
			if(inventoryJsonModel != null) {
				InventoryJsonObject = new InventoryJsonObject();
				InventoryJsonObject.setId(inventoryJsonModel.getId());
				InventoryJsonObject.setInventoryCode(inventoryJsonModel.getInventoryCode());
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert Inventory JsonModel To Object");
			e.printStackTrace();
		}
		return InventoryJsonObject;
	}
	
	public String createInventory(InventoryJsonObject inventoryJsonObject, String warehouseId) {
		String inventoryId = null;
		try {
			InventoryJsonModel inventoryJsonModel = convertInventoryJsonObjectToModel(inventoryJsonObject);
			if(inventoryJsonModel != null) {
				inventoryJsonModel.setWarehouseId(warehouseId);
				inventoryId = inventoryDao.createInventory(inventoryJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while create Inventory");
			e.printStackTrace();
		}
		return inventoryId;
	}
	
	public boolean updateInventory(InventoryJsonObject inventoryJsonObject, String warehouseId) {
		boolean status = false;
		try {
			InventoryJsonModel inventoryJsonModel = convertInventoryJsonObjectToModel(inventoryJsonObject);
			if(inventoryJsonModel != null) {
				inventoryJsonModel.setWarehouseId(warehouseId);
				status = inventoryDao.updateInventory(inventoryJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while update Inventory");
			e.printStackTrace();
		}
		return status;
	}
	
	public InventoryJsonObject getInventoryById(String inventoryId) {
		InventoryJsonObject inventoryJsonObject = null;
		try {
			InventoryJsonModel inventoryJsonModel = inventoryDao.getInventoryById(inventoryId);
			if(inventoryJsonModel != null) {
				inventoryJsonObject = convertInventoryJsonModelToObject(inventoryJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get Inventory By Id");
			e.printStackTrace();
		}
		return inventoryJsonObject;
	}
	
	public List<InventoryJsonObject> getInventoriesByWarehouseId(String warehouseId) {
		List<InventoryJsonObject> inventoryJsonObjectList = null;
		try {
			List<InventoryJsonModel> inventoryJsonModelList = inventoryDao.getInventoriesByWarehouseId(warehouseId);
			if(inventoryJsonModelList != null && inventoryJsonModelList.size() > 0) {
				inventoryJsonObjectList = new ArrayList<InventoryJsonObject>();
				for(InventoryJsonModel inventoryJsonModel : inventoryJsonModelList) {
					InventoryJsonObject inventoryJsonObject = convertInventoryJsonModelToObject(inventoryJsonModel);
					if(inventoryJsonObject != null) {
						inventoryJsonObjectList.add(inventoryJsonObject);
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get Inventories By WarehouseId");
			e.printStackTrace();
		}
		return inventoryJsonObjectList;
	}
	
	public InventoryJsonModel checkInventoryById(String inventoryId) {
		InventoryJsonModel inventoryJsonModel = null;
		try {
			inventoryJsonModel = inventoryDao.getInventoryById(inventoryId);
		} catch(Exception e) {
			LOGGERS.error("error occured while check Inventory By Id");
			e.printStackTrace();
		}
		return inventoryJsonModel;
	}
	
	public InventoryJsonModel checkInventoryByCode(String inventoryCode) {
		InventoryJsonModel inventoryJsonModel = null;
		try {
			inventoryJsonModel = inventoryDao.checkInventoryByCode(inventoryCode);
		} catch(Exception e) {
			LOGGERS.error("error occured while check Inventory By Code");
			e.printStackTrace();
		}
		return inventoryJsonModel;
	}
	
	public boolean deleteInventoryByWareHouseId(String warehouseId) {
		boolean status = false;
		try {
			status = inventoryDao.deleteInventoryByWareHouseId(warehouseId);
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Inventory By WareHouseId");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteInventoryById(String inventoryId) {
		boolean status = false;
		try {
			status = inventoryDao.deleteInventoryById(inventoryId);
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Inventory By Id");
			e.printStackTrace();
		}
		return status;
	}
	
	public ShelfJsonModel convertShelfJsonObjectToModel(ShelfJsonObject shelfJsonObject) {
		ShelfJsonModel shelfJsonModel = null;
		try {
			if(shelfJsonObject != null) {
				if(shelfJsonObject.getId() != null && !shelfJsonObject.getId().isEmpty()) {
					shelfJsonModel = inventoryDao.getShelfById(shelfJsonObject.getId());
					if(shelfJsonModel != null) {
						shelfJsonModel.setShelfCode(shelfJsonObject.getShelfCode());
						shelfJsonModel.setUpdatedAt(new Date());
						shelfJsonModel.setUpdatedBy(shelfJsonObject.getCustomerId());
						if(shelfJsonObject.getPosition() != null) {
							PositionJsonModel positionJsonModel = convertPositionJsonObjectToModel(shelfJsonObject.getPosition());
							if(positionJsonModel != null) {
								String positionId = inventoryDao.createPosition(positionJsonModel);
								shelfJsonModel.setPositionId(positionId);
							}
						}
					}
				} else {
					shelfJsonModel = new ShelfJsonModel();
					shelfJsonModel.setShelfCode(shelfJsonObject.getShelfCode());
					shelfJsonModel.setCustomerId(shelfJsonObject.getCustomerId());
					shelfJsonModel.setCreatedAt(new Date());
					shelfJsonModel.setCreatedBy(shelfJsonObject.getCustomerId());
					if(shelfJsonObject.getPosition() != null) {
						PositionJsonModel positionJsonModel = convertPositionJsonObjectToModel(shelfJsonObject.getPosition());
						if(positionJsonModel != null) {
							String positionId = inventoryDao.createPosition(positionJsonModel);
							shelfJsonModel.setPositionId(positionId);
						}
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert ShelfJsonObject To Model");
			e.printStackTrace();
		}
		return shelfJsonModel;
	}
	
	public ShelfJsonObject convertShelfJsonModelToObject(ShelfJsonModel shelfJsonModel) {
		ShelfJsonObject shelfJsonObject = null;
		try {
			if(shelfJsonModel != null) {
				shelfJsonObject = new ShelfJsonObject();
				shelfJsonObject.setId(shelfJsonModel.getId());
				shelfJsonObject.setShelfCode(shelfJsonModel.getShelfCode());
				if(shelfJsonModel.getPositionId() != null && !shelfJsonModel.getPositionId().isEmpty()) {
					PositionJsonModel positionJsonModel = inventoryDao.getPositionById(shelfJsonModel.getPositionId());
					PositionJsonObject positionJsonObject = convertPositionJsonModelToObject(positionJsonModel); 
					if(positionJsonObject != null) {
						shelfJsonObject.setPosition(positionJsonObject);
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert ShelfJsonModel To Object");
			e.printStackTrace();
		}
		return shelfJsonObject;
	}
	
	public String createShelf(ShelfJsonObject shelfJsonObject, String inventoryId) {
		String shelfId = null;
		try {
			ShelfJsonModel shelfJsonModel = convertShelfJsonObjectToModel(shelfJsonObject);
			if(shelfJsonModel != null) {
				shelfJsonModel.setInventoryId(inventoryId);
				shelfId = inventoryDao.createShelf(shelfJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while create shelf");
			e.printStackTrace();
		}
		return shelfId;
	}
	
	public boolean updateShelf(ShelfJsonObject shelfJsonObject, String inventoryId) {
		boolean status = false;
		try {
			ShelfJsonModel shelfJsonModel = convertShelfJsonObjectToModel(shelfJsonObject);
			if(shelfJsonModel != null) {
				shelfJsonModel.setInventoryId(inventoryId);
				status = inventoryDao.updateShelf(shelfJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while update shelf");
			e.printStackTrace();
		}
		return status;
	}
	
	public ShelfJsonObject getShelfById(String shelfId) {
		ShelfJsonObject shelfJsonObject = null;
		try {
			ShelfJsonModel shelfJsonModel = inventoryDao.getShelfById(shelfId);
			if(shelfJsonModel != null) {
				shelfJsonObject = convertShelfJsonModelToObject(shelfJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get Shelf By Id");
			e.printStackTrace();
		}
		return shelfJsonObject;
	}
	
	public List<ShelfJsonObject> getShelfsByWarehouseId(String warehouseId) {
		List<ShelfJsonObject> shelfJsonObjectList = null;
		try {
			List<InventoryJsonModel> inventoryJsonModelList = inventoryDao.getInventoriesByWarehouseId(warehouseId);
			if(inventoryJsonModelList != null && inventoryJsonModelList.size() > 0) {
				shelfJsonObjectList = new ArrayList<ShelfJsonObject>();
				for(InventoryJsonModel inventoryJsonModel : inventoryJsonModelList) {
					List<ShelfJsonModel> shelfJsonModelList = inventoryDao.getShelfsByInventoryId(inventoryJsonModel.getId());
					if(shelfJsonModelList != null && shelfJsonModelList.size() > 0) {
						for(ShelfJsonModel shelfJsonModel : shelfJsonModelList) {
							ShelfJsonObject shelfJsonObject = convertShelfJsonModelToObject(shelfJsonModel);
							if(shelfJsonObject != null) {
								shelfJsonObjectList.add(shelfJsonObject);
							}
						}
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get Shelfs By WarehouseId");
			e.printStackTrace();
		}
		return shelfJsonObjectList;
	}
	
	public List<ShelfJsonObject> getShelfsByInventoryId(String inventoryId) {
		List<ShelfJsonObject> shelfJsonObjectList = null;
		try {
			List<ShelfJsonModel> shelfJsonModelList = inventoryDao.getShelfsByInventoryId(inventoryId);
			if(shelfJsonModelList != null && shelfJsonModelList.size() > 0) {
				shelfJsonObjectList = new ArrayList<ShelfJsonObject>();
				for(ShelfJsonModel shelfJsonModel : shelfJsonModelList) {
					ShelfJsonObject shelfJsonObject = convertShelfJsonModelToObject(shelfJsonModel);
					if(shelfJsonObject != null) {
						shelfJsonObjectList.add(shelfJsonObject);
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get Shelfs By InventoryId");
			e.printStackTrace();
		}
		return shelfJsonObjectList;
	}
	
	public ShelfJsonModel checkShelfById(String shelfId) {
		ShelfJsonModel shelfJsonModel = null;
		try {
			shelfJsonModel = inventoryDao.getShelfById(shelfId);
		} catch(Exception e) {
			LOGGERS.error("error occured while check Shelf By Id");
			e.printStackTrace();
		}
		return shelfJsonModel;
	}
	
	public ShelfJsonModel checkShelfByCode(String shelfCode) {
		ShelfJsonModel shelfJsonModel = null;
		try {
			shelfJsonModel = inventoryDao.checkShelfByCode(shelfCode);
		} catch(Exception e) {
			LOGGERS.error("error occured while check Shelf By code");
			e.printStackTrace();
		}
		return shelfJsonModel;
	}
	
	public boolean deleteShelfByWareHouseId(String warehouseId) {
		boolean status = false;
		try {
			List<InventoryJsonModel> inventoryJsonModelList = inventoryDao.getInventoriesByWarehouseId(warehouseId);
			if(inventoryJsonModelList != null && inventoryJsonModelList.size() > 0) {
				for(InventoryJsonModel inventoryJsonModel : inventoryJsonModelList) {
					status = inventoryDao.deleteShelfByInventoryId(inventoryJsonModel.getId());
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Shelf By WareHouseId");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteShelfByInventoryId(String inventoryId) {
		boolean status = false;
		try {
			status = inventoryDao.deleteShelfByInventoryId(inventoryId);
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Shelf By InventoryId");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteShelfById(String shelfId) {
		boolean status = false;
		try {
			status = inventoryDao.deleteShelfById(shelfId);
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Shelf By Id");
			e.printStackTrace();
		}
		return status;
	}
	
	public BinJsonModel convertBinJsonObjectToModel(BinJsonObject binJsonObject) {
		BinJsonModel binJsonModel = null;
		try {
			if(binJsonObject != null) {
				if(binJsonObject.getId() != null && !binJsonObject.getId().isEmpty()) {
					binJsonModel = inventoryDao.getBinById(binJsonObject.getId());
					binJsonModel.setBinCode(binJsonObject.getBinCode());
					binJsonModel.setUpdatedBy(binJsonObject.getCustomerId());
					binJsonModel.setUpdatedAt(new Date());
				} else {
					binJsonModel = new BinJsonModel();
					binJsonModel.setBinCode(binJsonObject.getBinCode());
					binJsonModel.setCreatedBy(binJsonObject.getCustomerId());
					binJsonModel.setCreatedAt(new Date());
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert BinJsonObject To Model");
			e.printStackTrace();
		}
		return binJsonModel;
	}
	
	public BinJsonObject convertBinJsonModelToObject(BinJsonModel binJsonModel) {
		BinJsonObject binJsonObject = null;
		try {
			if(binJsonModel != null) {
				binJsonObject = new BinJsonObject();
				binJsonObject.setId(binJsonModel.getId());
				binJsonObject.setBinCode(binJsonModel.getBinCode());
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert BinJsonModel To Object");
			e.printStackTrace();
		}
		return binJsonObject;
	}
	
	public String createBin(BinJsonObject binJsonObject, String shelfId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateBin(BinJsonObject binJsonObject, String shelfId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return false;
	}
	
	public BinJsonObject getBinById(String binId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<BinJsonObject> getBinsByShelfId(String shelfId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<BinJsonObject> getBinsByInventoryId(String inventoryId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public BinJsonModel checkBinById(String binId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public BinJsonModel checkBinByCode(String binCode) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteBinByShelfId(String shelfId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteBinByInventoryId(String inventoryId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteBinById(String binId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return false;
	}
	
	public PositionJsonModel convertPositionJsonObjectToModel(PositionJsonObject positionJsonObject) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public PositionJsonObject convertPositionJsonModelToObject(PositionJsonModel positionJsonModel) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public String createPosition(PositionJsonObject positionJsonObject) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updatePosition(PositionJsonObject positionJsonObject) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return false;
	}
	
	public PositionJsonObject getPositionById(String positionId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deletePositionById(String positionId) {
		try {
			
		} catch(Exception e) {
			LOGGERS.error("error occured while ");
			e.printStackTrace();
		}
		return false;
	}
}
