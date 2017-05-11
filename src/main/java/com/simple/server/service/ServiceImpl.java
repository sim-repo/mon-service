package com.simple.server.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.config.MiscType;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.SysMessage;
import com.simple.server.job.IJob;
import com.simple.server.util.ObjectConverter;


@Service("msgService")
@Scope("singleton")
public class ServiceImpl implements IService{
	
	@Autowired
	AppConfig appConfig;

	final private static String MON_HEADER_NAME = "clazz";
	
	final private static String SERVICE_ID = "mon"; 
	
	public AppConfig getAppConfig() throws Exception {
		return appConfig;
	}
	
	@Override
	public void send(MessageChannel msgChannel, IContract msg) throws Exception {
		
		if (msgChannel==null || msg==null)
			return;
				
		String json = ObjectConverter.objectToJson(msg);			
		msgChannel.send(MessageBuilder.withPayload( json ).setHeader(MON_HEADER_NAME, msg.getClass().getSimpleName()).build());			
	}
	

	@Override
	public void insert(IJob job) throws Exception {
		 getAppConfig().getMsgDao().insert(job);		
	}	
	
	@Override
	public List<?> readbyCriteria(Class<?> clazz, Map<String, Object> params, int topNum, Map<String, MiscType> orders) throws Exception {
		List<?> res = getAppConfig().getMsgDao().readbyCriteria(clazz, params, topNum, orders);
		return res;
	}

	
}
