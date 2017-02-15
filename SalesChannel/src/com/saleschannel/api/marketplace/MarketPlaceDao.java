package com.saleschannel.api.marketplace;

import java.util.List;

public interface MarketPlaceDao {
	
	public List<MarketPlaceJsonModel> getAllMarketPlaces();
	
	public MarketPlaceJsonModel getMarketPlaceById(String marketPlaceId);
	
	public List<MarketPlaceRegionJsonModel> getMarketPlaceRegions(String marketPlaceId);
	
	public MarketPlaceRegionJsonModel getMarketPlaceRegionById(String id);
	
	public List<MarketPlaceHeadersJsonModel> getMarketPlaceHeaders(String marketPlaceId, String customerId);
	
	public MarketPlaceHeadersJsonModel getMarketPlaceHeaderById(String headerId);
	
	public String insertMarketPlaceCustomerRegion(MarketPlaceCustomerRegionJsonModel customerRegionJsonModel);
	
	public boolean updateMarketPlaceCustomerRegion(MarketPlaceCustomerRegionJsonModel customerRegionJsonModel);
	
	public MarketPlaceCustomerRegionJsonModel getMarketPlaceCustomerRegionById(String customerRegionId);
	
	public List<MarketPlaceCustomerRegionJsonModel> getMarketPlaceCustomerRegions(String marketPlaceId, String customerId);
	
	public boolean deleteMarketPlaceCustomerRegionById(String customerRegionId);
	
	public boolean deleteMarketPlaceCustomerRegions(String marketPlaceId, String customerId);
	
	public String insertMarketPlaceHeader(MarketPlaceHeadersJsonModel headersJsonModel);
	
	public boolean updateMarketPlaceHeader(MarketPlaceHeadersJsonModel headersJsonModel);
	
	public boolean deleteMarketPlaceHeader(MarketPlaceHeadersJsonModel headersJsonModel);

}
