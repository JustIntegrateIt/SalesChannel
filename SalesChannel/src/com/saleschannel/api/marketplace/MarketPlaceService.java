package com.saleschannel.api.marketplace;

import java.util.List;

public interface MarketPlaceService {

	public MarketPlaceJsonObject convertMarketPlaceJsonModelToObject(MarketPlaceJsonModel marketPlaceJsonModel, String customerId);
	
	public List<MarketPlaceJsonObject> getAllMarketPlaces(String customerId);
	
	public MarketPlaceJsonObject getMarketPlaceById(String marketPlaceId, String customerId);
	
	public MarketPlaceRegionJsonObject convertMarketPlaceRegionJsonModelToObject(MarketPlaceRegionJsonModel marketPlaceRegionJsonModel);
	
	public MarketPlaceHeadersJsonObject convertMarketPlaceHeaderJsonModelToObject(MarketPlaceHeadersJsonModel marketPlaceHeadersJsonModel, String customerId);
	
	public List<MarketPlaceRegionJsonObject> getMarketPlaceRegions(String marketplaceId);
	
	public MarketPlaceRegionJsonModel getMarketPlaceRegionById(String id);
	
	public List<MarketPlaceHeadersJsonObject> getMarketPlaceHeaders(String marketPlaceId, String customerId);
	
	public MarketPlaceHeadersJsonModel getMarketPlaceHeaderById(String headerId);
	
	public List<MarketPlaceCustomerRegionJsonObject> getMarketPlaceCustomerRegions(String marketPlaceId, String customerId);
	
	public MarketPlaceCustomerRegionJsonObject getMarketPlaceCustomerRegionById(String customerRegionId);
	
	public MarketPlaceCustomerRegionJsonObject convertMarketPlaceCustomerRegionJsonModelToObject(MarketPlaceCustomerRegionJsonModel customerRegionJsonModel);
	
	public boolean insertUpdateMarketPlaceCustomerRegion(MarketPlaceCustomerRegionJsonObject customerRegionJsonObject);
	
	public boolean deleteMarketPlaceCustomerRegions(String marketPlaceId, String customerId);
	
	public boolean deleteMarketPlaceCustomerRegionById(String customerRegionId);
	
	public List<MarketPlaceHeadersJsonModel> convertMarketPlaceHeaderJsonObjectToModel(MarketPlaceCustomerRegionJsonObject customerRegionJsonObject);
}
