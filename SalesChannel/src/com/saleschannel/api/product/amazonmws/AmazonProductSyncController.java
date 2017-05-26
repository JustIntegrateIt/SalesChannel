package com.saleschannel.api.product.amazonmws;

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
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import com.saleschannel.api.SalesChannelServerResource;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.marketplace.MarketPlaceRegionJsonModel;
import com.saleschannel.api.marketplace.MarketPlaceServiceImpl;
import com.saleschannel.api.product.MarketPlaceProductSyncJsonObject;

public class AmazonProductSyncController extends SalesChannelServerResource<MarketPlaceProductSyncJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(AmazonProductSyncController.class);
	
	private AmazonProductServiceImpl amazonProductService;
	
	private MarketPlaceServiceImpl marketPlaceService;
	
	@Override
	public Representation fetchDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			MarketPlaceProductSyncJsonObject obj) {
		Representation representation = null;
		boolean status = false;
		try {
			if(obj.getIdType() != null && !obj.getIdType().isEmpty()) {
				status = amazonProductService.getMatchingProductForId(SalesChannelConstants.merchantIdSellerId, 
						obj.getMarketPlaceRegionKey(), obj.getIdType(), obj.getId());	
			} else if(obj.isAll()) {
				List<String> marketplacesIds = new ArrayList<String>();
				marketplacesIds.add(obj.getMarketPlaceRegionKey());
				status = amazonProductService.requestReport(SalesChannelConstants.merchantIdSellerId
						, marketplacesIds, getCustomerId());
			} else {
				status = amazonProductService.getMatchingProduct(SalesChannelConstants.merchantIdSellerId, 
						obj.getMarketPlaceRegionKey(), obj.getId());
			}
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Amazon Product Sync Process Started Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(30300);
				salesChannelErrorObject.setMessage(getErrorMessage(30300));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while AmazonProductSync Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			MarketPlaceProductSyncJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(MarketPlaceProductSyncJsonObject obj,
			String method, JSONObject jsonObject, Form form)
			throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//POST method
		if (method.equals(SalesChannelConstants.POST)) {
			obj.setCustomerId(getCustomerId());
			//marketPlaceRegionKey validation
			if(obj.getMarketPlaceRegionKey() != null && !obj.getMarketPlaceRegionKey().isEmpty()) {
				MarketPlaceRegionJsonModel marketPlaceRegionJsonModel = marketPlaceService.
						getMarketPlaceRegionByRegionId(obj.getMarketPlaceRegionKey());
				if(marketPlaceRegionJsonModel != null) {
					//Id validation
					if(obj.getId() != null && obj.getId().size() > 0) {
						if(obj.getId().size() > 5){
							jsonObject2.put("30313", "Ids should not be greater than 5.@#id#@");
						} else {
							//Id Type validation /*EAN-13 UPC-12 ASIN-10 GCID- GTIN-14 ISBN-10/13*/
							if(obj.getIdType() != null && !obj.getIdType().isEmpty()) {
								if(obj.getIdType().equals("ASIN") || obj.getIdType().equals("ISBN")) {
									for(String asin : obj.getId()) {
										if(asin != null && !asin.isEmpty() && asin.length() != 10) {
											jsonObject2.put("30316", "ASIN/ISBN Id is not valid.@#id#@");
										}
									}
								} else if(obj.getIdType().equals("UPC")) {
									for(String asin : obj.getId()) {
										if(asin != null && !asin.isEmpty() && asin.length() != 12) {
											jsonObject2.put("30317", "UPC Id is not valid.@#id#@");
										}
									}
								} else if(obj.getIdType().equals("EAN") || obj.getIdType().equals("ISBN")) {
									for(String asin : obj.getId()) {
										if(asin != null && !asin.isEmpty() && asin.length() != 13) {
											jsonObject2.put("30318", "EAN/ISBN Id is not valid.@#id#@");
										}
									}
								} else if(obj.getIdType().equals("GTIN")) {
									for(String asin : obj.getId()) {
										if(asin != null && !asin.isEmpty() && asin.length() != 14) {
											jsonObject2.put("30319", "GTIN Id is not valid.@#id#@");
										}
									}
								}
							} else {
								for(String asin : obj.getId()) {
									if(asin != null && !asin.isEmpty() && asin.length() != 10) {
										jsonObject2.put("30314", "ASIN Id is not valid.@#id#@");
									}
								}
							}
						}
					} else {
						if(!obj.isAll()) {
							jsonObject2.put("30315", "Invalid Request Passed.@##@");
						}
					}
				} else {
					jsonObject2.put("30312", "Amazon MarketPlace Region Key is Not Valid.@#marketPlaceRegionKey#@");
				}
			} else {
				jsonObject2.put("30311", "Amazon MarketPlace Region Key is empty.@#marketPlaceRegionKey#@");
			}
			
		}
		return jsonObject2;
	}

	@Override
	public MarketPlaceProductSyncJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final MarketPlaceProductSyncJsonObject marketPlaceProductSyncJsonObject = mapper.readValue(stream, MarketPlaceProductSyncJsonObject.class);
		return marketPlaceProductSyncJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		return paramList;
	}

	public AmazonProductServiceImpl getAmazonProductService() {
		return amazonProductService;
	}

	public void setAmazonProductService(AmazonProductServiceImpl amazonProductService) {
		this.amazonProductService = amazonProductService;
	}

	public MarketPlaceServiceImpl getMarketPlaceService() {
		return marketPlaceService;
	}

	public void setMarketPlaceService(MarketPlaceServiceImpl marketPlaceService) {
		this.marketPlaceService = marketPlaceService;
	}

}
