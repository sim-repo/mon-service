package com.simple.server.tasks;

import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.config.OperationType;
import com.simple.server.domain.contract.AContract;
import com.simple.server.domain.contract.MonMsg;
import com.simple.server.job.time.Timing;
import com.simple.server.lifecycle.HqlStepsType;
import com.simple.server.mediators.CommandType;
import com.simple.server.util.DateTimeConverter;

@SuppressWarnings("static-access")
@Service("PerfomTask")
@Scope("prototype")
public class PerfomTask extends AbstractTask {

	@Autowired
	private AppConfig appConfig;

	private MonMsg mon = new MonMsg();

	private int count = 0;

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

		mon.setTaskClazz("com.simple.server.task.PubTask");
		mon.setBeanStatClazz("perfomancerStat");
		//mon.setWhen(DateTimeConverter.add2CurDate(0, 0, 1));
		mon.setUntil(DateTimeConverter.add2CurDate(0, 0, 4));
		mon.setDelay(100l);
		mon.setPeriod(1000l);
		mon.setOperationType(OperationType.MON_START.toValue());
		while (basePhaser.getCurrNumPhase() != HqlStepsType.START.ordinal()) {
		}
	
		count++;
		if (count == 10) {
			System.out.println("Perfomance-task");

			Thread.currentThread().sleep(AppConfig.MAIN_SLEEP);
			appConfig.getMsgService().send(appConfig.getSrvPerfomTopicChannel(), (AContract) mon);
			count = 0;
		}
	}
}
