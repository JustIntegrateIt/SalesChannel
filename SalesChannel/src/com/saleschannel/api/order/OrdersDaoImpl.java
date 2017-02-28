package com.saleschannel.api.order;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.order.amazonmws.AmazonOrderInvoiceDataJsonModel;
import com.saleschannel.api.order.amazonmws.AmazonOrderItemsJsonModel;
import com.saleschannel.api.order.amazonmws.AmazonOrderPaymentExeDetailJsonModel;
import com.saleschannel.api.order.amazonmws.AmazonOrderShippingAddressJsonModel;
import com.saleschannel.api.order.amazonmws.AmazonOrdersJsonModel;

public class OrdersDaoImpl implements OrdersDao {

private static final Logger LOGGERS = Logger.getLogger(OrdersDaoImpl.class);
	
	private MongoOperations mongoOps;
 	
	public OrdersDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	//can insert all MarketPlace orders by this method
	public boolean insertOrder(Object order) {
		boolean status = false;
		try{
			this.mongoOps.insert(order, SalesChannelConstants.SC_ORDER);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while insertOrder in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	//can update all MarketPlace orders by this method
	public boolean updateOrder(Object order) {
		boolean status = false;
		try{
			this.mongoOps.save(order, SalesChannelConstants.SC_ORDER);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while updateOrder in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public AmazonOrdersJsonModel getAmazonOrderByVariable(String variableName, String variableValue) {
		AmazonOrdersJsonModel order = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.order.amazonmws.AmazonOrdersJsonModel")
					.and(variableName).is(variableValue));
			order = this.mongoOps.findOne(query, AmazonOrdersJsonModel.class, SalesChannelConstants.SC_ORDER);
		} catch(Exception e) {
			LOGGERS.error("error occured while get AmazonOrderByVariable from DB");
			e.printStackTrace();
		}
		return order;
	}
	
	//can insert all MarketPlace orders shipping addresses by this method
	public boolean insertShippingAddress(Object address) {
		boolean status = false;
		try {
			this.mongoOps.insert(address, SalesChannelConstants.SC_ORDER_SHIPPING_ADDRESS);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while insert ShippingAddress in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	//can update all MarketPlace orders shipping addresses by this method
	public boolean updateShippingAddress(Object address) {
		boolean status = false;
		try {
			this.mongoOps.save(address, SalesChannelConstants.SC_ORDER_SHIPPING_ADDRESS);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update ShippingAddress in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public AmazonOrderShippingAddressJsonModel getAmazonOrderShippingAddressByVariable(String variableName, String variableValue) {
		AmazonOrderShippingAddressJsonModel address = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.order.amazonmws.AmazonOrderShippingAddressJsonModel")
					.and(variableName).is(variableValue));
			address = this.mongoOps.findOne(query, AmazonOrderShippingAddressJsonModel.class, SalesChannelConstants.SC_ORDER_SHIPPING_ADDRESS);
		} catch(Exception e) {
			LOGGERS.error("error occured while get AmazonOrderShippingAddressByVariable from DB");
			e.printStackTrace();
		}
		return address;
	}
	
	public boolean insertAmazonPaymentExeDetail(AmazonOrderPaymentExeDetailJsonModel paymentExeDetailJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.insert(paymentExeDetailJsonModel, SalesChannelConstants.SC_AMAZON_ORDER_PAYMENT_EXECUTION_DETAILS);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while insert AmazonPaymentExeDetail in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean updateAmazonPaymentExeDetail(AmazonOrderPaymentExeDetailJsonModel paymentExeDetailJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(paymentExeDetailJsonModel, SalesChannelConstants.SC_AMAZON_ORDER_PAYMENT_EXECUTION_DETAILS);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update AmazonPaymentExeDetail in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public List<AmazonOrderPaymentExeDetailJsonModel> getAmazonOrderPaymentExeDetailByVariable(String variableName, String variableValue) {
		List<AmazonOrderPaymentExeDetailJsonModel> paymentExeDetailList = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.order.amazonmws.AmazonOrderPaymentExeDetailJsonModel")
					.and(variableName).is(variableValue));
			paymentExeDetailList = this.mongoOps.find(query, AmazonOrderPaymentExeDetailJsonModel.class, SalesChannelConstants.SC_AMAZON_ORDER_PAYMENT_EXECUTION_DETAILS);
		} catch(Exception e) {
			LOGGERS.error("error occured while get AmazonOrderPaymentExeDetailByVariable from DB");
			e.printStackTrace();
		}
		return paymentExeDetailList;
	}
	
	public String insertAmazonOrderInvoiceData(AmazonOrderInvoiceDataJsonModel invoiceDataJsonModel) {
		String invoiceId = null;
		try {
			ObjectId objectId = new ObjectId();
			invoiceDataJsonModel.setId(objectId.toString());
			invoiceId = objectId.toString(); 
			this.mongoOps.insert(invoiceDataJsonModel, SalesChannelConstants.SC_AMAZON_ORDER_INVOICE_DATA);
		} catch(Exception e) {
			LOGGERS.error("error occured while insert AmazonOrderInvoiceData in DB");
			e.printStackTrace();
		}
		return invoiceId;
	}
	
	public boolean updateAmazonOrderInvoiceData(AmazonOrderInvoiceDataJsonModel invoiceDataJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(invoiceDataJsonModel, SalesChannelConstants.SC_AMAZON_ORDER_INVOICE_DATA);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update AmazonOrderInvoiceData in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public AmazonOrderInvoiceDataJsonModel getAmazonOrderInvoiceDataByVariable(String variableName, String variableValue) {
		AmazonOrderInvoiceDataJsonModel amazonOrderInvoiceData = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.order.amazonmws.AmazonOrderInvoiceDataJsonModel")
					.and(variableName).is(variableValue));
			amazonOrderInvoiceData = this.mongoOps.findOne(query, AmazonOrderInvoiceDataJsonModel.class, SalesChannelConstants.SC_AMAZON_ORDER_INVOICE_DATA);
		} catch(Exception e) {
			LOGGERS.error("error occured while get AmazonOrderInvoiceDataByVariable from DB");
			e.printStackTrace();
		}
		return amazonOrderInvoiceData;
	}
	
	public boolean insertAmazonOrderItem(AmazonOrderItemsJsonModel orderItemJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.insert(orderItemJsonModel, SalesChannelConstants.SC_AMAZON_ORDER_ITEMS);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while insert AmazonOrderItem in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean updateAmazonOrderItem(AmazonOrderItemsJsonModel orderItemJsonModel) {
		boolean status = false;
		try {
			this.mongoOps.save(orderItemJsonModel, SalesChannelConstants.SC_AMAZON_ORDER_ITEMS);
			status = true;
		} catch(Exception e) {
			LOGGERS.error("error occured while update AmazonOrderItem in DB");
			e.printStackTrace();
		}
		return status;
	}
	
	public List<AmazonOrderItemsJsonModel> getAmazonOrderItemsByVariable(String variableName, String variableValue) {
		List<AmazonOrderItemsJsonModel> amazonOrderItemsList = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.order.amazonmws.AmazonOrderItemsJsonModel")
					.and(variableName).is(variableValue));
			amazonOrderItemsList = this.mongoOps.find(query, AmazonOrderItemsJsonModel.class, SalesChannelConstants.SC_AMAZON_ORDER_ITEMS);
		} catch(Exception e) {
			LOGGERS.error("error occured while get AmazonOrderItemsByVariable from DB");
			e.printStackTrace();
		}
		return amazonOrderItemsList;
	}

}
