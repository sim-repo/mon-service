package com.simple.server.tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.SysMsg;
import com.simple.server.mediators.CommandType;
import com.simple.server.statistics.time.Timing;

@Service("SrvDispatcherTask")
@Scope("prototype")
public class SrvDispatcherTask extends AbstractTask {

	@Autowired
	private AppConfig appConfig;

	
    private final static Integer MAX_NUM_ELEMENTS = 100;
    private List<String> list = new ArrayList();
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
        if (appConfig.getDirtySrvJsonQueue().drainTo(list, MAX_NUM_ELEMENTS) == 0) {
            list.add(appConfig.getDirtySrvJsonQueue().take());
        }
                       
        Thread.currentThread().sleep(Timing.getSleep());
        
       //while (appConfig.getDirtyJsonQueue().size()>0) {
       // 	Thread.currentThread().sleep(Timing.getTimeMaxSleep());	 
        	appConfig.getDirtySrvJsonQueue().drainTo(list, MAX_NUM_ELEMENTS);
    	//}
        
        try{
	        for(String json: list) {    	        		       
	        	IContract message =  mapper.readValue(json, IContract.class);
	        	Thread.currentThread().sleep(Timing.getSleep());	 
	        	if(message.getClass().equals(SysMsg.class)){
	        		SysMsg msg = (SysMsg)message;	
	        		msg.setLogDatetime(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSSz").format(Calendar.getInstance().getTime()));
	        		msg.setLoggerId(this.toString());
	        		appConfig.getSysMsgQueue().put(msg);
	        	}	        	
	        }
        }catch(Exception e){
        	System.out.println("error in dispatcher task:");
        	e.printStackTrace();
        }               
      
        list.clear();
    }

}