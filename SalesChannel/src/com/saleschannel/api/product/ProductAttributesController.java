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
import org.restlet.data.Parameter;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import com.saleschannel.api.SalesChannelServerResource;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.utility.SalesChannelUtility;

public class ProductAttributesController extends SalesChannelServerResource<ProductAttributesJsonObject>{

	private static final Logger LOGGERS = Logger.getLogger(ProductAttributesController.class);
	
	private ProductServiceImpl productService;
	
	private String productAttributeId = null;
	
	private String productId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<ProductAttributesJsonObject> productAttributesJsonObjectList = new ArrayList<ProductAttributesJsonObject>();
			if(productId != null && !productId.isEmpty()) {
				isValidReq = true;
				productAttributesJsonObjectList = productService.getProductAttributesByProductId(productId);
			}
			else if(productAttributeId != null && !productAttributeId.isEmpty()) {
				isValidReq = true;
				ProductAttributesJsonObject productAttributesJsonObject = productService.getProductAttributesByProductAttributeId(productAttributeId);
				if(productAttributesJsonObject != null) {
					productAttributesJsonObjectList.add(productAttributesJsonObject);
				}
			}
			if(productAttributesJsonObjectList != null && productAttributesJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productAttributesJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(30104);
				salesChannelErrorObject.setMessage(getErrorMessage(30104));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product Attributes.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ProductAttributesJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			if(productId != null && !productId.isEmpty()) {
				if(obj.getProductAttributes() != null && obj.getProductAttributes().size() > 0) {
					String productAttributeId = null;
					productAttributeId = productService.insertUpdateProductAttributes(obj, productId);
					if(productAttributeId != null) {
						salesChannelErrorObject.setStatusCode(200);
						salesChannelErrorObject.setMessage(getErrorMessage(200));
						salesChannelErrorObject.setData("Product Attribute Added Successfully.");
					} else {
						salesChannelErrorObject.setStatusCode(30100);
						salesChannelErrorObject.setMessage(getErrorMessage(30100));
					}
				} else {
					salesChannelErrorObject.setStatusCode(1005);
					salesChannelErrorObject.setMessage(getErrorMessage(1005));
				}	
			} else {
				salesChannelErrorObject.setStatusCode(30001);
				salesChannelErrorObject.setMessage(getErrorMessage(30001));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Product Attributes.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ProductAttributesJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			if(productId != null && !productId.isEmpty()) {
				if(obj.getProductAttributes() != null && obj.getProductAttributes().size() > 0) {
					String productAttributeId = null;
					productAttributeId = productService.insertUpdateProductAttributes(obj, productId);
					if(productAttributeId != null) {
						salesChannelErrorObject.setStatusCode(200);
						salesChannelErrorObject.setMessage(getErrorMessage(200));
						salesChannelErrorObject.setData("Product Attribute updated.");
					} else {
						salesChannelErrorObject.setStatusCode(30102);
						salesChannelErrorObject.setMessage(getErrorMessage(30102));
					}
				} else {
					salesChannelErrorObject.setStatusCode(1005);
					salesChannelErrorObject.setMessage(getErrorMessage(1005));
				}	
			} else {
				salesChannelErrorObject.setStatusCode(30001);
				salesChannelErrorObject.setMessage(getErrorMessage(30001));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails Product Attributes.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation deleteDetails() {
		Representation representation = null;
		try {
			boolean isExist = false;
			boolean status = false;
			List<ProductAttributesJsonObject> productAttributesJsonObjectList = new ArrayList<ProductAttributesJsonObject>();
			if(productId != null && !productId.isEmpty()) {
				productAttributesJsonObjectList = productService.getProductAttributesByProductId(productId);
				if(productAttributesJsonObjectList != null && productAttributesJsonObjectList.size() > 0) {
					isExist = true;
					status = productService.deleteProductAttributes(productId);
				}
			}
			else if(productAttributeId != null && !productAttributeId.isEmpty()) {
				ProductAttributesJsonModel productAttributesJsonModel = productService.getProductAttributeById(productAttributeId);
				if(productAttributesJsonModel != null) {
					isExist = true;
					status = productService.deleteProductAttributesByProductAttributeId(productAttributeId);
				}
			}
			if(status && isExist) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Product Attribute deleted.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(30103);
				salesChannelErrorObject.setMessage(getErrorMessage(30103));
			} else {
				salesChannelErrorObject.setStatusCode(30104);
				salesChannelErrorObject.setMessage(getErrorMessage(30104));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product Attributes.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(ProductAttributesJsonObject obj,
			String method, JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//GET DELETE PUT POST method
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
		//PUT method
		if (method.equals(SalesChannelConstants.PUT)) {
			if(obj.getProductAttributeId() != null && !obj.getProductAttributeId().isEmpty()) {
				ProductAttributesJsonModel productAttributes = productService.getProductAttributeById(obj.getProductAttributeId());
				if(productAttributes == null) {
					jsonObject2.put("30106", "Product Attribute Id is not valid.@#productAttributeId#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("30105", "Product Attribute Id is empty.@#productAttributeId#@");
				return jsonObject2;
			}
		}
		//PUT POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			if(obj.getProductAttributes() != null && obj.getProductAttributes().size() > 0) {
				for(ProductAttributeSetJsonObject productAttributeSetJsonObject : obj.getProductAttributes()) {
					if(productAttributeSetJsonObject.getName() == null || productAttributeSetJsonObject.getName().isEmpty()) {
						jsonObject2.put("30108", "One of Product Attribute Set Name is empty.@#name#@");
						return jsonObject2;
					}
					if(productAttributeSetJsonObject.getValue() == null || productAttributeSetJsonObject.getValue().isEmpty()) {
						jsonObject2.put("30109", "One of Product Attribute Set Value is empty.@#value#@");
						return jsonObject2;
					}
					if(method.equals(SalesChannelConstants.POST)) {
						productAttributeSetJsonObject.setValueId(null);
					}
				}
			} else {
				jsonObject2.put("30107", "Product Attribute Set is empty.@#productAttributes#@");
				return jsonObject2;
			}
			//skuId validation
			if(method.equals(SalesChannelConstants.POST)) {
				obj.setProductAttributeId(null);
				List<ProductAttributeSetModel> productAttributeSetList = new ArrayList<ProductAttributeSetModel>();
				ProductAttributeSetModel productAttributeSet = new ProductAttributeSetModel();
				productAttributeSet.setProductAttributeSet(obj.getProductAttributes());
				productAttributeSetList.add(productAttributeSet);
				List<ProductAttributesJsonModel> productAttributesList = productService.prepareProductAttributes(productAttributeSetList);
				if(productAttributesList != null && productAttributesList.size() > 0) {
					for(ProductAttributesJsonModel productAttributes : productAttributesList) {
						if(productAttributes.getSkuId() != null && !productAttributes.getSkuId().isEmpty()) {
							ProductAttributesJsonModel productAttribute = productService.checkProductAttributeExist(productId, productAttributes.getSkuId());
							if(productAttribute != null) {
								jsonObject2.put("30108", "Product Attribute Already Exist With this skuId.@#skuId#@");
								return jsonObject2;
							}
						} else {
							jsonObject2.put("30022", "skuId is empty in attribute set.@#skuId#@");
							return jsonObject2;
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
