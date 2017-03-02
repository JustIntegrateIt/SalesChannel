package com.saleschannel.api.marketplacemanager.amazonmws;

import java.util.List;

import com.amazonservices.mws.products.model.ASINListType;
import com.amazonservices.mws.products.model.FeesEstimateRequest;
import com.amazonservices.mws.products.model.FeesEstimateRequestList;
import com.amazonservices.mws.products.model.GetCompetitivePricingForASINResponse;
import com.amazonservices.mws.products.model.GetCompetitivePricingForSKUResponse;
import com.amazonservices.mws.products.model.GetLowestOfferListingsForASINResponse;
import com.amazonservices.mws.products.model.GetLowestOfferListingsForSKUResponse;
import com.amazonservices.mws.products.model.GetLowestPricedOffersForASINResponse;
import com.amazonservices.mws.products.model.GetLowestPricedOffersForSKUResponse;
import com.amazonservices.mws.products.model.GetMatchingProductForIdResponse;
import com.amazonservices.mws.products.model.GetMatchingProductResponse;
import com.amazonservices.mws.products.model.GetMyFeesEstimateResponse;
import com.amazonservices.mws.products.model.GetMyPriceForASINResponse;
import com.amazonservices.mws.products.model.GetMyPriceForSKUResponse;
import com.amazonservices.mws.products.model.GetProductCategoriesForASINResponse;
import com.amazonservices.mws.products.model.GetProductCategoriesForSKUResponse;
import com.amazonservices.mws.products.model.GetServiceStatusResponse;
import com.amazonservices.mws.products.model.IdListType;
import com.amazonservices.mws.products.model.ListMatchingProductsResponse;
import com.amazonservices.mws.products.model.MoneyType;
import com.amazonservices.mws.products.model.Points;
import com.amazonservices.mws.products.model.PriceToEstimateFees;
import com.amazonservices.mws.products.model.SellerSKUListType;

public interface AmazonProductManagerService {

	public List<GetCompetitivePricingForASINResponse> getCompetitivePricingForASINAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, ASINListType asinList, List<String> asin);
	
	public GetCompetitivePricingForASINResponse getCompetitivePricingForASIN(String sellerId, String mwsAuthToken
    		, String marketplaceId, ASINListType asinList, List<String> asin);
	
	public List<GetCompetitivePricingForSKUResponse> getCompetitivePricingForSKUAsync(String sellerId, String mwsAuthToken, String marketplaceId, SellerSKUListType sellerSKUList
    		, List<String> sellerSKU);
	
	public GetCompetitivePricingForSKUResponse getCompetitivePricingForSKU(String sellerId, String mwsAuthToken, String marketplaceId, SellerSKUListType sellerSKUList
    		, List<String> sellerSKU);
	
	public List<GetLowestOfferListingsForASINResponse> getLowestOfferListingsForASINAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, ASINListType asinList, List<String> asin, String itemCondition, Boolean excludeMe);
	
	public GetLowestOfferListingsForASINResponse getLowestOfferListingsForASIN(String sellerId, String mwsAuthToken
    		, String marketplaceId, ASINListType asinList, List<String> asin, String itemCondition, Boolean excludeMe);
	
	public List<GetLowestOfferListingsForSKUResponse> getLowestOfferListingsForSKUAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, SellerSKUListType sellerSKUList, List<String> sellerSKU, String itemCondition, Boolean excludeMe);
	
	public GetLowestOfferListingsForSKUResponse getLowestOfferListingsForSKU(String sellerId, String mwsAuthToken
    		, String marketplaceId, SellerSKUListType sellerSKUList, List<String> sellerSKU, String itemCondition, Boolean excludeMe);
	
	public List<GetLowestPricedOffersForASINResponse> getLowestPricedOffersForASINAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, String asin, String itemCondition);
	
	public GetLowestPricedOffersForASINResponse getLowestPricedOffersForASIN(String sellerId, String mwsAuthToken
    		, String marketplaceId, String asin, String itemCondition);
	
	public List<GetLowestPricedOffersForSKUResponse> getLowestPricedOffersForSKUAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, String sellerSKU, String itemCondition);
	
	public GetLowestPricedOffersForSKUResponse getLowestPricedOffersForSKU(String sellerId, String mwsAuthToken
			, String marketplaceId, String sellerSKU, String itemCondition);
	 
	public List<GetMatchingProductResponse> getMatchingProductAsync(String sellerId, String mwsAuthToken
			, String marketplaceId, ASINListType asinList, List<String> asin);
	
	public GetMatchingProductResponse getMatchingProduct(String sellerId, String mwsAuthToken
    		, String marketplaceId, ASINListType asinList, List<String> asin);
	
	public List<GetMatchingProductForIdResponse> getMatchingProductForIdAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, String idType, IdListType idList, List<String> id);
	
	public GetMatchingProductForIdResponse getMatchingProductForId(String sellerId, String mwsAuthToken
    		, String marketplaceId, String idType, IdListType idList, List<String> id);
	
	public List<GetMyFeesEstimateResponse> getMyFeesEstimateAsync(String sellerId
    		, String mwsAuthToken, FeesEstimateRequestList feesEstimateRequestList
    		, FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt, Points points
    		, List<FeesEstimateRequest> feesEstimateRequest);
	
	public GetMyFeesEstimateResponse getMyFeesEstimate(String sellerId
    		, String mwsAuthToken, FeesEstimateRequestList feesEstimateRequestList
    		, FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt, Points points
    		, List<FeesEstimateRequest> feesEstimateRequest);
	
	public List<GetMyPriceForASINResponse> getMyPriceForASINAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, ASINListType asinList, List<String> asin);
	
	public GetMyPriceForASINResponse getMyPriceForASIN(String sellerId, String mwsAuthToken
    		, String marketplaceId, ASINListType asinList, List<String> asin);
	
	public List<GetMyPriceForSKUResponse> getMyPriceForSKUAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, SellerSKUListType sellerSKUList, List<String> sellerSKU);
	
	public GetMyPriceForSKUResponse getMyPriceForSKU(String sellerId, String mwsAuthToken
    		, String marketplaceId, SellerSKUListType sellerSKUList, List<String> sellerSKU);
	
    public List<GetProductCategoriesForASINResponse> getProductCategoriesForASINAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, String asin);
    
    public GetProductCategoriesForASINResponse getProductCategoriesForASIN(String sellerId, String mwsAuthToken, String marketplaceId,
			String asin);
    
    public List<GetProductCategoriesForSKUResponse> getProductCategoriesForSKUAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, String sellerSKU);
    
    public GetProductCategoriesForSKUResponse getProductCategoriesForSKU(String sellerId, String mwsAuthToken, String marketplaceId,
			String sellerSKU);
    
    public List<GetServiceStatusResponse> getServiceStatusAsync(String sellerId, String mwsAuthToken);
    
    public GetServiceStatusResponse getServiceStatus(String sellerId, String mwsAuthToken);
    
    public List<ListMatchingProductsResponse> listMatchingProductsAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, String query, String queryContextId);
    
    public ListMatchingProductsResponse listMatchingProducts(String sellerId, String mwsAuthToken, String marketplaceId,
			String query, String queryContextId);
}
