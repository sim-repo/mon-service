package com.simple.server.job;

import java.util.Date;

import com.simple.server.config.AppConfig;
import com.simple.server.config.JobStatusType;

public interface IJob {
	void run() throws Exception;
	String getKey();
	String getGroupId();
	Integer getOrderId();
	
	Date getJobWhen();
	Date getJobUntil();
	
	Long getJobDelay();
	Long getJobPeriod();
	
	JobStatusType getStatus();
	void setStatus(JobStatusType status);
	void setAppConfig(AppConfig appConfig);
	String getJuuid();
	void setJuuid(String juud);
}
