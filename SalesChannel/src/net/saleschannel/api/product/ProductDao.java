package net.saleschannel.api.product;

import java.util.List;

public interface ProductDao {

	/*Product*/
	public String insertProduct(ProductJsonModel productJsonModel);
	
	public ProductJsonModel checkProductExist(String skuId, String customerId);
	
	public boolean updateProduct(ProductJsonModel productJsonModel);
	
	public boolean deleteProduct(String productId);
	
	public ProductJsonModel getProductById(String productId);
	
	public List<ProductJsonModel> getProductsByCustomer(String customerId);
	
	/*Attribute*/
	public boolean insertAttribute(AttributeJsonModel attribute);
	
	public AttributeJsonModel checkAttributeExist(String attributeName);
	
	public boolean updateAttribute(AttributeJsonModel attribute);
	
	public boolean deleteAttribute(String attributeId);
	
	public AttributeJsonModel getAttributeById(String attributeId);
	
	public List<AttributeJsonModel> getAttributesByIds(List<String> attributeIds);
	
	public List<AttributeJsonModel> getAllAttributes();
	
	/*ProductAttribute*/
	public String insertProductAttribute(ProductAttributesJsonModel productAttribute);
	
	public ProductAttributesJsonModel checkProductAttributeExist(String productId, String skuId);
	
	public boolean updateProductAttribute(ProductAttributesJsonModel productAttribute);
	
	public boolean deleteProductAttribute(String productAttributeId);
	
	public ProductAttributesJsonModel getProductAttributeById(String productAttributeId);
	
	public List<ProductAttributesJsonModel> getProductAttributeByProductId(String productId);
	
	public boolean deleteProductAttributeByProductId(String productId);
	
	/*ProductAttributeCombination*/
	public String insertProductAttributeCombination(ProductAttributeCombinationJsonModel productAttributeCombination);
	
	public boolean updateProductAttributeCombination(ProductAttributeCombinationJsonModel productAttributeCombination);
	
	public boolean deleteProductAttributeCombination(String productAttributeCombinationId);
	
	public ProductAttributeCombinationJsonModel getProductAttributeCombinationById(String productAttributeCombinationId);
	
	public List<ProductAttributeCombinationJsonModel> getProductAttributeCombinationByProductAttributId(String productAttributId);
	
	public boolean deleteProductAttributeCombinationByProductAttributId(String productAttributId);
	
	/*ProductAccessories*/
	public String insertProductAccessories(ProductAccessoriesJsonModel productAccessoriesJsonModel);
	
	public boolean updateProductAccessories(ProductAccessoriesJsonModel productAccessoriesJsonModel);
	
	public boolean deleteProductAccessoriesById(String productAccessoriesId);
	
	public boolean deleteProductAccessoriesByProductId(String productId);
	
	public ProductAccessoriesJsonModel getProductAccessoriesById(String productAccessoriesId);
	
	public List<ProductAccessoriesJsonModel> getProductAccessoriesByProductId(String productId);
	
	/*ProductImage*/
	public String insertProductImage(ProductImage productImage);
	
	public boolean updateProductImage(ProductImage productImage);
	
	public boolean deleteImageById(String productImageId);
	
	public boolean deleteProductImageByProductId(String productId);
	
	public ProductImage getProductImageById(String productImageId);
	
	public List<ProductImage> getProductImageByProductId(String productId);
}
