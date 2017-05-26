package com.saleschannel.api.product.amazonmws;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.XML;

import com.amazonaws.mws.model.GetReportRequestListResponse;
import com.amazonaws.mws.model.ReportRequestInfo;
import com.amazonaws.mws.model.RequestReportResponse;
import com.amazonservices.mws.products.model.FeesEstimateRequest;
import com.amazonservices.mws.products.model.FeesEstimateRequestList;
import com.amazonservices.mws.products.model.GetMatchingProductForIdResponse;
import com.amazonservices.mws.products.model.GetMatchingProductForIdResult;
import com.amazonservices.mws.products.model.GetProductCategoriesForSKUResponse;
import com.amazonservices.mws.products.model.MoneyType;
import com.amazonservices.mws.products.model.Points;
import com.amazonservices.mws.products.model.PriceToEstimateFees;
import com.google.gson.Gson;
import com.saleschannel.api.base.ThreadJsonObject;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.constants.amazonmws.AmazonProductTypes;
import com.saleschannel.api.constants.amazonmws.ListingsReportsType;
import com.saleschannel.api.marketplacemanager.amazonmws.AmazonProductManagerServiceImpl;
import com.saleschannel.api.marketplacemanager.amazonmws.AmazonReportManagerServiceImpl;
import com.saleschannel.api.product.ProductDaoImpl;
import com.saleschannel.api.product.ProductJsonModel;
import com.saleschannel.api.productcategory.CategoryColumnsValueJsonObject;
import com.saleschannel.api.productcategory.ProductCategoryColumnParametersJsonObject;
import com.saleschannel.api.productcategory.ProductCategoryColumnValueJsonObject;
import com.saleschannel.api.productcategory.ProductCategoryJsonObject;
import com.saleschannel.api.productcategory.ProductCategoryServiceImpl;
import com.saleschannel.api.productcategory.amazonmws.AmazonProductCategoriesJsonModel;
import com.saleschannel.api.utility.SalesChannelUtility;

public class AmazonProductServiceImpl implements AmazonProductService, Runnable {

	private static final Logger LOGGERS = Logger.getLogger(AmazonProductServiceImpl.class);
	
	private ProductDaoImpl productDao;
	
	private ProductCategoryServiceImpl categoryService;
	
	private AmazonProductManagerServiceImpl amazonProductManagerService;
	
	private AmazonReportManagerServiceImpl amazonReportManagerService;
	
