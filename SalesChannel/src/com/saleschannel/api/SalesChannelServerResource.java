package com.saleschannel.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.saleschannel.api.base.SalesChannelBaseDao;
import com.saleschannel.api.base.SalesChannelBaseJsonObject;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.customer.CustomerJsonModel;
import com.saleschannel.api.customer.CustomerServiceImpl;
import com.saleschannel.api.encryption.SalesChannelEncryptionDecryption;
import com.saleschannel.api.utility.SalesChannelPropertyLoader;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.data.Method;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public abstract class SalesChannelServerResource<T extends SalesChannelBaseJsonObject> extends ServerResource {

	private static final Logger LOGGERS = Logger.getLogger(SalesChannelServerResource.class);
	
	private String customerId;
	
	private String authToken;
	
	private Form form;
	
	private CustomerServiceImpl customerService;
	
	public SalesChannelErrorObject salesChannelErrorObject = new SalesChannelErrorObject();
	
	protected JSONObject jsonResponse = new JSONObject();
	
	public SalesChannelServerResource() {
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Representation handle() {
		Representation representation = null;
		form = getRequest().getResourceRef().getQueryAsForm();
		long startTime = System.currentTimeMillis();
		String requestString = getRequest().toString();
		try {
			final Method method = getMethod();
			String screenPath = getResourcePath();
			final SalesChannelBaseJsonObject baseJsonObject = validateAuthentication();
			final SalesChannelEncryptionDecryption encryptDecryptService = new SalesChannelEncryptionDecryption(SalesChannelBaseDao.endecryptionKey);
			
			//#1.Check if customer token is invalid, return error response
			if (baseJsonObject.getAuthToken() != null && !baseJsonObject.getAuthToken().isEmpty() && getCustomerId() == null) {
				salesChannelErrorObject.setStatusCode(400);
				salesChannelErrorObject.setMessage("authToken is invalid !");
				representation = new JsonRepresentation(salesChannelErrorObject);
				
				try {
					//representation = new JsonRepresentation(encryptDecryptService.encryptWithHash(representation.getText()));
					representation = new JsonRepresentation(representation.getText());
				} catch (final IOException e) {
					LOGGERS.error("Error in setting the encrypted json text: " + e.getMessage());
				}
				
				getResponse().setEntity(representation);
				resetValues();
				return representation;
			}
			
			//#2.Check if auth token sent in header, return error response
			if (screenPath != null && !screenPath.isEmpty() && !screenPath.equals("customerLogin")
					&& !screenPath.equals("customer")
					&& (baseJsonObject.getAuthToken() == null || baseJsonObject.getAuthToken().isEmpty())) {
				salesChannelErrorObject.setStatusCode(401);
				salesChannelErrorObject.setMessage("authToken is missing !");
				representation = new JsonRepresentation(salesChannelErrorObject);
				
				try {
					//representation = new JsonRepresentation(encryptDecryptService.encryptWithHash(representation.getText()));
					representation = new JsonRepresentation(representation.getText());
				} catch (final IOException e) {
					LOGGERS.error("Error in setting the encrypted json text: " + e.getMessage());
				}
				
				getResponse().setEntity(representation);
				resetValues();
				return representation;
			} else if(method != null && !method.toString().isEmpty() && screenPath.equals("customer")
					&& !method.toString().equals(SalesChannelConstants.POST)
					&& (baseJsonObject.getAuthToken() == null || baseJsonObject.getAuthToken().isEmpty())) {
				salesChannelErrorObject.setStatusCode(401);
				salesChannelErrorObject.setMessage("authToken is missing !");
				representation = new JsonRepresentation(salesChannelErrorObject);
				
				try {
					//representation = new JsonRepresentation(encryptDecryptService.encryptWithHash(representation.getText()));
					representation = new JsonRepresentation(representation.getText());
				} catch (final IOException e) {
					LOGGERS.error("Error in setting the encrypted json text: " + e.getMessage());
				}
				
				getResponse().setEntity(representation);
				resetValues();
				return representation;
			}
			
			JSONObject jsonObject = new JSONObject();
			Representation reqRepresentation = getRequestEntity();
			
			//#3.Check if invalid JSON request passed
			Object jsonModelObj = null;
			try {
				//#3.1 - Setting the JSON model only when action is POST or PUT
				if (!method.getName().equals(SalesChannelConstants.GET) && !method.getName().equals(SalesChannelConstants.DELETE)) {
					try {
						jsonObject = new JSONObject();
						final String requestContent = encryptDecryptService.encryptWithHash(reqRepresentation.getText());
						
						if (encryptDecryptService.isAuthenticatedData(requestContent)) {
							reqRepresentation = new JsonRepresentation(encryptDecryptService.decryptWithOutHash(requestContent));
							jsonModelObj = getJsonObject(reqRepresentation.getStream());
						} else {
							jsonObject = getJSONObject("9000", getErrorMessage(9000));
						}
						
					} catch (final JsonParseException e) {
						jsonObject = getJSONObject("1002", "Exception While parsing the JSON content.");
						LOGGERS.error("Error while parsing the JSON content: " + e.getLocalizedMessage());
					} catch (final JsonMappingException e) {
						jsonObject = getJSONObject("1002", "Exception While mapping the JSON content.");
						LOGGERS.error("Error while Mapping the JSON content: " + e.getLocalizedMessage());
					} finally {
						if (jsonObject.length() != 0) {
							representation = new JsonRepresentation(getErrorObject(jsonObject));
							
							try {
								//representation = new JsonRepresentation(encryptDecryptService.encryptWithHash(representation.getText()));
								representation = new JsonRepresentation(representation.getText());
							} catch (final IOException e) {
								LOGGERS.error("Error in setting the encrypted json text: " + e.getMessage());
							}
							
							getResponse().setEntity(representation);
							resetValues();
							return representation;
						}
					}
				}
			} catch (final IOException e1) {
				jsonObject = new JSONObject();
				LOGGERS.error("Error in getting the input stream.");
				return representation;
			} catch (final Exception e1) {
				LOGGERS.error("Error in framing the json object.");
			}
			
			jsonObject = new JSONObject();
			try {
				// validate the GET request parameters
				if (method.getName() != null && method.getName().equals(SalesChannelConstants.GET)) {
					jsonObject = validateParameters(form, getParametersList());
				}
				
				if (jsonObject != null && jsonObject.length() != 0) {
					representation = new JsonRepresentation(getErrorObject(jsonObject));
					
					try {
						//representation = new JsonRepresentation(encryptDecryptService.encryptWithHash(representation.getText()));
						representation = new JsonRepresentation(representation.getText());
					} catch (final IOException e) {
						LOGGERS.error("Error in setting the encrypted json text: " + e.getMessage());
					}
					
					getResponse().setEntity(representation);
					resetValues();
					return representation; //return-5
				}

				// validate the request content passed
				if (!method.getName().equals(SalesChannelConstants.GET) && !method.getName().equals(SalesChannelConstants.DELETE)) {
					if(jsonModelObj != null) {
						jsonObject = validate((T) jsonModelObj, method.getName(), jsonObject, form);
					} else {
						jsonObject = getJSONObject("1006", getErrorMessage(1006));
					}
				} else {
					jsonObject = validate((T) jsonModelObj, method.getName(), jsonObject, form);
				}
			} catch (final JSONException e) {
				LOGGERS.error("Error in forming the Json Object: " + e.getMessage());
			}
			
			//#4.Check if invalid JSON request passed
			if (jsonObject != null && jsonObject.length() != 0) {
				representation = new JsonRepresentation(getErrorObject(jsonObject));
				try {
					//representation = new JsonRepresentation(encryptDecryptService.encryptWithHash(representation.getText()));
					representation = new JsonRepresentation(representation.getText());
				} catch (final IOException e) {
					LOGGERS.error("Error in setting the encrypted json text: " + e.getMessage());
				}
				getResponse().setEntity(representation);
				resetValues();
				return representation;
			}
			
			//#4.invoke REST method and get representation
			if (method.getName().equals(SalesChannelConstants.GET)) {
				representation = fetchDetails();
			} else if (method.getName().equals(SalesChannelConstants.POST)) {
				representation = insertOrUpdateDetails(reqRepresentation, (T) jsonModelObj);
			} else if (method.getName().equals(SalesChannelConstants.PUT)) {
				representation = updateDetails(reqRepresentation, (T) jsonModelObj);
			} else if (method.getName().equals(SalesChannelConstants.DELETE)) {
				representation = deleteDetails();
			} else {
				salesChannelErrorObject.setStatusCode(306);
				salesChannelErrorObject.setMessage("Request not defined.");
				representation = new JsonRepresentation(salesChannelErrorObject);
			}
			
			if (representation == null) {
				salesChannelErrorObject.setStatusCode(444);
				salesChannelErrorObject.setMessage("Invalid Request.");
				representation = new JsonRepresentation(salesChannelErrorObject);
			}
			
			try {
				//representation = new JsonRepresentation(encryptDecryptService.encryptWithHash(representation.getText()));
				representation = new JsonRepresentation(representation.getText());
			} catch (final IOException e) {
				LOGGERS.error("Error in setting the encrypted json text: " + e.getMessage());
			}
			
			getResponse().setEntity(representation);
			resetValues();
			return representation;
		} finally {
			long endTime = System.currentTimeMillis();
			final float totalTime = (endTime - startTime) / 1000.0f;
			LOGGERS.info(requestString + "\nTotal time taken to process request : " + totalTime + " seconds\n\n");
		}
	}
	
	/* getting the resource access path */
	private String getResourcePath() {
		String reqResource = getRequest().getResourceRef().getPath();
		reqResource = actualPath(reqResource, 1);
		LOGGERS.info("reqResourcePath---->" + reqResource);
		return reqResource;
	}

	/* return the requested resource from the URL path */
	public String actualPath(final String path, final int val) {
		String[] pathArr = {};
		if (!("").equals(path)) {
			pathArr = path.split("/");
		}
		String resource = "";
		try {
			resource = pathArr[pathArr.length - val];
		} catch (final ArrayIndexOutOfBoundsException boundsException) {
			boundsException.printStackTrace();
		}
		return resource;
	}
	
	public SalesChannelBaseJsonObject validateAuthentication() {
		try {
			resetValues();
			final Form headers = (Form) getRequestAttributes().get("org.restlet.http.headers");
			Map<String, String> headerValues = headers.getValuesMap();
			authToken = headerValues.get("authtoken");
			CustomerJsonModel customerJsonModel = customerService.validateLogin(authToken);
			if(customerJsonModel != null) {
				customerId = customerJsonModel.getId();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		final SalesChannelBaseJsonObject salesChannelBaseJsonObject = new SalesChannelBaseJsonObject();
		salesChannelBaseJsonObject.setAuthToken(authToken);
		salesChannelBaseJsonObject.setCustomerId(customerId);
		return salesChannelBaseJsonObject;
	}
	
	public void resetValues() {
		customerId = null;
		authToken = null;
		salesChannelErrorObject = new SalesChannelErrorObject();
		jsonResponse = new JSONObject();
	}
	
	/*@SuppressWarnings("unchecked")
	public void setResponseHeaders() {
		final ConcurrentMap<String, Object> attrs = getResponse().getAttributes();
		Series<Header> headers = (Series<Header>) attrs.get(HeaderConstants.ATTRIBUTE_HEADERS);
		if (headers == null) {
			headers = new Series<Header>(Header.class);
			final Series<Header> prev = (Series<Header>)
					attrs.putIfAbsent(HeaderConstants.ATTRIBUTE_HEADERS, headers);
			if (prev != null) {
				headers = prev;
			}
		}
		getResponse().setAccessControlAllowOrigin("*");
		final Set<Method> accessControlAllowMethods = new HashSet<Method>(Arrays.asList(Method.GET, Method.POST, Method.PUT, Method.DELETE));
		getResponse().setAccessControlAllowMethods(accessControlAllowMethods);
		getResponse().setAge(86400);
	}*/
	
	// abstract methods that subclass must implement
	@Get("JSON")
	public abstract Representation fetchDetails();
	
	@Post("JSON")
	public abstract Representation insertOrUpdateDetails(Representation entity, T obj);
	
	@Put("JSON")
	public abstract Representation updateDetails(Representation entity, T obj);
	
	@Delete
	public abstract Representation deleteDetails();
	
	public abstract JSONObject validate(T obj, String method, JSONObject jsonObject, Form form) throws JSONException;
	
	public abstract T getJsonObject(InputStream stream) throws JsonParseException, JsonMappingException, IOException, JSONException;

	public abstract List<String> getParametersList();
	
	// Utility to construct error JSON with given message and value
	private JSONObject validateJSON;
	
	public JSONObject getJSONObject(final String message, final String value) {
		validateJSON = new JSONObject();
		
		try {
			validateJSON.put(message, value);
		} catch (final JSONException e) {
			LOGGERS.error("Error in Forming the Json object: " + e.getMessage());
		}
		
		return validateJSON;
	}
	
	public String getErrorMessage(final int statusCode) {
		final Properties properties = SalesChannelPropertyLoader.salesChannelErrorProperties;
		final String value = properties.getProperty(String.valueOf(statusCode));
		
		if (value == null) {
			LOGGERS.error("The property for this status code is not added to the file: " + statusCode);
		}
		
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public SalesChannelErrorObject getErrorObject(final JSONObject jsonObject) {
		final Iterator<String> keyIterator = jsonObject.keys();
		for (final Iterator<String> iterator2 = keyIterator; iterator2.hasNext();) {
			final String key = iterator2.next();
			String value = "";
			try {
				value = jsonObject.get(key).toString();
			} catch (final JSONException e) {
				LOGGERS.error("Error in the retreving the key: " + e.getMessage());
			}
			
			try {
				salesChannelErrorObject.setStatusCode(Integer.parseInt(key));
				final int index = value.indexOf("@#");
				
				if (index >= 0) {
					final String fieldName = value.substring(index + 2, value.length() - 2);
					salesChannelErrorObject.setFieldName(fieldName);
				}
				
				if (index >= 0) {
					value = value.substring(0, index);
				}
				
				salesChannelErrorObject.setMessage(value);
			} catch (final Exception e) {
				LOGGERS.error("Invalid key: " + e.getMessage());
				return null;
			}
		}
		
		return salesChannelErrorObject;
	}

	public JSONObject validateParameters(final Form form, final List<String> parameterToPass) {
		JSONObject jsonObject = null;
		final ArrayList<String> invalidparam = new ArrayList<String>();
		
		if (parameterToPass != null && parameterToPass.size() > 0) {
			if (form != null && !form.isEmpty()) {
				final Set<String> parameternames = form.getNames();
				
				for (final Iterator<String> iterator = parameternames.iterator(); iterator.hasNext();) {
					final String paraPassed = iterator.next();
					boolean present = false;
					
					for (final Iterator<String> iteParamaToPass = parameterToPass.iterator(); iteParamaToPass.hasNext();) {
						final String strParamToPass = iteParamaToPass.next();
						if (paraPassed.equals(strParamToPass)) {
							present = true;
						}
					}
					
					// Check to see only invalid parameters are passed
					if (!present) {
						invalidparam.add(paraPassed);
					}
				}
			}
			
			if (invalidparam.size() >= 1) {
				jsonObject = getJSONObject("1005", "Invalid Parameters.@#" + invalidparam.toString() + "#@");
			} else {
				jsonObject = new JSONObject();
			}
			
		} else {
			jsonObject = new JSONObject();
		}
		
		return jsonObject;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public CustomerServiceImpl getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerServiceImpl customerService) {
		this.customerService = customerService;
	}
}
