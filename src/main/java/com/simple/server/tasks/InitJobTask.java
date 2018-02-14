package com.simple.server.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.config.EndpointType;
import com.simple.server.config.JobStatusType;
import com.simple.server.job.TestCaseJob;
import com.simple.server.job.TaskHealthJob;
import com.simple.server.job.time.MySchedule;
import com.simple.server.lifecycle.HqlStepsType;
import com.simple.server.mediators.CommandType;

@SuppressWarnings("static-access")
@Service("InitJobTask")
@Scope("prototype")
public class InitJobTask extends AbstractTask {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private ApplicationContext ctx;

	Map<String, Object> map1 = new HashMap();
	Map<String, Object> map2 = new HashMap();
	
	{
		map1.put("status", JobStatusType.READY);
		map2.put("force_stop", JobStatusType.CANCEL);
	}

	
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null && arg.getClass() == CommandType.class) {
			switch ((CommandType) arg) {
			case WAKEUP_CONSUMER:
			case WAKEUP_ALL:
				arg = CommandType.WAKEUP_ALLOW;
				super.update(o, arg);
				break;
			case AWAIT_CONSUMER:
			case AWAIT_ALL:
				arg = CommandType.AWAIT_ALLOW;
				super.update(o, arg);
				break;
			}
		}
	}

		
	@Override
	public void task() throws Exception {

		List<TestCaseJob> jobs = (List<TestCaseJob>) appConfig.getMsgService().readbyCriteria(EndpointType.MON, TestCaseJob.class, map1, 0,null);
				
		//List<MySchedule> sch = (List<MySchedule>) appConfig.getMsgService().readbyCriteria(MySchedule.class, null, 0,null);
		
		//List<LifecycleJob> forcedJobs = (List<LifecycleJob>) appConfig.getMsgService().<LifecycleJob>readbyCriteria(LifecycleJob.class, map2, 0,null);
		
		//for(MySchedule s : sch){
		//	System.out.println(s);
		//}		
		
		while (basePhaser.getCurrNumPhase() != HqlStepsType.START.ordinal()) {
		}

		//for (LifecycleJob job : forcedJobs) {
		//	appConfig.getJobMgt().create(job);						
		//}
		
		for (int i=0; i <= 5; i++) {
			for (TestCaseJob job : jobs) {
				if(job.getOrderId() == i) {
					System.out.println("JOB");
					job.setAppConfig(appConfig);
					System.out.println("before reg, current i: "+i);
					appConfig.getJobMgt().register(job);
					System.out.println("after reg, current i: "+i);
					Thread.currentThread().sleep(100l);
				}
			}			
		}				
		Thread.currentThread().sleep(AppConfig.MAIN_TASK_AFTER_DONE_SLEEP);
	}
}
