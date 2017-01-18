package net.saleschannel.api.productcategory;

import java.util.List;

public interface ProductCategoryService {

	public ProductCategoryJsonObject convertProductCategoryJsonModelToObject(ProductCategoryJsonModel productCategoryJsonModel);
	
	public ProductCategoryJsonModel convertProductCategoryJsonObjectToModel(ProductCategoryJsonObject productCategoryJsonObject);
	
	public String insertProductCategory(ProductCategoryJsonObject categoryJsonObject);
	
	public boolean updateProductCategory(ProductCategoryJsonObject categoryJsonObject);
	
	public ProductCategoryJsonObject getProductCategoryById(String categoryId, String customerId);
	
	public ProductCategoryJsonObject getProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public List<ProductCategoryJsonObject> getProductCategoryByCustomerId(String customerId);
	
	public boolean deleteProductCategoryById(String productCategoryId, String customerId);
	
	public boolean deleteProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public boolean deleteProductCategoryByCustomerId(String customerId);
	
	public boolean deleteProductCategoryByMarketPlaceId(String marketPlaceId);
	
	public ProductCategoryJsonModel isProductCategoryExist(ProductCategoryJsonObject categoryJsonObject);
	
}
