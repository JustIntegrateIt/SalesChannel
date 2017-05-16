package com.saleschannel.api.quartz;

import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.saleschannel.api.utility.SalesChannelBeanLocator;

public class QuartzListener implements ServletContextListener {

	private static final Logger LOGGERS = Logger.getLogger(QuartzListener.class);
	
	Scheduler scheduler = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LOGGERS.info("Context Destroyed");
        try {
        	scheduler.shutdown();
        } catch (SchedulerException e) {
        	e.printStackTrace();
        }
	}

	/*Get all the active jobs from database and Scheduler it*/
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			boolean hasJobs = false;
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
			String quartzDomain = System.getProperty("QUARTZ_DOMAIN_NAME");
			if (quartzDomain != null && quartzDomain.equals("local")) {
				QuartzListenerDaoImpl quartzListenerDao = (QuartzListenerDaoImpl) SalesChannelBeanLocator.getInstance().findBean("quartzListenerDao");
				List<QuartzListenerJsonModel> quartzJobs = quartzListenerDao.getQuartzJobs();
				if(quartzJobs.size() > 0) {
					LOGGERS.debug("Total no. of Jobs to Schedule : "+quartzJobs.size());
					JobDetail[] job = new JobDetail[quartzJobs.size()];
					CronTrigger[] trigger = new CronTrigger[quartzJobs.size()];
					for(int i = 0; i < quartzJobs.size(); i++) {
						job[i] = new JobDetail();
						job[i].setName(quartzJobs.get(i).getJobName());
						job[i].setGroup(quartzJobs.get(i).getJobGroup());
						job[i].setJobClass(Class.forName(quartzJobs.get(i).getJobClass()));
						trigger[i] = new CronTrigger();
						trigger[i].setName(quartzJobs.get(i).getTriggerName());
						trigger[i].setCronExpression(quartzJobs.get(i).getTriggerCronExpression());
						trigger[i].setGroup(quartzJobs.get(i).getTriggerGroup());
						trigger[i].setTimeZone(TimeZone.getTimeZone(quartzJobs.get(i).getTriggerTimeZone()));
						LOGGERS.debug("Job : "+job[i]+" created");
						LOGGERS.debug("Job : "+trigger[i]+" created");
						scheduler.scheduleJob(job[i], trigger[i]);
						LOGGERS.info("Quartz Job Added");
						hasJobs = true;
					}
				} else {
					LOGGERS.debug("No Jobs to Schedule");
				}
			}else{
				LOGGERS.info("Quartz Job Not started in "+quartzDomain);
			}
			if(hasJobs) {
				scheduler.start();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
