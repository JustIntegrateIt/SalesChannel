package net.saleschannel.api.productcategory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.representation.Representation;

import net.saleschannel.api.SalesChannelServerResource;

public class ProductCategoryMappingController extends SalesChannelServerResource<ProductCategoryMappingJsonObject> {

	@Override
	public Representation fetchDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ProductCategoryMappingJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ProductCategoryMappingJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(ProductCategoryMappingJsonObject obj,
			String method, JSONObject jsonObject, Form form)
			throws JSONException {
		// TODO Auto-generated method stub
		return null;
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
		paramList.add("isAll");
		return paramList;
	}

}
