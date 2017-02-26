package com.simple.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.simple.server.factory.TaskRunner;

@Component
public class PostInit implements ApplicationListener<ContextRefreshedEvent> {
	  @Autowired
	  TaskRunner taskRunner;
	  
	  @Autowired
	  AppConfig appConfig;	  
	  
	  @Value("${dirtyBusJsonQueueCapacity.int.property :100}")
	  private int dirtyBusJsonQueueCapacity;
	  
	  @Value("${dirtySrvJsonQueueCapacity.int.property :100}")
	  private int dirtySrvJsonQueueCapacity;
	  
	  @Value("${busClientMsgQueueCapacity.int.property :100}")
	  private int busClientMsgQueueCapacity;
	  	  
	  @Value("${busLogMsgQueueCapacity.int.property :100}")
	  private int busLogMsgQueueCapacity;
	  
	  @Value("${dirtySysMsgQueueCapacity.int.property :100}")
	  private int dirtySysMsgQueueCapacity;
	  
	  
	  @Override
	  public void onApplicationEvent(final ContextRefreshedEvent event) {
		  
		  appConfig.initDirtyBusJsonQueue(dirtyBusJsonQueueCapacity);
		  appConfig.initDirtySrvJsonQueue(dirtySrvJsonQueueCapacity);
		  appConfig.initBusClientMsgQueue(busClientMsgQueueCapacity);
		  appConfig.initBusLogMsgQueue(busLogMsgQueueCapacity);
		  appConfig.initSysMsgQueue(dirtySysMsgQueueCapacity);
		  taskRunner.initProcessing();
	  }
}