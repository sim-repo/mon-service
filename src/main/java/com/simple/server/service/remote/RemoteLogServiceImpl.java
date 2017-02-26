package com.simple.server.service.remote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.IContract;

public class RemoteLogServiceImpl implements IRemoteLogService{

	@Autowired
	AppConfig appConfig;
	
	public AppConfig getAppConfig() throws Exception {
		return appConfig;
	}
	
	@Override
	public List<IContract> getAllMsg(IContract msg) throws Exception {
		List<IContract> res = getAppConfig().getMsgService().readAll(msg);	
		return res;
	}

	@Override
	public List<IContract> getMsgBySqlCriteria(IContract msg, String sql) throws Exception {
		List<IContract> res = getAppConfig().getMsgService().readbySQLCriteria(msg, sql);	
		return res;
	}
		
}
