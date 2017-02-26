package com.simple.server.dao;

import java.util.List;
import org.hibernate.Session;

import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.SysMessage;

public interface MsgDao {
	void send(IContract message) throws Exception;
	void send(SysMessage message) throws Exception;
	void insertBus(List<IContract> msgList) throws Exception;
	Session currentSession() throws Exception;
	void insertSys(List<SysMessage> msgList) throws Exception;
	void insertSql(String sql) throws Exception;
	List<IContract> readAll(IContract msg) throws Exception;
	List<IContract> readbySQLCriteria(IContract msg, String sql) throws Exception;
}
