package com.simple.server.job.time;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.config.JobStatusType;
import com.simple.server.job.IJob;
import com.simple.server.job.TestCaseJob;

@Service("jobMgt")
@Scope("singleton")
public class JobMgt {

	// <job.groupId,..>
	private static final ConcurrentHashMap<String, TimerTask> TIMING_REGISTER = new ConcurrentHashMap<>();
	// <hashcode(job.groupId+job.orderId),..>
	private static final ConcurrentHashMap<Integer, IJob> JOB_REGISTER = new ConcurrentHashMap<>();
	
	
	@Autowired
	private AppConfig appConfig;

	@Autowired
	private ApplicationContext ctx;

	private synchronized Integer getJobHashCode(IJob job, Integer offset) {
		if(job.getGroupId() != null && job.getOrderId() != null)
			return job.getGroupId().hashCode()+job.getOrderId().hashCode()+offset;
		return 0;
	}
	
	public synchronized boolean canRun(IJob job) throws Exception {						
		if (job.getJobWhen() != null) {
			Date date = new Date();			
			updateStatus(job, JobStatusType.CANCEL);
			return (date.before(job.getJobWhen()));
		}
		return true;
	}

	public synchronized void register(IJob job) throws Exception {
		
		Integer jobHashCode = getJobHashCode(job,0);		
		if(JOB_REGISTER.containsKey(jobHashCode))
			return;
		
		if (canRun(job)) {						
			JOB_REGISTER.put(jobHashCode, job);
			Timing timing = (Timing) getExists(job);
			if (timing == null) {
				timing = (Timing) ctx.getBean("timing");
				timing.initTimer(job);
			}
			updateStatus(job, JobStatusType.WAITING);						
			TIMING_REGISTER.put(job.getGroupId(), timing);
		}
	}
	

	private synchronized void updateStatus(IJob job, JobStatusType status) throws Exception {
		job.setStatus(status);
		System.out.println(job.getKey()+":"+status);
		appConfig.getMsgService().insert(job);
	}

	public synchronized Timing getExists(IJob job) throws Exception {
		if (TIMING_REGISTER.containsKey(job.getGroupId())) {
			return (Timing) TIMING_REGISTER.get(job.getGroupId());
		}
		return null;
	}
	
	private synchronized void tryNext(IJob curJob, Timing timing) throws Exception{
		Integer hashcode = getJobHashCode(curJob,1);
		
		if(JOB_REGISTER.containsKey(hashcode)) {
			//timing.setNextJob(JOB_REGISTER.get(hashcode));
			timing.stopTiming();
			timing.clear();
			timing = null;
			timing = (Timing) ctx.getBean("timing");	
			IJob nextJob = JOB_REGISTER.get(hashcode);
			nextJob.setJuuid(curJob.getJuuid());
			timing.initTimer(nextJob);
		}else {
			timing.stopTiming();
			timing.clear();
			removeGroupFromRegister(curJob.getGroupId());
		}
	}
	
	public synchronized void jobStarted(IJob job) throws Exception{
		updateStatus(job, JobStatusType.RUNNING);
	}
	
	public synchronized void jobErr(IJob job, Timing timing) throws Exception {		
		updateStatus(job, JobStatusType.ERROR);					
		timing.stopTiming();
		timing.clear();
	}
	
	public synchronized void jobDone(IJob job, Timing timing) throws Exception {
		updateStatus(job, JobStatusType.DONE);		
		tryNext(job, timing);
	}
			
	public synchronized void removeGroupFromRegister(String jobGroupId) throws Exception{
		Map<String, Object> map1 = new HashMap();
		map1.put("groupId", jobGroupId);
		List<TestCaseJob> jobs = (List<TestCaseJob>) appConfig.getMsgService().readbyCriteria(TestCaseJob.class, map1, 0,null);		
		Integer maxlen = jobs.size();
		
		
		for(IJob j: jobs) {
			Integer jobHashCode = getJobHashCode(j,0);		
			if(JOB_REGISTER.containsKey(jobHashCode)) {
				System.out.println(j.getKey()+": removed from register");				
				JOB_REGISTER.remove(jobHashCode);	
			}
		}
	
		TIMING_REGISTER.remove(jobGroupId);	
	}

}
