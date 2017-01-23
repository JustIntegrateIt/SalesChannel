package net.saleschannel.api.marketplace;

import java.util.List;

public interface MarketPlaceDao {
	
	public List<MarketPlaceJsonModel> getAllMarketPlaces();
	
	public List<MarketPlaceRegionJsonModel> getMarketPlaceRegions(String marketPlaceId);

}
