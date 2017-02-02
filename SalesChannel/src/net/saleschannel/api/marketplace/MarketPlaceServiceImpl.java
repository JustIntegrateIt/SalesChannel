package net.saleschannel.api.marketplace;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class MarketPlaceServiceImpl implements MarketPlaceService {

	private static final Logger LOGGERS = Logger.getLogger(MarketPlaceServiceImpl.class);
	
	private MarketPlaceDaoImpl marketPlaceDao;
	
	public MarketPlaceJsonObject convertMarketPlaceJsonModelToObject(MarketPlaceJsonModel marketPlaceJsonModel, String customerId) {
		MarketPlaceJsonObject marketPlaceJsonObject = null;
		try {
			if(marketPlaceJsonModel != null) {
				marketPlaceJsonObject = new MarketPlaceJsonObject();
				marketPlaceJsonObject.setId(marketPlaceJsonModel.getId());
				if(marketPlaceJsonModel.getId() != null && !marketPlaceJsonModel.getId().isEmpty()) {
					List<MarketPlaceRegionJsonObject> marketPlaceRegions = getMarketPlaceRegions(marketPlaceJsonModel.getId());
					if(marketPlaceRegions != null && marketPlaceRegions.size() > 0) {
						marketPlaceJsonObject.setRegions(marketPlaceRegions);
					}
					List<MarketPlaceHeadersJsonObject> marketPlaceHeaders = getMarketPlaceHeaders(marketPlaceJsonModel.getId(), customerId);
					if(marketPlaceHeaders != null && marketPlaceHeaders.size() > 0) {
						marketPlaceJsonObject.setHeaders(marketPlaceHeaders);
					}
				}
				marketPlaceJsonObject.setMarketPlaceName(marketPlaceJsonModel.getMarketPlaceName());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertMarketPlaceJsonModelToObject");
			e.printStackTrace();
		}
		return marketPlaceJsonObject;
	}
	
	public List<MarketPlaceJsonObject> getAllMarketPlaces(String customerId) {
		List<MarketPlaceJsonObject> marketPlaceJsonObjectList = null;
		try {
			List<MarketPlaceJsonModel> marketPlaceJsonModelList = marketPlaceDao.getAllMarketPlaces();
			if(marketPlaceJsonModelList != null && marketPlaceJsonModelList.size() > 0) {
				marketPlaceJsonObjectList = new ArrayList<MarketPlaceJsonObject>();
				for(MarketPlaceJsonModel marketPlaceJsonModel : marketPlaceJsonModelList) {
					MarketPlaceJsonObject marketPlaceJsonObject = convertMarketPlaceJsonModelToObject(marketPlaceJsonModel, customerId);
					marketPlaceJsonObjectList.add(marketPlaceJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get all the marketplaces");
			e.printStackTrace();
		}
		return marketPlaceJsonObjectList;
	}
	
	public MarketPlaceJsonObject getMarketPlaceById(String marketPlaceId, String customerId) {
		MarketPlaceJsonObject marketPlaceJsonObject = null;
		try {
			MarketPlaceJsonModel marketPlaceJsonModel = marketPlaceDao.getMarketPlaceById(marketPlaceId);
			if(marketPlaceJsonModel != null && marketPlaceJsonModel.getId() != null && !marketPlaceJsonModel.getId().isEmpty()) {
				marketPlaceJsonObject = convertMarketPlaceJsonModelToObject(marketPlaceJsonModel, customerId);
			}
		} catch(Exception e) {
			LOGGERS.error("error while get the marketplace by marketPlaceId");
			e.printStackTrace();
		}
		return marketPlaceJsonObject;
	}
	
	public MarketPlaceRegionJsonObject convertMarketPlaceRegionJsonModelToObject(MarketPlaceRegionJsonModel marketPlaceRegionJsonModel) {
		MarketPlaceRegionJsonObject marketPlaceRegionJsonObject = null;
		try {
			if(marketPlaceRegionJsonModel != null) {
				marketPlaceRegionJsonObject = new MarketPlaceRegionJsonObject();
				marketPlaceRegionJsonObject.setId(marketPlaceRegionJsonModel.getId());
				marketPlaceRegionJsonObject.setMarketPlaceSubRegionName(marketPlaceRegionJsonModel.getMarketPlaceSubRegionName());	
				marketPlaceRegionJsonObject.setMarketPlaceRegionName(marketPlaceRegionJsonModel.getMarketPlaceRegionName());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertMarketPlaceRegionJsonModelToObject");
			e.printStackTrace();
		}
		return marketPlaceRegionJsonObject;
	}
	
	public MarketPlaceHeadersJsonObject convertMarketPlaceHeaderJsonModelToObject(MarketPlaceHeadersJsonModel marketPlaceHeadersJsonModel, String customerId) {
		MarketPlaceHeadersJsonObject marketPlaceHeadersJsonObject = null;
		try {
			if(marketPlaceHeadersJsonModel != null) {
				marketPlaceHeadersJsonObject = new MarketPlaceHeadersJsonObject();
				if(marketPlaceHeadersJsonModel.getCustomerId() != null && !marketPlaceHeadersJsonModel.getCustomerId().isEmpty()
						&& !marketPlaceHeadersJsonModel.getCustomerId().equals('0')) {
					marketPlaceHeadersJsonObject.setId(marketPlaceHeadersJsonModel.getId());
				}
				marketPlaceHeadersJsonObject.setHeaderKey(marketPlaceHeadersJsonModel.getHeaderParamName());
				marketPlaceHeadersJsonObject.setHeaderValue(marketPlaceHeadersJsonModel.getHeaderParamValue());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertMarketPlaceRegionJsonModelToObject");
			e.printStackTrace();
		}
		return marketPlaceHeadersJsonObject;
	}
	
	public List<MarketPlaceRegionJsonObject> getMarketPlaceRegions(String marketPlaceId) {
		List<MarketPlaceRegionJsonObject> marketPlaceRegionJsonList = null;
		try {
			List<MarketPlaceRegionJsonModel> marketPlaceRegionJsonModelList = marketPlaceDao.getMarketPlaceRegions(marketPlaceId);
			if(marketPlaceRegionJsonModelList != null && marketPlaceRegionJsonModelList.size() > 0) {
				marketPlaceRegionJsonList = new ArrayList<MarketPlaceRegionJsonObject>();
				for(MarketPlaceRegionJsonModel marketPlaceRegionJsonModel : marketPlaceRegionJsonModelList) {
					MarketPlaceRegionJsonObject marketPlaceRegionJsonObject = convertMarketPlaceRegionJsonModelToObject(marketPlaceRegionJsonModel);
					marketPlaceRegionJsonList.add(marketPlaceRegionJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get marketplace regions");
			e.printStackTrace();
		}
		return marketPlaceRegionJsonList;
	}
	
	public MarketPlaceRegionJsonModel getMarketPlaceRegionById(String id) {
		MarketPlaceRegionJsonModel marketPlaceRegionJsonModel = null;
		try {
			marketPlaceRegionJsonModel = marketPlaceDao.getMarketPlaceRegionById(id);
		} catch(Exception e) {
			LOGGERS.error("error while get marketplace region by Id");
			e.printStackTrace();
		}
		return marketPlaceRegionJsonModel;
	}
	
	public List<MarketPlaceHeadersJsonObject> getMarketPlaceHeaders(String marketPlaceId, String customerId) {
		List<MarketPlaceHeadersJsonObject> marketPlaceHeadersJsonObjectList = null;
		try {
			List<MarketPlaceHeadersJsonModel> marketPlaceHeadersJsonModelList = marketPlaceDao.getMarketPlaceHeaders(marketPlaceId, customerId);
			if(marketPlaceHeadersJsonModelList != null && marketPlaceHeadersJsonModelList.size() > 0) {
				marketPlaceHeadersJsonObjectList = new ArrayList<MarketPlaceHeadersJsonObject>();
				for(MarketPlaceHeadersJsonModel marketPlaceHeaderJsonModel : marketPlaceHeadersJsonModelList) {
					MarketPlaceHeadersJsonObject marketPlaceHeaderJsonObject = convertMarketPlaceHeaderJsonModelToObject(marketPlaceHeaderJsonModel, customerId);
					marketPlaceHeadersJsonObjectList.add(marketPlaceHeaderJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get marketplace headers");
			e.printStackTrace();
		}
		return marketPlaceHeadersJsonObjectList;
	}
	
	public MarketPlaceHeadersJsonModel getMarketPlaceHeaderById(String headerId) {
		MarketPlaceHeadersJsonModel marketPlaceHeadersJsonModel = null;
		try {
			marketPlaceHeadersJsonModel = marketPlaceDao.getMarketPlaceHeaderById(headerId);
		} catch(Exception e) {
			LOGGERS.error("error while get marketplace header");
			e.printStackTrace();
		}
		return marketPlaceHeadersJsonModel;
	}
	
	public List<MarketPlaceCustomerRegionJsonObject> getMarketPlaceCustomerRegions(String marketPlaceId, String customerId) {
		List<MarketPlaceCustomerRegionJsonObject> marketPlaceCustomerRegionJsonObjectList = null;
		try {
			List<MarketPlaceCustomerRegionJsonModel> customerRegionJsonModelList = marketPlaceDao.getMarketPlaceCustomerRegions(marketPlaceId, customerId);
			if(customerRegionJsonModelList != null && customerRegionJsonModelList.size() > 0) {
				marketPlaceCustomerRegionJsonObjectList = new ArrayList<MarketPlaceCustomerRegionJsonObject>();
				for(MarketPlaceCustomerRegionJsonModel customerRegionJsonModel : customerRegionJsonModelList) {
					MarketPlaceCustomerRegionJsonObject customerRegionJsonObject = convertMarketPlaceCustomerRegionJsonModelToObject(customerRegionJsonModel);
					marketPlaceCustomerRegionJsonObjectList.add(customerRegionJsonObject);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while get marketplace customer region by customerId and marketPlaceId");
			e.printStackTrace();
		}
		return marketPlaceCustomerRegionJsonObjectList;
	}
	
	public MarketPlaceCustomerRegionJsonObject getMarketPlaceCustomerRegionById(String customerRegionId) {
		MarketPlaceCustomerRegionJsonObject customerRegionJsonObject = null;
		try {
			MarketPlaceCustomerRegionJsonModel customerRegionJsonModel = marketPlaceDao.getMarketPlaceCustomerRegionById(customerRegionId);
			if(customerRegionJsonModel != null) {
				customerRegionJsonObject = convertMarketPlaceCustomerRegionJsonModelToObject(customerRegionJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error while get marketplace customer region by customerRegionId");
			e.printStackTrace();
		}
		return customerRegionJsonObject;
	}

	public MarketPlaceCustomerRegionJsonObject convertMarketPlaceCustomerRegionJsonModelToObject(MarketPlaceCustomerRegionJsonModel customerRegionJsonModel) {
		MarketPlaceCustomerRegionJsonObject customerRegionJsonObject = null;
		try {
			if(customerRegionJsonModel != null) {
				customerRegionJsonObject = new MarketPlaceCustomerRegionJsonObject();
				customerRegionJsonObject.setId(customerRegionJsonModel.getId());
				customerRegionJsonObject.setMarketPlaceRegionId(customerRegionJsonModel.getMarketPlaceRegionId());
				if(customerRegionJsonModel.getMarketPlaceRegionId() != null && !customerRegionJsonModel.getMarketPlaceRegionId().isEmpty()) {
					MarketPlaceRegionJsonModel marketPlaceRegion = marketPlaceDao.getMarketPlaceRegionById(customerRegionJsonModel.getMarketPlaceRegionId());
					if(marketPlaceRegion != null) {
						customerRegionJsonObject.setMarketPlaceRegion(marketPlaceRegion.getMarketPlaceRegionName());
						customerRegionJsonObject.setMarketPlaceSubRegion(marketPlaceRegion.getMarketPlaceSubRegionName());
						if(marketPlaceRegion.getMarketPlaceId() != null && !marketPlaceRegion.getMarketPlaceId().isEmpty()) {
							MarketPlaceJsonModel marketPlace = marketPlaceDao.getMarketPlaceById(marketPlaceRegion.getMarketPlaceId());
							if(marketPlace != null) {
								customerRegionJsonObject.setMarketPlace(marketPlace.getMarketPlaceName());
							}
							List<MarketPlaceHeadersJsonObject> marketPlaceHeadersList = getMarketPlaceHeaders(marketPlaceRegion.getMarketPlaceId()
									, customerRegionJsonModel.getCustomerId());
							if(marketPlaceHeadersList != null && marketPlaceHeadersList.size() > 0) {
								customerRegionJsonObject.setHeaders(marketPlaceHeadersList);
							}
						}
					}
				}
				customerRegionJsonObject.setCustomerId(customerRegionJsonModel.getCustomerId());
				customerRegionJsonObject.setIsActive(customerRegionJsonModel.isActive());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convert MarketPlace CustomerRegion JsonModel To Object");
			e.printStackTrace();
		}
		return customerRegionJsonObject;
	}
	
	public List<MarketPlaceHeadersJsonModel> convertMarketPlaceHeaderJsonObjectToModel(MarketPlaceCustomerRegionJsonObject customerRegionJsonObject) {
		List<MarketPlaceHeadersJsonModel> headersJsonModelList = null;
		try {
			if(customerRegionJsonObject != null && customerRegionJsonObject.getHeaders() != null && customerRegionJsonObject.getHeaders().size() > 0) {
				headersJsonModelList = new ArrayList<MarketPlaceHeadersJsonModel>();
				for(MarketPlaceHeadersJsonObject header : customerRegionJsonObject.getHeaders()) {
					MarketPlaceHeadersJsonModel headersJsonModel = new MarketPlaceHeadersJsonModel();
					if(header.getId() != null && !header.getId().isEmpty()) {
						headersJsonModel.setId(header.getId());
					}
					headersJsonModel.setHeaderParamName(header.getHeaderKey());
					headersJsonModel.setHeaderParamValue(header.getHeaderValue());
					headersJsonModel.setCustomerId(customerRegionJsonObject.getCustomerId());
					if(customerRegionJsonObject.getMarketPlaceRegionId() != null && !customerRegionJsonObject.getMarketPlaceRegionId().isEmpty()) {
						headersJsonModel.setMarketPlaceRegionId(customerRegionJsonObject.getMarketPlaceRegionId());
						MarketPlaceRegionJsonModel marketPlaceRegion = marketPlaceDao.getMarketPlaceRegionById(customerRegionJsonObject.getMarketPlaceRegionId());
						if(marketPlaceRegion != null) {
							headersJsonModel.setMarketPlaceId(marketPlaceRegion.getMarketPlaceId());
						}
					}
					headersJsonModelList.add(headersJsonModel);
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while convert MarketPlace Header JsonObject To Model");
			e.printStackTrace();
		}
		return headersJsonModelList;
	}
	
	public boolean insertUpdateMarketPlaceCustomerRegion(MarketPlaceCustomerRegionJsonObject customerRegionJsonObject) {
		boolean status = false;
		try {
			MarketPlaceCustomerRegionJsonModel customerRegionJsonModel = null;
			if(customerRegionJsonObject != null) {
				if(customerRegionJsonObject.getId() != null && !customerRegionJsonObject.getId().isEmpty()) {
					customerRegionJsonModel = marketPlaceDao.getMarketPlaceCustomerRegionById(customerRegionJsonObject.getId());
					customerRegionJsonModel.setIsActive(customerRegionJsonObject.getIsActive());
					customerRegionJsonModel.setUpdatedAt(new Date());
					customerRegionJsonModel.setUpdatedBy(customerRegionJsonObject.getCustomerId());
					customerRegionJsonModel.setCustomerId(customerRegionJsonObject.getCustomerId());
					if(customerRegionJsonObject.getMarketPlaceRegionId() != null && !customerRegionJsonObject.getMarketPlaceRegionId().isEmpty()) {
						customerRegionJsonModel.setMarketPlaceRegionId(customerRegionJsonObject.getMarketPlaceRegionId());
						MarketPlaceRegionJsonModel marketPlaceRegion = marketPlaceDao.getMarketPlaceRegionById(customerRegionJsonObject.getMarketPlaceRegionId());
						if(marketPlaceRegion != null) {
							customerRegionJsonModel.setMarketPlaceId(marketPlaceRegion.getMarketPlaceId());
						}
					}
					status = marketPlaceDao.updateMarketPlaceCustomerRegion(customerRegionJsonModel);
					if(status && customerRegionJsonObject.getHeaders() != null && !customerRegionJsonObject.getHeaders().isEmpty()) {
						List<MarketPlaceHeadersJsonModel> headersJsonModelList = convertMarketPlaceHeaderJsonObjectToModel(customerRegionJsonObject);
						if(headersJsonModelList != null && headersJsonModelList.size() > 0) {
							for(MarketPlaceHeadersJsonModel headersJsonModel : headersJsonModelList) {
								if(headersJsonModel.getId() != null && !headersJsonModel.getId().isEmpty()) {
									headersJsonModel.setUpdatedBy(customerRegionJsonObject.getCustomerId());
									headersJsonModel.setUpdatedAt(new Date());
									status = marketPlaceDao.updateMarketPlaceHeader(headersJsonModel);
								}
							}
						}
					}
				} else {
					customerRegionJsonModel = new MarketPlaceCustomerRegionJsonModel();
					customerRegionJsonModel.setIsActive(customerRegionJsonObject.getIsActive());
					customerRegionJsonModel.setCreatedAt(new Date());
					customerRegionJsonModel.setCreateBy(customerRegionJsonObject.getCustomerId());
					customerRegionJsonModel.setCustomerId(customerRegionJsonObject.getCustomerId());
					if(customerRegionJsonObject.getMarketPlaceRegionId() != null && !customerRegionJsonObject.getMarketPlaceRegionId().isEmpty()) {
						customerRegionJsonModel.setMarketPlaceRegionId(customerRegionJsonObject.getMarketPlaceRegionId());
						MarketPlaceRegionJsonModel marketPlaceRegion = marketPlaceDao.getMarketPlaceRegionById(customerRegionJsonObject.getMarketPlaceRegionId());
						if(marketPlaceRegion != null) {
							customerRegionJsonModel.setMarketPlaceId(marketPlaceRegion.getMarketPlaceId());
						}
					}
					String id = marketPlaceDao.insertMarketPlaceCustomerRegion(customerRegionJsonModel);
					if(id != null ) {
						status = true;
					}
					if(status && customerRegionJsonObject.getHeaders() != null && !customerRegionJsonObject.getHeaders().isEmpty()) {
						List<MarketPlaceHeadersJsonModel> headersJsonModelList = convertMarketPlaceHeaderJsonObjectToModel(customerRegionJsonObject);
						if(headersJsonModelList != null && headersJsonModelList.size() > 0) {
							for(MarketPlaceHeadersJsonModel headersJsonModel : headersJsonModelList) {
									headersJsonModel.setUpdatedBy(customerRegionJsonObject.getCustomerId());
									headersJsonModel.setUpdatedAt(new Date());
									id = marketPlaceDao.insertMarketPlaceHeader(headersJsonModel);
									if(id != null )
										status = true;
							}
						}
					}
				}
			}
		} catch(Exception e) {
			LOGGERS.error("error while insert Update MarketPlace CustomerRegion");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteMarketPlaceCustomerRegions(String marketPlaceId, String customerId) {
		boolean status = false;
		try {
			status = marketPlaceDao.deleteMarketPlaceCustomerRegions(marketPlaceId, customerId);
		} catch(Exception e) {
			LOGGERS.error("error while delete MarketPlace CustomerRegion");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteMarketPlaceCustomerRegionById(String customerRegionId) {
		boolean status = false;
		try {
			status = marketPlaceDao.deleteMarketPlaceCustomerRegionById(customerRegionId); 
		} catch(Exception e) {
			LOGGERS.error("error while delete MarketPlace CustomerRegion");
			e.printStackTrace();
		}
		return status;
	}
	
	public MarketPlaceDaoImpl getMarketPlaceDao() {
		return marketPlaceDao;
	}

	public void setMarketPlaceDao(MarketPlaceDaoImpl marketPlaceDao) {
		this.marketPlaceDao = marketPlaceDao;
	}
}
