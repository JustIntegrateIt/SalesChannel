package com.saleschannel.api.flatfile;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.saleschannel.api.constants.SalesChannelConstants;

public class FlatFileDaoImpl implements FlatFileDao {

	private static final Logger LOGGERS = Logger.getLogger(FlatFileDaoImpl.class);

	private MongoOperations mongoOps;
 	
	public FlatFileDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	@Override
	public FlatFileJsonModel getFlatFile(String categoryId) {
		FlatFileJsonModel FlatFileJsonModel = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.flatfile.FlatFileJsonModel")
					.and("productCategoryId").is(categoryId));
			FlatFileJsonModel = this.mongoOps.findOne(query, FlatFileJsonModel.class, SalesChannelConstants.SC_AMAZON_FLAT_FILES);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFlatFile from DB");
			e.printStackTrace();
		}
		return FlatFileJsonModel;
	}

}
