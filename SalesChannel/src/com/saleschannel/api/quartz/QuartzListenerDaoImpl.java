package com.saleschannel.api.quartz;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.saleschannel.api.constants.SalesChannelConstants;

public class QuartzListenerDaoImpl implements QuartzListenerDao {

	private static final Logger LOGGERS = Logger.getLogger(QuartzListenerDaoImpl.class);

	private MongoOperations mongoOps;
 	
	public QuartzListenerDaoImpl(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
    }
	
	@Override
	public List<QuartzListenerJsonModel> getQuartzJobs() {
		List<QuartzListenerJsonModel> quartzJobs = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_class").is("com.saleschannel.api.quartz.QuartzListenerJsonModel")
					.and("isActive").is(true));
			quartzJobs = this.mongoOps.find(query, QuartzListenerJsonModel.class, SalesChannelConstants.SC_QUARTZ_JOB);			
		} catch(Exception e) {
			LOGGERS.error("error while get QuartzJobs in database");
			e.printStackTrace();
		}
		return quartzJobs;
	}
}
