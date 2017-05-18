package com.saleschannel.api.product.amazonmws;

import java.util.List;

import org.apache.log4j.Logger;

import com.amazonservices.mws.products.model.FeesEstimateRequest;
import com.amazonservices.mws.products.model.FeesEstimateRequestList;
import com.amazonservices.mws.products.model.MoneyType;
import com.amazonservices.mws.products.model.Points;
import com.amazonservices.mws.products.model.PriceToEstimateFees;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.marketplacemanager.amazonmws.AmazonProductManagerServiceImpl;
import com.saleschannel.api.marketplacemanager.amazonmws.AmazonReportManagerServiceImpl;
import com.saleschannel.api.product.ProductDaoImpl;

public class AmazonProductServiceImpl implements AmazonProductService {

	private static final Logger LOGGERS = Logger.getLogger(AmazonProductServiceImpl.class);
	
	private ProductDaoImpl productDao;
	
	private AmazonProductManagerServiceImpl amazonProductManagerService;
	
	private AmazonReportManagerServiceImpl amazonReportManagerService;

	@Override
	public boolean getCompetitivePricingForASINAsync(String sellerId,
			String marketplaceId, List<String> asin) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getCompetitivePricingForASINAsync(sellerId, 
					mwsAuthToken, marketplaceId, asin);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get CompetitivePricingForASINAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getCompetitivePricingForASIN(String sellerId,
			String marketplaceId, List<String> asin) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getCompetitivePricingForASIN(sellerId, 
					mwsAuthToken, marketplaceId, asin);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get CompetitivePricingForASIN in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getCompetitivePricingForSKUAsync(String sellerId,
			String marketplaceId, List<String> sellerSKU) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getCompetitivePricingForSKUAsync(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get CompetitivePricingForSKUAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getCompetitivePricingForSKU(String sellerId,
			String marketplaceId, List<String> sellerSKU) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getCompetitivePricingForSKU(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get CompetitivePricingForSKU in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getLowestOfferListingsForASINAsync(String sellerId,
			String marketplaceId, List<String> asin, String itemCondition, 
			Boolean excludeMe) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getLowestOfferListingsForASINAsync(sellerId, 
					mwsAuthToken, marketplaceId, asin, itemCondition, excludeMe);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get LowestOfferListingsForASINAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getLowestOfferListingsForASIN(String sellerId,
			String marketplaceId, List<String> asin, String itemCondition, 
			Boolean excludeMe) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getLowestOfferListingsForASIN(sellerId, 
					mwsAuthToken, marketplaceId, asin, itemCondition, excludeMe);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get LowestOfferListingsForASIN in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getLowestOfferListingsForSKUAsync(String sellerId, String marketplaceId, 
			List<String> sellerSKU,	String itemCondition, Boolean excludeMe) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getLowestOfferListingsForSKUAsync(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU, itemCondition, excludeMe);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get LowestOfferListingsForSKUAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getLowestOfferListingsForSKU(String sellerId, String marketplaceId,
			List<String> sellerSKU, String itemCondition, Boolean excludeMe) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getLowestOfferListingsForSKU(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU, itemCondition, excludeMe);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get LowestOfferListingsForSKU in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getLowestPricedOffersForASINAsync(String sellerId,
			String marketplaceId, String asin, String itemCondition) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getLowestPricedOffersForASINAsync(sellerId, 
					mwsAuthToken, marketplaceId, asin, itemCondition);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get LowestPricedOffersForASINAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getLowestPricedOffersForASIN(String sellerId,
			String marketplaceId, String asin, String itemCondition) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getLowestPricedOffersForASIN(sellerId, 
					mwsAuthToken, marketplaceId, asin, itemCondition);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get LowestPricedOffersForASIN in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getLowestPricedOffersForSKUAsync(String sellerId,
			String marketplaceId, String sellerSKU, String itemCondition) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getLowestPricedOffersForSKUAsync(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU, itemCondition);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get LowestPricedOffersForSKUAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getLowestPricedOffersForSKU(String sellerId,
			String marketplaceId, String sellerSKU, String itemCondition) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getLowestPricedOffersForSKU(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU, itemCondition);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get LowestPricedOffersForSKU in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMatchingProductAsync(String sellerId,
			String marketplaceId, List<String> asin) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMatchingProductAsync(sellerId, 
					mwsAuthToken, marketplaceId, asin);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MatchingProductAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMatchingProduct(String sellerId, String marketplaceId, List<String> asin) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMatchingProduct(sellerId, 
					mwsAuthToken, marketplaceId, asin);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MatchingProduct in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMatchingProductForIdAsync(String sellerId,
			String marketplaceId, String idType, List<String> id) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMatchingProductForIdAsync(sellerId, 
					mwsAuthToken, marketplaceId, idType, id);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MatchingProductForIdAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMatchingProductForId(String sellerId,
			String marketplaceId, String idType, List<String> id) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMatchingProductForId(sellerId, mwsAuthToken, 
					marketplaceId, idType, id);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MatchingProductForId in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMyFeesEstimateAsync(String sellerId, FeesEstimateRequestList feesEstimateRequestList,
			FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt,
			Points points, List<FeesEstimateRequest> feesEstimateRequest) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMyFeesEstimateAsync(sellerId, 
					mwsAuthToken, feesEstimateRequestList, fer, pte, mt, points, feesEstimateRequest);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MyFeesEstimateAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMyFeesEstimate(String sellerId, FeesEstimateRequestList feesEstimateRequestList,
			FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt,
			Points points, List<FeesEstimateRequest> feesEstimateRequest) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMyFeesEstimate(sellerId, 
					mwsAuthToken, feesEstimateRequestList, fer, pte, mt, points, feesEstimateRequest);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MyFeesEstimate in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMyPriceForASINAsync(String sellerId, String marketplaceId, List<String> asin) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMyPriceForASINAsync(sellerId, 
					mwsAuthToken, marketplaceId, asin);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MyPriceForASINAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMyPriceForASIN(String sellerId, String marketplaceId, List<String> asin) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMyPriceForASIN(sellerId, 
					mwsAuthToken, marketplaceId, asin);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MyPriceForASIN in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMyPriceForSKUAsync(String sellerId, String marketplaceId, 
			List<String> sellerSKU) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMyPriceForSKUAsync(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MyPriceForSKUAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getMyPriceForSKU(String sellerId, String marketplaceId, List<String> sellerSKU) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getMyPriceForSKU(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get MyPriceForSKU in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getProductCategoriesForASINAsync(String sellerId,
			String marketplaceId, String asin) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getProductCategoriesForASINAsync(sellerId, 
					mwsAuthToken, marketplaceId, asin);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get ProductCategoriesForASINAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getProductCategoriesForASIN(String sellerId,
			String marketplaceId, String asin) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getProductCategoriesForASIN(sellerId, 
					mwsAuthToken, marketplaceId, asin);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get ProductCategoriesForASIN in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getProductCategoriesForSKUAsync(String sellerId,
			String marketplaceId, String sellerSKU) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getProductCategoriesForSKUAsync(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get ProductCategoriesForSKUAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getProductCategoriesForSKU(String sellerId,
			String marketplaceId, String sellerSKU) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getProductCategoriesForSKU(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get ProductCategoriesForSKU in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getServiceStatusAsync(String sellerId) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getServiceStatusAsync(sellerId, mwsAuthToken);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get ServiceStatusAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getServiceStatus(String sellerId) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.getServiceStatus(sellerId, mwsAuthToken);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get ServiceStatus in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean listMatchingProductsAsync(String sellerId,
			String marketplaceId, String query, String queryContextId) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.listMatchingProductsAsync(sellerId, 
					mwsAuthToken, marketplaceId, query, queryContextId);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while list MatchingProductsAsync in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean listMatchingProducts(String sellerId,
			String marketplaceId, String query, String queryContextId) {
		boolean status = false;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			amazonProductManagerService.listMatchingProducts(sellerId, 
					mwsAuthToken, marketplaceId, query, queryContextId);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while list MatchingProducts in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public boolean requestReport(String merchantId, List<String> marketplacesIds) {
		boolean status = false;
		try {
			String sellerDevAuthToken = SalesChannelConstants.authToken;
			amazonReportManagerService.requestReport(merchantId, sellerDevAuthToken, marketplacesIds);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while requestReport in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getReportRequestList(String merchantId) {
		boolean status = false;
		try {
			String sellerDevAuthToken = SalesChannelConstants.authToken;
			amazonReportManagerService.getReportRequestList(merchantId, sellerDevAuthToken);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get ReportRequestList in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getReport(String merchantId) {
		boolean status = false;
		try {
			String sellerDevAuthToken = SalesChannelConstants.authToken;
			String reportPath = null;
			amazonReportManagerService.getReport(merchantId, sellerDevAuthToken, reportPath);
			//save the response in DB
		} catch(Exception e) {
			LOGGERS.error("error occured while get Report in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	public AmazonReportManagerServiceImpl getAmazonReportManagerService() {
		return amazonReportManagerService;
	}

	public void setAmazonReportManagerService(AmazonReportManagerServiceImpl amazonReportManagerService) {
		this.amazonReportManagerService = amazonReportManagerService;
	}

	public ProductDaoImpl getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

	public AmazonProductManagerServiceImpl getAmazonProductManagerService() {
		return amazonProductManagerService;
	}

	public void setAmazonProductManagerService(
			AmazonProductManagerServiceImpl amazonProductManagerService) {
		this.amazonProductManagerService = amazonProductManagerService;
	}

}
