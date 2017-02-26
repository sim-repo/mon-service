package com.simple.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.SysMessage;


@Service("msgService")
@Scope("singleton")
public class ServiceImpl implements IService{
	
	@Autowired
	AppConfig appConfig;

	public AppConfig getAppConfig() throws Exception {
		return appConfig;
	}
	
	@Override
	public void send(IContract message) throws Exception {
		getAppConfig().getMsgDao().send(message);		
	}

	@Override
	public void insertBus(List<IContract> msgList) throws Exception {
		getAppConfig().getMsgDao().insertBus(msgList);
	}

	@Override
	public void send(SysMessage message) throws Exception {
		// TODO Auto-generated method stub		
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
