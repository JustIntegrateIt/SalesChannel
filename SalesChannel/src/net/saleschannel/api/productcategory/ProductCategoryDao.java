package net.saleschannel.api.productcategory;

import java.util.List;

public interface ProductCategoryDao {

	public String insertProductCategory(ProductCategoryJsonModel productCategoryJsonModel);
	
	public boolean updateProductCategory(ProductCategoryJsonModel productCategoryJsonModel);
	
	public ProductCategoryJsonModel getProductCategoryById(String productCategoryId);
	
	public ProductCategoryJsonModel getProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public List<ProductCategoryJsonModel> getProductCategoryByCustomerId(String customerId);
	
	public boolean deleteProductCategoryById(String productCategoryId);
	
	public boolean deleteProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public ProductCategoryJsonModel isProductCategoryExist(ProductCategoryJsonModel productCategoryJsonModel);
}
