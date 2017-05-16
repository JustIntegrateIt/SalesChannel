package com.saleschannel.api.quartz;

import java.util.List;

public interface QuartzListenerDao {

	public List<QuartzListenerJsonModel> getQuartzJobs();
}
