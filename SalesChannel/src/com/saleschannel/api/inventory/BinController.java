package com.saleschannel.api.inventory;

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
import com.saleschannel.api.utility.SalesChannelUtility;

public class BinController extends SalesChannelServerResource<BinJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ShelfController.class);
	
	private InventoryServiceImpl inventoryService;
	
	private String inventoryId = null;
	
	private String shelfId = null;
	
	private String binId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<BinJsonObject> binJsonObjectList = new ArrayList<BinJsonObject>();
			if(binId != null && !binId.isEmpty()) {
				isValidReq = true;
				BinJsonObject binJsonObject = inventoryService.getBinById(binId);
				if(binJsonObject != null)
					binJsonObjectList.add(binJsonObject);
			} else if(shelfId != null && !shelfId.isEmpty()) {
				isValidReq = true;
				binJsonObjectList = inventoryService.getBinsByShelfId(shelfId);
			} else if(inventoryId != null && !inventoryId.isEmpty()) {
				isValidReq = true;
				binJsonObjectList = inventoryService.getBinsByInventoryId(inventoryId);
			}
			if(binJsonObjectList != null && binJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(binJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(60304);
				salesChannelErrorObject.setMessage(getErrorMessage(60304));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Bin.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			BinJsonObject obj) {
		Representation representation = null;
		try {
		//Shelf Id validation
		if(shelfId != null && !shelfId.isEmpty()) {
			ShelfJsonModel shelfJsonModel = inventoryService.checkShelfById(shelfId);
			if(shelfJsonModel != null) {
				String binId = inventoryService.createBin(obj, shelfId);
				if(binId != null) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData("Bin Created Successfully.");
				} else {
					salesChannelErrorObject.setStatusCode(60301);
					salesChannelErrorObject.setMessage(getErrorMessage(60301));
				}
			} else {
				salesChannelErrorObject.setStatusCode(60204);
				salesChannelErrorObject.setMessage(getErrorMessage(60204));
			}
		} else {
			salesChannelErrorObject.setStatusCode(60205);
			salesChannelErrorObject.setMessage(getErrorMessage(60205));
		}
		representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Bin.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity, BinJsonObject obj) {
		Representation representation = null;
		try {
		//Shelf Id validation
		if(shelfId != null && !shelfId.isEmpty()) {
			ShelfJsonModel shelfJsonModel = inventoryService.checkShelfById(shelfId);
			if(shelfJsonModel != null) {
				boolean status = inventoryService.updateBin(obj, shelfId);
				if(status) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData("Bin Updated Successfully.");
				} else {
					salesChannelErrorObject.setStatusCode(60302);
					salesChannelErrorObject.setMessage(getErrorMessage(60302));
				}
			} else {
				salesChannelErrorObject.setStatusCode(60204);
				salesChannelErrorObject.setMessage(getErrorMessage(60204));
			}
		} else {
			salesChannelErrorObject.setStatusCode(60205);
			salesChannelErrorObject.setMessage(getErrorMessage(60205));
		}
		representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails Bin.");
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
			if(binId != null && !binId.isEmpty()) {
				BinJsonModel binJsonModel = inventoryService.checkBinById(binId);
				if(binJsonModel != null) {
					isExist = true;
					status = inventoryService.deleteBinById(binId);
				}
			} else if(inventoryId != null && !inventoryId.isEmpty()) {
				InventoryJsonModel inventoryJsonModel = inventoryService.checkInventoryById(inventoryId);
				if(inventoryJsonModel != null) {
					isExist = true;
					status = inventoryService.deleteBinByInventoryId(inventoryId);
				}
			} else if(shelfId != null && !shelfId.isEmpty()) {
				ShelfJsonModel shelfJsonModel = inventoryService.checkShelfById(shelfId);
				if(shelfJsonModel != null) {
					isExist = true;
					status = inventoryService.deleteBinByShelfId(shelfId);
				}
			}
			if(isExist && status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Bin deleted Successfully.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(60303);
				salesChannelErrorObject.setMessage(getErrorMessage(60303));
			} else {
				salesChannelErrorObject.setStatusCode(60304);
				salesChannelErrorObject.setMessage(getErrorMessage(60304));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while delete Bin.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(BinJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			obj.setCustomerId(getCustomerId());
			//BinCode validation
			if(obj.getBinCode() != null && !obj.getBinCode().isEmpty()) {
				if(method.equals(SalesChannelConstants.POST)) {
					BinJsonModel binJsonModel = inventoryService.checkBinByCode(obj.getBinCode());
					if(binJsonModel != null) {
						jsonObject2.put("60312", "BinCode is already exist.@#binCode#@");
						return jsonObject2;
					}
				}
			} else {
				jsonObject2.put("60311", "BinCode is empty.@#binCode#@");
				return jsonObject2;
			}
			//Bin Id validation
			if(method.equals(SalesChannelConstants.PUT)) {
				if(obj.getId() != null && !obj.getId().isEmpty()) {
					BinJsonModel binJsonModel = inventoryService.checkBinById(obj.getId());
					if(binJsonModel == null) {
						jsonObject2.put("60314", "Bin Id is not valid.@#id#@");
						return jsonObject2;
					}
				} else {
					jsonObject2.put("60313", "Bin Id is empty.@#id#@");
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
					if (parameter.getName().equalsIgnoreCase("binId")) {
						binId = parameter.getValue();
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
	public BinJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final BinJsonObject binJsonObject = mapper.readValue(stream, BinJsonObject.class);
		return binJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("inventoryId");
		paramList.add("shelfId");
		paramList.add("binId");
		return paramList;
	}

	public InventoryServiceImpl getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryServiceImpl inventoryService) {
		this.inventoryService = inventoryService;
	}

}
