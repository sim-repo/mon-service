package com.simple.server.job.time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.config.JobStatusType;
import com.simple.server.job.IJob;
import com.simple.server.util.DateTimeConverter;

@Service("timing")
@Scope("prototype")
public class Timing extends TimerTask {

	

	@Autowired
	private AppConfig appConfig;

	private Timer timer = null;
	private Date when = null;
	private Date until = null;
	private Long delay = null;
	private Long period = null;
	private boolean firstRun = false;
	private IJob job = null;


	public void initTimer(IJob job) throws Exception{		
		setUntil(job);
		firstRun = true;
		this.when = job.getJobWhen();
		this.period = job.getJobPeriod();
		this.delay = job.getJobDelay();
		if (when != null) {
			schedule(when, period);
		} else if (delay != 0l) {
			schedule(delay, period);
		}	
		this.job = job;
	}

	private void schedule(Date when, long period) {
		timer = new Timer(false);
		timer.scheduleAtFixedRate(this, when, period);
	}

	private void schedule(long delay, long period) {
		timer = new Timer(false);
		timer.scheduleAtFixedRate(this, delay, period);
	}

	public void setUntil(IJob job) {
		if (job.getJobUntil() != null)
			this.until = job.getJobUntil();
		else {
			if (job.getJobWhen() != null)
				this.until = DateTimeConverter.add(job.getJobWhen(), 0, 0, 10);
			else{
				this.until = DateTimeConverter.add(new Date(), 0, 0, 10);
			}
		}
	}

	private void clear() {
		this.timer = null;
		this.when = null;
		this.until = null;
		this.delay = null;
		this.period = null;
		appConfig.getJobMgt().removeFromRegister(job);		
	}

	private void afterDone() throws Exception {
		clear();
		job.setStatus(JobStatusType.DONE);
		appConfig.getMsgService().insert(job);
		this.job = null;
	}

	@Override
	public void run() {
		try {			
			if (firstRun) {
				firstRun = false;
				job.setStatus(JobStatusType.RUNNING);
				appConfig.getMsgService().insert(job);
			}

			if (until != null) {
				Date date = new Date();
				if (date.after(until)) {
					timer.cancel();
					afterDone();
				} else {
					job.run();
				}
			}
		} catch (Exception e) {
			// TODO Exception to log
			e.printStackTrace();
		}
	}
}
