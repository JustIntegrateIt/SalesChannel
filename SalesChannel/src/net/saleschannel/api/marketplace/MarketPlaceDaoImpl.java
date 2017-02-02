package net.saleschannel.api.marketplace;

import java.util.List;

import net.saleschannel.api.constants.SalesChannelConstants;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
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
			marketPlaceJsonModelList = this.mongoOps.findAll(MarketPlaceJsonModel.class, SalesChannelConstants.SC_MARKETPLACE);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market places from DB");
			e.printStackTrace();
		}
		return marketPlaceJsonModelList;
	}
	
	public MarketPlaceJsonModel getMarketPlaceById(String marketPlaceId) {
		MarketPlaceJsonModel marketPlaceJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceJsonModel")
					.and("_id").is(marketPlaceId));
			marketPlaceJsonModel = this.mongoOps.findOne(query, MarketPlaceJsonModel.class, SalesChannelConstants.SC_MARKETPLACE);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market places by marketPlaceId from DB");
			e.printStackTrace();
		}
		return marketPlaceJsonModel;
	}
	
	public List<MarketPlaceRegionJsonModel> getMarketPlaceRegions(String marketPlaceId) {
		List<MarketPlaceRegionJsonModel> marketPlaceRegionJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceRegionJsonModel")
					.and("marketPlaceId").is(marketPlaceId));
			marketPlaceRegionJsonModelList = this.mongoOps.find(query, MarketPlaceRegionJsonModel.class, SalesChannelConstants.SC_MARKETPLACE_REGION);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market places from DB");
			e.printStackTrace();
		}
		return marketPlaceRegionJsonModelList;
	}
	
	public MarketPlaceRegionJsonModel getMarketPlaceRegionById(String id) {
		MarketPlaceRegionJsonModel marketPlaceRegion = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceRegionJsonModel")
					.and("_id").is(id));
			marketPlaceRegion = this.mongoOps.findOne(query, MarketPlaceRegionJsonModel.class, SalesChannelConstants.SC_MARKETPLACE_REGION);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market places from DB");
			e.printStackTrace();
		}
		return marketPlaceRegion;
	}
	
	public String insertMarketPlaceCustomerRegion(MarketPlaceCustomerRegionJsonModel customerRegionJsonModel) {
		String id = null;
		try {
			ObjectId objectId = new ObjectId();
			customerRegionJsonModel.setId(objectId.toString());
			id = objectId.toString(); 
			this.mongoOps.insert(customerRegionJsonModel, SalesChannelConstants.SC_MARKETPLACE_CUSTOMER);
		} catch(Exception e) {
			LOGGERS.error("error while insert customerRegion JsonModel in database");
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean updateMarketPlaceCustomerRegion(MarketPlaceCustomerRegionJsonModel customerRegionJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(customerRegionJsonModel, SalesChannelConstants.SC_MARKETPLACE_CUSTOMER);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update customerRegion JsonModel in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public List<MarketPlaceHeadersJsonModel> getMarketPlaceHeaders(String marketPlaceId, String customerId) {
		List<MarketPlaceHeadersJsonModel> marketPlaceHeadersJsonModelList = null;
		try {
			if(customerId == null || customerId.isEmpty()) {
				customerId = "0";
			}
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceHeadersJsonModel")
					.and("marketPlaceId").is(marketPlaceId).and("customerId").is(customerId));
			marketPlaceHeadersJsonModelList = this.mongoOps.find(query, MarketPlaceHeadersJsonModel.class, SalesChannelConstants.SC_MARKETPLACE_HEADER);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market place headers by marketPlaceId and customerId from DB");
			e.printStackTrace();
		}
		return marketPlaceHeadersJsonModelList;
	}
	
	public MarketPlaceHeadersJsonModel getMarketPlaceHeaderById(String headerId) {
		MarketPlaceHeadersJsonModel headersJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceHeadersJsonModel")
					.and("_id").is(headerId));
			headersJsonModel = this.mongoOps.findOne(query, MarketPlaceHeadersJsonModel.class, SalesChannelConstants.SC_MARKETPLACE_HEADER);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market place header by headerId from DB");
			e.printStackTrace();
		}
		return headersJsonModel;
	}
	
	public MarketPlaceCustomerRegionJsonModel getMarketPlaceCustomerRegionById(String customerRegionId) {
		MarketPlaceCustomerRegionJsonModel customerRegionJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceCustomerRegionJsonModel")
					.and("_id").is(customerRegionId));
			customerRegionJsonModel = this.mongoOps.findOne(query, MarketPlaceCustomerRegionJsonModel.class, SalesChannelConstants.SC_MARKETPLACE_CUSTOMER);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market places by customerRegionId from DB");
			e.printStackTrace();
		}
		return customerRegionJsonModel;
	}
	
	public List<MarketPlaceCustomerRegionJsonModel> getMarketPlaceCustomerRegions(String marketPlaceId, String customerId) {
		List<MarketPlaceCustomerRegionJsonModel> customerRegionJsonModelList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceCustomerRegionJsonModel"));
			if(marketPlaceId != null && !marketPlaceId.isEmpty()) {
				query.addCriteria(Criteria.where("marketPlaceId").is(marketPlaceId));
			}
			if(customerId != null && !customerId.isEmpty()) {
				query.addCriteria(Criteria.where("customerId").is(customerId));
			}
			customerRegionJsonModelList = this.mongoOps.find(query, MarketPlaceCustomerRegionJsonModel.class, SalesChannelConstants.SC_MARKETPLACE_CUSTOMER);
		} catch(Exception e) {
			LOGGERS.error("error occured while fetch market place customer region by marketPlaceId and customerId from DB");
			e.printStackTrace();
		}
		return customerRegionJsonModelList;
	}
	
	public boolean deleteMarketPlaceCustomerRegionById(String customerRegionId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceCustomerRegionJsonModel")
					.and("_id").is(customerRegionId));
			MarketPlaceCustomerRegionJsonModel customerRegionJsonModel = this.mongoOps.findAndRemove(query, MarketPlaceCustomerRegionJsonModel.class, SalesChannelConstants.SC_MARKETPLACE_CUSTOMER);
			if(customerRegionJsonModel != null)
				status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete market places by customerRegionId from DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteMarketPlaceCustomerRegions(String marketPlaceId, String customerId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceCustomerRegionJsonModel"));
			if(marketPlaceId != null && !marketPlaceId.isEmpty()) {
				query.addCriteria(Criteria.where("marketPlaceId").is(marketPlaceId));
			}
			if(customerId != null && !customerId.isEmpty()) {
				query.addCriteria(Criteria.where("customerId").is(customerId));
			}
			this.mongoOps.remove(query, SalesChannelConstants.SC_MARKETPLACE_CUSTOMER);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while delete market places by customerRegionId from DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public String insertMarketPlaceHeader(MarketPlaceHeadersJsonModel headersJsonModel) {
		String id = null;
		try {
			ObjectId objectId = new ObjectId();
			headersJsonModel.setId(objectId.toString());
			id = objectId.toString(); 
			this.mongoOps.insert(headersJsonModel, SalesChannelConstants.SC_MARKETPLACE_HEADER);
		} catch(Exception e) {
			LOGGERS.error("error while insert MarketPlaceHeaders in database");
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean updateMarketPlaceHeader(MarketPlaceHeadersJsonModel headersJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(headersJsonModel, SalesChannelConstants.SC_MARKETPLACE_HEADER);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error while update MarketPlaceHeaders in database");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteMarketPlaceHeader(MarketPlaceHeadersJsonModel headersJsonModel) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.marketplace.MarketPlaceHeadersJsonModel"));
			if(headersJsonModel.getId() != null && !headersJsonModel.getId().isEmpty()) {
				query.addCriteria(Criteria.where("_id").is(headersJsonModel.getId()));
				MarketPlaceHeadersJsonModel header = this.mongoOps.findAndRemove(query, MarketPlaceHeadersJsonModel.class
						, SalesChannelConstants.SC_MARKETPLACE_HEADER);
				if(header != null)
					status = true;
				
			} else {
				if(headersJsonModel.getCustomerId() != null && !headersJsonModel.getCustomerId().isEmpty()) {
					query.addCriteria(Criteria.where("customerId").is(headersJsonModel.getCustomerId()));
				}
				if(headersJsonModel.getMarketPlaceId() != null && !headersJsonModel.getMarketPlaceId().isEmpty()) {
					query.addCriteria(Criteria.where("marketPlaceId").is(headersJsonModel.getMarketPlaceId()));
				}
				if(headersJsonModel.getMarketPlaceRegionId() != null && !headersJsonModel.getMarketPlaceRegionId().isEmpty()) {
					query.addCriteria(Criteria.where("marketPlaceRegionId").is(headersJsonModel.getMarketPlaceRegionId()));
				}
				this.mongoOps.remove(query, SalesChannelConstants.SC_MARKETPLACE_HEADER);
				status = true;
			}
		} catch(Exception e) {
			LOGGERS.error("error occured while delete market places header by headersJsonModel from DB");
			e.printStackTrace();
		}
		return status;
	}
}
