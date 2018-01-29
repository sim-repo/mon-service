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
		
		if(job.getJobPeriod() != null && job.getJobPeriod() != 0l) {
			this.period = job.getJobPeriod();
			setUntil(job);
		}
			
		firstRun = true;
		this.when = job.getJobWhen();		
		this.delay = job.getJobDelay();
		
		if (when != null) {
			schedule(when, period);
		} else if (delay != null && delay != 0l) {
			schedule(delay, period);
		}		
		this.job = job;
	}

	private void schedule(Date when, Long period) {
		timer = new Timer(false);
		if(period != null && period != 0l) {
			timer.scheduleAtFixedRate(this, when, period);
		}else {
			timer.schedule(this, when);
		}
	}

	private void schedule(Long delay, Long period) {
		timer = new Timer(false);
		if(period != null && period != 0l) {
			timer.scheduleAtFixedRate(this, delay, period);
		}else {
			timer.schedule(this, delay);
		}
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

	public void clear() {
		this.timer = null;
		this.when = null;
		this.until = null;
		this.delay = null;
		this.period = null;				
	}
	
	public void setNextJob(IJob job) {
		this.job = job;
		firstRun = true;
	}

	private void afterDone() throws Exception {
		appConfig.getJobMgt().jobDone(this.job, this);			
	}
	
	private void handleErr() throws Exception {
		appConfig.getJobMgt().jobErr(this.job, this);	
	}
	
	
	public void stopTiming() {
		timer.cancel();
	}
		

	@Override
	public void run() {
		try {			
			if (firstRun) {
				firstRun = false;
				appConfig.getJobMgt().jobStarted(job);
			}

			if (until != null) {
				Date date = new Date();
				if (date.after(until)) {					
					afterDone();
				} else {
					job.run();
				}
			}else {
				job.run();				
				afterDone();
			}			
		} catch (Exception e) {			
			try {
				handleErr();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public void setUntil(Date until) {
		this.until = until;
	}

	public void setDelay(Long delay) {
		this.delay = delay;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public void setJob(IJob job) {
		this.job = job;
	}
	
	
	
}
