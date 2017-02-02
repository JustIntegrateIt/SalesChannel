package net.saleschannel.api.productcategorymapping;

import java.util.List;

import net.saleschannel.api.constants.SalesChannelConstants;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ProductCategoryMappingDaoImpl implements ProductCategoryMappingDao {

	private static final Logger LOGGERS = Logger.getLogger(ProductCategoryMappingDaoImpl.class);

	private MongoOperations mongoOps;
 	
	public ProductCategoryMappingDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	public String insertProductCategoryMapping(
			ProductCategoryMappingJsonModel productCategoryMappingJsonModel) {
		String productCategoryMappingId = null;
		try {
			ObjectId objectId = new ObjectId();
			productCategoryMappingId = objectId.toString();
			productCategoryMappingJsonModel.setId(productCategoryMappingId);
			this.mongoOps.insert(productCategoryMappingJsonModel, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
		} catch(Exception e) {
			LOGGERS.error("error while insert product category mapping in database");
			e.printStackTrace();
		}
		return productCategoryMappingId;
	}

	public boolean updateProductCategoryMapping(
			ProductCategoryMappingJsonModel productCategoryMappingJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(productCategoryMappingJsonModel, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update product category mapping in database");
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryMappingJsonModel getProductCategoryMappingById(
			String productCategoryMappingId) {
		ProductCategoryMappingJsonModel productCategoryMappingJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategorymapping.ProductCategoryMappingJsonModel")
					.and("_id").is(productCategoryMappingId));
			productCategoryMappingJsonModel = this.mongoOps.findOne(query, ProductCategoryMappingJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
		} catch(Exception e) {
			LOGGERS.error("error while get product category mapping by Id from database");
			e.printStackTrace();
		}
		return productCategoryMappingJsonModel;
	}

	public List<ProductCategoryMappingJsonModel> getProductCategoryMappingByCustomerId(
			String customerId) {
		List<ProductCategoryMappingJsonModel> productCategoryMappingJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategorymapping.ProductCategoryMappingJsonModel")
					.and("customerId").is(customerId));
			productCategoryMappingJsonModelList = this.mongoOps.find(query, ProductCategoryMappingJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
		} catch(Exception e) {
			LOGGERS.error("error while get product category mapping by CustomerId from database");
			e.printStackTrace();
		}
		return productCategoryMappingJsonModelList;
	}

	public List<ProductCategoryMappingJsonModel> getProductCategoryMappingByCustomerIdAndMarketPlaceId(String customerId, String marketPlaceId) {
		List<ProductCategoryMappingJsonModel> productCategoryMappingJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategorymapping.ProductCategoryMappingJsonModel")
					.and("customerId").is(customerId).and("marketPlaceId").is(marketPlaceId));
			productCategoryMappingJsonModelList = this.mongoOps.find(query, ProductCategoryMappingJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
		} catch(Exception e) {
			LOGGERS.error("error while get product category mapping by CustomerId and marketPlaceId from database");
			e.printStackTrace();
		}
		return productCategoryMappingJsonModelList;
	}

	public boolean deleteProductCategoryMappingById(
			String productCategoryMappingId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategorymapping.ProductCategoryMappingJsonModel")
				.and("_id").is(productCategoryMappingId));
			ProductCategoryMappingJsonModel productCategoryMappingJsonModel = this.mongoOps.findAndRemove(query, ProductCategoryMappingJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
			if(productCategoryMappingJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product category mapping by Id in database");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteProductCategoryMappingByCustomerIdAndMarketPlaceId(
			String customerId, String marketPlaceId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategorymapping.ProductCategoryMappingJsonModel")
				.and("customerId").is(customerId).and("marketPlaceId").is(marketPlaceId));
			this.mongoOps.remove(query, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product category by name and customerId in database");
			e.printStackTrace();
		}
		return status;
	}

	public ProductCategoryMappingJsonModel isProductCategoryMappingExist(
			ProductCategoryMappingJsonModel productCategoryMappingJsonModel) {
		ProductCategoryMappingJsonModel categoryMappingJsonModel = null;
		try {
			Query query = new Query();
			if(productCategoryMappingJsonModel != null) {
				if(productCategoryMappingJsonModel.getCustomerId() != null && !productCategoryMappingJsonModel.getCustomerId().isEmpty()
						&& !productCategoryMappingJsonModel.getCustomerId().equals("0") && productCategoryMappingJsonModel.getCustomerProductCategoryId() != null 
						&& !productCategoryMappingJsonModel.getCustomerProductCategoryId().isEmpty() && productCategoryMappingJsonModel.getMarketPlaceProductCategoryId() != null
						&& !productCategoryMappingJsonModel.getMarketPlaceProductCategoryId().isEmpty()) {
					query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategorymapping.ProductCategoryMappingJsonModel")
							.and("customerId").is(productCategoryMappingJsonModel.getCustomerId()).and("customerProductCategoryId").is(productCategoryMappingJsonModel.getCustomerProductCategoryId())
							.and("marketPlaceProductCategoryId").is(productCategoryMappingJsonModel.getMarketPlaceProductCategoryId()));
				}
			}
			categoryMappingJsonModel = this.mongoOps.findOne(query, ProductCategoryMappingJsonModel.class, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
		} catch(Exception e) {
			LOGGERS.error("error while check product category exist in database");
			e.printStackTrace();
		}
		return categoryMappingJsonModel;
	}

	public boolean deleteProductCategoryMappingByCustomerId(String customerId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.productcategorymapping.ProductCategoryMappingJsonModel")
				.and("customerId").is(customerId));
			this.mongoOps.remove(query, SalesChannelConstants.SC_PRODUCT_CATEGORY_MAPPING);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product category mapping by customerId in database");
			e.printStackTrace();
		}
		return status;
	}

}
