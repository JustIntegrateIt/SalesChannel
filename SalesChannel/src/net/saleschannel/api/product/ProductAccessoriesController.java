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

public class ProductAccessoriesController extends SalesChannelServerResource<ProductAccessoriesJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ProductAccessoriesController.class);
	
	private ProductServiceImpl productService;
	
	private String productAccessoriesId = null;
	
	private String productId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			ProductAccessoriesJsonObject productAccessoriesJsonObject = new ProductAccessoriesJsonObject();
			if(productId != null && !productId.isEmpty()) {
				isValidReq = true;
				productAccessoriesJsonObject = productService.getProductAccessoriesByProductId(productId);
			}
			else if(productAccessoriesId != null && !productAccessoriesId.isEmpty()) {
				isValidReq = true;
				List<ProductAttributeSetJsonObject> productAccessories = new ArrayList<ProductAttributeSetJsonObject>();
				ProductAttributeSetJsonObject productAttributeSetJsonObject = productService.getProductAccessoriesByProductAccessoriesId(productAccessoriesId);
				if(productAttributeSetJsonObject != null) {
					productAccessories.add(productAttributeSetJsonObject);
				}
				productAccessoriesJsonObject.setProductAccessories(productAccessories);
			}
			if(productAccessoriesJsonObject != null && productAccessoriesJsonObject.getProductAccessories() != null 
					&& productAccessoriesJsonObject.getProductAccessories().size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productAccessoriesJsonObject);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(30204);
				salesChannelErrorObject.setMessage(getErrorMessage(30204));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product Accessories.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ProductAccessoriesJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			if(productId != null && !productId.isEmpty()) {
				if(obj.getProductAccessories() != null && obj.getProductAccessories().size() > 0) {
					String productAccessoriesId = null;
					for(ProductAttributeSetJsonObject productAttributeSetJsonObject : obj.getProductAccessories()) {
						productAccessoriesId = productService.insertProductAccessories(productAttributeSetJsonObject, productId, obj.getCustomerId());
					}
					if(productAccessoriesId != null) {
						salesChannelErrorObject.setStatusCode(200);
						salesChannelErrorObject.setMessage(getErrorMessage(200));
						salesChannelErrorObject.setData("Product Added Successfully.");
					} else {
						salesChannelErrorObject.setStatusCode(30000);
						salesChannelErrorObject.setMessage(getErrorMessage(30000));
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
			LOGGERS.error("Error occured while insertOrUpdateDetails Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ProductAccessoriesJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			if(productId != null && !productId.isEmpty()) {
				if(obj.getProductAccessories() != null && obj.getProductAccessories().size() > 0) {
					String productAccessoriesId = null;
					for(ProductAttributeSetJsonObject productAttributeSetJsonObject : obj.getProductAccessories()) {
						productAccessoriesId = productService.insertProductAccessories(productAttributeSetJsonObject, productId, obj.getCustomerId());
					}
					if(productAccessoriesId != null) {
						salesChannelErrorObject.setStatusCode(200);
						salesChannelErrorObject.setMessage(getErrorMessage(200));
						salesChannelErrorObject.setData("Product Accessories Updated Successfully.");
					} else {
						salesChannelErrorObject.setStatusCode(30202);
						salesChannelErrorObject.setMessage(getErrorMessage(30202));
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
			LOGGERS.error("Error occured while insertOrUpdateDetails Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation deleteDetails() {
		Representation representation = null;
		try {
			boolean status = false;
			boolean isExist = false;
			ProductAccessoriesJsonObject productAccessoriesJsonObject = new ProductAccessoriesJsonObject();
			if(productId != null && !productId.isEmpty()) {
				productAccessoriesJsonObject = productService.getProductAccessoriesByProductId(productId);
				if(productAccessoriesJsonObject != null) {
					isExist = true;
					status = productService.deleteProductsAccessoriesByProductId(productId);
				}
			}
			else if(productAccessoriesId != null && !productAccessoriesId.isEmpty()) {
				ProductAttributeSetJsonObject productAttributeSetJsonObject = productService.getProductAccessoriesByProductAccessoriesId(productAccessoriesId);
				if(productAttributeSetJsonObject != null) {
					isExist = true;
					status = productService.deleteProductsAccessoriesByProductAccessoriesId(productAccessoriesId);
				}
			}
			if(status && isExist) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Product Accessories deleted.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(30203);
				salesChannelErrorObject.setMessage(getErrorMessage(30203));
			} else {
				salesChannelErrorObject.setStatusCode(30204);
				salesChannelErrorObject.setMessage(getErrorMessage(30204));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product Accessories.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(ProductAccessoriesJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//GET DELETE PUT POST method
		if (!form.isEmpty()) {
			for (final Parameter parameter : form) {
				jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
				if (jsonObject.length() != 0) {
					return jsonObject2;
				} else {
					if (parameter.getName().equalsIgnoreCase("productAccessoriesId")) {
						productAccessoriesId = parameter.getValue();
					}
					else if (parameter.getName().equalsIgnoreCase("productId")) {
						productId = parameter.getValue();
					}
				}
			}
		}
		//POST method
		if (method.equals(SalesChannelConstants.PUT)) {
			if(obj.getProductAccessories() != null && obj.getProductAccessories().size() > 0) {
				for(ProductAttributeSetJsonObject productAttributeSetJsonObject : obj.getProductAccessories()) {
					if(productAttributeSetJsonObject.getValueId() != null && !productAttributeSetJsonObject.getValueId().isEmpty()) {
						if(productAttributeSetJsonObject.getName() != null && !productAttributeSetJsonObject.getName().isEmpty() 
								&& productAttributeSetJsonObject.getName().contains("image")) {
							ProductImageJsonModel productImageJsonModel = productService.getProductImageById(productAttributeSetJsonObject.getValueId());
							if(productImageJsonModel == null) {
								jsonObject2.put("30207", "Product Accessories Image Id is not valid.@#valueId#@");
								return jsonObject2;
							}
						} else {
							ProductAttributeSetJsonObject productAccessories = productService.getProductAccessoriesByProductAccessoriesId(productAttributeSetJsonObject.getValueId());
							if(productAccessories == null) {
								jsonObject2.put("30206", "Product Accessories Id is not valid.@#valueId#@");
								return jsonObject2;
							}
						}
					} else {
						jsonObject2.put("30205", "Product Accessories Id is empty.@#valueId#@");
						return jsonObject2;
					}
				}
			}
		}
		//PUT POST method
		if (method.equals(SalesChannelConstants.POST) || method.equals(SalesChannelConstants.PUT)) {
			if(obj.getProductAccessories() != null && obj.getProductAccessories().size() > 0) {
				for(ProductAttributeSetJsonObject productAttributeSetJsonObject : obj.getProductAccessories()) {
					if(productAttributeSetJsonObject.getName() == null || productAttributeSetJsonObject.getName().isEmpty()) {
						jsonObject2.put("30207", "Product Accessories Name is empty.@#name#@");
						return jsonObject2;
					}
					if(productAttributeSetJsonObject.getValue() == null || productAttributeSetJsonObject.getValue().isEmpty()) {
						jsonObject2.put("30208", "Product Accessories value is empty.@#value#@");
						return jsonObject2;
					}
					if(method.equals(SalesChannelConstants.POST)) {
						productAttributeSetJsonObject.setValueId(null);
					}
				}
			} else {
				jsonObject2.put("30209", "Product Accessories is empty.@#productAccessories#@");
				return jsonObject2;
			}
		}
		return jsonObject2;
	}

	@Override
	public ProductAccessoriesJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException, JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductAccessoriesJsonObject productAccessoriesJsonObject = mapper.readValue(stream, ProductAccessoriesJsonObject.class);
		return productAccessoriesJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("productAccessoriesId");
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
