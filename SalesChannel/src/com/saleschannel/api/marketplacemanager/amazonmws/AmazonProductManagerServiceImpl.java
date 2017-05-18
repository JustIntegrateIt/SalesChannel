package com.saleschannel.api.marketplacemanager.amazonmws;

import java.util.List;

import org.apache.log4j.Logger;

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
import com.amazonservices.mws.products.model.ListMatchingProductsResponse;
import com.amazonservices.mws.products.model.MoneyType;
import com.amazonservices.mws.products.model.Points;
import com.amazonservices.mws.products.model.PriceToEstimateFees;
import com.amazonservices.mws.products.samples.GetCompetitivePricingForASINAsyncSample;
import com.amazonservices.mws.products.samples.GetCompetitivePricingForASINSample;
import com.amazonservices.mws.products.samples.GetCompetitivePricingForSKUAsyncSample;
import com.amazonservices.mws.products.samples.GetCompetitivePricingForSKUSample;
import com.amazonservices.mws.products.samples.GetLowestOfferListingsForASINAsyncSample;
import com.amazonservices.mws.products.samples.GetLowestOfferListingsForASINSample;
import com.amazonservices.mws.products.samples.GetLowestOfferListingsForSKUAsyncSample;
import com.amazonservices.mws.products.samples.GetLowestOfferListingsForSKUSample;
import com.amazonservices.mws.products.samples.GetLowestPricedOffersForASINAsyncSample;
import com.amazonservices.mws.products.samples.GetLowestPricedOffersForASINSample;
import com.amazonservices.mws.products.samples.GetLowestPricedOffersForSKUAsyncSample;
import com.amazonservices.mws.products.samples.GetLowestPricedOffersForSKUSample;
import com.amazonservices.mws.products.samples.GetMatchingProductAsyncSample;
import com.amazonservices.mws.products.samples.GetMatchingProductForIdAsyncSample;
import com.amazonservices.mws.products.samples.GetMatchingProductForIdSample;
import com.amazonservices.mws.products.samples.GetMatchingProductSample;
import com.amazonservices.mws.products.samples.GetMyFeesEstimateAsyncSample;
import com.amazonservices.mws.products.samples.GetMyFeesEstimateSample;
import com.amazonservices.mws.products.samples.GetMyPriceForASINAsyncSample;
import com.amazonservices.mws.products.samples.GetMyPriceForASINSample;
import com.amazonservices.mws.products.samples.GetMyPriceForSKUAsyncSample;
import com.amazonservices.mws.products.samples.GetMyPriceForSKUSample;
import com.amazonservices.mws.products.samples.GetProductCategoriesForASINAsyncSample;
import com.amazonservices.mws.products.samples.GetProductCategoriesForASINSample;
import com.amazonservices.mws.products.samples.GetProductCategoriesForSKUAsyncSample;
import com.amazonservices.mws.products.samples.GetProductCategoriesForSKUSample;
import com.amazonservices.mws.products.samples.GetServiceStatusAsyncSample;
import com.amazonservices.mws.products.samples.GetServiceStatusSample;
import com.amazonservices.mws.products.samples.ListMatchingProductsAsyncSample;
import com.amazonservices.mws.products.samples.ListMatchingProductsSample;

public class AmazonProductManagerServiceImpl implements AmazonProductManagerService {

	private static final Logger LOGGERS = Logger.getLogger(AmazonProductManagerServiceImpl.class);

	@Override
	public List<GetCompetitivePricingForASINResponse> getCompetitivePricingForASINAsync(
			String sellerId, String mwsAuthToken, String marketplaceId, List<String> asin) {
		List<GetCompetitivePricingForASINResponse> getCompetitivePricingForASINResponseList = null;
		try {
			GetCompetitivePricingForASINAsyncSample getCompetitivePricingForASINAsyncSample = new GetCompetitivePricingForASINAsyncSample();
			getCompetitivePricingForASINResponseList = getCompetitivePricingForASINAsyncSample.getCompetitivePricingForASINAsync(sellerId, 
					mwsAuthToken, marketplaceId, asin);
		} catch(Exception e) {
			LOGGERS.error("error occured while getCompetitivePricingForASINAsync");
			e.printStackTrace();
		}
		return getCompetitivePricingForASINResponseList;
	}