	public Thread thread;
	
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
			GetMatchingProductForIdResponse getMatchingProductForIdResponse = amazonProductManagerService.getMatchingProductForId(sellerId, mwsAuthToken, 
					marketplaceId, idType, id);
			if(getMatchingProductForIdResponse != null) {
				List<GetMatchingProductForIdResult> getMatchingProductForIdResult = 
						getMatchingProductForIdResponse.getGetMatchingProductForIdResult();
				for(GetMatchingProductForIdResult matchingProductForId : getMatchingProductForIdResult) {
					System.out.println(XML.toJSONObject(getMatchingProductForIdResponse.toXML()));
					LOGGERS.error("matchingProductForId"+matchingProductForId.getId());
				}
			}
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
	public String getProductCategoriesForSKU(String sellerId,
			String marketplaceId, String sellerSKU) {
		String categoryId = null;
		try {
			String mwsAuthToken = SalesChannelConstants.authToken;
			GetProductCategoriesForSKUResponse getProductCategoriesForSKUResponse = amazonProductManagerService.getProductCategoriesForSKU(sellerId, 
					mwsAuthToken, marketplaceId, sellerSKU);
			categoryId = null;
			if(getProductCategoriesForSKUResponse != null && getProductCategoriesForSKUResponse.getGetProductCategoriesForSKUResult() != null) {
				if(getProductCategoriesForSKUResponse.getGetProductCategoriesForSKUResult().getSelf() != null 
						&& getProductCategoriesForSKUResponse.getGetProductCategoriesForSKUResult().getSelf().size() > 0) {
					categoryId = getProductCategoriesForSKUResponse.getGetProductCategoriesForSKUResult().getSelf().get(0).getProductCategoryId();
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get ProductCategoriesForSKU in Amazon Product");
			e.printStackTrace();
		}
		return categoryId;
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
	public boolean requestReport(String merchantId, List<String> marketplacesIds, String customerId) {
		boolean status = false;
		try {
			String sellerDevAuthToken = SalesChannelConstants.authToken;
			String reportType = ListingsReportsType.GET_MERCHANT_LISTINGS_ALL_DATA.toString();
			RequestReportResponse requestReportResponse = amazonReportManagerService
					.requestReport(merchantId, reportType, sellerDevAuthToken, marketplacesIds);
			if(requestReportResponse != null && requestReportResponse.getRequestReportResult() != null 
					&& requestReportResponse.getRequestReportResult().getReportRequestInfo() != null 
					&& requestReportResponse.getRequestReportResult().getReportRequestInfo().getReportRequestId() != null
					&& !requestReportResponse.getRequestReportResult().getReportRequestInfo().getReportRequestId().isEmpty()) {
				String responseId = requestReportResponse.getRequestReportResult().getReportRequestInfo().getReportRequestId();
				if(responseId != null && !responseId.isEmpty()) {
					List<String> responseIds = new ArrayList<String>();
					responseIds.add(responseId);
					ThreadJsonObject threadJsonObject = new ThreadJsonObject();
					threadJsonObject.setResponseIds(responseIds);
					threadJsonObject.setMarketplacesIds(marketplacesIds);
					threadJsonObject.setCustomerId(customerId);
					threadJsonObject.setThreadName("RequestReport");
					String threadValues = new JSONObject(threadJsonObject).toString();
					thread = new Thread(this);
					thread.setName(threadValues);
					thread.start();
					status = true;
				} else {
					LOGGERS.error("RequestReport in Amazon Product responseId is not available");
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while requestReport in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getReportRequestList(String merchantId, List<String> reportRequestIds, String customerId, List<String> marketplacesIds) {
		boolean status = false;
		try {
			String sellerDevAuthToken = SalesChannelConstants.authToken;
			GetReportRequestListResponse getReportRequestListResponse = amazonReportManagerService
					.getReportRequestList(merchantId, sellerDevAuthToken, reportRequestIds);
			if(getReportRequestListResponse != null && getReportRequestListResponse.getGetReportRequestListResult() != null 
					&& getReportRequestListResponse.getGetReportRequestListResult().getReportRequestInfoList() != null
					&& getReportRequestListResponse.getGetReportRequestListResult().getReportRequestInfoList().size() > 0) {
				ReportRequestInfo reportRequestInfo = getReportRequestListResponse.getGetReportRequestListResult().getReportRequestInfoList().get(0);
				if(reportRequestInfo != null && reportRequestInfo.getGeneratedReportId() != null 
						&& !reportRequestInfo.getGeneratedReportId().isEmpty()) {
					getReport(merchantId, reportRequestInfo.getGeneratedReportId(), customerId, marketplacesIds);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while get ReportRequestList in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean getReport(String merchantId, String reportId, String customerId, List<String> marketplacesIds) {
		boolean status = false;
		try {
			String sellerDevAuthToken = SalesChannelConstants.authToken;
			String reportPathCSV = SalesChannelConstants.FLATFILE_RESPONSE_SOURCE_PATH 
					+ SalesChannelConstants.FILE_SEPERATOR + customerId + SalesChannelConstants.FILE_SEPERATOR 
					+ UUID.randomUUID().toString().replace("-", "") + ".csv";
			String reportPathXLSX = reportPathCSV.replace(".csv", ".xlsx");
			amazonReportManagerService.getReport(merchantId, sellerDevAuthToken, reportPathCSV, reportId);
			//convert file csv To XLSX
			SalesChannelUtility.csvToXLSX(reportPathCSV, reportPathXLSX);
			XSSFSheet xlsxSheet = null;
            FileInputStream sourceFile = new FileInputStream(new File(reportPathXLSX));
        	XSSFWorkbook xlsxWorkBook = new XSSFWorkbook(sourceFile);
        	if(xlsxWorkBook != null) {
        		xlsxSheet = xlsxWorkBook.getSheetAt(0);
        	}
        	Cell headerCell = null;
        	Cell valueCell = null;
        	if(xlsxSheet != null) {
        		Row headerRow = xlsxSheet.getRow(1);
        		for(int i = 2; i <= xlsxSheet.getLastRowNum(); i++) {
        			ProductJsonModel productJsonModel = new ProductJsonModel();
        			List<ProductCategoryColumnValueJsonObject> productCategoryColumnValueList = new ArrayList<ProductCategoryColumnValueJsonObject>();
        			Row valueRow = xlsxSheet.getRow(i);
        			Iterator<Cell> headerRowCells = headerRow.iterator();
        			int j = 0;
        			while (headerRowCells.hasNext() && headerRow.getLastCellNum() > j) {
        				headerCell = headerRow.getCell(j);
        				if(headerCell != null && headerCell.toString().equals("item-name")) {
        					valueCell = valueRow.getCell(j);
        					productJsonModel.setProductName(valueCell.getStringCellValue());
            			} else if(headerCell != null && headerCell.toString().equals("item-description")) {
            				valueCell = valueRow.getCell(j);
            				productJsonModel.setDescription(valueCell.getStringCellValue());
            			} else if(headerCell != null && headerCell.toString().equals("listing-id")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("seller-sku")) {
            				valueCell = valueRow.getCell(j);
            				productJsonModel.setSkuId(valueCell.getStringCellValue());
            			} else if(headerCell != null && headerCell.toString().equals("price")) {
            				valueCell = valueRow.getCell(j);
            				productJsonModel.setCost(Integer.parseInt(valueCell.getRichStringCellValue().toString()));
            			} else if(headerCell != null && headerCell.toString().equals("quantity")) {
            				valueCell = valueRow.getCell(j);
            				productJsonModel.setQuantity(Integer.parseInt(valueCell.getRichStringCellValue().toString()));
            			} else if(headerCell != null && headerCell.toString().equals("open-date")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("item-is-marketplace")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("product-id-type")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
        					ProductCategoryColumnValueJsonObject productCategoryColumnValue = new ProductCategoryColumnValueJsonObject();
        					productCategoryColumnValue.setCategoryColumnParameterName("external_product_id_type");
        					if(valueCell.getRichStringCellValue().toString().equals("1")) {
        						productCategoryColumnValue.setValue(AmazonProductTypes._1.toString());
        					} else if(valueCell.getRichStringCellValue().toString().equals("2")) {
        						productCategoryColumnValue.setValue(AmazonProductTypes._2.toString());
        					} else if(valueCell.getRichStringCellValue().toString().equals("3")) {
        						productCategoryColumnValue.setValue(AmazonProductTypes._3.toString());
        					} else if(valueCell.getRichStringCellValue().toString().equals("4")) {
        						productCategoryColumnValue.setValue(AmazonProductTypes._4.toString());
        					}
        					productCategoryColumnValueList.add(productCategoryColumnValue);
            			} else if(headerCell != null && headerCell.toString().equals("zshop-shipping-fee")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("item-note")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("item-condition")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
        					ProductCategoryColumnValueJsonObject productCategoryColumnValue = new ProductCategoryColumnValueJsonObject();
        					productCategoryColumnValue.setCategoryColumnParameterName("condition_type");
        					productCategoryColumnValue.setValue(valueCell.getRichStringCellValue().toString());
        					productCategoryColumnValueList.add(productCategoryColumnValue);
            			} else if(headerCell != null && headerCell.toString().equals("zshop-category1")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("zshop-browse-path")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("zshop-storefront-feature")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("asin1")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("asin2")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("asin3")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("will-ship-internationally")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("expedited-shipping")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("zshop-boldface")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("product-id")) {
            				valueCell = valueRow.getCell(j);
        					ProductCategoryColumnValueJsonObject productCategoryColumnValue = new ProductCategoryColumnValueJsonObject();
        					productCategoryColumnValue.setCategoryColumnParameterName("external_product_id");
        					productCategoryColumnValue.setValue(valueCell.getRichStringCellValue().toString());
        					productCategoryColumnValueList.add(productCategoryColumnValue);
            			} else if(headerCell != null && headerCell.toString().equals("bid-for-featured-placement")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("add-delete")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			} else if(headerCell != null && headerCell.toString().equals("pending-quantity")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
        					productJsonModel.setQuantity(Integer.parseInt(valueCell.getRichStringCellValue().toString()));
            			} else if(headerCell != null && headerCell.toString().equals("fulfillment-channel")) {
            				valueCell = valueRow.getCell(j);
        					valueCell.getRichStringCellValue();
            			}
        				j++;
        			}
        			//Insert Product In DB
        			if(productJsonModel != null) {
        				String categoryId = getProductCategoriesForSKU(merchantId, marketplacesIds.get(0), productJsonModel.getSkuId());
        				AmazonProductCategoriesJsonModel amazonProductCategories = categoryService.getCategoryDao().getAmazonProductCategoryById(categoryId);
        				if(amazonProductCategories != null && amazonProductCategories.getCategoryId() != null) {
        					productJsonModel.setProductCategory(amazonProductCategories.getCategoryName());
        					ProductJsonModel product = productDao.checkProductExist(productJsonModel.getSkuId(), customerId);
        					String productId = null;
        					if(product != null) {
        						productId = product.getId();
        						productDao.updateProduct(productJsonModel);
        					} else {
        						productId = productDao.insertProduct(productJsonModel);
        					}
        					//assigning Category Column ParameterId using categoryId
        					if(productId != null) {
        						if(productCategoryColumnValueList != null && productCategoryColumnValueList.size() > 0) {
        							for(ProductCategoryColumnValueJsonObject productCategoryColumnValue : productCategoryColumnValueList) {
        								productCategoryColumnValue.setProductId(productId);
        								ProductCategoryJsonObject productCategoryJsonObject =  categoryService.getProductCategoryById(amazonProductCategories.getCategoryId(), SalesChannelConstants.CUSTOMER_ID);
        								if(productCategoryJsonObject != null && productCategoryJsonObject.getParentId() != null && !productCategoryJsonObject.getParentId().isEmpty()) {
        									productCategoryJsonObject =  categoryService.getProductCategoryById(productCategoryJsonObject.getParentId(), SalesChannelConstants.CUSTOMER_ID);
        								}
        								if(productCategoryJsonObject != null) {
        									ProductCategoryColumnParametersJsonObject productCategoryColumnParameter = categoryService.getProductCategoryColumnParameterByColumnNameAndCategoryId(productCategoryJsonObject.getId(), productCategoryColumnValue.getCategoryColumnParameterName());
            								productCategoryColumnValue.setCategoryColumnParameterId(productCategoryColumnParameter.getId());
        								} else {
        									LOGGERS.error("Amazon Product category not available productId:"+productId);
        								}
        								ProductCategoryColumnValueJsonObject categoryColumnValue = categoryService.getProductCategoryColumnValueByProductIdAndParamId(productId, productCategoryColumnValue.getCategoryColumnParameterId());
        								if(categoryColumnValue != null) {
        									productCategoryColumnValue.setId(categoryColumnValue.getId());
        								}
        							}
        							//Insert Category Column values
        							CategoryColumnsValueJsonObject categoryColumnsValueJsonObject = new CategoryColumnsValueJsonObject();
        							categoryColumnsValueJsonObject.setCategoryColumnsValue(productCategoryColumnValueList);
        							categoryService.insertUpdateProductCategoryColumnValues(categoryColumnsValueJsonObject);
        						}
        					} else {
        						LOGGERS.error("Amazon Product Not inserted SKUID:"+productJsonModel.getSkuId());
        					}
        				} else {
        					LOGGERS.error("No ProductCategory for Amazon categoryId:" + categoryId + "& SKUID:"+productJsonModel.getSkuId());
        				}
        			}
        		}
        	}
        	xlsxWorkBook.close();
            sourceFile.close();
		} catch(Exception e) {
			LOGGERS.error("error occured while get Report in Amazon Product");
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public void run() {
		try {
			if(thread != null && thread.getName() != null && !thread.getName().isEmpty()) {
				LOGGERS.info("RequestReport Thread started "+ new Date());
				Thread.sleep(30000);
				LOGGERS.info("RequestReport Thread Awake " + new Date());
				Gson gson = new Gson();
				ThreadJsonObject object = gson.fromJson(thread.getName(), ThreadJsonObject.class);
				if(object.getThreadName() != null && !object.getThreadName().isEmpty() && object.getThreadName().equals("RequestReport")) {
					getReportRequestList(SalesChannelConstants.merchantIdSellerId, object.getResponseIds(), object.getCustomerId()
							, object.getMarketplacesIds());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
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

	public ProductCategoryServiceImpl getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ProductCategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}

}
