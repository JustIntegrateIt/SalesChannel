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
	
	public ProductAttributes checkProductAttributeExist(String productId, String skuId);
	
	public List<Attribute> prepareAttributes(List<ProductAttributeSetModel> productAttributeSetModelList);
	
	public List<ProductAttributes> prepareProductAttributes(List<ProductAttributeSetModel> productAttributeSetModelList);
	
	public List<ProductAttributeCombination> prepareProductAttributeCombination(List<ProductAttributeSetModel> productAttributeSetModelList, String productId);
	
	public ProductJsonModel prepareProductJsonModel(ProductJsonModel productJsonModel);
}
