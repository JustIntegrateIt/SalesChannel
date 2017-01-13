package net.saleschannel.api.product;

import java.util.List;

public interface ProductService {

	public String insertProduct(ProductJsonModel productJsonModel);
	
	public ProductJsonModel checkProductExist(String skuId, String customerId);
	
	public boolean updateProduct(ProductJsonModel productJsonModelNew);
	
	public boolean deleteProduct(ProductJsonModel productJsonModel);
	
	public boolean deleteProductAttributes(String productId);
	
	public boolean deleteProducts(String customerId);
	
	public ProductJsonModel getProductById(String productId);
	
	public ProductJsonModel getProductBySkuId(String skuId);
	
	public List<ProductJsonModel> getProductsByCustomer(String customerId);
	
	public ProductAttributesJsonModel checkProductAttributeExist(String productId, String skuId);
	
	public List<AttributeJsonModel> prepareAttributes(List<ProductAttributeSetModel> productAttributeSetModelList);
	
	public List<ProductAttributesJsonModel> prepareProductAttributes(List<ProductAttributeSetModel> productAttributeSetModelList);
	
	public List<ProductAttributeCombinationJsonModel> prepareProductAttributeCombination(List<ProductAttributeSetModel> productAttributeSetModelList, String productId);
	
	public ProductJsonModel prepareProductJsonModel(ProductJsonModel productJsonModel);
}
