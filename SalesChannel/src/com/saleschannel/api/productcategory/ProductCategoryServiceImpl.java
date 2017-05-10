package com.saleschannel.api.productcategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.saleschannel.api.product.ProductDaoImpl;
import com.saleschannel.api.product.ProductJsonModel;

public class ProductCategoryServiceImpl implements ProductCategoryService {

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryServiceImpl.class);
	
	private ProductCategoryDaoImpl categoryDao;
	
	private ProductDaoImpl productDao;

	public ProductCategoryDaoImpl getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ProductCategoryDaoImpl categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public ProductDaoImpl getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

	public ProductCategoryJsonObject convertProductCategoryJsonModelToObject(ProductCategoryJsonModel productCategoryJsonModel) {
		ProductCategoryJsonObject productCategoryJsonObject = null;
		try {
			if(productCategoryJsonModel != null) {
				productCategoryJsonObject = new ProductCategoryJsonObject();
				productCategoryJsonObject.setId(productCategoryJsonModel.getId());
				productCategoryJsonObject.setCategoryName(productCategoryJsonModel.getCategoryName());
				productCategoryJsonObject.setParentId(productCategoryJsonModel.getParentId());
				productCategoryJsonObject.setMarketPlaceId(productCategoryJsonModel.getMarketPlaceId());
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
				productCategoryJsonModel.setMarketPlaceId(productCategoryJsonObject.getMarketPlaceId());
				productCategoryJsonModel.setParentId(productCategoryJsonObject.getParentId());
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

	public ProductCategoryJsonObject getProductCategoryByProductId(String productId) {
		ProductCategoryJsonObject productCategoryJsonObject = null;
		try {
			ProductJsonModel productJsonModel = productDao.getProductById(productId);
			if(productJsonModel != null && productJsonModel.getProductCategory() != null && !productJsonModel.getProductCategory().isEmpty()) {
				ProductCategoryJsonModel productCategoryJsonModel = categoryDao.getProductCategoryByNameAndCustomerId("0", productJsonModel.getProductCategory());
				if(productCategoryJsonModel != null) {
					if(productCategoryJsonModel.getParentId() != null && !productCategoryJsonModel.getParentId().isEmpty()) {
						productCategoryJsonModel = categoryDao.getProductCategoryByNameAndCustomerId("0", productCategoryJsonModel.getParentId());
					}
					productCategoryJsonObject = new ProductCategoryJsonObject();
					productCategoryJsonObject = convertProductCategoryJsonModelToObject(productCategoryJsonModel);		
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get product category by productId");
			e.printStackTrace();
		}
		return productCategoryJsonObject;
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
	
	public ProductCategoryColumnParametersJsonObject convertProductCategoryColumnParametersJsonModelToObject(ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel) {
		ProductCategoryColumnParametersJsonObject productCategoryColumnParametersJsonObject = null;
		try {
			productCategoryColumnParametersJsonObject = new ProductCategoryColumnParametersJsonObject();
			productCategoryColumnParametersJsonObject.setId(productCategoryColumnParametersJsonModel.getId());
			productCategoryColumnParametersJsonObject.setColumnName(productCategoryColumnParametersJsonModel.getColumnName());
			productCategoryColumnParametersJsonObject.setName(productCategoryColumnParametersJsonModel.getName());
			productCategoryColumnParametersJsonObject.setCategoryId(productCategoryColumnParametersJsonModel.getCategoryId());
			productCategoryColumnParametersJsonObject.setMarketPlaceId(productCategoryColumnParametersJsonModel.getMarketPlaceId());
			productCategoryColumnParametersJsonObject.setIsRequired(productCategoryColumnParametersJsonModel.isRequired());
			productCategoryColumnParametersJsonObject.setFieldType(productCategoryColumnParametersJsonModel.getFieldType());
			productCategoryColumnParametersJsonObject.setMaxLength(productCategoryColumnParametersJsonModel.getMaxLength());
			productCategoryColumnParametersJsonObject.setDefaultValues(productCategoryColumnParametersJsonModel.isDefaultValues());
			//get the default values if present
			if(productCategoryColumnParametersJsonObject.isDefaultValues()) {
				List<CategoryColumnValidValuesJsonModel> categoryColumnValidValuesJsonModelList = categoryDao.getCategoryColumnValidValuesByColumnName(productCategoryColumnParametersJsonModel.getColumnName());
				//check with name if more than one valid value available
				if(categoryColumnValidValuesJsonModelList != null && categoryColumnValidValuesJsonModelList.size() > 0 && categoryColumnValidValuesJsonModelList.size() > 1) {
					for(CategoryColumnValidValuesJsonModel categoryColumnValidValuesJsonModel : categoryColumnValidValuesJsonModelList) {
						if(categoryColumnValidValuesJsonModel.getName().contains(productCategoryColumnParametersJsonModel.getName())) {
							productCategoryColumnParametersJsonObject.setValidValues(categoryColumnValidValuesJsonModel.getValidValues());
						}
					}
				} else if(categoryColumnValidValuesJsonModelList != null && categoryColumnValidValuesJsonModelList.size() > 0) {
					productCategoryColumnParametersJsonObject.setValidValues(categoryColumnValidValuesJsonModelList.get(0).getValidValues());
				}
			} else {
				productCategoryColumnParametersJsonObject.setValidValues(productCategoryColumnParametersJsonModel.getValidValues());
			}
			productCategoryColumnParametersJsonObject.setIsDecimal(productCategoryColumnParametersJsonModel.isDecimal());
			productCategoryColumnParametersJsonObject.setBefore(productCategoryColumnParametersJsonModel.getBefore());
			productCategoryColumnParametersJsonObject.setAfter(productCategoryColumnParametersJsonModel.getAfter());
			productCategoryColumnParametersJsonObject.setFeedProductType(productCategoryColumnParametersJsonModel.getFeedProductType());
		} catch(Exception e) {
			LOGGERS.error("error while convert ProductCategoryColumnParametersJsonModel To Object");
			e.printStackTrace();
		}
		return productCategoryColumnParametersJsonObject;
	}
	
	public ProductCategoryColumnParametersJsonModel convertProductCategoryColumnParametersJsonObjectToModel(ProductCategoryColumnParametersJsonObject productCategoryColumnParametersJsonObject) {
		ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel = null;
		try {
			productCategoryColumnParametersJsonModel = new ProductCategoryColumnParametersJsonModel();
			productCategoryColumnParametersJsonModel.setId(productCategoryColumnParametersJsonObject.getId());
			productCategoryColumnParametersJsonModel.setColumnName(productCategoryColumnParametersJsonObject.getColumnName());
			productCategoryColumnParametersJsonModel.setName(productCategoryColumnParametersJsonObject.getName());
			productCategoryColumnParametersJsonModel.setCategoryId(productCategoryColumnParametersJsonObject.getCategoryId());
			productCategoryColumnParametersJsonModel.setMarketPlaceId(productCategoryColumnParametersJsonObject.getMarketPlaceId());
			productCategoryColumnParametersJsonModel.setIsRequired(productCategoryColumnParametersJsonObject.isRequired());
			productCategoryColumnParametersJsonModel.setFieldType(productCategoryColumnParametersJsonObject.getFieldType());
			productCategoryColumnParametersJsonModel.setMaxLength(productCategoryColumnParametersJsonObject.getMaxLength());
			productCategoryColumnParametersJsonModel.setDefaultValues(productCategoryColumnParametersJsonObject.isDefaultValues());
			productCategoryColumnParametersJsonModel.setValidValues(productCategoryColumnParametersJsonObject.getValidValues());
			productCategoryColumnParametersJsonModel.setIsDecimal(productCategoryColumnParametersJsonObject.isDecimal());
			productCategoryColumnParametersJsonModel.setBefore(productCategoryColumnParametersJsonObject.getBefore());
			productCategoryColumnParametersJsonModel.setAfter(productCategoryColumnParametersJsonObject.getAfter());
			productCategoryColumnParametersJsonModel.setFeedProductType(productCategoryColumnParametersJsonObject.getFeedProductType());
		} catch(Exception e) {
			LOGGERS.error("error while convert ProductCategoryColumnParametersJsonObject To Model");
			e.printStackTrace();
		}
		return productCategoryColumnParametersJsonModel;
	}
	
	public List<ProductCategoryColumnParametersJsonObject> getProductCategoryColumnParametersByCategoryId(String productCategoryId) {
		List<ProductCategoryColumnParametersJsonObject> productCategoryColumnParametersJsonObjectList = null;
		try {
			List<ProductCategoryColumnParametersJsonModel> productCategoryColumnParametersJsonModelList = categoryDao.getProductCategoryColumnParametersByCategoryId(productCategoryId);
			if(productCategoryColumnParametersJsonModelList != null && productCategoryColumnParametersJsonModelList.size() > 0) {
				productCategoryColumnParametersJsonObjectList = new ArrayList<ProductCategoryColumnParametersJsonObject>();
				for(ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel : productCategoryColumnParametersJsonModelList) {
					ProductCategoryColumnParametersJsonObject productCategoryColumnParametersJsonObject = convertProductCategoryColumnParametersJsonModelToObject(productCategoryColumnParametersJsonModel);
					productCategoryColumnParametersJsonObjectList.add(productCategoryColumnParametersJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while check get ProductCategoryColumnParameters By CategoryId");
			e.printStackTrace();
		}
		return productCategoryColumnParametersJsonObjectList;
	}
	
	public void insertProductCategoryColumnParameter(ProductCategoryColumnParametersJsonObject productCategoryColumnParametersJsonObject) {
		try {
			if(productCategoryColumnParametersJsonObject != null) {
				ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel = convertProductCategoryColumnParametersJsonObjectToModel(productCategoryColumnParametersJsonObject);
				if(productCategoryColumnParametersJsonModel != null) {
					productCategoryColumnParametersJsonModel.setCreateBy(productCategoryColumnParametersJsonObject.getCustomerId());
					productCategoryColumnParametersJsonModel.setCreatedAt(new Date());
					categoryDao.insertProductCategoryColumnParameter(productCategoryColumnParametersJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while insert category column parameter.");
			e.printStackTrace();
		}
	}
	
	public CategoryColumnValidValuesJsonModel convertCategoryColumnValidValuesJsonObjectToModel(CategoryColumnValidValuesJsonObject categoryColumnValidValuesJsonObject) {
		CategoryColumnValidValuesJsonModel categoryColumnValidValuesJsonModel = null;
		try {
			if(categoryColumnValidValuesJsonObject != null) {
				categoryColumnValidValuesJsonModel = new CategoryColumnValidValuesJsonModel();
				categoryColumnValidValuesJsonModel.setColumnName(categoryColumnValidValuesJsonObject.getColumnName());
				categoryColumnValidValuesJsonModel.setName(categoryColumnValidValuesJsonObject.getName());
				categoryColumnValidValuesJsonModel.setValidValues(categoryColumnValidValuesJsonObject.getValidValues());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convert category column valid values object to model.");
			e.printStackTrace();
		}
		return categoryColumnValidValuesJsonModel;
	}
	
	public CategoryColumnValidValuesJsonObject convertCategoryColumnValidValuesJsonModelToObject(CategoryColumnValidValuesJsonModel categoryColumnValidValuesJsonModel) {
		CategoryColumnValidValuesJsonObject categoryColumnValidValuesJsonObject = null;
		try {
			if(categoryColumnValidValuesJsonModel != null) {
				categoryColumnValidValuesJsonObject = new CategoryColumnValidValuesJsonObject();
				categoryColumnValidValuesJsonObject.setId(categoryColumnValidValuesJsonModel.getId());
				categoryColumnValidValuesJsonObject.setColumnName(categoryColumnValidValuesJsonModel.getColumnName());
				categoryColumnValidValuesJsonObject.setName(categoryColumnValidValuesJsonModel.getName());
				categoryColumnValidValuesJsonObject.setValidValues(categoryColumnValidValuesJsonModel.getValidValues());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convert category column valid values model to object.");
			e.printStackTrace();
		}
		return categoryColumnValidValuesJsonObject;
	}
	
	public void insertCategoryColumnValidValues(CategoryColumnValidValuesJsonObject categoryColumnValidValuesJsonObject) {
		try {
			if(categoryColumnValidValuesJsonObject != null) {
				CategoryColumnValidValuesJsonModel categoryColumnValidValuesJsonModel = convertCategoryColumnValidValuesJsonObjectToModel(categoryColumnValidValuesJsonObject);
				if(categoryColumnValidValuesJsonModel != null) {
					categoryColumnValidValuesJsonModel.setCreateBy(categoryColumnValidValuesJsonObject.getCustomerId());
					categoryColumnValidValuesJsonModel.setCreatedAt(new Date());
					categoryDao.insertCategoryColumnValidValues(categoryColumnValidValuesJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while insert category column valid values.");
			e.printStackTrace();
		}
	}
	
	public List<CategoryColumnValidValuesJsonObject> getCategoryColumnValidValuesByColumnName(String columnName) {
		List<CategoryColumnValidValuesJsonObject> categoryColumnValidValuesJsonObjectList = null;
		try {
			List<CategoryColumnValidValuesJsonModel> categoryColumnValidValuesJsonModelList = categoryDao.getCategoryColumnValidValuesByColumnName(columnName);
			if(categoryColumnValidValuesJsonModelList != null && categoryColumnValidValuesJsonModelList.size() > 0) {
				categoryColumnValidValuesJsonObjectList = new ArrayList<CategoryColumnValidValuesJsonObject>();
				for(CategoryColumnValidValuesJsonModel categoryColumnValidValuesJsonModel : categoryColumnValidValuesJsonModelList) {
					CategoryColumnValidValuesJsonObject categoryColumnValidValuesJsonObject = convertCategoryColumnValidValuesJsonModelToObject(categoryColumnValidValuesJsonModel);
					categoryColumnValidValuesJsonObjectList.add(categoryColumnValidValuesJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while check get CategoryColumnValidValues By ColumnName");
			e.printStackTrace();
		}
		return categoryColumnValidValuesJsonObjectList;
	}

	
	public ProductCategoryColumnValueJsonObject convertProductCategoryColumnValuesJsonModelToObject(ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel) {
		ProductCategoryColumnValueJsonObject productCategoryColumnValueJsonObject = null;
		try {
			if(productCategoryColumnValueJsonModel != null) {
				productCategoryColumnValueJsonObject = new ProductCategoryColumnValueJsonObject();
				if(productCategoryColumnValueJsonModel.getCategoryColumnParameterId() != null 
						&& productCategoryColumnValueJsonModel.equals("")) {
					ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel = categoryDao.
							getProductCategoryColumnParameterById(productCategoryColumnValueJsonObject.getCategoryColumnParameterId());
					if(productCategoryColumnParametersJsonModel != null) {
						productCategoryColumnValueJsonObject.setCategoryColumnParameterName(productCategoryColumnParametersJsonModel.getColumnName());
					}
				}
				productCategoryColumnValueJsonObject.setCategoryColumnParameterId(productCategoryColumnValueJsonModel.getCategoryColumnParameterId());
				productCategoryColumnValueJsonObject.setId(productCategoryColumnValueJsonModel.getId());
				productCategoryColumnValueJsonObject.setProductId(productCategoryColumnValueJsonModel.getProductId());
				productCategoryColumnValueJsonObject.setValue(productCategoryColumnValueJsonModel.getValue());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productCategoryColumnValueJsonObject;
	}
	
	public ProductCategoryColumnValueJsonModel convertProductCategoryColumnValuesJsonObjectToModel(ProductCategoryColumnValueJsonObject productCategoryColumnValueJsonObject) {
		ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel = null;
		try {
			if(productCategoryColumnValueJsonObject != null) {
				productCategoryColumnValueJsonModel = new ProductCategoryColumnValueJsonModel();
				productCategoryColumnValueJsonModel.setCategoryColumnParameterId(productCategoryColumnValueJsonObject.getCategoryColumnParameterId());
				productCategoryColumnValueJsonModel.setId(productCategoryColumnValueJsonObject.getId());
				productCategoryColumnValueJsonModel.setProductId(productCategoryColumnValueJsonObject.getProductId());
				productCategoryColumnValueJsonModel.setValue(productCategoryColumnValueJsonObject.getValue());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productCategoryColumnValueJsonModel;
	}
	
	public List<ProductCategoryColumnValueJsonObject> getProductCategoryColumnValuesByProductId(String productId) {
		List<ProductCategoryColumnValueJsonObject> productCategoryColumnValueJsonObjectList = null;
		try {
			List<ProductCategoryColumnValueJsonModel> productCategoryColumnValueJsonModelList = categoryDao.getProductCategoryColumnValuesByProductId(productId);
			if(productCategoryColumnValueJsonModelList != null && productCategoryColumnValueJsonModelList.size() > 0) {
				productCategoryColumnValueJsonObjectList = new ArrayList<ProductCategoryColumnValueJsonObject>();
				for(ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel : productCategoryColumnValueJsonModelList) {
					ProductCategoryColumnValueJsonObject productCategoryColumnValueJsonObject = convertProductCategoryColumnValuesJsonModelToObject(productCategoryColumnValueJsonModel);
					productCategoryColumnValueJsonObjectList.add(productCategoryColumnValueJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while check get ProductCategoryColumnValues By ProductId");
			e.printStackTrace();
		}
		return productCategoryColumnValueJsonObjectList;
	}
	
	public ProductCategoryColumnValueJsonObject getProductCategoryColumnValueById(String id) {
		ProductCategoryColumnValueJsonObject columnValueJsonObject = null;
		try {
			ProductCategoryColumnValueJsonModel categoryColumnValueJsonModel = categoryDao.getProductCategoryColumnValueById(id);
			if(categoryColumnValueJsonModel != null) {
				columnValueJsonObject = convertProductCategoryColumnValuesJsonModelToObject(categoryColumnValueJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error while check get ProductCategoryColumnValue By id");
			e.printStackTrace();
		}
		return columnValueJsonObject;
	}
	
	public void insertUpdateProductCategoryColumnValues(CategoryColumnsValueJsonObject categoryColumnsValueJsonObject) {
		try {
			if(categoryColumnsValueJsonObject != null && categoryColumnsValueJsonObject.getCategoryColumnsValue() != null 
					&& categoryColumnsValueJsonObject.getCategoryColumnsValue().size() > 0) {
				for(ProductCategoryColumnValueJsonObject categoryColumnValueJsonObject : categoryColumnsValueJsonObject.getCategoryColumnsValue()) {
					if(categoryColumnValueJsonObject != null) {
						ProductCategoryColumnValueJsonModel categoryColumnValueJsonModel = convertProductCategoryColumnValuesJsonObjectToModel(categoryColumnValueJsonObject);
						if(categoryColumnValueJsonModel != null) {
							if(categoryColumnValueJsonModel.getId() != null && !categoryColumnValueJsonModel.getId().isEmpty()) {
								categoryDao.insertCategoryColumnValue(categoryColumnValueJsonModel);
							} else {
								categoryDao.updateCategoryColumnValue(categoryColumnValueJsonModel);
							}
						}
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while insert ProductCategoryColumnValues");
			e.printStackTrace();
		}
	}

}
