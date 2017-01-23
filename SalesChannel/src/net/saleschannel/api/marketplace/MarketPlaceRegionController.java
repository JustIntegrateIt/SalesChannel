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

public class MarketPlaceRegionController extends SalesChannelServerResource<MarketPlaceRegionJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(MarketPlaceRegionController.class);

	private MarketPlaceServiceImpl marketPlaceService;
	
	private String marketPlaceId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			if(marketPlaceId != null && !marketPlaceId.isEmpty()) {
				List<MarketPlaceJsonObject> marketPlaceJsonObjectList = marketPlaceService.getMarketPlaceRegions(marketPlaceId);
				if(marketPlaceJsonObjectList != null && marketPlaceJsonObjectList.size() > 0) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData(marketPlaceJsonObjectList);
				} else {
					salesChannelErrorObject.setStatusCode(3000);
					salesChannelErrorObject.setMessage(getErrorMessage(3000));
				}
			} else {
				salesChannelErrorObject.setStatusCode(20001);
				salesChannelErrorObject.setMessage(getErrorMessage(200001));
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
			MarketPlaceRegionJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation updateDetails(Representation entity,
			MarketPlaceRegionJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(MarketPlaceRegionJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//GET method
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
					}
				}
			}
		}
		return jsonObject2;
	}

	@Override
	public MarketPlaceRegionJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final MarketPlaceRegionJsonObject marketPlaceRegionJsonObject = mapper.readValue(stream, MarketPlaceRegionJsonObject.class);
		return marketPlaceRegionJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("marketPlaceId");
		return paramList;
	}

}
