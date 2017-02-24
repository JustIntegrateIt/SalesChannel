package com.saleschannel.api.order;

import java.util.List;

public interface OrdersDao {
	
	public boolean insertOrder(Object order);
	
	public boolean updateOrder(Object order);
	
	public AmazonOrdersJsonModel getAmazonOrderByVariable(String variableName, String variableValue);
	
	public boolean insertShippingAddress(Object address);
	
	public boolean updateShippingAddress(Object address);
	
	public AmazonOrderShippingAddressJsonModel getAmazonOrderShippingAddressByVariable(String variableName, String variableValue);
	
	public boolean insertAmazonPaymentExeDetail(AmazonOrderPaymentExeDetailJsonModel paymentExeDetailJsonModel);
	
	public boolean updateAmazonPaymentExeDetail(AmazonOrderPaymentExeDetailJsonModel paymentExeDetailJsonModel);
	
	public List<AmazonOrderPaymentExeDetailJsonModel> getAmazonOrderPaymentExeDetailByVariable(String variableName, String variableValue);
	
	public String insertAmazonOrderInvoiceData(AmazonOrderInvoiceDataJsonModel invoiceDataJsonModel);
	
	public boolean updateAmazonOrderInvoiceData(AmazonOrderInvoiceDataJsonModel invoiceDataJsonModel);
	
	public AmazonOrderInvoiceDataJsonModel getAmazonOrderInvoiceDataByVariable(String variableName, String variableValue);
	
	public boolean insertAmazonOrderItem(AmazonOrderItemsJsonModel orderItemJsonModel);
	
	public boolean updateAmazonOrderItem(AmazonOrderItemsJsonModel orderItemJsonModel);
	
	public List<AmazonOrderItemsJsonModel> getAmazonOrderItemsByVariable(String variableName, String variableValue);
}
