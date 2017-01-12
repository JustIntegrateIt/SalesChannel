package net.saleschannel.api.customer;

import net.saleschannel.api.base.SalesChannelBaseDao;
import net.saleschannel.api.constants.SalesChannelConstants;
import net.saleschannel.api.encryption.SalesChannelEncryptionDecryption;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CustomerDaoImpl implements CustomerDao {

private static final Logger LOGGERS = Logger.getLogger(CustomerDaoImpl.class);
	
	private MongoOperations mongoOps;
 	
	public CustomerDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	public String authenticateLogin(CustomerJsonModel customerJsonModel) {
		String authToken = null;
		try {
			final SalesChannelEncryptionDecryption encryptDecryptService = new SalesChannelEncryptionDecryption(SalesChannelBaseDao.endecryptionKey);
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.customer.CustomerJsonModel")
					.and("userName").is(customerJsonModel.getUserName())
					.and("password").is(encryptDecryptService.encryptWithHash(customerJsonModel.getPassword())));
			CustomerJsonModel customer =  this.mongoOps.findOne(query, CustomerJsonModel.class, SalesChannelConstants.SC_CUSTOMER);
			if(customer != null) {
				ObjectId objectId = new ObjectId();
				authToken = objectId.toString();
				customer.setAuthToken(authToken);
				this.mongoOps.save(customer, SalesChannelConstants.SC_CUSTOMER);
			}
			return authToken;		
		} catch(Exception e) {
			LOGGERS.error("error occured while authenticate customer.");
			e.printStackTrace();
		}
		return authToken;
	}

	public CustomerJsonModel validateLogin(String authToken) {
		CustomerJsonModel customerJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.customer.CustomerJsonModel")
					.and("authToken").is(authToken));
			customerJsonModel =  this.mongoOps.findOne(query, CustomerJsonModel.class, SalesChannelConstants.SC_CUSTOMER);
		} catch(Exception e) {
			LOGGERS.error("error occured while validate customer authentication.");
			e.printStackTrace();
		}
		return customerJsonModel;
	}

}
