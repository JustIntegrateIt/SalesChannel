package com.saleschannel.api.warehouse;

import java.util.List;

import com.saleschannel.api.constants.SalesChannelConstants;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class WarehouseDaoImpl implements WarehouseDao {
	
	private static final Logger LOGGERS = Logger.getLogger(WarehouseDaoImpl.class);

	private MongoOperations mongoOps;
 	
	public WarehouseDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	public String createWarehouse(WarehouseJsonModel warehouseJsonModel) {
		String warehouseId = null;
		try {
			ObjectId objectId = new ObjectId();
			warehouseId = objectId.toString();
			warehouseJsonModel.setId(warehouseId);
			this.mongoOps.insert(warehouseJsonModel, SalesChannelConstants.SC_WAREHOUSE);
		} catch(Exception e) {
			LOGGERS.error("error occured while create warehouse in DB");
			e.printStackTrace();
		}
		return warehouseId;
	}
	
	public boolean updateWarehouse(WarehouseJsonModel warehouseJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(warehouseJsonModel, SalesChannelConstants.SC_WAREHOUSE);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update warehouse in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public WarehouseJsonModel getWarehouseById(String warehouseId) {
		WarehouseJsonModel warehouseJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.warehouse.WarehouseJsonModel")
					.and("_id").is(warehouseId));
			warehouseJsonModel = this.mongoOps.findOne(query, WarehouseJsonModel.class, SalesChannelConstants.SC_WAREHOUSE);
		} catch(Exception e) {
			LOGGERS.error("error occured while get warehouse by id from DB");
			e.printStackTrace();
		}
		return warehouseJsonModel;
	}
	
	public WarehouseJsonModel checkWarehouseExist(String warehouseName) {
		WarehouseJsonModel warehouseJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.warehouse.WarehouseJsonModel")
					.and("warehouseName").is(warehouseName));
			warehouseJsonModel = this.mongoOps.findOne(query, WarehouseJsonModel.class, SalesChannelConstants.SC_WAREHOUSE);
		} catch(Exception e) {
			LOGGERS.error("error occured while get warehouse by name from DB");
			e.printStackTrace();
		}
		return warehouseJsonModel;
	}
	
	public List<WarehouseJsonModel> getAllWarehouses(String customerId) {
		List<WarehouseJsonModel> warehouseJsonModelList = null; 
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.warehouse.WarehouseJsonModel")
					.and("customerId").is(customerId));
			warehouseJsonModelList = this.mongoOps.find(query, WarehouseJsonModel.class, SalesChannelConstants.SC_WAREHOUSE);
		} catch(Exception e) {
			LOGGERS.error("error occured while get all warehouses from DB");
			e.printStackTrace();
		}
		return warehouseJsonModelList;
	}
	
	public boolean deleteWarehouseById(String warehouseId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.warehouse.WarehouseJsonModel")
					.and("_id").is(warehouseId));
			WarehouseJsonModel warehouseJsonModel = this.mongoOps.findAndRemove(query, WarehouseJsonModel.class, SalesChannelConstants.SC_WAREHOUSE);
			if(warehouseJsonModel != null) {
				status = true;
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while delete warehouse in DB");
			e.printStackTrace();
		}
		return status;
	}

}
