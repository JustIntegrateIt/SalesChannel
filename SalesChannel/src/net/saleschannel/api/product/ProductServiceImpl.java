package net.saleschannel.api.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.saleschannel.api.constants.ProductTypes;
import net.saleschannel.api.constants.SalesChannelConstants;

import org.apache.log4j.Logger;

public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGERS = Logger.getLogger(ProductServiceImpl.class);
	
	private ProductDaoImpl productDao;
	
	public String insertProduct(ProductJsonModel productJsonModel) {
		String productId = null;
		String productAttributeId = null;
		productJsonModel.setSync(false);
		productJsonModel.setCreateBy(productJsonModel.getCustomerId());
		productJsonModel.setCreatedAt(new Date());
		try {
			if(productJsonModel.getProductType().equals(ProductTypes.Simple)) {
				productJsonModel.setProductAttributes(null);
				productId = productDao.insertProduct(productJsonModel);
			} else {
				//insert attributes if not exist in database
				List<ProductAttributeSetModel> productAttributeSetModelList = new ArrayList<ProductAttributeSetModel>();
				productAttributeSetModelList = productJsonModel.getProductAttributes();
				List<Attribute> attributes = prepareAttributes(productJsonModel.getProductAttributes()); 
				for(Attribute attribute : attributes) {
					Attribute attributeExist = productDao.checkAttributeExist(attribute.getName());
					if(attributeExist == null) {
						attribute.setCreatedAt(new Date());
						attribute.setCreatedBy(productJsonModel.getCustomerId());
						productDao.insertAttribute(attribute);
					}
				}
				//insert product
				productJsonModel.setProductAttributes(null);
				productId = productDao.insertProduct(productJsonModel);
				productJsonModel.setProductAttributes(productAttributeSetModelList);
				//insert ProductAttributes
				if(productId != null) {
					List<ProductAttributes> productAttributes = prepareProductAttributes(productJsonModel.getProductAttributes());
					if(productAttributes != null && productAttributes.size() > 0) {
						int i = 0;
						do {
							ProductAttributes productAttribute = productAttributes.get(i); 
							productAttribute.setProductId(productId);
							ProductAttributes productAttributeExist = checkProductAttributeExist(
									productAttribute.getProductId(), productAttribute.getSkuId());
							if(productAttributeExist == null) {
								productAttribute.setCreatedBy(productJsonModel.getCustomerId());
								productAttribute.setCreatedAt(new Date());
								productAttributeId = productDao.insertProductAttribute(productAttribute);
							}
							i++;
						} while(productAttributeId != null && productAttributes.size() > i);
					}
				}
				//insert ProductAttributesCombinations
				if(productId != null && productAttributeId != null) {
					List<ProductAttributeCombination> productAttributeCombinations = prepareProductAttributeCombination(productJsonModel.getProductAttributes(), productId);
					for(ProductAttributeCombination productAttributeCombination : productAttributeCombinations) {
						productAttributeCombination.setCreateBy(productJsonModel.getCustomerId());
						productAttributeCombination.setCreatedAt(new Date());
						productDao.insertProductAttributeCombination(productAttributeCombination);
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while perform insert product.");
			e.printStackTrace();
		}
		return productId;
	}

	public ProductJsonModel checkProductExist(String skuId, String customerId) {
		ProductJsonModel productJsonModel = null;
		try {
			productJsonModel = productDao.checkProductExist(skuId, customerId);
			if(productJsonModel != null) {
				if(productJsonModel.getProductType() != null 
						&& !productJsonModel.getProductType().equals(ProductTypes.Simple))
					prepareProductJsonModel(productJsonModel);				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productJsonModel;
	}
	
	public ProductAttributes checkProductAttributeExist(String productId, String skuId) {
		ProductAttributes productAttributeExist = null;
		try {
			productAttributeExist = productDao.checkProductAttributeExist(productId,	skuId);
		} catch (Exception e) {
			LOGGERS.error("error occured while check product attribute exist.");
			e.printStackTrace();
		}
		return productAttributeExist;
	}
	
	public boolean updateProduct(ProductJsonModel productJsonModelNew) {
		boolean status = false;
		try {
			ProductJsonModel productJsonModelOld = getProductById(productJsonModelNew.getId());
			productJsonModelOld.setUpdatedBy(productJsonModelNew.getCustomerId());
			productJsonModelOld.setUpdatedAt(new Date());
			if(productJsonModelNew.getProductType().equals(ProductTypes.Simple) 
					&& productJsonModelOld.getProductType().equals(ProductTypes.Simple)) {
				productJsonModelOld.setProductAttributes(null);
				productJsonModelOld.setCost(productJsonModelNew.getCost());
				productJsonModelOld.setDescription(productJsonModelNew.getDescription());
				productJsonModelOld.setProductCategory(productJsonModelNew.getProductCategory());
				productJsonModelOld.setProductName(productJsonModelNew.getProductName());
				productJsonModelOld.setQuantity(productJsonModelNew.getQuantity());
				//code to perform synced files
				status = productDao.updateProduct(productJsonModelOld);
			} 
			else if(productJsonModelNew.getProductType().equals(ProductTypes.Simple) 
					&& !productJsonModelOld.getProductType().equals(ProductTypes.Configurable)) {
				status = deleteProductAttributes(productJsonModelNew.getId());
				if(status) {
					productJsonModelOld.setProductAttributes(null);
					productJsonModelOld.setCost(productJsonModelNew.getCost());
					productJsonModelOld.setDescription(productJsonModelNew.getDescription());
					productJsonModelOld.setProductCategory(productJsonModelNew.getProductCategory());
					productJsonModelOld.setProductName(productJsonModelNew.getProductName());
					productJsonModelOld.setQuantity(productJsonModelNew.getQuantity());
					//code to perform synced files
					status = productDao.updateProduct(productJsonModelOld);
				}
			}
			else if(productJsonModelNew.getProductType().equals(ProductTypes.Configurable) 
					&& productJsonModelOld.getProductType().equals(ProductTypes.Configurable)) {
				//insert attributes if not exist in database
				List<Attribute> attributes = prepareAttributes(productJsonModelNew.getProductAttributes()); 
				for(Attribute attribute : attributes) {
					Attribute attributeExist = productDao.checkAttributeExist(attribute.getName());
					if(attributeExist == null) {
						attribute.setCreatedBy(productJsonModelNew.getCustomerId());
						attribute.setCreatedAt(new Date());
						attribute.setUpdatedBy(productJsonModelNew.getCustomerId());
						attribute.setUpdatedAt(new Date());
						productDao.insertAttribute(attribute);
					}
				}
				//insert product
				productJsonModelOld.setProductAttributes(null);
				productJsonModelOld.setCost(productJsonModelNew.getCost());
				productJsonModelOld.setDescription(productJsonModelNew.getDescription());
				productJsonModelOld.setProductCategory(productJsonModelNew.getProductCategory());
				productJsonModelOld.setProductName(productJsonModelNew.getProductName());
				productJsonModelOld.setQuantity(productJsonModelNew.getQuantity());
				status = productDao.updateProduct(productJsonModelOld);
				//insert ProductAttributes
				if(status) {
					String productAttributeId = null;
					//delete all the product attributes and add the new one
					status = deleteProductAttributes(productJsonModelNew.getId());
					if(status) {
						List<ProductAttributes> productAttributes = prepareProductAttributes(productJsonModelNew.getProductAttributes());
						if(productAttributes != null && productAttributes.size() > 0) {
							int i = 0;
							do {
								ProductAttributes productAttribute = productAttributes.get(i); 
								productAttribute.setProductId(productJsonModelNew.getId());
								ProductAttributes productAttributeExist = checkProductAttributeExist(
										productAttribute.getProductId(), productAttribute.getSkuId());
								if(productAttributeExist == null) {
									productAttribute.setCreatedBy(productJsonModelNew.getCustomerId());
									productAttribute.setCreatedAt(new Date());
									productAttribute.setUpdatedBy(productJsonModelNew.getCustomerId());
									productAttribute.setUpdatedAt(new Date());
									productAttributeId = productDao.insertProductAttribute(productAttribute);
								}
								i++;
							} while(productAttributeId != null && productAttributes.size() > i);
						}
						//insert ProductAttributesCombinations
						if(productJsonModelNew.getId() != null && productAttributeId != null) {
							List<ProductAttributeCombination> productAttributeCombinations = prepareProductAttributeCombination(
									productJsonModelNew.getProductAttributes(), productJsonModelNew.getId());
							for(ProductAttributeCombination productAttributeCombination : productAttributeCombinations) {
								productAttributeCombination.setCreateBy(productJsonModelNew.getCustomerId());
								productAttributeCombination.setCreatedAt(new Date());
								productAttributeCombination.setUpdatedBy(productJsonModelNew.getCustomerId());
								productAttributeCombination.setUpdatedAt(new Date());
								productDao.insertProductAttributeCombination(productAttributeCombination);
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

	public boolean deleteProduct(ProductJsonModel productJsonModel) {
		String productId = productJsonModel.getId();
		boolean status = false;
		try {
			status = deleteProductAttributes(productId);
			if(status) {
				status = productDao.deleteProduct(productId);
			}
		} catch(Exception e) {
			e.printStackTrace();
			LOGGERS.error("error while delete product.");
		}
		return status;
	}
	
	public boolean deleteProductAttributes(String productId) {
		boolean status = false;
		try {
			List<ProductAttributes> productAttributesList = productDao.getProductAttributeByProductId(productId);
			if(productAttributesList != null && productAttributesList.size() > 0) {
				for(ProductAttributes productAttributes : productAttributesList) {
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
					status = deleteProduct(productJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error delete products");
			e.printStackTrace();
		}
		return status;
	}

	public ProductJsonModel getProductById(String productId) {
		ProductJsonModel productJsonModel = null;
		try {
			productJsonModel = productDao.getProductById(productId);
			if(productJsonModel != null) {
				if(productJsonModel.getProductType() != null 
						&& !productJsonModel.getProductType().equals(ProductTypes.Simple))
					prepareProductJsonModel(productJsonModel);				
			}
		} catch (Exception e) {
			LOGGERS.error("error occured while check product attribute exist.");
			e.printStackTrace();
		}
		return productJsonModel;
	}
	
	public ProductJsonModel getProductBySkuId(String skuId) {
		ProductJsonModel productJsonModel = null;
		try {
			productJsonModel = productDao.getProductById(skuId);
			if(productJsonModel != null) {
				if(productJsonModel.getProductType() != null 
						&& !productJsonModel.getProductType().equals(ProductTypes.Simple))
					prepareProductJsonModel(productJsonModel);				
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get product by skuId.");
			e.printStackTrace();
		}
		return productJsonModel;
	}

	public List<ProductJsonModel> getProductsByCustomer(String customerId) {
		List<ProductJsonModel> products = null;
		try {
			products = productDao.getProductsByCustomer(customerId);
			for(ProductJsonModel productJsonModel : products) {
				if(productJsonModel != null) {
					if(productJsonModel.getProductType() != null 
							&& !productJsonModel.getProductType().equals(ProductTypes.Simple))
						prepareProductJsonModel(productJsonModel);				
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get product by customer.");
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Attribute> prepareAttributes(List<ProductAttributeSetModel> productAttributeSetModelList) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		try {
			if(productAttributeSetModelList != null && productAttributeSetModelList.size() > 0) {
				for (ProductAttributeSetModel productAttributeSetModel : productAttributeSetModelList) {
					for(ProductAttributeSet productAttributeSet : productAttributeSetModel.getProductAttributeSet()) {
						Attribute attribute = new Attribute();
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

	public List<ProductAttributes> prepareProductAttributes(List<ProductAttributeSetModel> productAttributeSetModelList) {
		List<ProductAttributes> productAttributes = new ArrayList<ProductAttributes>();
		try {
			if(productAttributeSetModelList != null && productAttributeSetModelList.size() > 0) {
				for (ProductAttributeSetModel productAttributeSetModel : productAttributeSetModelList) {
					ProductAttributes productAttribute = new ProductAttributes();
					for(ProductAttributeSet productAttributeSet : productAttributeSetModel.getProductAttributeSet()) {
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
	
	public List<ProductAttributeCombination> prepareProductAttributeCombination(List<ProductAttributeSetModel> productAttributeSetModelList,
			String productId) {
		List<ProductAttributeCombination> productAttributeCombinations = new ArrayList<ProductAttributeCombination>();
		try {
			if(productAttributeSetModelList != null && productAttributeSetModelList.size() > 0) {
				for (ProductAttributeSetModel productAttributeSetModel : productAttributeSetModelList) {
					ProductAttributes productAttributeId = null;
					Attribute attributeId = null;
					for(ProductAttributeSet productAttributeSet : productAttributeSetModel.getProductAttributeSet()) {
						if(productAttributeSet.getName().equals(SalesChannelConstants.SKU_ID)) {
							productAttributeId = productDao.checkProductAttributeExist(productId, 
									productAttributeSet.getValue());
						}
					}
					for(ProductAttributeSet productAttributeSet : productAttributeSetModel.getProductAttributeSet()) {
						ProductAttributeCombination productAttributeCombination = new ProductAttributeCombination();
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
	
	public ProductJsonModel prepareProductJsonModel(ProductJsonModel productJsonModel) {
		List<ProductAttributeSetModel> productAttributeSetModelList = new ArrayList<ProductAttributeSetModel>();
		try {
			if(productJsonModel != null) {
				List<ProductAttributes> productAttributes = productDao.getProductAttributeByProductId(productJsonModel.getId());
				if(productAttributes != null && productAttributes.size() > 0) {
					for(ProductAttributes productAttribute : productAttributes) {
						ProductAttributeSetModel productAttributeSetModel = new ProductAttributeSetModel();
						List<ProductAttributeSet> productAttributeSetList = new ArrayList<ProductAttributeSet>();
						List<ProductAttributeCombination> productAttributeCombinations = productDao.
								getProductAttributeCombinationByProductAttributId(productAttribute.getId());
						if(productAttributeCombinations != null && productAttributeCombinations.size() > 0) {
							for(ProductAttributeCombination productAttributeCombination : productAttributeCombinations) {
								Attribute attribute = productDao.getAttributeById(productAttributeCombination.getAttributeId());
								ProductAttributeSet productAttributeSet = new ProductAttributeSet();
								productAttributeSet.setName(attribute.getName());
								productAttributeSet.setDescription(attribute.getDescription());
								productAttributeSet.setValue(productAttributeCombination.getValue());
								productAttributeSetList.add(productAttributeSet);
							}
						}
						if(productAttribute.getSkuId() != null && !productAttribute.getSkuId().isEmpty()) {
							ProductAttributeSet productAttributeSet = new ProductAttributeSet();
							productAttributeSet.setName(SalesChannelConstants.SKU_ID);
							productAttributeSet.setValue(productAttribute.getSkuId());
							productAttributeSetList.add(productAttributeSet);
						}
						if(productAttribute.getQuantity() != null && !productAttribute.getQuantity().equals("")) {
							ProductAttributeSet productAttributeSet = new ProductAttributeSet();
							productAttributeSet.setName(SalesChannelConstants.QUANTITY);
							productAttributeSet.setValue(productAttribute.getQuantity().toString());
							productAttributeSetList.add(productAttributeSet);
						}
						if(productAttribute.getCost() != null && !productAttribute.getCost().equals("")) {
							ProductAttributeSet productAttributeSet = new ProductAttributeSet();
							productAttributeSet.setName(SalesChannelConstants.COST);
							productAttributeSet.setValue(productAttribute.getCost().toString());
							productAttributeSetList.add(productAttributeSet);
						}
						productAttributeSetModel.setProductAttributeSet(productAttributeSetList);
						productAttributeSetModelList.add(productAttributeSetModel);
					}
					productJsonModel.setProductAttributes(productAttributeSetModelList);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productJsonModel;
	}
	
	public ProductDaoImpl getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

}
