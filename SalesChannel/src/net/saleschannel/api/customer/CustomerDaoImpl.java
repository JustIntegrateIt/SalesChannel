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
	
	public String authenticateLogin(CustomerLoginJsonModel customerLoginJsonModel) {
		String authToken = null;
		try {
			final SalesChannelEncryptionDecryption encryptDecryptService = new SalesChannelEncryptionDecryption(SalesChannelBaseDao.endecryptionKey);
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.customer.CustomerJsonModel")
					.and("userName").is(customerLoginJsonModel.getUserName())
					.and("password").is(encryptDecryptService.encryptWithHash(customerLoginJsonModel.getPassword())));
			CustomerJsonModel customerJsonModel =  this.mongoOps.findOne(query, CustomerJsonModel.class, SalesChannelConstants.SC_CUSTOMER);
			if(customerJsonModel != null) {
				ObjectId objectId = new ObjectId();
				authToken = objectId.toString();
				customerJsonModel.setAuthToken(authToken);
				this.mongoOps.save(customerJsonModel, SalesChannelConstants.SC_CUSTOMER);
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
	
	public String createCustomer(CustomerJsonModel customerJsonModel) {
		String customerId = null;
		try {
			ObjectId objectId = new ObjectId();
			customerJsonModel.setId(objectId.toString());
			customerId = objectId.toString(); 
			this.mongoOps.insert(customerJsonModel, SalesChannelConstants.SC_CUSTOMER);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return customerId;
	}
	
	public boolean updateCustomer(CustomerJsonModel customerJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(customerJsonModel, SalesChannelConstants.SC_CUSTOMER);
			status = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public CustomerJsonModel getCustomer(String customerId) {
		CustomerJsonModel customerJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.customer.CustomerJsonModel")
					.and("_id").is(new ObjectId(customerId)));
			customerJsonModel = this.mongoOps.findOne(query, CustomerJsonModel.class, SalesChannelConstants.SC_CUSTOMER);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return customerJsonModel;
	}
	
	public CustomerJsonModel getCustomerByEmail(String email) {
		CustomerJsonModel customerJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.customer.CustomerJsonModel")
					.and("email").is(email));
			customerJsonModel = this.mongoOps.findOne(query, CustomerJsonModel.class, SalesChannelConstants.SC_CUSTOMER);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return customerJsonModel;
	}
	
	public CustomerJsonModel getCustomerByUserName(String userName) {
		CustomerJsonModel customerJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.customer.CustomerJsonModel")
					.and("userName").is(userName));
			customerJsonModel = this.mongoOps.findOne(query, CustomerJsonModel.class, SalesChannelConstants.SC_CUSTOMER);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return customerJsonModel;
	}
	
	public boolean deleteCustomer(String customerId) {
		boolean status = false;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("net.saleschannel.api.customer.CustomerJsonModel")
					.and("_id").is(new ObjectId(customerId)));
			CustomerJsonModel customerJsonModel = this.mongoOps.findAndRemove(query, CustomerJsonModel.class, SalesChannelConstants.SC_CUSTOMER);
			if(customerJsonModel != null)
				status = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
