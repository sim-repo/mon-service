package com.simple.server.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.SysMessage;
import com.simple.server.lifecycle.HqlStepsType;
import com.simple.server.mediators.CommandType;

@Service("SrvMsgTask")
@Scope("prototype")
public class SrvMsgTask extends AbstractTask {
	@Autowired
	private AppConfig appConfig;

	
    private final static Integer MAX_NUM_ELEMENTS = 100000;
    private List<SysMessage> list = new ArrayList();
    private ObjectMapper mapper = new ObjectMapper();   
    
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg != null && arg.getClass() == CommandType.class) {
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
    	try{
	    	if (appConfig.getSysMsgQueue().drainTo(list, MAX_NUM_ELEMENTS) == 0) 
	            list.add(appConfig.getSysMsgQueue().take());
	      
        	appConfig.getMsgService().insertSys(list);
        } catch(Exception e){
        	e.printStackTrace();
        }               
        list.clear();
    }

}