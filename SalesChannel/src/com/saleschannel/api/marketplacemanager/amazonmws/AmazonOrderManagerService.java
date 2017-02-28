package com.saleschannel.api.marketplacemanager.amazonmws;

import java.util.List;

import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.model.GetServiceStatusResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsByNextTokenResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersByNextTokenResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrdersResponse;

public interface AmazonOrderManagerService {

	public  List<GetOrderResponse> getOrderAsync(String sellerId, String mwsAuthToken
    		, List<String> amazonOrderId);
	
	public GetOrderResponse getOrder(String sellerId, String mwsAuthToken
			, List<String> amazonOrderId);
	
	public List<GetServiceStatusResponse> getServiceStatusAsync(String sellerId, String mwsAuthToken);
	
	public GetServiceStatusResponse getServiceStatus(String sellerId, String mwsAuthToken);
	
	public List<ListOrderItemsResponse> listOrderItemsAsync(String sellerId, String mwsAuthToken
    		, String amazonOrderId);
	
	public List<ListOrderItemsByNextTokenResponse> listOrderItemsByNextTokenAsync(String sellerId, String mwsAuthToken
    		, String nextToken);
	
	public ListOrderItemsByNextTokenResponse listOrderItemsByNextToken(String sellerId, String mwsAuthToken, String nextToken);
	
	public ListOrderItemsResponse listOrderItems(String sellerId, String mwsAuthToken, String amazonOrderId);
	
	public List<ListOrdersResponse> listOrdersAsync(List<ListOrdersRequest> requestList);
	
	public List<ListOrdersByNextTokenResponse> listOrdersByNextTokenAsync(String sellerId, String mwsAuthToken, String nextToken);
	
	public ListOrdersByNextTokenResponse listOrdersByNextToken(String sellerId, String mwsAuthToken, String nextToken);
	
	public ListOrdersResponse listOrders(ListOrdersRequest request);
}
