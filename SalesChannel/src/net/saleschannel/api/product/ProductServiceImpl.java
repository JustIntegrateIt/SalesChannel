package net.saleschannel.api.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.saleschannel.api.base.SalesChannelBaseDao;
import net.saleschannel.api.constants.ProductTypes;
import net.saleschannel.api.constants.SalesChannelConstants;
import net.saleschannel.api.utility.SalesChannelUtility;

import org.apache.log4j.Logger;

public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGERS = Logger.getLogger(ProductServiceImpl.class);
	
	private ProductDaoImpl productDao;
	
	public ProductJsonObject convertProductSimpleJsonModelToObject(ProductJsonModel productJsonModel) {
		ProductJsonObject productJsonObject = null;
		try {
			if(productJsonModel != null) {
				productJsonObject = new ProductJsonObject();
				productJsonObject.setId(productJsonModel.getId());
				productJsonObject.setCost(productJsonModel.getCost());
				productJsonObject.setDescription(productJsonModel.getDescription());
				productJsonObject.setProductCategory(productJsonModel.getProductCategory());
				productJsonObject.setProductName(productJsonModel.getProductName());
				productJsonObject.setProductType(productJsonModel.getProductType());
				productJsonObject.setQuantity(productJsonModel.getQuantity());
				productJsonObject.setSkuId(productJsonModel.getSkuId());
				List<ProductAttributeSetJsonObject> productAccessoriesJsonObjectList = new ArrayList<ProductAttributeSetJsonObject>();
				List<ProductAccessoriesJsonModel> productAccessoriesList = productDao.getProductAccessoriesByProductId(productJsonModel.getId());
				if(productAccessoriesList != null && productAccessoriesList.size() > 0) {
					for(ProductAccessoriesJsonModel productAccessories : productAccessoriesList) {
						ProductAttributeSetJsonObject productAttributeSet = convertProductAccessoriesJsonModelToObject(productAccessories);
						productAccessoriesJsonObjectList.add(productAttributeSet);
					}
				}
				productJsonObject.setProductAccessories(productAccessoriesJsonObjectList);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert ProductSimpleJsonModel To Object");
			e.printStackTrace();
		}
		return productJsonObject;
	}
	
	public ProductJsonModel convertProductJsonObjectToModel(ProductJsonObject productJsonObject) {
		ProductJsonModel productJsonModel = null;
		try {
			if(productJsonObject != null) {
				productJsonModel = new ProductJsonModel();
				productJsonModel.setId(productJsonObject.getId());
				productJsonModel.setCustomerId(productJsonObject.getCustomerId());
				productJsonModel.setCost(productJsonObject.getCost());
				productJsonModel.setDescription(productJsonObject.getDescription());
				productJsonModel.setProductCategory(productJsonObject.getProductCategory());
				productJsonModel.setProductName(productJsonObject.getProductName());
				productJsonModel.setProductType(productJsonObject.getProductType());
				productJsonModel.setQuantity(productJsonObject.getQuantity());
				productJsonModel.setSkuId(productJsonObject.getSkuId());
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert ProductJsonObject To Model");
			e.printStackTrace();
		}
		return productJsonModel;
	}
	
	public ProductJsonObject prepareProductCompoundJsonObject(ProductJsonModel productJsonModel) {
		List<ProductAttributeSetModel> productAttributeSetModelList = null;
		List<ProductAttributeSetJsonObject> productAccessoriesJsonObjectList = null;
		ProductJsonObject productJsonObject = new ProductJsonObject();
		productJsonObject.setId(productJsonModel.getId());
		productJsonObject.setCost(productJsonModel.getCost());
		productJsonObject.setDescription(productJsonModel.getDescription());
		productJsonObject.setProductCategory(productJsonModel.getProductCategory());
		productJsonObject.setProductName(productJsonModel.getProductName());
		productJsonObject.setProductType(productJsonModel.getProductType());
		productJsonObject.setQuantity(productJsonModel.getQuantity());
		productJsonObject.setSkuId(productJsonModel.getSkuId());
		try {
			if(productJsonModel != null) {
				List<ProductAttributesJsonModel> productAttributes = productDao.getProductAttributeByProductId(productJsonModel.getId());
				if(productAttributes != null && productAttributes.size() > 0) {
					productAttributeSetModelList = new ArrayList<ProductAttributeSetModel>();
					for(ProductAttributesJsonModel productAttribute : productAttributes) {
						ProductAttributeSetModel productAttributeSetModel = new ProductAttributeSetModel();
						List<ProductAttributeSetJsonObject> productAttributeSetList = new ArrayList<ProductAttributeSetJsonObject>();
						List<ProductAttributeCombinationJsonModel> productAttributeCombinations = productDao.
								getProductAttributeCombinationByProductAttributId(productAttribute.getId());
						if(productAttributeCombinations != null && productAttributeCombinations.size() > 0) {
							for(ProductAttributeCombinationJsonModel productAttributeCombination : productAttributeCombinations) {
								AttributeJsonModel attribute = productDao.getAttributeById(productAttributeCombination.getAttributeId());
								ProductAttributeSetJsonObject productAttributeSet = new ProductAttributeSetJsonObject();
								productAttributeSet.setName(attribute.getName());
								productAttributeSet.setDescription(attribute.getDescription());
								productAttributeSet.setValue(productAttributeCombination.getValue());
								productAttributeSetList.add(productAttributeSet);
							}
						}
						if(productAttribute.getSkuId() != null && !productAttribute.getSkuId().isEmpty()) {
							ProductAttributeSetJsonObject productAttributeSet = new ProductAttributeSetJsonObject();
							productAttributeSet.setName(SalesChannelConstants.SKU_ID);
							productAttributeSet.setValue(productAttribute.getSkuId());
							productAttributeSetList.add(productAttributeSet);
						}
						if(productAttribute.getQuantity() != null && !productAttribute.getQuantity().equals("")) {
							ProductAttributeSetJsonObject productAttributeSet = new ProductAttributeSetJsonObject();
							productAttributeSet.setName(SalesChannelConstants.QUANTITY);
							productAttributeSet.setValue(productAttribute.getQuantity().toString());
							productAttributeSetList.add(productAttributeSet);
						}
						if(productAttribute.getCost() != null && !productAttribute.getCost().equals("")) {
							ProductAttributeSetJsonObject productAttributeSet = new ProductAttributeSetJsonObject();
							productAttributeSet.setName(SalesChannelConstants.COST);
							productAttributeSet.setValue(productAttribute.getCost().toString());
							productAttributeSetList.add(productAttributeSet);
						}
						productAttributeSetModel.setProductAttributeSet(productAttributeSetList);
						productAttributeSetModelList.add(productAttributeSetModel);
					}
					productJsonObject.setProductAttributes(productAttributeSetModelList);
				} else {
					productJsonObject.setProductAttributes(null);
				}
				List<ProductAccessoriesJsonModel> productAccessoriesList = productDao.getProductAccessoriesByProductId(productJsonModel.getId());
				if(productAccessoriesList != null && productAccessoriesList.size() > 0) {
					productAccessoriesJsonObjectList = new ArrayList<ProductAttributeSetJsonObject>();
					for(ProductAccessoriesJsonModel productAccessories : productAccessoriesList) {
						ProductAttributeSetJsonObject productAttributeSet = convertProductAccessoriesJsonModelToObject(productAccessories);
						productAccessoriesJsonObjectList.add(productAttributeSet);
					}
					productJsonObject.setProductAccessories(productAccessoriesJsonObjectList);
				} else {
					productJsonObject.setProductAccessories(null);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productJsonObject;
	}
	
	public ProductAccessoriesJsonModel convertProductAccessoriesJsonObjectToModel(ProductAttributeSetJsonObject productAttributeSetJsonObject) {
		ProductAccessoriesJsonModel productAccessoriesJsonModel = null;
		try {
			if(productAttributeSetJsonObject != null) {
				productAccessoriesJsonModel = new ProductAccessoriesJsonModel();
				productAccessoriesJsonModel.setName(productAttributeSetJsonObject.getName());
				productAccessoriesJsonModel.setValue(productAttributeSetJsonObject.getValue());
				productAccessoriesJsonModel.setDescription(productAttributeSetJsonObject.getDescription());
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert ProductAccessories Json Object To Model");
			e.printStackTrace();
		}
		return productAccessoriesJsonModel;
	}
	
	public ProductAttributeSetJsonObject convertProductAccessoriesJsonModelToObject(ProductAccessoriesJsonModel productAccessoriesJsonModel) {
		ProductAttributeSetJsonObject productAttributeSetJsonObject = null;
		try {
			if(productAccessoriesJsonModel != null) {
				productAttributeSetJsonObject = new ProductAttributeSetJsonObject();
				productAttributeSetJsonObject.setName(productAccessoriesJsonModel.getName());
				productAttributeSetJsonObject.setValue(productAccessoriesJsonModel.getValue());
				productAttributeSetJsonObject.setDescription(productAccessoriesJsonModel.getDescription());
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert ProductAccessories Json Model To Object");
			e.printStackTrace();
		}
		return productAttributeSetJsonObject;
	}
	
	public String insertProduct(ProductJsonObject productJsonObject) {
		String productId = null;
		String productAttributeId = null;
		if(productJsonObject != null) {
			try {
				ProductJsonModel productJsonModel = convertProductJsonObjectToModel(productJsonObject);
				productJsonModel.setCreateBy(productJsonModel.getCustomerId());
				productJsonModel.setCreatedAt(new Date());
				if(productJsonObject.getProductType().equals(ProductTypes.Simple)) {
					productId = productDao.insertProduct(productJsonModel);
					//insert product accessories
					if(productId != null && productJsonObject != null && productJsonObject.getProductAccessories() != null 
							&& productJsonObject.getProductAccessories().size() > 0) {
						for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
							insertProductAccessories(productAttributeSetJsonObject, productId, productJsonObject.getCustomerId());
						}
					}
				} else {
					//insert attributes if not exist in database
					List<AttributeJsonModel> attributes = prepareAttributes(productJsonObject.getProductAttributes()); 
					for(AttributeJsonModel attribute : attributes) {
						AttributeJsonModel attributeExist = productDao.checkAttributeExist(attribute.getName());
						if(attributeExist == null) {
							productDao.insertAttribute(attribute);
						}
					}
					//insert product
					productId = productDao.insertProduct(productJsonModel);
					//insert ProductAttributes
					if(productId != null) {
						List<ProductAttributesJsonModel> productAttributes = prepareProductAttributes(productJsonObject.getProductAttributes());
						if(productAttributes != null && productAttributes.size() > 0) {
							int i = 0;
							do {
								ProductAttributesJsonModel productAttribute = productAttributes.get(i); 
								productAttribute.setProductId(productId);
								ProductAttributesJsonModel productAttributeExist = checkProductAttributeExist(
										productAttribute.getProductId(), productAttribute.getSkuId());
								if(productAttributeExist == null) {
									productAttributeId = productDao.insertProductAttribute(productAttribute);
								}
								i++;
							} while(productAttributeId != null && productAttributes.size() > i);
						}
					}
					//insert ProductAttributesCombinations
					if(productId != null && productAttributeId != null) {
						List<ProductAttributeCombinationJsonModel> productAttributeCombinations = prepareProductAttributeCombination(productJsonObject.getProductAttributes(), productId);
						for(ProductAttributeCombinationJsonModel productAttributeCombination : productAttributeCombinations) {
							productDao.insertProductAttributeCombination(productAttributeCombination);
						}
					}
					//insert product accessories
					if(productId != null && productJsonObject != null && productJsonObject.getProductAccessories() != null 
							&& productJsonObject.getProductAccessories().size() > 0) {
						for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
							insertProductAccessories(productAttributeSetJsonObject, productId, productJsonObject.getCustomerId());
						}
					}
				}
			} catch(Exception e) {
				LOGGERS.error("error while perform insert product.");
				e.printStackTrace();
			}
		}
		return productId;
	}

	public ProductJsonObject checkProductExist(String skuId, String customerId) {
		ProductJsonObject productJsonObject = null;
		try {
			ProductJsonModel productJsonModel = productDao.checkProductExist(skuId, customerId);
			if(productJsonModel != null) {
				if(productJsonModel.getProductType() != null 
						&& !productJsonModel.getProductType().equals(ProductTypes.Simple)) {
					productJsonObject = convertProductSimpleJsonModelToObject(productJsonModel);	
				} else if(productJsonModel.getProductType() != null 
						&& productJsonModel.getProductType().equals(ProductTypes.Configurable)) {
					productJsonObject = prepareProductCompoundJsonObject(productJsonModel);	
				} 
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productJsonObject;
	}
	
	public ProductAttributesJsonModel checkProductAttributeExist(String productId, String skuId) {
		ProductAttributesJsonModel productAttributeExist = null;
		try {
			productAttributeExist = productDao.checkProductAttributeExist(productId,	skuId);
		} catch (Exception e) {
			LOGGERS.error("error occured while check product attribute exist.");
			e.printStackTrace();
		}
		return productAttributeExist;
	}
	
	public boolean updateProduct(ProductJsonObject productJsonObject) {
		boolean status = false;
		try {
			if(productJsonObject != null) {
				ProductJsonModel productJsonModelOld = productDao.getProductById(productJsonObject.getId());
				productJsonModelOld.setUpdatedBy(productJsonObject.getCustomerId());
				productJsonModelOld.setUpdatedAt(new Date());
				if(productJsonObject.getProductType().equals(ProductTypes.Simple) 
						&& productJsonModelOld.getProductType().equals(ProductTypes.Simple)) {
					productJsonModelOld.setCost(productJsonObject.getCost());
					productJsonModelOld.setDescription(productJsonObject.getDescription());
					productJsonModelOld.setProductCategory(productJsonObject.getProductCategory());
					productJsonModelOld.setProductName(productJsonObject.getProductName());
					productJsonModelOld.setQuantity(productJsonObject.getQuantity());
					//code to perform synced files
					status = productDao.updateProduct(productJsonModelOld);
					if(status) {
						List<ProductAccessoriesJsonModel> productAccessories = productDao.getProductAccessoriesByProductId(productJsonObject.getId());
						if(productAccessories != null && productAccessories.size() > 0) {
							status = productDao.deleteProductAccessoriesByProductId(productJsonObject.getId());
							if(status && productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
								for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
									insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
								}
							}
						} else {
							if(productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
								for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
									insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
								}
							}
						}
					}
				} 
				else if(productJsonObject.getProductType().equals(ProductTypes.Simple) 
						&& !productJsonModelOld.getProductType().equals(ProductTypes.Configurable)) {
					status = deleteProductAttributes(productJsonObject.getId());
					if(status) {
						productJsonModelOld.setCost(productJsonObject.getCost());
						productJsonModelOld.setDescription(productJsonObject.getDescription());
						productJsonModelOld.setProductCategory(productJsonObject.getProductCategory());
						productJsonModelOld.setProductName(productJsonObject.getProductName());
						productJsonModelOld.setQuantity(productJsonObject.getQuantity());
						//code to perform synced files
						status = productDao.updateProduct(productJsonModelOld);
						if(status) {
							List<ProductAccessoriesJsonModel> productAccessories = productDao.getProductAccessoriesByProductId(productJsonObject.getId());
							if(productAccessories != null && productAccessories.size() > 0) {
								status = productDao.deleteProductAccessoriesByProductId(productJsonObject.getId());
								if(status && productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
									for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
										insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
									}
								}
							} else {
								if(productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
									for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
										insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
									}
								}
							}
						}
					}
				}
				else if(productJsonObject.getProductType().equals(ProductTypes.Configurable) 
						&& productJsonModelOld.getProductType().equals(ProductTypes.Configurable)) {
					//insert attributes if not exist in database
					List<AttributeJsonModel> attributes = prepareAttributes(productJsonObject.getProductAttributes()); 
					for(AttributeJsonModel attribute : attributes) {
						AttributeJsonModel attributeExist = productDao.checkAttributeExist(attribute.getName());
						if(attributeExist == null) {
							productDao.insertAttribute(attribute);
						}
					}
					//update product
					productJsonModelOld.setCost(productJsonObject.getCost());
					productJsonModelOld.setDescription(productJsonObject.getDescription());
					productJsonModelOld.setProductCategory(productJsonObject.getProductCategory());
					productJsonModelOld.setProductName(productJsonObject.getProductName());
					productJsonModelOld.setQuantity(productJsonObject.getQuantity());
					status = productDao.updateProduct(productJsonModelOld);
					//insert ProductAttributes
					if(status) {
						String productAttributeId = null;
						//delete all the product attributes and add the new one
						status = deleteProductAttributes(productJsonObject.getId());
						if(status) {
							List<ProductAttributesJsonModel> productAttributes = prepareProductAttributes(productJsonObject.getProductAttributes());
							if(productAttributes != null && productAttributes.size() > 0) {
								int i = 0;
								do {
									ProductAttributesJsonModel productAttribute = productAttributes.get(i); 
									productAttribute.setProductId(productJsonObject.getId());
									ProductAttributesJsonModel productAttributeExist = checkProductAttributeExist(
											productAttribute.getProductId(), productAttribute.getSkuId());
									if(productAttributeExist == null) {
										productAttributeId = productDao.insertProductAttribute(productAttribute);
									}
									i++;
								} while(productAttributeId != null && productAttributes.size() > i);
							}
							//insert ProductAttributesCombinations
							if(productJsonObject.getId() != null && productAttributeId != null) {
								List<ProductAttributeCombinationJsonModel> productAttributeCombinations = prepareProductAttributeCombination(
										productJsonObject.getProductAttributes(), productJsonObject.getId());
								for(ProductAttributeCombinationJsonModel productAttributeCombination : productAttributeCombinations) {
									productDao.insertProductAttributeCombination(productAttributeCombination);
								}
							}
							//delete and insert ProductAccessories
							List<ProductAccessoriesJsonModel> productAccessories = productDao.getProductAccessoriesByProductId(productJsonObject.getId());
							if(productAccessories != null && productAccessories.size() > 0) {
								status = productDao.deleteProductAccessoriesByProductId(productJsonObject.getId());
								if(status && productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
									for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
										insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
									}
								}
							} else {
								if(productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
									for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
										insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
									}
								}
							}
						}
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while perform update product.");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteProduct(ProductJsonObject productJsonObject) {
		String productId = productJsonObject.getId();
		boolean status = false;
		try {
			status = deleteProductAttributes(productId);
			if(status) {
				status = productDao.deleteProductAccessoriesByProductId(productId);
				if(status) {
					status = productDao.deleteProduct(productId);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGERS.error("error while delete product.");
		}
		return status;
	}
	
	public boolean deleteProductJsonModel(ProductJsonModel productJsonModel) {
		String productId = productJsonModel.getId();
		boolean status = false;
		try {
			status = deleteProductAttributes(productId);
			if(status) {
				status = productDao.deleteProductAccessoriesByProductId(productId);
				if(status) {
					status = productDao.deleteProduct(productId);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGERS.error("error while delete Product Json Model.");
		}
		return status;
	}
	
	public boolean deleteProductAttributes(String productId) {
		boolean status = false;
		try {
			List<ProductAttributesJsonModel> productAttributesList = productDao.getProductAttributeByProductId(productId);
			if(productAttributesList != null && productAttributesList.size() > 0) {
				for(ProductAttributesJsonModel productAttributes : productAttributesList) {
					status = productDao.deleteProductAttributeCombinationByProductAttributId(productAttributes.getId());
				}
			}
			if(status) {
				status = productDao.deleteProductAttributeByProductId(productId);
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGERS.error("error while delete product attributes.");
		}
		return status;
	}
	
	public boolean deleteProducts(String customerId) {
		boolean status = false;
		try {
			List<ProductJsonModel> productJsonModelList = productDao.getProductsByCustomer(customerId);
			if(productJsonModelList != null && productJsonModelList.size() > 0) {
				for(ProductJsonModel productJsonModel : productJsonModelList) {
					status = deleteProductJsonModel(productJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error delete products");
			e.printStackTrace();
		}
		return status;
	}

	public ProductJsonObject getProductById(String productId) {
		ProductJsonObject productJsonObject = null;
		try {
			ProductJsonModel productJsonModel = productDao.getProductById(productId);
			if(productJsonModel != null) {
				if(productJsonModel.getProductType() != null 
						&& productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString())) {
					productJsonObject = convertProductSimpleJsonModelToObject(productJsonModel);	
				} else if(productJsonModel.getProductType() != null 
						&& productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Configurable.toString()).toString())) {
					productJsonObject = prepareProductCompoundJsonObject(productJsonModel);	
				}
			}
		} catch (Exception e) {
			LOGGERS.error("error occured while check product attribute exist.");
			e.printStackTrace();
		}
		return productJsonObject;
	}
	
	public ProductJsonObject getProductBySkuId(String skuId) {
		ProductJsonObject productJsonObject = null;
		try {
			ProductJsonModel productJsonModel = productDao.getProductById(skuId);
			if(productJsonModel != null) {
				if(productJsonModel.getProductType() != null 
						&& productJsonModel.getProductType().equals(ProductTypes.Simple)) {
					productJsonObject = convertProductSimpleJsonModelToObject(productJsonModel);	
				} else if(productJsonModel.getProductType() != null 
						&& productJsonModel.getProductType().equals(ProductTypes.Configurable)) {
					productJsonObject = prepareProductCompoundJsonObject(productJsonModel);	
				}			
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get product by skuId.");
			e.printStackTrace();
		}
		return productJsonObject;
	}

	public List<ProductJsonObject> getProductsByCustomer(String customerId) {
		List<ProductJsonObject> productJsonObjectList = null;
		try {
			List<ProductJsonModel> productModels = productDao.getProductsByCustomer(customerId);
			if(productModels != null && productModels.size() > 0) {
				for(ProductJsonModel productJsonModel : productModels) {
					productJsonObjectList = new ArrayList<ProductJsonObject>();
					if(productJsonModel != null) {
						if(productJsonModel.getProductType() != null 
								&& productJsonModel.getProductType().equals(ProductTypes.Simple)) {
							ProductJsonObject productJsonObject = convertProductSimpleJsonModelToObject(productJsonModel);
							productJsonObjectList.add(productJsonObject);
						} else if(productJsonModel.getProductType() != null 
								&& productJsonModel.getProductType().equals(ProductTypes.Configurable)) {
							ProductJsonObject productJsonObject = prepareProductCompoundJsonObject(productJsonModel);
							productJsonObjectList.add(productJsonObject);
						}
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get product by customer.");
			e.printStackTrace();
		}
		return productJsonObjectList;
	}
	
	public List<AttributeJsonModel> prepareAttributes(List<ProductAttributeSetModel> productAttributeSetModelList) {
		List<AttributeJsonModel> attributes = null;
		try {
			if(productAttributeSetModelList != null && productAttributeSetModelList.size() > 0) {
				attributes = new ArrayList<AttributeJsonModel>();
				for (ProductAttributeSetModel productAttributeSetModel : productAttributeSetModelList) {
					for(ProductAttributeSetJsonObject productAttributeSet : productAttributeSetModel.getProductAttributeSet()) {
						AttributeJsonModel attribute = new AttributeJsonModel();
						if(!productAttributeSet.getName().equals(SalesChannelConstants.SKU_ID)
								&& !productAttributeSet.getName().equals(SalesChannelConstants.QUANTITY)
								&& !productAttributeSet.getName().equals(SalesChannelConstants.COST)) {
							attribute.setName(productAttributeSet.getName());
							attribute.setDescription(productAttributeSet.getName());
							attributes.add(attribute);
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return attributes;
	}

	public List<ProductAttributesJsonModel> prepareProductAttributes(List<ProductAttributeSetModel> productAttributeSetModelList) {
		List<ProductAttributesJsonModel> productAttributes = null;
		try {
			if(productAttributeSetModelList != null && productAttributeSetModelList.size() > 0) {
				productAttributes = new ArrayList<ProductAttributesJsonModel>();
				for (ProductAttributeSetModel productAttributeSetModel : productAttributeSetModelList) {
					ProductAttributesJsonModel productAttribute = new ProductAttributesJsonModel();
					for(ProductAttributeSetJsonObject productAttributeSet : productAttributeSetModel.getProductAttributeSet()) {
						if(productAttributeSet.getName().equals(SalesChannelConstants.SKU_ID))
							productAttribute.setSkuId(productAttributeSet.getValue());
						else if(productAttributeSet.getName().equals(SalesChannelConstants.QUANTITY))
							productAttribute.setQuantity(Integer.parseInt(productAttributeSet.getValue()));
						else if(productAttributeSet.getName().equals(SalesChannelConstants.COST))
							productAttribute.setCost(Integer.parseInt(productAttributeSet.getValue()));
					}
					productAttributes.add(productAttribute);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productAttributes;
	}
	
	public List<ProductAttributeCombinationJsonModel> prepareProductAttributeCombination(List<ProductAttributeSetModel> productAttributeSetModelList,
			String productId) {
		List<ProductAttributeCombinationJsonModel> productAttributeCombinations = null;
		try {
			if(productAttributeSetModelList != null && productAttributeSetModelList.size() > 0) {
				productAttributeCombinations = new ArrayList<ProductAttributeCombinationJsonModel>();
				for (ProductAttributeSetModel productAttributeSetModel : productAttributeSetModelList) {
					ProductAttributesJsonModel productAttributeId = null;
					AttributeJsonModel attributeId = null;
					for(ProductAttributeSetJsonObject productAttributeSet : productAttributeSetModel.getProductAttributeSet()) {
						if(productAttributeSet.getName().equals(SalesChannelConstants.SKU_ID)) {
							productAttributeId = productDao.checkProductAttributeExist(productId, 
									productAttributeSet.getValue());
						}
					}
					for(ProductAttributeSetJsonObject productAttributeSet : productAttributeSetModel.getProductAttributeSet()) {
						ProductAttributeCombinationJsonModel productAttributeCombination = new ProductAttributeCombinationJsonModel();
						if(!productAttributeSet.getName().equals(SalesChannelConstants.SKU_ID)
								&& !productAttributeSet.getName().equals(SalesChannelConstants.QUANTITY)
								&& !productAttributeSet.getName().equals(SalesChannelConstants.COST)) {
							attributeId = productDao.checkAttributeExist(productAttributeSet.getName());
							if(productAttributeId != null && attributeId != null) {
								productAttributeCombination.setAttributeId(attributeId.getId());
								productAttributeCombination.setProductAttributeId(productAttributeId.getId());
								productAttributeCombination.setValue(productAttributeSet.getValue());
								productAttributeCombinations.add(productAttributeCombination);
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productAttributeCombinations;
	}
	
	public String insertProductAccessories(ProductAttributeSetJsonObject productAttributeSetJsonObject, String productId, String customerId) {
		String productAccessoriesId = null;
		try {
			if(productAttributeSetJsonObject.getName() != null && !productAttributeSetJsonObject.getName().isEmpty() 
					&& productAttributeSetJsonObject.getName().contains("image")) {
				ProductImage productImage = new ProductImage();
				productImage.setProductId(productId);
				productImage.setImageName(productAttributeSetJsonObject.getName());
				if(productAttributeSetJsonObject.getValue() != null && !productAttributeSetJsonObject.getValue().isEmpty()) {
					if(productAttributeSetJsonObject.getValue().contains("http")) {
						String imagePath = SalesChannelBaseDao.saveImagePath + SalesChannelConstants.FILE_SEPERATOR + customerId; 
						String actualPath = SalesChannelUtility.getImagefromUrl(productAttributeSetJsonObject.getValue().trim()
								, imagePath);
						productImage.setImagePath(imagePath);
						productImage.setActualPath(actualPath);
					} 
					productDao.insertProductImage(productImage);
				}
			} else {
				ProductAccessoriesJsonModel productAccessoriesJsonModel = convertProductAccessoriesJsonObjectToModel(productAttributeSetJsonObject);
				productAccessoriesJsonModel.setProductId(productId);
				productAccessoriesJsonModel.setName(productAttributeSetJsonObject.getName());
				productAccessoriesJsonModel.setValue(productAttributeSetJsonObject.getValue());
				productAccessoriesJsonModel.setDescription(productAttributeSetJsonObject.getDescription());
				productDao.insertProductAccessories(productAccessoriesJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error while insert product accessories");
			e.printStackTrace();
		}
		return productAccessoriesId;
	}
	
	public ProductDaoImpl getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

}
