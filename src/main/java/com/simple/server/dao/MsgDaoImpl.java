package com.simple.server.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.simple.server.config.EndpointType;
import com.simple.server.config.MiscType;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.UniMinMsg;
import com.simple.server.domain.sys.SysMessage;
import com.simple.server.job.IJob;
import com.simple.server.mediators.CommandType;


@Service("msgDao")
@Scope("singleton")
public class MsgDaoImpl implements MsgDao{

	@Autowired
	SessionFactory testTargetSessionFactory;
		
	@Autowired
	SessionFactory monSessionFactory;
	
	@Override
	public Session currentSession(EndpointType endpointType) throws Exception{		
		switch ((EndpointType) endpointType) {
			case TESTED:
				return testTargetSessionFactory.getCurrentSession();
			case MON:
			return monSessionFactory.getCurrentSession();
		}
		return monSessionFactory.getCurrentSession();
	}
	
	@Override
	public void insert(EndpointType endpointType, IJob job) throws Exception {
		currentSession(endpointType).saveOrUpdate(job);	
	}
	
	@Override
	public void insert(EndpointType endpointType, SysMessage msg) throws Exception {
		currentSession(endpointType).saveOrUpdate(msg);		
	}
	
	@Override
	public List<?> readbyCriteria(EndpointType endpointType, Class<?> clazz, Map<String,Object> params, int topNum, Map<String,MiscType> orders) throws Exception{		
		Criteria criteria = currentSession(endpointType).createCriteria(clazz);	
		if(params != null)
			for(Map.Entry<String,Object> pair: params.entrySet()){						
				criteria.add(Restrictions.eq(pair.getKey(), pair.getValue()));			
			}		
		if(topNum != 0){
			criteria.setMaxResults(topNum);
		}
		if(orders != null && orders.size() != 0){
			for(Map.Entry<String, MiscType> pair: orders.entrySet()){
				String fld = pair.getKey();								
				if(pair.getValue().equals(MiscType.asc))
					criteria.addOrder(Order.asc(fld));
				else
					if(pair.getValue().equals(MiscType.desc))
						criteria.addOrder(Order.desc(fld));
			}
		}
						
		List<?> res = criteria.list();				
		return res;
	}

	
}
