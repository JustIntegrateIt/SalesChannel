package com.saleschannel.api.productcategory;

import java.util.List;

import com.saleschannel.api.constants.SalesChannelConstants;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ProductCategoryDaoImpl implements ProductCategoryDao {

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryDaoImpl.class);
	
	private MongoOperations mongoOps;
 	
	public ProductCategoryDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }

	public String insertProductCategory(ProductCategoryJsonModel productCategoryJsonModel) {
		String productCategoryId = null;
		try {
			ObjectId objectId = new ObjectId();
			productCategoryId = objectId.toString();
			productCategoryJsonModel.setId(productCategoryId);
			this.mongoOps.insert(productCategoryJsonModel, SalesChannelConstants.SC_PRODUCT_CATEGORY);
		} catch(Exception e) {
			LOGGERS.error("error while insert product category in database");
			e.printStackTrace();
		}
		return productCategoryId;
	}

	public boolean updateProductCategory(ProductCategoryJsonModel productCategoryJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(productCategoryJsonModel, SalesChannelConstants.SC_PRODUCT_CATEGORY);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update product category in database");
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryJsonModel getProductCategoryById(String productCategoryId, String customerId) {
		ProductCategoryJsonModel productCategoryJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
					.and("_id").is(productCategoryId).and("customerId").is(customerId));
			productCategoryJsonModel = this.mongoOps.findOne(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
		} catch(Exception e) {
			LOGGERS.error("error while get product category by Id from database");
			e.printStackTrace();
		}
		return productCategoryJsonModel;
	}
	
	public ProductCategoryJsonModel getProductCategoryByNameAndCustomerId(String customerId, String categoryName) {
		ProductCategoryJsonModel productCategoryJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
					.and("customerId").is(customerId).and("categoryName").is(categoryName));
			productCategoryJsonModel = this.mongoOps.findOne(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
		} catch(Exception e) {
			LOGGERS.error("error while get product category by Name and CustomerId from database");
			e.printStackTrace();
		}
		return productCategoryJsonModel;
	}

	public List<ProductCategoryJsonModel> getProductCategoryByCustomerId(String customerId) {
		List<ProductCategoryJsonModel> productCategoryJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
					.and("customerId").is(customerId));
			productCategoryJsonModelList = this.mongoOps.find(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
		} catch(Exception e) {
			LOGGERS.error("error while get product category list by customerId from database");
			e.printStackTrace();
		}
		return productCategoryJsonModelList;
	}
	
	public List<ProductCategoryJsonModel> getProductCategoryByMarketPlaceId(String marketPlaceId) {
		List<ProductCategoryJsonModel> productCategoryJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
					.and("marketPlaceId").is(marketPlaceId));
			productCategoryJsonModelList = this.mongoOps.find(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
		} catch(Exception e) {
			LOGGERS.error("error while get product category list by marketPlaceId from database");
			e.printStackTrace();
		}
		return productCategoryJsonModelList;
	}

	public boolean deleteProductCategoryById(String productCategoryId, String customerId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
				.and("_id").is(productCategoryId).and("customerId").is(customerId));
			ProductCategoryJsonModel productCategoryJsonModel = this.mongoOps.findAndRemove(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
			if(productCategoryJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by Id in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteProductCategoryByNameAndCustomerId(String customerId, String categoryName) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
				.and("customerId").is(customerId).and("categoryName").is(categoryName));
			ProductCategoryJsonModel productCategoryJsonModel = this.mongoOps.findAndRemove(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
			if(productCategoryJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by name and customerId in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteProductCategoryByCustomerId(String customerId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
				.and("customerId").is(customerId));
			this.mongoOps.remove(query, SalesChannelConstants.SC_PRODUCT_CATEGORY);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by customerId in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteProductCategoryByMarketPlaceId(String marketPlaceId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
				.and("marketPlaceId").is(marketPlaceId));
			this.mongoOps.remove(query, SalesChannelConstants.SC_PRODUCT_CATEGORY);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by marketPlaceId in database");
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryJsonModel isProductCategoryExist(ProductCategoryJsonModel productCategoryJsonModel) {
		ProductCategoryJsonModel categoryJsonModel = null;
		try {
			Query query = new Query();
			if(productCategoryJsonModel != null) {
				if(productCategoryJsonModel.getCustomerId() != null && !productCategoryJsonModel.getCustomerId().isEmpty()
						&& !productCategoryJsonModel.getCustomerId().equals("0") && productCategoryJsonModel.getCategoryName() != null 
						&& !productCategoryJsonModel.getCategoryName().isEmpty()) {
					query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryJsonModel")
							.and("customerId").is(productCategoryJsonModel.getCustomerId()).and("categoryName").is(productCategoryJsonModel.getCategoryName()));
					categoryJsonModel = this.mongoOps.findOne(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
				}
			}
		} catch(Exception e) {
			categoryJsonModel = new ProductCategoryJsonModel();
			LOGGERS.error("error while check product category exist in database");
			e.printStackTrace();
		}
		return categoryJsonModel;
	}
	
	public List<ProductCategoryColumnParametersJsonModel> getProductCategoryColumnParametersByCategoryId(String productCategoryId) {
		List<ProductCategoryColumnParametersJsonModel> productCategoryColumnParametersJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryColumnParametersJsonModel")
					.and("categoryId").is(productCategoryId));
			productCategoryColumnParametersJsonModelList = this.mongoOps.find(query, ProductCategoryColumnParametersJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_COLUMN_PARAMETERS);
		} catch(Exception e) {
			LOGGERS.error("error while get ProductCategoryColumnParameters By CategoryId from database");
			e.printStackTrace();
		}
		return productCategoryColumnParametersJsonModelList;
	}
	
	public ProductCategoryColumnParametersJsonModel getProductCategoryColumnParameterById(String productCategoryColumnParameterId) {
		ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryColumnParametersJsonModel")
					.and("_id").is(productCategoryColumnParameterId));
			productCategoryColumnParametersJsonModel = this.mongoOps.findOne(query, ProductCategoryColumnParametersJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_COLUMN_PARAMETERS);
		} catch(Exception e) {
			LOGGERS.error("error while get ProductCategoryColumnParameter By Id from database");
			e.printStackTrace();
		}
		return productCategoryColumnParametersJsonModel;
	}
	
	public ProductCategoryColumnParametersJsonModel getProductCategoryColumnParameterByColumnNameAndCategoryId(String categoryId, String columnName) {
		ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryColumnParametersJsonModel")
					.and("categoryId").is(categoryId).and("columnName").is(columnName));
			productCategoryColumnParametersJsonModel = this.mongoOps.findOne(query, ProductCategoryColumnParametersJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_COLUMN_PARAMETERS);
		} catch(Exception e) {
			LOGGERS.error("error while get ProductCategoryColumnParameter By columnName from database");
			e.printStackTrace();
		}
		return productCategoryColumnParametersJsonModel;
	}
	
	public void insertProductCategoryColumnParameter(ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel) {
		String id = null;
		try {
			ObjectId objectId = new ObjectId();
			id = objectId.toString();
			productCategoryColumnParametersJsonModel.setId(id);
			this.mongoOps.insert(productCategoryColumnParametersJsonModel, SalesChannelConstants.SC_PRODUCT_CATEGORY_COLUMN_PARAMETERS);
		} catch(Exception e) {
			LOGGERS.error("error while insert product category column parameter in database");
			e.printStackTrace();
		}
	}
	
	public void insertCategoryColumnValidValues(CategoryColumnValidValuesJsonModel categoryColumnValidValuesJsonModel) {
		String id = null;
		try {
			ObjectId objectId = new ObjectId();
			id = objectId.toString();
			categoryColumnValidValuesJsonModel.setId(id);
			this.mongoOps.insert(categoryColumnValidValuesJsonModel, SalesChannelConstants.SC_CATEGORY_COLUMN_VALID_VALUES);
		} catch(Exception e) {
			LOGGERS.error("error while insert product category column valid values in database");
			e.printStackTrace();
		}
	}
	
	public List<CategoryColumnValidValuesJsonModel> getCategoryColumnValidValuesByColumnName(String columnName) {
		List<CategoryColumnValidValuesJsonModel> categoryColumnValidValuesJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.CategoryColumnValidValuesJsonModel")
					.and("columnName").is(columnName));
			categoryColumnValidValuesJsonModelList = this.mongoOps.find(query, CategoryColumnValidValuesJsonModel.class, SalesChannelConstants.SC_CATEGORY_COLUMN_VALID_VALUES);
		} catch(Exception e) {
			LOGGERS.error("error while get CategoryColumnValidValues By ColumnName from database");
			e.printStackTrace();
		}
		return categoryColumnValidValuesJsonModelList;
	}
	
	public void insertCategoryColumnValue(ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel) {
		String id = null;
		try {
			ObjectId objectId = new ObjectId();
			id = objectId.toString();
			productCategoryColumnValueJsonModel.setId(id);
			this.mongoOps.insert(productCategoryColumnValueJsonModel, SalesChannelConstants.SC_PRODUCT_CATEGORY_COLUMN_VALUES);
		} catch(Exception e) {
			LOGGERS.error("error while insert product category column values in database");
			e.printStackTrace();
		}
	}
	
	public void updateCategoryColumnValue(ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel) {
		try {
			this.mongoOps.save(productCategoryColumnValueJsonModel, SalesChannelConstants.SC_PRODUCT_CATEGORY_COLUMN_VALUES);
		} catch(Exception e) {
			LOGGERS.error("error while update product category column values in database");
			e.printStackTrace();
		}
	}
	
	public List<ProductCategoryColumnValueJsonModel> getProductCategoryColumnValuesByProductId(String productId) {
		List<ProductCategoryColumnValueJsonModel> productCategoryColumnValueJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryColumnValueJsonModel")
					.and("productId").is(productId));
			productCategoryColumnValueJsonModelList = this.mongoOps.find(query, ProductCategoryColumnValueJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_COLUMN_VALUES);
		} catch(Exception e) {
			LOGGERS.error("error while get ProductCategoryColumnValues By ProductId from database");
			e.printStackTrace();
		}
		return productCategoryColumnValueJsonModelList;
	}
	
	public ProductCategoryColumnValueJsonModel getProductCategoryColumnValueById(String id) {
		ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.productcategory.ProductCategoryColumnValueJsonModel")
					.and("_id").is(id));
			productCategoryColumnValueJsonModel = this.mongoOps.findOne(query, ProductCategoryColumnValueJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_COLUMN_VALUES);
		} catch(Exception e) {
			LOGGERS.error("error while get ProductCategoryColumnValue By id from database");
			e.printStackTrace();
		}
		return productCategoryColumnValueJsonModel;
	}
}
