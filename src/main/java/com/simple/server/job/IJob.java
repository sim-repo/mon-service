package com.simple.server.job;

import java.util.Date;

import com.simple.server.config.JobStatusType;

public interface IJob {
	void run() throws Exception;
	String getKey();
	Date getJobWhen();
	Date getJobUntil();
	
	Long getJobDelay();
	Long getJobPeriod();
	
	JobStatusType getStatus();
	void setStatus(JobStatusType status);
}
