package net.saleschannel.api.warehouse;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class WarehouseServiceImpl implements WarehouseService {

	private static final Logger LOGGERS = Logger.getLogger(WarehouseServiceImpl.class);
	
	private WarehouseDaoImpl warehouseDao;

	public WarehouseDaoImpl getWarehouseDao() {
		return warehouseDao;
	}

	public void setWarehouseDao(WarehouseDaoImpl warehouseDao) {
		this.warehouseDao = warehouseDao;
	}
	
	public WarehouseJsonModel convertWarehouseJsonObjectToModel(WarehouseJsonObject warehouseJsonObject) {
		WarehouseJsonModel warehouseJsonModel = null;
		try {
			if(warehouseJsonObject != null) {
				warehouseJsonModel = new WarehouseJsonModel();
				warehouseJsonModel.setId(warehouseJsonObject.getId());
				warehouseJsonModel.setWarehouseName(warehouseJsonObject.getWarehouseName());
				warehouseJsonModel.setCustomerId(warehouseJsonObject.getCustomerId());
				warehouseJsonModel.setAddress1(warehouseJsonObject.getAddress1());
				warehouseJsonModel.setAddress2(warehouseJsonObject.getAddress2());
				warehouseJsonModel.setCity(warehouseJsonObject.getCity());
				warehouseJsonModel.setProvince(warehouseJsonObject.getProvince());
				warehouseJsonModel.setPostalCode(warehouseJsonObject.getPostalCode());
				warehouseJsonModel.setCountry(warehouseJsonObject.getCountry());
			}
		} catch(Exception e) {
			LOGGERS.error("error occure while convert Warehouse JsonObject To Model");
			e.printStackTrace();
		}
		return warehouseJsonModel;
	}
	
	public WarehouseJsonObject convertWarehouseJsonModelToObject(WarehouseJsonModel warehouseJsonModel) {
		WarehouseJsonObject warehouseJsonObject = null;
		try {
			if(warehouseJsonModel != null) {
				warehouseJsonObject = new WarehouseJsonObject();
				warehouseJsonObject.setId(warehouseJsonModel.getId());
				warehouseJsonObject.setWarehouseName(warehouseJsonModel.getWarehouseName());
				warehouseJsonObject.setAddress1(warehouseJsonModel.getAddress1());
				warehouseJsonObject.setAddress2(warehouseJsonModel.getAddress2());
				warehouseJsonObject.setCity(warehouseJsonModel.getCity());
				warehouseJsonObject.setProvince(warehouseJsonModel.getProvince());
				warehouseJsonObject.setPostalCode(warehouseJsonModel.getPostalCode());
				warehouseJsonObject.setCountry(warehouseJsonModel.getCountry());
			}
		} catch(Exception e) {
			LOGGERS.error("error occure while convert WarehouseJsonModel To Object");
			e.printStackTrace();
		}
		return warehouseJsonObject;
	}
	
	public String createWarehouse(WarehouseJsonObject warehouseJsonObject) {
		String warehouseId = null;
		try {
			WarehouseJsonModel warehouseJsonModel = convertWarehouseJsonObjectToModel(warehouseJsonObject);
			if(warehouseJsonModel != null) {
				warehouseId = warehouseDao.createWarehouse(warehouseJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occure while createWarehouse");
			e.printStackTrace();
		}
		return warehouseId;
	}
	
	public boolean updateWarehouse(WarehouseJsonObject warehouseJsonObject) {
		boolean status = false;
		try {
			WarehouseJsonModel warehouseJsonModel = convertWarehouseJsonObjectToModel(warehouseJsonObject);
			if(warehouseJsonModel != null) {
				status = warehouseDao.updateWarehouse(warehouseJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occure while updateWarehouse");
			e.printStackTrace();
		}
		return status;
	}
	
	public WarehouseJsonObject getWarehouseById(String warehouseId) {
		WarehouseJsonObject warehouseJsonObject = null;
		try {
			WarehouseJsonModel warehouseJsonModel = warehouseDao.getWarehouseById(warehouseId);
			warehouseJsonObject = convertWarehouseJsonModelToObject(warehouseJsonModel);
		} catch(Exception e) {
			LOGGERS.error("error occure while getWarehouseById");
			e.printStackTrace();
		}
		return warehouseJsonObject;
	}
	
	public WarehouseJsonModel checkWarehouseById(String warehouseId) {
		WarehouseJsonModel warehouseJsonModel = null; 
		try {
			warehouseJsonModel = warehouseDao.getWarehouseById(warehouseId);
		} catch(Exception e) {
			LOGGERS.error("error occure while checkWarehouseById");
			e.printStackTrace();
		}
		return warehouseJsonModel;
	}
	
	public WarehouseJsonModel checkWarehouseExist(String warehouseName) {
		WarehouseJsonModel warehouseJsonModel = null; 
		try {
			warehouseJsonModel = warehouseDao.checkWarehouseExist(warehouseName);
		} catch(Exception e) {
			LOGGERS.error("error occure while checkWarehouseExist");
			e.printStackTrace();
		}
		return warehouseJsonModel;
	}
	
	public List<WarehouseJsonObject> getAllWarehouses(String customerId) {
		List<WarehouseJsonObject> warehouseJsonObjectList = null; 
		try {
			List<WarehouseJsonModel> warehouseJsonModelList = warehouseDao.getAllWarehouses(customerId);
			if(warehouseJsonModelList != null && warehouseJsonModelList.size() > 0) {
				warehouseJsonObjectList = new ArrayList<WarehouseJsonObject>();
				for(WarehouseJsonModel warehouseJsonModel : warehouseJsonModelList) {
					WarehouseJsonObject warehouseJsonObject = convertWarehouseJsonModelToObject(warehouseJsonModel);
					if(warehouseJsonObject != null)
						warehouseJsonObjectList.add(warehouseJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occure while getAllWarehouses");
			e.printStackTrace();
		}
		return warehouseJsonObjectList;
	}
	
	public boolean deleteWarehouseById(String warehouseId) {
		boolean status = false;
		try {
			status = warehouseDao.deleteWarehouseById(warehouseId);
		} catch(Exception e) {
			LOGGERS.error("error occure while deleteWarehouseById");
			e.printStackTrace();
		}
		return status;
	}
}
