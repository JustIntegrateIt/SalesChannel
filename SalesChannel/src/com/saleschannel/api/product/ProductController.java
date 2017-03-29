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
import com.saleschannel.api.constants.ProductTypes;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.inventory.BinJsonModel;
import com.saleschannel.api.inventory.InventoryServiceImpl;
import com.saleschannel.api.productcategory.ProductCategoryJsonObject;
import com.saleschannel.api.productcategory.ProductCategoryServiceImpl;
import com.saleschannel.api.utility.SalesChannelUtility;

public class ProductController extends SalesChannelServerResource<ProductsJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ProductController.class);
	
	private ProductServiceImpl productService;
	
	private ProductCategoryServiceImpl categoryService;
	
	private InventoryServiceImpl inventoryService;
	
	private String skuId = null;
	
	private String productId = null;
	
	private boolean isAll = false;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			boolean isValidReq = false;
			List<ProductJsonObject> productJsonObjects = new ArrayList<ProductJsonObject>();
			ProductJsonObject productJsonObject = new ProductJsonObject();
			if(skuId != null) {
				isValidReq = true;
				productJsonObject = productService.getProduct(skuId, getCustomerId());
				if(productJsonObject != null)
					productJsonObjects.add(productJsonObject);
			}
			else if(productId != null) {
				isValidReq = true;
				productJsonObject = productService.getProductById(productId);
				if(productJsonObject != null)
					productJsonObjects.add(productJsonObject);
			}
			else if(isAll) {
				isValidReq = true;
				productJsonObjects = productService.getProductsByCustomer(getCustomerId());
			}
			if(productJsonObjects != null && productJsonObjects.size() > 0) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(productJsonObjects);
			} else if(!isValidReq){
				salesChannelErrorObject.setStatusCode(1005);
				salesChannelErrorObject.setMessage(getErrorMessage(1005));
			} else {
				salesChannelErrorObject.setStatusCode(30004);
				salesChannelErrorObject.setMessage(getErrorMessage(30004));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,	ProductsJsonObject obj) {
		Representation representation = null;
		try {
			List<ProductJsonObject> productJsonObjectList = null;
			obj.setCustomerId(getCustomerId());
			if(obj != null && obj.getProductJsonObjects() != null && obj.getProductJsonObjects().size() > 0) {
				String id = null;
				for(ProductJsonObject productJsonObject : obj.getProductJsonObjects()) {
					//product isExist validation
					ProductJsonModel productJsonModel = productService.checkProductExist(productJsonObject.getSkuId(), productJsonObject.getCustomerId());
					if(productJsonModel != null) {
						if(productJsonObjectList == null) {
							productJsonObjectList = new ArrayList<ProductJsonObject>();
						}
						productJsonObjectList.add(productJsonObject);
					} else {
						productJsonObject.setCustomerId(getCustomerId());
						id = productService.insertProduct(productJsonObject);
					}
				}
				if(id != null && productJsonObjectList == null) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData("Product Added Successfully.");
				} else if(productJsonObjectList != null && productJsonObjectList.size() > 0){
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage("These Products Already Exist. So not Added.");
					salesChannelErrorObject.setData(productJsonObjectList);
				} else {
					salesChannelErrorObject.setStatusCode(30000);
					salesChannelErrorObject.setMessage(getErrorMessage(30000));
				}
			} else {
				salesChannelErrorObject.setStatusCode(1006);
				salesChannelErrorObject.setMessage(getErrorMessage(1006));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,	ProductsJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			if(obj != null && obj.getProductJsonObjects() != null && obj.getProductJsonObjects().size() > 0) {
				for(ProductJsonObject productJsonObject : obj.getProductJsonObjects()) {
					productJsonObject.setCustomerId(getCustomerId());
					boolean status = productService.updateProduct(productJsonObject);
					if(status) {
						salesChannelErrorObject.setStatusCode(200);
						salesChannelErrorObject.setMessage(getErrorMessage(200));
						salesChannelErrorObject.setData("Product Updated Successfully.");
					} else {
						salesChannelErrorObject.setStatusCode(30002);
						salesChannelErrorObject.setMessage(getErrorMessage(30002));
					}
				}
			} else {
					salesChannelErrorObject.setStatusCode(1005);
					salesChannelErrorObject.setMessage(getErrorMessage(1005));
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
			ProductJsonObject productJsonObject = new ProductJsonObject();
			if(skuId != null) {
				productJsonObject = productService.getProduct(skuId, getCustomerId());
				if(productJsonObject != null) {
					isExist = true;
					deleted = productService.deleteProduct(productJsonObject);
				}
			}
			else if(productId != null) {
				productJsonObject = productService.getProductById(productId);
				if(productJsonObject != null) {
					isExist = true;
					deleted = productService.deleteProduct(productJsonObject);
				}
			} else if(isAll) {
				List<ProductJsonObject> productJsonObjectList = productService.getProductsByCustomer(
						getCustomerId());
				if(productJsonObjectList != null && productJsonObjectList.size() > 0) {
					isExist = true;
					deleted = productService.deleteProducts(getCustomerId());
				}
			}
			if(isExist && deleted) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData("Product deleted successfully");
			} else if(isExist) {
				salesChannelErrorObject.setStatusCode(30003);
				salesChannelErrorObject.setMessage(getErrorMessage(30003));
			} else {
				salesChannelErrorObject.setStatusCode(30004);
				salesChannelErrorObject.setMessage(getErrorMessage(30004));
			}
			representation = new JsonRepresentation(new JSONObject(salesChannelErrorObject));
		} catch (Exception e) {
			LOGGERS.error("Error occured while fetch Product.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(ProductsJsonObject productsJsonObject, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			productsJsonObject.setCustomerId(getCustomerId());
			if(productsJsonObject != null && productsJsonObject.getProductJsonObjects() != null && productsJsonObject.getProductJsonObjects().size() > 0) {
				for(ProductJsonObject obj : productsJsonObject.getProductJsonObjects()) {
					obj.setCustomerId(getCustomerId());
					//productName validation
					if(obj.getProductName() == null || obj.getProductName().isEmpty()) {
						jsonObject2.put("30011", "Product Name is empty.@#productName#@");
						return jsonObject2;
					}
					//productType validation
					if(obj.getProductType() == null || obj.getProductType().isEmpty()) {
						jsonObject2.put("30012", "Product Type is empty.@#productType#@");
						return jsonObject2;
					} else {
						ProductTypes productType = ProductTypes.getByName(obj.getProductType());
						if(productType == null) {
							jsonObject2.put("30013", "Product Type value is not valid. Please send any of these "
						+ProductTypes.Simple+", "+ProductTypes.Configurable+", "+ProductTypes.Virtual+", "
						+ProductTypes.Downloadable+", "+ProductTypes.Grouped+", "+ProductTypes.Bundled
						+".@#productType#@");
							return jsonObject2;
						} else {
							if(productType.equals(ProductTypes.Simple)) {
								//cost validation
								if(obj.getCost() == null || obj.getCost().equals("")) {
									jsonObject2.put("30014", "Product Cost is empty.@#cost#@");
									return jsonObject2;
								} else {
									try {
										obj.setCost(obj.getCost().intValue());
									} catch(Exception e) {
										LOGGERS.error(e.getStackTrace());
										jsonObject2.put("30015", "Product Cost is not valid.@#cost#@");
										return jsonObject2;
									}
								}
								//quantity validation
								if(obj.getQuantity() == null || obj.getQuantity().equals("")) {
									jsonObject2.put("30016", "Product Quantity is empty.@#quantity#@");
									return jsonObject2;
								} else {
									try {
										obj.setQuantity(obj.getQuantity().intValue());
									} catch(Exception e) {
										LOGGERS.error(e.getStackTrace());
										jsonObject2.put("30017", "Product Quantity is not valid.@#quantity#@");
										return jsonObject2;
									}
								}
							} else if(productType.equals(ProductTypes.Configurable)) {
								//productAttributes validation
								if(obj.getProductAttributes() != null && !obj.getProductAttributes().isEmpty()) {
									for(ProductAttributeSetModel productAttributeSetModel : obj.getProductAttributes()) {
										//binContent validation
										if(productAttributeSetModel.getProductAttributeSet() != null && productAttributeSetModel.getProductAttributeSet().size() > 0 
												&& productAttributeSetModel.getBinContent() != null) {
											//binId validation
											if(productAttributeSetModel.getBinContent().getBinId() != null && !productAttributeSetModel.getBinContent().getBinId().isEmpty()) {
												BinJsonModel binJsonModel = inventoryService.checkBinById(productAttributeSetModel.getBinContent().getBinId());
												if(binJsonModel == null) {
													jsonObject2.put("30024", "BinId is not Exist.@#binId#@");
													return jsonObject2;
												}
											} else {
												jsonObject2.put("30023", "BinId is empty in BinContent.@#binId#@");
												return jsonObject2;
											}
											//quantity validation
											if(productAttributeSetModel.getBinContent().getQuantity() == null) {
												jsonObject2.put("30025", "BinContent Quantity is empty.@#quantity#@");
												return jsonObject2;
											}
											//cost validation
											if(productAttributeSetModel.getBinContent().getCost() == null) {
												jsonObject2.put("30026", "BinContent Cost is empty.@#cost#@");
												return jsonObject2;
											}
										}
									}
								} else {
									jsonObject2.put("30018", "Product Attributes is empty.@#productAttributes#@");
									return jsonObject2;
								}
							}
						}
					}
					//productCategory validation
					if(obj.getProductCategory() == null || obj.getProductCategory().isEmpty()) {
						jsonObject2.put("30019", "Product Category is empty.@#productCategory#@");
						return jsonObject2;
					} else {
						ProductCategoryJsonObject productCategoryJsonObject = categoryService.getProductCategoryByNameAndCustomerId(
								getCustomerId(), obj.getProductCategory());
						if(productCategoryJsonObject == null) {
							jsonObject2.put("30020", "Product Category is Invalid.@#productCategory#@");
							return jsonObject2;	
						}
						
					}
					//skuId validation
					if(obj.getSkuId() == null || obj.getSkuId().isEmpty()) {
						jsonObject2.put("30021", "Product skuId is empty.@#skuId#@");
						return jsonObject2;
					}
					//productAttribute skuId validation
					if(obj.getProductAttributes() != null && obj.getProductAttributes().size() > 0) {
						List<ProductAttributesJsonModel> productAttributesList = productService.prepareProductAttributes(
								obj.getProductAttributes());
						if(productAttributesList != null && productAttributesList.size() > 0) {
							for(ProductAttributesJsonModel productAttributes : productAttributesList) {
								if(productAttributes.getSkuId() == null || productAttributes.getSkuId().isEmpty()) {
									jsonObject2.put("30022", "skuId is empty in attribute set.@#skuId#@");
									return jsonObject2;
								}
							}
						}
					}
					//PUT method
					if (method.equals(SalesChannelConstants.PUT)) {
						//productId validation
						if(obj.getId() == null || obj.getId().isEmpty()) {
							jsonObject2.put("30027", "Product Id is empty.@#id#@");
							return jsonObject2;
						}
						//product isExist validation
						ProductJsonModel productJsonModel = productService.checkProductById(obj.getId()); 
						if(productJsonModel == null) {
							jsonObject2.put("30028", "Database Error.Record not exist.");
							return jsonObject2;
						}
						if(productJsonModel != null && productJsonModel.getCustomerId() != null 
								&& !productJsonModel.getCustomerId().isEmpty() && !productJsonModel.getCustomerId().equals(
										obj.getCustomerId())) {
							jsonObject2.put("30029", "Validation Error.Invalid productId passed.");
							return jsonObject2;
						}
					}
				}
			}
		}
		//GET DELETE method
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
	public ProductsJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException, JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final ProductsJsonObject productsJsonObject = mapper.readValue(stream, ProductsJsonObject.class);
		return productsJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("skuId");
		paramList.add("productId");
		paramList.add("isAll");
		return paramList;
	}

	public ProductServiceImpl getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceImpl productService) {
		this.productService = productService;
	}
	
	public ProductCategoryServiceImpl getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ProductCategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}

	public InventoryServiceImpl getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryServiceImpl inventoryService) {
		this.inventoryService = inventoryService;
	}

}
