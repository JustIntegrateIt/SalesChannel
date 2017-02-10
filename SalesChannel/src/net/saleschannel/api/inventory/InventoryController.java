package net.saleschannel.api.inventory;

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

import net.saleschannel.api.SalesChannelServerResource;
import net.saleschannel.api.constants.SalesChannelConstants;
import net.saleschannel.api.utility.SalesChannelUtility;
import net.saleschannel.api.warehouse.WarehouseJsonModel;
import net.saleschannel.api.warehouse.WarehouseServiceImpl;

public class InventoryController extends SalesChannelServerResource<InventoryJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(InventoryController.class);
	
	private InventoryServiceImpl inventoryService;
	
	private WarehouseServiceImpl warehouseService;
	
	private String warehouseId = null;
	
	private String inventoryId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<InventoryJsonObject> inventoryJsonObjectList = new ArrayList<InventoryJsonObject>();
			if(inventoryId != null && !inventoryId.isEmpty()) {
				isValidReq = true;
				InventoryJsonObject inventoryJsonObject = inventoryService.getInventoryById(inventoryId);
				if(inventoryJsonObject != null)
					inventoryJsonObjectList.add(inventoryJsonObject);
			} else if(warehouseId != null && !warehouseId.isEmpty()) {
				isValidReq = true;
				inventoryJsonObjectList = inventoryService.getInventoriesByWarehouseId(warehouseId);
			}
			if(inventoryJsonObjectList != null && inventoryJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(inventoryJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(60104);
				salesChannelErrorObject.setMessage(getErrorMessage(60104));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Inventory.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			InventoryJsonObject obj) {
		Representation representation = null;
		try {
		//Warehouse Id validation
		if(warehouseId != null && !warehouseId.isEmpty()) {
			WarehouseJsonModel warehouseJsonModel = warehouseService.checkWarehouseById(warehouseId);
			if(warehouseJsonModel != null) {
				String inventoryId = inventoryService.createInventory(obj, warehouseId);
				if(inventoryId != null) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData("Inventory Created Successfully.");
				} else {
					salesChannelErrorObject.setStatusCode(60101);
					salesChannelErrorObject.setMessage(getErrorMessage(60101));
				}
			} else {
				salesChannelErrorObject.setStatusCode(60004);
				salesChannelErrorObject.setMessage(getErrorMessage(60004));
			}
		} else {
			salesChannelErrorObject.setStatusCode(60005);
			salesChannelErrorObject.setMessage(getErrorMessage(60005));
		}
		representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Inventory.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			InventoryJsonObject obj) {
		Representation representation = null;
		try {
		//Warehouse Id validation
		if(warehouseId != null && !warehouseId.isEmpty()) {
			WarehouseJsonModel warehouseJsonModel = warehouseService.checkWarehouseById(warehouseId);
			if(warehouseJsonModel != null) {
				boolean status = inventoryService.updateInventory(obj, warehouseId);
				if(status) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData("Inventory Updated Successfully.");
				} else {
					salesChannelErrorObject.setStatusCode(60102);
					salesChannelErrorObject.setMessage(getErrorMessage(60102));
				}
			} else {
				salesChannelErrorObject.setStatusCode(60004);
				salesChannelErrorObject.setMessage(getErrorMessage(60004));
			}
		} else {
			salesChannelErrorObject.setStatusCode(60005);
			salesChannelErrorObject.setMessage(getErrorMessage(60005));
		}
		representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails Inventory.");
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
				List<InventoryJsonObject> inventoryJsonObjectList = inventoryService.getInventoriesByWarehouseId(warehouseId);
				if(inventoryJsonObjectList != null && inventoryJsonObjectList.size() > 0) {
					isExist = true;
					status = inventoryService.deleteInventoryByWareHouseId(warehouseId);
				}
			} else if(inventoryId != null && !inventoryId.isEmpty()) {
				InventoryJsonModel inventoryJsonModel = inventoryService.checkInventoryById(inventoryId);
				if(inventoryJsonModel != null) {
					isExist = true;
					status = inventoryService.deleteInventoryById(inventoryId);
				}
			}
			if(isExist && status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Inventory deleted Successfully.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(60103);
				salesChannelErrorObject.setMessage(getErrorMessage(60103));
			} else {
				salesChannelErrorObject.setStatusCode(60104);
				salesChannelErrorObject.setMessage(getErrorMessage(60104));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while delete Inventory.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(InventoryJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			obj.setCustomerId(getCustomerId());
			//InventoryCode validation
			if(obj.getInventoryCode() != null && !obj.getInventoryCode().isEmpty()) {
				if(method.equals(SalesChannelConstants.POST)) {
					InventoryJsonModel inventoryJsonModel = inventoryService.checkInventoryByCode(obj.getInventoryCode());
					if(inventoryJsonModel != null) {
						jsonObject2.put("60112", "InventoryCode is already exist.@#inventoryCode#@");
						return jsonObject2;
					}
				}
			} else {
				jsonObject2.put("60111", "InventoryCode is empty.@#inventoryCode#@");
				return jsonObject2;
			}
			//Inventory Id validation
			if(method.equals(SalesChannelConstants.PUT)) {
				if(obj.getId() != null && !obj.getId().isEmpty()) {
					InventoryJsonModel inventoryJsonModel = inventoryService.checkInventoryById(obj.getId());
					if(inventoryJsonModel == null) {
						jsonObject2.put("60114", "Inventory Id is not valid.@#id#@");
						return jsonObject2;
					}
				} else {
					jsonObject2.put("60113", "Inventory Id is empty.@#id#@");
					return jsonObject2;
				}
			}
			
		}
		//GET PUT POST && DELETE method
		if (!form.isEmpty()) {
			for (final Parameter parameter : form) {
				jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
				if (jsonObject.length() != 0) {
					return jsonObject2;
				} else {
					if (parameter.getName().equalsIgnoreCase("warehouseId")) {
						warehouseId = parameter.getValue();
					} 
					else if (parameter.getName().equalsIgnoreCase("inventoryId")) {
						inventoryId = parameter.getValue();
					}
				}
			}
		}
		return jsonObject2;
	}

	@Override
	public InventoryJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final InventoryJsonObject inventoryJsonObject = mapper.readValue(stream, InventoryJsonObject.class);
		return inventoryJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("warehouseId");
		paramList.add("inventoryId");
		return paramList;
	}

	public InventoryServiceImpl getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryServiceImpl inventoryService) {
		this.inventoryService = inventoryService;
	}

	public WarehouseServiceImpl getWarehouseService() {
		return warehouseService;
	}

	public void setWarehouseService(WarehouseServiceImpl warehouseService) {
		this.warehouseService = warehouseService;
	}

}
