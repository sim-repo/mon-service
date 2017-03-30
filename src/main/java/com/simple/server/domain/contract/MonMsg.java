package com.simple.server.domain.contract;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonAutoDetect
@JsonDeserialize(as = MonMsg.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonMsg extends AContract {

	private String taskClazz;
	private String beanStatClazz;
	private String when;
	private long period;
	private long delay;
	private String until;
	private long ttl;
	private long startTimeMillis;
	private long endTimeMillis;
	
	@Override
	public String getClazz() {
		return MonMsg.class.getName();
	}

	public String getTaskClazz() {
		return taskClazz;
	}
	
	public String getBeanStatClazz() {
		return beanStatClazz;
	}

	public void setBeanStatClazz(String statClazz) {
		this.beanStatClazz = statClazz;
	}

	public void setTaskClazz(String taskClazz) {
		this.taskClazz = taskClazz;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public String getUntil() {
		return until;
	}

	public void setUntil(String until) {
		this.until = until;
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	public long getStartTimeMillis() {
		return startTimeMillis;
	}

	public void setStartTimeMillis(long startTimeMillis) {
		this.startTimeMillis = startTimeMillis;
	}

	public long getEndTimeMillis() {
		return endTimeMillis;
	}

	public void setEndTimeMillis(long endTimeMillis) {
		this.endTimeMillis = endTimeMillis;
	}
	
}
