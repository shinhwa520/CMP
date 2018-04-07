package com.cmp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.SysLogDAO;
import com.cmp.model.SysLog;

@Repository
@Transactional
public class SysLogDAOImpl extends BaseDaoHibernate implements SysLogDAO {
	
	@Override
	public SysLog saveSysLog(SysLog sysLog) {
		return (SysLog) getHibernateTemplate().merge(sysLog);
//	   sessionFactory.getCurrentSession().save(user);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysLog> findSysLogByUserAction(String userId, String action, Integer start,Integer length) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select s from SysLog s where 1=1 ");
		if(StringUtils.isNotBlank(userId)){
			sb.append(" and s.user.id = :userId ");
		}
		if(StringUtils.isNotBlank(action)){
			sb.append(" and s.action = :action ");
		}
		sb.append(" order by s.createTime desc ");
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if(StringUtils.isNotBlank(userId)){
			q.setParameter("userId", userId);
		}
	    if(StringUtils.isNotBlank(action)){
			q.setParameter("action", action);
		}
	    q.setFirstResult(start).setMaxResults(length);
	    return (List<SysLog>) q.list();
	}
	
	@Override
	public long countSysLogByUserAction(String userId, String action) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from SysLog s where 1=1 ");
		if(StringUtils.isNotBlank(userId)){
			sb.append(" and s.user.id = :userId ");
		}
		if(StringUtils.isNotBlank(action)){
			sb.append(" and s.action = :action ");
		}
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
	    if(StringUtils.isNotBlank(userId)){
			q.setParameter("userId", userId);
		}
	    if(StringUtils.isNotBlank(action)){
			q.setParameter("action", action);
		}
	    return (long) q.uniqueResult();
	}
}
