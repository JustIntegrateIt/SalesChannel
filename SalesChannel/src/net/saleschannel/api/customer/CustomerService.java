package net.saleschannel.api.customer;

public interface CustomerService {

	public CustomerJsonObject convertCustomerJsonModelToObject(CustomerJsonModel customerJsonModel);
	
	public CustomerJsonModel convertCustomerJsonObjcetToModel(CustomerJsonObject customerJsonObject);
	
	public String authenticateLogin(CustomerLoginJsonModel customerLoginJsonModel);
	
	public CustomerJsonModel validateLogin(String authToken);
	
	public boolean createCustomer(CustomerJsonObject customerJsonObject);
	
	public boolean updateCustomer(CustomerJsonObject customerJsonObject);
	
	public boolean deleteCustomer(String customerId);
	
	public CustomerJsonObject getCustomer(String customerId);
	
	public CustomerJsonObject getCustomerByEmail(String email);
	
	public CustomerJsonObject getCustomerByUserName(String userName);
}
