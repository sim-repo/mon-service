package com.simple.server.service;

import java.util.List;

import org.springframework.messaging.MessageChannel;

import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.SysMessage;


public interface IService {
	void send(MessageChannel msgChannel, IContract msg) throws Exception;
	void insertBus(List<IContract> msgList) throws Exception;
	void send(SysMessage msg) throws Exception;
	void insertSys(List<SysMessage> msg) throws Exception;	
	void insertSql(String sql) throws Exception;
	List<IContract> readAll(IContract msg) throws Exception;
	List<IContract> readbySQLCriteria(IContract msg, String sql) throws Exception;
}
