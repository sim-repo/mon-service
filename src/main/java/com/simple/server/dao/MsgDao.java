package com.simple.server.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.simple.server.config.MiscType;
import com.simple.server.domain.contract.UniMinMsg;
import com.simple.server.domain.sys.SysMessage;
import com.simple.server.job.IJob;

public interface MsgDao {	
	Session currentSession() throws Exception;
	void insert(IJob job) throws Exception;
	void insert(SysMessage msg) throws Exception;
	List<?> readbyCriteria(Class<?> clazz, Map<String,Object> params, int topNum, Map<String,MiscType> orders) throws Exception;
}
