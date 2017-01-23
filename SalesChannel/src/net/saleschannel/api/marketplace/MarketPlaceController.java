package net.saleschannel.api.marketplace;

import java.io.IOException;
import java.io.InputStream;
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

import net.saleschannel.api.SalesChannelServerResource;

public class MarketPlaceController extends SalesChannelServerResource<MarketPlaceJsonObject>{

	private static final Logger LOGGERS = Logger.getLogger(MarketPlaceController.class);

	private MarketPlaceServiceImpl marketPlaceService;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			List<MarketPlaceJsonObject> marketPlaceJsonObjectList = marketPlaceService.getAllMarketPlaces();
			if(marketPlaceJsonObjectList != null && marketPlaceJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(marketPlaceJsonObjectList);
			} else {
				salesChannelErrorObject.setStatusCode(3000);
				salesChannelErrorObject.setMessage(getErrorMessage(3000));
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	public MarketPlaceServiceImpl getMarketPlaceService() {
		return marketPlaceService;
	}

	public void setMarketPlaceService(MarketPlaceServiceImpl marketPlaceService) {
		this.marketPlaceService = marketPlaceService;
	}

}
