package com.saleschannel.api.productcategory;

import java.util.List;

import com.saleschannel.api.productcategory.amazonmws.AmazonProductCategoriesJsonModel;

public interface ProductCategoryDao {

	public String insertProductCategory(ProductCategoryJsonModel productCategoryJsonModel);
	
	public boolean updateProductCategory(ProductCategoryJsonModel productCategoryJsonModel);
	
	public ProductCategoryJsonModel getProductCategoryById(String productCategoryId, String customerId);
	
	public ProductCategoryJsonModel getProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public List<ProductCategoryJsonModel> getProductCategoryByCustomerId(String customerId);
	
	public List<ProductCategoryJsonModel> getProductCategoryByMarketPlaceId(String marketPlaceId);
	
	public boolean deleteProductCategoryById(String productCategoryId, String customerId);
	
	public boolean deleteProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public ProductCategoryJsonModel isProductCategoryExist(ProductCategoryJsonModel productCategoryJsonModel);
	
	public List<ProductCategoryColumnParametersJsonModel> getProductCategoryColumnParametersByCategoryId(String productCategoryId);
	
	public ProductCategoryColumnParametersJsonModel getProductCategoryColumnParameterById(String productCategoryColumnParameterId);
	
	public ProductCategoryColumnParametersJsonModel getProductCategoryColumnParameterByColumnNameAndCategoryId(String categoryId, String columnName);
	
	public void insertProductCategoryColumnParameter(ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel);
	
	public void insertCategoryColumnValidValues(CategoryColumnValidValuesJsonModel categoryColumnValidValuesJsonModel);
	
	public List<CategoryColumnValidValuesJsonModel> getCategoryColumnValidValuesByColumnName(String columnName);
	
	public void insertCategoryColumnValue(ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel);
	
	public void updateCategoryColumnValue(ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel);
	
	public List<ProductCategoryColumnValueJsonModel> getProductCategoryColumnValuesByProductId(String productId);
	
	public ProductCategoryColumnValueJsonModel getProductCategoryColumnValueById(String id);
	
	public ProductCategoryColumnValueJsonModel getProductCategoryColumnValueByProductIdAndParamId(String productId, String paramId);
	
	public AmazonProductCategoriesJsonModel getAmazonProductCategoryById(String id);
}
