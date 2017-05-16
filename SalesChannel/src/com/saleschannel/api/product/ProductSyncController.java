package com.saleschannel.api.product;

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
import org.restlet.representation.Representation;

import com.saleschannel.api.SalesChannelServerResource;

public class ProductSyncController extends SalesChannelServerResource<ProductSyncJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ProductSyncController.class);
	
	private ProductServiceImpl productService;
	
	private String skuId = null;
	
	private String productId = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ProductSyncJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ProductSyncJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(ProductSyncJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductSyncJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductSyncJsonObject productSyncJsonObject = mapper.readValue(stream, ProductSyncJsonObject.class);
		return productSyncJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("category");
		paramList.add("product");
		paramList.add("isAll");
		return paramList;
	}

	public ProductServiceImpl getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}

}
