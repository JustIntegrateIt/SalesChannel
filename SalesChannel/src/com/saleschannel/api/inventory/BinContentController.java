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
import com.saleschannel.api.constants.ProductTypes;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.product.ProductAttributesJsonModel;
import com.saleschannel.api.product.ProductJsonModel;
import com.saleschannel.api.product.ProductServiceImpl;
import com.saleschannel.api.utility.SalesChannelUtility;

public class BinContentController extends SalesChannelServerResource<BinContentJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(BinContentController.class);
	
	private InventoryServiceImpl inventoryService;
	
	private ProductServiceImpl productService;
	
	private String binId = null;
	
	private String binContentId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<BinContentJsonObject> binContentJsonObjectList = new ArrayList<BinContentJsonObject>();
			if(binContentId != null && !binContentId.isEmpty()) {
				isValidReq = true;
				BinContentJsonObject binContentJsonObject = inventoryService.getBinContentById(binContentId);
				if(binContentJsonObject != null)
					binContentJsonObjectList.add(binContentJsonObject);
			} else if(binId != null && !binId.isEmpty()) {
				isValidReq = true;
				binContentJsonObjectList = inventoryService.getBinContentsByBinId(binId);
			}
			if(binContentJsonObjectList != null && binContentJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(binContentJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(60404);
				salesChannelErrorObject.setMessage(getErrorMessage(60404));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails BinContent.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			BinContentJsonObject obj) {
		Representation representation = null;
		try {
			String binContentId = inventoryService.createBinContent(obj);
			if(binContentId != null) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("BinContent Created Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(60401);
				salesChannelErrorObject.setMessage(getErrorMessage(60401));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails BinContent.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			BinContentJsonObject obj) {
		Representation representation = null;
		try {
			boolean status = inventoryService.updateBinContent(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("BinContent Updated Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(60402);
				salesChannelErrorObject.setMessage(getErrorMessage(60402));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails BinContent.");
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
			if(binContentId != null && !binContentId.isEmpty()) {
				BinContentJsonModel binContentJsonModel = inventoryService.checkBinContentById(binContentId);
				if(binContentJsonModel != null) {
					isExist = true;
					status = inventoryService.deleteBinContentById(binContentId);
				}
			}
			if(isExist && status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("BinContent deleted Successfully.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(60403);
				salesChannelErrorObject.setMessage(getErrorMessage(60403));
			} else {
				salesChannelErrorObject.setStatusCode(60404);
				salesChannelErrorObject.setMessage(getErrorMessage(60404));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while delete BinContent.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(BinContentJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			obj.setCustomerId(getCustomerId());
			//BinId validation
			if(obj.getBinId() != null && !obj.getBinId().isEmpty()) {
				BinJsonModel binJsonModel = inventoryService.checkBinById(obj.getBinId());
				if(binJsonModel == null) {
					jsonObject2.put("60412", "BinId not exist.@#binId#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("60411", "BinId is empty.@#binId#@");
				return jsonObject2;
			}
			//ProductId validation
			if(obj.getProductId() != null && !obj.getProductId().isEmpty()) {
				ProductJsonModel productJsonModel = productService.checkProductById(obj.getProductId());
				if(productJsonModel != null) {
					if(!productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString())) {
						//ProductAttributesId validation
						if(obj.getProductAttributesId() != null && !obj.getProductAttributesId().isEmpty()) {
							ProductAttributesJsonModel productAttributesJsonModel = productService.getProductAttributeById(obj.getProductAttributesId());
							if(productAttributesJsonModel != null) {
								if(productJsonModel.getId() == null || productJsonModel.getId().isEmpty() || productAttributesJsonModel.getId() == null
										|| productAttributesJsonModel.getId().isEmpty() || !productAttributesJsonModel.getProductId().equals(productJsonModel.getId())) {
									jsonObject2.put("60417", "ProductAttributesId is not valid for ProductId.@#productAttributesId#@");
									return jsonObject2;
								}
							} else {
								jsonObject2.put("60416", "ProductAttributesId is not valid.@#productAttributesId#@");
								return jsonObject2;
							}
						} else {
							jsonObject2.put("60415", "ProductAttributesId is empty.@#productAttributesId#@");
							return jsonObject2;
						}
					}
				} else {
					jsonObject2.put("60414", "ProductId is not valid.@#productId#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("60413", "ProductId is empty.@#productId#@");
				return jsonObject2;
			}
			//Quantity validation
			if(obj.getQuantity() == null) {
				jsonObject2.put("60418", "Quantity is empty.@#quantity#@");
				return jsonObject2;
			}
			//Cost validation
			if(obj.getCost() == null) {
				jsonObject2.put("60419", "Cost is empty.@#cost#@");
				return jsonObject2;
			}
		}
		//PUT method
		if (method.equals(SalesChannelConstants.PUT)) {
			//BinContentId validation
			if(obj.getId() != null && !obj.getId().isEmpty()) {
				BinContentJsonModel binContentJsonModel = inventoryService.checkBinContentById(obj.getId());
				if(binContentJsonModel == null) {
					jsonObject2.put("60421", "BinContentId not exist.@#id#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("60420", "BinContentIs is empty.@#id#@");
				return jsonObject2;
			}
		}
		//GET & DELETE method
		if (method.equals(SalesChannelConstants.GET) || method.equals(SalesChannelConstants.DELETE)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("binId")) {
							binId = parameter.getValue();
						} 
						else if (parameter.getName().equalsIgnoreCase("binContentId")) {
							binContentId = parameter.getValue();
						}
					}
				}
			}
		}
		return jsonObject2;
	}

	@Override
	public BinContentJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final BinContentJsonObject binContentJsonObject = mapper.readValue(stream, BinContentJsonObject.class);
		return binContentJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("binId");
		paramList.add("binContentId");
		return paramList;
	}

	public InventoryServiceImpl getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryServiceImpl inventoryService) {
		this.inventoryService = inventoryService;
	}

	public ProductServiceImpl getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}

}
