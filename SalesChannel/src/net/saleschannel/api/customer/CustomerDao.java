package net.saleschannel.api.customer;

public interface CustomerDao {

	public String authenticateLogin(CustomerJsonModel customerJsonModel);
	
	public CustomerJsonModel validateLogin(String authToken);
}
