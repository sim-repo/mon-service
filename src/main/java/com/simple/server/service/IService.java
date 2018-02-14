package com.simple.server.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.messaging.MessageChannel;

import com.simple.server.config.EndpointType;
import com.simple.server.config.MiscType;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.UniMinMsg;
import com.simple.server.domain.sys.SysMessage;
import com.simple.server.job.IJob;


public interface IService {
	void send(MessageChannel msgChannel, IContract msg) throws Exception;

	
	@Transactional()
	void insert(EndpointType endpointType, IJob job) throws Exception;
	@Transactional()
	void insert(EndpointType endpointType, SysMessage msg) throws Exception;
	@Transactional()
	List<?> readbyCriteria(EndpointType endpointType, Class<?> clazz, Map<String,Object> params, int topNum, Map<String,MiscType> orders) throws Exception;

}
