package com.simple.server.job;

import java.util.Date;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.JobStatusType;
import com.simple.server.job.time.MySchedule;


@Service("lifecycleJob")
@Scope("singleton")
public class LifecycleJob implements IJob{

	private String testId;	
	private String eventId;
	private String serviceHandler;
	private String juuid;
	private String senderId;	
	private String body;
	private Integer timeout;
	
	private Date targetWhen;
	private Date targetUntil;
	private Long targetDelay;
	private Long targetPeriod;
	private Date jobWhen;
	private Date jobUntil;
	private Long jobDelay;
	private Long jobPeriod;	
	private String status;
	
	private Set<MySchedule> schedulers;
	
	
	@Override
	public void run() throws Exception {
		System.out.println("running task");
	}
	
	@Override
	public String getKey() {		
		return getTestId();
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getServiceHandler() {
		return serviceHandler;
	}

	public void setServiceHandler(String serviceHandler) {
		this.serviceHandler = serviceHandler;
	}

	public String getJuuid() {
		return juuid;
	}

	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	
	public Date getTargetWhen() {
		return targetWhen;
	}

	public void setTargetWhen(Date targetWhen) {
		this.targetWhen = targetWhen;
	}

	public Date getTargetUntil() {
		return targetUntil;
	}

	public void setTargetUntil(Date targetUntil) {
		this.targetUntil = targetUntil;
	}

	public Long getTargetDelay() {
		return targetDelay;
	}

	public void setTargetDelay(Long targetDelay) {
		this.targetDelay = targetDelay;
	}

	public Long getTargetPeriod() {
		return targetPeriod;
	}

	public void setTargetPeriod(Long targetPeriod) {
		this.targetPeriod = targetPeriod;
	}

	@Override
	public Date getJobWhen() {
		return jobWhen;
	}

	public void setJobWhen(Date jobWhen) {
		this.jobWhen = jobWhen;
	}

	@Override
	public Date getJobUntil() {
		return jobUntil;
	}

	public void setJobUntil(Date jobUntil) {
		this.jobUntil = jobUntil;
	}

	@Override
	public Long getJobDelay() {
		return jobDelay;
	}

	public void setJobDelay(Long jobDelay) {
		this.jobDelay = jobDelay;
	}

	@Override
	public Long getJobPeriod() {
		return jobPeriod;
	}

	public void setJobPeriod(Long jobPeriod) {
		this.jobPeriod = jobPeriod;
	}

	@Override
	public JobStatusType getStatus() {
		return JobStatusType.fromValue(status);
	}

	public void setStatus(JobStatusType status) {
		this.status = status.toValue();
	}
		
}
