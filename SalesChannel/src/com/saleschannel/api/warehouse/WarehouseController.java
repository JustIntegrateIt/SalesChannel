package com.saleschannel.api.warehouse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.data.Parameter;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import com.saleschannel.api.SalesChannelServerResource;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.inventory.BinJsonModel;
import com.saleschannel.api.inventory.BinJsonObject;
import com.saleschannel.api.inventory.InventoryJsonModel;
import com.saleschannel.api.inventory.InventoryJsonObject;
import com.saleschannel.api.inventory.InventoryServiceImpl;
import com.saleschannel.api.inventory.ShelfJsonModel;
import com.saleschannel.api.inventory.ShelfJsonObject;
import com.saleschannel.api.utility.SalesChannelUtility;

public class WarehouseController extends SalesChannelServerResource<WarehouseJsonObject>{

	private static final Logger LOGGERS = Logger.getLogger(WarehouseController.class);
	
	private WarehouseServiceImpl warehouseService;
	
	private InventoryServiceImpl inventoryService;
	
	private String warehouseId = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<WarehouseJsonObject> warehouseJsonObjectList = new ArrayList<WarehouseJsonObject>();
			if(warehouseId != null && !warehouseId.isEmpty()) {
				isValidReq = true;
				WarehouseJsonObject warehouseJsonObject = warehouseService.getWarehouseById(warehouseId);
				if(warehouseJsonObject != null)
					warehouseJsonObjectList.add(warehouseJsonObject);
			} else if(isAll) {
				isValidReq = true;
				warehouseJsonObjectList = warehouseService.getAllWarehouses(getCustomerId());
			}
			if(warehouseJsonObjectList != null && warehouseJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(warehouseJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(60004);
				salesChannelErrorObject.setMessage(getErrorMessage(60004));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Warehouse.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			WarehouseJsonObject obj) {
		Representation representation = null;
		try {
			String warehouseId = warehouseService.createWarehouse(obj);
			if(warehouseId != null) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Warehouse Created Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(60001);
				salesChannelErrorObject.setMessage(getErrorMessage(60001));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Warehouse.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			WarehouseJsonObject obj) {
		Representation representation = null;
		try {
			boolean status = warehouseService.updateWarehouse(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Warehouse Updated Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(60002);
				salesChannelErrorObject.setMessage(getErrorMessage(60002));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails Warehouse.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation deleteDetails() {
		Representation representation = null;
		try {
			boolean status = false;
			boolean isExist = false;
			if(warehouseId != null && !warehouseId.isEmpty()) {
				WarehouseJsonModel warehouseJsonModel = warehouseService.checkWarehouseById(warehouseId);
				if(warehouseJsonModel != null) {
					isExist = true;
					status = warehouseService.deleteWarehouseById(warehouseId);
				}
			} else if(isAll) {
				isExist = true;
			}
			if(isExist && status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Warehouse deleted Successfully.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(60003);
				salesChannelErrorObject.setMessage(getErrorMessage(60003));
			} else {
				salesChannelErrorObject.setStatusCode(60004);
				salesChannelErrorObject.setMessage(getErrorMessage(60004));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while delete Warehouse.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(WarehouseJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			obj.setCustomerId(getCustomerId());
			//WarehouseName validation
			if(obj.getWarehouseName() != null && !obj.getWarehouseName().isEmpty()) {
				if(method.equals(SalesChannelConstants.POST)) {
					WarehouseJsonModel warehouseJsonModel = warehouseService.checkWarehouseExist(obj.getWarehouseName());
					if(warehouseJsonModel != null) {
						jsonObject2.put("60012", "WarehouseName is already exist.@#warehouseName#@");
						return jsonObject2;
					}
				}
			} else {
				jsonObject2.put("60011", "WarehouseName is empty.@#warehouseName#@");
				return jsonObject2;
			}
			//address1 validation
			if(obj.getAddress1() == null || obj.getAddress1().isEmpty()) {
				jsonObject2.put("60013", "Address1 is empty.@#address1#@");
				return jsonObject2;
			}
			//city validation
			if(obj.getCity() == null || obj.getCity().isEmpty()) {
				jsonObject2.put("60014", "City is empty.@#city#@");
				return jsonObject2;
			}
			//province validation
			if(obj.getProvince() == null || obj.getProvince().isEmpty()) {
				jsonObject2.put("60015", "Province is empty.@#province#@");
				return jsonObject2;
			}
			//postalCode validation
			if(obj.getPostalCode() == null || obj.getPostalCode().isEmpty()) {
				jsonObject2.put("60016", "PostalCode is empty.@#postalCode#@");
				return jsonObject2;
			}
			//country validation
			if(obj.getCountry() == null || obj.getCountry().isEmpty()) {
				jsonObject2.put("60017", "Country is empty.@#country#@");
				return jsonObject2;
			}
			//Inventory validation
			if(obj.getInventoryList() != null && !obj.getInventoryList().isEmpty() && 
					obj.getInventoryList().size() > 0) {
				for(InventoryJsonObject inventory : obj.getInventoryList()) {
					//id validation
					if(inventory.getId() != null && !inventory.getId().isEmpty()) {
						InventoryJsonObject inventoryCheck = inventoryService.getInventoryById(inventory.getId());
						if(inventoryCheck != null) {
							//inventoryCode validation
							InventoryJsonModel inventoryModel = inventoryService.checkInventoryByCode(inventory.getInventoryCode());
							if(inventoryModel != null && !inventoryModel.getId().equals(inventory.getId())
									&& inventoryModel.getWarehouseId().equals(obj.getId())) {
								jsonObject2.put("60022", "Inventory Code already exist.@#inventoryCode#@");
								return jsonObject2;
							}
							
						} else {
							jsonObject2.put("60021", "Invalid InventoryId passed while update.@#id#@");
							return jsonObject2;
						}
					} else {
						//inventoryCode validation
						InventoryJsonModel inventoryModel = inventoryService.checkInventoryByCode(inventory.getInventoryCode());
						if(inventoryModel != null) {
							jsonObject2.put("60023", "Inventory Code already exist.@#inventoryCode#@");
							return jsonObject2;
						}
					}
					//shelf Validation
					for(ShelfJsonObject shelf : inventory.getShelfList()) {
						if(shelf.getId() != null) {
							ShelfJsonObject shelfCheck = inventoryService.getShelfById(shelf.getId());
							if(shelfCheck != null) {
								ShelfJsonModel shelfModel = inventoryService.checkShelfByCode(shelf.getShelfCode());
								if(shelfModel != null && !shelfModel.getId().equals(shelf.getId())) {
									jsonObject2.put("60026", "Shelf Code already exist.@#shelfCode#@");
									return jsonObject2;	
								}	
							} else {
								jsonObject2.put("60025", "Shelf Id is not valid.@#id#@");
								return jsonObject2;
							}
						} else {
							ShelfJsonModel shelfCheck = inventoryService.checkShelfByCode(shelf.getShelfCode());
							if(shelfCheck != null) {
								jsonObject2.put("60024", "Shelf Code already exist.@#shelfCode#@");
								return jsonObject2;	
							}
						}
						//bin Validation
						for(BinJsonObject bin : shelf.getBinList()) {
							if(bin.getId() != null) {
								ShelfJsonObject shelfCheck = inventoryService.getShelfById(shelf.getId());
								if(shelfCheck != null) {
									BinJsonModel binModel = inventoryService.checkBinByCode(bin.getBinCode());
									if(binModel != null && !binModel.getId().equals(bin.getId())) {
										jsonObject2.put("60028", "Bin Code already exist.@#binCode#@");
										return jsonObject2;	
									}	
								} else {
									jsonObject2.put("60027", "Bin Id is not valid.@#id#@");
									return jsonObject2;
								}
							} else {
								BinJsonModel binCheck = inventoryService.checkBinByCode(bin.getBinCode());
								if(binCheck != null) {
									jsonObject2.put("60026", "Bin Code already exist.@#binCode#@");
									return jsonObject2;	
								}
							}
						}
					}
				}
			}
			if (method.equals(SalesChannelConstants.PUT)) {
				//id validation
				if(obj.getId() != null && !obj.getId().isEmpty()) {
					WarehouseJsonModel warehouseJsonModel = warehouseService.checkWarehouseById(obj.getId());
					if(warehouseJsonModel == null) {
						jsonObject2.put("60019", "Warehouse Id is not valid.@#id#@");
						return jsonObject2;
					}
				} else {
					jsonObject2.put("60018", "Warehouse Id is empty.@#id#@");
					return jsonObject2;
				}
				//warehouseName validation
				WarehouseJsonModel warehouseJsonModel = warehouseService.checkWarehouseExist(obj.getWarehouseName());
				if(warehouseJsonModel != null && !warehouseJsonModel.getId().equals(obj.getId())) {
					jsonObject2.put("60020", "WarehouseName is already exist.@#warehouseName#@");
					return jsonObject2;
				}
			}
		}
		//GET && DELETE method
		else if (method.equals(SalesChannelConstants.GET) || method.equals(SalesChannelConstants.DELETE)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("warehouseId")) {
							warehouseId = parameter.getValue();
						}
						else if (parameter.getName().equalsIgnoreCase("isAll")) {
							String isAll = parameter.getValue();
							if(isAll != null && !isAll.isEmpty() && isAll.equals("true")) {
								this.isAll = true;
							}
						}
					}
				}
			}
		}
		return jsonObject2;
	}

	@Override
	public WarehouseJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final WarehouseJsonObject warehouseJsonObject = mapper.readValue(stream, WarehouseJsonObject.class);
		return warehouseJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("warehouseId");
		paramList.add("isAll");
		return paramList;
	}

	public WarehouseServiceImpl getWarehouseService() {
		return warehouseService;
	}

	public void setWarehouseService(WarehouseServiceImpl warehouseService) {
		this.warehouseService = warehouseService;
	}

	public InventoryServiceImpl getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryServiceImpl inventoryService) {
		this.inventoryService = inventoryService;
	}

}
