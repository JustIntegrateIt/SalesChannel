package net.saleschannel.api.productcategory;

import java.util.List;

import net.saleschannel.api.constants.SalesChannelConstants;

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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
				.and("_id").is(productCategoryId).and("customerId").is(customerId));
			this.mongoOps.findAndRemove(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
				.and("customerId").is(customerId).and("categoryName").is(categoryName));
			this.mongoOps.findAndRemove(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
				.and("customerId").is(customerId));
			this.mongoOps.remove(query, ProductCategoryJsonModel.class);
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
				.and("marketPlaceId").is(marketPlaceId));
			this.mongoOps.remove(query, ProductCategoryJsonModel.class);
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
					query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategory.ProductCategoryJsonModel")
							.and("customerId").is(productCategoryJsonModel.getCustomerId()).and("categoryName").is(productCategoryJsonModel.getCategoryName()));
					categoryJsonModel = this.mongoOps.findOne(query, ProductCategoryJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY);
				} else {
					categoryJsonModel = new ProductCategoryJsonModel();
				}
			}  else {
				categoryJsonModel = new ProductCategoryJsonModel();
			}
		} catch(Exception e) {
			categoryJsonModel = new ProductCategoryJsonModel();
			LOGGERS.error("error while check product category exist in database");
			e.printStackTrace();
		}
		return categoryJsonModel;
	}
}
