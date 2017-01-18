package net.saleschannel.api.productcategory;

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

public class ProductCategoryController extends SalesChannelServerResource<ProductCategoryJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryController.class);

	private ProductCategoryServiceImpl categoryService;
	
	private String productCategoryId = null;
	
	private String categoryName = null;
	
	private String marketPlaceId = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			List<ProductCategoryJsonObject> productCategoryJsonObjectList = new ArrayList<ProductCategoryJsonObject>();
			if(productCategoryId != null && !productCategoryId.isEmpty()) {
				ProductCategoryJsonObject productCategoryJsonObject = categoryService.getProductCategoryById(productCategoryId, getCustomerId());
				productCategoryJsonObjectList.add(productCategoryJsonObject);
			} else if(categoryName != null && !categoryName.isEmpty()) {
				ProductCategoryJsonObject productCategoryJsonObject = categoryService.getProductCategoryByNameAndCustomerId(
						getCustomerId(), categoryName);
				productCategoryJsonObjectList.add(productCategoryJsonObject);
			} else if(marketPlaceId != null && !marketPlaceId.isEmpty()) {
				productCategoryJsonObjectList = categoryService.getProductCategoryByMarketPlaceId(marketPlaceId);
			} else if(isAll) {
				productCategoryJsonObjectList = categoryService.getProductCategoryByCustomerId(getCustomerId());
			}
			if(productCategoryJsonObjectList != null && productCategoryJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productCategoryJsonObjectList);
			} else {
				salesChannelErrorObject.setStatusCode(3000);
				salesChannelErrorObject.setMessage(getErrorMessage(3000));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Category.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ProductCategoryJsonObject obj) {
		Representation representation = null;
		try {
			String categoryId = categoryService.insertProductCategory(obj);
			if(categoryId != null) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Category Inserted Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(4000);
				salesChannelErrorObject.setMessage(getErrorMessage(4000));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Category.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ProductCategoryJsonObject obj) {
		Representation representation = null;
		try {
			boolean status = categoryService.updateProductCategory(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Category Updated Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(4000);
				salesChannelErrorObject.setMessage(getErrorMessage(4000));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while UpdateDetails Category.");
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
			ProductCategoryJsonObject productCategoryJsonObject = new ProductCategoryJsonObject();
			if(productCategoryId != null && !productCategoryId.isEmpty()) {
				productCategoryJsonObject = categoryService.getProductCategoryById(productCategoryId, getCustomerId());
				if(productCategoryJsonObject != null) {
					isExist = true;
					status = categoryService.deleteProductCategoryById(productCategoryId, getCustomerId());
				}
			} else if(categoryName != null && !categoryName.isEmpty()) {
				productCategoryJsonObject = categoryService.getProductCategoryByNameAndCustomerId(getCustomerId(), categoryName);
				if(productCategoryJsonObject != null) {
					isExist = true;
					status = categoryService.deleteProductCategoryByNameAndCustomerId(
							getCustomerId(), categoryName);
				}
			} else if(isAll) {
				List<ProductCategoryJsonObject> productCategoryJsonObjectList = categoryService.getProductCategoryByCustomerId(getCustomerId()); 
				if(productCategoryJsonObjectList != null && productCategoryJsonObjectList.size() > 0) {
					isExist = true;
					status = categoryService.deleteProductCategoryByCustomerId(getCustomerId());
				}
			}
			if(isExist && status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Product Category deleted Successfully.");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(4002);
				salesChannelErrorObject.setMessage(getErrorMessage(4002));
			} else {
				salesChannelErrorObject.setStatusCode(4003);
				salesChannelErrorObject.setMessage(getErrorMessage(4003));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while delete Category.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(ProductCategoryJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			//CategoryName validation
			if(obj.getCategoryName() == null || obj.getCategoryName().isEmpty()) {
				jsonObject2.put("1000", "category Name is empty.@#categoryName#@");
				return jsonObject2;
			}
			//parent Category validation
			if(obj.getParentId() != null && !obj.getParentId().isEmpty()) {
				ProductCategoryJsonObject categoryJsonObject = categoryService.getProductCategoryById(obj.getParentId(), getCustomerId());
				if(categoryJsonObject == null || categoryJsonObject.getId() == null) {
					jsonObject2.put("1004", "Invalid value passed.@#parentId#@");
					return jsonObject2;
				}
			}
		}
		//PUT method
		if (method.equals(SalesChannelConstants.PUT)) {
			//CategoryName validation
			if(obj.getCategoryName() != null && !obj.getCategoryName().isEmpty()) {
				ProductCategoryJsonModel categoryJsonModel = categoryService.isProductCategoryExist(obj);
				if(categoryJsonModel != null && categoryJsonModel.getId() != null) {
					jsonObject2.put("4001", "Database Error.Record already exist.@#categoryName#@");
					return jsonObject2;
				}
			}
		}
		//GET method
		else if (method.equals(SalesChannelConstants.GET) || method.equals(SalesChannelConstants.DELETE)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("categoryId")) {
							productCategoryId = parameter.getValue();
						}
						else if (parameter.getName().equalsIgnoreCase("categoryName")) {
							categoryName = parameter.getValue();
						}
						else if (parameter.getName().equalsIgnoreCase("marketPlaceId")) {
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
	public ProductCategoryJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductCategoryJsonObject categoryJsonModel = mapper.readValue(stream, ProductCategoryJsonObject.class);
		return categoryJsonModel;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("categoryId");
		paramList.add("categoryName");
		paramList.add("marketPlaceId");
		paramList.add("isAll");
		return paramList;
	}

	public ProductCategoryServiceImpl getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ProductCategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}

}
