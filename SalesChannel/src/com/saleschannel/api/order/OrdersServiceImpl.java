package com.saleschannel.api.order;

import org.apache.log4j.Logger;

public class OrdersServiceImpl implements OrdersService {

	private static final Logger LOGGERS = Logger.getLogger(OrdersServiceImpl.class);
	
	private OrdersDaoImpl ordersDao;

	public OrdersDaoImpl getOrdersDao() {
		return ordersDao;
	}

	public void setOrdersDao(OrdersDaoImpl ordersDao) {
		this.ordersDao = ordersDao;
	}

}
