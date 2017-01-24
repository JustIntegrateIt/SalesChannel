package net.saleschannel.api.marketplace;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.representation.Representation;

import net.saleschannel.api.SalesChannelServerResource;

public class MarketPlaceCustomerRegionController extends SalesChannelServerResource<MarketPlaceRegionJsonObject> {

	@Override
	public Representation fetchDetails() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarketPlaceRegionJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getParametersList() {
		// TODO Auto-generated method stub
		return null;
	}

}
