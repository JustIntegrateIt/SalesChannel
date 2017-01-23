package net.saleschannel.api.marketplace;

import java.util.List;

public interface MarketPlaceService {

	public MarketPlaceJsonObject convertMarketPlaceJsonModelToObject(MarketPlaceJsonModel marketPlaceJsonModel);
	
	public List<MarketPlaceJsonObject> getAllMarketPlaces();
	
	public MarketPlaceJsonObject convertMarketPlaceRegionJsonModelToObject(MarketPlaceRegionJsonModel marketPlaceRegionJsonModel);
	
	public List<MarketPlaceJsonObject> getMarketPlaceRegions(String marketplaceId);
}
