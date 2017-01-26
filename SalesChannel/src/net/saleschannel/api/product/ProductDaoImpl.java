package net.saleschannel.api.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import net.saleschannel.api.constants.SalesChannelConstants;

public class ProductDaoImpl implements ProductDao {

	private static final Logger LOGGERS = Logger.getLogger(ProductDaoImpl.class);
	
	private MongoOperations mongoOps;
 	
	public ProductDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	public String insertProduct(ProductJsonModel productJsonModel) {
		String productId = null;
		try {
			ObjectId objectId = new ObjectId();
			productJsonModel.setId(objectId.toString());
			productId = objectId.toString(); 
			this.mongoOps.insert(productJsonModel, SalesChannelConstants.SC_PRODUCT);
		} catch(Exception e) {
			LOGGERS.error("error while insert product in database");
			e.printStackTrace();
		}
		return productId;
	}

	public ProductJsonModel checkProductExist(String skuId, String customerId) {
		ProductJsonModel productJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductJsonModel")
					.and("skuId").is(skuId).and("customerId").is(customerId));
			productJsonModel = this.mongoOps.findOne(query, 
					ProductJsonModel.class, SalesChannelConstants.SC_PRODUCT);
		} catch(Exception e) {
			LOGGERS.error("error while check product existance by skuId and customerId in database");
			e.printStackTrace();
		}
		return productJsonModel;
	}
	
	public boolean updateProduct(ProductJsonModel productJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(productJsonModel, SalesChannelConstants.SC_PRODUCT);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update product in database");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteProduct(String productId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductJsonModel")
					.and("_id").is(new ObjectId(productId)));
			ProductJsonModel productJsonModel = this.mongoOps.findAndRemove(query, 
					ProductJsonModel.class, SalesChannelConstants.SC_PRODUCT);
			if(productJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete by id product in database");
			e.printStackTrace();
		}
		return status;
	}

	public ProductJsonModel getProductById(String productId) {
		ProductJsonModel productJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductJsonModel")
					.and("_id").is(new ObjectId(productId)));
			productJsonModel = this.mongoOps.findOne(query, 
					ProductJsonModel.class, SalesChannelConstants.SC_PRODUCT);			
		} catch(Exception e) {
			LOGGERS.error("error while get product by id in database");
			e.printStackTrace();
		}
		return productJsonModel;
	}

	public List<ProductJsonModel> getProductsByCustomer(String customerId) {
		List<ProductJsonModel> products = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductJsonModel")
					.and("customerId").is(customerId));
			products = this.mongoOps.find(query, ProductJsonModel.class, SalesChannelConstants.SC_PRODUCT);			
		} catch(Exception e) {
			LOGGERS.error("error while get products by customerId in database");
			e.printStackTrace();
		}
		return products;
	}

	public boolean insertAttribute(AttributeJsonModel attribute) {
		boolean status = false;
		try {
			this.mongoOps.insert(attribute, SalesChannelConstants.SC_ATTRIBUTE);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while insert attribute in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public AttributeJsonModel checkAttributeExist(String attributeName) {
		AttributeJsonModel attributeExist = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.AttributeJsonModel")
					.and("name").is(attributeName));
			attributeExist = this.mongoOps.findOne(query, AttributeJsonModel.class, SalesChannelConstants.SC_ATTRIBUTE);
		} catch(Exception e) {
			LOGGERS.error("error while insert attribute in database");
			e.printStackTrace();
		}
		return attributeExist;
	}

	public boolean updateAttribute(AttributeJsonModel attribute) {
		boolean status = false;
		try {
			this.mongoOps.save(attribute, SalesChannelConstants.SC_ATTRIBUTE);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update attribute in database");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteAttribute(String attributeId) {
		// TODO Auto-generated method stub
		return false;
	}

	public AttributeJsonModel getAttributeById(String attributeId) {
		AttributeJsonModel attribute = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.AttributeJsonModel")
					.and("_id").is(new ObjectId(attributeId)));
			attribute = this.mongoOps.findOne(query, 
					AttributeJsonModel.class, SalesChannelConstants.SC_ATTRIBUTE);			
		} catch(Exception e) {
			LOGGERS.error("error while get Attribute by id in database");
			e.printStackTrace();
		}
		return attribute;
	}

	public List<AttributeJsonModel> getAttributesByIds(List<String> attributeIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<AttributeJsonModel> getAllAttributes() {
		List<AttributeJsonModel> attributeList = null;
		try {
			attributeList = this.mongoOps.findAll(AttributeJsonModel.class, SalesChannelConstants.SC_ATTRIBUTE);
		} catch(Exception e) {
			LOGGERS.error("error while fetch all attributes from database");
			e.printStackTrace();
		}
		return attributeList;
	}

	public String insertProductAttribute(ProductAttributesJsonModel productAttribute) {
		String productAttributeId = null;
		try {
			ObjectId objectId = new ObjectId();
			productAttribute.setId(objectId.toString());
			productAttributeId = objectId.toString(); 
			this.mongoOps.insert(productAttribute, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);
		} catch(Exception e) {
			LOGGERS.error("error while insert product attribute in database");
			e.printStackTrace();
		}
		return productAttributeId;
	}

	public ProductAttributesJsonModel checkProductAttributeExist(String productId, String skuId) {
		ProductAttributesJsonModel productAttributesExist = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributesJsonModel")
					.and("skuId").is(skuId).and("productId").is(productId));
			productAttributesExist = this.mongoOps.findOne(query, ProductAttributesJsonModel.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);
		} catch(Exception e) {
			LOGGERS.error("error while insert attribute in database");
			e.printStackTrace();
		}
		return productAttributesExist;
	}
	
	public boolean updateProductAttribute(ProductAttributesJsonModel productAttribute) {
		boolean status = false;
		try {
			this.mongoOps.save(productAttribute, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update product attribute in database");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteProductAttribute(String productAttributeId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributesJsonModel")
					.and("_id").is(new ObjectId(productAttributeId)));
			ProductAttributesJsonModel productAttributesJsonModel = this.mongoOps.findAndRemove(query, 
					ProductAttributesJsonModel.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);
			if(productAttributesJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete productAttributesJsonModel by id in database");
			e.printStackTrace();
		}
		return status;
	}

	public ProductAttributesJsonModel getProductAttributeById(String productAttributeId) {
		ProductAttributesJsonModel productAttributesJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributesJsonModel")
					.and("_id").is(productAttributeId));
			productAttributesJsonModel = this.mongoOps.findAndRemove(query, 
					ProductAttributesJsonModel.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);
		} catch(Exception e) {
			LOGGERS.error("error while get productAttributesJsonModel by id in database");
			e.printStackTrace();
		}
		return productAttributesJsonModel;
	}

	public List<ProductAttributesJsonModel> getProductAttributeByProductId(String productId) {
		List<ProductAttributesJsonModel> productAttributes = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributesJsonModel")
					.and("productId").is(productId));
			productAttributes = this.mongoOps.find(query, ProductAttributesJsonModel.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);			
		} catch(Exception e) {
			LOGGERS.error("error while get Product Attribute by productId in database");
			e.printStackTrace();
		}
		return productAttributes;
	}
	
	public boolean deleteProductAttributeByProductId(String productId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributesJsonModel")
					.and("productId").is(productId));
			this.mongoOps.remove(query, ProductAttributesJsonModel.class);			
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product attribute by productId in database");
			e.printStackTrace();
		}
		return status;
	}

	public String insertProductAttributeCombination(
			ProductAttributeCombinationJsonModel productAttributeCombination) {
		String productAttributeCombinationId = null;
		try {
			ObjectId objectId = new ObjectId();
			productAttributeCombinationId = objectId.toString();
			productAttributeCombination.setId(productAttributeCombinationId);
			this.mongoOps.insert(productAttributeCombination, 
					SalesChannelConstants.SC_PRODUCT_ATTRIBUTE_COMBINATION);
		} catch(Exception e) {
			LOGGERS.error("error while insert product attribute combination in database");
			e.printStackTrace();
		}
		return productAttributeCombinationId;
	}

	public boolean updateProductAttributeCombination(
			ProductAttributeCombinationJsonModel productAttributeCombination) {
		boolean status = false;
		try {
			this.mongoOps.save(productAttributeCombination, 
					SalesChannelConstants.SC_PRODUCT_ATTRIBUTE_COMBINATION);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update product attribute combination in database");
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteProductAttributeCombination(String productAttributeCombinationId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributeCombinationJsonModel")
					.and("_id").is(new ObjectId(productAttributeCombinationId)));
			ProductAttributeCombinationJsonModel productAttributeCombinationJsonModel = this.mongoOps.findAndRemove(query, 
					ProductAttributeCombinationJsonModel.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE_COMBINATION);
			if(productAttributeCombinationJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete productAttributeCombinationJsonModel by id in database");
			e.printStackTrace();
		}
		return status;
	}

	public ProductAttributeCombinationJsonModel getProductAttributeCombinationById(String productAttributeCombinationId) {
		ProductAttributeCombinationJsonModel productAttributeCombinationJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributeCombinationJsonModel")
					.and("_id").is(productAttributeCombinationId));
			productAttributeCombinationJsonModel = this.mongoOps.findOne(query, 
					ProductAttributeCombinationJsonModel.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE_COMBINATION);
		} catch(Exception e) {
			LOGGERS.error("error while get productAttributeCombinationJsonModel by id in database");
			e.printStackTrace();
		}
		return productAttributeCombinationJsonModel;
	}

	public List<ProductAttributeCombinationJsonModel> getProductAttributeCombinationByProductAttributId(
			String productAttributId) {
		List<ProductAttributeCombinationJsonModel> productAttributeCombinations = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributeCombinationJsonModel")
					.and("productAttributeId").is(productAttributId));
			productAttributeCombinations = this.mongoOps.find(query, ProductAttributeCombinationJsonModel.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE_COMBINATION);			
		} catch(Exception e) {
			LOGGERS.error("error while get Product Attribute Combination by productAttributId in database");
			e.printStackTrace();
		}
		return productAttributeCombinations;
	}
	
	public boolean deleteProductAttributeCombinationByProductAttributId(
			String productAttributId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributeCombinationJsonModel")
					.and("productAttributeId").is(productAttributId));
			this.mongoOps.remove(query, ProductAttributeCombinationJsonModel.class);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete Product Attribute Combination by productAttributId in database");
			e.printStackTrace();
		}
		return status;
	}

	public String insertProductAccessories(ProductAccessoriesJsonModel productAccessoriesJsonModel) {
		String productAccessoriesId = null;
		try {
			ObjectId objectId = new ObjectId();
			productAccessoriesId = objectId.toString();
			productAccessoriesJsonModel.setId(productAccessoriesId);
			this.mongoOps.insert(productAccessoriesJsonModel, 
					SalesChannelConstants.SC_PRODUCT_ACCESSORIES);
		} catch(Exception e) {
			LOGGERS.error("error while insert product accessories in database");
			e.printStackTrace();
		}
		return productAccessoriesId;
	}
	
	public boolean updateProductAccessories(ProductAccessoriesJsonModel productAccessoriesJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(productAccessoriesJsonModel, 
					SalesChannelConstants.SC_PRODUCT_ACCESSORIES);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update product accessories in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteProductAccessoriesById(String productAccessoriesId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAccessoriesJsonModel")
					.and("_id").is(new ObjectId(productAccessoriesId)));
			ProductAccessoriesJsonModel productAccessoriesJsonModel = this.mongoOps.findAndRemove(query, ProductAccessoriesJsonModel.class, SalesChannelConstants.SC_PRODUCT_ACCESSORIES);
			if(productAccessoriesJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete Product accessories by productAccessoriesId in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteProductAccessoriesByProductId(String productId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAccessoriesJsonModel")
					.and("productId").is(productId));
			this.mongoOps.remove(query, ProductAccessoriesJsonModel.class);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete Product Accessories by productId in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public ProductAccessoriesJsonModel getProductAccessoriesById(String productAccessoriesId) {
		ProductAccessoriesJsonModel productAccessoriesJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAccessoriesJsonModel")
					.and("_id").is(new ObjectId(productAccessoriesId)));
			productAccessoriesJsonModel = this.mongoOps.findOne(query, ProductAccessoriesJsonModel.class, SalesChannelConstants.SC_PRODUCT_ACCESSORIES);
		} catch(Exception e) {
			LOGGERS.error("error while get Product Accessories by productAccessoriesId in database");
			e.printStackTrace();
		}
		return productAccessoriesJsonModel;
	}
	
	public List<ProductAccessoriesJsonModel> getProductAccessoriesByProductId(String productId) {
		List<ProductAccessoriesJsonModel> productAccessoriesJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAccessoriesJsonModel")
					.and("productId").is(productId));
			productAccessoriesJsonModelList = this.mongoOps.find(query, ProductAccessoriesJsonModel.class, SalesChannelConstants.SC_PRODUCT_ACCESSORIES);
		} catch(Exception e) {
			LOGGERS.error("error while get Product Accessories by productId in database");
			e.printStackTrace();
		}
		return productAccessoriesJsonModelList;
	}
	
	public String insertProductImage(ProductImageJsonModel productImage) {
		String productImageId = null;
		try {
			ObjectId objectId = new ObjectId();
			productImageId = objectId.toString();
			productImage.setId(productImageId);
			this.mongoOps.insert(productImage, SalesChannelConstants.SC_PRODUCT_IMAGE);
		} catch(Exception e) {
			LOGGERS.error("error while insert product image in database");
			e.printStackTrace();
		}
		return productImageId;
	}
	
	public boolean updateProductImage(ProductImageJsonModel productImage) {
		boolean status = false;
		try {
			this.mongoOps.save(productImage, SalesChannelConstants.SC_PRODUCT_IMAGE);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update product image in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteImageById(String productImageId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductImageJsonModel")
					.and("_id").is(new ObjectId(productImageId)));
			ProductImageJsonModel productImageJsonModel = this.mongoOps.findAndRemove(query, ProductImageJsonModel.class, SalesChannelConstants.SC_PRODUCT_IMAGE);
			if(productImageJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete Product Image by productImageId in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteProductImageByProductId(String productId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductImageJsonModel")
					.and("productId").is(productId));
			this.mongoOps.remove(query, ProductImageJsonModel.class);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete Product Image by productId in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public ProductImageJsonModel getProductImageById(String productImageId) {
		ProductImageJsonModel productImage = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductImageJsonModel")
					.and("_id").is(new ObjectId(productImageId)));
			productImage = this.mongoOps.findOne(query, ProductImageJsonModel.class, SalesChannelConstants.SC_PRODUCT_IMAGE);
		} catch(Exception e) {
			LOGGERS.error("error while get Product Image by productImageId in database");
			e.printStackTrace();
		}
		return productImage;
	}
	
	public List<ProductImageJsonModel> getProductImageByProductId(String productId) {
		List<ProductImageJsonModel> productImageList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductImageJsonModel")
					.and("productId").is(productId));
			productImageList = this.mongoOps.find(query, ProductImageJsonModel.class, SalesChannelConstants.SC_PRODUCT_IMAGE);
		} catch(Exception e) {
			LOGGERS.error("error while get Product Image by productId in database");
			e.printStackTrace();
		}
		return productImageList;
	}
	
	public List<ProductImageJsonModel> getProductImageByProductAttributeId(String productAttributeId) {
		List<ProductImageJsonModel> productImageList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductImageJsonModel")
					.and("productAttributeId").is(productAttributeId));
			productImageList = this.mongoOps.find(query, ProductImageJsonModel.class, SalesChannelConstants.SC_PRODUCT_IMAGE);
		} catch(Exception e) {
			LOGGERS.error("error while get Product Image by productAttributeId in database");
			e.printStackTrace();
		}
		return productImageList;
	}
}
