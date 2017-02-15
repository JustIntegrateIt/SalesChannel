package com.saleschannel.api.productcategorymapping;

import java.util.List;

public interface ProductCategoryMappingDao {

	public String insertProductCategoryMapping(ProductCategoryMappingJsonModel productCategoryMappingJsonModel);
	
	public boolean updateProductCategoryMapping(ProductCategoryMappingJsonModel productCategoryMappingJsonModel);
	
	public ProductCategoryMappingJsonModel getProductCategoryMappingById(String productCategoryMappingId);
	
	public List<ProductCategoryMappingJsonModel> getProductCategoryMappingByCustomerId(String customerId);
	
	public List<ProductCategoryMappingJsonModel> getProductCategoryMappingByCustomerIdAndMarketPlaceId(String customerId, String marketPlaceId);
	
	public boolean deleteProductCategoryMappingById(String productCategoryMappingId);
	
	public boolean deleteProductCategoryMappingByCustomerId(String customerId);
	
	public boolean deleteProductCategoryMappingByCustomerIdAndMarketPlaceId(String customerId, String marketPlaceId);
	
	public ProductCategoryMappingJsonModel isProductCategoryMappingExist(ProductCategoryMappingJsonModel productCategoryMappingJsonModel);
}
