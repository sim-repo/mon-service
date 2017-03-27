package com.simple.server.tasks;

import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.AContract;
import com.simple.server.domain.contract.UniMsg;
import com.simple.server.mediators.CommandType;
import com.simple.server.statistics.time.Timing;


@SuppressWarnings("static-access")
@Service("PerfomTask")
@Scope("prototype")
public class PerfomTask extends AbstractTask {

	@Autowired
	private AppConfig appConfig;

	private UniMsg uni = new UniMsg();
	
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
		//System.out.println("Perfomance-task");
		Thread.currentThread().sleep(Timing.getSleep());
		
		appConfig.getMsgService().send(appConfig.getSrvPerfomTopicChannel(), (AContract)uni);         
		
	}
}
