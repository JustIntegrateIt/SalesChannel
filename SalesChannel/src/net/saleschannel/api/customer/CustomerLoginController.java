package net.saleschannel.api.customer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import net.saleschannel.api.SalesChannelServerResource;
import net.saleschannel.api.constants.SalesChannelConstants;

public class CustomerLoginController extends SalesChannelServerResource<CustomerJsonModel>{

	private static final Logger LOGGERS = Logger.getLogger(CustomerLoginController.class);
	
	private CustomerServiceImpl customerService;
	
	@Override
	public Representation fetchDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			CustomerJsonModel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation updateDetails(Representation entity,	CustomerJsonModel obj) {
		Representation representation = null;
		try {
			String authToken = customerService.authenticateLogin(obj);
			if(authToken != null) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
				salesChannelErrorObject.setData(authToken);
			} else {
				salesChannelErrorObject.setStatusCode(2002);
				salesChannelErrorObject.setMessage(getErrorMessage(2002));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails authToken.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(CustomerJsonModel obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		if (method.equals(SalesChannelConstants.PUT)) {
			//userName validation
			if(obj.getUserName() == null || obj.getUserName().isEmpty()) {
				jsonObject2.put("1000", "UserName is empty.@#userName#@");
				return jsonObject2;
			}
			//password validation
			if(obj.getPassword() == null || obj.getPassword().isEmpty()) {
				jsonObject2.put("1000", "Password is empty.@#password#@");
				return jsonObject2;
			}
		}
			return jsonObject2;
	}

	@Override
	public CustomerJsonModel getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final CustomerJsonModel customerJsonModel = mapper.readValue(stream, CustomerJsonModel.class);
		return customerJsonModel;
	}

	public CustomerServiceImpl getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerServiceImpl customerService) {
		this.customerService = customerService;
	}

	@Override
	public List<String> getParametersList() {
		// TODO Auto-generated method stub
		return null;
	}

}
