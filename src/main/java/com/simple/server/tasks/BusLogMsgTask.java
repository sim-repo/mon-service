package com.simple.server.tasks;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.Jolokia;
import com.simple.server.http.HttpImpl;
import com.simple.server.mediators.CommandType;
import com.simple.server.statistics.time.Timing;

@SuppressWarnings("static-access")
@Service("BusLogMsgTask")
@Scope("prototype")
public class BusLogMsgTask extends AbstractTask  {
	
	@Autowired
	private AppConfig appConfig;

	private final static Integer MAX_NUM_ELEMENTS = 100000;
	private List<IContract> list = new ArrayList<IContract>();

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
		
		
		//System.out.println("BusLogMsgTask");
		/*
		Jolokia s = HttpImpl.doGet2("http://localhost:8080/jolokia-war-1.3.5/read/java.lang:type=Memory/HeapMemoryUsage");
		
		StringBuilder sql = new StringBuilder("INSERT INTO `jdb`.`sys stat memory`(`timestamp`,`memory_type`,`jvm_id`,`used`,`committed`,`init`,`max`)");
		sql.append(String.format("VALUES('%s', '%s', '%s', '%s', %s, %s, %s);",				
				s.getTimestamp(),
				s.getRequest().getAttribute(),
				"id#1",
				s.getValue().getUsed(),
				s.getValue().getCommitted(),
				s.getValue().getInit(),
				s.getValue().getMax()));
		
		appConfig.getMsgService().insertSql(sql.toString());
			*/
		Thread.currentThread().sleep(Timing.getSleep());
	}
}