	@Override
	public GetCompetitivePricingForASINResponse getCompetitivePricingForASIN(String sellerId, 
			String mwsAuthToken, String marketplaceId, List<String> asin) {
		GetCompetitivePricingForASINResponse getCompetitivePricingForASINResponse = null;
		try {
			GetCompetitivePricingForASINSample getCompetitivePricingForASINSample = new GetCompetitivePricingForASINSample();
			getCompetitivePricingForASINResponse = getCompetitivePricingForASINSample.getCompetitivePricingForASIN(sellerId, 
					mwsAuthToken, marketplaceId, asin);
		} catch(Exception e) {
			LOGGERS.error("error occured while getCompetitivePricingForASIN");
			e.printStackTrace();
		}
		return getCompetitivePricingForASINResponse;
	}

	@Override
	public List<GetCompetitivePricingForSKUResponse> getCompetitivePricingForSKUAsync(
			String sellerId, String mwsAuthToken, String marketplaceId,
			List<String> sellerSKU) {
		List<GetCompetitivePricingForSKUResponse> getCompetitivePricingForSKUResponseList = null;
		try {
			GetCompetitivePricingForSKUAsyncSample getCompetitivePricingForSKUAsyncSample = new GetCompetitivePricingForSKUAsyncSample();
			getCompetitivePricingForSKUResponseList = getCompetitivePricingForSKUAsyncSample.getCompetitivePricingForSKUAsync(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
		} catch(Exception e) {
			LOGGERS.error("error occured while getCompetitivePricingForSKUAsync");
			e.printStackTrace();
		}
		return getCompetitivePricingForSKUResponseList;
	}

	@Override
	public GetCompetitivePricingForSKUResponse getCompetitivePricingForSKU(String sellerId, 
			String mwsAuthToken, String marketplaceId, List<String> sellerSKU) {
		GetCompetitivePricingForSKUResponse getCompetitivePricingForSKUResponse = null;
		try {
			GetCompetitivePricingForSKUSample getCompetitivePricingForSKUSample = new GetCompetitivePricingForSKUSample();
			getCompetitivePricingForSKUResponse = getCompetitivePricingForSKUSample.getCompetitivePricingForSKU(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
		} catch(Exception e) {
			LOGGERS.error("error occured while getCompetitivePricingForSKU");
			e.printStackTrace();
		}
		return getCompetitivePricingForSKUResponse;
	}

	@Override
	public List<GetLowestOfferListingsForASINResponse> getLowestOfferListingsForASINAsync(
			String sellerId, String mwsAuthToken, String marketplaceId,
			List<String> asin, String itemCondition,
			Boolean excludeMe) {
		List<GetLowestOfferListingsForASINResponse> getLowestOfferListingsForASINResponseList = null;
		try {
			GetLowestOfferListingsForASINAsyncSample getLowestOfferListingsForASINAsyncSample = new GetLowestOfferListingsForASINAsyncSample();
			getLowestOfferListingsForASINResponseList = getLowestOfferListingsForASINAsyncSample.getLowestOfferListingsForASINAsync(sellerId, mwsAuthToken
					, marketplaceId, asin, itemCondition, excludeMe);
		} catch(Exception e) {
			LOGGERS.error("error occured while getLowestOfferListingsForASINAsync");
			e.printStackTrace();
		}
		return getLowestOfferListingsForASINResponseList;
	}

	@Override
	public GetLowestOfferListingsForASINResponse getLowestOfferListingsForASIN(
			String sellerId, String mwsAuthToken, String marketplaceId,
			List<String> asin, String itemCondition,
			Boolean excludeMe) {
		GetLowestOfferListingsForASINResponse getLowestOfferListingsForASINResponse = null;
		try {
			GetLowestOfferListingsForASINSample getLowestOfferListingsForASINSample = new GetLowestOfferListingsForASINSample();
			getLowestOfferListingsForASINResponse = getLowestOfferListingsForASINSample.getLowestOfferListingsForASIN(sellerId, mwsAuthToken
					, marketplaceId, asin, itemCondition, excludeMe);
		} catch(Exception e) {
			LOGGERS.error("error occured while getLowestOfferListingsForASIN");
			e.printStackTrace();
		}
		return getLowestOfferListingsForASINResponse;
	}

	@Override
	public List<GetLowestOfferListingsForSKUResponse> getLowestOfferListingsForSKUAsync(
			String sellerId, String mwsAuthToken, String marketplaceId,
			List<String> sellerSKU,	String itemCondition, Boolean excludeMe) {
		List<GetLowestOfferListingsForSKUResponse> getLowestOfferListingsForSKUResponseList = null;
		try {
			GetLowestOfferListingsForSKUAsyncSample getLowestOfferListingsForSKUAsyncSample = new GetLowestOfferListingsForSKUAsyncSample();
			getLowestOfferListingsForSKUResponseList = getLowestOfferListingsForSKUAsyncSample.getLowestOfferListingsForSKUAsync(sellerId
					, mwsAuthToken, marketplaceId, sellerSKU, itemCondition, excludeMe);
		} catch(Exception e) {
			LOGGERS.error("error occured while getLowestOfferListingsForSKUAsync");
			e.printStackTrace();
		}
		return getLowestOfferListingsForSKUResponseList;
	}

	@Override
	public GetLowestOfferListingsForSKUResponse getLowestOfferListingsForSKU(String sellerId, 
			String mwsAuthToken, String marketplaceId, List<String> sellerSKU,
			String itemCondition, Boolean excludeMe) {
		GetLowestOfferListingsForSKUResponse getLowestOfferListingsForSKUResponse = null;
		try {
			GetLowestOfferListingsForSKUSample getLowestOfferListingsForSKUSample = new GetLowestOfferListingsForSKUSample();
			getLowestOfferListingsForSKUResponse = getLowestOfferListingsForSKUSample.getLowestOfferListingsForSKU(sellerId
					, mwsAuthToken, marketplaceId, sellerSKU, itemCondition, excludeMe);
		} catch(Exception e) {
			LOGGERS.error("error occured while getLowestOfferListingsForSKU");
			e.printStackTrace();
		}
		return getLowestOfferListingsForSKUResponse;
	}

	@Override
	public List<GetLowestPricedOffersForASINResponse> getLowestPricedOffersForASINAsync(String sellerId, 
			String mwsAuthToken, String marketplaceId, String asin, String itemCondition) {
		List<GetLowestPricedOffersForASINResponse> getLowestPricedOffersForASINResponseList = null;
		try {
			GetLowestPricedOffersForASINAsyncSample getLowestPricedOffersForASINAsyncSample = new GetLowestPricedOffersForASINAsyncSample();
			getLowestPricedOffersForASINResponseList = getLowestPricedOffersForASINAsyncSample.getLowestPricedOffersForASINAsync(sellerId, mwsAuthToken
					, marketplaceId, asin, itemCondition);
		} catch(Exception e) {
			LOGGERS.error("error occured while getLowestPricedOffersForASINAsync");
			e.printStackTrace();
		}
		return getLowestPricedOffersForASINResponseList;
	}

	@Override
	public GetLowestPricedOffersForASINResponse getLowestPricedOffersForASIN(String sellerId, 
			String mwsAuthToken, String marketplaceId, String asin, String itemCondition) {
		GetLowestPricedOffersForASINResponse getLowestPricedOffersForASINResponse = null;
		try {
			GetLowestPricedOffersForASINSample getLowestPricedOffersForASINSample = new GetLowestPricedOffersForASINSample();
			getLowestPricedOffersForASINResponse = getLowestPricedOffersForASINSample.getLowestPricedOffersForASIN(sellerId, mwsAuthToken
					, marketplaceId, asin, itemCondition);
		} catch(Exception e) {
			LOGGERS.error("error occured while getLowestPricedOffersForASIN");
			e.printStackTrace();
		}
		return getLowestPricedOffersForASINResponse;
	}

	@Override
	public List<GetLowestPricedOffersForSKUResponse> getLowestPricedOffersForSKUAsync(String sellerId, 
			String mwsAuthToken, String marketplaceId, String sellerSKU, String itemCondition) {
		List<GetLowestPricedOffersForSKUResponse> getLowestPricedOffersForSKUResponseList = null;
		try {
			GetLowestPricedOffersForSKUAsyncSample getLowestPricedOffersForSKUAsyncSample = new GetLowestPricedOffersForSKUAsyncSample();
			getLowestPricedOffersForSKUResponseList = getLowestPricedOffersForSKUAsyncSample.getLowestPricedOffersForSKUAsync(sellerId
					, mwsAuthToken, marketplaceId, sellerSKU, itemCondition);
		} catch(Exception e) {
			LOGGERS.error("error occured while getLowestPricedOffersForSKUAsync");
			e.printStackTrace();
		}
		return getLowestPricedOffersForSKUResponseList;
	}

	@Override
	public GetLowestPricedOffersForSKUResponse getLowestPricedOffersForSKU(String sellerId, 
			String mwsAuthToken, String marketplaceId, String sellerSKU, String itemCondition) {
		GetLowestPricedOffersForSKUResponse getLowestPricedOffersForSKUResponse = null;
		try {
			GetLowestPricedOffersForSKUSample getLowestPricedOffersForSKUSample = new GetLowestPricedOffersForSKUSample();
			getLowestPricedOffersForSKUResponse = getLowestPricedOffersForSKUSample.getLowestPricedOffersForSKU(sellerId
					, mwsAuthToken, marketplaceId, sellerSKU, itemCondition);
		} catch(Exception e) {
			LOGGERS.error("error occured while getLowestPricedOffersForSKU");
			e.printStackTrace();
		}
		return getLowestPricedOffersForSKUResponse;
	}

	@Override
	public List<GetMatchingProductResponse> getMatchingProductAsync(String sellerId, 
			String mwsAuthToken, String marketplaceId, List<String> asin) {
		List<GetMatchingProductResponse> getMatchingProductResponseList = null;
		try {
			GetMatchingProductAsyncSample getMatchingProductAsyncSample = new GetMatchingProductAsyncSample();
			getMatchingProductResponseList = getMatchingProductAsyncSample.getMatchingProductAsync(sellerId
					, mwsAuthToken, marketplaceId, asin);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMatchingProductAsync");
			e.printStackTrace();
		}
		return getMatchingProductResponseList;
	}

	@Override
	public GetMatchingProductResponse getMatchingProduct(String sellerId,
			String mwsAuthToken, String marketplaceId, List<String> asin) {
		GetMatchingProductResponse getMatchingProductResponse = null;
		try {
			GetMatchingProductSample getMatchingProductSample = new GetMatchingProductSample();
			getMatchingProductResponse = getMatchingProductSample.getMatchingProduct(sellerId
					, mwsAuthToken, marketplaceId, asin);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMatchingProduct");
			e.printStackTrace();
		}
		return getMatchingProductResponse;
	}

	@Override
	public List<GetMatchingProductForIdResponse> getMatchingProductForIdAsync(String sellerId, 
			String mwsAuthToken, String marketplaceId, String idType, List<String> id) {
		List<GetMatchingProductForIdResponse> getMatchingProductForIdResponseList = null;
		try {
			GetMatchingProductForIdAsyncSample getMatchingProductForIdAsyncSample = new GetMatchingProductForIdAsyncSample();
			getMatchingProductForIdResponseList = getMatchingProductForIdAsyncSample.getMatchingProductForIdAsync(sellerId, mwsAuthToken
					, marketplaceId, idType, id);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMatchingProductForIdAsync");
			e.printStackTrace();
		}
		return getMatchingProductForIdResponseList;
	}

	@Override
	public GetMatchingProductForIdResponse getMatchingProductForId(String sellerId, 
			String mwsAuthToken, String marketplaceId, String idType, List<String> id) {
		GetMatchingProductForIdResponse getMatchingProductForIdResponse = null;
		try {
			GetMatchingProductForIdSample getMatchingProductForIdSample = new GetMatchingProductForIdSample();
			getMatchingProductForIdResponse = getMatchingProductForIdSample.getMatchingProductForId(sellerId, mwsAuthToken
					, marketplaceId, idType, id);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMatchingProductForId");
			e.printStackTrace();
		}
		return getMatchingProductForIdResponse;
	}

	@Override
	public List<GetMyFeesEstimateResponse> getMyFeesEstimateAsync(String sellerId, 
			String mwsAuthToken, FeesEstimateRequestList feesEstimateRequestList,
			FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt,
			Points points, List<FeesEstimateRequest> feesEstimateRequest) {
		List<GetMyFeesEstimateResponse> getMyFeesEstimateResponseList = null;
		try {
			GetMyFeesEstimateAsyncSample getMyFeesEstimateAsyncSample = new GetMyFeesEstimateAsyncSample();
			getMyFeesEstimateResponseList = getMyFeesEstimateAsyncSample.getMyFeesEstimateAsync(sellerId, mwsAuthToken
					, feesEstimateRequestList, fer, pte, mt, points, feesEstimateRequest);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMyFeesEstimateAsync");
			e.printStackTrace();
		}
		return getMyFeesEstimateResponseList;
	}

	@Override
	public GetMyFeesEstimateResponse getMyFeesEstimate(String sellerId,
			String mwsAuthToken,
			FeesEstimateRequestList feesEstimateRequestList,
			FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt,
			Points points, List<FeesEstimateRequest> feesEstimateRequest) {
		GetMyFeesEstimateResponse getMyFeesEstimateResponse = null;
		try {
			GetMyFeesEstimateSample getMyFeesEstimateSample = new GetMyFeesEstimateSample();
			getMyFeesEstimateResponse = getMyFeesEstimateSample.getMyFeesEstimate(sellerId, mwsAuthToken
					, feesEstimateRequestList, fer, pte, mt, points, feesEstimateRequest);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMyFeesEstimate");
			e.printStackTrace();
		}
		return getMyFeesEstimateResponse;
	}

	@Override
	public List<GetMyPriceForASINResponse> getMyPriceForASINAsync(String sellerId, 
			String mwsAuthToken, String marketplaceId, List<String> asin) {
		List<GetMyPriceForASINResponse> getMyPriceForASINResponseList = null;
		try {
			GetMyPriceForASINAsyncSample getMyPriceForASINAsyncSample = new GetMyPriceForASINAsyncSample();
			getMyPriceForASINResponseList = getMyPriceForASINAsyncSample.getMyPriceForASINAsync(sellerId, mwsAuthToken
					, marketplaceId, asin);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMyPriceForASINAsync");
			e.printStackTrace();
		}
		return getMyPriceForASINResponseList;
	}

	@Override
	public GetMyPriceForASINResponse getMyPriceForASIN(String sellerId,
			String mwsAuthToken, String marketplaceId, List<String> asin) {
		GetMyPriceForASINResponse getMyPriceForASINResponse = null;
		try {
			GetMyPriceForASINSample getMyPriceForASINSample = new GetMyPriceForASINSample();
			getMyPriceForASINResponse = getMyPriceForASINSample.getMyPriceForASIN(sellerId, mwsAuthToken
					, marketplaceId, asin);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMyPriceForASIN");
			e.printStackTrace();
		}
		return getMyPriceForASINResponse;
	}

	@Override
	public List<GetMyPriceForSKUResponse> getMyPriceForSKUAsync(String sellerId, 
			String mwsAuthToken, String marketplaceId, List<String> sellerSKU) {
		List<GetMyPriceForSKUResponse> getMyPriceForSKUResponseList = null;
		try {
			GetMyPriceForSKUAsyncSample getMyPriceForSKUAsyncSample = new GetMyPriceForSKUAsyncSample();
			getMyPriceForSKUResponseList = getMyPriceForSKUAsyncSample.getMyPriceForSKUAsync(sellerId, mwsAuthToken
					, marketplaceId, sellerSKU);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMyPriceForSKUAsync");
			e.printStackTrace();
		}
		return getMyPriceForSKUResponseList;
	}

	@Override
	public GetMyPriceForSKUResponse getMyPriceForSKU(String sellerId,
			String mwsAuthToken, String marketplaceId, List<String> sellerSKU) {
		GetMyPriceForSKUResponse getMyPriceForSKUResponse = null;
		try {
			GetMyPriceForSKUSample getMyPriceForSKUSample = new GetMyPriceForSKUSample();
			getMyPriceForSKUResponse = getMyPriceForSKUSample.getMyPriceForSKU(sellerId, mwsAuthToken
					, marketplaceId, sellerSKU);
		} catch(Exception e) {
			LOGGERS.error("error occured while getMyPriceForSKU");
			e.printStackTrace();
		}
		return getMyPriceForSKUResponse;
	}

	@Override
	public List<GetProductCategoriesForASINResponse> getProductCategoriesForASINAsync(String sellerId, 
			String mwsAuthToken, String marketplaceId, String asin) {
		List<GetProductCategoriesForASINResponse> getProductCategoriesForASINResponseList = null;
		try {
			GetProductCategoriesForASINAsyncSample getProductCategoriesForASINAsyncSample = new GetProductCategoriesForASINAsyncSample();
			getProductCategoriesForASINResponseList = getProductCategoriesForASINAsyncSample.getProductCategoriesForASINAsync(sellerId, 
					mwsAuthToken, marketplaceId, asin);
		} catch(Exception e) {
			LOGGERS.error("error occured while getProductCategoriesForASINAsync");
			e.printStackTrace();
		}
		return getProductCategoriesForASINResponseList;
	}

	@Override
	public GetProductCategoriesForASINResponse getProductCategoriesForASIN(String sellerId, 
			String mwsAuthToken, String marketplaceId, String asin) {
		GetProductCategoriesForASINResponse getProductCategoriesForASINResponse = null;
		try {
			GetProductCategoriesForASINSample getProductCategoriesForASINSample = new GetProductCategoriesForASINSample();
			getProductCategoriesForASINResponse = getProductCategoriesForASINSample.getProductCategoriesForASIN(sellerId, 
					mwsAuthToken, marketplaceId, asin);
		} catch(Exception e) {
			LOGGERS.error("error occured while getProductCategoriesForASIN");
			e.printStackTrace();
		}
		return getProductCategoriesForASINResponse;
	}

	@Override
	public List<GetProductCategoriesForSKUResponse> getProductCategoriesForSKUAsync(String sellerId, 
			String mwsAuthToken, String marketplaceId, String sellerSKU) {
		List<GetProductCategoriesForSKUResponse> getProductCategoriesForSKUResponseList = null;
		try {
			GetProductCategoriesForSKUAsyncSample getProductCategoriesForSKUAsyncSample = new GetProductCategoriesForSKUAsyncSample();
			getProductCategoriesForSKUResponseList = getProductCategoriesForSKUAsyncSample.getProductCategoriesForSKUAsync(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
		} catch(Exception e) {
			LOGGERS.error("error occured while getProductCategoriesForSKUAsync");
			e.printStackTrace();
		}
		return getProductCategoriesForSKUResponseList;
	}
	
	@Override
	public GetProductCategoriesForSKUResponse getProductCategoriesForSKU(String sellerId, 
			String mwsAuthToken, String marketplaceId, String sellerSKU) {
		GetProductCategoriesForSKUResponse getProductCategoriesForSKUResponse = null;
		try {
			GetProductCategoriesForSKUSample getProductCategoriesForSKUSample = new GetProductCategoriesForSKUSample();
			getProductCategoriesForSKUResponse = getProductCategoriesForSKUSample.getProductCategoriesForSKU(sellerId, mwsAuthToken
					, marketplaceId, sellerSKU);
		} catch(Exception e) {
			LOGGERS.error("error occured while getProductCategoriesForSKU");
			e.printStackTrace();
		}
		return getProductCategoriesForSKUResponse;
	}

	@Override
	public List<GetServiceStatusResponse> getServiceStatusAsync(String sellerId, String mwsAuthToken) {
		List<GetServiceStatusResponse> getServiceStatusResponseList = null;
		try {
			GetServiceStatusAsyncSample getServiceStatusAsyncSample = new GetServiceStatusAsyncSample();
			getServiceStatusResponseList = getServiceStatusAsyncSample.getServiceStatusAsync(sellerId, mwsAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getServiceStatusAsync");
			e.printStackTrace();
		}
		return getServiceStatusResponseList;
	}
	
	@Override
	public GetServiceStatusResponse getServiceStatus(
			String sellerId, String mwsAuthToken) {
		GetServiceStatusResponse getServiceStatusResponse = null;
		try {
			GetServiceStatusSample getServiceStatusSample = new GetServiceStatusSample();
			getServiceStatusResponse = getServiceStatusSample.getServiceStatus(sellerId, mwsAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getServiceStatus");
			e.printStackTrace();
		}
		return getServiceStatusResponse;
	}

	@Override
	public List<ListMatchingProductsResponse> listMatchingProductsAsync(
			String sellerId, String mwsAuthToken, String marketplaceId,
			String query, String queryContextId) {
		List<ListMatchingProductsResponse> listMatchingProductsResponseList = null;
		try {
			ListMatchingProductsAsyncSample listMatchingProductsAsyncSample = new ListMatchingProductsAsyncSample();
			listMatchingProductsResponseList = listMatchingProductsAsyncSample.listMatchingProductsAsync(sellerId, 
					mwsAuthToken, marketplaceId, query, queryContextId);
		} catch(Exception e) {
			LOGGERS.error("error occured while listMatchingProductsAsync");
			e.printStackTrace();
		}
		return listMatchingProductsResponseList;
	}
	
	@Override
	public ListMatchingProductsResponse listMatchingProducts(
			String sellerId, String mwsAuthToken, String marketplaceId,
			String query, String queryContextId) {
		ListMatchingProductsResponse listMatchingProductsResponse = null;
		try {
			ListMatchingProductsSample listMatchingProductsSample = new ListMatchingProductsSample();
			listMatchingProductsResponse = listMatchingProductsSample.listMatchingProducts(sellerId, 
					mwsAuthToken, marketplaceId, query, queryContextId);
		} catch(Exception e) {
			LOGGERS.error("error occured while listMatchingProducts");
			e.printStackTrace();
		}
		return listMatchingProductsResponse;
	}
}
