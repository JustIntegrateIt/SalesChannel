package net.saleschannel.api.product;

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

public class ProductAttributesController extends SalesChannelServerResource<ProductAttributesJsonObject>{

	private static final Logger LOGGERS = Logger.getLogger(ProductAttributesController.class);
	
	private ProductServiceImpl productService;
	
	private String productAttributeId = null;
	
	private String productId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			List<ProductAttributesJsonObject> productAttributesJsonObjectList = new ArrayList<ProductAttributesJsonObject>();
			if(productId != null && !productId.isEmpty()) {
				productAttributesJsonObjectList = productService.getProductAttributesByProductId(productId);
			}
			else if(productAttributeId != null && !productAttributeId.isEmpty()) {
				ProductAttributesJsonObject productAttributesJsonObject = productService.getProductAttributesByProductAttributeId(productAttributeId);
				if(productAttributesJsonObject != null) {
					productAttributesJsonObjectList.add(productAttributesJsonObject);
				}
			}
			if(productAttributesJsonObjectList != null && productAttributesJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productAttributesJsonObjectList);
			} else {
				salesChannelErrorObject.setStatusCode(30104);
				salesChannelErrorObject.setMessage(getErrorMessage(30104));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ProductAttributesJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ProductAttributesJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(ProductAttributesJsonObject obj,
			String method, JSONObject jsonObject, Form form)
			throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//GET DELETE method
		if (method.equals(SalesChannelConstants.GET) || method.equals(SalesChannelConstants.DELETE)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("productAttributeId")) {
							productAttributeId = parameter.getValue();
						}
						else if (parameter.getName().equalsIgnoreCase("productId")) {
							productId = parameter.getValue();
						}
					}
				}
			}
		}
		return jsonObject2;
	}

	@Override
	public ProductAttributesJsonObject getJsonObject(
			InputStream stream) throws JsonParseException,
			JsonMappingException, IOException, JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductAttributesJsonObject productAttributesAccessoriesJsonObject = mapper.readValue(stream, ProductAttributesJsonObject.class);
		return productAttributesAccessoriesJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("productAttributeId");
		paramList.add("productId");
		return paramList;
	}

	public ProductServiceImpl getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}

}
