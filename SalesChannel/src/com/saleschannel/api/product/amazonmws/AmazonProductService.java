package com.saleschannel.api.product.amazonmws;

import java.util.List;

import com.amazonservices.mws.products.model.FeesEstimateRequest;
import com.amazonservices.mws.products.model.FeesEstimateRequestList;
import com.amazonservices.mws.products.model.MoneyType;
import com.amazonservices.mws.products.model.Points;
import com.amazonservices.mws.products.model.PriceToEstimateFees;

public interface AmazonProductService {

	public boolean getCompetitivePricingForASINAsync(String sellerId, String marketplaceId
			, List<String> asin);
	
	public boolean getCompetitivePricingForASIN(String sellerId, String marketplaceId
			, List<String> asin);
	
	public boolean getCompetitivePricingForSKUAsync(String sellerId, String marketplaceId
    		, List<String> sellerSKU);
	
	public boolean getCompetitivePricingForSKU(String sellerId, String marketplaceId
    		, List<String> sellerSKU);
	
	public boolean getLowestOfferListingsForASINAsync(String sellerId, String marketplaceId
			, List<String> asin, String itemCondition, Boolean excludeMe);
	
	public boolean getLowestOfferListingsForASIN(String sellerId, String marketplaceId
			, List<String> asin, String itemCondition, Boolean excludeMe);
	
	public boolean getLowestOfferListingsForSKUAsync(String sellerId, String marketplaceId
    		, List<String> sellerSKU, String itemCondition, Boolean excludeMe);
	
	public boolean getLowestOfferListingsForSKU(String sellerId, String marketplaceId
    		, List<String> sellerSKU, String itemCondition, Boolean excludeMe);
	
	public boolean getLowestPricedOffersForASINAsync(String sellerId, String marketplaceId
    		, String asin, String itemCondition);
	
	public boolean getLowestPricedOffersForASIN(String sellerId, String marketplaceId
    		, String asin, String itemCondition);
	
	public boolean getLowestPricedOffersForSKUAsync(String sellerId, String marketplaceId
    		, String sellerSKU, String itemCondition);
	
	public boolean getLowestPricedOffersForSKU(String sellerId
			, String marketplaceId, String sellerSKU, String itemCondition);
	 
	public boolean getMatchingProductAsync(String sellerId, String marketplaceId, List<String> asin);
	
	public boolean getMatchingProduct(String sellerId, String marketplaceId, List<String> asin);
	
	public boolean getMatchingProductForIdAsync(String sellerId, String marketplaceId
			, String idType, List<String> id);
	
	public boolean getMatchingProductForId(String sellerId, String marketplaceId
			, String idType, List<String> id);
	
	public boolean getMyFeesEstimateAsync(String sellerId, FeesEstimateRequestList feesEstimateRequestList
    		, FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt, Points points
    		, List<FeesEstimateRequest> feesEstimateRequest);
	
	public boolean getMyFeesEstimate(String sellerId, FeesEstimateRequestList feesEstimateRequestList
    		, FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt, Points points
    		, List<FeesEstimateRequest> feesEstimateRequest);
	
	public boolean getMyPriceForASINAsync(String sellerId, String marketplaceId, List<String> asin);
	
	public boolean getMyPriceForASIN(String sellerId, String marketplaceId, List<String> asin);
	
	public boolean getMyPriceForSKUAsync(String sellerId, String marketplaceId, List<String> sellerSKU);
	
	public boolean getMyPriceForSKU(String sellerId, String marketplaceId, List<String> sellerSKU);
	
    public boolean getProductCategoriesForASINAsync(String sellerId, String marketplaceId, String asin);
    
    public boolean getProductCategoriesForASIN(String sellerId, String marketplaceId, String asin);
    
    public boolean getProductCategoriesForSKUAsync(String sellerId, String marketplaceId, String sellerSKU);
    
    public String getProductCategoriesForSKU(String sellerId, String marketplaceId, String sellerSKU);
    
    public boolean getServiceStatusAsync(String sellerId);
    
    public boolean getServiceStatus(String sellerId);
    
    public boolean listMatchingProductsAsync(String sellerId, String marketplaceId
    		, String query, String queryContextId);
    
    public boolean listMatchingProducts(String sellerId, String marketplaceId,
			String query, String queryContextId);
    
    public boolean requestReport(String merchantId, List<String> marketplacesIds, String customerId);
    
    public boolean getReportRequestList(String merchantId, List<String> reportRequestIds, String customerId, List<String> marketplacesIds);
    
    public boolean getReport(String merchantId, String reportId, String customerId, List<String> marketplacesIds);

}
