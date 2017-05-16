package com.saleschannel.api.quartz;

public class QuartzListenerJsonModel {

	private String jobName;
	
	private String jobGroup;
	
	private String jobClass;
	
	private String triggerName;
	
	private String triggerCronExpression;
	
	private String triggerGroup;
	
	private String triggerTimeZone;
	
	private boolean isActive;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerCronExpression() {
		return triggerCronExpression;
	}

	public void setTriggerCronExpression(String triggerCronExpression) {
		this.triggerCronExpression = triggerCronExpression;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getTriggerTimeZone() {
		return triggerTimeZone;
	}

	public void setTriggerTimeZone(String triggerTimeZone) {
		this.triggerTimeZone = triggerTimeZone;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
