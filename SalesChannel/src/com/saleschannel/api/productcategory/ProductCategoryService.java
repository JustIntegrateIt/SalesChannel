package com.saleschannel.api.productcategory;

import java.util.List;

public interface ProductCategoryService {

	public ProductCategoryJsonObject convertProductCategoryJsonModelToObject(ProductCategoryJsonModel productCategoryJsonModel);
	
	public ProductCategoryJsonModel convertProductCategoryJsonObjectToModel(ProductCategoryJsonObject productCategoryJsonObject);
	
	public String insertProductCategory(ProductCategoryJsonObject categoryJsonObject);
	
	public boolean updateProductCategory(ProductCategoryJsonObject categoryJsonObject);
	
	public ProductCategoryJsonObject getProductCategoryById(String categoryId, String customerId);
	
	public ProductCategoryJsonObject getProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public List<ProductCategoryJsonObject> getProductCategoryByCustomerId(String customerId);
	
	public List<ProductCategoryJsonObject> getProductCategoryByMarketPlaceId(String marketPlaceId);
	
	public ProductCategoryJsonObject getProductCategoryByProductId(String productId);
	
	public boolean deleteProductCategoryById(String productCategoryId, String customerId);
	
	public boolean deleteProductCategoryByNameAndCustomerId(String customerId, String categoryName);
	
	public boolean deleteProductCategoryByCustomerId(String customerId);
	
	public boolean deleteProductCategoryByMarketPlaceId(String marketPlaceId);
	
	public ProductCategoryJsonModel isProductCategoryExist(ProductCategoryJsonObject categoryJsonObject);
	
	public ProductCategoryColumnParametersJsonObject convertProductCategoryColumnParametersJsonModelToObject(ProductCategoryColumnParametersJsonModel productCategoryColumnParametersJsonModel);
	
	public ProductCategoryColumnParametersJsonModel convertProductCategoryColumnParametersJsonObjectToModel(ProductCategoryColumnParametersJsonObject productCategoryColumnParametersJsonObject);
	
	public List<ProductCategoryColumnParametersJsonObject> getProductCategoryColumnParametersByCategoryId(String productCategoryId);
	
	public void insertProductCategoryColumnParameter(ProductCategoryColumnParametersJsonObject productCategoryColumnParametersJsonObject);
	
	public void insertCategoryColumnValidValues(CategoryColumnValidValuesJsonObject categoryColumnValidValuesJsonObject);
	
	public CategoryColumnValidValuesJsonModel convertCategoryColumnValidValuesJsonObjectToModel(CategoryColumnValidValuesJsonObject categoryColumnValidValuesJsonObject);
	
	public CategoryColumnValidValuesJsonObject convertCategoryColumnValidValuesJsonModelToObject(CategoryColumnValidValuesJsonModel categoryColumnValidValuesJsonModel);
	
	public List<CategoryColumnValidValuesJsonObject> getCategoryColumnValidValuesByColumnName(String columnName);
	
	public List<ProductCategoryColumnValueJsonObject> getProductCategoryColumnValuesByProductId(String productId);
	
	public ProductCategoryColumnValueJsonObject getProductCategoryColumnValueById(String id);
	
	public ProductCategoryColumnValueJsonObject getProductCategoryColumnValueByProductIdAndParamId(String productId, String paramId);
	
	public ProductCategoryColumnValueJsonModel convertProductCategoryColumnValuesJsonObjectToModel(ProductCategoryColumnValueJsonObject productCategoryColumnValueJsonObject);
	
	public ProductCategoryColumnValueJsonObject convertProductCategoryColumnValuesJsonModelToObject(ProductCategoryColumnValueJsonModel productCategoryColumnValueJsonModel);
	
	public void insertUpdateProductCategoryColumnValues(CategoryColumnsValueJsonObject categoryColumnsValueJsonObject);
}
