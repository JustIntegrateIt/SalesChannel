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

public class MarketPlaceController extends SalesChannelServerResource<MarketPlaceJsonObject>{

	private static final Logger LOGGERS = Logger.getLogger(MarketPlaceController.class);

	private MarketPlaceServiceImpl marketPlaceService;
	
	private String marketPlaceId = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<MarketPlaceJsonObject> marketPlaceJsonObjectList = new ArrayList<MarketPlaceJsonObject>();
			if(marketPlaceId != null && !marketPlaceId.isEmpty()) {
				isValidReq = true;
				MarketPlaceJsonObject marketPlaceJsonObject = marketPlaceService.getMarketPlaceById(marketPlaceId, null);
				if(marketPlaceJsonObject != null)
					marketPlaceJsonObjectList.add(marketPlaceJsonObject);
			} else if(isAll){
				isValidReq = true;
				marketPlaceJsonObjectList = marketPlaceService.getAllMarketPlaces(null);
			}
			if(marketPlaceJsonObjectList != null && marketPlaceJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(marketPlaceJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(20002);
				salesChannelErrorObject.setMessage(getErrorMessage(20002));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Market Place.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			MarketPlaceJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation updateDetails(Representation entity,
			MarketPlaceJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(MarketPlaceJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		if (method.equals(SalesChannelConstants.GET)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("marketPlaceId")) {
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
	public MarketPlaceJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final MarketPlaceJsonObject marketPlaceJsonObject = mapper.readValue(stream, MarketPlaceJsonObject.class);
		return marketPlaceJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
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
