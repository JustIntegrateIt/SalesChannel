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
	public boolean insertAttribute(Attribute attribute);
	
	public Attribute checkAttributeExist(String attributeName);
	
	public boolean updateAttribute(Attribute attribute);
	
	public boolean deleteAttribute(String attributeId);
	
	public Attribute getAttributeById(String attributeId);
	
	public List<Attribute> getAttributesByIds(List<String> attributeIds);
	
	public List<Attribute> getAllAttributes();
	
	/*ProductAttribute*/
	public String insertProductAttribute(ProductAttributes productAttribute);
	
	public ProductAttributes checkProductAttributeExist(String productId, String skuId);
	
	public boolean updateProductAttribute(ProductAttributes productAttribute);
	
	public boolean deleteProductAttribute(String productAttributeId);
	
	public ProductAttributes getProductAttributeById(String productAttributeId);
	
	public List<ProductAttributes> getProductAttributeByProductId(String productId);
	
	public boolean deleteProductAttributeByProductId(String productId);
	
	/*ProductAttributeCombination*/
	public String insertProductAttributeCombination(ProductAttributeCombination productAttributeCombination);
	
	public boolean updateProductAttributeCombination(ProductAttributeCombination productAttributeCombination);
	
	public boolean deleteProductAttributeCombination(String productAttributeCombinationId);
	
	public ProductAttributeCombination getProductAttributeCombinationById(String productAttributeCombinationId);
	
	public List<ProductAttributeCombination> getProductAttributeCombinationByProductAttributId(String productAttributId);
	
	public boolean deleteProductAttributeCombinationByProductAttributId(String productAttributId);
}
