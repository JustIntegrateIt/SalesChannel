package net.saleschannel.api.customer;

import org.apache.log4j.Logger;

public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGERS = Logger.getLogger(CustomerServiceImpl.class);
	
	private CustomerDaoImpl customerDao;
	
	public String authenticateLogin(CustomerJsonModel customerJsonModel) {
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

	public CustomerDaoImpl getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDaoImpl customerDao) {
		this.customerDao = customerDao;
	}

}
