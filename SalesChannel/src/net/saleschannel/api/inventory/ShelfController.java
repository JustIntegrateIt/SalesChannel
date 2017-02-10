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

public class ShelfController extends SalesChannelServerResource<ShelfJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ShelfController.class);
	
	private InventoryServiceImpl inventoryService;
	
	private WarehouseServiceImpl warehouseService;
	
	private String warehouseId = null;
	
	private String inventoryId = null;
	
	private String shelfId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<ShelfJsonObject> shelfJsonObjectList = new ArrayList<ShelfJsonObject>();
			if(shelfId != null && !shelfId.isEmpty()) {
				isValidReq = true;
				ShelfJsonObject shelfJsonObject = inventoryService.getShelfById(shelfId);
				if(shelfJsonObject != null)
					shelfJsonObjectList.add(shelfJsonObject);
			} else if(warehouseId != null && !warehouseId.isEmpty()) {
				isValidReq = true;
				shelfJsonObjectList = inventoryService.getShelfsByWarehouseId(warehouseId);
			} else if(inventoryId != null && !inventoryId.isEmpty()) {
				isValidReq = true;
				shelfJsonObjectList = inventoryService.getShelfsByInventoryId(inventoryId);
			}
			if(shelfJsonObjectList != null && shelfJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(shelfJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(60204);
				salesChannelErrorObject.setMessage(getErrorMessage(60204));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Shelf.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ShelfJsonObject obj) {
		Representation representation = null;
		try {
		//Inventory Id validation
		if(inventoryId != null && !inventoryId.isEmpty()) {
			InventoryJsonModel inventoryJsonModel = inventoryService.checkInventoryById(inventoryId);
			if(inventoryJsonModel != null) {
				String shelfId = inventoryService.createShelf(obj, inventoryId);
				if(shelfId != null) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData("Shelf Created Successfully.");
				} else {
					salesChannelErrorObject.setStatusCode(60201);
					salesChannelErrorObject.setMessage(getErrorMessage(60201));
				}
			} else {
				salesChannelErrorObject.setStatusCode(60104);
				salesChannelErrorObject.setMessage(getErrorMessage(60104));
			}
		} else {
			salesChannelErrorObject.setStatusCode(60105);
			salesChannelErrorObject.setMessage(getErrorMessage(60105));
		}
		representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Shelf.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ShelfJsonObject obj) {
		Representation representation = null;
		try {
		//Inventory Id validation
		if(inventoryId != null && !inventoryId.isEmpty()) {
			InventoryJsonModel inventoryJsonModel = inventoryService.checkInventoryById(inventoryId);
			if(inventoryJsonModel != null) {
				boolean status = inventoryService.updateShelf(obj, inventoryId);
				if(status) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData("Shelf Updated Successfully.");
				} else {
					salesChannelErrorObject.setStatusCode(60202);
					salesChannelErrorObject.setMessage(getErrorMessage(60202));
				}
			} else {
				salesChannelErrorObject.setStatusCode(60104);
				salesChannelErrorObject.setMessage(getErrorMessage(60104));
			}
		} else {
			salesChannelErrorObject.setStatusCode(60105);
			salesChannelErrorObject.setMessage(getErrorMessage(60105));
		}
		representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails Shelf.");
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
			if(shelfId != null && !shelfId.isEmpty()) {
				ShelfJsonModel shelfJsonModel = inventoryService.checkShelfById(shelfId);
				if(shelfJsonModel != null) {
					isExist = true;
					status = inventoryService.deleteShelfById(shelfId);
				}
			} else if(inventoryId != null && !inventoryId.isEmpty()) {
				InventoryJsonModel inventoryJsonModel = inventoryService.checkInventoryById(inventoryId);
				if(inventoryJsonModel != null) {
					isExist = true;
					status = inventoryService.deleteShelfByInventoryId(inventoryId);
				}
			} else if(warehouseId != null && !warehouseId.isEmpty()) {
				WarehouseJsonModel warehouseJsonModel = warehouseService.checkWarehouseById(warehouseId);
				if(warehouseJsonModel != null) {
					isExist = true;
					status = inventoryService.deleteShelfByWareHouseId(warehouseId);
				}
			}
			if(isExist && status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Shelf deleted Successfully.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(60203);
				salesChannelErrorObject.setMessage(getErrorMessage(60203));
			} else {
				salesChannelErrorObject.setStatusCode(60204);
				salesChannelErrorObject.setMessage(getErrorMessage(60204));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while delete shelf.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(ShelfJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			obj.setCustomerId(getCustomerId());
			//ShelfCode validation
			if(obj.getShelfCode() != null && !obj.getShelfCode().isEmpty()) {
				if(method.equals(SalesChannelConstants.POST)) {
					ShelfJsonModel shelfJsonModel = inventoryService.checkShelfByCode(obj.getShelfCode());
					if(shelfJsonModel != null) {
						jsonObject2.put("60212", "ShelfCode is already exist.@#shelfCode#@");
						return jsonObject2;
					}
				}
			} else {
				jsonObject2.put("60211", "ShelfCode is empty.@#shelfCode#@");
				return jsonObject2;
			}
			//Shelf Position validation
			if(obj.getPosition() != null) {
				if(obj.getPosition().getRowPositionX() == null || obj.getPosition().getRowPositionX().isEmpty()
						|| obj.getPosition().getColumnPositionY() == null || obj.getPosition().getColumnPositionY().isEmpty()
						|| obj.getPosition().getThirdValueZ() == null || obj.getPosition().getThirdValueZ().isEmpty()) {
					jsonObject2.put("60214", "One Of Positions is empty.@#position#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("60213", "Position is empty.@#shelfCode#@");
				return jsonObject2;
			}
			//Shelf Id validation
			if(method.equals(SalesChannelConstants.PUT)) {
				if(obj.getId() != null && !obj.getId().isEmpty()) {
					ShelfJsonModel shelfJsonModel = inventoryService.checkShelfById(obj.getId());
					if(shelfJsonModel == null) {
						jsonObject2.put("60216", "Shelf Id is not valid.@#id#@");
						return jsonObject2;
					}
				} else {
					jsonObject2.put("60215", "Shelf Id is empty.@#id#@");
					return jsonObject2;
				}
				if(obj.getPosition() != null) {
					if(obj.getPosition().getId() != null && obj.getPosition().getId().isEmpty()) {
						PositionJsonObject positionJsonObject = inventoryService.getPositionById(obj.getPosition().getId());
						if(positionJsonObject == null) {
							jsonObject2.put("60218", "Position Id is not valid.@#id#@");
							return jsonObject2;
						}
					}
				} else {
					jsonObject2.put("60217", "Position Id is empty.@#id#@");
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
					else if (parameter.getName().equalsIgnoreCase("shelfId")) {
						shelfId = parameter.getValue();
					}
				}
			}
		}
		return jsonObject2;
	}

	@Override
	public ShelfJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ShelfJsonObject shelfJsonObject = mapper.readValue(stream, ShelfJsonObject.class);
		return shelfJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("warehouseId");
		paramList.add("inventoryId");
		paramList.add("shelfId");
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
