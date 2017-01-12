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
import net.saleschannel.api.constants.ProductTypes;
import net.saleschannel.api.constants.SalesChannelConstants;
import net.saleschannel.api.utility.SalesChannelUtility;

public class ProductController extends SalesChannelServerResource<ProductJsonModel> {

	private static final Logger LOGGERS = Logger.getLogger(ProductController.class);
	
	private ProductServiceImpl productService;
	
	private String skuId = null;
	
	private String productId = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			List<ProductJsonModel> productJsonModels = new ArrayList<ProductJsonModel>();
			ProductJsonModel productJsonModel = new ProductJsonModel();
			if(skuId != null) {
				productJsonModel = productService.checkProductExist(skuId, getCustomerId());
				productJsonModels.add(productJsonModel);
			}
			else if(productId != null) {
				productJsonModel = productService.getProductById(productId);
				productJsonModels.add(productJsonModel);
			}
			else if(isAll) {
				productJsonModels = productService.getProductsByCustomer(getCustomerId());
			}
			if(productJsonModels != null && productJsonModels.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productJsonModels);
			} else {
				salesChannelErrorObject.setStatusCode(3000);
				salesChannelErrorObject.setMessage(getErrorMessage(3000));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,	ProductJsonModel obj) {
		Representation representation = null;
		try {
			String productId = productService.insertProduct(obj);
			if(productId != null) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Product Added Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(4000);
				salesChannelErrorObject.setMessage(getErrorMessage(4000));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,	ProductJsonModel obj) {
		Representation representation = null;
		try {
			boolean status = productService.updateProduct(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Product Updated Successfully.");
			} else {
				salesChannelErrorObject.setStatusCode(4000);
				salesChannelErrorObject.setMessage(getErrorMessage(4000));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while UpdateDetails Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation deleteDetails() {
		Representation representation = null;
		boolean deleted = false;
		boolean isExist = false;
		try {
			ProductJsonModel productJsonModel = new ProductJsonModel();
			if(skuId != null) {
				productJsonModel = productService.checkProductExist(skuId, getCustomerId());
				if(productJsonModel != null) {
					isExist = true;
					deleted = productService.deleteProduct(productJsonModel);
				}
			}
			else if(productId != null) {
				productJsonModel = productService.getProductById(productId);
				if(productJsonModel != null) {
					isExist = true;
					deleted = productService.deleteProduct(productJsonModel);
				}
			} else if(isAll) {
				List<ProductJsonModel> productJsonModelList = productService.getProductsByCustomer(getCustomerId());
				if(productJsonModelList != null && productJsonModelList.size() > 0) {
					isExist = true;
					deleted = productService.deleteProducts(getCustomerId());
				}
			}
			if(isExist && deleted) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Product deleted successfully");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(4002);
				salesChannelErrorObject.setMessage(getErrorMessage(4002));
			} else {
				salesChannelErrorObject.setStatusCode(4003);
				salesChannelErrorObject.setMessage(getErrorMessage(4003));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(ProductJsonModel obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			obj.setCustomerId(getCustomerId());
			//productName validation
			if(obj.getProductName() == null || obj.getProductName().isEmpty()) {
				jsonObject2.put("1000", "Product Name is empty.@#productName#@");
				return jsonObject2;
			}
			//productType validation
			if(obj.getProductType() == null || obj.getProductType().isEmpty()) {
				jsonObject2.put("1000", "Product Type is empty.@#productType#@");
				return jsonObject2;
			} else {
				ProductTypes productType = ProductTypes.getByName(obj.getProductType());
				if(productType == null) {
					jsonObject2.put("1004", "Product Type value is not valid. Please send any of these "
				+ProductTypes.Simple+", "+ProductTypes.Configurable+", "+ProductTypes.Virtual+", "
				+ProductTypes.Downloadable+", "+ProductTypes.Grouped+", "+ProductTypes.Bundled
				+".@#productType#@");
					return jsonObject2;
				} else {
					if(productType.equals(ProductTypes.Simple)) {
						//cost validation
						if(obj.getCost() == null || obj.getCost().equals("")) {
							jsonObject2.put("1000", "Product Cost is empty.@#cost#@");
							return jsonObject2;
						} else {
							try {
								obj.setCost(obj.getCost().intValue());
							} catch(Exception e) {
								LOGGERS.error(e.getStackTrace());
								jsonObject2.put("1002", "Product Cost is not valid.@#cost#@");
								return jsonObject2;
							}
						}
						//quantity validation
						if(obj.getQuantity() == null || obj.getQuantity().equals("")) {
							jsonObject2.put("1000", "Product Quantity is empty.@#quantity#@");
							return jsonObject2;
						} else {
							try {
								obj.setQuantity(obj.getQuantity().intValue());
							} catch(Exception e) {
								LOGGERS.error(e.getStackTrace());
								jsonObject2.put("1002", "Product Quantity is not valid.@#quantity#@");
								return jsonObject2;
							}
						}
					} else if(productType.equals(ProductTypes.Configurable)) {
						//productAttributes validation
						if(obj.getProductAttributes() == null || obj.getProductAttributes().equals("")) {
							jsonObject2.put("1000", "Product Attributes is empty.@#productAttributes#@");
							return jsonObject2;
						}
					}
				}
			}
			//productCategory validation
			if(obj.getProductCategory() == null || obj.getProductCategory().isEmpty()) {
				jsonObject2.put("1000", "Product Category is empty.@#productCategory#@");
				return jsonObject2;
			} else {
				
			}
			//skuId validation
			if(obj.getSkuId() == null || obj.getSkuId().isEmpty()) {
				jsonObject2.put("1000", "Product skuId is empty.@#skuId#@");
				return jsonObject2;
			}
			//productAttribute skuId validation
			if(obj.getProductAttributes() != null && obj.getProductAttributes().size() > 0) {
				List<ProductAttributes> productAttributesList = productService.prepareProductAttributes(obj.getProductAttributes());
				if(productAttributesList != null && productAttributesList.size() > 0) {
					for(ProductAttributes productAttributes : productAttributesList) {
						if(productAttributes.getSkuId() == null || productAttributes.getSkuId().isEmpty()) {
							jsonObject2.put("1000", "skuId is empty in attribute set.@#skuId#@");
							return jsonObject2;
						}
					}
				}
			}
			//product isExist validation
			if (method.equals(SalesChannelConstants.POST) && obj.getId() == null) {
				ProductJsonModel productJsonModel = productService.checkProductExist(obj.getSkuId(), obj.getCustomerId()); 
				if(productJsonModel != null) {
					jsonObject2.put("4001", "Database Error.Record already exist.");
					return jsonObject2;
				}
			}
			//POST method
			else if (method.equals(SalesChannelConstants.PUT)) {
				//productId validation
				if(obj.getId() == null || obj.getId().isEmpty()) {
					jsonObject2.put("1000", "Product Id is empty.@#id#@");
					return jsonObject2;
				}
				//product isExist validation
				ProductJsonModel productJsonModel = productService.getProductById(obj.getId()); 
				if(productJsonModel == null) {
					jsonObject2.put("4002", "Database Error.Record not exist.");
					return jsonObject2;
				}
				if(productJsonModel != null && productJsonModel.getCustomerId() != null 
						&& !productJsonModel.getCustomerId().isEmpty() && !productJsonModel.getCustomerId().equals(obj.getCustomerId())) {
					jsonObject2.put("1004", "Validation Error.Invalid productId passed.");
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
						if (parameter.getName().equalsIgnoreCase("skuId")) {
							skuId = parameter.getValue();
						}
						else if (parameter.getName().equalsIgnoreCase("productId")) {
							productId = parameter.getValue();
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
	public ProductJsonModel getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException, JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductJsonModel productJsonModel = mapper.readValue(stream, ProductJsonModel.class);
		return productJsonModel;
	}

	public ProductServiceImpl getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("skuId");
		paramList.add("productId");
		paramList.add("isAll");
		return paramList;
	}

}