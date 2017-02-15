package com.saleschannel.api.customer;

public interface CustomerDao {

	public String authenticateLogin(CustomerLoginJsonModel customerLoginJsonModel);
	
	public CustomerJsonModel validateLogin(String authToken);
	
	public String createCustomer(CustomerJsonModel customerJsonModel);
	
	public boolean updateCustomer(CustomerJsonModel customerJsonModel);
	
	public CustomerJsonModel getCustomer(String customerId);
	
	public CustomerJsonModel getCustomerByEmail(String email);
	
	public CustomerJsonModel getCustomerByUserName(String userName);
	
	public boolean deleteCustomer(String customerId);
}
