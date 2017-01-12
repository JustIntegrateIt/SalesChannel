package net.saleschannel.api.productcategory;

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

	public String insertProductCategory(ProductCategoryJsonModel productCategoryJsonModel) {
		String categoryId = null;
		try {
			if(productCategoryJsonModel != null) {
				ProductCategoryJsonModel category = isProductCategoryExist(productCategoryJsonModel);
				if(category == null) {
					productCategoryJsonModel.setCreateBy(productCategoryJsonModel.getCustomerId());
					productCategoryJsonModel.setCreatedAt(new Date());
					categoryId = categoryDao.insertProductCategory(productCategoryJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while insert category.");
			e.printStackTrace();
		}
		return categoryId;
	}

	public boolean updateProductCategory(ProductCategoryJsonModel productCategoryJsonModelNew) {
		boolean status = false;
		try {
			ProductCategoryJsonModel productCategoryJsonModelOld = getProductCategoryById(productCategoryJsonModelNew.getId());
			if(productCategoryJsonModelOld != null) {
				productCategoryJsonModelOld.setCategoryName(productCategoryJsonModelNew.getCategoryName());
				productCategoryJsonModelOld.setUpdatedBy(productCategoryJsonModelOld.getCustomerId());
				productCategoryJsonModelOld.setUpdatedAt(new Date());
				categoryDao.updateProductCategory(productCategoryJsonModelOld);
				status = true;
			}
		} catch(Exception e) {
			LOGGERS.error("error while update product category");
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryJsonModel getProductCategoryById(String productCategoryId) {
		ProductCategoryJsonModel productCategoryJsonModel = null;
		try {
			productCategoryJsonModel = categoryDao.getProductCategoryById(productCategoryId);
		} catch(Exception e) {
			LOGGERS.error("error while get product category by id");
			e.printStackTrace();
		}
		return productCategoryJsonModel ;
	}
	
	public ProductCategoryJsonModel getProductCategoryByNameAndCustomerId(String customerId, String categoryName) {
		ProductCategoryJsonModel productCategoryJsonModel = null;
		try {
			productCategoryJsonModel = categoryDao.getProductCategoryByNameAndCustomerId(customerId, categoryName);
		} catch(Exception e) {
			LOGGERS.error("error while get product category by Name and CustomerId");
			e.printStackTrace();
		}
		return productCategoryJsonModel ;
	}

	public List<ProductCategoryJsonModel> getProductCategoryByCustomerId(String customerId) {
		List<ProductCategoryJsonModel> productCategoryJsonModelList = null;
		try {
			productCategoryJsonModelList = categoryDao.getProductCategoryByCustomerId(customerId);
		} catch(Exception e) {
			LOGGERS.error("error while get product category list by customerId");
			e.printStackTrace();
		}
		return productCategoryJsonModelList ;
	}

	public boolean deleteProductCategoryById(String productCategoryId) {
		boolean status = false;
		try {
			status = categoryDao.deleteProductCategoryById(productCategoryId);
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
	
	public ProductCategoryJsonModel isProductCategoryExist(ProductCategoryJsonModel categoryJsonModel) {
		ProductCategoryJsonModel productCategoryJsonModel = null;
		try {
			productCategoryJsonModel = categoryDao.isProductCategoryExist(productCategoryJsonModel);
		} catch(Exception e) {
			LOGGERS.error("error while check is product category exist");
			e.printStackTrace();
		}
		return productCategoryJsonModel ;
	}
}
