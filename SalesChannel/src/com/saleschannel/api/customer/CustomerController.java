package com.saleschannel.api.customer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.data.Parameter;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import com.saleschannel.api.SalesChannelServerResource;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.utility.SalesChannelUtility;

public class CustomerController extends SalesChannelServerResource<CustomerJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(CustomerController.class);
	
	private String customerId = null;
	
	@Override
	public Representation fetchDetails() {
		Representation representation = null;
		try {
			if(customerId != null && !customerId.isEmpty()) {
				CustomerJsonObject customerJsonObject = getCustomerService().getCustomer(customerId);
				if(customerJsonObject != null) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
					salesChannelErrorObject.setData(customerJsonObject);
				} else {
					salesChannelErrorObject.setStatusCode(10000);
					salesChannelErrorObject.setMessage(getErrorMessage(10000));
				}
			} else {
				salesChannelErrorObject.setStatusCode(10001);
				salesChannelErrorObject.setMessage(getErrorMessage(10001));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while deleteDetails customer.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			CustomerJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			boolean status = getCustomerService().createCustomer(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
			} else {
				salesChannelErrorObject.setStatusCode(10002);
				salesChannelErrorObject.setMessage(getErrorMessage(10002));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails customer.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			CustomerJsonObject obj) {
		Representation representation = null;
		try {
			obj.setCustomerId(getCustomerId());
			boolean status = getCustomerService().updateCustomer(obj);
			if(status) {
				salesChannelErrorObject.setStatusCode(200);
				salesChannelErrorObject.setMessage(getErrorMessage(200));
			} else {
				salesChannelErrorObject.setStatusCode(10000);
				salesChannelErrorObject.setMessage(getErrorMessage(10000));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while updateDetails customer.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation deleteDetails() {
		Representation representation = null;
		try {
			if(customerId != null && !customerId.isEmpty()) {
				boolean status = getCustomerService().deleteCustomer(customerId);
				if(status) {
					salesChannelErrorObject.setStatusCode(200);
					salesChannelErrorObject.setMessage(getErrorMessage(200));
				} else {
					salesChannelErrorObject.setStatusCode(10003);
					salesChannelErrorObject.setMessage(getErrorMessage(10003));
				}
			} else {
				salesChannelErrorObject.setStatusCode(10001);
				salesChannelErrorObject.setMessage(getErrorMessage(10001));
			}
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while deleteDetails customer.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public JSONObject validate(CustomerJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		JSONObject jsonObject2 = jsonObject;
		//PUT & POST method
		if (method.equals(SalesChannelConstants.PUT) || method.equals(SalesChannelConstants.POST)) {
			//Customer FirstName validation
			if(obj.getCustomerFirstName() != null && !obj.getCustomerFirstName().isEmpty()) {
				if(SalesChannelUtility.checkSpecialCharacters(obj.getCustomerFirstName())) {
					jsonObject2.put("10012", "Customer FirstName should not contain special character.@#customerFirstName#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("10011", "Customer FirstName is empty.@#customerFirstName#@");
				return jsonObject2;
			}
			//Customer LastName validation
			if(obj.getCustomerLastName() != null && !obj.getCustomerLastName().isEmpty()) {
				if(SalesChannelUtility.checkSpecialCharacters(obj.getCustomerLastName())) {
					jsonObject2.put("10014", "Customer LastName should not contain special character.@#customerLastName#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("10013", "Customer LastName is empty.@#customerLastName#@");
				return jsonObject2;
			}
			if(method.equals(SalesChannelConstants.POST)) {
				//Customer UserName validation
				if(obj.getUserName() != null && !obj.getUserName().isEmpty()) {
					if(obj.getUserName().length() > 6 && obj.getUserName().length() < 16) {
						CustomerJsonObject customerJsonObject = getCustomerService().getCustomerByUserName(obj.getUserName());
						if(customerJsonObject != null) {
							jsonObject2.put("10017", "Customer UserName already exist.@#userName#@");
							return jsonObject2;
						}
					} else {
						jsonObject2.put("10016", "Customer UserName should between 6 to 16 characters.@#userName#@");
						return jsonObject2;
					}
				} else {
					jsonObject2.put("10015", "Customer UserName is empty.@#userName#@");
					return jsonObject2;
				}
			}
			//Customer Password validation
			if(obj.getPassword() != null && !obj.getPassword().isEmpty()) {
				if(obj.getUserName().length() > 6 && obj.getUserName().length() > 16) {
					jsonObject2.put("10019", "Customer Password should between 6 to 16 characters.@#password#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("10018", "Customer Password is empty.@#password#@");
				return jsonObject2;
			}
			//Customer Email validation
			if(obj.getEmail() != null && !obj.getEmail().isEmpty()) {
				if(obj.getEmail().contains("@")) {
					CustomerJsonObject customerJsonObject = getCustomerService().getCustomerByEmail(obj.getEmail());
					if(customerJsonObject != null) {
						jsonObject2.put("10022", "Customer Email already exist.@#email#@");
						return jsonObject2;
					}
				} else {
					jsonObject2.put("10021", "Customer Email not valid.@#email#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("10020", "Customer Email is empty.@#email#@");
				return jsonObject2;
			}
			//Customer Phone validation
			if(obj.getPhone() != null && !obj.getPhone().isEmpty()) {
				if(obj.getPhone().length() < 10) {
					jsonObject2.put("10024", "Customer Phone is not valid.@#phone#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("10023", "Customer Phone is empty.@#phone#@");
				return jsonObject2;
			}
			//Customer Address1 validation
			if(obj.getAddress1() != null && !obj.getAddress1().isEmpty()) {
				if(SalesChannelUtility.checkSpecialCharacters(obj.getAddress1())) {
					jsonObject2.put("10026", "Customer Address1 should not contain special character.@#address1#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("10025", "Customer Address1 is empty.@#address1#@");
				return jsonObject2;
			}
			//Customer Address2 validation
			if(obj.getAddress2() != null && !obj.getAddress2().isEmpty()) {
				if(SalesChannelUtility.checkSpecialCharacters(obj.getAddress2())) {
					jsonObject2.put("10027", "Customer Address2 should not contain special character.@#address2#@");
					return jsonObject2;
				}
			}
			//Customer City validation
			if(obj.getCity() == null || obj.getCity().isEmpty()) {
				jsonObject2.put("10028", "Customer City is empty.@#city#@");
				return jsonObject2;
			}
			//Customer Province validation
			if(obj.getProvince() == null || obj.getProvince().isEmpty()) {
				jsonObject2.put("10029", "Customer Province is empty.@#province#@");
				return jsonObject2;
			}
			//Customer ProvinceCode validation
			if(obj.getProvinceCode() == null || obj.getProvinceCode().isEmpty()) {
				jsonObject2.put("10030", "Customer ProvinceCode is empty.@#provinceCode#@");
				return jsonObject2;
			}
			//Customer Country validation
			if(obj.getCountry() == null || obj.getCountry().isEmpty()) {
				jsonObject2.put("10031", "Customer Country is empty.@#country#@");
				return jsonObject2;
			}
		}
		//POST method
		else if (method.equals(SalesChannelConstants.PUT)) {
			//Customer Id validation
			if(obj.getId() != null && obj.getId().isEmpty()) {
				CustomerJsonObject customerJsonObject = getCustomerService().getCustomer(obj.getId());
				if(customerJsonObject == null) {
					jsonObject2.put("10033", "Customer Id is invalid.@#id#@");
					return jsonObject2;
				}
			} else {
				jsonObject2.put("10032", "Customer Id is empty.@#id#@");
				return jsonObject2;
			}
		}
		//GET method
		else if (method.equals(SalesChannelConstants.GET) || method.equals(SalesChannelConstants.DELETE)) {
			if (!form.isEmpty()) {
				for (final Parameter parameter : form) {
					jsonObject = SalesChannelUtility.validateParameters(parameter.getName(), parameter.getValue());
					if (jsonObject.length() != 0) {
						return jsonObject2;
					} else {
						if (parameter.getName().equalsIgnoreCase("customerId")) {
							customerId = parameter.getValue();
						}
					}
				}
			}
		}
		return jsonObject2;
	}

	@Override
	public CustomerJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		final ObjectMapper mapper = new ObjectMapper();
		final CustomerJsonObject customerJsonObject = mapper.readValue(stream, CustomerJsonObject.class);
		return customerJsonObject;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("customerId");
		return paramList;
	}
	
}
