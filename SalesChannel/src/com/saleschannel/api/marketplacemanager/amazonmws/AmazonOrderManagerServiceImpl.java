package com.saleschannel.api.marketplacemanager.amazonmws;

import java.util.List;

import org.apache.log4j.Logger;

import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.model.GetServiceStatusResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsByNextTokenResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersByNextTokenResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersResponse;
import com.amazonservices.mws.orders._2013_09_01.samples.GetOrderAsyncSample;
import com.amazonservices.mws.orders._2013_09_01.samples.GetOrderSample;
import com.amazonservices.mws.orders._2013_09_01.samples.GetServiceStatusAsyncSample;
import com.amazonservices.mws.orders._2013_09_01.samples.GetServiceStatusSample;
import com.amazonservices.mws.orders._2013_09_01.samples.ListOrderItemsAsyncSample;
import com.amazonservices.mws.orders._2013_09_01.samples.ListOrderItemsByNextTokenAsyncSample;
import com.amazonservices.mws.orders._2013_09_01.samples.ListOrderItemsByNextTokenSample;
import com.amazonservices.mws.orders._2013_09_01.samples.ListOrderItemsSample;
import com.amazonservices.mws.orders._2013_09_01.samples.ListOrdersAsyncSample;
import com.amazonservices.mws.orders._2013_09_01.samples.ListOrdersByNextTokenAsyncSample;
import com.amazonservices.mws.orders._2013_09_01.samples.ListOrdersByNextTokenSample;
import com.amazonservices.mws.orders._2013_09_01.samples.ListOrdersSample;

public class AmazonOrderManagerServiceImpl implements AmazonOrderManagerService {

	private static final Logger LOGGERS = Logger.getLogger(AmazonOrderManagerServiceImpl.class);
	
	@Override
	public List<GetOrderResponse> getOrderAsync(String sellerId,
			String mwsAuthToken, List<String> amazonOrderId) {
		List<GetOrderResponse> orderResponseList = null;
		try {
			GetOrderAsyncSample getOrderAsyncSample = new GetOrderAsyncSample();
			orderResponseList = getOrderAsyncSample.getOrderAsync(sellerId, mwsAuthToken, amazonOrderId);
		} catch(Exception e) {
			LOGGERS.error("error occured while get OrderAsync");
			e.printStackTrace();
		}
		return orderResponseList;
	}

	@Override
	public GetOrderResponse getOrder(String sellerId, String mwsAuthToken,
			List<String> amazonOrderId) {
		GetOrderResponse orderResponse = null;
		try {
			GetOrderSample getOrderSample = new GetOrderSample();
			orderResponse = getOrderSample.getOrder(sellerId, mwsAuthToken, amazonOrderId);
		} catch(Exception e) {
			LOGGERS.error("error occured while get Order");
			e.printStackTrace();
		}
		return orderResponse;
	}

