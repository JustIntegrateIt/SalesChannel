package net.saleschannel.api.marketplace;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class MarketPlaceServiceImpl implements MarketPlaceService {

	private static final Logger LOGGERS = Logger.getLogger(MarketPlaceServiceImpl.class);
	
	private MarketPlaceDaoImpl marketPlaceDao;
	
	public MarketPlaceJsonObject convertMarketPlaceJsonModelToObject(MarketPlaceJsonModel marketPlaceJsonModel) {
		MarketPlaceJsonObject marketPlaceJsonObject = null;
		try {
			if(marketPlaceJsonModel != null) {
				marketPlaceJsonObject = new MarketPlaceJsonObject();
				marketPlaceJsonObject.setId(marketPlaceJsonModel.getId());
				marketPlaceJsonObject.setMarketPlaceName(marketPlaceJsonModel.getMarketPlaceName());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertMarketPlaceJsonModelToObject");
			e.printStackTrace();
		}
		return marketPlaceJsonObject;
	}
	
	public List<MarketPlaceJsonObject> getAllMarketPlaces() {
		List<MarketPlaceJsonObject> marketPlaceJsonObjectList = null;
		try {
			List<MarketPlaceJsonModel> marketPlaceJsonModelList = marketPlaceDao.getAllMarketPlaces();
			if(marketPlaceJsonModelList != null && marketPlaceJsonModelList.size() > 0) {
				marketPlaceJsonObjectList = new ArrayList<MarketPlaceJsonObject>();
				for(MarketPlaceJsonModel marketPlaceJsonModel : marketPlaceJsonModelList) {
					MarketPlaceJsonObject marketPlaceJsonObject = convertMarketPlaceJsonModelToObject(marketPlaceJsonModel);
					marketPlaceJsonObjectList.add(marketPlaceJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get all the marketplaces");
			e.printStackTrace();
		}
		return marketPlaceJsonObjectList;
	}
	
	public MarketPlaceJsonObject convertMarketPlaceRegionJsonModelToObject(MarketPlaceRegionJsonModel marketPlaceRegionJsonModel) {
		MarketPlaceJsonObject marketPlaceJsonObject = null;
		try {
			if(marketPlaceRegionJsonModel != null) {
				marketPlaceJsonObject = new MarketPlaceJsonObject();
				marketPlaceJsonObject.setId(marketPlaceRegionJsonModel.getId());
				if(marketPlaceRegionJsonModel.getMarketPlaceSubRegionName() != null && !marketPlaceRegionJsonModel.getMarketPlaceSubRegionName().isEmpty()) {
					marketPlaceJsonObject.setMarketPlaceName(marketPlaceRegionJsonModel.getMarketPlaceSubRegionName());	
				} else {
					marketPlaceJsonObject.setMarketPlaceName(marketPlaceRegionJsonModel.getMarketPlaceRegionName());
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertMarketPlaceRegionJsonModelToObject");
			e.printStackTrace();
		}
		return marketPlaceJsonObject;
	}
	
	public List<MarketPlaceJsonObject> getMarketPlaceRegions(String marketPlaceId) {
		List<MarketPlaceJsonObject> marketPlaceJsonObjectList = null;
		try {
			List<MarketPlaceRegionJsonModel> marketPlaceRegionJsonModelList = marketPlaceDao.getMarketPlaceRegions(marketPlaceId);
			if(marketPlaceRegionJsonModelList != null && marketPlaceRegionJsonModelList.size() > 0) {
				marketPlaceJsonObjectList = new ArrayList<MarketPlaceJsonObject>();
				for(MarketPlaceRegionJsonModel marketPlaceRegionJsonModel : marketPlaceRegionJsonModelList) {
					MarketPlaceJsonObject marketPlaceJsonObject = convertMarketPlaceRegionJsonModelToObject(marketPlaceRegionJsonModel);
					marketPlaceJsonObjectList.add(marketPlaceJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get marketplace regions");
			e.printStackTrace();
		}
		return marketPlaceJsonObjectList;
	}

	public MarketPlaceDaoImpl getMarketPlaceDao() {
		return marketPlaceDao;
	}

	public void setMarketPlaceDao(MarketPlaceDaoImpl marketPlaceDao) {
		this.marketPlaceDao = marketPlaceDao;
	}
}
