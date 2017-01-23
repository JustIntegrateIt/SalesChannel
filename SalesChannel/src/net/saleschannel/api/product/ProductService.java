package net.saleschannel.api.product;

import java.util.List;

public interface ProductService {

	public ProductJsonObject convertProductSimpleJsonModelToObject(ProductJsonModel productJsonModel);
	
	public ProductJsonModel convertProductJsonObjectToModel(ProductJsonObject productJsonObject);
	
	public ProductAccessoriesJsonModel convertProductAccessoriesJsonObjectToModel(ProductAttributeSetJsonObject productAttributeSetJsonObject);
	
	public ProductAttributeSetJsonObject convertProductAccessoriesJsonModelToObject(ProductAccessoriesJsonModel productAccessoriesJsonModel);
	
	public String insertProduct(ProductJsonObject productJsonObject);
	
	public ProductJsonObject checkProductExist(String skuId, String customerId);
	
	public boolean updateProduct(ProductJsonObject productJsonObject);
	
	public boolean deleteProduct(ProductJsonObject productJsonObject);
	
	public boolean deleteProductJsonModel(ProductJsonModel productJsonModel);
	
	public boolean deleteProductAttributes(String productId);
	
	public boolean deleteProducts(String customerId);
	
	public ProductJsonObject getProductById(String productId);
	
	public ProductJsonObject getProductBySkuId(String skuId);
	
	public List<ProductJsonObject> getProductsByCustomer(String customerId);
	
	public ProductAttributesJsonModel checkProductAttributeExist(String productId, String skuId);
	
	public List<AttributeJsonModel> prepareAttributes(List<ProductAttributeSetModel> productAttributeSetModelList);
	
	public List<ProductAttributesJsonModel> prepareProductAttributes(List<ProductAttributeSetModel> productAttributeSetModelList);
	
	public List<ProductAttributeCombinationJsonModel> prepareProductAttributeCombination(List<ProductAttributeSetModel> productAttributeSetModelList, String productId);
	
	public ProductJsonObject prepareProductCompoundJsonObject(ProductJsonModel productJsonModel);
	
	public String insertProductAccessories(ProductAttributeSetJsonObject productAttributeSetJsonObject, String productId, String customerId);
}
