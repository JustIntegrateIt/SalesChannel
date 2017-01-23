package net.saleschannel.api.marketplace;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MarketPlaceDaoImpl implements MarketPlaceDao {

	private static final Logger LOGGERS = Logger.getLogger(MarketPlaceDaoImpl.class);

	private MongoOperations mongoOps;
 	
	public MarketPlaceDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	public List<MarketPlaceJsonModel> getAllMarketPlaces() {
		List<MarketPlaceJsonModel> marketPlaceJsonModelList = null;
		try {
			marketPlaceJsonModelList = this.mongoOps.findAll(MarketPlaceJsonModel.class);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market places from DB");
			e.printStackTrace();
		}
		return marketPlaceJsonModelList;
	}
	
	public List<MarketPlaceRegionJsonModel> getMarketPlaceRegions(String marketPlaceId) {
		List<MarketPlaceRegionJsonModel> marketPlaceRegionJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceRegionJsonModel")
					.and("marketPlaceId").is(marketPlaceId));
			marketPlaceRegionJsonModelList = this.mongoOps.find(query, MarketPlaceRegionJsonModel.class);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market places from DB");
			e.printStackTrace();
		}
		return marketPlaceRegionJsonModelList;
	}
}
