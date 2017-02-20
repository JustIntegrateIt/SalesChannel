package com.saleschannel.api.order;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;

public class OrdersDaoImpl implements OrdersDao {

private static final Logger LOGGERS = Logger.getLogger(OrdersDaoImpl.class);
	
	private MongoOperations mongoOps;
 	
	public OrdersDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
}
