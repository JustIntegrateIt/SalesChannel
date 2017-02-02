package net.saleschannel.api.product;

import java.io.InputStream;
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
				productJsonObject.setCustomerId(productJsonModel.getCustomerId());
				productJsonObject.setCost(productJsonModel.getCost());
				productJsonObject.setDescription(productJsonModel.getDescription());
				productJsonObject.setProductCategory(productJsonModel.getProductCategory());
				productJsonObject.setProductName(productJsonModel.getProductName());
				productJsonObject.setProductType(productJsonModel.getProductType());
				productJsonObject.setQuantity(productJsonModel.getQuantity());
				productJsonObject.setSkuId(productJsonModel.getSkuId());
				if(productJsonModel.getImage() != null && !productJsonModel.getImage().isEmpty()) {
					ProductImageJsonModel productImageJsonModel = productDao.getProductImageById(productJsonModel.getImage());
					if(productImageJsonModel.isURL()) {
						productJsonObject.setImage(productImageJsonModel.getImage());
					} else {
						InputStream imageStream = SalesChannelUtility.convertImageIntoStream(productImageJsonModel.getActualPath());
						productJsonObject.setImage(imageStream.toString());
					}
				}
				List<ProductAttributeSetJsonObject> productAccessoriesJsonObjectList = new ArrayList<ProductAttributeSetJsonObject>();
				List<ProductAccessoriesJsonModel> productAccessoriesList = productDao.getProductAccessoriesByProductId(productJsonModel.getId());
				if(productAccessoriesList != null && productAccessoriesList.size() > 0) {
					for(ProductAccessoriesJsonModel productAccessories : productAccessoriesList) {
						ProductAttributeSetJsonObject productAttributeSet = convertProductAccessoriesJsonModelToObject(productAccessories);
						productAccessoriesJsonObjectList.add(productAttributeSet);
					}
				}
				List<ProductImageJsonModel> productImageJsonModelList = productDao.getProductImageByProductId(productJsonModel.getId());
				if(productImageJsonModelList != null && productImageJsonModelList.size() > 0) {
					for(ProductImageJsonModel productImageJsonModel : productImageJsonModelList) {
						ProductAttributeSetJsonObject productAttributeSet = convertProductImageJsonModelToObject(productImageJsonModel);
						productAccessoriesJsonObjectList.add(productAttributeSet);
					}
				}
				if(productAccessoriesJsonObjectList != null && productAccessoriesJsonObjectList.size() > 0) {
					productJsonObject.setProductAccessories(productAccessoriesJsonObjectList);	
				} else {
					productJsonObject.setProductAccessories(null);
				}
				
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
				if(productJsonObject.getImage() != null && !productJsonObject.getImage().isEmpty()) {
					String imageId = saveUpdateImage(productJsonObject.getImage(), null, null, productJsonObject.getCustomerId(), null, null);
					productJsonModel.setImage(imageId);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while convert ProductJsonObject To Model");
			e.printStackTrace();
		}
		return productJsonModel;
	}
	
	public ProductJsonObject prepareConfigurableProductJsonObject(ProductJsonModel productJsonModel) {
		List<ProductAttributeSetModel> productAttributeSetModelList = new ArrayList<ProductAttributeSetModel>();
		List<ProductAttributeSetJsonObject> productAccessoriesJsonObjectList = new ArrayList<ProductAttributeSetJsonObject>();
		ProductJsonObject productJsonObject = new ProductJsonObject();
		productJsonObject.setId(productJsonModel.getId());
		productJsonObject.setCustomerId(productJsonModel.getCustomerId());
		productJsonObject.setCost(productJsonModel.getCost());
		productJsonObject.setDescription(productJsonModel.getDescription());
		productJsonObject.setProductCategory(productJsonModel.getProductCategory());
		productJsonObject.setProductName(productJsonModel.getProductName());
		productJsonObject.setProductType(productJsonModel.getProductType());
		productJsonObject.setQuantity(productJsonModel.getQuantity());
		productJsonObject.setSkuId(productJsonModel.getSkuId());
		try {
			if(productJsonModel.getImage() != null && !productJsonModel.getImage().isEmpty()) {
				ProductImageJsonModel productImageJsonModel = productDao.getProductImageById(productJsonModel.getImage());
				if(productImageJsonModel.isURL()) {
					productJsonObject.setImage(productImageJsonModel.getImage());
				} else {
					InputStream imageStream = SalesChannelUtility.convertImageIntoStream(productImageJsonModel.getActualPath());
					productJsonObject.setImage(imageStream.toString());
				}
			}
			if(productJsonModel != null) {
				List<ProductAttributesJsonModel> productAttributes = productDao.getProductAttributeByProductId(productJsonModel.getId());
				if(productAttributes != null && productAttributes.size() > 0) {
					for(ProductAttributesJsonModel productAttribute : productAttributes) {
						ProductAttributeSetModel productAttributeSetModel = new ProductAttributeSetModel();
						List<ProductAttributeSetJsonObject> productAttributeSetList = new ArrayList<ProductAttributeSetJsonObject>();
						List<ProductAttributeCombinationJsonModel> productAttributeCombinations = productDao.
								getProductAttributeCombinationByProductAttributId(productAttribute.getId());
						if(productAttributeCombinations != null && productAttributeCombinations.size() > 0) {
							for(ProductAttributeCombinationJsonModel productAttributeCombination : productAttributeCombinations) {
								AttributeJsonModel attribute = productDao.getAttributeById(productAttributeCombination.getAttributeId());
								ProductAttributeSetJsonObject productAttributeSet = new ProductAttributeSetJsonObject();
								productAttributeSet.setValueId(productAttributeCombination.getId());
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
						//Product Attributes Image
						List<ProductImageJsonModel> productAttributesImageJsonModelList = productDao.getProductImageByProductAttributeId(productAttribute.getId());
						if(productAttributesImageJsonModelList != null && productAttributesImageJsonModelList.size() > 0) {
							for(ProductImageJsonModel productImageJsonModel : productAttributesImageJsonModelList) {
								ProductAttributeSetJsonObject productAttributeSet = convertProductImageJsonModelToObject(productImageJsonModel);
								productAttributeSetList.add(productAttributeSet);
							}
						}
						productAttributeSetModel.setProductAttributeSetId(productAttribute.getId());
						productAttributeSetModel.setProductAttributeSet(productAttributeSetList);
						productAttributeSetModelList.add(productAttributeSetModel);
					}
					if(productAttributeSetModelList != null && productAttributeSetModelList.size() > 0) {
						productJsonObject.setProductAttributes(productAttributeSetModelList);
					} else {
						productJsonObject.setProductAttributes(null);
					}
				}
			
				List<ProductAccessoriesJsonModel> productAccessoriesList = productDao.getProductAccessoriesByProductId(productJsonModel.getId());
				if(productAccessoriesList != null && productAccessoriesList.size() > 0) {
					for(ProductAccessoriesJsonModel productAccessories : productAccessoriesList) {
						ProductAttributeSetJsonObject productAttributeSet = convertProductAccessoriesJsonModelToObject(productAccessories);
						productAccessoriesJsonObjectList.add(productAttributeSet);
					}
				}
				
				//Product Accessories Image
				List<ProductImageJsonModel> productImageJsonModelList = productDao.getProductImageByProductId(productJsonModel.getId());
				if(productImageJsonModelList != null && productImageJsonModelList.size() > 0) {
					for(ProductImageJsonModel productImageJsonModel : productImageJsonModelList) {
						if(productImageJsonModel.getProductAttributeId() == null || productImageJsonModel.getProductAttributeId().isEmpty()) {
							ProductAttributeSetJsonObject productAttributeSet = convertProductImageJsonModelToObject(productImageJsonModel);
							productAccessoriesJsonObjectList.add(productAttributeSet);
						}
					}
				}
				if(productAccessoriesJsonObjectList != null && productAccessoriesJsonObjectList.size() > 0) {
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
				productAccessoriesJsonModel.setId(productAttributeSetJsonObject.getValueId());
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
				productAttributeSetJsonObject.setValueId(productAccessoriesJsonModel.getId());
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
	
	public ProductAttributeSetJsonObject convertProductImageJsonModelToObject(ProductImageJsonModel productImageJsonModel) {
		ProductAttributeSetJsonObject productAttributeSetJsonObject = null;
		try {
			if(productImageJsonModel != null) {
				productAttributeSetJsonObject = new ProductAttributeSetJsonObject();
				productAttributeSetJsonObject.setValueId(productImageJsonModel.getId());
				productAttributeSetJsonObject.setName(productImageJsonModel.getImageName());
				if(productImageJsonModel.isURL()) {
					productAttributeSetJsonObject.setValue(productImageJsonModel.getImage());
				} else {
					InputStream imageStream = SalesChannelUtility.convertImageIntoStream(productImageJsonModel.getActualPath());
					productAttributeSetJsonObject.setValue(imageStream.toString());
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while create convert ProductImage JsonModel To Object");
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
				if(productJsonObject.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString())) {
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
						List<ProductAttributeCombinationJsonModel> productAttributeCombinations = prepareProductAttributeCombination(productJsonObject.getProductAttributes(), productId, productJsonObject.getCustomerId());
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

	public ProductJsonObject getProduct(String skuId, String customerId) {
		ProductJsonObject productJsonObject = null;
		try {
			ProductJsonModel productJsonModel = productDao.checkProductExist(skuId, customerId);
			if(productJsonModel != null) {
				if(productJsonModel.getProductType() != null 
						&& productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString())) {
					productJsonObject = convertProductSimpleJsonModelToObject(productJsonModel);	
				} else if(productJsonModel.getProductType() != null 
						&& productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Configurable.toString()).toString())) {
					productJsonObject = prepareConfigurableProductJsonObject(productJsonModel);	
				} 
			}
		} catch(Exception e) {
			LOGGERS.error("error while get Product");
			e.printStackTrace();
		}
		return productJsonObject;
	}
	
	public ProductJsonModel checkProductExist(String skuId, String customerId) {
		ProductJsonModel productJsonModel = null;
		try {
			productJsonModel = productDao.checkProductExist(skuId, customerId);
		} catch(Exception e) {
			LOGGERS.error("error while check Product Exist");
			e.printStackTrace();
		}
		return productJsonModel;
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
	
	public ProductAttributesJsonModel getProductAttributeById(String productAttributeId) {
		ProductAttributesJsonModel productAttribute = null;
		try {
			productAttribute = productDao.getProductAttributeById(productAttributeId);
		} catch (Exception e) {
			LOGGERS.error("error occured while get product attribute by id.");
			e.printStackTrace();
		}
		return productAttribute;
	}
	
	public boolean updateProduct(ProductJsonObject productJsonObject) {
		boolean status = false;
		try {
			if(productJsonObject != null) {
				ProductJsonModel productJsonModelOld = productDao.getProductById(productJsonObject.getId());
				productJsonModelOld.setUpdatedBy(productJsonObject.getCustomerId());
				productJsonModelOld.setUpdatedAt(new Date());
				if(productJsonObject.getProductType() != null && !productJsonObject.getProductType().isEmpty()
						&& productJsonModelOld.getProductType() != null && !productJsonModelOld.getProductType().isEmpty()) {
					if(productJsonObject.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString()) 
							&& productJsonModelOld.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString())) {
						productJsonModelOld.setCost(productJsonObject.getCost());
						productJsonModelOld.setDescription(productJsonObject.getDescription());
						productJsonModelOld.setProductCategory(productJsonObject.getProductCategory());
						productJsonModelOld.setProductName(productJsonObject.getProductName());
						productJsonModelOld.setQuantity(productJsonObject.getQuantity());
						if(productJsonObject.getImage() != null && !productJsonObject.getImage().isEmpty()
								&& productJsonModelOld.getImage() != null && !productJsonModelOld.getImage().isEmpty()) {
							ProductImageJsonModel imageOld = productDao.getProductImageById(productJsonModelOld.getImage());
							if(imageOld != null) {
								SalesChannelUtility.deleteImage(imageOld.getActualPath());
							}
							String imageId = null;
							if(imageOld != null) {
								imageId = saveUpdateImage(productJsonObject.getImage(), imageOld.getImageName(), imageOld.getId(), productJsonObject.getCustomerId(), imageOld.getProductId(), null);
							} else {
								imageId = saveUpdateImage(productJsonObject.getImage(), null, null, productJsonObject.getCustomerId(), productJsonObject.getId(), null);
							}			
							productJsonModelOld.setImage(imageId);
						}
						else if(productJsonObject.getImage() != null && !productJsonObject.getImage().isEmpty()) {
							String imageId = saveUpdateImage(productJsonObject.getImage(), null, null, productJsonObject.getCustomerId(), productJsonObject.getId(), null);
							productJsonModelOld.setImage(imageId);
						}
						//code to perform synced files
						status = productDao.updateProduct(productJsonModelOld);
						if(status) {
							/*List<ProductAccessoriesJsonModel> productAccessories = productDao.getProductAccessoriesByProductId(productJsonObject.getId());
							if(productAccessories != null && productAccessories.size() > 0) {
								status = productDao.deleteProductAccessoriesByProductId(productJsonObject.getId());
								if(status && productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
									for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
										insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
									}
								}
							} else {*/
								if(productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
									for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
										insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
									}
								}
							/*}*/
						}
					} 
					else if(productJsonObject.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString()) 
							&& !productJsonModelOld.getProductType().equals(ProductTypes.getByName(ProductTypes.Configurable.toString()).toString())) {
						status = deleteProductAttributes(productJsonObject.getId());
						if(status) {
							productJsonModelOld.setCost(productJsonObject.getCost());
							productJsonModelOld.setDescription(productJsonObject.getDescription());
							productJsonModelOld.setProductCategory(productJsonObject.getProductCategory());
							productJsonModelOld.setProductName(productJsonObject.getProductName());
							productJsonModelOld.setQuantity(productJsonObject.getQuantity());
							if(productJsonObject.getImage() != null && !productJsonObject.getImage().isEmpty()
									&& productJsonModelOld.getImage() != null && !productJsonModelOld.getImage().isEmpty()) {
								ProductImageJsonModel imageOld = productDao.getProductImageById(productJsonModelOld.getImage());
								if(imageOld != null) {
									SalesChannelUtility.deleteImage(imageOld.getActualPath());
								}
								String imageId = null;
								if(imageOld != null) {
									imageId = saveUpdateImage(productJsonObject.getImage(), imageOld.getImageName(), imageOld.getId(), productJsonObject.getCustomerId(), imageOld.getProductId(), null);
								} else {
									imageId = saveUpdateImage(productJsonObject.getImage(), null, null, productJsonObject.getCustomerId(), productJsonObject.getId(), null);
								}
								productJsonModelOld.setImage(imageId);
							}
							else if(productJsonObject.getImage() != null && !productJsonObject.getImage().isEmpty()) {
								String imageId = saveUpdateImage(productJsonObject.getImage(), null, null, productJsonObject.getCustomerId(), productJsonObject.getId(), null);
								productJsonModelOld.setImage(imageId);
							}
							//code to perform synced files
							status = productDao.updateProduct(productJsonModelOld);
							if(status) {
								/*List<ProductAccessoriesJsonModel> productAccessories = productDao.getProductAccessoriesByProductId(productJsonObject.getId());
								if(productAccessories != null && productAccessories.size() > 0) {
									status = productDao.deleteProductAccessoriesByProductId(productJsonObject.getId());
									if(status && productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
										for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
											insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
										}
									}
								} else {*/
									if(productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
										for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
											insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
										}
									}
								/*}*/
							}
						}
					}
					else if(productJsonObject.getProductType().equals(ProductTypes.getByName(ProductTypes.Configurable.toString()).toString()) 
							&& productJsonModelOld.getProductType().equals(ProductTypes.getByName(ProductTypes.Configurable.toString()).toString())) {
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
						if(productJsonObject.getImage() != null && !productJsonObject.getImage().isEmpty()
								&& productJsonModelOld.getImage() != null && !productJsonModelOld.getImage().isEmpty()) {
							ProductImageJsonModel imageOld = productDao.getProductImageById(productJsonModelOld.getImage());
							if(imageOld != null) {
								SalesChannelUtility.deleteImage(imageOld.getActualPath());
							}
							String imageId = null;
							if(imageOld != null) {
								imageId = saveUpdateImage(productJsonObject.getImage(), imageOld.getImageName(), imageOld.getId(), productJsonObject.getCustomerId(), imageOld.getProductId(), null);
							} else {
								imageId = saveUpdateImage(productJsonObject.getImage(), null, null, productJsonObject.getCustomerId(), productJsonObject.getId(), null);
							}
							productJsonModelOld.setImage(imageId);
						}
						else if(productJsonObject.getImage() != null && !productJsonObject.getImage().isEmpty()) {
							String imageId = saveUpdateImage(productJsonObject.getImage(), null, null, productJsonObject.getCustomerId(), productJsonObject.getId(), null);
							productJsonModelOld.setImage(imageId);
						}
						status = productDao.updateProduct(productJsonModelOld);
						//insert ProductAttributes
						if(status) {
							String productAttributeId = null;
							//delete all the product attributes and add the new one
							/*status = deleteProductAttributes(productJsonObject.getId());
							if(status) {*/
								List<ProductAttributesJsonModel> productAttributes = prepareProductAttributes(productJsonObject.getProductAttributes());
								if(productAttributes != null && productAttributes.size() > 0) {
									int i = 0;
									do {
										ProductAttributesJsonModel productAttribute = productAttributes.get(i); 
										productAttribute.setProductId(productJsonObject.getId());
										ProductAttributesJsonModel productAttributeExist = checkProductAttributeExist(
												productAttribute.getProductId(), productAttribute.getSkuId());
										if(productAttributeExist == null && (productAttribute.getId() == null || productAttribute.getId().isEmpty())) {
											productAttributeId = productDao.insertProductAttribute(productAttribute);
										} else {
											if(productAttribute.getCost() != null)
												productAttributeExist.setCost(productAttribute.getCost());
											if(productAttribute.getQuantity() != null)
												productAttributeExist.setQuantity(productAttribute.getQuantity());
											productDao.updateProductAttribute(productAttributeExist);
											productAttributeId = productAttribute.getId();
										}
										i++;
									} while(productAttributeId != null && productAttributes.size() > i);
								}
								//insert ProductAttributesCombinations
								if(productJsonObject.getId() != null && productAttributeId != null) {
									List<ProductAttributeCombinationJsonModel> productAttributeCombinations = prepareProductAttributeCombination(
											productJsonObject.getProductAttributes(), productJsonObject.getId(), productJsonObject.getCustomerId());
									if(productAttributeCombinations != null && productAttributeCombinations.size() > 0) {
										for(ProductAttributeCombinationJsonModel productAttributeCombination : productAttributeCombinations) {
											if(productAttributeCombination.getId() != null && !productAttributeCombination.getId().isEmpty()) {
												ProductAttributeCombinationJsonModel productAttributeCombinationExist = productDao.getProductAttributeCombinationById(productAttributeCombination.getId());
												if(productAttributeCombinationExist != null) {
													productDao.updateProductAttributeCombination(productAttributeCombination);
												} else {
													productDao.insertProductAttributeCombination(productAttributeCombination);
												}
											} else {
												productDao.insertProductAttributeCombination(productAttributeCombination);
											}
										}
									}
								}
								//delete and insert ProductAccessories
								/*List<ProductAccessoriesJsonModel> productAccessories = productDao.getProductAccessoriesByProductId(productJsonObject.getId());
								if(productAccessories != null && productAccessories.size() > 0) {
									status = productDao.deleteProductAccessoriesByProductId(productJsonObject.getId());
									if(status && productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
										for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
											insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
										}
									}
								} else {*/
									if(productJsonObject.getProductAccessories() != null && productJsonObject.getProductAccessories().size() > 0) {
										for(ProductAttributeSetJsonObject productAttributeSetJsonObject : productJsonObject.getProductAccessories()) {
											insertProductAccessories(productAttributeSetJsonObject, productJsonObject.getId(), productJsonObject.getCustomerId());
										}
									}
								/*}*/
							/*}*/
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
					//delete images of the product related to all tables  
					ProductJsonModel product = productDao.getProductById(productId);
					if(product != null && product.getImage() != null && !product.getImage().isEmpty()) {
						ProductImageJsonModel image = productDao.getProductImageById(product.getImage());
						if(image != null && image.getId() != null && !image.getId().isEmpty()) {
							if(SalesChannelUtility.deleteImage(image.getActualPath())) {
								productDao.deleteImageById(image.getId());
							}
						}
						List<ProductImageJsonModel> imageList = productDao.getProductImageByProductId(productId);
						if(imageList != null && imageList.size() > 0) {
							for(ProductImageJsonModel productImage : imageList) {
								if(SalesChannelUtility.deleteImage(productImage.getActualPath())) {
									productDao.deleteImageById(productImage.getId());	
								}
							}
						}
					}
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
					//delete images of the product related to all tables 
					ProductJsonModel product = productDao.getProductById(productId);
					if(product != null && product.getImage() != null && !product.getImage().isEmpty()) {
						ProductImageJsonModel image = productDao.getProductImageById(product.getImage());
						if(image != null && image.getId() != null && !image.getId().isEmpty()) {
							if(SalesChannelUtility.deleteImage(image.getActualPath())) {
								productDao.deleteImageById(image.getId());
							}
						}
						List<ProductImageJsonModel> imageList = productDao.getProductImageByProductId(productId);
						if(imageList != null && imageList.size() > 0) {
							for(ProductImageJsonModel productImage : imageList) {
								if(SalesChannelUtility.deleteImage(productImage.getActualPath())) {
									productDao.deleteImageById(productImage.getId());	
								}
							}
						}
					}
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
			if(status) {
				List<ProductImageJsonModel> imageList = productDao.getProductImageByProductId(productId);
				if(imageList != null && imageList.size() > 0) {
					for(ProductImageJsonModel image : imageList) {
						if(image.getProductAttributeId() != null && !image.getProductAttributeId().isEmpty()) {
							if(SalesChannelUtility.deleteImage(image.getActualPath())) {
								productDao.deleteImageById(image.getId());
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGERS.error("error while delete product attributes.");
		}
		return status;
	}
	
	public boolean deleteProductAttributesByProductAttributeId(String productAttributeId) {
		boolean status = false;
		try {
			ProductAttributesJsonModel productAttribute = productDao.getProductAttributeById(productAttributeId);
			if(productAttribute != null) {
				status = productDao.deleteProductAttributeCombinationByProductAttributId(productAttributeId);
			}
			if(status) {
				status = productDao.deleteProductAttribute(productAttributeId);
			}
			if(status) {
				List<ProductImageJsonModel> imageList = productDao.getProductImageByProductAttributeId(productAttributeId);
				if(imageList != null && imageList.size() > 0) {
					for(ProductImageJsonModel image : imageList) {
						if(SalesChannelUtility.deleteImage(image.getActualPath())) {
							productDao.deleteImageById(image.getId());
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGERS.error("error while delete product attributes by productAttributeId.");
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
					productJsonObject = prepareConfigurableProductJsonObject(productJsonModel);	
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
						&& productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString())) {
					productJsonObject = convertProductSimpleJsonModelToObject(productJsonModel);	
				} else if(productJsonModel.getProductType() != null 
						&& productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Configurable.toString()).toString())) {
					productJsonObject = prepareConfigurableProductJsonObject(productJsonModel);	
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
								&& productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Simple.toString()).toString())) {
							ProductJsonObject productJsonObject = convertProductSimpleJsonModelToObject(productJsonModel);
							productJsonObjectList.add(productJsonObject);
						} else if(productJsonModel.getProductType() != null 
								&& productJsonModel.getProductType().equals(ProductTypes.getByName(ProductTypes.Configurable.toString()).toString())) {
							ProductJsonObject productJsonObject = prepareConfigurableProductJsonObject(productJsonModel);
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
	
	public String insertUpdateProductAttributes(ProductAttributesJsonObject productAttributesJsonObject, String productId) {
		boolean status = false;
		boolean isInsert = false;
		try {
			List<AttributeJsonModel> attributeJsonModelList = prepareAttributesFromProductAttributeSetJsonObject(productAttributesJsonObject.getProductAttributes());
			for(AttributeJsonModel attribute : attributeJsonModelList) {
				AttributeJsonModel attributeExist = productDao.checkAttributeExist(attribute.getName());
				if(attributeExist == null) {
					productDao.insertAttribute(attribute);
				}
			}
			//insert/update ProductAttribute
			ProductAttributesJsonModel productAttributesJsonModel = productDao.getProductAttributeById(productAttributesJsonObject.getProductAttributeId());
			ProductAttributesJsonModel productAttribute = new ProductAttributesJsonModel();
			for(ProductAttributeSetJsonObject productAttributeSet : productAttributesJsonObject.getProductAttributes()) {
				if(productAttributesJsonModel != null) {
					if(productAttributeSet.getName().equals(SalesChannelConstants.SKU_ID))
						productAttributesJsonModel.setSkuId(productAttributeSet.getValue());
					if(productAttributeSet.getName().equals(SalesChannelConstants.QUANTITY))
						productAttributesJsonModel.setQuantity(Integer.parseInt(productAttributeSet.getValue()));
					else if(productAttributeSet.getName().equals(SalesChannelConstants.COST))
						productAttributesJsonModel.setCost(Integer.parseInt(productAttributeSet.getValue()));	
				} else {
						if(productAttributeSet.getName().equals(SalesChannelConstants.SKU_ID))
							productAttribute.setSkuId(productAttributeSet.getValue());
						if(productAttributeSet.getName().equals(SalesChannelConstants.QUANTITY))
							productAttribute.setQuantity(Integer.parseInt(productAttributeSet.getValue()));
						else if(productAttributeSet.getName().equals(SalesChannelConstants.COST))
							productAttribute.setCost(Integer.parseInt(productAttributeSet.getValue()));
				}
			}	
			if(productAttributesJsonObject.getProductAttributeId() == null || productAttributesJsonObject.getProductAttributeId().isEmpty()) {
				productAttribute.setProductId(productId);
				String id = productDao.insertProductAttribute(productAttribute);
				productAttributesJsonObject.setProductAttributeId(id);
				status = true;
				isInsert = true;
			} else {
				status = productDao.updateProductAttribute(productAttributesJsonModel);
			}
			//insert/update ProductAttributesCombinations
			if(status) {
				for(ProductAttributeSetJsonObject productAttributeSet : productAttributesJsonObject.getProductAttributes()) {
					ProductAttributeCombinationJsonModel productAttributeCombination = new ProductAttributeCombinationJsonModel();
					if(!productAttributeSet.getName().equals(SalesChannelConstants.SKU_ID)
							&& !productAttributeSet.getName().equals(SalesChannelConstants.QUANTITY)
							&& !productAttributeSet.getName().equals(SalesChannelConstants.COST)
							&& !productAttributeSet.getName().contains("image")) {
						AttributeJsonModel attributeId = productDao.checkAttributeExist(productAttributeSet.getName());
						if(productAttributesJsonObject.getProductAttributeId() != null && attributeId != null) {
							if(isInsert){
								productAttributeCombination.setId(null);								
							} else {
								productAttributeCombination.setId(productAttributeSet.getValueId());
							}
							productAttributeCombination.setAttributeId(attributeId.getId());
							productAttributeCombination.setProductId(productId);
							productAttributeCombination.setProductAttributeId(productAttributesJsonObject.getProductAttributeId());
							productAttributeCombination.setValue(productAttributeSet.getValue());
							if(productAttributeCombination.getId() != null && !productAttributeCombination.getId().isEmpty()) {
								productDao.updateProductAttributeCombination(productAttributeCombination);
							} else {
								productDao.insertProductAttributeCombination(productAttributeCombination);
							}
						}
					} else if(productAttributesJsonObject.getProductAttributeId() != null && productAttributeSet.getName().contains("image")) {
						if(productAttributeSet.getValue() != null && !productAttributeSet.getValue().isEmpty()) {
							if(isInsert) {
								productAttributeSet.setValueId(null);
							}
							saveUpdateImage(productAttributeSet.getValue(), productAttributeSet.getName(), productAttributeSet.getValueId(), productAttributesJsonObject.getCustomerId(), productId, productAttributesJsonObject.getProductAttributeId());
						}
					}
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(status) {
			return productAttributesJsonObject.getProductAttributeId();	
		} else {
			return null;
		}
	}
	
	public List<AttributeJsonModel> prepareAttributesFromProductAttributeSetJsonObject(List<ProductAttributeSetJsonObject> productAttributeSetModelList) {
		List<AttributeJsonModel> attributes = null;
		try {
			if(productAttributeSetModelList != null && productAttributeSetModelList.size() > 0) {
				attributes = new ArrayList<AttributeJsonModel>();
				for(ProductAttributeSetJsonObject productAttributeSet : productAttributeSetModelList) {
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		return attributes;
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
					productAttribute.setId(productAttributeSetModel.getProductAttributeSetId());
					productAttributes.add(productAttribute);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productAttributes;
	}
	
	public List<ProductAttributeCombinationJsonModel> prepareProductAttributeCombination(List<ProductAttributeSetModel> productAttributeSetModelList,
			String productId, String customerId) {
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
								&& !productAttributeSet.getName().equals(SalesChannelConstants.COST)
								&& !productAttributeSet.getName().contains("image")) {
							attributeId = productDao.checkAttributeExist(productAttributeSet.getName());
							if(productAttributeId != null && attributeId != null) {
								productAttributeCombination.setId(productAttributeSet.getValueId());
								productAttributeCombination.setAttributeId(attributeId.getId());
								productAttributeCombination.setProductAttributeId(productAttributeId.getId());
								productAttributeCombination.setValue(productAttributeSet.getValue());
								productAttributeCombinations.add(productAttributeCombination);
							}
						} else if(productAttributeId != null && productAttributeSet.getName().contains("image")) {
							if(productAttributeSet.getValue() != null && !productAttributeSet.getValue().isEmpty()) {
								saveUpdateImage(productAttributeSet.getValue(), productAttributeSet.getName(), productAttributeSet.getValueId(), customerId, productId, productAttributeId.getId());
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
				if(productAttributeSetJsonObject.getValue() != null && !productAttributeSetJsonObject.getValue().isEmpty()) {
					if(productAttributeSetJsonObject.getValueId() != null && !productAttributeSetJsonObject.getValueId().isEmpty()) {
						ProductImageJsonModel imageOld = productDao.getProductImageById(productAttributeSetJsonObject.getValueId());
						if(imageOld != null) {
							SalesChannelUtility.deleteImage(imageOld.getActualPath());
						}
					}
					productAccessoriesId = saveUpdateImage(productAttributeSetJsonObject.getValue(), productAttributeSetJsonObject.getName(), productAttributeSetJsonObject.getValueId(), customerId, productId, null);
				}
			} else {
				ProductAccessoriesJsonModel productAccessoriesJsonModel = convertProductAccessoriesJsonObjectToModel(productAttributeSetJsonObject);
				productAccessoriesJsonModel.setProductId(productId);
				if(productAccessoriesJsonModel.getId() != null && !productAccessoriesJsonModel.getId().isEmpty()) {
					ProductAccessoriesJsonModel productAccessoriesExist = productDao.getProductAccessoriesById(productAccessoriesJsonModel.getId());
					if(productAccessoriesExist != null) {
						productDao.updateProductAccessories(productAccessoriesJsonModel);		
					} else {
						productDao.insertProductAccessories(productAccessoriesJsonModel);
					}
				} else {
					productDao.insertProductAccessories(productAccessoriesJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while insert product accessories");
			e.printStackTrace();
		}
		return productAccessoriesId;
	}
	
	public String saveUpdateImage(String image, String imageName, String id, String customerId, String productId, String productAttributeId) {
		String imageId = null;
		try {
			if(image != null) {
				ProductImageJsonModel productImageJsonModel = new ProductImageJsonModel();
				productImageJsonModel.setId(id);
				productImageJsonModel.setCreateBy(customerId);
				productImageJsonModel.setCreatedAt(new Date());
				productImageJsonModel.setProductId(productId);
				productImageJsonModel.setImageName(imageName);
				productImageJsonModel.setProductAttributeId(productAttributeId);
				String imagePath = null;
				String actualPath = null;
				if(image.contains("http")) {
					imagePath = SalesChannelBaseDao.saveImagePath + SalesChannelConstants.FILE_SEPERATOR + customerId;
					actualPath = SalesChannelUtility.getImagefromUrl(image.trim(), imagePath);
					productImageJsonModel.setImage(image);
					productImageJsonModel.setImagePath(imagePath);
					productImageJsonModel.setActualPath(actualPath);
					productImageJsonModel.setURL(true);
				} else {
					imagePath = SalesChannelBaseDao.saveImagePath + SalesChannelConstants.FILE_SEPERATOR + customerId; 
					actualPath = SalesChannelUtility.createImagefromStream(image.trim(), imagePath);
					productImageJsonModel.setImagePath(imagePath);
					productImageJsonModel.setActualPath(actualPath);
					productImageJsonModel.setURL(false);
				}
				if(id != null && !id.isEmpty()) {
					ProductImageJsonModel productImageExist = productDao.getProductImageById(id);
					if(productImageExist != null) {
						productDao.updateProductImage(productImageJsonModel);
						imageId = id;
					} else {
						imageId = productDao.insertProductImage(productImageJsonModel);
					}
				} else {
					imageId = productDao.insertProductImage(productImageJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while save image");
			e.printStackTrace();
		}
		return imageId;
	}
	
	public List<ProductAttributesJsonObject> getProductAttributesByProductId(String productId) {
		List<ProductAttributesJsonObject> productAttributesJsonObject = null;
		try {
			List<ProductAttributesJsonModel> productAttributes = productDao.getProductAttributeByProductId(productId);
			if(productAttributes != null && productAttributes.size() > 0) {
				productAttributesJsonObject = new ArrayList<ProductAttributesJsonObject>();
				for(ProductAttributesJsonModel productAttribute : productAttributes) {
					ProductAttributesJsonObject productAttributeSetModel = new ProductAttributesJsonObject();
					List<ProductAttributeSetJsonObject> productAttributeSetList = new ArrayList<ProductAttributeSetJsonObject>();
					List<ProductAttributeCombinationJsonModel> productAttributeCombinations = productDao.
							getProductAttributeCombinationByProductAttributId(productAttribute.getId());
					if(productAttributeCombinations != null && productAttributeCombinations.size() > 0) {
						for(ProductAttributeCombinationJsonModel productAttributeCombination : productAttributeCombinations) {
							AttributeJsonModel attribute = productDao.getAttributeById(productAttributeCombination.getAttributeId());
							ProductAttributeSetJsonObject productAttributeSet = new ProductAttributeSetJsonObject();
							productAttributeSet.setValueId(productAttributeCombination.getId());
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
					//Product Attributes Image
					List<ProductImageJsonModel> productAttributesImageJsonModelList = productDao.getProductImageByProductAttributeId(productAttribute.getId());
					if(productAttributesImageJsonModelList != null && productAttributesImageJsonModelList.size() > 0) {
						for(ProductImageJsonModel productImageJsonModel : productAttributesImageJsonModelList) {
							ProductAttributeSetJsonObject productAttributeSet = convertProductImageJsonModelToObject(productImageJsonModel);
							productAttributeSetList.add(productAttributeSet);
						}
					}
					productAttributeSetModel.setProductAttributeId(productAttribute.getId());
					productAttributeSetModel.setProductAttributes(productAttributeSetList);
					productAttributesJsonObject.add(productAttributeSetModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get ProductAttributes By ProductId");
			e.printStackTrace();
		}
		return productAttributesJsonObject;
	}
	
	public ProductAttributesJsonObject getProductAttributesByProductAttributeId(String productAttributeId) {
		ProductAttributesJsonObject productAttributesJsonObject = null;
		try {
			ProductAttributesJsonModel productAttribute = productDao.getProductAttributeById(productAttributeId);
			if(productAttribute != null) {
				productAttributesJsonObject = new ProductAttributesJsonObject();
				List<ProductAttributeSetJsonObject> productAttributeSetList = new ArrayList<ProductAttributeSetJsonObject>();
				List<ProductAttributeCombinationJsonModel> productAttributeCombinations = productDao.
						getProductAttributeCombinationByProductAttributId(productAttribute.getId());
				if(productAttributeCombinations != null && productAttributeCombinations.size() > 0) {
					for(ProductAttributeCombinationJsonModel productAttributeCombination : productAttributeCombinations) {
						AttributeJsonModel attribute = productDao.getAttributeById(productAttributeCombination.getAttributeId());
						ProductAttributeSetJsonObject productAttributeSet = new ProductAttributeSetJsonObject();
						productAttributeSet.setValueId(productAttributeCombination.getId());
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
				//Product Attributes Image
				List<ProductImageJsonModel> productAttributesImageJsonModelList = productDao.getProductImageByProductAttributeId(productAttribute.getId());
				if(productAttributesImageJsonModelList != null && productAttributesImageJsonModelList.size() > 0) {
					for(ProductImageJsonModel productImageJsonModel : productAttributesImageJsonModelList) {
						ProductAttributeSetJsonObject productAttributeSet = convertProductImageJsonModelToObject(productImageJsonModel);
						productAttributeSetList.add(productAttributeSet);
					}
				}
				productAttributesJsonObject.setProductAttributeId(productAttribute.getId());
				productAttributesJsonObject.setProductAttributes(productAttributeSetList);
			}
		} catch(Exception e) {
			LOGGERS.error("error while get ProductAttributes By ProductAttributeId");
			e.printStackTrace();
		}
		return productAttributesJsonObject;
	}
	
	public ProductAccessoriesJsonObject getProductAccessoriesByProductId(String productId) {
		ProductAccessoriesJsonObject productAccessoriesJsonObjectList = null;
		try {
			List<ProductAccessoriesJsonModel> productAccessoriesList = productDao.getProductAccessoriesByProductId(productId);
			productAccessoriesJsonObjectList = new ProductAccessoriesJsonObject();
			List<ProductAttributeSetJsonObject> productAttributeSetList = new ArrayList<ProductAttributeSetJsonObject>();
			if(productAccessoriesList != null && productAccessoriesList.size() > 0) {
				for(ProductAccessoriesJsonModel productAccessories : productAccessoriesList) {
					ProductAttributeSetJsonObject productAttributeSet = convertProductAccessoriesJsonModelToObject(productAccessories);
					productAttributeSetList.add(productAttributeSet);
				}
				productAccessoriesJsonObjectList.setProductAccessories(productAttributeSetList);
			}
			
			//Product Accessories Image
			List<ProductImageJsonModel> productImageJsonModelList = productDao.getProductImageByProductId(productId);
			if(productImageJsonModelList != null && productImageJsonModelList.size() > 0) {
				for(ProductImageJsonModel productImageJsonModel : productImageJsonModelList) {
					ProductAttributeSetJsonObject productAttributeSet = convertProductImageJsonModelToObject(productImageJsonModel);
					productAttributeSetList.add(productAttributeSet);
				}
				productAccessoriesJsonObjectList.setProductAccessories(productAttributeSetList);
			}
		} catch(Exception e) {
			LOGGERS.error("error while get ProductAccessories By ProductId");
			e.printStackTrace();
		}
		return productAccessoriesJsonObjectList;
	}
	
	public ProductAttributeSetJsonObject getProductAccessoriesByProductAccessoriesId(String productAccessoriesId) {
		ProductAttributeSetJsonObject productAttributeSetJsonObject = null;
		try {
			ProductAccessoriesJsonModel productAccessoriesJsonModel = productDao.getProductAccessoriesById(productAccessoriesId);
			if(productAccessoriesJsonModel != null) {
				productAttributeSetJsonObject = new ProductAttributeSetJsonObject();
				productAttributeSetJsonObject.setValueId(productAccessoriesJsonModel.getId());
				productAttributeSetJsonObject.setName(productAccessoriesJsonModel.getName());
				productAttributeSetJsonObject.setValue(productAccessoriesJsonModel.getValue());
				productAttributeSetJsonObject.setDescription(productAccessoriesJsonModel.getDescription());
			}
		} catch(Exception e) {
			LOGGERS.error("error while get ProductAccessories By ProductAccessoriesId");
			e.printStackTrace();
		}
		return productAttributeSetJsonObject;
	}
	
	public boolean deleteProductsAccessoriesByProductId(String productId) {
		boolean status = false;
		try {
			List<ProductAccessoriesJsonModel> productAccessoriesJsonModelList = productDao.getProductAccessoriesByProductId(productId);
			if(productAccessoriesJsonModelList != null && productAccessoriesJsonModelList.size() > 0) {
				status = productDao.deleteProductAccessoriesByProductId(productId);
			}
			if(!status) {
				List<ProductImageJsonModel> imageList = productDao.getProductImageByProductId(productId);
				if(imageList != null && imageList.size() > 0) {
					for(ProductImageJsonModel image : imageList) {
						if(image.getProductAttributeId() == null || image.getProductAttributeId().isEmpty()) {
							if(SalesChannelUtility.deleteImage(image.getActualPath())) {
								productDao.deleteImageById(image.getId());
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGERS.error("error while delete product Accessories By ProductId.");
		}
		return status;
	}
	
	public boolean deleteProductsAccessoriesByProductAccessoriesId(String productAccessoriesId) {
		boolean status = false;
		try {
			ProductAccessoriesJsonModel productAccessoriesJsonModel = productDao.getProductAccessoriesById(productAccessoriesId);
			if(productAccessoriesJsonModel != null) {
				status = productDao.deleteProductAccessoriesById(productAccessoriesId);
			} else {
				ProductImageJsonModel image = productDao.getProductImageById(productAccessoriesId);
				if(image != null) {
					if(SalesChannelUtility.deleteImage(image.getActualPath())) {
						status = productDao.deleteImageById(productAccessoriesId);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGERS.error("error while delete product Accessories By productAccessoriesId.");
		}
		return status;
	}
	
	public ProductImageJsonModel getProductImageById(String imageId) {
		ProductImageJsonModel productImageJsonModel = null;
		try {
			productImageJsonModel = productDao.getProductImageById(imageId);
		} catch(Exception e) {
			LOGGERS.error("error while get ProductAccessories By ProductAccessoriesId");
			e.printStackTrace();
		}
		return productImageJsonModel;
	}
	
	public ProductDaoImpl getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

}
