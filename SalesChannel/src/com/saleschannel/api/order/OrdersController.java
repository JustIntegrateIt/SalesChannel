package com.saleschannel.api.order;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import com.saleschannel.api.SalesChannelServerResource;
import com.saleschannel.api.order.amazonmws.AmazonOrdersJsonModel;

public class OrdersController extends SalesChannelServerResource<OrdersJsonObject>{

	private static final Logger LOGGERS = Logger.getLogger(OrdersController.class);

	private OrdersServiceImpl ordersService;
	
	@Override
	public Representation fetchDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,	OrdersJsonObject obj) {
		Representation representation = null;
		try {
			AmazonOrdersJsonModel amazonOrdersJsonObject = new AmazonOrdersJsonModel();
			/*amazonOrdersJsonObject.setAmazonOrderId("123456789");
			amazonOrdersJsonObject.setBuyerName("sathishkumar");
			ordersService.insertAmazonOrder(amazonOrdersJsonObject);*/
			amazonOrdersJsonObject.setId("58add9dc84ae2064c5439d2c");
			amazonOrdersJsonObject.setBuyerName("karthick");
			amazonOrdersJsonObject.setAmazonOrderId("123456789");
			ordersService.updateAmazonOrder(amazonOrdersJsonObject);
			ordersService.getAmazonOrderByVariable("amazonOrderId", "123456789");
			salesChannelErrorObject.setStatusCode(200);
			salesChannelErrorObject.setMessage(getErrorMessage(200));
			salesChannelErrorObject.setData("Product Added Successfully.");
			representation = new JsonRepresentation(salesChannelErrorObject);
		} catch (Exception e) {
			LOGGERS.error("Error occured while insertOrUpdateDetails Order.");
			e.printStackTrace();
		}
		return representation;
	}

	@Override
	public Representation updateDetails(Representation entity,
			OrdersJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(OrdersJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getParametersList() {
		// TODO Auto-generated method stub
		return null;
	}

	public OrdersServiceImpl getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersServiceImpl ordersService) {
		this.ordersService = ordersService;
	}

}
