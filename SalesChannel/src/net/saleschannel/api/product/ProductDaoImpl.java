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

	public boolean insertAttribute(Attribute attribute) {
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
	
	public Attribute checkAttributeExist(String attributeName) {
		Attribute attributeExist = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.Attribute")
					.and("name").is(attributeName));
			attributeExist = this.mongoOps.findOne(query, Attribute.class, SalesChannelConstants.SC_ATTRIBUTE);
		} catch(Exception e) {
			LOGGERS.error("error while insert attribute in database");
			e.printStackTrace();
		}
		return attributeExist;
	}

	public boolean updateAttribute(Attribute attribute) {
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

	public Attribute getAttributeById(String attributeId) {
		Attribute attribute = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.Attribute")
					.and("_id").is(new ObjectId(attributeId)));
			attribute = this.mongoOps.findOne(query, 
					Attribute.class, SalesChannelConstants.SC_ATTRIBUTE);			
		} catch(Exception e) {
			LOGGERS.error("error while get Attribute by id in database");
			e.printStackTrace();
		}
		return attribute;
	}

	public List<Attribute> getAttributesByIds(List<String> attributeIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Attribute> getAllAttributes() {
		List<Attribute> attributeList = null;
		try {
			attributeList = this.mongoOps.findAll(Attribute.class, SalesChannelConstants.SC_ATTRIBUTE);
		} catch(Exception e) {
			LOGGERS.error("error while fetch all attributes from database");
			e.printStackTrace();
		}
		return attributeList;
	}

	public String insertProductAttribute(ProductAttributes productAttribute) {
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

	public ProductAttributes checkProductAttributeExist(String productId, String skuId) {
		ProductAttributes productAttributesExist = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributes")
					.and("skuId").is(skuId).and("productId").is(productId));
			productAttributesExist = this.mongoOps.findOne(query, ProductAttributes.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);
		} catch(Exception e) {
			LOGGERS.error("error while insert attribute in database");
			e.printStackTrace();
		}
		return productAttributesExist;
	}
	
	public boolean updateProductAttribute(ProductAttributes productAttribute) {
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
		// TODO Auto-generated method stub
		return false;
	}

	public ProductAttributes getProductAttributeById(String productAttributeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductAttributes> getProductAttributeByProductId(String productId) {
		List<ProductAttributes> productAttributes = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributes")
					.and("productId").is(productId));
			productAttributes = this.mongoOps.find(query, ProductAttributes.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);			
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributes")
					.and("productId").is(productId));
			this.mongoOps.findAndRemove(query, ProductAttributes.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE);			
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while delete product attribute by productId in database");
			e.printStackTrace();
		}
		return status;
	}

	public String insertProductAttributeCombination(
			ProductAttributeCombination productAttributeCombination) {
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
			ProductAttributeCombination productAttributeCombination) {
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

	public boolean deleteProductAttributeCombination(
			String productAttributeCombinationId) {
		// TODO Auto-generated method stub
		return false;
	}

	public ProductAttributeCombination getProductAttributeCombinationById(
			String productAttributeCombinationId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductAttributeCombination> getProductAttributeCombinationByProductAttributId(
			String productAttributId) {
		List<ProductAttributeCombination> productAttributeCombinations = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributeCombination")
					.and("productAttributeId").is(productAttributId));
			productAttributeCombinations = this.mongoOps.find(query, ProductAttributeCombination.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE_COMBINATION);			
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
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.product.ProductAttributeCombination")
					.and("productAttributeId").is(productAttributId));
			this.mongoOps.findAndRemove(query, ProductAttributeCombination.class, SalesChannelConstants.SC_PRODUCT_ATTRIBUTE_COMBINATION);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while get Product Attribute Combination by productAttributId in database");
			e.printStackTrace();
		}
		return status;
	}

}
