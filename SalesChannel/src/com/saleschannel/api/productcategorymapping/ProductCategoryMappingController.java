package com.saleschannel.api.productcategorymapping;

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

public class ProductCategoryMappingController extends SalesChannelServerResource<ProductCategoryMappingJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryMappingController.class);
	
	private ProductCategoryMappingServiceImpl categoryMappingService;
	
	private String marketPlaceId = null;
	
	private String categoryMappingId = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<ProductCategoryMappingJsonObject> productCategoryMappingJsonObjectList = new ArrayList<ProductCategoryMappingJsonObject>();
			if(categoryMappingId != null && !categoryMappingId.isEmpty()) {
				isValidReq = true;
				ProductCategoryMappingJsonObject productCategoryMappingJsonObject = categoryMappingService.getProductCategoryMappingById(categoryMappingId);
				if(productCategoryMappingJsonObject != null)
					productCategoryMappingJsonObjectList.add(productCategoryMappingJsonObject);
			} else if(marketPlaceId != null && !marketPlaceId.isEmpty()) {
				isValidReq = true;
				productCategoryMappingJsonObjectList = categoryMappingService.getProductCategoryMappingByCustomerIdAndMarketPlaceId(getCustomerId(), marketPlaceId);
			} else if(isAll) {
				isValidReq = true;
				productCategoryMappingJsonObjectList = categoryMappingService.getProductCategoryMappingByCustomerId(getCustomerId());
			}
			if(productCategoryMappingJsonObjectList != null && productCategoryMappingJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productCategoryMappingJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(50004);
				salesChannelErrorObject.setMessage(getErrorMessage(50004));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails CategoryMapping.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ProductCategoryMappingJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			String categoryMappingId = categoryMappingService.insertProductCategoryMapping(obj);
			if(categoryMappingId != null) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Category Mapping Inserted Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(50001);
				salesChannelErrorObject.setMessage(getErrorMessage(50001));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails CategoryMapping.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ProductCategoryMappingJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			boolean status = categoryMappingService.updateProductCategoryMapping(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Category Mapping Updated Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(50002);
				salesChannelErrorObject.setMessage(getErrorMessage(50002));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while UpdateDetails CategoryMapping.");
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
			if(categoryMappingId != null && !categoryMappingId.isEmpty()) {
				ProductCategoryMappingJsonObject productCategoryMappingJsonObject = categoryMappingService.getProductCategoryMappingById(categoryMappingId);
				if(productCategoryMappingJsonObject != null) {
					isExist = true;
					status = categoryMappingService.deleteProductCategoryMappingById(categoryMappingId);
				}
			} else if(marketPlaceId != null && !marketPlaceId.isEmpty()) {
				List<ProductCategoryMappingJsonObject> productCategoryMappingJsonObjectList = categoryMappingService.getProductCategoryMappingByCustomerIdAndMarketPlaceId(getCustomerId(), marketPlaceId);
				if(productCategoryMappingJsonObjectList != null) {
					isExist = true;
					status = categoryMappingService.deleteProductCategoryMappingByCustomerIdAndMarketPlaceId(getCustomerId(), marketPlaceId);
				}
			} else if(isAll) {
				List<ProductCategoryMappingJsonObject> productCategoryMappingJsonObjectList = categoryMappingService.getProductCategoryMappingByCustomerId(getCustomerId()); 
				if(productCategoryMappingJsonObjectList != null && productCategoryMappingJsonObjectList.size() > 0) {
					isExist = true;
					status = categoryMappingService.deleteProductCategoryMappingByCustomerId(getCustomerId());
				}
			}
			if(isExist && status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Product Category Mapping deleted Successfully.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(50003);
				salesChannelErrorObject.setMessage(getErrorMessage(50003));
			} else {
				salesChannelErrorObject.setStatusCode(50004);
				salesChannelErrorObject.setMessage(getErrorMessage(50004));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while delete CategoryMapping.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(ProductCategoryMappingJsonObject obj,
			String method, JSONObject jsonObject, Form form)
			throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			//Customer Category Id validation
			if(obj.getCustomerCategoryId() == null || obj.getCustomerCategoryId().isEmpty()) {
				jsonObject2.put("50011", "Customer category Id is empty.@#customerCategoryId#@");
				return jsonObject2;
			}
			//MarketPlace Category Id validation
			if(obj.getMarketPlaceCategoryId() == null || obj.getMarketPlaceCategoryId().isEmpty()) {
				jsonObject2.put("50012", "MarketPlace category Id is empty.@#marketPlaceCategoryId#@");
				return jsonObject2;
			}
		}
		//GET & DELETE method
		else if (method.equals(SalesChannelConstants.GET) || method.equals(SalesChannelConstants.DELETE)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("categoryMappingId")) {
							categoryMappingId = parameter.getValue();
						}
						else if (parameter.getName().equalsIgnoreCase("marketPlaceId")) {
							marketPlaceId = parameter.getValue();
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
	public ProductCategoryMappingJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductCategoryMappingJsonObject productCategoryMappingJsonObject = mapper.readValue(stream, ProductCategoryMappingJsonObject.class);
		return productCategoryMappingJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("marketPlaceId");
		paramList.add("categoryMappingId");
		paramList.add("isAll");
		return paramList;
	}

	public ProductCategoryMappingServiceImpl getCategoryMappingService() {
		return categoryMappingService;
	}

	public void setCategoryMappingService(ProductCategoryMappingServiceImpl categoryMappingService) {
		this.categoryMappingService = categoryMappingService;
	}

}
