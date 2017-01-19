package net.saleschannel.api.productcategorymapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.saleschannel.api.productcategory.ProductCategoryDaoImpl;
import net.saleschannel.api.productcategory.ProductCategoryJsonModel;

import org.apache.log4j.Logger;

public class ProductCategoryMappingServiceImpl implements ProductCategoryMappingService {

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryMappingServiceImpl.class);
	
	private ProductCategoryMappingDaoImpl categoryMappingDao;
	
	private ProductCategoryDaoImpl categoryDao;
	
	public ProductCategoryMappingJsonObject convertProductCategoryMappingJsonModelToObject(
			ProductCategoryMappingJsonModel productCategoryMappingJsonModel) {
		ProductCategoryMappingJsonObject productCategoryMappingJsonObject = new ProductCategoryMappingJsonObject();
		try {
			if(productCategoryMappingJsonModel != null) {
				productCategoryMappingJsonObject.setId(productCategoryMappingJsonModel.getId());
				productCategoryMappingJsonObject.setCustomerCategoryId(productCategoryMappingJsonModel.getCustomerProductCategoryId());
				productCategoryMappingJsonObject.setMarketPlaceCategoryId(productCategoryMappingJsonModel.getMarketPlaceProductCategoryId());
				productCategoryMappingJsonObject.setCustomerId(productCategoryMappingJsonModel.getCustomerId());
				if(productCategoryMappingJsonModel.getMarketPlaceProductCategoryId() != null && !productCategoryMappingJsonModel.getMarketPlaceProductCategoryId().isEmpty()) {
					ProductCategoryJsonModel productCategoryJsonModel = categoryDao.getProductCategoryById(productCategoryMappingJsonModel.getMarketPlaceProductCategoryId()
							, productCategoryMappingJsonModel.getCustomerId());
					if(productCategoryJsonModel != null && productCategoryJsonModel.getCategoryName() != null 
							&& !productCategoryJsonModel.getCategoryName().isEmpty()) {
						productCategoryMappingJsonObject.setMarketPlaceCategoryName(productCategoryJsonModel.getCategoryName());				
					}
				}
				if(productCategoryMappingJsonModel.getCustomerProductCategoryId() != null && !productCategoryMappingJsonModel.getCustomerProductCategoryId().isEmpty()) {
					ProductCategoryJsonModel productCategoryJsonModel = categoryDao.getProductCategoryById(productCategoryMappingJsonModel.getCustomerProductCategoryId()
							, productCategoryMappingJsonModel.getCustomerId());
					if(productCategoryJsonModel != null && productCategoryJsonModel.getCategoryName() != null 
							&& !productCategoryJsonModel.getCategoryName().isEmpty()) {
						productCategoryMappingJsonObject.setCustomerCategoryName(productCategoryJsonModel.getCategoryName());				
					}
				}
			}
		} catch(Exception e) {
			productCategoryMappingJsonObject = null;
			LOGGERS.error("error occured while convertProductCategoryMappingJsonModelToObject");
			e.printStackTrace();
		}
		return productCategoryMappingJsonObject;
	}

	public ProductCategoryMappingJsonModel convertProductCategoryMappingJsonObjectToModel(
			ProductCategoryMappingJsonObject productCategoryMappingJsonObject) {
		ProductCategoryMappingJsonModel productCategoryMappingJsonModel = new ProductCategoryMappingJsonModel();
		try {
			if(productCategoryMappingJsonObject != null) {
				productCategoryMappingJsonModel.setId(productCategoryMappingJsonObject.getId());
				productCategoryMappingJsonModel.setCustomerProductCategoryId(productCategoryMappingJsonObject.getCustomerCategoryId());
				productCategoryMappingJsonModel.setMarketPlaceProductCategoryId(productCategoryMappingJsonObject.getMarketPlaceCategoryId());
				productCategoryMappingJsonModel.setCustomerId(productCategoryMappingJsonObject.getCustomerId());
				if(productCategoryMappingJsonObject.getMarketPlaceCategoryId() != null && !productCategoryMappingJsonObject.getMarketPlaceCategoryId().isEmpty()) {
					ProductCategoryJsonModel productCategoryJsonModel = categoryDao.getProductCategoryById(productCategoryMappingJsonObject.getMarketPlaceCategoryId()
							, productCategoryMappingJsonObject.getCustomerId());
					if(productCategoryJsonModel != null && productCategoryJsonModel.getMarketPlaceId() != null 
							&& !productCategoryJsonModel.getMarketPlaceId().isEmpty()) {
						productCategoryMappingJsonModel.setMarketPlaceId(productCategoryJsonModel.getMarketPlaceId());				
					}
				}
			}
		} catch(Exception e) {
			productCategoryMappingJsonModel = null;
			LOGGERS.error("error occured while convertProductCategoryMappingJsonObjectToModel");
			e.printStackTrace();
		}
		return productCategoryMappingJsonModel;
	}

	public String insertProductCategoryMapping(
			ProductCategoryMappingJsonObject categoryMappingJsonObject) {
		String categoryMappingId = null;
		try {
			if(categoryMappingJsonObject != null) {
				ProductCategoryMappingJsonModel categoryMappingJsonModel = convertProductCategoryMappingJsonObjectToModel(categoryMappingJsonObject);
				if(categoryMappingJsonModel != null) {
					ProductCategoryMappingJsonModel productCategoryMapping = categoryMappingDao.isProductCategoryMappingExist(categoryMappingJsonModel);
					if(productCategoryMapping == null) {
						categoryMappingJsonModel.setCreatedAt(new Date());
						categoryMappingJsonModel.setCreatedBy(categoryMappingJsonObject.getCustomerId());
						categoryMappingId = categoryMappingDao.insertProductCategoryMapping(categoryMappingJsonModel);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return categoryMappingId;
	}

	public boolean updateProductCategoryMapping(ProductCategoryMappingJsonObject categoryMappingJsonObject) {
		boolean status = false;
		try {
			if(categoryMappingJsonObject != null) {
				ProductCategoryMappingJsonModel categoryMappingJsonModel = isProductCategoryMappingExist(categoryMappingJsonObject);
				if(categoryMappingJsonModel != null) {
					categoryMappingJsonModel.setCustomerProductCategoryId(categoryMappingJsonObject.getCustomerCategoryId());
					categoryMappingJsonModel.setMarketPlaceProductCategoryId(categoryMappingJsonObject.getMarketPlaceCategoryId());
					categoryMappingJsonModel.setUpdatedAt(new Date());
					categoryMappingJsonModel.setUpdatedBy(categoryMappingJsonObject.getCustomerId());
					status = categoryMappingDao.updateProductCategoryMapping(categoryMappingJsonModel);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryMappingJsonObject getProductCategoryMappingById(
			String categoryMappingId) {
		ProductCategoryMappingJsonObject productCategoryMappingJsonObject = null;
		try {
			ProductCategoryMappingJsonModel productCategoryMappingJsonModel = categoryMappingDao.getProductCategoryMappingById(categoryMappingId);
			if(productCategoryMappingJsonModel != null) {
				productCategoryMappingJsonObject = convertProductCategoryMappingJsonModelToObject(productCategoryMappingJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get ProductCategoryMapping By Id");
			e.printStackTrace();
		}
		return productCategoryMappingJsonObject;
	}

	public List<ProductCategoryMappingJsonObject> getProductCategoryMappingByCustomerIdAndMarketPlaceId(
			String customerId, String marketPlaceId) {
		List<ProductCategoryMappingJsonObject> productCategoryMappingJsonObjectList = new ArrayList<ProductCategoryMappingJsonObject>();
		try {
			List<ProductCategoryMappingJsonModel> productCategoryMappingJsonModelList = categoryMappingDao.getProductCategoryMappingByCustomerIdAndMarketPlaceId(customerId
					, marketPlaceId);
			if(productCategoryMappingJsonModelList != null && productCategoryMappingJsonModelList.size() > 0) {
				for(ProductCategoryMappingJsonModel productCategoryMappingJsonModel : productCategoryMappingJsonModelList) {
					ProductCategoryMappingJsonObject productCategoryMappingJsonObject = convertProductCategoryMappingJsonModelToObject(productCategoryMappingJsonModel);
					if(productCategoryMappingJsonObject != null) {
						productCategoryMappingJsonObjectList.add(productCategoryMappingJsonObject);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productCategoryMappingJsonObjectList;
	}

	public List<ProductCategoryMappingJsonObject> getProductCategoryMappingByCustomerId(
			String customerId) {
		List<ProductCategoryMappingJsonObject> productCategoryMappingJsonObjectList = new ArrayList<ProductCategoryMappingJsonObject>();
		try {
			List<ProductCategoryMappingJsonModel> productCategoryMappingJsonModelList = categoryMappingDao.getProductCategoryMappingByCustomerId(customerId);
			if(productCategoryMappingJsonModelList != null && productCategoryMappingJsonModelList.size() > 0) {
				for(ProductCategoryMappingJsonModel productCategoryMappingJsonModel : productCategoryMappingJsonModelList) {
					ProductCategoryMappingJsonObject productCategoryMappingJsonObject = convertProductCategoryMappingJsonModelToObject(productCategoryMappingJsonModel);
					if(productCategoryMappingJsonObject != null) {
						productCategoryMappingJsonObjectList.add(productCategoryMappingJsonObject);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productCategoryMappingJsonObjectList;
	}
	
	public boolean deleteProductCategoryMappingById(String productCategoryMappingId) {
		boolean status = false;
		try {
			ProductCategoryMappingJsonModel productCategoryMappingJsonModel = categoryMappingDao.getProductCategoryMappingById(productCategoryMappingId);
			if(productCategoryMappingJsonModel != null) {
				status = categoryMappingDao.deleteProductCategoryMappingById(productCategoryMappingId);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while delete ProductCategoryMapping By Id");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteProductCategoryMappingByCustomerIdAndMarketPlaceId(
			String customerId, String marketPlaceId) {
		boolean status = false;
		try {
			List<ProductCategoryMappingJsonModel> productCategoryMappingJsonModelList = categoryMappingDao.getProductCategoryMappingByCustomerIdAndMarketPlaceId(customerId
					, marketPlaceId);
			if(productCategoryMappingJsonModelList != null && productCategoryMappingJsonModelList.size() > 0) {
				status = categoryMappingDao.deleteProductCategoryMappingByCustomerIdAndMarketPlaceId(customerId, marketPlaceId);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while delete ProductCategoryMapping By Customer Id and MarketPlace Id");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteProductCategoryMappingByCustomerId(String customerId) {
		boolean status = false;
		try {
			List<ProductCategoryMappingJsonModel> productCategoryMappingJsonModelList = categoryMappingDao.getProductCategoryMappingByCustomerId(customerId);
			if(productCategoryMappingJsonModelList != null && productCategoryMappingJsonModelList.size() > 0) {
				status = categoryMappingDao.deleteProductCategoryMappingByCustomerId(customerId);
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while delete ProductCategoryMapping By Customer Id ");
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryMappingJsonModel isProductCategoryMappingExist(
			ProductCategoryMappingJsonObject categoryMappingJsonObject) {
		ProductCategoryMappingJsonModel productCategoryMappingJsonModel = null;
		try {
			if(categoryMappingJsonObject != null) {
				productCategoryMappingJsonModel = convertProductCategoryMappingJsonObjectToModel(categoryMappingJsonObject);
				if(categoryMappingJsonObject != null) {
					productCategoryMappingJsonModel = categoryMappingDao.isProductCategoryMappingExist(productCategoryMappingJsonModel);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productCategoryMappingJsonModel;
	}

	public ProductCategoryMappingDaoImpl getCategoryMappingDao() {
		return categoryMappingDao;
	}

	public void setCategoryMappingDao(ProductCategoryMappingDaoImpl categoryMappingDao) {
		this.categoryMappingDao = categoryMappingDao;
	}

	public ProductCategoryDaoImpl getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ProductCategoryDaoImpl categoryDao) {
		this.categoryDao = categoryDao;
	}

}
