package com.simple.server.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.config.JobStatusType;
import com.simple.server.config.OperationType;
import com.simple.server.domain.contract.AContract;
import com.simple.server.domain.contract.MonMsg;
import com.simple.server.util.DateTimeConverter;



@Service("taskHealthJob")
@Scope("singleton")
public class TaskHealthJob implements IJob{

	@Autowired
	private AppConfig appConfig;	
	
	@Override
	public void run() throws Exception {
		MonMsg mon = new MonMsg();
		mon.setTaskClazz("com.simple.server.task.PubTask");
		mon.setBeanStatClazz("perfomancerStat");
		//mon.setWhen(DateTimeConverter.add2CurDate(0, 0, 1));
		mon.setUntil(DateTimeConverter.add2CurDate(0, 0, 4));
		mon.setDelay(100l);
		mon.setPeriod(1000l);
		mon.setOperationType(OperationType.MON_START.toValue());
		appConfig.getMsgService().send(appConfig.getSrvPerfomTopicChannel(), (AContract) mon);
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getJobWhen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getJobUntil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getJobDelay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getJobPeriod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobStatusType getStatus() {
		return null;
	}

	public void setStatus(JobStatusType status) {
		
	}

}
