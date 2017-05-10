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
import com.saleschannel.api.constants.CategoryColumnFieldType;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.utility.SalesChannelBeanLocator;
import com.saleschannel.api.utility.SalesChannelUtility;

public class CategoryColumnsValueController extends SalesChannelServerResource<CategoryColumnsValueJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(CategoryColumnsValueController.class);

	private ProductCategoryServiceImpl categoryService;
	
	private String productId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<ProductCategoryColumnValueJsonObject> productCategoryColumnValueJsonObjectList = null;
			if(productId != null && !productId.isEmpty()) {
				isValidReq = true;
				productCategoryColumnValueJsonObjectList = categoryService.getProductCategoryColumnValuesByProductId(productId);
			}
			if(productCategoryColumnValueJsonObjectList != null && productCategoryColumnValueJsonObjectList.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productCategoryColumnValueJsonObjectList);
			} else if(!isValidReq) {
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(40304);
				salesChannelErrorObject.setMessage(getErrorMessage(40304));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetchDetails Category Column Values.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			CategoryColumnsValueJsonObject obj) {
		try {
			obj.setCustomerId(getCustomerId());
			categoryService.insertUpdateProductCategoryColumnValues(obj);
			salesChannelErrorObject.setStatusCode(200);
			salesChannelErrorObject.setMessage(getErrorMessage(200));
			salesChannelErrorObject.setData("Category Column Values Inserted Successfully.");
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Category Column Values.");
			e.printStackTrace();
			salesChannelErrorObject.setStatusCode(40301);
			salesChannelErrorObject.setMessage(getErrorMessage(40301));
		}
		return new JsonRepresentation(salesChannelErrorObject);
	}

	@Override
	public Representation updateDetails(Representation entity,
			CategoryColumnsValueJsonObject obj) {
		try {
			obj.setCustomerId(getCustomerId());
			categoryService.insertUpdateProductCategoryColumnValues(obj);
			salesChannelErrorObject.setStatusCode(200);
			salesChannelErrorObject.setMessage(getErrorMessage(200));
			salesChannelErrorObject.setData("Category Column Values Updated Successfully.");
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails Category Column Values.");
			e.printStackTrace();
			salesChannelErrorObject.setStatusCode(40302);
			salesChannelErrorObject.setMessage(getErrorMessage(40302));
		}
		return new JsonRepresentation(salesChannelErrorObject);
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(CategoryColumnsValueJsonObject obj,
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
						if (parameter.getName().equalsIgnoreCase("productId")) {
							productId = parameter.getValue();
						}
					}
				}
			}
		} else if (method.equals(SalesChannelConstants.POST) || method.equals(SalesChannelConstants.PUT)) {
			for(ProductCategoryColumnValueJsonObject columnValue : obj.getCategoryColumnsValue()) {
				if(columnValue.getId() != null && !columnValue.getId().isEmpty()) {
					ProductCategoryColumnValueJsonObject value = categoryService.getProductCategoryColumnValueById(columnValue.getId());
					if(value == null) {
						jsonObject2.put(columnValue.getId(), "Invalid Id");
					}
				}
			}
			List<CategoryColumnsValueErrorJsonObject> columnsValueError = validateColumnParametersValue(obj);
			if(columnsValueError != null && columnsValueError.size() > 0) {
				jsonObject2.put("40311", new JSONObject(columnsValueError));
			}
		} 
		return jsonObject2;
	}
	
	/*method to validate whether all the values are valid*/
	private static List<CategoryColumnsValueErrorJsonObject> validateColumnParametersValue(CategoryColumnsValueJsonObject categoryColumnsValueJsonObject) {
		List<CategoryColumnsValueErrorJsonObject> categoryColumnsValueErrors = new ArrayList<CategoryColumnsValueErrorJsonObject>();
		CategoryColumnsValueErrorJsonObject categoryColumnsValueError = null;
		try {
			if(categoryColumnsValueJsonObject != null && categoryColumnsValueJsonObject.getCategoryColumnsValue() != null 
					&& categoryColumnsValueJsonObject.getCategoryColumnsValue().size() > 0) {
				ProductCategoryServiceImpl categoryService = (ProductCategoryServiceImpl) SalesChannelBeanLocator.getInstance().findBean("categoryService");
				ProductCategoryJsonObject productCategoryJsonObject = categoryService.getProductCategoryByProductId(categoryColumnsValueJsonObject.getCategoryColumnsValue().get(0).getProductId());
				if(productCategoryJsonObject != null) {
					List<ProductCategoryColumnParametersJsonObject> productCategoryColumnParametersList = categoryService.
							getProductCategoryColumnParametersByCategoryId(productCategoryJsonObject.getId());
					if(productCategoryColumnParametersList != null && productCategoryColumnParametersList.size() > 0) {
						for(ProductCategoryColumnValueJsonObject columnValue : categoryColumnsValueJsonObject.getCategoryColumnsValue()) {
							boolean isExist = false;
							for(ProductCategoryColumnParametersJsonObject columnParameter : productCategoryColumnParametersList) {
								if(columnParameter != null && columnParameter.getId() != null && !columnParameter.getId().isEmpty()	&& columnValue != null 
										&& columnValue.getCategoryColumnParameterId() != null && !columnValue.getCategoryColumnParameterId().isEmpty() 
										&& columnValue.getCategoryColumnParameterId().equals(columnParameter.getId())) {
									isExist = true;
									if(columnParameter.getFieldType() != null && !columnParameter.getFieldType().isEmpty()) {
										if(columnParameter.getFieldType().equals(CategoryColumnFieldType.string)) {
											if(columnValue.getValue().length() > columnParameter.getMaxLength()) {
												categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
												categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
												categoryColumnsValueError.setError("Value should not be > "+columnParameter.getMaxLength());
												categoryColumnsValueErrors.add(categoryColumnsValueError);
											} else if(!SalesChannelUtility.isValidString(columnValue.getValue())) {
												categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
												categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
												categoryColumnsValueError.setError("Value should not contains numbers or special characters");
												categoryColumnsValueErrors.add(categoryColumnsValueError);
											}
										} else if(columnParameter.getFieldType().equals(CategoryColumnFieldType.alphanumeric)) {
											if(columnValue.getValue().length() > columnParameter.getMaxLength()) {
												categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
												categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
												categoryColumnsValueError.setError("Value should not be > "+columnParameter.getMaxLength());
												categoryColumnsValueErrors.add(categoryColumnsValueError);
											} else if(!SalesChannelUtility.isValidString(columnValue.getValue())) {
												categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
												categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
												categoryColumnsValueError.setError("Value should not contains special characters");
												categoryColumnsValueErrors.add(categoryColumnsValueError);
											}
										} else if(columnParameter.getFieldType().equals(CategoryColumnFieldType.number)) {
											if(columnParameter.isDecimal()) {
												if(!SalesChannelUtility.isValidDecimal(columnValue.getValue())) {
													categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
													categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
													categoryColumnsValueError.setError("Value should contains special characters");
													categoryColumnsValueErrors.add(categoryColumnsValueError);
												} else if(!SalesChannelUtility.isValidNumber(columnValue.getValue())) {
													categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
													categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
													categoryColumnsValueError.setError("Value should be a whole number");
													categoryColumnsValueErrors.add(categoryColumnsValueError);
												} else if(columnValue.getValue().contains(".")) {
													String[] token = columnValue.getValue().split(".");
													if(token.length > 0 && token[0] != null && !token[0].isEmpty() && token[1] != null && !token[1].isEmpty()) {
														if(token[0].length() > columnParameter.getBefore()) {
															categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
															categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
															categoryColumnsValueError.setError("Value should not contains more than "+ columnParameter.getBefore() +" digits");
															categoryColumnsValueErrors.add(categoryColumnsValueError);
														} else if(token[1].length() > columnParameter.getAfter()) {
															categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
															categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
															categoryColumnsValueError.setError("Value should not contains more than "+ columnParameter.getAfter() +" digits");
															categoryColumnsValueErrors.add(categoryColumnsValueError);
														}
													} else {
														categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
														categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
														categoryColumnsValueError.setError("Value is not valid");
														categoryColumnsValueErrors.add(categoryColumnsValueError);
													}
												} else {
													categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
													categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
													categoryColumnsValueError.setError("Value is not valid");
													categoryColumnsValueErrors.add(categoryColumnsValueError);
												}
											} else {
												if(!SalesChannelUtility.isValidNumber(columnValue.getValue())) {
													categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
													categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
													categoryColumnsValueError.setError("Value should be a whole number");
													categoryColumnsValueErrors.add(categoryColumnsValueError);
												} else if(columnValue.getValue().length() <= 10) {
													categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
													categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
													categoryColumnsValueError.setError("Value should not contains more than 10 digits");
													categoryColumnsValueErrors.add(categoryColumnsValueError);
												}
											}
										} else {
											categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
											categoryColumnsValueError.setColumnName(columnParameter.getColumnName());
											categoryColumnsValueError.setError("Invalid Field Type");
											categoryColumnsValueErrors.add(categoryColumnsValueError);
										}
									} else if(columnParameter.getValidValues() != null && columnParameter.getValidValues().size() > 0) {
										
									}
								}
							}
							if(!isExist) {
								categoryColumnsValueError = new CategoryColumnsValueErrorJsonObject();
								categoryColumnsValueError.setColumnName(columnValue.getCategoryColumnParameterId());
								categoryColumnsValueError.setError("Invalid Column");
								categoryColumnsValueErrors.add(categoryColumnsValueError);	
							}
						}
					}
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return categoryColumnsValueErrors;
    }

	@Override
	public CategoryColumnsValueJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final CategoryColumnsValueJsonObject categoryColumnsValueJsonObject = mapper.readValue(stream
				, CategoryColumnsValueJsonObject.class);
		return categoryColumnsValueJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("productId");
		return paramList;
	}

	public ProductCategoryServiceImpl getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ProductCategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}

}
