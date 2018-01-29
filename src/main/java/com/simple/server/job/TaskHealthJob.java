package com.simple.server.job;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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

	
	private Integer orderId;
	private Integer groupId;
	private String juuid;
	
	@Autowired
	private AppConfig appConfig;	
	
	@Override
	public void run() throws Exception {
		/*
		MonMsg mon = new MonMsg();
		mon.setTaskClazz("com.simple.server.task.PubTask");
		mon.setBeanStatClazz("perfomancerStat");
		//mon.setWhen(DateTimeConverter.add2CurDate(0, 0, 1));
		mon.setUntil(DateTimeConverter.add2CurDate(0, 0, 4));
		mon.setDelay(100l);
		mon.setPeriod(1000l);
		mon.setOperationType(OperationType.MON_START.toValue());
		appConfig.getMsgService().send(appConfig.getSrvPerfomTopicChannel(), (AContract) mon);
		*/
		
		CyclicBarrier cb = new CyclicBarrier(2);
		
		
		Runnable barrier1Action = new Runnable() {		
			private CyclicBarrier cb = null;
			Runnable setCB(CyclicBarrier cb) {
				this.cb = cb;
				return this;
			}
		    public void run() {
		        System.out.println("BarrierAction 1 executed ");
		        try {
					cb.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println("BarrierAction 1 well done ");
		    }
		}.setCB(cb);
		
		Runnable barrier2Action = new Runnable() {		
			private CyclicBarrier cb = null;
			Runnable setCB(CyclicBarrier cb) {
				this.cb = cb;
				return this;
			}
		    public void run() {
		        System.out.println("BarrierAction 2 executed ");
		        try {
					Thread.currentThread().sleep(4000l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					cb.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println("BarrierAction 2 well done ");
		    }
		}.setCB(cb);
		
		

		new Thread(barrier1Action).start();
		new Thread(barrier2Action).start();
		
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

	@Override
	public void setAppConfig(AppConfig appConfig) {
		// TODO Auto-generated method stub
		
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Override
	public void setJuuid(String juud) {
		this.juuid = juud;		
	}

	@Override
	public String getJuuid() {
		return this.juuid;
	}
	
	

}