	@Override
	public List<GetServiceStatusResponse> getServiceStatusAsync(
			String sellerId, String mwsAuthToken) {
		List<GetServiceStatusResponse> serviceStatusResponseList = null;
		try {
			GetServiceStatusAsyncSample getServiceStatusAsyncSample = new GetServiceStatusAsyncSample();
			serviceStatusResponseList = getServiceStatusAsyncSample.getServiceStatusAsync(sellerId, mwsAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while get ServiceStatusAsync");
			e.printStackTrace();
		}
		return serviceStatusResponseList;
	}

	@Override
	public GetServiceStatusResponse getServiceStatus(String sellerId,
			String mwsAuthToken) {
		GetServiceStatusResponse serviceStatusResponse = null;
		try {
			GetServiceStatusSample getServiceStatusSample = new GetServiceStatusSample();
			serviceStatusResponse = getServiceStatusSample.getServiceStatus(sellerId, mwsAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while get ServiceStatus");
			e.printStackTrace();
		}
		return serviceStatusResponse;
	}

	@Override
	public List<ListOrderItemsResponse> listOrderItemsAsync(String sellerId,
			String mwsAuthToken, String amazonOrderId) {
		List<ListOrderItemsResponse> orderItemsResponseList = null;
		try {
			ListOrderItemsAsyncSample listOrderItemsAsyncSample = new ListOrderItemsAsyncSample();
			orderItemsResponseList = listOrderItemsAsyncSample.listOrderItemsAsync(sellerId, mwsAuthToken, amazonOrderId);
		} catch(Exception e) {
			LOGGERS.error("error occured while list OrderItemsAsync");
			e.printStackTrace();
		}
		return orderItemsResponseList;
	}

	@Override
	public List<ListOrderItemsByNextTokenResponse> listOrderItemsByNextTokenAsync(
			String sellerId, String mwsAuthToken, String nextToken) {
		List<ListOrderItemsByNextTokenResponse> orderItemsByNextTokenResponseList = null;
		try {
			ListOrderItemsByNextTokenAsyncSample listOrderItemsByNextTokenAsyncSample = new ListOrderItemsByNextTokenAsyncSample();
			orderItemsByNextTokenResponseList = listOrderItemsByNextTokenAsyncSample.listOrderItemsByNextTokenAsync(sellerId, mwsAuthToken, nextToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while list OrderItemsByNextTokenAsync");
			e.printStackTrace();
		}
		return orderItemsByNextTokenResponseList;
	}

	@Override
	public ListOrderItemsByNextTokenResponse listOrderItemsByNextToken(
			String sellerId, String mwsAuthToken, String nextToken) {
		ListOrderItemsByNextTokenResponse listOrderItemsByNextToken = null;
		try {
			ListOrderItemsByNextTokenSample listOrderItemsByNextTokenSample = new ListOrderItemsByNextTokenSample();
			listOrderItemsByNextToken = listOrderItemsByNextTokenSample.listOrderItemsByNextToken(sellerId, mwsAuthToken, nextToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while list OrderItemsByNextToken");
			e.printStackTrace();
		}
		return listOrderItemsByNextToken;
	}

	@Override
	public ListOrderItemsResponse listOrderItems(String sellerId,
			String mwsAuthToken, String amazonOrderId) {
		ListOrderItemsResponse orderItemsResponse = null;
		try {
			ListOrderItemsSample listOrderItemsSample = new ListOrderItemsSample();
			orderItemsResponse = listOrderItemsSample.listOrderItems(sellerId, mwsAuthToken, amazonOrderId);
		} catch(Exception e) {
			LOGGERS.error("error occured while list OrderItems");
			e.printStackTrace();
		}
		return orderItemsResponse;
	}

	@Override
	public List<ListOrdersResponse> listOrdersAsync(List<ListOrdersRequest> requestList) {
		List<ListOrdersResponse> ordersResponseList = null;
		try {
			ListOrdersAsyncSample listOrdersAsyncSample = new ListOrdersAsyncSample();
			ordersResponseList = listOrdersAsyncSample.listOrdersAsync(requestList);
		} catch(Exception e) {
			LOGGERS.error("error occured while list OrdersAsync");
			e.printStackTrace();
		}
		return ordersResponseList;
	}

	@Override
	public List<ListOrdersByNextTokenResponse> listOrdersByNextTokenAsync(
			String sellerId, String mwsAuthToken, String nextToken) {
		List<ListOrdersByNextTokenResponse> listOrdersByNextTokenResponseList = null;
		try {
			ListOrdersByNextTokenAsyncSample listOrdersByNextTokenAsyncSample = new ListOrdersByNextTokenAsyncSample();
			listOrdersByNextTokenResponseList = listOrdersByNextTokenAsyncSample.listOrdersByNextTokenAsync(sellerId, mwsAuthToken, nextToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while list OrdersByNextTokenAsync");
			e.printStackTrace();
		}
		return listOrdersByNextTokenResponseList;
	}

	@Override
	public ListOrdersByNextTokenResponse listOrdersByNextToken(String sellerId,
			String mwsAuthToken, String nextToken) {
		ListOrdersByNextTokenResponse listOrdersByNextTokenResponse = null;
		try {
			ListOrdersByNextTokenSample listOrdersByNextTokenSample = new ListOrdersByNextTokenSample();
			listOrdersByNextTokenResponse = listOrdersByNextTokenSample.listOrdersByNextToken(sellerId, mwsAuthToken, nextToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while list OrdersByNextToken");
			e.printStackTrace();
		}
		return listOrdersByNextTokenResponse;
	}

	@Override
	public ListOrdersResponse listOrders(ListOrdersRequest request) {
		ListOrdersResponse listOrdersResponse = null;
		try {
			ListOrdersSample listOrdersSample = new ListOrdersSample();
			listOrdersResponse = listOrdersSample.listOrders(request);
		} catch(Exception e) {
			LOGGERS.error("error occured while list Orders");
			e.printStackTrace();
		}
		return listOrdersResponse;
	}
}
