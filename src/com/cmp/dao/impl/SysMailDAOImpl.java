package com.cmp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmp.dao.SysMailDAO;
import com.cmp.model.SysMail;

@Repository
@Transactional
public class SysMailDAOImpl extends BaseDaoHibernate implements SysMailDAO {
	
	@Override
	public SysMail saveSysMail(SysMail sysMail) {
		return (SysMail) getHibernateTemplate().merge(sysMail);
	}
	
	@Override
	public void deleteSysMail(SysMail sysMail) {
		getHibernateTemplate().delete(sysMail);
	}
	
	@Override
	public void deleteSysMailById(long id) {
		deleteSysMail(findSysMailById(id));
	}
	
	@Override
	public void deleteSysMails(List<SysMail> sysMail) {
		getHibernateTemplate().deleteAll(sysMail);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SysMail findSysMailById(long id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from SysMail m ")
		  .append(" where 1=1 ")
		  .append(" and m.id = ? ");
		List<SysMail> returnList = (List<SysMail>)getHibernateTemplate().find(sb.toString(), id);
		return returnList.isEmpty() ? null : returnList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysMail> findSysMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead, Integer start,Integer length) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select m ")
		.append(" from SysMail m ")
		.append(" where 1=1 ")
		.append(" and m.alive = :alive ");
		if(StringUtils.isNotBlank(mailFromId)){
			sb.append(" and m.mailFrom.id = :mailFromId ");
		}
		if(StringUtils.isNotBlank(mailToId)){
			sb.append(" and m.mailTo.id = :mailToId ");
		}
		if(null!=beenRead){
			sb.append(" and m.beenRead = :beenRead ");
		}
		sb.append(" order by m.updateTime desc ");
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
		if(StringUtils.isNotBlank(mailFromId)){
			q.setParameter("mailFromId", mailFromId);
		}
		if(StringUtils.isNotBlank(mailToId)){
			q.setParameter("mailToId", mailToId);
		}
		if(null!=beenRead){
			q.setParameter("beenRead", beenRead);
		}
		q.setParameter("alive", alive);
	    q.setFirstResult(start).setMaxResults(length);
	    return (List<SysMail>) q.list();
	}
	
	public long countSysMailByUser(String mailFromId, String mailToId, boolean alive, Boolean beenRead) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) ")
		.append(" from SysMail m ")
		.append(" where 1=1 ")
		.append(" and m.alive = :alive ");
		if(StringUtils.isNotBlank(mailFromId)){
			sb.append(" and m.mailFrom.id = :mailFromId ");
		}
		if(StringUtils.isNotBlank(mailToId)){
			sb.append(" and m.mailTo.id = :mailToId ");
		}
		if(null!=beenRead){
			sb.append(" and m.beenRead = :beenRead ");
		}
	    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
	    Query<?> q = session.createQuery(sb.toString());
		if(StringUtils.isNotBlank(mailFromId)){
			q.setParameter("mailFromId", mailFromId);
		}
		if(StringUtils.isNotBlank(mailToId)){
			q.setParameter("mailToId", mailToId);
		}
		if(null!=beenRead){
			q.setParameter("beenRead", beenRead);
		}
		q.setParameter("alive", alive);
	    return (long) q.uniqueResult();
	}
}
