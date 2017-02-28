package com.saleschannel.api.order;

import java.util.List;

import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResponse;
import com.saleschannel.api.order.amazonmws.AmazonOrderItemsJsonModel;
import com.saleschannel.api.order.amazonmws.AmazonOrdersJsonModel;

public interface OrdersService {

	public boolean insertAmazonOrders(GetOrderResponse orderResponse);
	
	public boolean insertAmazonOrder(AmazonOrdersJsonModel amazonOrdersJsonObject);
	
	public boolean updateAmazonOrder(AmazonOrdersJsonModel amazonOrdersJsonObject);
	
	public OrdersJsonObject getAmazonOrderByVariable(String variableName, String variableValue);
	
	public boolean insertAmazonOrderItems(ListOrderItemsResponse orderItemsResponse);
	
	public boolean insertAmazonOrderItems(AmazonOrderItemsJsonModel amazonOrderItem);
	
	public boolean updateAmazonOrderItems(AmazonOrderItemsJsonModel amazonOrderItem);
	
	public List<AmazonOrderItemsJsonModel> getAmazonOrderItemsByVariable(String variableName, String variableValue);
}
