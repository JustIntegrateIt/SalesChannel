package net.saleschannel.api.marketplace;

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

public class MarketPlaceCustomerRegionController extends SalesChannelServerResource<MarketPlaceCustomerRegionJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(MarketPlaceCustomerRegionController.class);

	private MarketPlaceServiceImpl marketPlaceService;
	
	private String customerRegionId = null;
	
	private String marketPlaceId = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<MarketPlaceCustomerRegionJsonObject> marketPlaceCustomerRegionJsonObjectList = new ArrayList<MarketPlaceCustomerRegionJsonObject>();
			if(customerRegionId != null && !customerRegionId.isEmpty()) {
				isValidReq = true;
				MarketPlaceCustomerRegionJsonObject marketPlaceJsonObject = marketPlaceService.getMarketPlaceCustomerRegionById(customerRegionId);
				if(marketPlaceJsonObject != null)
					marketPlaceCustomerRegionJsonObjectList.add(marketPlaceJsonObject);
			} else if(marketPlaceId != null && !marketPlaceId.isEmpty()) {
				isValidReq = true;
				marketPlaceCustomerRegionJsonObjectList = marketPlaceService.getMarketPlaceCustomerRegions(marketPlaceId, getCustomerId());
			} else if(isAll) {
				isValidReq = true;
				marketPlaceCustomerRegionJsonObjectList = marketPlaceService.getMarketPlaceCustomerRegions(null, getCustomerId());
			} if(marketPlaceCustomerRegionJsonObjectList != null && marketPlaceCustomerRegionJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(marketPlaceCustomerRegionJsonObjectList);
			} else if(isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(20104);
				salesChannelErrorObject.setMessage(getErrorMessage(20104));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Market Place CustomerRegion.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			MarketPlaceCustomerRegionJsonObject obj) {
		Representation representation = null;
		try {
			boolean status = marketPlaceService.insertUpdateMarketPlaceCustomerRegion(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("MarketPlace Customer Info Added Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(20102);
				salesChannelErrorObject.setMessage(getErrorMessage(20102));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Market Place CustomerRegion.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			MarketPlaceCustomerRegionJsonObject obj) {
		Representation representation = null;
		try {
			boolean status = marketPlaceService.insertUpdateMarketPlaceCustomerRegion(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("MarketPlace Customer Info Updated Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(20103);
				salesChannelErrorObject.setMessage(getErrorMessage(20103));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Market Place CustomerRegion.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation deleteDetails() {
		Representation representation = null;
		try {
			boolean isExist = false;
			boolean deleted = false;
			List<MarketPlaceCustomerRegionJsonObject> marketPlaceCustomerRegionJsonObjectList = new ArrayList<MarketPlaceCustomerRegionJsonObject>();
			if(customerRegionId != null && !customerRegionId.isEmpty()) {
				MarketPlaceCustomerRegionJsonObject marketPlaceJsonObject = marketPlaceService.getMarketPlaceCustomerRegionById(customerRegionId);
				if(marketPlaceJsonObject != null) {
					isExist = true;
					deleted = marketPlaceService.deleteMarketPlaceCustomerRegionById(customerRegionId);
				}
			} else if(marketPlaceId != null && !marketPlaceId.isEmpty()){
				marketPlaceCustomerRegionJsonObjectList = marketPlaceService.getMarketPlaceCustomerRegions(marketPlaceId, getCustomerId());
				if(marketPlaceCustomerRegionJsonObjectList != null && marketPlaceCustomerRegionJsonObjectList.size() > 0) {
					isExist = true;
					deleted = marketPlaceService.deleteMarketPlaceCustomerRegions(marketPlaceId, getCustomerId());
				}
			} else if(isAll){
				marketPlaceCustomerRegionJsonObjectList = marketPlaceService.getMarketPlaceCustomerRegions(null, getCustomerId());
				if(marketPlaceCustomerRegionJsonObjectList != null && marketPlaceCustomerRegionJsonObjectList.size() > 0) {
					isExist = true;
					deleted = marketPlaceService.deleteMarketPlaceCustomerRegions(null, getCustomerId());
				}
			} else {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			}
			if(isExist && deleted) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("MarketPlace Customer Info Deleted Successfully.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(20104);
				salesChannelErrorObject.setMessage(getErrorMessage(20104));
			} else if(!deleted){
				salesChannelErrorObject.setStatusCode(20105);
				salesChannelErrorObject.setMessage(getErrorMessage(20105));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while deleteDetails Market Place CustomerRegion.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(MarketPlaceCustomerRegionJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//POST PUT Method
		if (method.equals(SalesChannelConstants.POST) || method.equals(SalesChannelConstants.PUT)) {
			if(obj != null) {
				obj.setCustomerId(getCustomerId());
				MarketPlaceRegionJsonModel marketPlaceRegion = null;
				if(obj.getMarketPlaceRegionId() != null && !obj.getMarketPlaceRegionId().isEmpty()) {
					marketPlaceRegion = marketPlaceService.getMarketPlaceRegionById(obj.getMarketPlaceRegionId());
					if(marketPlaceRegion != null) {
						if(marketPlaceRegion.getMarketPlaceId() != null && !marketPlaceRegion.getMarketPlaceId().isEmpty()) {
							List<MarketPlaceHeadersJsonObject> headersList = marketPlaceService.getMarketPlaceHeaders(marketPlaceRegion.getMarketPlaceId(), null);
							if(headersList != null && headersList.size() > 0) {
								for(MarketPlaceHeadersJsonObject header : headersList) {
									boolean isKeyPresent = false;
									boolean isValuePresent = false;
									for(MarketPlaceHeadersJsonObject headerInput : obj.getHeaders()) {
										if(headerInput != null && headerInput.getHeaderKey() != null 
												&& !headerInput.getHeaderKey().isEmpty() && headerInput.getHeaderKey().equals(header.getHeaderKey())) {
											isKeyPresent = true;
											if(headerInput.getHeaderValue() != null && !headerInput.getHeaderValue().isEmpty()) {
												isValuePresent = true;
												break;
											}
										}
									}
									if(!isKeyPresent) {
										jsonObject2.put("20113", header.getHeaderKey()+" headerKey missing.@#headerKey#@");
										return jsonObject2;
									}
									if(!isValuePresent) {
										jsonObject2.put("20114", header.getHeaderKey()+" headerKey Value missing.@#headerValue#@");
										return jsonObject2;
									}
								}
							}
						}
					} else {
						jsonObject2.put("20112", "MarketPlace RegionId is not Valid.@#marketPlaceRegionId#@");
						return jsonObject2;
					}
				} else {
					jsonObject2.put("20111", "MarketPlace RegionId is empty.@#marketPlaceRegionId#@");
					return jsonObject2;
				}
			}
		}
		//PUT Method
		if (method.equals(SalesChannelConstants.PUT)) {
			if(obj != null) {
				if(obj.getId() != null && !obj.getId().isEmpty()) {
					MarketPlaceCustomerRegionJsonObject customerRegionJsonObject = marketPlaceService.getMarketPlaceCustomerRegionById(obj.getId());
					if(customerRegionJsonObject == null) {
						jsonObject2.put("20116", "MarketPlace Customer Info Id is not valid.@#id#@");
						return jsonObject2;
					}
				} else {
					jsonObject2.put("20115", "MarketPlace Customer Info Id is empty.@#id#@");
					return jsonObject2;
				}
				for(MarketPlaceHeadersJsonObject headerInput : obj.getHeaders()) {
					if(headerInput != null && headerInput.getId() != null 
							&& !headerInput.getId().isEmpty()) {
						MarketPlaceHeadersJsonModel header = marketPlaceService.getMarketPlaceHeaderById(headerInput.getId());
						if(header == null) {
							jsonObject2.put("20118", headerInput.getHeaderKey() + " MarketPlace header Id is not valid.@#id#@");
							return jsonObject2;
						}
					} else {
						jsonObject2.put("20117", "MarketPlace header Id is empty.@#id#@");
						return jsonObject2;
					}
				}
			}
		}
		//GET DELETE Method
		if (method.equals(SalesChannelConstants.GET) || method.equals(SalesChannelConstants.DELETE)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("customerRegionId")) {
							customerRegionId = parameter.getValue();
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
	public MarketPlaceCustomerRegionJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final MarketPlaceCustomerRegionJsonObject marketPlaceRegionJsonObject = mapper.readValue(stream, MarketPlaceCustomerRegionJsonObject.class);
		return marketPlaceRegionJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("customerRegionId");
		paramList.add("marketPlaceId");
		paramList.add("isAll");
		return paramList;
	}

	public MarketPlaceServiceImpl getMarketPlaceService() {
		return marketPlaceService;
	}

	public void setMarketPlaceService(MarketPlaceServiceImpl marketPlaceService) {
		this.marketPlaceService = marketPlaceService;
	}

}
