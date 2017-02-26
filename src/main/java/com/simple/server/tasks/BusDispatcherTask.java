package com.simple.server.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.server.config.AppConfig;
import com.simple.server.domain.AContract;
import com.simple.server.domain.AbstractLogMsg;
import com.simple.server.domain.contract.IContract;
import com.simple.server.mediators.CommandType;
import com.simple.server.statistics.time.Timing;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@SuppressWarnings("static-access")
@Service("BusDispatcherTask")
@Scope("prototype")
public class BusDispatcherTask extends AbstractTask {

	@Autowired
	private AppConfig appConfig;

	private final static Integer MAX_NUM_ELEMENTS = 100000;
	private List<String> list = new ArrayList<String>();
	private ObjectMapper mapper = new ObjectMapper();

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
		if (appConfig.getDirtyBusJsonQueue().drainTo(list, MAX_NUM_ELEMENTS) == 0) {
			list.add(appConfig.getDirtyBusJsonQueue().take());
		}
		Thread.currentThread().sleep(Timing.getSleep());

		// while (appConfig.getDirtyJsonQueue().size()>0) {
		// Thread.currentThread().sleep(Timing.getTimeMaxSleep());
		appConfig.getDirtyBusJsonQueue().drainTo(list, MAX_NUM_ELEMENTS);
		// }

		try {
			for (String json : list) {
				IContract msg = mapper.readValue(json, IContract.class);
				Thread.currentThread().sleep(Timing.getSleep());			
				if (msg instanceof AContract) {					
					appConfig.getBusClientMsgQueue().put((AContract)msg);
				}			
				else if(msg instanceof AbstractLogMsg){
					appConfig.getBusLogMsgQueue().put((AbstractLogMsg)msg);
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}

		list.clear();
	}

}