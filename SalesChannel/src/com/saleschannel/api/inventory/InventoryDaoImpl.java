package com.saleschannel.api.inventory;

import java.util.List;

import com.saleschannel.api.constants.SalesChannelConstants;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class InventoryDaoImpl implements InventoryDao {

	private static final Logger LOGGERS = Logger.getLogger(InventoryDaoImpl.class);

	private MongoOperations mongoOps;
 	
	public InventoryDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	public String createInventory(InventoryJsonModel inventoryJsonModel) {
		String inventoryId = null;
		try {
			ObjectId objectId = new ObjectId();
			inventoryId = objectId.toString();
			inventoryJsonModel.setId(inventoryId);
			this.mongoOps.insert(inventoryJsonModel, SalesChannelConstants.SC_INVENTORY);
		} catch(Exception e) {
			LOGGERS.error("error occured while create Inventory in DB");
			e.printStackTrace();
		}
		return inventoryId;
	}
	
	public boolean updateInventory(InventoryJsonModel inventoryJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(inventoryJsonModel, SalesChannelConstants.SC_INVENTORY);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update Inventory in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public InventoryJsonModel getInventoryById(String inventoryId) {
		InventoryJsonModel inventoryJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.InventoryJsonModel")
					.and("_id").is(inventoryId));
			inventoryJsonModel = this.mongoOps.findOne(query, InventoryJsonModel.class, SalesChannelConstants.SC_INVENTORY);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Inventory By Id from DB");
			e.printStackTrace();
		}
		return inventoryJsonModel;
	}
	
	public List<InventoryJsonModel> getInventoriesByWarehouseId(String warehouseId) {
		List<InventoryJsonModel> inventoryJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.InventoryJsonModel")
					.and("warehouseId").is(warehouseId));
			inventoryJsonModelList = this.mongoOps.find(query, InventoryJsonModel.class, SalesChannelConstants.SC_INVENTORY);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Inventories By WarehouseId from DB");
			e.printStackTrace();
		}
		return inventoryJsonModelList;
	}
	
	public InventoryJsonModel checkInventoryByCode(String inventoryCode) {
		InventoryJsonModel inventoryJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.InventoryJsonModel")
					.and("inventoryCode").is(inventoryCode));
			inventoryJsonModel = this.mongoOps.findOne(query, InventoryJsonModel.class, SalesChannelConstants.SC_INVENTORY);
		} catch(Exception e) {
			LOGGERS.error("error occured while check Inventory By Code in DB");
			e.printStackTrace();
		}
		return inventoryJsonModel;
	}
	
	public boolean deleteInventoryByWareHouseId(String warehouseId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.InventoryJsonModel")
					.and("warehouseId").is(warehouseId));
			this.mongoOps.remove(query, SalesChannelConstants.SC_INVENTORY);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Inventory By WareHouseId in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteInventoryById(String inventoryId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.InventoryJsonModel")
					.and("_id").is(inventoryId));
			InventoryJsonModel inventoryJsonModel = this.mongoOps.findAndRemove(query, InventoryJsonModel.class, SalesChannelConstants.SC_INVENTORY);
			if(inventoryJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Inventory By Id in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public String createShelf(ShelfJsonModel shelfJsonModel) {
		String shelfId = null;
		try {
			ObjectId objectId = new ObjectId();
			shelfId = objectId.toString();
			shelfJsonModel.setId(shelfId);
			this.mongoOps.insert(shelfJsonModel, SalesChannelConstants.SC_SHELF);
		} catch(Exception e) {
			LOGGERS.error("error occured while create Shelf in DB");
			e.printStackTrace();
		}
		return shelfId;
	}
	
	public boolean updateShelf(ShelfJsonModel shelfJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(shelfJsonModel, SalesChannelConstants.SC_SHELF);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update Shelf in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public ShelfJsonModel getShelfById(String shelfId) {
		ShelfJsonModel shelfJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.ShelfJsonModel")
					.and("_id").is(shelfId));
			shelfJsonModel = this.mongoOps.findOne(query, ShelfJsonModel.class, SalesChannelConstants.SC_SHELF);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Shelf By Id from DB");
			e.printStackTrace();
		}
		return shelfJsonModel;
	}
	
	public List<ShelfJsonModel> getShelfsByInventoryId(String inventoryId) {
		List<ShelfJsonModel> shelfJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.ShelfJsonModel")
					.and("inventoryId").is(inventoryId));
			shelfJsonModelList = this.mongoOps.find(query, ShelfJsonModel.class, SalesChannelConstants.SC_SHELF);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Shelfs By Inventory Id from DB");
			e.printStackTrace();
		}
		return shelfJsonModelList;
	}
	
	public ShelfJsonModel checkShelfByCode(String shelfCode) {
		ShelfJsonModel shelfJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.ShelfJsonModel")
					.and("shelfCode").is(shelfCode));
			shelfJsonModel = this.mongoOps.findOne(query, ShelfJsonModel.class, SalesChannelConstants.SC_SHELF);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Shelf By Id from DB");
			e.printStackTrace();
		}
		return shelfJsonModel;
	}
	
	public boolean deleteShelfById(String shelfId)  {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.ShelfJsonModel")
					.and("_id").is(shelfId));
			ShelfJsonModel shelfJsonModel = this.mongoOps.findAndRemove(query, ShelfJsonModel.class, SalesChannelConstants.SC_SHELF);
			if(shelfJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Shelf By Id in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteShelfByInventoryId(String inventoryId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.ShelfJsonModel")
					.and("inventoryId").is(inventoryId));
			this.mongoOps.remove(query, SalesChannelConstants.SC_SHELF);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Shelf By Inventory Id in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public String createBin(BinJsonModel binJsonModel) {
		String binId = null;
		try {
			ObjectId objectId = new ObjectId();
			binId = objectId.toString();
			binJsonModel.setId(binId);
			this.mongoOps.insert(binJsonModel, SalesChannelConstants.SC_BIN);
		} catch(Exception e) {
			LOGGERS.error("error occured while create Bin in DB");
			e.printStackTrace();
		}
		return binId;
	}
	
	public boolean updateBin(BinJsonModel binJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(binJsonModel, SalesChannelConstants.SC_BIN);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update Bin in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public BinJsonModel getBinById(String binId) {
		BinJsonModel binJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinJsonModel")
					.and("_id").is(binId));
			binJsonModel = this.mongoOps.findOne(query, BinJsonModel.class, SalesChannelConstants.SC_BIN);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Bin By Id from DB");
			e.printStackTrace();
		}
		return binJsonModel;
	}
	
	public List<BinJsonModel> getBinsByShelfId(String shelfId) {
		List<BinJsonModel> binJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinJsonModel")
					.and("shelfId").is(shelfId));
			binJsonModelList = this.mongoOps.find(query, BinJsonModel.class, SalesChannelConstants.SC_BIN);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Bins By ShelfId from DB");
			e.printStackTrace();
		}
		return binJsonModelList;
	}
	
	public BinJsonModel checkBinByCode(String binCode) {
		BinJsonModel binJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinJsonModel")
					.and("binCode").is(binCode));
			binJsonModel = this.mongoOps.findOne(query, BinJsonModel.class, SalesChannelConstants.SC_BIN);
		} catch(Exception e) {
			LOGGERS.error("error occured while check Bin By Code in DB");
			e.printStackTrace();
		}
		return binJsonModel;
	}
	
	public boolean deleteBinByShelfId(String shelfId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinJsonModel")
					.and("shelfId").is(shelfId));
			this.mongoOps.remove(query, SalesChannelConstants.SC_BIN);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Bin By ShelfId in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteBinById(String binId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinJsonModel")
					.and("_id").is(binId));
			BinJsonModel binJsonModel = this.mongoOps.findAndRemove(query, BinJsonModel.class, SalesChannelConstants.SC_BIN);
			if(binJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Bin By Id in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	//Position Operations
	public String createPosition(PositionJsonModel positionJsonModel) {
		String positionId = null;
		try {
			ObjectId objectId = new ObjectId();
			positionId = objectId.toString();
			positionJsonModel.setId(positionId);
			this.mongoOps.insert(positionJsonModel, SalesChannelConstants.SC_POSITION);
		} catch(Exception e) {
			LOGGERS.error("error occured while create Position in DB");
			e.printStackTrace();
		}
		return positionId;
	}
	
	public boolean updatePosition(PositionJsonModel positionJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(positionJsonModel, SalesChannelConstants.SC_POSITION);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update Position in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public PositionJsonModel getPositionById(String positionId) {
		PositionJsonModel positionJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.PositionJsonModel")
					.and("_id").is(positionId));
			positionJsonModel = this.mongoOps.findOne(query, PositionJsonModel.class, SalesChannelConstants.SC_POSITION);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Position By Id from DB");
			e.printStackTrace();
		}
		return positionJsonModel;
	}
	
	public boolean deletePositionById(String positionId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.PositionJsonModel")
					.and("_id").is(positionId));
			PositionJsonModel positionJsonModel = this.mongoOps.findAndRemove(query, PositionJsonModel.class, SalesChannelConstants.SC_POSITION);
			if(positionJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete Position By Id in DB");
			e.printStackTrace();
		}
		return status;
	}

	public String createBinContent(BinContentJsonModel binContentJsonModel) {
		String binContentId = null;
		try {
			ObjectId objectId = new ObjectId();
			binContentId = objectId.toString();
			binContentJsonModel.setId(binContentId);
			this.mongoOps.insert(binContentJsonModel, SalesChannelConstants.SC_BIN_CONTENT);
		} catch(Exception e) {
			LOGGERS.error("error occured while create BinContent");
			e.printStackTrace();
		}
		return binContentId;
	}
	
	public boolean updateBinContent(BinContentJsonModel binContentJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(binContentJsonModel, SalesChannelConstants.SC_BIN_CONTENT);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update BinContent");
			e.printStackTrace();
		}
		return status;
	}
	
	public BinContentJsonModel getBinContentById(String binContentId) {
		BinContentJsonModel binContentJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinContentJsonModel")
					.and("_id").is(binContentId));
			binContentJsonModel = this.mongoOps.findOne(query, BinContentJsonModel.class, SalesChannelConstants.SC_BIN_CONTENT);
		} catch(Exception e) {
			LOGGERS.error("error occured while get BinContent By Id");
			e.printStackTrace();
		}
		return binContentJsonModel;
	}
	
	public List<BinContentJsonModel> getBinContentsByBinId(String binId) {
		List<BinContentJsonModel> binContentJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinContentJsonModel")
					.and("binId").is(binId));
			binContentJsonModelList = this.mongoOps.find(query, BinContentJsonModel.class, SalesChannelConstants.SC_BIN_CONTENT);
		} catch(Exception e) {
			LOGGERS.error("error occured while get BinContents By BinId");
			e.printStackTrace();
		}
		return binContentJsonModelList;
	}
	
	public boolean deleteBinContentByBinId(String binId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinContentJsonModel")
					.and("binId").is(binId));
			this.mongoOps.remove(query, SalesChannelConstants.SC_BIN_CONTENT);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete BinContent By BinId");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteBinContentById(String binContentId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.inventory.BinContentJsonModel")
					.and("_id").is(binContentId));
			BinContentJsonModel binContentJsonModel = this.mongoOps.findAndRemove(query, BinContentJsonModel.class, SalesChannelConstants.SC_BIN_CONTENT);
			if(binContentJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete BinContent By Id");
			e.printStackTrace();
		}
		return status;
	}
}
