package net.saleschannel.api.customer;

public interface CustomerService {

	public String authenticateLogin(CustomerJsonModel customerJsonModel);
	
	public CustomerJsonModel validateLogin(String authToken);
}
