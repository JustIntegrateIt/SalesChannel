package net.saleschannel.api.productcategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class ProductCategoryServiceImpl implements ProductCategoryService {

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryServiceImpl.class);
	
	private ProductCategoryDaoImpl categoryDao;

	public ProductCategoryDaoImpl getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ProductCategoryDaoImpl categoryDao) {
		this.categoryDao = categoryDao;
	}

	public ProductCategoryJsonObject convertProductCategoryJsonModelToObject(ProductCategoryJsonModel productCategoryJsonModel) {
		ProductCategoryJsonObject productCategoryJsonObject = null;
		try {
			if(productCategoryJsonModel != null) {
				productCategoryJsonObject = new ProductCategoryJsonObject();
				productCategoryJsonObject.setId(productCategoryJsonModel.getId());
				productCategoryJsonObject.setCategoryName(productCategoryJsonModel.getCategoryName());
				productCategoryJsonObject.setParentId(productCategoryJsonModel.getParentId());
				if(productCategoryJsonModel.getParentId() != null && !productCategoryJsonModel.getParentId().isEmpty()) {
					ProductCategoryJsonModel productCategoryModel = categoryDao.getProductCategoryById(productCategoryJsonModel.getParentId()
							, productCategoryJsonModel.getCustomerId());
					if(productCategoryModel != null && productCategoryModel.getCategoryName() != null && !productCategoryModel.getCategoryName().isEmpty()) {
						productCategoryJsonObject.setParentCategoryName(productCategoryModel.getCategoryName());
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertProductCategoryJsonModelToObject");
			e.printStackTrace();
		}
		return productCategoryJsonObject;
	}
	
	public ProductCategoryJsonModel convertProductCategoryJsonObjectToModel(ProductCategoryJsonObject productCategoryJsonObject) {
		ProductCategoryJsonModel productCategoryJsonModel = null;
		try {
			if(productCategoryJsonObject != null) {
				productCategoryJsonModel = new ProductCategoryJsonModel();
				productCategoryJsonModel.setId(productCategoryJsonObject.getId());
				productCategoryJsonModel.setCategoryName(productCategoryJsonObject.getCategoryName());
				productCategoryJsonModel.setCustomerId(productCategoryJsonObject.getCustomerId());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertProductCategoryJsonObjectToModel");
			e.printStackTrace();
		}
		return productCategoryJsonModel;
	}
	
	public String insertProductCategory(ProductCategoryJsonObject productCategoryJsonObject) {
		String categoryId = null;
		try {
			if(productCategoryJsonObject != null) {
				ProductCategoryJsonModel productCategoryJsonModel = convertProductCategoryJsonObjectToModel(productCategoryJsonObject);
				if(productCategoryJsonModel != null) {
					ProductCategoryJsonModel category = isProductCategoryExist(productCategoryJsonObject);
					if(category == null) {
						productCategoryJsonModel.setCreateBy(productCategoryJsonModel.getCustomerId());
						productCategoryJsonModel.setCreatedAt(new Date());
						categoryId = categoryDao.insertProductCategory(productCategoryJsonModel);
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while insert category.");
			e.printStackTrace();
		}
		return categoryId;
	}

	public boolean updateProductCategory(ProductCategoryJsonObject productCategoryJsonObject) {
		boolean status = false;
		try {
			if(productCategoryJsonObject != null) {
				ProductCategoryJsonModel productCategoryJsonModelNew = convertProductCategoryJsonObjectToModel(productCategoryJsonObject);
				if(productCategoryJsonModelNew != null) {
					ProductCategoryJsonModel productCategoryJsonModelOld = categoryDao.getProductCategoryById(productCategoryJsonModelNew.getId()
							, productCategoryJsonObject.getCustomerId());
					if(productCategoryJsonModelOld != null) {
						productCategoryJsonModelOld.setCategoryName(productCategoryJsonModelNew.getCategoryName());
						productCategoryJsonModelOld.setUpdatedBy(productCategoryJsonModelOld.getCustomerId());
						productCategoryJsonModelOld.setUpdatedAt(new Date());
						categoryDao.updateProductCategory(productCategoryJsonModelOld);
						status = true;
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while update product category");
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryJsonObject getProductCategoryById(String productCategoryId, String customerId) {
		ProductCategoryJsonObject productCategoryJsonObject = null;
		try {
			ProductCategoryJsonModel productCategoryJsonModel = categoryDao.getProductCategoryById(productCategoryId
					, customerId);
			if(productCategoryJsonModel != null) {
				productCategoryJsonObject = convertProductCategoryJsonModelToObject(productCategoryJsonModel); 
			}
		} catch(Exception e) {
			LOGGERS.error("error while get product category by id");
			e.printStackTrace();
		}
		return productCategoryJsonObject;
	}
	
	public ProductCategoryJsonObject getProductCategoryByNameAndCustomerId(String customerId, String categoryName) {
		ProductCategoryJsonObject productCategoryJsonObject = null;
		try {
			ProductCategoryJsonModel productCategoryJsonModel = categoryDao.getProductCategoryByNameAndCustomerId(customerId
					, categoryName);
			if(productCategoryJsonModel != null) {
				productCategoryJsonObject = convertProductCategoryJsonModelToObject(productCategoryJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error while get product category by Name and CustomerId");
			e.printStackTrace();
		}
		return productCategoryJsonObject ;
	}

	public List<ProductCategoryJsonObject> getProductCategoryByCustomerId(String customerId) {
		List<ProductCategoryJsonObject> productCategoryJsonObjectList = null;
		try {
			List<ProductCategoryJsonModel>  productCategoryJsonModelList = categoryDao.getProductCategoryByCustomerId(customerId);
			if(productCategoryJsonModelList != null && productCategoryJsonModelList.size() > 0) {
				productCategoryJsonObjectList = new ArrayList<ProductCategoryJsonObject>();
				for(ProductCategoryJsonModel productCategoryJsonModel : productCategoryJsonModelList) {
					ProductCategoryJsonObject productCategoryJsonObject = convertProductCategoryJsonModelToObject(productCategoryJsonModel);
					if(productCategoryJsonObject != null)
						productCategoryJsonObjectList.add(productCategoryJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get product category list by customerId");
			e.printStackTrace();
		}
		return productCategoryJsonObjectList ;
	}
	
	public List<ProductCategoryJsonObject> getProductCategoryByMarketPlaceId(String marketPlaceId) {
		List<ProductCategoryJsonObject> productCategoryJsonObjectList = null;
		try {
			List<ProductCategoryJsonModel>  productCategoryJsonModelList = categoryDao.getProductCategoryByMarketPlaceId(marketPlaceId);
			if(productCategoryJsonModelList != null && productCategoryJsonModelList.size() > 0) {
				productCategoryJsonObjectList = new ArrayList<ProductCategoryJsonObject>();
				for(ProductCategoryJsonModel productCategoryJsonModel : productCategoryJsonModelList) {
					ProductCategoryJsonObject productCategoryJsonObject = convertProductCategoryJsonModelToObject(productCategoryJsonModel);
					if(productCategoryJsonObject != null)
						productCategoryJsonObjectList.add(productCategoryJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get product category list by marketPlaceId");
			e.printStackTrace();
		}
		return productCategoryJsonObjectList ;
	}

	public boolean deleteProductCategoryById(String productCategoryId, String customerId) {
		boolean status = false;
		try {
			status = categoryDao.deleteProductCategoryById(productCategoryId, customerId);
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by id");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteProductCategoryByNameAndCustomerId(String customerId, String categoryName) {
		boolean status = false;
		try {
			status = categoryDao.deleteProductCategoryByNameAndCustomerId(customerId, categoryName);
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by name and customerId");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteProductCategoryByCustomerId(String customerId) {
		boolean status = false;
		try {
			status = categoryDao.deleteProductCategoryByCustomerId(customerId);
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by customerId");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteProductCategoryByMarketPlaceId(String marketPlaceId){
		boolean status = false;
		try {
			status = categoryDao.deleteProductCategoryByMarketPlaceId(marketPlaceId);
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by marketPlaceId");
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryJsonModel isProductCategoryExist(ProductCategoryJsonObject categoryJsonObject) {
		ProductCategoryJsonModel productCategoryJsonModel = null;
		try {
			if(categoryJsonObject != null) {
				productCategoryJsonModel = convertProductCategoryJsonObjectToModel(categoryJsonObject);
				if(productCategoryJsonModel != null) {
					productCategoryJsonModel = categoryDao.isProductCategoryExist(productCategoryJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while check is product category exist");
			e.printStackTrace();
		}
		return productCategoryJsonModel ;
	}
}
