package com.saleschannel.api.productcategory;

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

public class ProductCategoryColumnController extends SalesChannelServerResource<ProductCategoryColumnParametersJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryColumnController.class);

	private ProductCategoryServiceImpl categoryService;
	
	private String productCategoryId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<ProductCategoryColumnParametersJsonObject> productCategoryColumnParametersJsonObjectList = new ArrayList<ProductCategoryColumnParametersJsonObject>();
			if(productCategoryId != null && !productCategoryId.isEmpty()) {
				isValidReq = true;
				productCategoryColumnParametersJsonObjectList = categoryService.getProductCategoryColumnParametersByCategoryId(productCategoryId);
			}
			if(productCategoryColumnParametersJsonObjectList != null && productCategoryColumnParametersJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productCategoryColumnParametersJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(40104);
				salesChannelErrorObject.setMessage(getErrorMessage(40104));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Category Column Parameters.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ProductCategoryColumnParametersJsonObject obj) {
		try {
			obj.setCustomerId(getCustomerId());
			getCategoryService().insertProductCategoryColumnParameter(obj);
			salesChannelErrorObject.setStatusCode(200);
			salesChannelErrorObject.setMessage(getErrorMessage(200));
			salesChannelErrorObject.setData("Category Column Inserted Successfully.");
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Category Column Parameter.");
			e.printStackTrace();
			salesChannelErrorObject.setStatusCode(40101);
			salesChannelErrorObject.setMessage(getErrorMessage(40101));
		}
		return new JsonRepresentation(salesChannelErrorObject);
	}

	@Override
	public Representation updateDetails(Representation entity,
			ProductCategoryColumnParametersJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(ProductCategoryColumnParametersJsonObject obj,
			String method, JSONObject jsonObject, Form form)
			throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//GET method
		if (method.equals(SalesChannelConstants.GET)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("categoryId")) {
							productCategoryId = parameter.getValue();
						}
					}
				}
			}
		}
		return jsonObject2;
	}

	@Override
	public ProductCategoryColumnParametersJsonObject getJsonObject(
			InputStream stream) throws JsonParseException,
			JsonMappingException, IOException, JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductCategoryColumnParametersJsonObject categoryJsonModel = mapper.readValue(stream
				, ProductCategoryColumnParametersJsonObject.class);
		return categoryJsonModel;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("categoryId");
		return paramList;
	}

	public ProductCategoryServiceImpl getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ProductCategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}

}
