package com.simple.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.SysMessage;
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
		//System.out.println("mon::::: "+json);
		msgChannel.send(MessageBuilder.withPayload( json ).setHeader(MON_HEADER_NAME, msg.getClass().getSimpleName()).build());			
	}
	
	@Override
	public void send(SysMessage message) throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void insertBus(List<IContract> msgList) throws Exception {
		getAppConfig().getMsgDao().insertBus(msgList);
	}

	@Override
	public void insertSys(List<SysMessage> msg) throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void insertSql(String sql) throws Exception {
		getAppConfig().getMsgDao().insertSql(sql);
		
	}

	@Override
	public List<IContract> readAll(IContract msg) throws Exception {		
		List<IContract> res =  getAppConfig().getMsgDao().readAll(msg);
		return res;
	}

	@Override
	public List<IContract> readbySQLCriteria(IContract msg, String sql) throws Exception {
		List<IContract> res =  getAppConfig().getMsgDao().readbySQLCriteria(msg, sql);
		return res;
	}		
}
