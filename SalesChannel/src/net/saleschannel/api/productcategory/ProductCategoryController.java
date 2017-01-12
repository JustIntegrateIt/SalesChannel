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

public class ProductCategoryController extends SalesChannelServerResource<ProductCategoryJsonModel>{

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryController.class);

	private ProductCategoryServiceImpl categoryService;
	
	private String productCategoryId = null;
	
	private String categoryName = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			List<ProductCategoryJsonModel> productCategoryJsonModelList = new ArrayList<ProductCategoryJsonModel>();
			if(productCategoryId != null && !productCategoryId.isEmpty()) {
				ProductCategoryJsonModel productCategoryJsonModel = categoryService.getProductCategoryById(productCategoryId);
				productCategoryJsonModelList.add(productCategoryJsonModel);
			} else if(categoryName != null && !categoryName.isEmpty()) {
				ProductCategoryJsonModel productCategoryJsonModel = categoryService.getProductCategoryByNameAndCustomerId(
						getCustomerId(), categoryName);
				productCategoryJsonModelList.add(productCategoryJsonModel);
			} else if(isAll) {
				productCategoryJsonModelList = categoryService.getProductCategoryByCustomerId(getCustomerId());
			}
			if(productCategoryJsonModelList != null && productCategoryJsonModelList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productCategoryJsonModelList);
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
			ProductCategoryJsonModel obj) {
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
			ProductCategoryJsonModel obj) {
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
			ProductCategoryJsonModel productCategoryJsonModel = new ProductCategoryJsonModel();
			if(productCategoryId != null && !productCategoryId.isEmpty()) {
				productCategoryJsonModel = categoryService.getProductCategoryById(productCategoryId);
				if(productCategoryJsonModel != null) {
					isExist = true;
					status = categoryService.deleteProductCategoryById(productCategoryId);
				}
			} else if(categoryName != null && !categoryName.isEmpty()) {
				productCategoryJsonModel = categoryService.getProductCategoryByNameAndCustomerId(getCustomerId(), categoryName);
				if(productCategoryJsonModel != null) {
					isExist = true;
					status = categoryService.deleteProductCategoryByNameAndCustomerId(
							getCustomerId(), categoryName);
				}
			} else if(isAll) {
				List<ProductCategoryJsonModel> productCategoryJsonModelList = categoryService.getProductCategoryByCustomerId(getCustomerId()); 
				if(productCategoryJsonModelList != null && productCategoryJsonModelList.size() > 0) {
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
	public JSONObject validate(ProductCategoryJsonModel obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			obj.setCustomerId(getCustomerId());
			//CategoryName validation
			if(obj.getCategoryName() == null || obj.getCategoryName().isEmpty()) {
				jsonObject2.put("1000", "category Name is empty.@#categoryName#@");
				return jsonObject2;
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
	public ProductCategoryJsonModel getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductCategoryJsonModel categoryJsonModel = mapper.readValue(stream, ProductCategoryJsonModel.class);
		return categoryJsonModel;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("categoryId");
		paramList.add("categoryName");
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
