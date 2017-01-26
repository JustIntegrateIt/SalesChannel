package net.saleschannel.api.customer;

import java.util.Date;

import net.saleschannel.api.base.SalesChannelBaseDao;
import net.saleschannel.api.encryption.SalesChannelEncryptionDecryption;

import org.apache.log4j.Logger;

public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGERS = Logger.getLogger(CustomerServiceImpl.class);
	
	private CustomerDaoImpl customerDao;
	
	public CustomerJsonObject convertCustomerJsonModelToObject(CustomerJsonModel customerJsonModel) {
		CustomerJsonObject customerJsonObject = null;
		try {
			final SalesChannelEncryptionDecryption encryptDecryptService = new SalesChannelEncryptionDecryption(SalesChannelBaseDao.endecryptionKey);
			if(customerJsonModel != null) {
				customerJsonObject = new CustomerJsonObject();
				customerJsonObject.setId(customerJsonModel.getId());
				customerJsonObject.setCustomerFirstName(customerJsonModel.getCustomerFirstName());
				customerJsonObject.setCustomerLastName(customerJsonModel.getCustomerLastName());
				customerJsonObject.setUserName(customerJsonModel.getUserName());
				if(customerJsonModel.getPassword() != null && !customerJsonModel.getPassword().isEmpty()) {
					customerJsonObject.setPassword(encryptDecryptService.decryptWithOutHash(customerJsonModel.getPassword()));
				}
				customerJsonObject.setEmail(customerJsonModel.getEmail());
				customerJsonObject.setPhone(customerJsonModel.getPhone());
				customerJsonObject.setAddress1(customerJsonModel.getAddress1());
				customerJsonObject.setAddress2(customerJsonModel.getAddress2());
				customerJsonObject.setCity(customerJsonModel.getCity());
				customerJsonObject.setProvince(customerJsonModel.getProvince());
				customerJsonObject.setProvinceCode(customerJsonModel.getProvinceCode());
				customerJsonObject.setCountry(customerJsonModel.getCountry());
				customerJsonObject.setLocale(customerJsonModel.getLocale());
				customerJsonObject.setIsActive(customerJsonModel.isActive());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertCustomerJsonModelToObject");
			e.printStackTrace();
		}
		return customerJsonObject;
	}
	
	public CustomerJsonModel convertCustomerJsonObjcetToModel(CustomerJsonObject customerJsonObject) {
		CustomerJsonModel customerJsonModel = null;
		try {
			final SalesChannelEncryptionDecryption encryptDecryptService = new SalesChannelEncryptionDecryption(SalesChannelBaseDao.endecryptionKey);
			if(customerJsonObject != null) {
				customerJsonModel = new CustomerJsonModel();
				customerJsonModel.setId(customerJsonObject.getId());
				customerJsonModel.setCustomerFirstName(customerJsonObject.getCustomerFirstName());
				customerJsonModel.setCustomerLastName(customerJsonObject.getCustomerLastName());
				if(customerJsonObject.getId() == null || customerJsonObject.getId().isEmpty()) {
					customerJsonModel.setUserName(customerJsonObject.getUserName());
				}
				if(customerJsonObject.getPassword() != null && !customerJsonObject.getPassword().isEmpty()) {
					customerJsonModel.setPassword(encryptDecryptService.encryptWithHash(customerJsonObject.getPassword()));
				}
				customerJsonModel.setEmail(customerJsonObject.getEmail());
				customerJsonModel.setPhone(customerJsonObject.getPhone());
				customerJsonModel.setAddress1(customerJsonObject.getAddress1());
				customerJsonModel.setAddress2(customerJsonObject.getAddress2());
				customerJsonModel.setCity(customerJsonObject.getCity());
				customerJsonModel.setProvince(customerJsonObject.getProvince());
				customerJsonModel.setProvinceCode(customerJsonObject.getProvinceCode());
				customerJsonModel.setCountry(customerJsonObject.getCountry());
				customerJsonModel.setLocale(customerJsonObject.getLocale());
				customerJsonModel.setActive(customerJsonObject.getIsActive());
			}
		} catch(Exception e) {
			LOGGERS.error("error while convertCustomerJsonObjcetToModel");
			e.printStackTrace();
		}
		return customerJsonModel;
	}
	
	public String authenticateLogin(CustomerLoginJsonModel customerJsonModel) {
		String authToken = null;
		try {
			authToken = customerDao.authenticateLogin(customerJsonModel);
		} catch(Exception e) {
			LOGGERS.error("error while authenticate login");
			e.printStackTrace();
		}
		return authToken;
	}
	
	public CustomerJsonModel validateLogin(String authToken) {
		CustomerJsonModel customerJsonModel = null;
		try {
			customerJsonModel = customerDao.validateLogin(authToken);
			return customerJsonModel;	
		} catch(Exception e) {
			LOGGERS.error("error while validate login");
			e.printStackTrace();
		}
		return customerJsonModel;
	}

	public boolean createCustomer(CustomerJsonObject customerJsonObject) {
		boolean status = false;
		try {
			CustomerJsonModel customerJsonModel = convertCustomerJsonObjcetToModel(customerJsonObject);
			if(customerJsonModel != null) {
				customerJsonModel.setCreateBy(customerJsonObject.getCustomerId());
				customerJsonModel.setCreatedAt(new Date());
				String id = customerDao.createCustomer(customerJsonModel);
				if(id != null)
					status = true;
			}
		} catch(Exception e) {
			LOGGERS.error("error while create customer");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean updateCustomer(CustomerJsonObject customerJsonObject) {
		boolean status = false;
		try {
			CustomerJsonModel customerJsonModel = convertCustomerJsonObjcetToModel(customerJsonObject);
			if(customerJsonModel != null) {
				customerJsonModel.setUpdatedBy(customerJsonObject.getCustomerId());
				customerJsonModel.setUpdatedAt(new Date());
				status = customerDao.updateCustomer(customerJsonModel);
			}
		} catch(Exception e) {
			LOGGERS.error("error while update customer");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteCustomer(String customerId) {
		boolean status = false;
		try {
			status = customerDao.deleteCustomer(customerId);
		} catch(Exception e) {
			LOGGERS.error("error while delete customer");
			e.printStackTrace();
		}
		return status;
	}
	
	public CustomerJsonObject getCustomer(String customerId) {
		CustomerJsonObject customerJsonObject = null;
		try {
			CustomerJsonModel customerJsonModel = customerDao.getCustomer(customerId);
			customerJsonObject = convertCustomerJsonModelToObject(customerJsonModel);
		} catch(Exception e) {
			LOGGERS.error("error while get customer");
			e.printStackTrace();
		}
		return customerJsonObject;
	}
	
	public CustomerJsonObject getCustomerByEmail(String email) {
		CustomerJsonObject customerJsonObject = null;
		try {
			CustomerJsonModel customerJsonModel = customerDao.getCustomer(email);
			customerJsonObject = convertCustomerJsonModelToObject(customerJsonModel);
		} catch(Exception e) {
			LOGGERS.error("error while get customer");
			e.printStackTrace();
		}
		return customerJsonObject;
	}
	
	public CustomerJsonObject getCustomerByUserName(String userName) {
		CustomerJsonObject customerJsonObject = null;
		try {
			CustomerJsonModel customerJsonModel = customerDao.getCustomerByUserName(userName);
			customerJsonObject = convertCustomerJsonModelToObject(customerJsonModel);
		} catch(Exception e) {
			LOGGERS.error("error while get customer");
			e.printStackTrace();
		}
		return customerJsonObject;
	}
	
	public CustomerDaoImpl getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDaoImpl customerDao) {
		this.customerDao = customerDao;
	}

}
