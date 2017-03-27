package com.simple.server.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.sys.SysMessage;


@Service("msgDao")
@Scope("singleton")
public class MsgDaoImpl implements MsgDao{

	@Autowired
	SessionFactory mysqlSessionFactory;
		
	@Override
	public Session currentSession() throws Exception{				
		return mysqlSessionFactory.getCurrentSession();
		
	}


	@Override
	@Transactional
	public void insertBus(List<IContract> msgList) throws Exception {
		int count=0;
		for(IContract msg: msgList){
			try{			
				//System.out.println("log: "+msg);
				currentSession().saveOrUpdate(msg);	
			}catch(SQLException e){
				e.printStackTrace();
			}			
			if (++count % 50 == 0 ) {
				currentSession().flush();
				currentSession().clear();
			}
		}
	}

	@Override
	public void insertSys(List<SysMessage> msgList) throws Exception {
		int count=0;
		for(SysMessage msg: msgList){
			currentSession().save(msg);	
			if (++count % 50 == 0 ) {
				currentSession().flush();
				currentSession().clear();
			}
		}		
	}
	@Transactional
	@Override
	public void insertSql(String sql) throws Exception{
		SQLQuery query = currentSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	@Transactional
	public List<IContract> readAll(IContract msg) throws Exception {
		Criteria criteria = currentSession().createCriteria(msg.getClass());
		List<IContract> res = criteria.list();	
		return res;
	}

	@Override
	@Transactional
	public List<IContract> readbySQLCriteria(IContract msg, String sql) throws Exception {
		Criteria criteria = currentSession().createCriteria(msg.getClass()).add(Restrictions.sqlRestriction(sql));
		List<IContract> res = criteria.list();	
		return res;
	}	
}
