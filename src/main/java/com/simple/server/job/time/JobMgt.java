package com.simple.server.job.time;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.config.JobStatusType;
import com.simple.server.job.IJob;

@Service("jobMgt")
@Scope("singleton")
public class JobMgt {

	private static final ConcurrentHashMap<String, TimerTask> TIMING_REGISTER = new ConcurrentHashMap<>();

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private ApplicationContext ctx;

	public boolean canRun(IJob job) throws Exception {
		if (job.getJobWhen() != null) {
			Date date = new Date();			
			updateStatus(job, JobStatusType.CANCEL);
			return (date.before(job.getJobWhen()));
		}
		return true;
	}

	public void register(IJob job) throws Exception {
		if (canRun(job)) {
			Timing timing = (Timing) getExists(job);
			if (timing == null) {
				timing = (Timing) ctx.getBean("timing");
				timing.initTimer(job);
			}
			updateStatus(job, JobStatusType.WAITING);						
			TIMING_REGISTER.put(job.getKey(), timing);
		}
	}

	private void updateStatus(IJob job, JobStatusType status) throws Exception {
		job.setStatus(status);
		appConfig.getMsgService().insert(job);
	}

	public Timing getExists(IJob job) throws Exception {
		if (TIMING_REGISTER.containsKey(job.getKey())) {
			return (Timing) TIMING_REGISTER.get(job.getKey());
		}
		return null;
	}
	
	public void removeFromRegister(IJob job){
		if(TIMING_REGISTER.containsKey(job.getKey()))
			TIMING_REGISTER.remove(job.getKey());
	}

}
