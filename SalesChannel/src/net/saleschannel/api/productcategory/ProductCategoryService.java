package net.saleschannel.api.productcategory;

import java.util.List;

public interface ProductCategoryService {

	public String insertProductCategory(ProductCategoryJsonModel categoryJsonModel);
	
	public boolean updateProductCategory(ProductCategoryJsonModel categoryJsonModel);
	
	public ProductCategoryJsonModel getProductCategoryById(String categoryId);
	
	public ProductCategoryJsonModel getProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public List<ProductCategoryJsonModel> getProductCategoryByCustomerId(String customerId);
	
	public boolean deleteProductCategoryById(String productCategoryId);
	
	public boolean deleteProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public boolean deleteProductCategoryByCustomerId(String customerId);
	
	public boolean deleteProductCategoryByMarketPlaceId(String marketPlaceId);
	
	public ProductCategoryJsonModel isProductCategoryExist(ProductCategoryJsonModel categoryJsonModel);
	
}
